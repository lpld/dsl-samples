package com.github.lpld.dslsamples.one.parsing.combinator;

import lombok.RequiredArgsConstructor;

/**
 * @author leopold
 * @since 1/4/14
 */
@RequiredArgsConstructor
public class ListCombinator implements ParserCombinator {

    private final ParserCombinator production;

    @Override
    public ParsingStep stepOver(ParsingStep inbound) {
        if (!inbound.isSuccess()) return inbound;

        ParsingStep latest = inbound;

        while (latest.isSuccess()) {
            latest = production.stepOver(latest);
            if (latest.isSuccess()) {
                // do smth
            }
        }

        return null;
    }
}
