package com.github.lpld.dslsamples.one.parsing.combinator.semantics.classes;

import com.github.lpld.dslsamples.one.lexing.TokenType;
import com.github.lpld.dslsamples.one.model.npc.Attitude;
import com.github.lpld.dslsamples.one.parsing.combinator.core.ParsingStep;
import com.github.lpld.dslsamples.one.parsing.combinator.core.SemanticsProcessor;
import com.github.lpld.dslsamples.one.parsing.combinator.semantics.GameModel;
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
    public void handleParsingResult(List<ParsingStep> steps) {
        Validate.notEmpty(steps);
        Validate.isTrue(steps.size() == 2, "Expected 2 but was ", steps.size());
        // TODO put line number in error message

        TokenType statName = steps.get(0).getMatchToken().getType();
        String value = steps.get(1).getMatchToken().getValue();

        switch (statName) {
            case ATTITUDE:
                attitude(value);
                break;
            case STRENGTH:
                gameModel.setCurrentStrengh(Integer.parseInt(value)); //todo error handling
                break;
            case HEALTH:
                gameModel.setCurrentHealth(Integer.parseInt(value));
                break;
            case MANA:
                gameModel.setCurrentMana(Integer.parseInt(value));
                break;
        }
    }

    private void attitude(String value) {
        Attitude attitude;

        switch (value) {
            case "ally":
                attitude = Attitude.ALLY;
                break;
            case "neutral":
                attitude = Attitude.NEUTRAL;
                break;
            case "enemy":
                attitude = Attitude.ENEMY;
                break;
            default:
                throw new IllegalStateException();
        }
        gameModel.setCurrentAttitude(attitude);
    }
}
