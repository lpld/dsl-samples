package com.github.lpld.dslsamples.one.model.map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Basic object to specify a location on the map.
 *
 * @author leopold
 * @since 12/29/13
 */
@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class Location {
    private final int x;
    private final int y;
}
