package com.nimtellij.ide

import com.intellij.lang.Language
import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

class NimLanguage private constructor(): Language("Nim") {
    companion object {
        val INSTANCE = NimLanguage()
    }
}

class NimIcons {
    companion object {
        val FILE: Icon = IconLoader.getIcon("/icons/nim-icon.png")
    }
}

class NimFileType private constructor() : LanguageFileType(NimLanguage.INSTANCE) {
    companion object {
        @JvmField
        val INSTANCE = NimFileType()
    }

    override fun getIcon(): Icon? {
        return NimIcons.FILE
    }

    override fun getName(): String {
        return "Nim File"
    }

    override fun getDefaultExtension(): String {
        return "nim"
    }

    override fun getDescription(): String {
        return "Nim File"
    }
}
