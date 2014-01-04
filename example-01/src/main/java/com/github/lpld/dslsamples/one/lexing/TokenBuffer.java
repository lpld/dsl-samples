package com.github.lpld.dslsamples.one.lexing;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author leopold
 * @since 1/4/14
 */
@RequiredArgsConstructor
public class TokenBuffer {
    private final List<Token> tokenList;
    private int currentToken = 0;

    @Getter
    private int currentLine = 0;

    public boolean hasNext() {
        return tokenList.size() > currentToken;
    }

    public Token nextToken() {
        if (!hasNext()) {
            new Token(TokenType.EOF, "eof", currentLine + 1);
        }
        return tokenList.get(currentToken);
    }

    public Token popToken() {
        Token token = nextToken();
        currentToken ++;
        currentLine = token.getLineNumber();
        return token;
    }
}
