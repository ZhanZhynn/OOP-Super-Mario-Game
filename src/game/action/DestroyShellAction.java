package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.item.SuperMushroom;

public class DestroyShellAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    /**
     * The direction of incoming attack.
     */
    protected String direction;

    /**
     * constructor of this action
     * @param target the dormant koopa
     * @param direction the direction of dormant koopa
     */
    public DestroyShellAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * what happened after executing this action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ActionList dropActions = new ActionList();
        // drop all items
        Location here = map.locationOf(target);
        map.removeActor(target);
        here.addItem(new SuperMushroom());
        return "shell is broken, super mushroom was found inside!";
    }

    /**
     * menu description of this action
     * @param actor The actor performing the action.
     * @return description as  a String
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " destroys " + target + "'s shell at " + direction;
    }
}
