package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actor.Player;
import game.item.Status;

public class Lava extends Ground {

    private int damage = 15;

    /**
     * Constructor for Lava
     */
    public Lava() {
        super('L');
    }

    public boolean canActorEnter(Actor actor) {
        if(actor instanceof Player){ //only player can step on lava
            return true;
        }
        return false;
    }

    public void tick(Location location){
        if (location.containsAnActor()){ //cause 15 damage to player when stepped on it
            location.getActor().hurt(damage);
        }
    }
}
