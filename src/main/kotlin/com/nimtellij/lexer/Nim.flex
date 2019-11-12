package com.nimtellij.lexer;

import com.intellij.psi.tree.IElementType;
import com.intellij.lexer.FlexLexer;

%%

%class NimLexer
%implements FlexLexer, NimTokenTypes
%unicode
%public

%function advance
%type IElementType



LineTerminator=\r|\n|\r\n
InputCharacter=[^\r\n]
WhiteSpace={LineTerminator}|[ \t\f]

LineComment=([ \t\f]*"#"{InputCharacter}*)

Digit=[0-9]
IntegerLiteral=(0|([1-9]{Digit}*))

ExponentPart=[Ee]["+""-"]?{Digit}*
FloatingPointLiteral1={Digit}+"."{Digit}*{ExponentPart}?
FloatingPointLiteral2="."{Digit}+{ExponentPart}?
FloatLiteral=({FloatingPointLiteral1})|({FloatingPointLiteral2})

// TODO: Add support for literals like "#2r101"
NumericLiteral=["+""-"]?({IntegerLiteral}|{FloatLiteral})

Char=[A-Za-z0-9!@#$%<>_/?&\^\+\*\-=\.\?\;\|]

Symbol={Char}+

GlobalVariable="*"{Char}+"*"

KeyWithoutSpace="#"?":"{Symbol}
// This makes sure that symbols of
// a package aren't lexed as keys, like package:symbol
Key=({WhiteSpace}+|"("){KeyWithoutSpace}

EscapeSequence=\\[^\r\n]
StringLiteral=\"([^\\\"]|{EscapeSequence})*(\"|\\)?
CharacterLiteral="#\\"[^\s)]+

%state LEFT_PAREN_BEFORE_KEY
%state WHITESPACE_BEFORE_KEY

%%

{WhiteSpace}+    { return WHITESPACE; }
{LineComment}    { return LINE_COMMENT; }
{NumericLiteral} { return NUMERIC_LITERAL; }
{StringLiteral}  { return STRING_LITERAL; }
{CharacterLiteral} { return CHARACTER_LITERAL; }


"("             { return LEFT_PAREN; }
")"             { return RIGHT_PAREN; }
"["             { return LEFT_BRACKET; }
"]"             { return RIGHT_BRACKET; }
"{"             { return LEFT_BRACE; }
"}"             { return RIGHT_BRACE; }

"nil"           { return NIL; }
"true"          { return TRUE; }
"false"         { return FALSE;}

"quote"                     { return QUOTE_KEYWORD; }

"import"                { return IMPORT; }
"proc"                     { return PROC; }
"func"                 { return FUNC; }

"const"               { return CONST; }
"let"                  { return LET; }
"var"                    { return VAR; }

"if"                        { return IF; }
"cond"                      { return COND; }
"when"                      { return WHEN; }
"unless"                    { return UNLESS; }
"progn"                     { return PROGN; }
"do"                        { return DO; }
"dolist"                    { return DOLIST; }
"dotimes"                   { return DOTIMES; }
"let"                       { return LET; }
"let*"                      { return LET_STAR; }

// TODO: Add all special forms as defined in
// TODO: http://clhs.lisp.se/Body/03_ababa.htm#clspecialops

"~"             { return TILDE; }
"="             { return EQ; }
"`"             { return BACKQUOTE; }
"'"             { return QUOTE; }
","             { return COMMA; }
",@"            { return COMMA_AT; }
"::"            { return DOUBLE_COLON; }
":"             { return COLON; }


{Key}           {
          // Read one paren / whitespace and let the next state
          // read the key. Otherwise the regex takes
          // the LEFT_PAREN and WHITESPACE with it.
          if (yycharat(0) == '(') {
              CharSequence matched = yytext();
              yypushback(matched.length() - 1);
              yybegin(LEFT_PAREN_BEFORE_KEY);

              return LEFT_PAREN;
          } else {
              String matched = yytext().toString();
              int keyLength = matched.trim().length();
              int whiteSpace = matched.length() - keyLength;
              yypushback(matched.length() - whiteSpace);
              yybegin(WHITESPACE_BEFORE_KEY);

              return WHITESPACE;
          }
      }

<LEFT_PAREN_BEFORE_KEY, WHITESPACE_BEFORE_KEY> {
    {KeyWithoutSpace}   {
          yybegin(YYINITIAL);
          return KEY;
      }
}

{GlobalVariable} { return GLOBAL; }
{Symbol}         { return SYMBOL; }

.               { return BAD_CHARACTER; }
