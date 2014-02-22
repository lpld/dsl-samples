package com.github.lpld.dslsamples.one.parsing.combinator;

import com.github.lpld.dslsamples.one.lexing.TokenBuffer;
import com.github.lpld.dslsamples.one.lexing.TokenType;
import com.github.lpld.dslsamples.one.lexing.Tokenizer;
import com.github.lpld.dslsamples.one.model.Game;
import com.github.lpld.dslsamples.one.parsing.GameDefinitionParser;
import com.github.lpld.dslsamples.one.parsing.ParsingException;
import com.github.lpld.dslsamples.one.parsing.combinator.core.*;
import com.github.lpld.dslsamples.one.parsing.combinator.semantics.LocationProcessor;
import com.github.lpld.dslsamples.one.parsing.combinator.semantics.classes.ClassProcessor;
import com.github.lpld.dslsamples.one.parsing.combinator.semantics.GameModel;
import com.github.lpld.dslsamples.one.parsing.combinator.semantics.classes.StatDefProcessor;
import com.github.lpld.dslsamples.one.parsing.combinator.semantics.field.*;

/**
 * @author leopold
 * @since 1/4/14
 */
public class GameDefinitionParserCombinator implements GameDefinitionParser {

    private final String buffer;
    private final TokenBuffer tokenBuffer;

    private final GameModel gameModel = new GameModel();

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

    // classes
    private Combinator classStatDef = new SequenceCombinator(new OrCombinator(tAttitude, tHealth, tStrength, tMana), tIdentifier)
            .withSemanticsProcessor(new StatDefProcessor(gameModel));

    private Combinator classDef = new SequenceCombinator(tIdentifier, new ListCombinator(classStatDef), tEnd)
            .withSemanticsProcessor(new ClassProcessor(gameModel));

    private Combinator classesList = new SequenceCombinator(tClasses, new ListCombinator(classDef), tEnd);

    // field
    private Combinator fieldWidth = new SequenceCombinator(tWidth, tIdentifier)
            .withSemanticsProcessor(new FieldWidthProcessor(gameModel));
    private Combinator fieldHeight = new SequenceCombinator(tHeight, tIdentifier)
            .withSemanticsProcessor(new FieldHeightProcessor(gameModel));
    private Combinator location = new SequenceCombinator(tLeftPar, tIdentifier, tIdentifier, tRightPar)
            .withSemanticsProcessor(new LocationProcessor(gameModel));
    private Combinator startPoint = new SequenceCombinator(tStartPoint, location)
            .withSemanticsProcessor(new StartPointProcessor(gameModel));

    // npcs
    private Combinator npc = new SequenceCombinator(tIdentifier, tIdentifier, location)
            .withSemanticsProcessor(new NpcProcessor(gameModel));
    private Combinator npcItem = new SequenceCombinator(npc)
            .withSemanticsProcessor(new NpcItemProcessor(gameModel));
    private Combinator npcsList = new SequenceCombinator(tNpcs, new ListCombinator(npcItem), tEnd);
    // walls
    private Combinator wall = new SequenceCombinator(location)
            .withSemanticsProcessor(new WallProcessor(gameModel));
    private Combinator walls = new SequenceCombinator(tWalls, new ListCombinator(wall), tEnd);

    private Combinator fieldDef = new SequenceCombinator(tField, fieldWidth, fieldHeight, startPoint, npcsList, walls, tEnd);



    private Combinator all = new SequenceCombinator(classesList, fieldDef);




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
        all.stepOver(new ParsingStep(true, tokenBuffer));
        return null;
    }
}
