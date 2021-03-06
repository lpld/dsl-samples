package com.github.lpld.dslsamples.one.model.rules;

import com.github.lpld.dslsamples.one.model.Game;
import com.github.lpld.dslsamples.one.model.Player;
import com.github.lpld.dslsamples.one.model.map.GameMap;

/**
 * Basic interface for an action that is executed when some
 * event is triggered.
 *
 * @author leopold
 * @since 12/29/13
 */
public interface Action {
    void execute(Game game);
}
