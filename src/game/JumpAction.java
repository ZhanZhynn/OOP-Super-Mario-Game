package game;
/*
Author: Hee Zhan Zhynn
Last modified: 28/04/2022
 */

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;

public class JumpAction extends Action{
    private Jumpable jumpable;
    private Location jumpableLocation;
    private String direction;


    /**
     * The Ground to jump over
     */
    protected Ground highGround;

    /**
     * The Player
     */
    protected Actor player;

    /**
     * Random number generator
     */
    protected Random rand = new Random();

    public JumpAction(Jumpable jumpable , Location jumpableLocation, String direction){
        this.jumpable = jumpable;
        this.jumpableLocation = jumpableLocation;
        this.direction = direction;

    }

    @Override
    public String execute(Actor actor, GameMap map) {
        //supermushroom jump
        if (actor.hasCapability(Status.GUARANTEED_JUMP)){
            jumpableLocation.map().moveActor(actor, jumpableLocation);
            return actor + " jumped over " +  jumpable +"(" + jumpableLocation.x() + ","+ jumpableLocation.y() + ")"  + " successfully.";
        }
        return jumpable.jumped(actor, jumpableLocation);

    }

    @Override
    public String menuDescription(Actor actor) {
        String res= actor + " jumps over "+ jumpable + " to the " + direction;
        return res;
    }
}