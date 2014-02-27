package com.github.lpld.dslsamples.one.model;

import com.github.lpld.dslsamples.one.model.events.EventType;
import com.github.lpld.dslsamples.one.model.map.GameMap;
import com.github.lpld.dslsamples.one.model.rules.Action;
import com.github.lpld.dslsamples.one.model.rules.Rule;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Game object which contains of Map, player and s set of rules to execute
 * different actions when certain events are triggered.
 *
 * @author leopold
 * @since 1/1/14
 */
@RequiredArgsConstructor
@Getter
public class Game {
    final GameMap gameMap;
    final Player player;
    final Map<Rule, Action> rules;

    public Game(GameMap gameMap, Player player) {
        this.gameMap = gameMap;
        this.player = player;
        this.rules = new HashMap<>();
    }

    public void addRule(String itemId, EventType eventType, Action action) {
        rules.put(new Rule(itemId, eventType), action);
    }
}
