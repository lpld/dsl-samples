package com.github.lpld.dslsamples.one.parsing.combinator.core;

import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * A combinator for a list of same structures.
 *
 * @author leopold
 * @since 1/4/14
 */
public class ListCombinator implements Combinator {

    private final Combinator production;

    @Setter
    private SemanticsProcessor semanticsProcessor;

    public ListCombinator(Combinator production) {
        this.production = production;
    }

    public ListCombinator(SemanticsProcessor semanticsProcessor, Combinator production) {
        this(production);
        this.semanticsProcessor = semanticsProcessor;
    }

    @Override
    public ParsingStep stepOver(ParsingStep inbound) {
        if (!inbound.isOk()) return inbound;

        ParsingStep latest = inbound;
        List<ParsingStep> results = new ArrayList<>();
        boolean atLeastOne = false;

        while (latest.isOk()) {
            latest = production.stepOver(latest);
            if (latest.isGrammarOk()) {
                atLeastOne = true;
                results.add(latest);
            }
        }

        if (atLeastOne && !latest.isGrammarOk()) {
            ParsingStep result = new ParsingStep(true, latest.getTokens());
            if (semanticsProcessor != null) {
                semanticsProcessor.handleParsingResult(results, result);
            }
            return result;
        } else {
            return latest;
        }
    }
}
