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
    private Item item;

    /**
     * constructor
     * @param item the item to be consumed
     */
    public ConsumeAction(Item item){
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Consumable consumable = (Consumable) item;
        consumable.consume(actor, map);
        return item + " consumed";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consume " + item.toString();
    }
}
