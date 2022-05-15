package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actor.PrincessPeach;
import game.item.Key;

/**
 * @author Ng Zu Shen
 * action to rescue an actor and finish the game
 */
public class RescueAction extends Action {

    /**
     * actor to be rescued.
     */
    private Actor pp;

    /**
     * constructor
     * @param pp the actor to be rescued
     */
    public RescueAction(Actor pp) {
        this.pp = pp;
    }

    /**
     * remove the player and finish the game once this is executed
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return pp+" is now free!! mission complete!!";
    }

    /**
     * description of this action
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Free " + pp;
    }
}
