package com.github.lpld.dslsamples.one.parsing.combinator.core;

import java.util.List;

/**
 * @author leopold
 * @since 2/1/14
 */
public interface SemanticsProcessor {
    void handleParsingResult(List<ParsingStep> step);
}
