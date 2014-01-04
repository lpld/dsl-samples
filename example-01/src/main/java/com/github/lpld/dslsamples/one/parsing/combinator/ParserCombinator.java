package com.github.lpld.dslsamples.one.parsing.combinator;

/**
 * @author leopold
 * @since 1/4/14
 */
public interface ParserCombinator {
    ParsingStep stepOver(ParsingStep inbound);
}
