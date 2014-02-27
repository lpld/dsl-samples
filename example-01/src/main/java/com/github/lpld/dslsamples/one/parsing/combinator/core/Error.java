package com.github.lpld.dslsamples.one.parsing.combinator.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Parsing error.
 *
 * @author leopold
 * @since 1/4/14
 */
@RequiredArgsConstructor
@Getter
public class Error {
    private final String message;
    private final int line;
}
