package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.interfaces.Jumpable;
import game.item.Status;

public class TeleportAction extends MoveActorAction {
    /**
     * A Jumpable object
     */
    private Location oldLocation;
    private GameMap map;


    public TeleportAction(Location moveToLocation, String direction){
        super(moveToLocation, direction);
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
        map.moveActor(actor, moveToLocation);
        return actor + " teleported " + " successfully.";
    }

    /**
     * description of what this action does
     * @param actor The actor performing the action.
     * @return result as a String
     */
    @Override
    public String menuDescription(Actor actor) {
        String res= actor + " jumps to" + direction;
        return res;
    }

}
