package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actor.Toad;
import game.item.Bottle;

public class getBottleAction extends Action {

    Toad toad;

    /**
     * constructor for getBottleAction.
     */
    public getBottleAction(Toad toad){
        this.toad = toad;
    }

    /**
     * actor adds bottle into inventory after receiving from toad.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return description of what happened as a String
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addItemToInventory(new Bottle());
        toad.giveBottle();
        return actor + " acquired Bottle!";
    }

    /**
     * description of what this action does
     * @param actor The actor performing the action.
     * @return result as a String
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Get bottle from Toad";
    }
}
