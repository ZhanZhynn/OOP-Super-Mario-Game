package game.item;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.CanDrinkFountain;
import game.interfaces.Consumable;

/**
 * @author Ng Zu Shen
 * power water from power fountain
 */
public class PowerWater implements Consumable{

    /**
     * is the water consumed?
     */
    public Boolean isConsumed = false;

    /**
     * buff the actor that drink this.
     * @param actor
     * @param map
     */
    @Override
    public void consume(Actor actor, GameMap map) {
        CanDrinkFountain actorr = (CanDrinkFountain) actor;
        actorr.incrementPowerBuff();
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
        return "Power Water";
    }
}
