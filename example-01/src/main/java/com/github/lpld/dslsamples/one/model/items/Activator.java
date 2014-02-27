package com.github.lpld.dslsamples.one.model.items;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import com.github.lpld.dslsamples.one.model.events.Event;
import com.github.lpld.dslsamples.one.model.events.EventType;
import com.github.lpld.dslsamples.one.model.map.Location;
import com.github.lpld.dslsamples.one.model.map.MapItem;

/**
 * A map item that can be activated only once.
 *
 * @author leopold
 * @since 12/29/13
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Activator extends MapItem {
    private boolean activated = false;

    public Activator(String id, Location location) {
        super(id, location);
    }

    public void activate() {
        if (!activated) {
            activated = true;
            publishEvent(new Event(EventType.ACTIVATOR_TRIGGERED, this));
        }
    }

}
