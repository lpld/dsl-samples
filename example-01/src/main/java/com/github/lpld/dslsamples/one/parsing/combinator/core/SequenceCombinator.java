package com.github.lpld.dslsamples.one.parsing.combinator.core;

import lombok.Setter;

import java.util.Arrays;
import java.util.List;

/**
 * @author leopold
 * @since 1/5/14
 */
public class SequenceCombinator implements Combinator {

    private final List<Combinator> productions;

    @Setter
    private SemanticsProcessor semanticsProcessor;

    public SequenceCombinator(Combinator... productions) {
        this.productions = Arrays.asList(productions);
    }

    public SequenceCombinator(SemanticsProcessor semanticsProcessor, Combinator... productions) {
        this(productions);
        this.semanticsProcessor = semanticsProcessor;
    }

    @Override
    public ParsingStep stepOver(ParsingStep inbound) {
        if (!inbound.isSuccess()) return inbound;

        ParsingStep latest = inbound;

        for (Combinator production : productions) {
            latest = production.stepOver(latest);

            if (!latest.isSuccess()) break;
        }

        return latest.isSuccess() ? new ParsingStep(true, latest.getTokens()) : latest;
    }
}
