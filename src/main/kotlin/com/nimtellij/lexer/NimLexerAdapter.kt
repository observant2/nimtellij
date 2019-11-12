package com.nimtellij.lexer

import com.intellij.lexer.FlexAdapter

class NimLexerAdapter : FlexAdapter(NimLexer(null))
