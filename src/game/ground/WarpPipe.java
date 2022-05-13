package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.action.JumpAction;
import game.actor.Player;
import game.interfaces.Jumpable;
import game.item.Status;

public class WarpPipe extends Ground implements Jumpable {

    public WarpPipe() {
        super('C');
    }

    public boolean canActorEnter(Actor actor) {
        return false;
    }

    //added by Hee Zhan Zhynn on 28/4/2022----------------
    public String jumped(Actor by, Location at) {
        Actor actor = by;
        Location location = at;

        location.map().moveActor(actor, location);
        return actor + " jumped over " +  location +"(" + location.x() + ","+ location.y() + ")"  + " successfully.";
    }

    /**
     * At the moment, we only make it can be attacked by Player.
     * You can do something else with this method.
     * @param otherActor the Actor that might perform an action.
     * @param location location of wall
     * @param direction  String representing the direction of the other Actor
     * @return list of actions
     */

    public ActionList allowableActions(Actor otherActor, Location location, String direction) {
        ActionList actions = new ActionList();
        if(otherActor instanceof Player && !location.containsAnActor()) {
            actions.add(new JumpAction(this, location, direction));
        } else if (otherActor instanceof Player && location.containsAnActor()){ //player on pipe, can teleport now
            actions.add(new TeleportAction(this, location, direction));
        }
        return actions;
    }

    /**
     * handle all the behaviour of trees as stated in assignment specification
     * @param location The location of the wall
     */
    public void tick(Location location){

    }

    public String toString() {
        return "Warp Pipe";
    }


}
