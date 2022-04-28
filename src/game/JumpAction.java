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

    public JumpAction(Actor player, Ground highGround){
        this.highGround = highGround;
        this.player = player;
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        boolean jumpResult = false;
        int successRate = rand.nextInt(100);
        if (this.highGround.getDisplayChar() == '#'){
            if (successRate > 10){
                jumpResult = true;
            }
        }
        if (jumpResult) {
            map.moveActor(actor, map.locationOf(actor));
            return "Jump Successful";
        }
        return "Jump Fail";
    }

    @Override
    public String menuDescription(Actor actor) {
        String res= actor + " jumps over "+ highGround.toString();
        return res;
    }
}