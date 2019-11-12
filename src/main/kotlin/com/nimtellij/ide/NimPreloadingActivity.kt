package com.nimtellij.ide

import com.intellij.openapi.application.PreloadingActivity
import com.intellij.openapi.progress.ProgressIndicator
import org.wso2.lsp4intellij.IntellijLanguageClient
import org.wso2.lsp4intellij.client.languageserver.serverdefinition.RawCommandServerDefinition

class NimPreloadingActivity : PreloadingActivity() {
    override fun preload(indicator: ProgressIndicator) {
        IntellijLanguageClient
            .addServerDefinition(
                RawCommandServerDefinition(
                    "nim",
                    arrayOf("C:/Users/Admin/Documents/eigene_programme/nimlsp.exe")
                )
            )
    }
}