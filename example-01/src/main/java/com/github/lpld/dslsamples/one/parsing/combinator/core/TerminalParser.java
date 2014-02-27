package com.github.lpld.dslsamples.one.parsing.combinator.core;

import com.github.lpld.dslsamples.one.lexing.Token;
import com.github.lpld.dslsamples.one.lexing.TokenType;
import lombok.RequiredArgsConstructor;

import java.lang.*;

/**
 * Parser for simple tokens.
 *
 * @author leopold
 * @since 1/4/14
 */
@RequiredArgsConstructor
public class TerminalParser implements Combinator {
    private final TokenType tokenType;

    @Override
    public ParsingStep stepOver(ParsingStep inbound) {
        if (!inbound.isParsingOk()) return inbound;

        ParsingStep result;

        Token token = inbound.getTokens().nextToken();

        if (tokenType == token.getType()) {
            result = new ParsingStep(true, inbound.getTokens().tail());
        } else {
            result = new ParsingStep(false, inbound.getTokens());
            result.setError(new Error(
                    "Illegal syntax: [" + token.getValue() + "]. Expected " + tokenType + " but found " + token.getType(),
                    token.getLineNumber()
            ));
        }

        result.setMatchToken(token);
        return result;
    }
}
