package game.action;

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
     * A Jumpable object
     */
    private Location oldLocation;
    private GameMap map;
    private Location newLocation;


    public TeleportAction(Location moveToLocation, String direction){
        super(moveToLocation, direction);
        newLocation = moveToLocation;
    }
    /**
     * actor moves to jumpable location after a successful jump
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
//        if (actor instanceof Player) {
//            ((Player) actor).setOriLocation(map.locationOf(actor));
//        }
        Location currentLocation = map.locationOf(actor);

        Location lastLocation = ((Player) actor).getLastTeleportLocation();
//        map.moveActor(actor, moveToLocation);
        map.moveActor(actor, lastLocation);

        ((Player) actor).setLastTeleportLocation(currentLocation);
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
