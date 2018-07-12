parser grammar TranslationParser;

options { tokenVocab=TranslationLexer; }

import JsonPath2Parser ;

/**
 * Defines template file, a collection of templates and field definitions.
 */

templatefile : optionspec*
               templatedef*
             ;

optionspec : optionname COLON optionvalue (COMMA optionvalue)* ;

optionname : IDENTIFIER ;
optionvalue : STRING ;

templatedef : (OPENROUND templateoption* CLOSEROUND)?
              STARTTEMPLATE template ENDTEMPLATE
              fielddef*
            ;

templateoption : noduplicates ;

template : TEMPLATE_CHAR* ;

fielddef : fieldname
           (OPENROUND fieldmodifiers? CLOSEROUND)?
           (OPENROUND formatstring? CLOSEROUND)?
           path?
         ;

fieldname : IDENTIFIER 
          | keyword
          ;

keyword : fieldflag
        | templateoption
        | DOC
        | REF
        | LIB
        | HERE
        ;

fieldmodifiers : fieldflag (COMMA fieldflag)* ;

fieldflag : triggerflag
          | requiredflag
          | keywordflag
          | rawflag
          | bodyflag
          | multiflag
          ;

noduplicates   : NODUPLICATES ; 
triggerflag    : TRIGGER   ;
requiredflag   : REQUIRED  ;
keywordflag    : KEYWORD ;
rawflag        : RAW  alternatesanitizer? ;
bodyflag       : BODY bodyjoiner?         ;
multiflag      : MULTIVALUE valuejoiner?  ;

alternatesanitizer : STRING ;
bodyjoiner         : STRING ;
formatstring       : STRING ;
valuejoiner        : STRING ;
