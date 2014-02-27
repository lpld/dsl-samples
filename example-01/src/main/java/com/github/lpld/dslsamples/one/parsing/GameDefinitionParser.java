package com.github.lpld.dslsamples.one.parsing;

import com.github.lpld.dslsamples.one.model.Game;

/**
 * Basic interface for parsing game definition from a configuration file.
 *
 * @author leopold
 * @since 1/1/14
 */
public interface GameDefinitionParser {
    Game parse() throws ParsingException;
}
