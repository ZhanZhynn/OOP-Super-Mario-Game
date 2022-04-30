package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;
import java.util.Random;

public class Mature extends Tree implements Jumpable, Destroyable {
    private int counter;

    public Mature(){
        super('T');
        this.addCapability(Capabilities.SPAWN_KOOPA);
        this.addCapability(Capabilities.GROW_SPROUT);
        counter = 0;
    }

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
        return "Mature";
    }
}
