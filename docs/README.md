# Text2Text

A transformation language based on JsonPath2 queries.

## Installation

First, clone the [JsonPath2 repository](https://github.jpl.nasa.gov/mbee-dev/jsonPath2.git), and follow the instructions to build and compile that project.

Next, clone this repository, preferably into `~/git/text2text` (other locations may require adjustments). Then, from the root directory for the project, execute

 * `mvn package` - to compile
 * `scripts/t2t` - to test

If that executes and complains that no input file was specified, you've installed the software correctly and are good to go.

The most likely source of errors are dependency failures. Make sure that Antlr4 is installed on your system, and that the Maven dependencies could be fetched.

## Description and Usage

To run a basic translation, execute the `scripts/t2t` script with the following options:

 * `-td <translation-file>` - the Translation Description file (usually a `.t2t` file, found in the `translations/` directory), which describes the transformation
 * `-in <input-file>` - the input file to be translated
 * `-out <output-file>` - the output file to be written to

By default, this repo comes with the BeCoS-to-Scenario DSL [translation file](../translations/becosToScenario.t2t). So, to translate the file `model.json` in the current directory into Scenario DSL, and name the output file `model.scenario`, also in the current directory, you should run:

```
scripts/t2t  -td translations/becosToScenario.t2t  -in ./model.json  -out ./model.scenario
```

Notice that the options are separated from their arguments by a space, not an equals sign. Inserting equals signs is the most common source of errors when using this tool.

This command takes many more arguments than just these basic 3. Of them, the most useful are 

 * `-h` for help
 * `-s`, `-q`, `-v`, `-d` for silent, quiet, verbose, and debug message verbosity
 * `-para` to force parallel processing and `-paraOff` to force sequential execution, and
 * `-pm` for "print model"

The help message will show all options and flags that the program recognizes, though it is generated automatically, so some combinations (like `--helpOff`) don't have meaning, and others (like `-pmOff`) are defaults, so adding them doesn't change behavior. Options and flags listed on the same line are synonyms. The other flags in the list above are self-explanatory, I think, except for `-pm`. With this flag on, the program will print out the model its using, which is the input file *after pre-processing*. This is useful for debugging the pre-processors, as well as for writing queries, since you can examine the 

## Modifying the Translator

There are 4 things you can change, which have different amounts of re-compiling that need to take place. In order of least intrusive to most,

 * If you just want to change a *translation* (or create a new one), modify or create the appropriate `.t2t` file, and run the appropriate `scripts/t2t` command, which will read in the new translation file and use it.
 * If you need to change how the *translator itself* works, modify the appropriate Java files in the source directory, then run `mvn package` from the root directory to recompile.
 * If you need to change the *grammar* of the translation files, modify the appropriate Antlr grammar file(s) in the `grammars/` folder, then run the following:
    * `grammars/make-test.sh` - This will compile the grammars into the `grammars/grammar-test` folder, where you can use `grun` to test out the changes. When you're satisfied that the grammar is correct, run
    * `grammars/make.sh` - This will compile the grammars and move the source into an appropriate folder in the  `src/` directory, ready to be compiled
    * `mvn package` - After compiling the grammars into Java source, you need to run this command to re-compile the Java into an executable. After this, use `scripts/t2t` like usual to run the translator.
 * If you need to change the *JsonPath2* query language, or the engine that runs the queries, you need to modify the jsonPath2 source code, according to the instructions in the jsonPath2 repository. After running `mvn package` in the jsonPath2 repo, run `mvn package` in the text2text root directory, and the changes will be incorporated.
    * For those who are curious, running `mvn package` in the `jsonPath2` repo adds the compiled jsonPath2 code to Maven's local artifacts. Then, when the `text2text` pom requires the `jsonPath2` artifact, Maven can supply it locally. If JsonPath2 were to be hosted publicly where Maven could reach it, the same pom in text2text should still work, but we could skip the step of downloading and compiling the JsonPath2 source code.