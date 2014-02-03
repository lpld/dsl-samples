package com.github.lpld.dslsamples.one.parsing.combinator.semantics;

import com.github.lpld.dslsamples.one.lexing.TokenType;
import com.github.lpld.dslsamples.one.parsing.combinator.core.ParsingStep;
import com.github.lpld.dslsamples.one.parsing.combinator.core.SemanticsProcessor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.Validate;

import java.util.List;

/**
 * @author leopold
 * @since 2/3/14
 */
@RequiredArgsConstructor
public class StatDefProcessor implements SemanticsProcessor {
    private final GameModel gameModel;

    @Override
    public void handleParsingResult(List<ParsingStep> step) {
        Validate.notEmpty(step);
        Validate.isTrue(step.size() == 2);
        // TODO put line number in error message

        TokenType statName = step.get(0).getMatchToken().getType();
        String value = step.get(1).getMatchToken().getValue();

    }
}
