package com.github.lpld.dslsamples.one.model.npc;

import lombok.*;

/**
 * @author leopold
 * @since 12/29/13
 */
@Setter
@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class NpcClass {
    private final String name;
    private int initialHealth;
    private int strength;
    private int mana;
    private Attitude attitude;
}
