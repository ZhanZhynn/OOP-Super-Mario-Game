package game;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import java.util.List;
import java.util.Random;

public class Sapling extends Tree implements Jumpable, Destroyable, Resettable{
    private int counter;
    private boolean reset = false;

    public Sapling(){
        super('t');
        this.addCapability(Capabilities.SPAWN_COIN);
        counter = 0;
        this.registerInstance();
    }

    public void resetInstance(){//remove all coins
        this.reset = true;
    }

    public void tick(Location location) {


        //grow into mature after 10 turns
        counter += 1;
        if (counter  == 10) {
//            location.addActor(new Bug());
            location.setGround(new Mature());
        }

        if (this.hasCapability(Capabilities.SPAWN_COIN)) {
            //10% chance to spawn coin
            if (Math.random() <= 0.1) { //if already got coin, can spawn again?
                location.addItem(new Coin(20));
            }
        }

//        if (this.reset){
//            List<Item> items = location.getItems();
//            if (items.size() > 0) {
//                for (Item item : items) {
//                    if (item instanceof Coin) {
//                        location.removeItem(item);
//                    }
//                }
//            }
//        }
//        this.reset = false;
    }

    public String jumped(Actor by, Location at) {
        Actor actor = by;
        Location location = at;
        if(Math.random() <= 0.8) {
            location.map().moveActor(actor, location);
            return actor + " jumped over " +  location +"(" + location.x() + ","+ location.y() + ")"  + " successfully.";
        }
        else {
            int damage = 20;
            actor.hurt(damage);
            return actor + " fell from Sapling. Received " + damage + " damage.";
        }
    }

    public boolean canActorEnter(Actor actor) {
        if(actor.hasCapability(Status.DESTROY_HIGH_GROUND)){
            return true;
        }
        return false;
    }


//    public ActionList allowableActions(Actor otherActor, Location location, String direction) {
//        ActionList actions = new ActionList();
//        if(otherActor instanceof Player) {
//            actions.add(new JumpAction(this, location, direction));
//        }
//        return actions;
//    }

    public String toString() {
        return "Sapling";
    }

}
