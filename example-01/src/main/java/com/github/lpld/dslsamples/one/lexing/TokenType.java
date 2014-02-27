package com.github.lpld.dslsamples.one.lexing;

import lombok.RequiredArgsConstructor;

/**
 * A set of all possible token types
 *
 * @author leopold
 * @since 12/30/13
 */
@RequiredArgsConstructor
public enum TokenType {
    CLASSES("^classes\\b", true),
    ATTITUDE("^attitude\\b", true),
    HEALTH("^health\\b", true),
    STRENGTH("^strength\\b", true),
    MANA("^mana\\b", true),
    END("^end\\b", true),
    FIELD("^field\\b", true),
    WIDTH("^width\\b", true),
    HEIGHT("^height\\b", true),
    START_POINT("^startPoint\\b", true),
    LEFT_PAR("^\\(", true),
    RIGHT_PAR("^\\)", true),
    NPCS("^npcs\\b", true),
    ITEMS("^items\\b", true),
    WALLS("^walls\\b", true),
    RULES("^rules\\b", true),
    WHEN("^when\\b", true),
    CLEAR("^clear\\b", true),
    CREATE("^create\\b", true),
    NPC("^npc\\b", true),
    ITEM("^item\\b", true),
    PLAYER_STATS("^playerStats\\b", true),
    PLUS("^plus\\b", true),
    MINUS("^minus\\b", true),
    MONEY("^money\\b", true),

    IDENTIFIER("^(\\w)+", true),

    COMMENTED_LINE("^#(.)*(\\r\\n|\\n)", false),
    EOL("^(\\r\\n|\\n)", false),
    WHITESPACE("^(\\s)+", false),
    EOF("^EOF", false);

    /**
     * Regex to match
     */
    final String regex;

    /**
     * If token of this type should be taken into account when processing
     * the syntax. For instance, comments, whitespaces, line endings will
     * be ignored and thus not processed.
     */
    final boolean output;
}
