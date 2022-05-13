package game.item;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actor.Player;
import game.interfaces.Consumable;

public class HealWaterZS implements Consumable {
    public Boolean isConsumed = false;

    @Override
    public void consume(Actor actor, GameMap map) {
        Player player = (Player) actor;
        player.heal(50);
        isConsumed = true;
    }

    @Override
    public boolean getIsConsumed() {
        return isConsumed;
    }

    @Override
    public String toString() {
        return "Heal Water";
    }
}
