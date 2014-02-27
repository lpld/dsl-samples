package com.github.lpld.dslsamples.one.lexing;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * A class that represents a buffer of tokens with
 * methods to iterate through them.
 *
 * @author leopold
 * @since 1/4/14
 */
@RequiredArgsConstructor
public class TokenBuffer {
    private final List<Token> tokenList;
    private int currentToken = 0;

    @Getter
    private int currentLine = 0;

    /**
     * If there's a token in buffer
     */
    public boolean hasNext() {
        return tokenList.size() > currentToken;
    }

    /**
     * Read next token without moving the pointer.
     * @return Next token if it exists, {@link TokenType.EOF} otherwise.     *
     */
    public Token nextToken() {
        if (!hasNext()) {
            new Token(TokenType.EOF, "eof", currentLine + 1);
        }
        return tokenList.get(currentToken);
    }

    /**
     * Pop next token and move the pointer.
     * @return Next token if it exists, {@link TokenType.EOF} otherwise.     *
     */
    public Token popToken() {
        Token token = nextToken();
        currentToken ++;
        currentLine = token.getLineNumber();
        return token;
    }

    /**
     * Create new {@code TokenBuffer} that is a tail of this buffer.
     */
    public TokenBuffer tail() {
        TokenBuffer tail = new TokenBuffer(tokenList);
        tail.currentToken = this.currentToken + 1;
        return tail;
    }
}
