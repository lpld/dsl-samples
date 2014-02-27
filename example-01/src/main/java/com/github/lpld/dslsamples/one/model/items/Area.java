package com.github.lpld.dslsamples.one.model.items;

import com.github.lpld.dslsamples.one.model.events.Event;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import com.github.lpld.dslsamples.one.model.events.EventType;
import com.github.lpld.dslsamples.one.model.map.Location;
import com.github.lpld.dslsamples.one.model.map.MapItem;

/**
 * Map item that triggers an event each time it is visited by player.
 *
 * @author leopold
 * @since 12/29/13
 */
@ToString
@EqualsAndHashCode(callSuper = true)
public class Area extends MapItem {
    public Area(String id, Location location) {
        super(id, location);
    }

    public void visit() {
        publishEvent(new Event(EventType.AREA_VISITED, this));
    }
}
