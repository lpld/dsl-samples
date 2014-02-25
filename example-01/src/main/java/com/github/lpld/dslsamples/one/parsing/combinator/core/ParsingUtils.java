package com.github.lpld.dslsamples.one.parsing.combinator.core;

/**
 * @author leopold
 * @since 2/25/14
 */
public final class ParsingUtils {
    private ParsingUtils() {
    }

    public static int parseInt(String stringValue, ParsingStep result, String fieldName) {
        int value = 0;
        try {
            value = Integer.parseInt(stringValue);
        } catch (NumberFormatException ex) {
            result.setError(new Error(
                    fieldName + " must be valid integer: " + stringValue,
                    result.getMatchToken().getLineNumber()
            ));
        }

        return value;
    }
}
