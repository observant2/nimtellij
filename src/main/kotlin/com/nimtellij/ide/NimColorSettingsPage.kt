package com.nimtellij.ide

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import com.nimtellij.lexer.NimTokenTypes
import javax.swing.Icon

class NimColorSettingsPage : ColorSettingsPage {
    companion object {
        val DESCRIPTORS = arrayOf(
            AttributesDescriptor(
                "Keywords", NimSyntaxHighlighter
                    .TOKEN_MAP[NimTokenTypes.KEYWORDS]!!
            )
        )
    }

    override fun getIcon(): Icon? = NimIcons.FILE

    override fun getHighlighter(): SyntaxHighlighter = NimSyntaxHighlighter()

    override fun getDemoText(): String {
        return """(defvar bla 3 "")

(defun bla (param1)
    ""
    )"""
    }

    override fun getAdditionalHighlightingTagToDescriptorMap(): MutableMap<String, TextAttributesKey>? = null

    override fun getAttributeDescriptors(): Array<AttributesDescriptor> = DESCRIPTORS

    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

    override fun getDisplayName(): String = "Nim"
}
