package com.github.lpld.dslsamples.one.model.rules;

import com.github.lpld.dslsamples.one.model.Game;
import com.github.lpld.dslsamples.one.model.Player;
import com.github.lpld.dslsamples.one.model.map.GameMap;
import lombok.*;

/**
 * Action that updates player parameters.
 *
 * @author leopold
 * @since 12/29/13
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
@EqualsAndHashCode
public class UpdatePlayerStatsAction implements Action {
    private int deltaHealth;
    private int deltaStrength;
    private int deltaMana;
    private int deltaMoney;

    @Override
    public void execute(Game game) {
        Player player = game.getPlayer();

        player.setHealth(player.getHealth() + deltaHealth);
        player.setStrength(player.getStrength() + deltaStrength);
        player.setMana(player.getMana() + deltaMana);
        player.setMoney(player.getMoney() + deltaMoney);
    }
}
