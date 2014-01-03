package com.github.lpld.dslsamples.one.model.rules;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import com.github.lpld.dslsamples.one.model.events.EventType;

/**
 * @author leopold
 * @since 12/29/13
 */
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
@ToString
public class Rule {
    private final String itemId;
    private final EventType eventType;
}
