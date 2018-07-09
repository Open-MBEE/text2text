lexer grammar TranslationLexer;

import JsonPath2Lexer;

/**
 * Defines the grammar of translation (Text2Text).
 */

/*
 * Standard mode: outside of template, allows for field definition
 */

STARTTEMPLATE : T E M P L A T E ':' -> pushMode(TEMPLATE_MODE) ;
TRIGGER   : T R I G G E R
          | T R I G
          | T R
          ;
REQUIRED  : R E Q U I R E D
          | R E Q
          ;
KEYWORD   : K E Y W O R D
          | K E Y
          | K W
          ;
RAW       : R A W
          ;
BODY      : B O D Y
          | B D
          ;
MULTIVALUE : M U L T I V A L U E
           | M U L T I V A L
           | M U L T I
           | M U L
           ;
NODUPLICATES : N O D U P L I C A T E S
             | N O D U P
             ;
COMMENT   : '//' (~[\r\n])* -> skip ;

// JsonPath2Lexer handles IDENTIFIER, etc.

mode TEMPLATE_MODE;

ENDTEMPLATE : '\n' F I E L D S ':' -> popMode;
TEMPLATE_CHAR : . ;

/*
 * Fragments (independent of mode)
 */

fragment A : [aA];
fragment B : [bB];
fragment C : [cC];
fragment D : [dD];
fragment E : [eE];
fragment F : [fF];
fragment G : [gG];
fragment H : [hH];
fragment I : [iI];
fragment J : [jJ];
fragment K : [kK];
fragment L : [lL];
fragment M : [mM];
fragment N : [nN];
fragment O : [oO];
fragment P : [pP];
fragment Q : [qQ];
fragment R : [rR];
fragment S : [sS];
fragment T : [tT];
fragment U : [uU];
fragment V : [vV];
fragment W : [wW];
fragment X : [xX];
fragment Y : [yY];
fragment Z : [zZ];
