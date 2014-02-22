package com.github.lpld.dslsamples.one.parsing.combinator.semantics.rules;

import com.github.lpld.dslsamples.one.lexing.TokenType;
import com.github.lpld.dslsamples.one.model.rules.UpdatePlayerStatsAction;
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
public class PlayerStatsActionProcessor implements SemanticsProcessor {
    private final GameModel gameModel;

    @Override
    public void handleParsingResult(List<ParsingStep> steps) {
        Validate.notEmpty(steps);
        Validate.isTrue(steps.size() == 3, "Expected 3 but was ", steps.size());

        gameModel.setCurrentAction(new UpdatePlayerStatsAction(
                gameModel.getDeltaHealth(),
                gameModel.getDeltaStrength(),
                gameModel.getDeltaMana(),
                gameModel.getDeltaMoney()
        ));

        gameModel.setDeltaHealth(0);
        gameModel.setDeltaStrength(0);
        gameModel.setDeltaMana(0);
        gameModel.setDeltaMoney(0);
    }
}
