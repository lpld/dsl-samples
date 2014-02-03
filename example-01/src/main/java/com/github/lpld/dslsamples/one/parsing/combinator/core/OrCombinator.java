package com.github.lpld.dslsamples.one.parsing.combinator.core;

import java.util.Arrays;
import java.util.List;

/**
 * @author leopold
 * @since 2/3/14
 */
public class OrCombinator implements Combinator {
    private final List<Combinator> productions;

    public OrCombinator(Combinator... productions) {
        this.productions = Arrays.asList(productions);
    }

    @Override
    public ParsingStep stepOver(ParsingStep inbound) {
        if (!inbound.isSuccess()) return inbound;

        ParsingStep latest = new ParsingStep(false, inbound.getTokens());

        for (Combinator production : productions) {
            latest = production.stepOver(inbound);

            if (latest.isSuccess()) break;
        }

        return latest;
    }
}
