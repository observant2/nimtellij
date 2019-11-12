package com.nimtellij

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import com.nimtellij.ide.NimLanguage
import com.nimtellij.lexer.NimLexerAdapter
import com.nimtellij.lexer.NimTokenTypes
import com.nimtellij.parser.NimParser
import com.nimtellij.psi.NimFile

class NimParserDefinition : ParserDefinition {
    companion object {
        val WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE)
        val COMMENTS = TokenSet.create(NimTokenTypes.LINE_COMMENT)

        val FILE = IFileElementType(NimLanguage.INSTANCE)
    }

    override fun createParser(project: Project?): PsiParser {
        return NimParser()
    }

    override fun createFile(viewProvider: FileViewProvider): PsiFile {
        return NimFile(viewProvider)
    }

    override fun getStringLiteralElements(): TokenSet {
        return TokenSet.EMPTY
    }

    override fun getFileNodeType(): IFileElementType {
        return FILE;
    }

    override fun createLexer(project: Project?): Lexer {
        return NimLexerAdapter()
    }

    override fun createElement(node: ASTNode): PsiElement {
        return ASTWrapperPsiElement(node)
    }

    override fun getCommentTokens(): TokenSet {
        return COMMENTS
    }

    override fun getWhitespaceTokens(): TokenSet {
        return WHITE_SPACES
    }

    override fun spaceExistenceTypeBetweenTokens(left: ASTNode?, right: ASTNode?): ParserDefinition.SpaceRequirements {
        return ParserDefinition.SpaceRequirements.MAY
    }
}
