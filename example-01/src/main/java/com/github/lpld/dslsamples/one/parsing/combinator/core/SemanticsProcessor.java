package com.github.lpld.dslsamples.one.parsing.combinator.core;

import java.util.List;

/**
 * Interface to encapsulate all specific logic that will be applied
 * after the parsing is done.
 *
 * @author leopold
 * @since 2/1/14
 */
public interface SemanticsProcessor {
    void handleParsingResult(List<ParsingStep> steps, ParsingStep result);
}
