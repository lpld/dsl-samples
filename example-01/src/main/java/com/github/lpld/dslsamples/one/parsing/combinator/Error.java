package com.github.lpld.dslsamples.one.parsing.combinator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author leopold
 * @since 1/4/14
 */
@RequiredArgsConstructor
@Getter
public class Error {
    private final String message;
    private final int line;
}
