package com.github.lpld.dslsamples.one.parsing.combinator;

import com.github.lpld.dslsamples.one.lexing.Token;
import com.github.lpld.dslsamples.one.lexing.TokenBuffer;
import com.github.lpld.dslsamples.one.lexing.TokenType;
import lombok.RequiredArgsConstructor;

/**
 * @author leopold
 * @since 1/4/14
 */
@RequiredArgsConstructor
public class TerminalParser implements ParserCombinator {
    private final TokenType tokenType;

    @Override
    public ParsingStep stepOver(ParsingStep inbound) {
        if (!inbound.isSuccess()) return inbound;

        ParsingStep result;

        Token token = inbound.getTokens().nextToken();

        if (tokenType == token.getType()) {
            result = new ParsingStep(true, inbound.getTokens().tail());
        } else {
            result = new ParsingStep(
                    false, inbound.getTokens(),
                    new Error("Expected " + tokenType + " but found " + token.getType(), token.getLineNumber())
            );
        }

        return result;
    }
}
