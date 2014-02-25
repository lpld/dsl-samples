package com.github.lpld.dslsamples.one.parsing.combinator.semantics.rules;

import com.github.lpld.dslsamples.one.lexing.TokenType;
import com.github.lpld.dslsamples.one.model.events.EventType;
import com.github.lpld.dslsamples.one.model.rules.Rule;
import com.github.lpld.dslsamples.one.parsing.combinator.core.*;
import com.github.lpld.dslsamples.one.parsing.combinator.core.Error;
import com.github.lpld.dslsamples.one.parsing.combinator.semantics.GameModel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.Validate;

import java.util.List;

/**
 * @author leopold
 * @since 2/22/14
 */
@RequiredArgsConstructor
public class RuleProcessor implements SemanticsProcessor {
    private final GameModel gameModel;

    @Override
    public void handleParsingResult(List<ParsingStep> steps, ParsingStep result) {
        Validate.notEmpty(steps);
        Validate.isTrue(steps.size() == 5, "Expected 5 but was ", steps.size());
        Validate.isTrue(steps.get(0).getMatchToken().getType() == TokenType.WHEN);
        Validate.isTrue(steps.get(4).getMatchToken().getType() == TokenType.END);

        String itemId = steps.get(1).getMatchToken().getValue();
        String event = steps.get(2).getMatchToken().getValue();

        EventType eventType = null;
        switch (event) {
            case "dead":
                eventType = EventType.NPC_DEAD;
                break;
            case "activated":
                eventType = EventType.ACTIVATOR_TRIGGERED;
                break;
            case "picked":
                eventType = EventType.ITEM_PICKED;
                break;
            case "visited":
                eventType = EventType.AREA_VISITED;
                break;
        }

        if (eventType == null) {
            result.setError(new Error(
                    "Unknown event: " + event,
                    steps.get(2).getMatchToken().getLineNumber()
            ));
        } else {
            Rule rule = new Rule(itemId, eventType);
            gameModel.getRules().put(rule, gameModel.getCurrentAction());
        }


    }
}
