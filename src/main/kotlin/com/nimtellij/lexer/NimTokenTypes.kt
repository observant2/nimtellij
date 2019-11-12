package com.nimtellij.lexer

import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet
import com.nimtellij.ide.NimLanguage


interface NimTokenTypes {

    companion object {
        // Special characters
        @JvmField val LEFT_PAREN: IElementType = NimElementType("(")
        @JvmField val RIGHT_PAREN: IElementType = NimElementType(")")
        @JvmField val LEFT_BRACKET: IElementType = NimElementType("[")
        @JvmField val RIGHT_BRACKET: IElementType = NimElementType("]")
        @JvmField val LEFT_BRACE: IElementType = NimElementType("{")
        @JvmField val RIGHT_BRACE: IElementType = NimElementType("}")

        @JvmField val HASH: IElementType = NimElementType("#")
        @JvmField val UP: IElementType = NimElementType("^")
        @JvmField val SHARPUP: IElementType = NimElementType("#^")
        @JvmField val TILDE: IElementType = NimElementType("~")
        @JvmField val EQ: IElementType = NimElementType("=")
        @JvmField val AT: IElementType = NimElementType("@")
        @JvmField val COMMA: IElementType = NimElementType(",")
        @JvmField val COMMA_AT: IElementType = NimElementType(",@")
        @JvmField val QUOTE: IElementType = NimElementType("'")
        @JvmField val BACKQUOTE: IElementType = NimElementType("`")
        @JvmField val COLON: IElementType = NimElementType(":")
        @JvmField val DOUBLE_COLON: IElementType = NimElementType("::")

        @JvmField val SPECIAL_CHARACTERS = TokenSet.create(TILDE, COLON, EQ, BACKQUOTE, QUOTE, COMMA, COMMA_AT);
        // Comments
        @JvmField val LINE_COMMENT: IElementType = NimElementType("line comment")

        // Special forms
        @JvmField val ADDR = NimElementType("addr")
        @JvmField val AND = NimElementType("and")
        @JvmField val AS = NimElementType("as")
        @JvmField val ASM = NimElementType("asm")
        @JvmField val BIND = NimElementType("bind")
        @JvmField val BLOCK = NimElementType("block")
        @JvmField val BREAK = NimElementType("break")
        @JvmField val CASE = NimElementType("case")
        @JvmField val CAST = NimElementType("cast")
        @JvmField val CONCEPT = NimElementType("concept")
        @JvmField val CONST = NimElementType("const")
        @JvmField val CONTINUE = NimElementType("continue")
        @JvmField val CONVERTER = NimElementType("converter")
        @JvmField val DEFER = NimElementType("defer")
        @JvmField val DISCARD = NimElementType("discard")
        @JvmField val DISTINCT = NimElementType("distinct")
        @JvmField val DIV = NimElementType("div")
        @JvmField val DO = NimElementType("do")
        @JvmField val ELIF = NimElementType("elif")
        @JvmField val ELSE = NimElementType("else")
        @JvmField val END = NimElementType("end")
        @JvmField val ENUM = NimElementType("enum")
        @JvmField val EXCEPT = NimElementType("except")
        @JvmField val EXPORT = NimElementType("export")
        @JvmField val FINALLY = NimElementType("finally")
        @JvmField val FOR = NimElementType("for")
        @JvmField val FROM = NimElementType("from")
        @JvmField val FUNC = NimElementType("func")
        @JvmField val IF = NimElementType("if")
        @JvmField val IMPORT = NimElementType("import")
        @JvmField val IN = NimElementType("in")
        @JvmField val INCLUDE = NimElementType("include")
        @JvmField val INTERFACE = NimElementType("interface")
        @JvmField val IS = NimElementType("is")
        @JvmField val ISNOT = NimElementType("isnot")
        @JvmField val ITERATOR = NimElementType("iterator")
        @JvmField val LET = NimElementType("let")
        @JvmField val MACRO = NimElementType("macro")
        @JvmField val METHOD = NimElementType("method")
        @JvmField val MIXIN = NimElementType("mixin")
        @JvmField val MOD = NimElementType("mod")
        @JvmField val NIL = NimElementType("nil")
        @JvmField val NOT = NimElementType("not")
        @JvmField val NOTIN = NimElementType("notin")
        @JvmField val OBJECT = NimElementType("object")
        @JvmField val OF = NimElementType("of")
        @JvmField val OR = NimElementType("or")
        @JvmField val OUT = NimElementType("out")
        @JvmField val PROC = NimElementType("proc")
        @JvmField val PTR = NimElementType("ptr")
        @JvmField val RAISE = NimElementType("raise")
        @JvmField val REF = NimElementType("ref")
        @JvmField val RETURN = NimElementType("return")
        @JvmField val SHL = NimElementType("shl")
        @JvmField val SHR = NimElementType("shr")
        @JvmField val STATIC = NimElementType("static")
        @JvmField val TEMPLATE = NimElementType("template")
        @JvmField val TRY = NimElementType("try")
        @JvmField val TUPLE = NimElementType("tuple")
        @JvmField val TYPE = NimElementType("type")
        @JvmField val USING = NimElementType("using")
        @JvmField val VAR = NimElementType("var")
        @JvmField val WHEN = NimElementType("when")
        @JvmField val WHILE = NimElementType("while")
        @JvmField val XOR = NimElementType("xor")
        @JvmField val YIELD = NimElementType("yield")

        @JvmField val QUOTE_KEYWORD = NimElementType("quote keyword")

        @JvmField val SYMBOL = NimElementType("symbol")
        @JvmField val GLOBAL = NimElementType("global")
        @JvmField val KEY = NimElementType("key")

        @JvmField val KEYWORDS = TokenSet.create(
            ADDR, AND, AS, ASM, BIND, BLOCK, BREAK, CASE, CAST, CONCEPT, CONST, CONTINUE, CONVERTER, DEFER, DISCARD, DISTINCT, DIV, DO, ELIF, ELSE, END, ENUM, EXCEPT, EXPORT, FINALLY, FOR, FROM, FUNC, IF, IMPORT, IN, INCLUDE, INTERFACE, IS, ISNOT, ITERATOR, LET, MACRO, METHOD, MIXIN, MOD, NIL, NOT, NOTIN, OBJECT, OF, OR, OUT, PROC, PTR, RAISE, REF, RETURN, SHL, SHR, STATIC, TEMPLATE, TRY, TUPLE, TYPE, USING, VAR, WHEN, WHILE, XOR, YIELD
        )

        // Literals
        @JvmField val STRING_LITERAL: IElementType = NimElementType("string literal")
        @JvmField val CHARACTER_LITERAL: IElementType = NimElementType("character literal")
        @JvmField val DOCSTRING: IElementType = NimElementType("docstring")

        @JvmField val STRINGS = TokenSet.create(
            STRING_LITERAL, DOCSTRING
        )


        @JvmField val CHAR_LITERAL: IElementType = NimElementType("character literal")
        @JvmField val NUMERIC_LITERAL = NimElementType("numeric literal")

        @JvmField val TRUE: IElementType = NimElementType("true")
        @JvmField val FALSE: IElementType = NimElementType("false")
        @JvmField val LITERAL = NimElementType("literal")
        @JvmField val LITERALS = TokenSet.create(
            NUMERIC_LITERAL, CHAR_LITERAL, TRUE, FALSE, NIL
        )

        // Control characters
        @JvmField val EOL: IElementType = NimElementType("end of line")
        @JvmField val EOF: IElementType = NimElementType("end of file")
        @JvmField val WHITESPACE = TokenType.WHITE_SPACE
        @JvmField val BAD_CHARACTER: IElementType = TokenType.BAD_CHARACTER
    }
}

interface NimPsiTypes {
    companion object {
        @JvmField val EXPRESSION = NimElementType("expression")
        @JvmField val VARIABLE_DEFINITION = NimElementType("variable definition")
        @JvmField val VARIABLE_REFERENCE = NimElementType("variable reference")
        @JvmField val PARAMETER = NimElementType("parameter")
        @JvmField val PARAMETER_LIST = NimElementType("parameter list")
    }
}

class NimTokenType(debugName: String) : IElementType(debugName, NimLanguage.INSTANCE) {
    override fun toString(): String {
        return "NimTokenType." + super.toString()
    }
}

class NimElementType(debugName: String) : IElementType(debugName, NimLanguage.INSTANCE) {
    override fun toString(): String = "Nim: " + super.toString()
}
