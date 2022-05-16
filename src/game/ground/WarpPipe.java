package game.ground;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.action.JumpAction;
import game.action.TeleportAction;
import game.actor.PiranhaPlant;
import game.actor.Player;
import game.interfaces.Jumpable;
import game.interfaces.Resettable;
import game.item.Status;

public class WarpPipe extends Ground implements Jumpable, Resettable {

    private ActionList teleportActions = new ActionList();
    private boolean reset = false;
    private boolean start = true;
    private Location moveToLocation;
    private String moveDirection;
//    private Location pipeLocation;

    public WarpPipe(Location moveToLocation, String direction) {
        super('C');
        this.registerInstance();
        this.moveToLocation = moveToLocation;
        this.moveDirection = direction;
//        this.pipeLocation = pipeLocation;
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

        if (otherActor instanceof Player && ((Player) otherActor).getAtLavaZone()){
            this.moveToLocation = ((Player) otherActor).getLastLocation();
//            System.out.println(((Player) otherActor).getLastLocation());
        }

        if(otherActor instanceof Player && !location.containsAnActor()) {
            actions.add(new JumpAction(this, location, direction));
        }
        else if (otherActor instanceof Player && location.containsAnActor()){ //player on pipe, can teleport now
//            actions.add(new TeleportAction(location, direction));
//            for (Action i:teleportActions){
//                actions.add(i);
//            }
            actions.add(new TeleportAction(this.moveToLocation, this.moveDirection));
        }


        return actions;
    }

    //	pipe.allowableActions(mario, lavaMap.at(0,0), " to Lava Zone!!");

//    public void addTeleportActions(Player otherActor, Location location, String direction){
//        teleportActions.add(new TeleportAction(location, direction));
//    }


    /**
     * handle all the behaviour of trees as stated in assignment specification
     * @param location The location of the wall
     */
    public void tick(Location location){
        //added by zushen on 13/5/2022
        if (start) {
            location.addActor(new PiranhaPlant());
            start = false;
        }

        if (reset){
            reset = false;
            if (!location.containsAnActor()) {
                location.addActor(new PiranhaPlant());
            }
        }

        System.out.println(location + "," + location.getActor());
    }

    public String toString() {
        return "Warp Pipe";
    }

    @Override
    public void resetInstance() {
        reset = true;
    }
}
