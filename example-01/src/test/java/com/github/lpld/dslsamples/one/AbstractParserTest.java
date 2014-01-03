package com.github.lpld.dslsamples.one;

import com.github.lpld.dslsamples.one.model.Game;
import com.github.lpld.dslsamples.one.model.Player;
import com.github.lpld.dslsamples.one.model.events.EventType;
import com.github.lpld.dslsamples.one.model.items.Activator;
import com.github.lpld.dslsamples.one.model.items.Area;
import com.github.lpld.dslsamples.one.model.items.Pickable;
import com.github.lpld.dslsamples.one.model.map.GameMap;
import com.github.lpld.dslsamples.one.model.map.Location;
import com.github.lpld.dslsamples.one.model.map.Wall;
import com.github.lpld.dslsamples.one.model.npc.Attitude;
import com.github.lpld.dslsamples.one.model.npc.Npc;
import com.github.lpld.dslsamples.one.model.npc.NpcClass;
import com.github.lpld.dslsamples.one.model.rules.ClearCellAction;
import com.github.lpld.dslsamples.one.model.rules.CreateItemAction;
import com.github.lpld.dslsamples.one.model.rules.UpdatePlayerStatsAction;
import com.github.lpld.dslsamples.one.parsing.ConfigParser;
import com.github.lpld.dslsamples.one.parsing.ParsingException;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author leopold
 * @since game_definition/2/14
 */
public abstract class AbstractParserTest {

    protected abstract ConfigParser createParser(String config);

    @Test
    public void testParser() throws IOException, ParsingException {
        ConfigParser parser = createParser(readFileAsString("game_definition"));

        Game parsedGame = parser.parse();
        Game filledGame = fillGameModel();

        assertGamesEqual(filledGame, parsedGame);
    }

    private void assertGamesEqual(Game gameOne, Game gameTwo) {
        assertEquals(gameOne.getRules(), gameTwo.getRules());
        assertEquals(gameOne.getPlayer(), gameTwo.getPlayer());
        assertArrayEquals(gameOne.getGameMap().getField(), gameTwo.getGameMap().getField());
    }

    private Game fillGameModel() {
        NpcClass warriorClass = new NpcClass("Warrior");
        warriorClass.setAttitude(Attitude.ENEMY);
        warriorClass.setInitialHealth(100);
        warriorClass.setMana(30);
        warriorClass.setStrength(100);

        NpcClass magicianClass = new NpcClass("Magician");
        magicianClass.setAttitude(Attitude.ENEMY);
        magicianClass.setInitialHealth(100);
        magicianClass.setMana(100);
        magicianClass.setStrength(30);

        NpcClass traderClass = new NpcClass("Trader");
        traderClass.setAttitude(Attitude.NEUTRAL);
        traderClass.setInitialHealth(30);
        traderClass.setMana(10);
        traderClass.setStrength(10);

        GameMap map = new GameMap(20, 20);
        Player player = new Player(new Location(1, 10));
        map.addItem(player);

        map.addItems(
                new Npc("w1", warriorClass, new Location(5, 17)),
                new Npc("w2", warriorClass, new Location(2, 8)),
                new Npc("m1", magicianClass, new Location(7, 6)),
                new Npc("t1", traderClass, new Location(17, 11)),

                new Activator("doorOpener", new Location(11, 12)),
                new Pickable("potion", new Location(7, 13)),
                new Area("lava", new Location(19, 7)),

                new Wall(new Location(1, 1)),
                new Wall(new Location(2, 1)),
                new Wall(new Location(3, 1)),
                new Wall(new Location(3, 2)),
                new Wall(new Location(3, 3)),
                new Wall(new Location(3, 4))
        );

        Game game = new Game(map, player);

        game.addRule(
                "doorOpener", EventType.ACTIVATOR_TRIGGERED,
                new ClearCellAction(new Location(3, 4))
        );

        game.addRule(
                "m1", EventType.NPC_DEAD,
                new CreateItemAction(new Npc("m2", magicianClass, new Location(7, 6)))
        );

        game.addRule(
                "w1", EventType.NPC_DEAD,
                new CreateItemAction(new Pickable("gem", new Location(5, 17)))
        );

        game.addRule(
                "potion", EventType.ITEM_PICKED,
                new UpdatePlayerStatsAction(30, 10, 20, 0)
        );

        game.addRule(
                "gem", EventType.ITEM_PICKED,
                new UpdatePlayerStatsAction(0, 0, 0, 100)
        );

        game.addRule(
                "lava", EventType.AREA_VISITED,
                new UpdatePlayerStatsAction(-20, 0, 0, 0)
        );

        return game;
    }

    private String readFileAsString(String filePath) throws IOException {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }


}
