package gov.nasa.jpl.text2text;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.json.JSONObject;

import gov.nasa.jpl.text2text.Template.TemplatedefVisitor;
import gov.nasa.jpl.text2text.TranslationParser.*;

import static gov.nasa.jpl.jsonPath2.Utils.*;

/**
 * Collects a set of templates along with various plugins to define how translation will act.
 * 
 * @author David K. Legg
 *
 */
public class TranslationDescription extends LinkedHashMap<String, Template> {
  private static final long serialVersionUID = 8052745587121104868L;

  // Use a totally agnostic default sanitizer
  private static final Function<String, String> DEFAULT_SANITIZE_FN = s -> s;
  // Since keywords are highly variable, the only language-agnostic option is to always fail.
  private static final Function<String, Optional<String>> DEFAULT_NATIVE_FN = s -> Optional.empty();
  
  /// Public fields
  
  public Function<String, String> sanitizeFn; // takes any string, and makes it a legal keyword.
  public Function<String, Optional<String>> nativeFn; // takes any string, and returns a "close" keyword in the target language
  public Translator.Plugins plugins;
  private Logger logger;
  
  /// Public methods
  
  public TranslationDescription() {
    super();
    sanitizeFn = DEFAULT_SANITIZE_FN;
    nativeFn   = DEFAULT_NATIVE_FN;
    plugins    = Translator.defaultPlugins();
    logger     = Logger.SILENT_LOGGER;
  }
  
  public JSONObject toJSON() {
    throw new UnsupportedOperationException("Not implemented yet.");
  }
  
  /**
   * Constructs a TranslationDescription from a template file, as a string.
   * 
   * @param templateFileString The String which has all template and field definitions in Template syntax.
   * @return The TranslationDescription with every parseable template added.
   * @throws S2KParseException If the string cannot be parsed for any reason.
   */
  public static TranslationDescription fromString(String templateFileString) throws IllegalArgumentException {
    try {
      TranslationLexer lexer = new TranslationLexer( CharStreams.fromString(templateFileString) );
      TranslationParser parser = new TranslationParser( new CommonTokenStream(lexer) );
      TemplateFileVisitor tfv = new TemplateFileVisitor();
      return tfv.visit( parser.templatefile() );
    } catch (Throwable e) {
      throw new IllegalArgumentException("Could not parse string as a TranslationDescription.", e);
    }
  }
  
  /**
   * Checks this object for errors and reports them. Also checks all Templates it contains.
   * @return A List of error messages, which is empty if there are no errors.
   */
  public List<String> errors() {
    List<String> output = new LinkedList<String>();
    
    if (sanitizeFn == null) output.add("No sanitize function is set on the translation description.");
    if (nativeFn == null) output.add("No native function is set on the translation description.");
    if (plugins == null) output.add("No plugins are set on the translation description.");
    
    for (Template t : this.values()) {
      output.addAll(t.errors());
    }
    
    return output;
  }
  
  /**
   * Adds template to this TranslationDescription
   * and sets template's logger to this TranslationDescription's logger.
   * @param template The template to add
   */
  public void add(Template template) {
    this.put(template.getName(), template);
  }

  /**
   * Adds template to this TranslationDescription
   * and sets template's logger to this TranslationDescription's logger.
   * @param name The name of template (using other keys may break things)
   * @param template The template to add
   */
  @Override
  public Template put(String name, Template template) {
    template.setLogger(logger);
    super.put(name, template);
    return template;
  }
  
  // TODO: perhaps flesh out the other put methods? not sure if the above override is enough
  
  public void setLogger(Logger newLogger) {
    this.logger = newLogger;
    for (Template t : this.values()) {
      t.setLogger(newLogger);
    }
  }
  
  public static class TemplateFileVisitor extends TranslationParserBaseVisitor<TranslationDescription> {
    @Override
    public TranslationDescription visitTemplatefile(TemplatefileContext ctx) {
      TemplatedefVisitor tv = new TemplatedefVisitor();
      TranslationDescription output = new TranslationDescription();
      ctx.templatedef().stream()
        .map( template -> template.accept(tv) )
        .flatMap( optStream() )
        .forEach( template -> template.setTranslationDescription(output) );
      return output;
    }
  }
}
