package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Consumable;
//created by Ng Zu shen on 20/4/2022

/**
 * An action to allow player to consume magical items
 * @author Ng Zu shen
 * @version 1.0
 */
public class ConsumeAction extends Action {

    /**
     * an Item object to be consumed
     */
    private Consumable item;

    /**
     * constructor
     * @param item the item to be consumed
     */
    public ConsumeAction(Consumable item){
        this.item = item;
    }

    /**
     * what happened after executing this action?
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return description of what happened as a String
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        item.consume(actor, map);
        return item + " consumed";
    }

    /**
     * description of what this action does
     * @param actor The actor performing the action.
     * @return result as a String
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consume " + item.toString();
    }
}
