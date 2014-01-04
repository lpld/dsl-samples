package com.github.lpld.dslsamples.one;

import com.github.lpld.dslsamples.one.parsing.GameDefinitionParser;
import com.github.lpld.dslsamples.one.parsing.recursive.GameDefinitionRecursiveParser;

/**
 * @author leopold
 * @since 1/2/14
 */
public class RecursiveParserTest extends AbstractParserTest {
    @Override
    protected GameDefinitionParser createParser(String config) {
        return new GameDefinitionRecursiveParser(config);
    }
}
