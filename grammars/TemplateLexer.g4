lexer grammar TemplateLexer;

/*
 * Lexer for inside of templates
 */

TEXTLINE   : (~[\\%\n] | '\\' [\\%\n])+ ; // escapes adapted from Bart Kiers, https://stackoverflow.com/questions/23799285/parsing-quoted-string-with-escape-chars
NEWLINE    : '\n' ;
FIELD      : '%' [a-zA-Z0-9]+ ;
