package com.github.lpld.dslsamples.one.parsing.combinator.semantics.rules;

import com.github.lpld.dslsamples.one.lexing.TokenType;
import com.github.lpld.dslsamples.one.parsing.combinator.core.ParsingStep;
import com.github.lpld.dslsamples.one.parsing.combinator.core.ParsingUtils;
import com.github.lpld.dslsamples.one.parsing.combinator.core.SemanticsProcessor;
import com.github.lpld.dslsamples.one.parsing.combinator.semantics.GameModel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.Validate;

import java.util.List;

/**
 * @author leopold
 * @since 2/22/14
 */
@RequiredArgsConstructor
public class StatUpdateProcessor implements SemanticsProcessor {
    private final GameModel gameModel;

    @Override
    public void handleParsingResult(List<ParsingStep> steps, ParsingStep result) {
        Validate.notEmpty(steps);
        Validate.isTrue(steps.size() == 3, "Expected 3 but was ", steps.size());

        TokenType fieldToUpdate = steps.get(0).getMatchToken().getType();
        TokenType sign = steps.get(1).getMatchToken().getType();
        String value = steps.get(2).getMatchToken().getValue();

        int delta = ParsingUtils.parseInt(value, result, "Delta value");

        switch (sign) {
            case PLUS:
                break;
            case MINUS: delta = -delta;
                break;
        }

        switch (fieldToUpdate) {
            case HEALTH:
                gameModel.setDeltaHealth(delta);
                break;
            case STRENGTH:
                gameModel.setDeltaStrength(delta);
                break;
            case MANA:
                gameModel.setDeltaMana(delta);
                break;
            case MONEY:
                gameModel.setDeltaMoney(delta);
                break;
        }
    }
}
