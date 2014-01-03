package com.github.lpld.dslsamples.one.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import com.github.lpld.dslsamples.one.model.map.Location;
import com.github.lpld.dslsamples.one.model.map.MapItem;

/**
 * @author leopold
 * @since 12/29/13
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Player extends MapItem {
    private int health;
    private int strength;
    private int mana;
    private int money;

    public Player(Location location) {
        super("player", location);
    }
}
