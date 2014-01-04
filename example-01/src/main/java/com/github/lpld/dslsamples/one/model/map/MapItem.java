package com.github.lpld.dslsamples.one.model.map;

import com.github.lpld.dslsamples.one.model.events.Event;
import com.github.lpld.dslsamples.one.model.events.GameEventListener;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author leopold
 * @since 12/29/13
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id", "location"})
public abstract class MapItem {
    protected final static String NO_ID = "_";

    protected final String id;
    protected Location location;

    protected GameMap map;

    protected MapItem(String id, Location location) {
        this.id = id;
        this.location = location;
    }

    protected void publishEvent(Event event) {
        map.publishEvent(event);
    }
}
