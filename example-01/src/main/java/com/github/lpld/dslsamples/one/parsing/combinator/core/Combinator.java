package com.github.lpld.dslsamples.one.parsing.combinator.core;

/**
 * Basic interface for parser combinators. The idea is simple: to implement
 * a grammar using a structure of parser objects. Each of this objects should
 * be represented as a parser combinator.
 *
 * @author leopold
 * @since 1/4/14
 */
public interface Combinator {
    ParsingStep stepOver(ParsingStep inbound);
}
