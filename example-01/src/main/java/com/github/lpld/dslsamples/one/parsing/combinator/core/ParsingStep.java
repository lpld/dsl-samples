package com.github.lpld.dslsamples.one.parsing.combinator.core;

import com.github.lpld.dslsamples.one.lexing.TokenBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author leopold
 * @since 1/4/14
 */
//@RequiredArgsConstructor
@Getter
public class ParsingStep {
    private final boolean success;
    private final TokenBuffer tokens;

    private Error error;
    private String matchValue;

    public ParsingStep(boolean success, TokenBuffer tokens) {
        this.success = success;
        this.tokens = tokens;
    }

    public ParsingStep(boolean success, TokenBuffer tokens, Error error) {
        this.success = success;
        this.tokens = tokens;
        this.error = error;
    }

    public ParsingStep(boolean success, TokenBuffer tokens, String matchValue) {
        this.success = success;
        this.tokens = tokens;
        this.matchValue = matchValue;
    }
}
