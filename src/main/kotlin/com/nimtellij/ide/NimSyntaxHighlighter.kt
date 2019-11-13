package com.nimtellij.ide

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet
import com.nimtellij.lexer.NimLexerAdapter
import com.nimtellij.lexer.NimTokenTypes
import gnu.trove.THashMap

class NimSyntaxHighlighter : SyntaxHighlighterBase() {
    companion object {
        val TOKEN_MAP = makeTokenMap()
    }

    override fun getHighlightingLexer(): Lexer = NimLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType?): Array<out TextAttributesKey> =
        pack(tokenType?.let { type ->
            for ((tokenSet, attributesKey) in TOKEN_MAP.entries) {
                if (type in tokenSet) {
                    return arrayOf(attributesKey)
                }
            }
            return arrayOf()
        })
}

/**
 * To get syntax highlighting for certain tokens,
 * add a TokenSet containing the tokens as a key
 * and the TextAttributesKey as a value to the
 * result of this function.
 */
private fun makeTokenMap(): Map<TokenSet, TextAttributesKey> {
    val result = THashMap<TokenSet, TextAttributesKey>()
    result[NimTokenTypes.KEYWORDS] = createTextAttributesKey(
        "NIM_KEYWORD",
        DefaultLanguageHighlighterColors.KEYWORD
    )
    result[TokenSet.create(NimTokenTypes.KEY)] = createTextAttributesKey(
        "NIM_KEY",
        DefaultLanguageHighlighterColors.STATIC_FIELD
    )
    result[TokenSet.create(NimTokenTypes.GLOBAL)] = createTextAttributesKey(
        "NIM_GLOBAL",
        DefaultLanguageHighlighterColors.INSTANCE_FIELD
    )
    result[NimTokenTypes.STRINGS] = createTextAttributesKey(
        "NIM_STRING",
        DefaultLanguageHighlighterColors.STRING
    )
    result[TokenSet.create(NimTokenTypes.CHARACTER_LITERAL)] = createTextAttributesKey(
        "NIM_CHARACTER",
        DefaultLanguageHighlighterColors.STRING
    )
    result[NimTokenTypes.LITERALS] = createTextAttributesKey(
        "NIM_LITERALS",
        DefaultLanguageHighlighterColors.NUMBER
    )
    result[TokenSet.create(NimTokenTypes.LINE_COMMENT)] = createTextAttributesKey(
        "NIM_COMMENT",
        DefaultLanguageHighlighterColors.LINE_COMMENT
    )
    result[TokenSet.create(TokenType.BAD_CHARACTER)] = createTextAttributesKey(
        "SIMPLE_BAD_CHARACTER",
        HighlighterColors.BAD_CHARACTER
    )

    return result
}

class NimSyntaxHighlighterFactory : SyntaxHighlighterFactory() {
    override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?): SyntaxHighlighter =
        NimSyntaxHighlighter()
}
