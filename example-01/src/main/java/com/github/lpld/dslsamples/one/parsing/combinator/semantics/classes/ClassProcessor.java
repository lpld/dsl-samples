package com.github.lpld.dslsamples.one.parsing.combinator.semantics.classes;

import com.github.lpld.dslsamples.one.model.npc.NpcClass;
import com.github.lpld.dslsamples.one.parsing.combinator.core.ParsingStep;
import com.github.lpld.dslsamples.one.parsing.combinator.core.SemanticsProcessor;
import com.github.lpld.dslsamples.one.parsing.combinator.semantics.GameModel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.Validate;

import java.util.List;

/**
 * @author leopold
 * @since 2/20/14
 */
@RequiredArgsConstructor
public class ClassProcessor implements SemanticsProcessor {

    private final GameModel gameModel;

    @Override
    public void handleParsingResult(List<ParsingStep> steps) {
        Validate.notEmpty(steps);
        Validate.isTrue(steps.size() == 3, "Expected to be 3, but was ", steps.size());

        String className = steps.get(0).getMatchToken().getValue();
        NpcClass npcClass = new NpcClass(className);
        npcClass.setInitialHealth(gameModel.getCurrentHealth());
        npcClass.setMana(gameModel.getCurrentMana());
        npcClass.setStrength(gameModel.getCurrentStrengh());
        npcClass.setAttitude(gameModel.getCurrentAttitude());
        gameModel.getNpcClasses().add(npcClass);
    }
}
