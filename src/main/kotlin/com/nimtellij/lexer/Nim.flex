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

EscapeSequence=\\[^\r\n]
StringLiteral=\"([^\\\"]|{EscapeSequence})*(\"|\\)?
CharacterLiteral="#\\"[^\s)]+


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

"true"          { return TRUE; }
"false"         { return FALSE;}

"quote"                     { return QUOTE_KEYWORD; }

"addr"	{ return ADDR; }
"and"	{ return AND; }
"as"	{ return AS; }
"asm"	{ return ASM; }
"bind"	{ return BIND; }
"block"	{ return BLOCK; }
"break"	{ return BREAK; }
"case"	{ return CASE; }
"cast"	{ return CAST; }
"concept"	{ return CONCEPT; }
"const"	{ return CONST; }
"continue"	{ return CONTINUE; }
"converter"	{ return CONVERTER; }
"defer"	{ return DEFER; }
"discard"	{ return DISCARD; }
"distinct"	{ return DISTINCT; }
"div"	{ return DIV; }
"do"	{ return DO; }
"elif"	{ return ELIF; }
"else"	{ return ELSE; }
"end"	{ return END; }
"enum"	{ return ENUM; }
"except"	{ return EXCEPT; }
"export"	{ return EXPORT; }
"finally"	{ return FINALLY; }
"for"	{ return FOR; }
"from"	{ return FROM; }
"func"	{ return FUNC; }
"if"	{ return IF; }
"import"	{ return IMPORT; }
"in"	{ return IN; }
"include"	{ return INCLUDE; }
"interface"	{ return INTERFACE; }
"is"	{ return IS; }
"isnot"	{ return ISNOT; }
"iterator"	{ return ITERATOR; }
"let"	{ return LET; }
"macro"	{ return MACRO; }
"method"	{ return METHOD; }
"mixin"	{ return MIXIN; }
"mod"	{ return MOD; }
"nil"	{ return NIL; }
"not"	{ return NOT; }
"notin"	{ return NOTIN; }
"object"	{ return OBJECT; }
"of"	{ return OF; }
"or"	{ return OR; }
"out"	{ return OUT; }
"proc"	{ return PROC; }
"ptr"	{ return PTR; }
"raise"	{ return RAISE; }
"ref"	{ return REF; }
"return"	{ return RETURN; }
"shl"	{ return SHL; }
"shr"	{ return SHR; }
"static"	{ return STATIC; }
"template"	{ return TEMPLATE; }
"try"	{ return TRY; }
"tuple"	{ return TUPLE; }
"type"	{ return TYPE; }
"using"	{ return USING; }
"var"	{ return VAR; }
"when"	{ return WHEN; }
"while"	{ return WHILE; }
"xor"	{ return XOR; }
"yield"	{ return YIELD; }


"~"             { return TILDE; }
"="             { return EQ; }
"`"             { return BACKQUOTE; }
"'"             { return QUOTE; }
","             { return COMMA; }
",@"            { return COMMA_AT; }
"::"            { return DOUBLE_COLON; }
":"             { return COLON; }

{GlobalVariable} { return GLOBAL; }
{Symbol}         { return SYMBOL; }

.               { return BAD_CHARACTER; }
