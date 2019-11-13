package com.nimtellij.parser

import com.intellij.lang.ASTNode
import com.intellij.lang.PsiBuilder
import com.intellij.lang.PsiParser
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet
import com.nimtellij.lexer.NimTokenTypes
import java.io.EOFException
import java.util.*

/**
 * The parser doesn't really do anything, but lsp4intellij
 * somehow requires it to provide code completion.
 */
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
        markAndAdvance(builder.tokenType!!)
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
