package com.github.lpld.dslsamples.one.parsing.combinator.semantics.field;

import com.github.lpld.dslsamples.one.model.items.Activator;
import com.github.lpld.dslsamples.one.model.items.Area;
import com.github.lpld.dslsamples.one.model.items.Pickable;
import com.github.lpld.dslsamples.one.model.map.Location;
import com.github.lpld.dslsamples.one.model.map.MapItem;
import com.github.lpld.dslsamples.one.model.npc.Npc;
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
public class ItemProcessor implements SemanticsProcessor {
    private final GameModel gameModel;
    @Override
    public void handleParsingResult(List<ParsingStep> steps, ParsingStep result) {
        Validate.notEmpty(steps);
        Validate.isTrue(steps.size() == 3, "Expected 3 but was ", steps.size());

        String itemId = steps.get(0).getMatchToken().getValue();
        String itemType = steps.get(1).getMatchToken().getValue();
        Location itemLocation = gameModel.getCurrentLocation();

        MapItem item = null;
        switch (itemType) {
            case "activator":
                item = new Activator(itemId, itemLocation);
                break;
            case "pickable":
                item = new Pickable(itemId, itemLocation);
                break;
            case "area":
                item = new Area(itemId, itemLocation);
                break;
        }
        if (item != null) {
            gameModel.setCurrentItem(item);
        } else {
            result.setError(new Error(
                    "Unknown item type: " + itemType,
                    result.getMatchToken().getLineNumber()
            ));
        }
    }
}
