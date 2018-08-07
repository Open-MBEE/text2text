package gov.nasa.jpl.text2text;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.json.JSONObject;

import gov.nasa.jpl.jsonPath2.*;

import static gov.nasa.jpl.jsonPath2.Utils.*;

/**
 * Combines all necessary elements for translation.
 * 
 * @author David K. Legg
 *
 */
public class Translator {
  private TranslationDescription translationDescription;
//  private Collection<JsonNode> libraries;
//  private Function<String, JsonNode> preprocessor;
  
  private Boolean useParallel;
  
  private Logger logger;
  
  public static void main(String[] args) {
    try {
      // first element of each sub-list is the canonical name for that option, all others are just synonyms
      List<String> inFileOptions    = Arrays.asList("inFile", "inputFile", "input", "in", "file", "if", "f", "i");
      List<String> outFileOptions   = Arrays.asList("outFile", "outputFile", "output", "out", "of", "o");
      List<String> logFileOptions   = Arrays.asList("logFile", "log", "lf", "l");
      List<String> errorFileOptions = Arrays.asList("errorFile", "errFile", "err", "ef", "e");
      List<String> configOptions    = Arrays.asList("config", "configuration", "conf", "con", "cfg");
      List<String> librariesOptions        = TranslationDescription.Plugins.librariesOptions;
      List<String> preprocessorOptions     = TranslationDescription.Plugins.preprocessorOptions;
      List<String> nativeFunctionOptions   = TranslationDescription.Plugins.nativeFunctionOptions;
      List<String> sanitizeFunctionOptions = TranslationDescription.Plugins.sanitizeFunctionOptions;
      List<String> translationDescriptionOptions = Arrays.asList("translationDescription", "td");
      
      List<String> parallelFlags = Arrays.asList("parallel", "para", "p");
      List<String> helpFlags    = Arrays.asList("help", "h");
      List<String> quietFlags   = Arrays.asList("quiet", "q");
      List<String> silentFlags  = Arrays.asList("silent", "s");
      List<String> verboseFlags = Arrays.asList("verbose", "v");
      List<String> debugFlags   = Arrays.asList("debug", "d");
      List<String> printModelFlags = Arrays.asList("printModel", "pm");
      
      List<List<String>> longOptionLists = Arrays.asList(inFileOptions, outFileOptions, logFileOptions, errorFileOptions,
          translationDescriptionOptions, librariesOptions, preprocessorOptions,
          nativeFunctionOptions, sanitizeFunctionOptions, configOptions);
      
      List<List<String>> flagLists = Arrays.asList(parallelFlags, helpFlags, quietFlags, silentFlags, verboseFlags, debugFlags, printModelFlags);
          
      Map<String, String> argMap = optionReader(longOptionLists, flagLists).apply(args);
      
      // Define some utility closures for getting options:
      
      Function<List<String>, Optional<Boolean>> testFlag = flags -> Optional.ofNullable( argMap.get(flags.get(0)) ).map( "true"::equalsIgnoreCase );
      Function<Function<String,String>,Function<String,Stream<String>>> withOrWithout = f -> t ->
        Stream.of(f.apply(t), t);
        
      Function<String, Stream<String>> tryFiles = name -> Stream.of(name)
          .flatMap(withOrWithout.apply( s -> "/" + s ))
          .flatMap(withOrWithout.apply( s -> s + ".json" ));
        
      Function<String, Optional<String>> readFilePath = filePath -> 
        tryFiles.apply(filePath) // check file paths:
          .map(optOnError( s -> new FileInputStream(s) ))
          .flatMap( optStream() )
          .map(optOnError( Utils::streamToString ) )
          .flatMap( optStream() )
          .findAny();
      
      
      Logger logger = new Logger();
      
      if (testFlag.apply(helpFlags).orElse(false)) {
        // if you ask for help, just construct the help message and quit
        logger.log("Options:");
        for (List<String> longOptionList : longOptionLists) {
          logger.log(longOptionList.stream().collect( Collectors.joining(", ") ));
        }
        
        logger.log("Flags:");
        for(List<String> flagList : flagLists) {
          logger.log(flagList.stream().map(s -> s + "[Off]").collect( Collectors.joining(", ") ));
        }
        return;
      }
      
      // quiet down standard logging only
      if (testFlag.apply(quietFlags).orElse(false)) logger.setMaxVerbosity(Logger.VERBOSITY.QUIET, Logger.MSG_TYPE.DEFAULT);
      // shut down all logging except output
      if (testFlag.apply(silentFlags).orElse(false)) logger.setMaxVerbosity(Logger.VERBOSITY.SILENT);
      // override all other logging options, set all normal logging to verbose:
      if (testFlag.apply(verboseFlags).orElse(false)) {
        logger.setMinVerbosity(Logger.VERBOSITY.VERBOSE, Logger.MSG_TYPE.DEFAULT);
        logger.setMinVerbosity(Logger.VERBOSITY.VERBOSE, Logger.MSG_TYPE.WARNING);
        logger.setMinVerbosity(Logger.VERBOSITY.VERBOSE, Logger.MSG_TYPE.ERROR);
      }
      // set debugging output based on its own flag
      if (testFlag.apply(debugFlags).orElse(false)) {
        logger.setMinVerbosity(Logger.VERBOSITY.VERBOSE, Logger.MSG_TYPE.DEBUG);
      } else {
        logger.setMaxVerbosity(Logger.VERBOSITY.QUIET, Logger.MSG_TYPE.DEBUG);
      }
      // specifically set the "other" channel based on the printModel flag, if set:
      if (testFlag.apply(printModelFlags).orElse(false)) {
        logger.setMinVerbosity(Logger.VERBOSITY.VERBOSE, Logger.MSG_TYPE.OTHER);
      } else {
        logger.setMaxVerbosity(Logger.VERBOSITY.QUIET, Logger.MSG_TYPE.OTHER);
      }
      // regardless, always log output at the default level:
      logger.setVerbosity(Logger.VERBOSITY.DEFAULT, Logger.MSG_TYPE.OUTPUT);
      
      JSONObject config = Optional.ofNullable( argMap.get( configOptions.get(0) ) )
          .map( readFilePath ).flatMap( logger.warningIfEmpty("Config file could not be read. Using command line options only...") )
          .map(optOnError( s -> new JSONObject(s) )).flatMap( logger.warningIfEmpty("Config file could not be found. Using command line options only...") )
          .orElseGet( JSONObject::new );
      
      Function<String, Supplier<Optional<String>>> configString = key -> optOnError( () -> config.getString(key) );
      
      Function<Collection<String>, Supplier<Optional<String>>> configStrings = keys -> () -> keys.stream()
          .map( configString )
          .map( Supplier::get )
          .flatMap( optStream() )
          .findFirst();
      
      Function<List<String>, Optional<String>> getOption = keys -> firstPresent(
          () -> Optional.ofNullable( argMap.get( keys.get(0) ) ),
          configStrings.apply( keys )
          );
      
      Optional<String> errFilePath = getOption.apply(errorFileOptions);
      if (errFilePath.isPresent()) {
        try {
          OutputStream errStream = new FileOutputStream(errFilePath.get());
          logger.setOutputStream( errStream, Logger.MSG_TYPE.ERROR );
          logger.setOutputStream( errStream, Logger.MSG_TYPE.WARNING );
        } catch (Throwable e) {
          logger.error(e.getMessage());
          logger.warning("Leaving error stream as System stderr.");
        }
      }
      
      Optional<String> logFilePath = getOption.apply(logFileOptions);
      if (logFilePath.isPresent()) {
        try {
          logger.setOutputStream( new FileOutputStream(logFilePath.get()), Logger.MSG_TYPE.DEFAULT );
          logger.verbose("Log file set to " + logFilePath.get());
        } catch (Throwable e) {
          logger.error(e.getMessage());
          logger.warning("Leaving log stream as System stdout.");
        }
      }

      logger.log("Processing options...");
      
//      Plugins plugins = defaultPlugins();
//      plugins.logger = logger;
      
      
      Optional<String> inputOpt = Optional.of(getOption.apply(inFileOptions))
          .flatMap( logger.errorIfEmpty("No input file specified. Exiting..."))
          .map( filePath -> { logger.verbose("Input file set to " + filePath); return filePath; })
          .map( readFilePath ).flatMap( logger.errorIfEmpty("Input file could not be opened. Exiting...") );
      if (!inputOpt.isPresent()) return;
      String input = inputOpt.get();
          
      Optional<String> outFilePath = getOption.apply(outFileOptions);
      if (outFilePath.isPresent()) {
        try {
          logger.setOutputStream( new FileOutputStream(outFilePath.get()), Logger.MSG_TYPE.OUTPUT );
          logger.verbose("Output file set to " + outFilePath.get());
        } catch (Throwable e) {
          logger.error(e.getMessage());
          logger.warning("Setting output to System stdout.");
          logger.setOutputStream( System.out, Logger.MSG_TYPE.OUTPUT );
        }
      } else {
        logger.setOutputStream( System.out, Logger.MSG_TYPE.OUTPUT );
      }
      TranslationDescription.defaultLogger = logger;
      
      Optional<String> tdFilePathOpt = getOption.apply(translationDescriptionOptions);
      if (tdFilePathOpt.isPresent()) {
        logger.verbose("Translation description file set to " + tdFilePathOpt.get());
      } else {
        logger.error("No translation description file specified. Exiting...");
        return;
      }
      Optional<String> tdFileOpt = tdFilePathOpt.flatMap( readFilePath );
      if (!tdFileOpt.isPresent()) {
        logger.error("No translation description file found. Exiting...");
        return;
      }
//      Optional<JSONObject> translationDescriptionJsonOpt = tdFileOpt.flatMap(optOnError( s -> new JSONObject(s) ));
//      if (!translationDescriptionJsonOpt.isPresent()) {
//    	  logger.error("Translation description was not valid JSON. Exiting...");
//    	  return;
//      }
//      Optional<TranslationDescription> translationDescriptionOpt = translationDescriptionJsonOpt.flatMap(optOnError( TranslationDescription::fromJSON ));
//      if (!translationDescriptionOpt.isPresent()) {
//        logger.error("Translation description was valid JSON but could not be parsed. Exiting...");
//        return;
//      }
      Optional<TranslationDescription> translationDescriptionOpt = tdFileOpt
          .flatMap(optOnError( str -> TranslationDescription.fromString(str) )).filter( td -> !td.isEmpty() );
      if (!translationDescriptionOpt.isPresent()) {
        logger.verbose("Could not parse file as Translation language. Exiting...");
        return;
      }
      TranslationDescription translationDescription = translationDescriptionOpt.get();
      
      TranslationDescription.Plugins plugins = translationDescription.plugins;
      translationDescription.setLogger(logger);
      
//      Function<String, JsonNode> preprocessor = plugins.getPreprocessor(getOption.apply(preprocessorOptions).orElse("default"));
//      logger.verbose("Preprocessor set to " + plugins.getCanonicalName(preprocessor) + ".");
        getOption.apply(preprocessorOptions)
          .ifPresent( s -> Stream.of(s.split("\\+"))
            .map( String::trim )
            .map( plugins::getPreprocessor )
            .map(splitStream( plugins::getCanonicalName ))
            .reduce( (e1, e2) -> 
              new AbstractMap.SimpleEntry<>(e1.getKey() + "+" + e2.getKey(), Preprocessors.compose(e1.getValue(), e2.getValue()))
            ).ifPresent(consumeEntry( (name,fn) -> {
              logger.verbose("Preprocessor set to %s.", name);
              plugins.preprocessor = fn;
            })));
      
      getOption.apply(sanitizeFunctionOptions)
        .map(plugins::getSanitizer)
        .ifPresent( fn -> {
        plugins.sanitizer = fn;
        logger.verbose("Sanitize function set to " + plugins.getCanonicalName(translationDescription.plugins.sanitizer) + ".");
      });
      
      Optional<String> nativeOpt = getOption.apply(nativeFunctionOptions);
      Optional<String> keywordsOpt = nativeOpt.flatMap(readFilePath);
      if (keywordsOpt.isPresent()) {
        logger.verbose("Building native function from " + nativeOpt.get());
        translationDescription.plugins.nativeFn = plugins.buildKeywordNative( Arrays.asList(keywordsOpt.get().split("\n")) );
      } else {
        nativeOpt.map(plugins::getNative).ifPresent( fn -> {
          translationDescription.plugins.nativeFn = fn;
        });
        logger.verbose("Native function set to " + plugins.getCanonicalName(translationDescription.plugins.nativeFn));
      }
      
      logger.verbose("Searching for libraries...");
      
      Collection<JsonNode> libraries = Stream.concat(
          optStream( getOption.apply(librariesOptions) )
            .map( splitStream(readFilePath) )
            .peek(consumeEntry( (readOpt, filePath) -> {
              if (readOpt.isPresent()) {
                logger.verbose("Successfully read library " + filePath + ".");
              } else {
                logger.warning("Failed to read library " + filePath + ".");
              }
            }))
            .map( Entry::getKey )
            .flatMap( optStream() )
            .map( JsonNode::readFrom ),
          librariesOptions.stream()
            .map(optOnError( key -> config.getJSONArray(key) ))
            .flatMap( optStream() )
            .map( JsonNode::buildFrom )
            .flatMap( node -> node.children().stream()
                .flatMap( child -> optStream(child.as(ValueNode.class)
                    .map( ValueNode::asText )
                    .flatMap( readFilePath )
                    .map( JsonNode::readFrom )) )))
        .collect( Collectors.toList() );
      
      if (libraries.isEmpty()) {
        logger.verbose("No libraries found.");
      } else {
        logger.verbose("Libraries found.");
      }
      
      Boolean parallel = testFlag.apply(parallelFlags).orElse(true);

      logger.log("Options processed.");
      
      // Actually run the translation:
      Translator translator = new Translator(translationDescription);
      translator.useLogger(logger); // adopt the logging settings that we're already using.
      translator.useParallel(parallel);
      List<String> errors = translator.errors();
      if (!errors.isEmpty()) {
        logger.error("%d errors detected:", errors.size());
        for (String error : errors) {
          logger.error(error, Logger.VERBOSITY.QUIET);
        }
        logger.error("Exiting...", Logger.VERBOSITY.QUIET);
        return;
      }
      String output = translator.translate(input);
      logger.output(output);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Builds a new Translator using the given translation description
   * @param translationDescription Specifies the templates to use and how their fields are to be found, and plugins object specifies rest
   */
  public Translator(TranslationDescription translationDescription) throws IllegalArgumentException {
    this.translationDescription = translationDescription;
    this.logger = new Logger();
    this.useParallel = true;
  }
  
  /**
   * Use this Translator, and the values given to it at construction, to translate the source String into target code.
   * @param sourceString The String for the source, which will be transformed by the preprocessor into a Json model.
   * @return The translation of the resulting model, as a String in the target language.
   * @throws S2KException If translation fails unrecoverably for any reason.
   */
  public String translate(String sourceString) throws IllegalArgumentException {
    try {
      long overallStart = System.currentTimeMillis();
      logger.log("Pre-processing...");
      long preprocessorStart = System.currentTimeMillis();
      JsonNode model = translationDescription.plugins.preprocessor.apply(sourceString);
      long preprocessorEnd = System.currentTimeMillis();
      logger.log("Finished pre-processing.");
      logger.log("Pre-processing time: %.3f s", (preprocessorEnd - preprocessorStart) / 1000.0);
      logger.other("%s", Logger.VERBOSITY.VERBOSE, model);
      String translation = translate( model );
      long overallEnd = System.currentTimeMillis();
      logger.log("Overall Time: %.3f s", (overallEnd - overallStart) / 1000.0);
      return translation;
    } catch (Throwable e) {
      logger.error("Fatal error: " + e.toString(), Logger.VERBOSITY.QUIET);
      throw new IllegalArgumentException("Fatal error occured while translating.", e);
    }
  }

  /**
   * Use this Translator, and the values given to it at construction, to translate the source object into target code.
   * Note: Calling this version directly side-steps any preprocessing that the translator is configured to do.
   * @param source The JSON-format source model, that provides the data for the translation.
   * @return A String in the target language, representing the translation of the source model.
   */
  public String translate(JsonNode source) {
    logger.log("Translating...");
    long translationStart = System.currentTimeMillis();
    
    // need "global" registrars, w.r.t. the templates, so they can communicate after a fashion.
    MatchRegistrar matchRegistrar = new MatchRegistrar();
    InstantiationRegistrar instantiationRegistrar = new InstantiationRegistrar();
    
    Path.setPeriodicalCacheReport(logger, 5_000_000);
    
    logger.verbose("Querying source model...");
    long queryStart = System.currentTimeMillis();
    
    (useParallel ? translationDescription.values().parallelStream() : translationDescription.values().stream())
        .forEach( template -> {
          logger.verbose("Querying for template " + template.getName() + "...");
          template.matchToSource(source, translationDescription.plugins.libraries).forEach( match ->
              matchRegistrar.register(template, match)); // associate each match individually with its template
          logger.verbose("Finished querying for template " + template.getName() + ".");
        });
    
    long queryEnd = System.currentTimeMillis();
    
    Long totalInstances = matchRegistrar.pairingStream().count();
    
    logger.verbose("Source model queried.");
    logger.verbose("%d potential template instances found.", totalInstances);
    logger.verbose("Query time: %.3f s", (queryEnd - queryStart) / 1000.0);
    logger.verbose("Instantiating templates...");
    
    long instantiationStart = System.currentTimeMillis();
    
    Long[] instanceNum = new Long[]{ 0L }; // Container so that forEach doesn't complain about instanceNum being non-final
    
    // Note: we use the collection forEach, not stream version, to maintain order.
    matchRegistrar.instantiationOrder(logger)
        .forEach( templatePair -> {
          instanceNum[0]++;
          if (instanceNum[0] % 100_000 == 0) {
            logger.verbose("%d instantiations completed...", instanceNum[0]);
          }
          templatePair.template.instantiate(templatePair.templateMatch, instantiationRegistrar);
        }); // instantiate every match, loading the instantiationRegistrar
    
    long instantiationEnd = System.currentTimeMillis();
    
    logger.verbose("Templates instantiated.");
    logger.verbose("%d real instances created.", instantiationRegistrar.pairingStream().count());
    logger.verbose("Instantiation time: %.3f s", (instantiationEnd - instantiationStart) / 1000.0);
    
    Set<JsonNode> realRefs = matchRegistrar.pairingStream()
        .map( Entry::getValue )
        .map( TemplateMatch::getTriggerValue )
        .flatMap( optStream() )
        .collect( Collectors.toSet() );
    
    logger.verbose("Joining top level instances...");
    long joiningStart = System.currentTimeMillis();
    
    String output = instantiationRegistrar.keySet().stream()
      .filter( ref -> !realRefs.contains(ref) )
      .map( instantiationRegistrar::get )
      .flatMap( Collection::stream )
      .filter( tm -> !tm.getTemplate().map( Template::isIncomplete ).orElse( false ) )
      .map( TemplateMatch::getInstance )
      .flatMap( optStream() )
      .collect( Collectors.joining("\n\n") );
    
    long joiningEnd = System.currentTimeMillis();
    
    logger.verbose("Top level instances joined. Output is %d lines long.", output.split("\n").length);
    logger.verbose("Joining time: %.3f s", (joiningEnd - joiningStart) / 1000.0);
    
    long translationEnd = System.currentTimeMillis();
    
    logger.log("Translation complete.");
    logger.log("Translation time: %.3f s", (translationEnd - translationStart) / 1000.0);
    
    Path.reportCacheUsage(logger);
    
    return output;
  }
  
  /**
   * Converts to JSON object
   * @return a JSON object completely specifying the Translator
   */
  public JSONObject toJSON() {
    return new JSONObject()
        .put("_type", "Translator")
        .put("translationDescription", translationDescription.toJSON())
//        .put("libraries", libraries)
        ;
  }

  /**
   * @param logger The Logger to be used until reset
   */
  public void useLogger(Logger logger) {
    this.logger = logger;
  }
  
  /**
   * @param use Sets whether to use parallel computing when translating.
   */
  public void useParallel(boolean use) {
    useParallel = use;
  }
  
  /**
   * Checks this object for errors and reports them. Also checks contained translation description.
   * @return A List of error messages, which is empty if there are no errors.
   */
  public List<String> errors() {
    List<String> output = new LinkedList<String>();
    
    if (translationDescription == null) output.add("No translation description was set.");
    else if (translationDescription.isEmpty()) output.add("No templates were added to the translation description.");
//    if (libraries == null) output.add("Libraries collection is null.");
//    if (preprocessor == null) output.add("No preprocessor is set.");
    if (translationDescription != null) output.addAll(translationDescription.errors());
    
    return output;
  }
}
