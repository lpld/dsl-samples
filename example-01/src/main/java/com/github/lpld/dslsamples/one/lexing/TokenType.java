package com.github.lpld.dslsamples.one.lexing;

import lombok.RequiredArgsConstructor;

/**
 * @author leopold
 * @since 12/30/13
 */
@RequiredArgsConstructor
public enum TokenType {
    CLASSES("^classes", true),
    ATTITUDE("^attitude", true),
    HEALTH("^health", true),
    STRENGTH("^strength", true),
    MANA("^mana", true),
    END("^end", true),
    FIELD("^field", true),
    WIDTH("^width", true),
    HEIGHT("^height", true),
    START_POINT("^startPoint", true),
    LEFT_PAR("^\\(", true),
    RIGHT_PAR("^\\)", true),
    NPCS("^npcs", true),
    ITEMS("^items", true),
    WALLS("^walls", true),
    RULES("^rules", true),
    WHEN("^when", true),
    CLEAR("^clear", true),
    CREATE("^create", true),
    NPC("^npc", true),
    ITEM("^item", true),
    PLAYER_STATS("^playerStats", true),
    PLUS("^plus", true),
    MINUS("^minus", true),
    MONEY("^money", true),

    IDENTIFIER("^(\\w)+", true),
    WHITESPACE("^(\\s)+", false),
    EOF("^EOF", false);

    final String regex;
    final boolean output;
}
