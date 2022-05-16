package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actor.Goomba;
import game.interfaces.Destroyable;
import game.interfaces.Jumpable;
import game.item.Coin;
import game.item.FireFlower;
import game.item.Status;

import java.util.List;

public class Sprout extends Tree implements Jumpable, Destroyable {
    private int counter;
    private boolean reset = false;

    /**
     * Constructor for class Sprout
     */
    public Sprout(){
        super('+');
        this.addCapability(Capabilities.SPAWN_GOOMBA);
        this.addCapability(Capabilities.SPAWN_FIREFLOWER);
        counter = 0;
        this.registerInstance();
    }

    /**
     * Acts as a counter for tree to grow into Mature after 10 turns.
     * If location does not contains an actor, it has 10% chance of spawning a Goomba at each turn.
     * @param location is the location of Sapling
     */
    public void tick(Location location){
        counter+=1;
        if (counter % 10 == 0){
            location.setGround(new Sapling());
            if (!(location.getItems().size()> 0) && this.hasCapability(Capabilities.SPAWN_FIREFLOWER) && Math.random()<=0.5){ //if got item then cannot spawn new flower
                location.addItem(new FireFlower());
            }
        }

        if (!location.containsAnActor()) {
            if (this.hasCapability(Capabilities.SPAWN_GOOMBA)) {
                if (Math.random() <= 0.1)
                    location.addActor(new Goomba());
            }
        }
    }

    /**
     * Function for Mario to jump.
     * There is a 90% success rate, if fail, there will be a 10 damage incurred.
     * If jump is successful, a message will be printed to prompt the player, if fail, another message will be printed.
     * @param by is the actor
     * @param at is the location of where the player jumped over.
     */
    public String jumped(Actor by, Location at) {
        Actor actor = by;
        Location location = at;
        if(Math.random() <= 0.9) {
            location.map().moveActor(actor, location);
            return actor + " jumped over " +  location.getGround().getClass().getSimpleName() +"(" + location.x() + ","+ location.y() + ")"  + " successfully.";
        }
        else {
            int damage = 10;
            actor.hurt(damage);
            return actor + " fell from Sprout. Received " + damage + " damage.";
        }



    }
    public String toString() {
        return "Sprout";
    }

    //    public ActionList allowableActions(Actor otherActor, Location location, String direction) {
//        ActionList actions = new ActionList();
//        if(otherActor instanceof Player) {
//            actions.add(new JumpAction(this, location, direction));
//        }
//        return actions;
//    }

}
