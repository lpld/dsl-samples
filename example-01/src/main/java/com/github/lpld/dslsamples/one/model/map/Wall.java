package com.github.lpld.dslsamples.one.model.map;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author leopold
 * @since 12/29/13
 */
@ToString
@EqualsAndHashCode(callSuper = true)
public class Wall extends MapItem {
    public Wall(Location location) {
        super(NO_ID, location);
    }
}
