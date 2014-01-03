package com.github.lpld.dslsamples.one.model;

import com.github.lpld.dslsamples.one.model.events.GameEventListener;
import lombok.RequiredArgsConstructor;
import com.github.lpld.dslsamples.one.model.events.Event;
import com.github.lpld.dslsamples.one.model.events.EventType;
import com.github.lpld.dslsamples.one.model.rules.Action;
import com.github.lpld.dslsamples.one.model.rules.Rule;

/**
 * @author leopold
 * @since 12/29/13
 */
@RequiredArgsConstructor
public class GameProcessor implements GameEventListener {
    private final Game game;

    @Override
    public void handleEvent(Event event) {
        Action action = game.rules.get(new Rule(event.getItemId(), event.getType()));

        if (action != null) {
            action.execute(game);
        }
    }
}
