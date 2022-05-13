package game.ground;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.interfaces.Destroyable;
import game.interfaces.Jumpable;
import game.interfaces.Resettable;
import game.item.Coin;
import game.item.FireFlower;
import game.item.Status;

public class Sapling extends Tree implements Jumpable, Destroyable{
    private int counter;

    /**
     * Constructor for Sapling class.
     */
    public Sapling(){
        super('t');
        this.addCapability(Capabilities.SPAWN_COIN);
        this.addCapability(Capabilities.SPAWN_FIREFLOWER);
        counter = 0;
    }
    /**
     * Acts as a counter for tree to grow into Mature after 10 turns.
     * It has 10% chance of spawning a coin at each turn.
     * Param : Location
     */

    public void tick(Location location) {


        //grow into mature after 10 turns
        counter += 1;
        if (counter  == 10) {
//            location.addActor(new Bug());
            location.setGround(new Mature());
        }

        if (!(location.getItems().size()> 0) && this.hasCapability(Capabilities.SPAWN_FIREFLOWER) && Math.random()<=0.5){ //if got item then cannot spawn new flower
            location.addItem(new FireFlower());
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

    /**
     * Function for Mario to jump.
     * There is a 80% success rate, if fail, there will be a 20 damage incurred.
     * If jump is successful, a message will be printed to prompt the player, if fail, another message will be printed.
     * @param by is the actor
     * @param at is the location of where the player jumped over.
     */

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
