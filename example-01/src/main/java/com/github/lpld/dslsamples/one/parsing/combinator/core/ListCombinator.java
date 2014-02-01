package com.github.lpld.dslsamples.one.parsing.combinator.core;

import lombok.RequiredArgsConstructor;

/**
 * @author leopold
 * @since 1/4/14
 */
@RequiredArgsConstructor
public class ListCombinator implements Combinator {

    private final Combinator production;

    @Override
    public ParsingStep stepOver(ParsingStep inbound) {
        if (!inbound.isSuccess()) return inbound;

        ParsingStep latest = inbound;
        boolean atLeastOne = false;

        while (latest.isSuccess()) {
            atLeastOne = true;
            latest = production.stepOver(latest);
            if (latest.isSuccess()) {
                // do smth
            }
        }

        return atLeastOne ? new ParsingStep(true, latest.getTokens()) : latest;
    }
}
