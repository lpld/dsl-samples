package com.github.lpld.dslsamples.one.parsing.combinator.core;

import com.github.lpld.dslsamples.one.lexing.Token;
import com.github.lpld.dslsamples.one.lexing.TokenType;
import lombok.RequiredArgsConstructor;

import java.lang.*;

/**
 * @author leopold
 * @since 1/4/14
 */
@RequiredArgsConstructor
public class TerminalParser implements Combinator {
    private final TokenType tokenType;

    @Override
    public ParsingStep stepOver(ParsingStep inbound) {
        if (!inbound.isSuccess()) return inbound;

        ParsingStep result;

        Token token = inbound.getTokens().nextToken();

        if (tokenType == token.getType()) {
            result = new ParsingStep(true, inbound.getTokens().tail());
            result.setMatchToken(token);
        } else {
            result = new ParsingStep(false, inbound.getTokens());

            result.setError(new Error("Expected " + tokenType + " but found " + token.getType(), token.getLineNumber()));
        }

        return result;
    }
}
