package com.nimtellij.ide

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import com.nimtellij.lexer.NimTokenTypes
import javax.swing.Icon

/**
 * Highlighted syntax isn't automatically available to the
 * 'Editor -> Color Scheme -> Nim' page. It has to be added
 * to the DESCRIPTORS array of this class.
 */
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
        return """Some nim code..."""
    }

    override fun getAdditionalHighlightingTagToDescriptorMap(): MutableMap<String, TextAttributesKey>? = null

    override fun getAttributeDescriptors(): Array<AttributesDescriptor> = DESCRIPTORS

    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

    override fun getDisplayName(): String = "Nim"
}
