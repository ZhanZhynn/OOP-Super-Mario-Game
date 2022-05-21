package game.ground;

/**
 * A class about the Warp Pipe
 *
 * @author Hee Zhan Zhynn (31989403)
 * @version 1
 */


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

    /**
     * A list of teleportActions that can be executed
     */
    private ActionList teleportActions = new ActionList();

    /**
     * Has the reset action been called?
     */
    private boolean reset = false;

    /**
     * Is this the start of the game?
     */
    private boolean start = true;

    /**
     * Location where the player will be teleported to
     */
    private Location moveToLocation;

    /**
     * Name of the location to teleport to
     */
    private String moveDirection;
//    private Location pipeLocation;

    /**
     * Constructor for warp pipe
     * @param moveToLocation Location to teleport to
     * @param direction Name of location
     */
    public WarpPipe(Location moveToLocation, String direction) {
        super('C');
        this.registerInstance();
        this.moveToLocation = moveToLocation;
        this.moveDirection = direction;
//        this.pipeLocation = pipeLocation;
    }

    /**
     * Can the actor enter this location?
     * @param actor Actor
     * @return actor can never enter
     */
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * Function for Player to jump.
     *
     * Jump is always succesful, no restriction
     * @param by is the actor
     * @param at is the location of where the player jumped over.
     * @return description when a actor jumped over the warp pipe
     */
    public String jumped(Actor by, Location at) {
        Actor actor = by;
        Location location = at;

        location.map().moveActor(actor, location);
        return actor + " jumped over " +  location +"(" + location.x() + ","+ location.y() + ")"  + " successfully.";
    }


    /**
     * We allow players to teleport when player is standing on top of warp pipe.
     *
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
            actions.add(new TeleportAction(this.moveToLocation, this.moveDirection));
        }

        return actions;
    }



    /**
     * handle all the behaviour of Warp Pipe as stated in assignment specification
     * @param location The location of the warp pipe
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
    }
    /**
     * name of class
     * @return name of the class
     */
    public String toString() {
        return "Warp Pipe";
    }

    @Override
    /**
     * Execute reset action
     */
    public void resetInstance() {
        reset = true;
    }
}
