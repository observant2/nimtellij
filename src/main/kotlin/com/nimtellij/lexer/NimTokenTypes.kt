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
        @JvmField val IMPORT = NimElementType("import")
        @JvmField val PROC = NimElementType("proc")
        @JvmField val FUNC = NimElementType("func")
        @JvmField val DEFMETHOD = NimElementType("defmethod")
        @JvmField val VAR = NimElementType("var")
        @JvmField val CONST = NimElementType("const")

        @JvmField val QUOTE_KEYWORD = NimElementType("quote keyword")

        @JvmField val SYMBOL = NimElementType("symbol")
        @JvmField val GLOBAL = NimElementType("global")
        @JvmField val KEY = NimElementType("key")

        // TODO: Add all special forms (should be roughly 25 or so)
        @JvmField val IF = NimElementType("if")
        @JvmField val COND = NimElementType("cond")
        @JvmField val WHEN = NimElementType("when")
        @JvmField val UNLESS = NimElementType("unless")
        @JvmField val PROGN = NimElementType("progn")
        @JvmField val DO = NimElementType("do")
        @JvmField val DOLIST = NimElementType("dolist")
        @JvmField val DOTIMES = NimElementType("dotimes")
        @JvmField val LET = NimElementType("let")
        @JvmField val LET_STAR = NimElementType("let*")

        @JvmField val KEYWORDS = TokenSet.create(
            IMPORT,
            PROC, FUNC, DEFMETHOD,
            VAR, CONST,
            QUOTE_KEYWORD,
            IF, COND, WHEN, UNLESS, PROGN, DO, DOLIST, DOTIMES, LET, LET_STAR
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
        @JvmField val NIL: IElementType = NimElementType("nil")

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
