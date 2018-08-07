# Text2Text

A transformation language based on JsonPath2 queries.

## Installation

First, clone the [JsonPath2 repository](https://github.jpl.nasa.gov/mbee-dev/jsonPath2.git), and follow the instructions to build and compile that project.

Next, clone this repository, preferably into `~/git/text2text` (other locations may require adjustments). Then, from the root directory for the project, execute

 * `grammars/make.sh` - to build the Antlr Lexer/Parser
 * `mvn package` - to compile
 * `scripts/t2t` - to test

If that executes and complains that no input file was specified, you've installed the software correctly and are good to go.

The most likely source of errors are dependency failures. Make sure that Antlr4 is installed on your system, and that the Maven dependencies could be fetched.

## Description and Usage

To run a basic translation, execute the `scripts/t2t` script with the following options:

 * `-td <translation-file>` - the Translation Description file (usually a `.translation` file, found in the `translations/` directory), which describes the transformation
 * `-in <input-file>` - the input file to be translated
 * `-out <output-file>` - the output file to be written to