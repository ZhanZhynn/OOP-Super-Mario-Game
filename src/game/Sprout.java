package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

public class Sprout extends Tree implements Jumpable, Destroyable{
    private int counter;

    public Sprout(){
        super('+');
        this.addCapability(Capabilities.SPAWN_GOOMBA);
        counter = 0;
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

    @Override
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

}
