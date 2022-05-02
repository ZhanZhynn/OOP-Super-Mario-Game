package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actor.Koopa;
import game.interfaces.Destroyable;
import game.interfaces.Jumpable;
import game.interfaces.Resettable;
import game.item.Status;

import java.util.ArrayList;
import java.util.Random;

public class Mature extends Tree implements Jumpable, Destroyable{
    private int counter;

    public Mature(){
        super('T');
        this.addCapability(Capabilities.SPAWN_KOOPA);
        this.addCapability(Capabilities.GROW_SPROUT);
        this.registerInstance();
        counter = 0;
    }
    /**
     * Acts as a counter for tree to grow into Mature after 10 turns.
     * If location does not contains an actor, it has 15% chance of spawning a koopa at each turn.
     * Also implements a new Sprout every 5 turns.
     * Param : Location
     */
    public void tick(Location location) {

        //grow into mature after 10 turns
        counter += 1;
        double chance = Math.random();
//        if (counter == 10) {
////            location.addActor(new Bug());
//            location.setGround(new Mature());
//        }

        if (!location.containsAnActor()) {
            if (this.hasCapability(Capabilities.SPAWN_KOOPA)) {
                //15% chance to spawn Koopa if actor is not standing on it
                if (Math.random() <= 0.15)
                    location.addActor(new Koopa());
            }
        }


        if (this.hasCapability(Capabilities.GROW_SPROUT) && this.counter % 5 == 0) {
            //got a new sprout every 5 turns at dirt ground
            addDirtNeighbour(location);
            ArrayList<Location> dirt = super.getDirt();
            if (dirt.size() != 0) {
                Random ran = new Random();
                int randomNumber = ran.nextInt(dirt.size());
                Location grow = dirt.get(randomNumber);
                grow.setGround(new Sprout()); //need to change this to sprout
            }
        }

        if (chance <= 0.2) {
            //20% chance to wither and turn into dirt
            location.setGround(new Dirt());
        }
    }
    /**
     * Function for Mario to jump.
     * There is a 70% success rate, if fail, there will be a 30 damage incurred.
     * If jump is successful, a message will be printed to prompt the player, if fail, another message will be printed.
     * *@param location of the tree, also used to get the map
     */

    public String jumped(Actor by, Location at) {
        Actor actor = by;
        Location location = at;
        if(Math.random() <= 0.7) {
            location.map().moveActor(actor, location);
            return actor + " jumped over " +  location +"(" + location.x() + ","+ location.y() + ")"  + " successfully.";
        }
        else {
            int damage = 30;
            actor.hurt(damage);
            return actor + " fell from Mature. Received " + damage + " damage.";
        }
    }

//    public ActionList allowableActions(Actor otherActor, Location location, String direction) {
//        ActionList actions = new ActionList();
//        if(otherActor instanceof Player) {
//            actions.add(new JumpAction(this, location, direction));
//        }
//        return actions;
//    }

    public String toString() {
        return "Mature";
    }

}
