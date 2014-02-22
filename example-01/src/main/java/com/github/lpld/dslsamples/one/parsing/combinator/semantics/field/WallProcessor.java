package com.github.lpld.dslsamples.one.parsing.combinator.semantics.field;

import com.github.lpld.dslsamples.one.model.map.Wall;
import com.github.lpld.dslsamples.one.parsing.combinator.core.ParsingStep;
import com.github.lpld.dslsamples.one.parsing.combinator.core.SemanticsProcessor;
import com.github.lpld.dslsamples.one.parsing.combinator.semantics.GameModel;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author leopold
 * @since 2/22/14
 */
@RequiredArgsConstructor
public class WallProcessor implements SemanticsProcessor {

    private final GameModel gameModel;
    @Override
    public void handleParsingResult(List<ParsingStep> steps) {
        gameModel.getItems().add(new Wall(
                gameModel.getCurrentLocation()
        ));
    }
}
