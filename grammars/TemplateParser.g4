parser grammar TemplateParser;

options { tokenVocab=TemplateLexer; }

/**
 * Defines templates, a series of literal text components and fields.
 */

template : (name NEWLINE)?
           component+
         ;

name : TEXTLINE ;

component : ( TEXTLINE | NEWLINE | field )
          ;

field : FIELD ;
