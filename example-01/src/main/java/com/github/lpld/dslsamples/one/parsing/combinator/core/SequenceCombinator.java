package com.github.lpld.dslsamples.one.parsing.combinator.core;

import lombok.Setter;

import java.util.ArrayList;
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

    public SequenceCombinator withSemanticsProcessor(SemanticsProcessor semanticsProcessor) {
        this.semanticsProcessor = semanticsProcessor;
        return this;
    }

    @Override
    public ParsingStep stepOver(ParsingStep inbound) {
         if (!inbound.isOk()) return inbound;

        ParsingStep latest = inbound;
        List<ParsingStep> results = new ArrayList<>();

        for (Combinator production : productions) {
            latest = production.stepOver(latest);
            results.add(latest);
            if (!latest.isOk()) break;
        }

        //ParsingStep result = latest; //.isOk() ? new ParsingStep(true, latest.getTokens()) : latest;

        if (latest.isOk() && semanticsProcessor != null) {
            semanticsProcessor.handleParsingResult(results, latest);
        }

        return latest;
    }
}
