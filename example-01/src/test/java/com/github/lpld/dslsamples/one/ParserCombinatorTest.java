package com.github.lpld.dslsamples.one;

import com.github.lpld.dslsamples.one.parsing.GameDefinitionParser;
import com.github.lpld.dslsamples.one.parsing.ParsingException;
import com.github.lpld.dslsamples.one.parsing.combinator.GameDefinitionParserCombinator;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author leopold
 * @since 1/5/14
 */
public class ParserCombinatorTest extends AbstractParserTest {
    @Override
    protected GameDefinitionParser createParser(String config) {
        return new GameDefinitionParserCombinator(config);
    }
}
