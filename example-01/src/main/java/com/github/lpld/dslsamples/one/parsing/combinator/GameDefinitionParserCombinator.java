package com.github.lpld.dslsamples.one.parsing.combinator;

import com.github.lpld.dslsamples.one.lexing.TokenBuffer;
import com.github.lpld.dslsamples.one.lexing.TokenType;
import com.github.lpld.dslsamples.one.lexing.Tokenizer;
import com.github.lpld.dslsamples.one.model.Game;
import com.github.lpld.dslsamples.one.parsing.GameDefinitionParser;
import com.github.lpld.dslsamples.one.parsing.ParsingException;
import com.github.lpld.dslsamples.one.parsing.combinator.core.*;

/**
 * @author leopold
 * @since 1/4/14
 */
public class GameDefinitionParserCombinator implements GameDefinitionParser {

    private final String buffer;
    private final TokenBuffer tokenBuffer;

    private Combinator tClasses = new TerminalParser(TokenType.CLASSES);
    private Combinator tAttitude = new TerminalParser(TokenType.ATTITUDE);
    private Combinator tHealth = new TerminalParser(TokenType.HEALTH);
    private Combinator tStrength = new TerminalParser(TokenType.STRENGTH);
    private Combinator tMana = new TerminalParser(TokenType.MANA);
    private Combinator tEnd = new TerminalParser(TokenType.END);
    private Combinator tField = new TerminalParser(TokenType.FIELD);
    private Combinator tWidth = new TerminalParser(TokenType.WIDTH);
    private Combinator tHeight = new TerminalParser(TokenType.HEIGHT);
    private Combinator tStartPoint = new TerminalParser(TokenType.START_POINT);
    private Combinator tLeftPar = new TerminalParser(TokenType.LEFT_PAR);
    private Combinator tRightPar = new TerminalParser(TokenType.RIGHT_PAR);
    private Combinator tNpcs = new TerminalParser(TokenType.NPCS);
    private Combinator tItems = new TerminalParser(TokenType.ITEMS);
    private Combinator tWalls = new TerminalParser(TokenType.WALLS);
    private Combinator tRules = new TerminalParser(TokenType.RULES);
    private Combinator tWhen = new TerminalParser(TokenType.WHEN);
    private Combinator tClear = new TerminalParser(TokenType.CLEAR);
    private Combinator tCreate = new TerminalParser(TokenType.CREATE);
    private Combinator tNpc = new TerminalParser(TokenType.NPC);
    private Combinator tItem = new TerminalParser(TokenType.ITEM);
    private Combinator tPlayerStats = new TerminalParser(TokenType.PLAYER_STATS);
    private Combinator tPlus = new TerminalParser(TokenType.PLUS);
    private Combinator tMinus = new TerminalParser(TokenType.MINUS);
    private Combinator tMoney = new TerminalParser(TokenType.MONEY);
    private Combinator tIdentifier = new TerminalParser(TokenType.IDENTIFIER);


    private Combinator classStatDef = new SequenceCombinator(tIdentifier, tIdentifier);
    private Combinator classDef = new SequenceCombinator(tIdentifier, new ListCombinator(classStatDef), tEnd);
    private Combinator classesList = new ListCombinator(classDef);
//    private Combinator npcDef = new SequenceCombinator(npc, strength, identifier, end);
//    private Combinator npcList = new SequenceCombinator(npcs, new ListCombinator(npcDef), end);

    public GameDefinitionParserCombinator(String buffer) {
        if (buffer == null) {
            throw new NullPointerException();
        }

        this.buffer = buffer;
        this.tokenBuffer = new Tokenizer(buffer).getTokens();
    }

    @Override
    public Game parse() throws ParsingException {
//        npcList.stepOver(new ParsingStep(true, tokenBuffer));
        return null;
    }
}
