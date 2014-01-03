package com.github.lpld.dslsamples.one.model.npc;

import com.github.lpld.dslsamples.one.model.events.Event;
import com.github.lpld.dslsamples.one.model.events.EventType;
import com.github.lpld.dslsamples.one.model.map.Location;
import com.github.lpld.dslsamples.one.model.map.MapItem;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author leopold
 * @since 12/29/13
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Npc extends MapItem {
    private final NpcClass npcClass;
    private int health;

    public Npc(String id, NpcClass npcClass, Location location) {
        super(id, location);
        this.npcClass = npcClass;
        this.health = npcClass.getInitialHealth();
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void attack(int hitStrength) {
        health -= hitStrength;
        if (!isAlive()) {
            publishEvent(new Event(EventType.NPC_DEAD, this));
        }
    }
}
