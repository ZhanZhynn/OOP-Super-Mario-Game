package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.resetmanager.ResetManager;

/**
 * An action to reset the gamemap
 *
 * @author Hee Zhan Zhynn (31989403)
 * @version 1
 */

public class ResetAction extends Action {
    //class added by Zz

    /**
     * constructor
     */
    public ResetAction(){
    }

    /**
     * all classes implemeting the Resettable interface is resetted
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return description of what happened as a String
     */
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run();
        return menuDescription(actor);
    }

    /**
     * description of what this action does
     * @param actor The actor performing the action.
     * @return result as a String
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " resets game";
    }

    /**
     * hotkey of the action
     * @return hotkey in the menu description
     */
    @Override
    public String hotkey() {
        return "r";
    }


}
