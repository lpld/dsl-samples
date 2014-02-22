package com.github.lpld.dslsamples.one.parsing.combinator.semantics.field;

import com.github.lpld.dslsamples.one.model.map.Location;
import com.github.lpld.dslsamples.one.model.npc.Npc;
import com.github.lpld.dslsamples.one.model.npc.NpcClass;
import com.github.lpld.dslsamples.one.parsing.combinator.core.ParsingStep;
import com.github.lpld.dslsamples.one.parsing.combinator.core.SemanticsProcessor;
import com.github.lpld.dslsamples.one.parsing.combinator.semantics.GameModel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.Validate;

import java.util.List;

/**
 * @author leopold
 * @since 2/22/14
 */
@RequiredArgsConstructor
public class NpcProcessor implements SemanticsProcessor {
    private final GameModel gameModel;

    @Override
    public void handleParsingResult(List<ParsingStep> steps) {
        Validate.notEmpty(steps);
        Validate.isTrue(steps.size() == 3, "Expected 3 but was ", steps.size());

        String npcName = steps.get(0).getMatchToken().getValue();
        String npcClass = steps.get(1).getMatchToken().getValue();
        Location npcLocation = gameModel.getCurrentLocation();

        gameModel.setCurrentNpc(new Npc(npcName, findClass(npcClass), npcLocation));
    }

    private NpcClass findClass(String className) {
        for (NpcClass npcClass : gameModel.getNpcClasses()) {
            if (npcClass.getName().equals(className)) {
                return npcClass;
            }
        }
        return null;
    }
}
