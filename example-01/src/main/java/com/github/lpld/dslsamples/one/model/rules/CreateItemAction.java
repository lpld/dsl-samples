package com.github.lpld.dslsamples.one.model.rules;

import com.github.lpld.dslsamples.one.model.Game;
import com.github.lpld.dslsamples.one.model.Player;
import com.github.lpld.dslsamples.one.model.map.GameMap;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import com.github.lpld.dslsamples.one.model.map.MapItem;

/**
 * @author leopold
 * @since 12/29/13
 */
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class CreateItemAction implements Action {
    private final MapItem itemToAdd;

    @Override
    public void execute(Game game) {
        game.getGameMap().addItem(itemToAdd);
    }
}
