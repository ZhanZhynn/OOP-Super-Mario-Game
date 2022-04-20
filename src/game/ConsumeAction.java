package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

public class ConsumeAction extends Action {

    private Item item;

    public ConsumeAction(Item item){
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Consumable consumable = (Consumable) item;
        consumable.consume(actor);
        return item + " consumed";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consume " + item.toString();
    }
}
