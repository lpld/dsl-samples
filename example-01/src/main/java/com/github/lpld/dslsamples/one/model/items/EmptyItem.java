package com.github.lpld.dslsamples.one.model.items;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import com.github.lpld.dslsamples.one.model.map.Location;
import com.github.lpld.dslsamples.one.model.map.MapItem;

/**
 * @author leopold
 * @since 12/29/13
 */
@ToString
@EqualsAndHashCode(callSuper = true)
public class EmptyItem extends MapItem {
    public EmptyItem(Location location) {
        super(NO_ID, location);
    }
}
