package com.github.lpld.dslsamples.one;

import com.github.lpld.dslsamples.one.parsing.ConfigParser;
import com.github.lpld.dslsamples.one.parsing.recursive.ConfigRecursiveParser;

/**
 * @author leopold
 * @since 1/2/14
 */
public class RecursiveParserTest extends AbstractParserTest {
    @Override
    protected ConfigParser createParser(String config) {
        return new ConfigRecursiveParser(config);
    }
}
