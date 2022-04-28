package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public class DestroyShellAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    /**
     * The direction of incoming attack.
     */
    protected String direction;

    public DestroyShellAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        ActionList dropActions = new ActionList();
        // drop all items
        Location here = map.locationOf(target);
        map.removeActor(target);
        here.addItem(new SuperMushroom());
        return "shell is broken, super mushroom was found inside!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " whack " + target + " at " + direction;
    }
}
