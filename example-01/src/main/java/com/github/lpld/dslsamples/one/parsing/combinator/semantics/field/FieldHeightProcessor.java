package com.github.lpld.dslsamples.one.parsing.combinator.semantics.field;

import com.github.lpld.dslsamples.one.lexing.TokenType;
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
public class FieldHeightProcessor implements SemanticsProcessor {

    private final GameModel gameModel;

    @Override
    public void handleParsingResult(List<ParsingStep> steps, ParsingStep result) {
        Validate.notEmpty(steps);
        Validate.isTrue(steps.size() == 2, "Expected 2 but was ", steps.size());
        Validate.isTrue(steps.get(0).getMatchToken().getType() == TokenType.HEIGHT);


        gameModel.setHeight(ParsingUtils.parseInt(
                steps.get(1).getMatchToken().getValue(),
                result,
                "Field height"
        ));
    }
}
