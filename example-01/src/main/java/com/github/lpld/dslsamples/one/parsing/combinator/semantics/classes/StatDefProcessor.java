package com.github.lpld.dslsamples.one.parsing.combinator.semantics.classes;

import com.github.lpld.dslsamples.one.lexing.TokenType;
import com.github.lpld.dslsamples.one.model.npc.Attitude;
import com.github.lpld.dslsamples.one.parsing.combinator.core.*;
import com.github.lpld.dslsamples.one.parsing.combinator.core.Error;
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
    public void handleParsingResult(List<ParsingStep> steps, ParsingStep result) {
        Validate.notEmpty(steps);
        Validate.isTrue(steps.size() == 2, "Expected 2 but was ", steps.size());

        TokenType statName = steps.get(0).getMatchToken().getType();
        String value = steps.get(1).getMatchToken().getValue();

        switch (statName) {
            case ATTITUDE:
                Attitude attitude = attitude(value);
                if (attitude == null) {
                    result.setError(new Error(
                            "Unknown attitude: " + value,
                            steps.get(1).getMatchToken().getLineNumber()
                    ));
                } else {
                    gameModel.setCurrentAttitude(attitude);
                }
                break;
            case STRENGTH:
                gameModel.setCurrentStrengh(
                        ParsingUtils.parseInt(value, result, "Strength")
                );
                break;
            case HEALTH:
                gameModel.setCurrentHealth(
                        ParsingUtils.parseInt(value, result, "Health")
                );
                break;
            case MANA:
                gameModel.setCurrentMana(
                        ParsingUtils.parseInt(value, result, "Mana")
                );
                break;
        }
    }

    private Attitude attitude(String value) {
        Attitude attitude = null;

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
        }
        return attitude;
    }
}
