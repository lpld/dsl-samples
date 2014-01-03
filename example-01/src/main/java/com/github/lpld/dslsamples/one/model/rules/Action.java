package com.github.lpld.dslsamples.one.model.rules;

import com.github.lpld.dslsamples.one.model.Game;
import com.github.lpld.dslsamples.one.model.Player;
import com.github.lpld.dslsamples.one.model.map.GameMap;

/**
 * @author leopold
 * @since 12/29/13
 */
public interface Action {
    void execute(Game game);
}
