package game.item;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actor.Player;
import game.interfaces.Consumable;

/**
 * @author Ng Zu Shen
 * healing water from healing fountain
 */
public class HealWaterZS implements Consumable {

    /**
     * is the water consumed
     */
    public Boolean isConsumed = false;

    /**
     * what happen after consume
     * @param actor
     * @param map
     */
    @Override
    public void consume(Actor actor, GameMap map) {
        actor.heal(50);
        isConsumed = true;
    }

    /**
     * getter of isConsumed
     * @return
     */
    @Override
    public boolean getIsConsumed() {
        return isConsumed;
    }

    /**
     * to string method
     * @return
     */
    @Override
    public String toString() {
        return "Heal Water";
    }
}
