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

## Operation Overview

In general, all translations with this system have the same basic operation. First, a collection of templates and related settings is read in, usually from a `.t2t` file. Next, the input document is read in, and pre-processed into a JSON document. Then, all the templates run their "trigger queries", generating template instances. Each template instance then runs all its remaining queries, which fill in as much information as they can, according to the rules described in the next section. Template instances that are missing information are deleted, and the remaining instances are composed, again according to rules described in the next section. Finally, these composed "top-level" instances are printed to the output file.

## The t2t System in Detail

A "translation" is defined primarily by a `.t2t` file, also called the translation file. At the top of a translation file are a few lines setting options for the translation overall, in the format
```
OptionName: "option value" {, "other option values"}
```
The options that can be set are:

* Preprocessor - the steps to take in converting the input document to a queryable JSON document. Leave this option out if the input is already in JSON format.
* SanitizeFn - Converts strings to valid identifiers in the target language. The "identifier" setting is generally appropriate to most programming languages, and the "string" setting is useful in data storage formats, like JSON.
* NativeFn - Recognizes keywords in the target language, to avoid using them as identifiers. The easiest way to specify this is to directly specify all keywords on this line. For instance, if the keywords in a language were "class, if, for, while", then this option would read `NativeFn: "class", "if", "for", "while"`
* Libraries - a list of file names to use as the "library" documents for queries. These will *not* be pre-processed, so they should be in JSON format already.

After setting all desired options is the main body of the translation file. This is a collection of templates, separated by at least one new line. Each template has a format like the following:
```
NoDuplicates
Template: Name of Template
Literal text in the template body,
except for a %fieldName like this.
Fields:
fieldName
	(fieldFlags)
	("my %s format string")
	DOC.path.to.data
nonPrintingField
	(fieldFlags)
	DOC.path.to.other.data
PARENT_REFERENCE
	DOC.path.to.parent.trigger
```

The first line, `NoDuplicates`, is optional; if given, it specifies that the template should be unique. If the template matches twice with exactly the same output, the duplicates are deleted in the output.

`Name of Template` is only used inside the translator, for describing the translation and tracking the progress of the translator when it's running. It's suggested, but not required, that template names are unique.

The template body (starting at the line after the name, and ending at the line before the `Fields:` keyword) is composed of literal text, except for words that start with %. Those are fields, placeholders for data that comes from the document being translated.

After the `Fields:` keyword is the field specification, which describes how to find the information to fill in the fields given in the template body.

The `fieldName` must match exactly the specification in the body (case sensitive).

`fieldFlags` is an optional list of settings to specify the behavior of this field, and how it affects the behavior of the template. *These are the primary way of controlling the translator.* As such, they have their own section, below.

The format string is also optional, and is a standard Java format string, with at most one `%s` format code to specify where to put the data for this field.

Finally, all fields (except body fields) must specify a path, in JsonPath2, to query the input document with to get their data. See the JsonPath2 repo for more details.

Note that fields do not have to appear in the template body to be defined. Defining a so-called non-printing field, and marking it "required", is a very useful way of removing extraneous template instances. Also, it is often convenient to trigger a template with a non-printing field.

The special `PARENT_REFERENCE` field is used to control where a template instantiation is inserted in the output. If omitted, the instance is considered a "top-level" instance. If included, and the query matches the trigger for another instance, that instance is considered the "parent" of this one, and this instance is inserted into a body field of the parent instance.

### Field Flags

There are six possible field flags:

* `Trigger` - Designates this fields' query as the trigger query. This is the query that templates run first. Every result from this query will generate a template instance. Often, the trigger field is something like a UID or an object in the input document, and is non-printing.
* `Required` - Template instances that don't fill this field are considered "incomplete" and are deleted.
* `Keyword` - Denotes that this field can be a keyword (as recognized by NativeFn). Otherwise, if this field collides with a keyword, "\_\_" is prepended to the word to prevent a collision.
* `Raw` - Skips running the SanitizeFn on this field. By default, all values are sanitized. Alternatively, can be specified as `Raw "alternateSanitizeFn"`, in which case the alternate SanitizeFn is run on this field, instead of the default SanitizeFn.
* `Multi` - By default, a (non-trigger) field uses only the first result from its query. With this setting, it uses all the results, joining them with a space. Alternatively, this flag can be specified as `Multi "joiner"`, in which case the "joiner" string is used between results instead of " ".
* `Body` - Marks this field as a body field, whose value is the output from other template instances, rather than a direct query. It is not required to give a path for this field. If one is given, only template instances triggered on a value matching a result of this query are inserted into this field. By default, template instances are joined by a newline. If this flag is specified as `Body "joiner"`, then the "joiner" string is used instead of a newline.

Note that some combinations of flags are incompatible, like specifying that a Body field is also a Trigger, and other combinations are redundant, like demanding that a Trigger is Required.

---

Copyright 2018, by the California Institute of Technology. ALL RIGHTS RESERVED. United States Government Sponsorship acknowledged. Any commercial use must be negotiated with the Office of Technology Transfer at the California Institute of Technology.
This software may be subject to U.S. export control laws. By accepting this software, the user agrees to comply with all applicable U.S. export laws and regulations. User has the responsibility to obtain export licenses, or other export authority as may be required before exporting such information to foreign countries or providing access to foreign persons.
