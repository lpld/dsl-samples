package com.github.lpld.dslsamples.one.parsing.recursive;

import com.github.lpld.dslsamples.one.lexing.Token;
import com.github.lpld.dslsamples.one.lexing.Tokenizer;
import com.github.lpld.dslsamples.one.model.Game;
import com.github.lpld.dslsamples.one.model.Player;
import com.github.lpld.dslsamples.one.model.map.GameMap;
import com.github.lpld.dslsamples.one.model.map.Wall;
import com.github.lpld.dslsamples.one.lexing.TokenType;
import com.github.lpld.dslsamples.one.model.events.EventType;
import com.github.lpld.dslsamples.one.model.items.Activator;
import com.github.lpld.dslsamples.one.model.items.Area;
import com.github.lpld.dslsamples.one.model.items.Pickable;
import com.github.lpld.dslsamples.one.model.map.Location;
import com.github.lpld.dslsamples.one.model.map.MapItem;
import com.github.lpld.dslsamples.one.model.npc.Attitude;
import com.github.lpld.dslsamples.one.model.npc.Npc;
import com.github.lpld.dslsamples.one.model.npc.NpcClass;
import com.github.lpld.dslsamples.one.model.rules.*;
import com.github.lpld.dslsamples.one.parsing.ConfigParser;
import com.github.lpld.dslsamples.one.parsing.ParsingException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A recursive descent parser for
 *
 * @author leopold
 * @since 12/30/13
 */
public class ConfigRecursiveParser implements ConfigParser {
    private String buffer;
    private final Tokenizer tokenizer;

    // TEMP
    private NpcClass npcClass;
    private Npc npc;
    private MapItem item;
    private Location location;
    private int integer;
    private String string;
    private Action ruleAction;

    // MODEL
    private List<NpcClass> npcClasses = new ArrayList<>();
    private List<MapItem> items = new ArrayList<>();
    private Map<Rule, Action> rules = new HashMap<>();
    private int width;
    private int height;
    private Location startPoint;

    public ConfigRecursiveParser(String buffer) {
        if (buffer == null) {
            throw new NullPointerException();
        }

        this.buffer = buffer;
        this.tokenizer = new Tokenizer(buffer);
    }

    @Override
    public Game parse() throws ParsingException {
        boolean result = doParse();

        if (result) {
            GameMap gameMap = new GameMap(width, height);
            Player player = new Player(startPoint);
            gameMap.addItem(player);
            for (MapItem item : items) {
                gameMap.addItem(item);
            }

            return new Game(gameMap, player, rules);
        } else {
            // TODO provide more informative errors
            throw new ParsingException("Unable to parse");
        }

    }

    private boolean doParse() {
        return classes() && field() && rules();
    }

    private boolean classes() {
        if (tokenizer.popToken().getType() != TokenType.CLASSES) {
            return false;
        }

        while (tokenizer.nextToken().getType() != TokenType.END) {
            if (!loadClass()) {
                return false;
            }
        }

        tokenizer.popToken(); // END
        return true;
    }

    private boolean loadClass() {
        if (!string()) return false;

        npcClass = new NpcClass(string);

        while (tokenizer.nextToken().getType() != TokenType.END) {
            boolean result;
            switch (tokenizer.popToken().getType()) {
                case ATTITUDE:
                    result = attitude();
                    break;
                case HEALTH:
                    result = health();
                    break;
                case STRENGTH:
                    result = strength();
                    break;
                case MANA:
                    result = mana();
                    break;
                default:
                    result = false;
            }

            if (!result) {
                return false;
            }
        }

        npcClasses.add(npcClass);
        tokenizer.popToken(); // END
        return true;
    }

    private boolean mana() {
        if (!integer()) return false;
        npcClass.setMana(integer);
        return true;
    }

    private boolean strength() {
        if (!integer()) return false;
        npcClass.setStrength(integer);
        return true;
    }

    private boolean health() {
        if (!integer()) return false;
        npcClass.setInitialHealth(integer);
        return true;
    }

    private boolean attitude() {
        if (!string()) return false;
        Attitude attitude = Attitude.valueOf(string.toUpperCase());  // can throw illegal arg.
        npcClass.setAttitude(attitude);
        return true;

    }

    private boolean field() {
        Token token = tokenizer.popToken();
        if (token.getType() != TokenType.FIELD) {
            return false;
        }

        while (tokenizer.nextToken().getType() != TokenType.END) {
            boolean result;
            switch (tokenizer.popToken().getType()) {
                case WIDTH:
                    result = width();
                    break;
                case HEIGHT:
                    result = height();
                    break;
                case START_POINT:
                    result = startPoint();
                    break;
                case NPCS:
                    result = npcs();
                    break;
                case ITEMS:
                    result = items();
                    break;
                case WALLS:
                    result = walls();
                    break;
                default:
                    result = false;
            }

            if (!result) {
                return false;
            }
        }

        tokenizer.popToken(); // END
        return true;
    }

    private boolean items() {
        while (tokenizer.nextToken().getType() != TokenType.END) {
            if (!item()) return false;
            items.add(item);
        }

        tokenizer.popToken(); // END
        return true;
    }

    private boolean item() {
        if (!string()) return false;
        String itemId = string;

        if (!string()) return false;
        String itemType = string;

        if (!location()) return false;
        Location itemLocation = location;

        if ("activator".equals(itemType)) {
            item = new Activator(itemId, itemLocation);
        } else if ("pickable".equals(itemType)) {
            item = new Pickable(itemId, itemLocation);
        } else if ("area".equals(itemType)) {
            item = new Area(itemId, itemLocation);
        }
        return true;
    }

    private boolean npcs() {
        while (tokenizer.nextToken().getType() != TokenType.END) {
            if (!npc()) return false;
            items.add(npc);
        }

        tokenizer.popToken(); // END
        return true;
    }

    private boolean npc() {
        if (!string()) return false;
        String npcId = string;

        if (!string()) return false;
        String npcClassName = string;

        if (!location()) return false;
        Location npcLocation = location;

        npc = new Npc(npcId, findClass(npcClassName), npcLocation);
        return true;
    }


    private boolean width() {
        if (!integer()) return false;
        width = integer;
        return true;
    }

    private boolean height() {
        if (!integer()) return false;
        height = integer;
        return true;
    }

    private boolean startPoint() {
        if (!location()) return false;
        startPoint = location;
        return true;
    }

    private boolean location() {
        if (tokenizer.popToken().getType() != TokenType.LEFT_PAR) {
            return false;
        }

        if (!integer()) return false;
        int x = integer;

        if (!integer()) return false;
        int y = integer;

        location = new Location(x, y);

        if (tokenizer.popToken().getType() != TokenType.RIGHT_PAR) {
            return false;
        }
        return true;
    }

    public boolean walls() {
        while (tokenizer.nextToken().getType() != TokenType.END) {
            if (!location()) return false;
            items.add(new Wall(location));
        }

        tokenizer.popToken(); // END
        return true;
    }

    public boolean rules() {
        if (tokenizer.popToken().getType() != TokenType.RULES) {
            return false;
        }

        while (tokenizer.nextToken().getType() != TokenType.END) {
            if (!rule()) return false;
        }

        tokenizer.popToken(); // END
        return true;
    }

    private boolean rule() {
        if (tokenizer.popToken().getType() != TokenType.WHEN) {
            return false;
        }

        if (!string()) return false;
        String itemId = string;

        if (!string()) return false;
        EventType eventType;
        if ("dead".equals(string)) {
            eventType = EventType.NPC_DEAD;
        } else if ("activated".equals(string)) {
            eventType = EventType.ACTIVATOR_TRIGGERED;
        } else if ("picked".equals(string)) {
            eventType = EventType.ITEM_PICKED;
        } else if ("visited".equals(string)) {
            eventType = EventType.AREA_VISITED;
        } else {
            return false;
        }

        Rule rule = new Rule(itemId, eventType);
        if (!ruleAction()) return false;
        rules.put(rule, ruleAction);

        if (tokenizer.popToken().getType() != TokenType.END) {
            return false;
        }
        return true;
    }

    private boolean ruleAction() {
        boolean result;
        switch (tokenizer.popToken().getType()) {
            case CLEAR:
                result = clearAction();
                break;
            case CREATE:
                result = createAction();
                break;
            case PLAYER_STATS:
                result = playerStatsAction();
                break;
            default:
                result = false;
        }

        if (!result) {
            return false;
        }
        return true;
    }

    private boolean clearAction() {
        if (!location()) return false;
        ruleAction = new ClearCellAction(location);
        return true;
    }

    private boolean createAction() {
        boolean result;
        switch (tokenizer.popToken().getType()) {
            case NPC:
                result = npc();
                ruleAction = new CreateItemAction(npc);
                break;
            case ITEM:
                result = item();
                ruleAction = new CreateItemAction(item);
                break;
            default:
                result = false;
        }
        if (!result || tokenizer.popToken().getType() != TokenType.END) {
            return false;
        }

        return true;
    }

    private boolean playerStatsAction() {
        UpdatePlayerStatsAction updatePlayerStatsAction = new UpdatePlayerStatsAction();

        while (tokenizer.nextToken().getType() != TokenType.END) {
            boolean result;

            Token paramToken = tokenizer.popToken();
            Token signToken = tokenizer.popToken();
            if (!integer()) return false;
            int delta = integer;

            switch (signToken.getType()) {
                case PLUS:
                    break;
                case MINUS:
                    delta = -integer;
                    break;
                default:
                    return false;
            }

            switch (paramToken.getType()) {
                case HEALTH:
                    updatePlayerStatsAction.setDeltaHealth(delta);
                    break;
                case STRENGTH:
                    updatePlayerStatsAction.setDeltaStrength(delta);
                    break;
                case MANA:
                    updatePlayerStatsAction.setDeltaMana(delta);
                    break;
                case MONEY:
                    updatePlayerStatsAction.setDeltaMoney(delta);
                    break;
                default:
                    return false;
            }

        }
        ruleAction = updatePlayerStatsAction;
        tokenizer.popToken(); // END
        return true;
    }


    private boolean integer() {
        Token integerToken = tokenizer.popToken();
        if (integerToken.getType() != TokenType.IDENTIFIER) {
            return false;
        }

        integer = Integer.parseInt(integerToken.getValue());
        return true;
    }

    private boolean string() {
        Token stringToken = tokenizer.popToken();
        if (stringToken.getType() != TokenType.IDENTIFIER) {
            return false;
        }

        string = stringToken.getValue();
        return true;
    }

    private NpcClass findClass(String className) {
        for (NpcClass npcClass : npcClasses) {
            if (npcClass.getName().equals(className)) {
                return npcClass;
            }
        }
        return null;
    }

}
