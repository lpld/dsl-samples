package com.github.lpld.dslsamples.one.lexing;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author leopold
 * @since 12/30/13
 */
@RequiredArgsConstructor
@Getter
public class Token {
    private final TokenType type;
    private final String value;
}
