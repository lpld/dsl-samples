package com.github.lpld.dslsamples.one.model.rules;

import com.github.lpld.dslsamples.one.model.Game;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import com.github.lpld.dslsamples.one.model.map.Location;

/**
 * Action that clears a specified cell on the game map.
 *
 * @author leopold
 * @since 12/29/13
 */
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class ClearCellAction implements Action {
    private final Location location;

    @Override
    public void execute(Game game) {
        game.getGameMap().removeItem(location);
    }
}
