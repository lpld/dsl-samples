package com.github.lpld.dslsamples.one.parsing.combinator.semantics;

import com.github.lpld.dslsamples.one.lexing.TokenType;
import com.github.lpld.dslsamples.one.model.map.Location;
import com.github.lpld.dslsamples.one.parsing.combinator.core.ParsingStep;
import com.github.lpld.dslsamples.one.parsing.combinator.core.ParsingUtils;
import com.github.lpld.dslsamples.one.parsing.combinator.core.SemanticsProcessor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.Validate;

import java.util.List;

/**
 * @author leopold
 * @since 2/22/14
 */
@RequiredArgsConstructor
public class LocationProcessor implements SemanticsProcessor {
    private final GameModel gameModel;

    @Override
    public void handleParsingResult(List<ParsingStep> steps, ParsingStep result) {
        Validate.notEmpty(steps);
        Validate.isTrue(steps.size() == 4, "Expected 4 but was ", steps.size());
        Validate.isTrue(steps.get(0).getMatchToken().getType() == TokenType.LEFT_PAR);
        Validate.isTrue(steps.get(3).getMatchToken().getType() == TokenType.RIGHT_PAR);

        ParsingUtils.parseInt(steps.get(1).getMatchToken().getValue(), result, "Location x");

        gameModel.setCurrentLocation(new Location(
                ParsingUtils.parseInt(steps.get(1).getMatchToken().getValue(), result, "Location x"),
                ParsingUtils.parseInt(steps.get(2).getMatchToken().getValue(), result, "Location y")
        ));
    }
}
