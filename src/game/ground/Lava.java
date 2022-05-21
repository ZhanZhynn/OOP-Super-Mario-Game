package game.ground;

/**
 * A class about the Lava
 *
 * @author Hee Zhan Zhynn (31989403)
 * @version 1
 */

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actor.Player;
import game.item.Status;

public class Lava extends Ground {
    /**
     * Damage caused to actor when stepped on the lava
     */
    private int damage = 15;

    /**
     * Constructor for Lava
     */
    public Lava() {
        super('L');
    }

    /**
     * Can the actor enter this location?
     * @param actor Actor
     * @return only player can enter (step on lava)
     */
    public boolean canActorEnter(Actor actor) {
        if(actor instanceof Player){ //only player can step on lava
            return true;
        }
        return false;
    }

    /**
     * Lava deals damage to player when player steps on it
     * @param location The location of the lava
     */
    public void tick(Location location){
        if (location.containsAnActor()){ //cause 15 damage to player when stepped on it
            location.getActor().hurt(damage);
        }
    }

    /**
     * name of class
     * @return name of the class
     */
    public String toString() {
        return "Lava";
    }
}
