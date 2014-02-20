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
public class ParserCombinatorTest {
    @Test
    public void testSomething() throws IOException, ParsingException {
        GameDefinitionParser parser = new GameDefinitionParserCombinator(readFileAsString("some_try"));
        //parser.parse();
    }

    private String readFileAsString(String filePath) throws IOException {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line).append('\n');
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }
}
