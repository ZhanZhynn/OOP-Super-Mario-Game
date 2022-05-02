package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actor.Goomba;
import game.interfaces.Destroyable;
import game.interfaces.Jumpable;
import game.item.Coin;
import game.item.Status;

import java.util.List;

public class Sprout extends Tree implements Jumpable, Destroyable {
    private int counter;
    private boolean reset = false;

    public Sprout(){
        super('+');
        this.addCapability(Capabilities.SPAWN_GOOMBA);
        counter = 0;
        this.registerInstance();
    }



    public void tick(Location location){
        counter+=1;
        if (counter % 10 == 0){
            location.setGround(new Sapling());
        }
        if (!location.containsAnActor()) {
            if (this.hasCapability(Capabilities.SPAWN_GOOMBA)) {
                if (Math.random() <= 0.1)
                    location.addActor(new Goomba());
            }
        }
    }

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

    //    public ActionList allowableActions(Actor otherActor, Location location, String direction) {
//        ActionList actions = new ActionList();
//        if(otherActor instanceof Player) {
//            actions.add(new JumpAction(this, location, direction));
//        }
//        return actions;
//    }

}
