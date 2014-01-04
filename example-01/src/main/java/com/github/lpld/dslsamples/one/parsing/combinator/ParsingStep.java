package com.github.lpld.dslsamples.one.parsing.combinator;

import com.github.lpld.dslsamples.one.lexing.TokenBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author leopold
 * @since 1/4/14
 */
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class ParsingStep {
    private final boolean success;
    private final TokenBuffer tokens;

    private Error error;
}
