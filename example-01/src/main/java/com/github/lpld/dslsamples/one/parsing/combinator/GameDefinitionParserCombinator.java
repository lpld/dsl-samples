package com.github.lpld.dslsamples.one.parsing.combinator;

import com.github.lpld.dslsamples.one.lexing.TokenBuffer;
import com.github.lpld.dslsamples.one.lexing.TokenType;
import com.github.lpld.dslsamples.one.lexing.Tokenizer;
import com.github.lpld.dslsamples.one.model.Game;
import com.github.lpld.dslsamples.one.model.Player;
import com.github.lpld.dslsamples.one.model.map.GameMap;
import com.github.lpld.dslsamples.one.model.map.MapItem;
import com.github.lpld.dslsamples.one.parsing.GameDefinitionParser;
import com.github.lpld.dslsamples.one.parsing.ParsingException;
import com.github.lpld.dslsamples.one.parsing.combinator.core.*;
import com.github.lpld.dslsamples.one.parsing.combinator.semantics.LocationProcessor;
import com.github.lpld.dslsamples.one.parsing.combinator.semantics.classes.ClassProcessor;
import com.github.lpld.dslsamples.one.parsing.combinator.semantics.GameModel;
import com.github.lpld.dslsamples.one.parsing.combinator.semantics.classes.StatDefProcessor;
import com.github.lpld.dslsamples.one.parsing.combinator.semantics.field.*;
import com.github.lpld.dslsamples.one.parsing.combinator.semantics.rules.*;

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
    private Combinator npcInit = new SequenceCombinator(npc)
            .withSemanticsProcessor(new NpcInitProcessor(gameModel));
    private Combinator npcsList = new SequenceCombinator(tNpcs, new ListCombinator(npcInit), tEnd);
    // walls
    private Combinator wall = new SequenceCombinator(location)
            .withSemanticsProcessor(new WallProcessor(gameModel));
    private Combinator walls = new SequenceCombinator(tWalls, new ListCombinator(wall), tEnd);

    // items
    private Combinator item = new SequenceCombinator(tIdentifier, tIdentifier, location)
            .withSemanticsProcessor(new ItemProcessor(gameModel));
    private Combinator itemInit = new SequenceCombinator(item)
            .withSemanticsProcessor(new ItemInitProcessor(gameModel));
    private Combinator itemsList = new SequenceCombinator(tItems, new ListCombinator(itemInit), tEnd);

    private Combinator fieldDef = new SequenceCombinator(tField, fieldWidth, fieldHeight, startPoint, npcsList, itemsList, walls, tEnd);

    // rules
    private Combinator clearCellAction = new SequenceCombinator(tClear, location)
            .withSemanticsProcessor(new ClearCellActionProcessor(gameModel));
    private Combinator createNpcAction = new SequenceCombinator(tCreate, tNpc, npc, tEnd)
            .withSemanticsProcessor(new CreateNpcActionProcessor(gameModel));
    private Combinator createItemAction = new SequenceCombinator(tCreate, tItem, item, tEnd)
            .withSemanticsProcessor(new CreateItemActionProcessor(gameModel));
    private Combinator statUpdate = new SequenceCombinator(
            new OrCombinator(tHealth, tStrength, tMana, tMoney),
            new OrCombinator(tPlus, tMinus),
            tIdentifier
    ).withSemanticsProcessor(new StatUpdateProcessor(gameModel));
    private Combinator playerStatsAction = new SequenceCombinator(tPlayerStats, new ListCombinator(statUpdate), tEnd)
            .withSemanticsProcessor(new PlayerStatsActionProcessor(gameModel));

    private Combinator action = new OrCombinator(clearCellAction, createNpcAction, createItemAction, playerStatsAction);
    private Combinator rule = new SequenceCombinator(tWhen, tIdentifier, tIdentifier, action, tEnd)
            .withSemanticsProcessor(new RuleProcessor(gameModel));

    private Combinator rulesList = new SequenceCombinator(tRules, new ListCombinator(rule), tEnd);


    private Combinator all = new SequenceCombinator(classesList, fieldDef, rulesList);

    public GameDefinitionParserCombinator(String buffer) {
        if (buffer == null) {
            throw new NullPointerException();
        }

        this.buffer = buffer;
        this.tokenBuffer = new Tokenizer(buffer).getTokens();
    }

    @Override
    public Game parse() throws ParsingException {
        ParsingStep result = all.stepOver(new ParsingStep(true, tokenBuffer));

        if (result.isOk()) {
            GameMap gameMap = new GameMap(gameModel.getWidth(), gameModel.getHeight());
            Player player = new Player(gameModel.getStartPoint());
            gameMap.addItem(player);
            for (MapItem item : gameModel.getItems()) {
                gameMap.addItem(item);
            }

            return new Game(gameMap, player, gameModel.getRules());
        } else {
            throw new ParsingException("Unable to parse. Error on line: " + result.getError().getLine() + ": " + result.getError().getMessage());
        }
    }
}
