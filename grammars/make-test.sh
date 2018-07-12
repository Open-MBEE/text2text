#!/bin/bash

ANTLRCP=/usr/local/Cellar/antlr/4.7.1/antlr-4.7.1-complete.jar

function makeGrammar() {
    if [ ! -d grammar-test ]; then
        mkdir grammar-test
    fi
    antlr4 $1Parser.g4 $1Lexer.g4 -visitor -no-listener &&
    cp $1*.java $1*.tokens grammar-test/
}

makeGrammar Template
makeGrammar Translation

cd grammar-test

javac -cp "$ANTLRCP:$CLASSPATH" *.java
