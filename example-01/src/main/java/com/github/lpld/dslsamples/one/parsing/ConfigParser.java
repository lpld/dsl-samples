package com.github.lpld.dslsamples.one.parsing;

import com.github.lpld.dslsamples.one.model.Game;

/**
 * @author leopold
 * @since 1/1/14
 */
public interface ConfigParser {
    Game parse() throws ParsingException;
}
