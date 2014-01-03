package com.github.lpld.dslsamples.one.lexing;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author leopold
 * @since 12/30/13
 */
public class Tokenizer {
    private Map<TokenType, Pattern> tokenPatterns = new LinkedHashMap<>();
    private String buffer;
    private List<Token> tokenList = new ArrayList<>();
    private int current = 0;

    public Tokenizer(String buffer) {
        this.buffer = buffer;

        for (TokenType tokenType : TokenType.values()) {
            tokenPatterns.put(tokenType, Pattern.compile(tokenType.regex));
        }

        boolean parseInProgress = true;
        while (parseInProgress) {
            parseInProgress = matchToken();
        }
    }

    public boolean hasNext() {
        return tokenList.size() > current;
    }

    public Token nextToken() {
        if (!hasNext()) {
            new Token(TokenType.EOF, "eof");
        }
        return tokenList.get(current);
    }

    public Token popToken() {
        return tokenList.get(current++);
    }

    private boolean matchToken() {
        boolean tokenMatch = false;
        Matcher matcher = null;
        for (Map.Entry<TokenType, Pattern> entry : tokenPatterns.entrySet()) {
            TokenType tokenType = entry.getKey();
            Pattern pattern = entry.getValue();
            matcher = pattern.matcher(buffer);

            if (matcher.find()) {
                tokenMatch = true;
                if (tokenType.output) {
                    tokenList.add(new Token(tokenType, matcher.group()));
                }
                buffer = buffer.substring(matcher.end());
                break;
            }
        }

        return tokenMatch;
    }
}
