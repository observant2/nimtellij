package com.nimtellij.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.nimtellij.ide.NimFileType
import com.nimtellij.ide.NimLanguage

class NimFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, NimLanguage.INSTANCE) {
    override fun getFileType(): FileType {
        return NimFileType.INSTANCE
    }

    override fun toString(): String {
        return "Nim File";
    }
}
