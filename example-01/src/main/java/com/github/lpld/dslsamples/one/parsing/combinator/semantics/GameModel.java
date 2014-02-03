package com.github.lpld.dslsamples.one.parsing.combinator.semantics;

import com.github.lpld.dslsamples.one.model.map.Location;
import com.github.lpld.dslsamples.one.model.map.MapItem;
import com.github.lpld.dslsamples.one.model.npc.Npc;
import com.github.lpld.dslsamples.one.model.npc.NpcClass;
import com.github.lpld.dslsamples.one.model.rules.Action;
import com.github.lpld.dslsamples.one.model.rules.Rule;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author leopold
 * @since 2/3/14
 */
@Data
public class GameModel {
    // currently processing
    private NpcClass currentClass;
    private Npc currentNpc;
    private MapItem currentItem;
    private Location currentLocation;
    private int currentInt;
    private String currentString;
    private Action currentAction;

    // actual model
    private List<NpcClass> npcClasses = new ArrayList<>();
    private List<MapItem> items = new ArrayList<>();
    private Map<Rule, Action> rules = new HashMap<>();
    private int width;
    private int height;
    private Location startPoint;
}
