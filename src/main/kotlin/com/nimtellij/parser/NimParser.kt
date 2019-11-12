package com.nimtellij.parser

import com.intellij.lang.ASTNode
import com.intellij.lang.PsiBuilder
import com.intellij.lang.PsiParser
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet
import com.nimtellij.lexer.NimPsiTypes.Companion.EXPRESSION
import com.nimtellij.lexer.NimPsiTypes.Companion.PARAMETER
import com.nimtellij.lexer.NimPsiTypes.Companion.PARAMETER_LIST
import com.nimtellij.lexer.NimPsiTypes.Companion.VARIABLE_DEFINITION
import com.nimtellij.lexer.NimPsiTypes.Companion.VARIABLE_REFERENCE
import com.nimtellij.lexer.NimTokenTypes
import com.nimtellij.lexer.NimTokenTypes.Companion.BACKQUOTE
import com.nimtellij.lexer.NimTokenTypes.Companion.COMMA
import com.nimtellij.lexer.NimTokenTypes.Companion.COMMA_AT
import com.nimtellij.lexer.NimTokenTypes.Companion.DOCSTRING
import com.nimtellij.lexer.NimTokenTypes.Companion.IF
import com.nimtellij.lexer.NimTokenTypes.Companion.KEYWORDS
import com.nimtellij.lexer.NimTokenTypes.Companion.LEFT_PAREN
import com.nimtellij.lexer.NimTokenTypes.Companion.LET
import com.nimtellij.lexer.NimTokenTypes.Companion.LET_STAR
import com.nimtellij.lexer.NimTokenTypes.Companion.LITERAL
import com.nimtellij.lexer.NimTokenTypes.Companion.PROC
import com.nimtellij.lexer.NimTokenTypes.Companion.QUOTE
import com.nimtellij.lexer.NimTokenTypes.Companion.RIGHT_PAREN
import com.nimtellij.lexer.NimTokenTypes.Companion.SPECIAL_CHARACTERS
import com.nimtellij.lexer.NimTokenTypes.Companion.STRING_LITERAL
import com.nimtellij.lexer.NimTokenTypes.Companion.SYMBOL
import java.io.EOFException
import java.util.*

class NimParser : PsiParser, NimTokenTypes {
    val markers = Stack<PsiBuilder.Marker>()
    lateinit var builder: PsiBuilder

    override fun parse(root: IElementType, psiBuilder: PsiBuilder): ASTNode {
        builder = psiBuilder

        builder.setDebugMode(true)

        val rootMarker = builder.mark()

        try {
            while (!builder.eof()) {
                parseNext()
            }
        } catch (e: EOFException) {
            while (!markers.empty()) {
                drop()
            }
        }

        rootMarker.done(root)

        return builder.treeBuilt
    }

    private fun parseNext() {
        if (isAt(LEFT_PAREN)) {
            parseExpression()
        } else if (isAt(SYMBOL)) {
            markAndAdvance(VARIABLE_REFERENCE)
        } else if (isAtMacroTemplateToken()) {
            advance()
        } else {
            // TODO - We can actually have error conditions at this point - for example, when parseNext gets called, we're not expecting a right paren, but we could get one!
            markAndAdvance(LITERAL)
        }
    }

    private fun parseExpression() {
        markAndAdvance()
        val type = getExpressionType()

        if (isAt(PROC)) {
            advance()
            parseName()
            parseParameters()
            parseDocstring()
        } else if (isAt(IF)) {
            advance()
        } else if (isAt(LET) || isAt(LET_STAR)) {
            advance()
            parseLet()
        }
        parseBody()
        done(type)
    }

    private fun parseLet() {
        advance()

        parseExpression()

        parseNext()
    }

    private fun parseName() {
        if (isAtMacroTemplateToken()) {
            advance()
        }

        if (isAt(SYMBOL) || isIn(KEYWORDS) || isIn(SPECIAL_CHARACTERS)) {
            markAndAdvance(VARIABLE_DEFINITION)
        } else {
            builder.error("Expected identifier!")
        }
    }

    private fun parseParameters() {
        mark()
        if (isAt(LEFT_PAREN)) {
            advance()
            while (!isAt(RIGHT_PAREN)) {
                parseParameter()
            }
            advance()
        } else {
            advance()
            builder.error("Expected parameter list!")
        }
        done(PARAMETER_LIST)
    }

    private fun parseParameter() {
        if (isAt(SYMBOL)) {
            markAndAdvance(PARAMETER)
        } else {
            advance()
            builder.error("Expected parameter!")
        }
    }

    private fun parseBody() {
        while (!isAt(RIGHT_PAREN)) {
            parseNext()
        }
        advance()
    }

    private fun parseDocstring() {
        if (isAt(STRING_LITERAL)) {
            markAndAdvance()
            done(DOCSTRING)
        }
    }

    private fun isAtMacroTemplateToken(): Boolean {
        return isAt(BACKQUOTE) || isAt(QUOTE) || isAt(COMMA) || isAt(COMMA_AT)
    }

    private fun getExpressionType(): IElementType {
        if (isAt(PROC)) {
            return PROC
        } else if (isAt(IF)) {
            return IF
        } else if (isAt(LET)) {
            return LET
        } else if (isAt(LET_STAR)) {
            return LET_STAR
        }

        return EXPRESSION
    }

    // ----------- HELPERS -------------------------

    private fun markAndAdvance(type: IElementType) {
        markAndAdvance()
        done(type)
    }

    private fun markAndAdvance() {
        mark()
        advance()
    }

    private fun mark() {
        markers.push(builder.mark())
    }

    private fun drop() {
        try {
            markers.pop().drop()
        } catch (e: RuntimeException) {
            throw RuntimeException(builder.tokenText, e)
        }
    }


    /**
     * Advances the lexer.
     */
    private fun advance() {
        if (builder.eof()) {
            throw EOFException();
        }
        builder.advanceLexer()
    }

    private fun done(type: IElementType) {
        try {
            markers.pop().done(type)
        } catch (e: RuntimeException) {
            throw RuntimeException(builder.tokenText, e)
        }
    }

    private fun isAt(token: IElementType): Boolean {
        return builder.tokenType == token
    }

    private fun isIn(set: TokenSet): Boolean {
        return builder.tokenType in set
    }
}
