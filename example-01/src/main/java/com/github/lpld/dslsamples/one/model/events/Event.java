package com.github.lpld.dslsamples.one.model.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.github.lpld.dslsamples.one.model.map.MapItem;

/**
 * Game event.
 *
 * @author leopold
 * @since 12/29/13
 */
@Getter
@RequiredArgsConstructor
public class Event {
    private final EventType type;
    private final MapItem target;

    public String getItemId() {
        return target.getId();
    }
}
