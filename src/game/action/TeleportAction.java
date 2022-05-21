package game.action;

/**
 * A class the teleport action
 *
 * @author Hee Zhan Zhynn (31989403)
 * @version 1
 */
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actor.Player;
import game.interfaces.Jumpable;
import game.item.Status;

public class TeleportAction extends MoveActorAction {
    /**
     * The last location before player teleports
     */
    private Location oldLocation;

    /**
     * GameMap
     */
    private GameMap map;

    /**
     * Location the player teleported to
     */
    private Location newLocation;

    /**
     * Is the player at lava zone?
     */
    private Boolean lavaZone = false;

    /**
     * Constructor for teleport action
     * @param moveToLocation Location to teleport to
     * @param direction Name of the location
     */
    public TeleportAction(Location moveToLocation, String direction){
        super(moveToLocation, direction);
        newLocation = moveToLocation;
    }

    /**
     * actor executing the teleport action
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return description of what happened as a String
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        //kill any actor who is on the other side of teleport
        if (moveToLocation.containsAnActor()) {
            moveToLocation.map().removeActor(moveToLocation.getActor());
        }

        if (!this.lavaZone){
            ((Player)actor).setLastLocation(map.locationOf(actor)); //save previous location before teleporting
        }

        map.moveActor(actor, moveToLocation);
        this.lavaZone = !(this.lavaZone);
        ((Player)actor).setAtLavaZone(this.lavaZone);
        return actor + " teleported " + " successfully.";
    }

    /**
     * description of what this action does
     * @param actor The actor performing the action.
     * @return result as a String
     */
    @Override
    public String menuDescription(Actor actor) {
        String res= actor + " travel " + direction;
        return res;
    }

}
