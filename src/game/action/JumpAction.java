package game.action;
/**
 * A class that handles the actor jump action
 *
 * @author Hee Zhan Zhynn (31989403)
 * @version 1
 */

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.interfaces.Jumpable;
import game.item.Status;

import java.util.Random;

public class JumpAction extends Action{

    /**
     * A Jumpable object
     */
    private Jumpable jumpable;

    /**
     * Location of jumpable objection
     */
    private Location jumpableLocation;

    /**
     * Direction of jumpable objection from actor
     */
    private String direction;



    /**
     * constructor
     * @param jumpable jumpable ground
     * @param jumpableLocation location of jumpable ground
     * @param direction direction of jumpable objection from actor
     */
    public JumpAction(Jumpable jumpable , Location jumpableLocation, String direction){
        this.jumpable = jumpable;
        this.jumpableLocation = jumpableLocation;
        this.direction = direction;

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
        //supermushroom jump
        if (actor.hasCapability(Status.GUARANTEED_JUMP)){
            jumpableLocation.map().moveActor(actor, jumpableLocation);
            return actor + " jumped over " +  jumpable +"(" + jumpableLocation.x() + ","+ jumpableLocation.y() + ")"  + " successfully.";
        }
        return jumpable.jumped(actor, jumpableLocation);
    }

    /**
     * description of what this action does
     * @param actor The actor performing the action.
     * @return result as a String
     */
    @Override
    public String menuDescription(Actor actor) {
        String res= actor + " jumps over "+ jumpable + " to the " + direction;
        return res;
    }
}