package com.nimtellij.ide

import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import com.nimtellij.lexer.NimTokenTypes

class NimPairedBraceMatcher : PairedBraceMatcher {
    override fun getPairs(): Array<BracePair> =
        arrayOf(NimBracePair())

    override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?): Boolean = true

    override fun getCodeConstructStart(file: PsiFile?, openingBraceOffset: Int): Int = openingBraceOffset
}

class NimBracePair : BracePair(
    NimTokenTypes.LEFT_PAREN,
    NimTokenTypes.RIGHT_PAREN, true
)
