package com.github.lpld.dslsamples.one.parsing.combinator.core;

import com.github.lpld.dslsamples.one.lexing.Token;
import com.github.lpld.dslsamples.one.lexing.TokenBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author leopold
 * @since 1/4/14
 */
@RequiredArgsConstructor
@Getter
@Setter
public class ParsingStep {
    private final TokenBuffer tokens;
    private Error error;
    private Token matchToken;

    public boolean isSuccess() {
        return error == null;
    }
}
