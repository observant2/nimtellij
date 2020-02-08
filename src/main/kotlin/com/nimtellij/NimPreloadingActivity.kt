package com.nimtellij

import com.intellij.openapi.application.PreloadingActivity
import com.intellij.openapi.progress.ProgressIndicator
import org.apache.commons.lang.SystemUtils
import org.wso2.lsp4intellij.IntellijLanguageClient
import org.wso2.lsp4intellij.client.languageserver.serverdefinition.RawCommandServerDefinition

/**
 * This class connects nimlsp with IntelliJ.
 * It uses lsp4intellij's predefined `RawCommandServerDefinition`
 * to do that.
 */
class NimPreloadingActivity : PreloadingActivity() {
    override fun preload(indicator: ProgressIndicator) {
        var command = "nimlsp"
        if (SystemUtils.IS_OS_WINDOWS) {
            command += ".cmd"
        }

        IntellijLanguageClient
            .addServerDefinition(
                RawCommandServerDefinition(
                    "nim",
                    arrayOf(command)
                )
            )
    }
}
