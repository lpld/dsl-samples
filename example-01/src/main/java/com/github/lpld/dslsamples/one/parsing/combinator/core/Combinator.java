package com.github.lpld.dslsamples.one.parsing.combinator.core;

/**
 * @author leopold
 * @since 1/4/14
 */
public interface Combinator {
    ParsingStep stepOver(ParsingStep inbound);
}
