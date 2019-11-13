package com.nimtellij.ide

import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import com.nimtellij.lexer.NimTokenTypes

/**
 * A PairedBraceMatcher highlights a paren/bracket/brace
 * when the cursor is at the associated paren/bracket/brace.
 */
class NimPairedBraceMatcher : PairedBraceMatcher {
    override fun getPairs(): Array<BracePair> =
        arrayOf(NimParenPair(), NimBracketPair(), NimBracePair())

    override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?): Boolean = true

    override fun getCodeConstructStart(file: PsiFile?, openingBraceOffset: Int): Int = openingBraceOffset
}

class NimParenPair : BracePair(
    NimTokenTypes.LEFT_PAREN,
    NimTokenTypes.RIGHT_PAREN, true
)
class NimBracketPair : BracePair(
    NimTokenTypes.LEFT_BRACKET,
    NimTokenTypes.RIGHT_BRACKET, true
)
class NimBracePair : BracePair(
    NimTokenTypes.LEFT_BRACE,
    NimTokenTypes.RIGHT_BRACE, true
)
