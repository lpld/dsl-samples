package com.github.lpld.dslsamples.one.parsing.combinator.semantics.rules;

import com.github.lpld.dslsamples.one.lexing.TokenType;
import com.github.lpld.dslsamples.one.model.rules.CreateItemAction;
import com.github.lpld.dslsamples.one.parsing.combinator.core.ParsingStep;
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
public class CreateItemActionProcessor implements SemanticsProcessor {
    private final GameModel gameModel;

    @Override
    public void handleParsingResult(List<ParsingStep> steps) {
        Validate.notEmpty(steps);
        Validate.isTrue(steps.size() == 4, "Expected 4 but was ", steps.size());

        Validate.isTrue(steps.get(0).getMatchToken().getType() == TokenType.CREATE);
        Validate.isTrue(steps.get(1).getMatchToken().getType() == TokenType.ITEM);
        Validate.isTrue(steps.get(3).getMatchToken().getType() == TokenType.END);

        gameModel.setCurrentAction(new CreateItemAction(gameModel.getCurrentItem()));
    }
}
