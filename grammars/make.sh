#!/bin/bash

function makeGrammar() {
    antlr4 $1Parser.g4 $1Lexer.g4 -visitor -no-listener -package gov.nasa.jpl.text2text &&
    cp $1*.java $1*.tokens ../src/gov/nasa/jpl/text2text/
}

makeGrammar Template
makeGrammar Translation
