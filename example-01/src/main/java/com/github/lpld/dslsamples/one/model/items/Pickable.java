package com.github.lpld.dslsamples.one.model.items;

import com.github.lpld.dslsamples.one.model.events.Event;
import com.github.lpld.dslsamples.one.model.events.EventType;
import com.github.lpld.dslsamples.one.model.map.Location;
import com.github.lpld.dslsamples.one.model.map.MapItem;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author leopold
 * @since 12/29/13
 */
@ToString
@EqualsAndHashCode(callSuper = true)
public class Pickable extends MapItem {
    public Pickable(String id, Location location) {
        super(id, location);
    }

    public void pick() {
        map.removeItem(this);
        publishEvent(new Event(EventType.ITEM_PICKED, this));
    }
}
