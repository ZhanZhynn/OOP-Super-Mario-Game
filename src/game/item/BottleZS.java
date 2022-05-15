package game.item;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.action.ConsumeAction;
import game.interfaces.Consumable;
import game.interfaces.Water;

import java.util.Deque;
import java.util.Stack;

/**
 * @author Ng Zu Shen
 */
public class BottleZS extends Item{

    /**
     * where the water is stored
     */
    private Stack<Consumable> storage = new Stack<>();
    /**
     * pointer for a given consume action
     */
    private ConsumeAction consumeAction = null;
    /**
     * pointer for latest water.
     */
    private Consumable lastWater;

    /***
     * Constructor.
     */
    public BottleZS() {
        super("Bottle", 'b', false);
    }

    /**
     * add consume action for last water if there is any.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        if (consumeAction!=null){
            this.removeAction(consumeAction);
        }
        if(!storage.empty()){
            lastWater = storage.peek();
            if (lastWater.getIsConsumed()){
                storage.pop();
                if(!storage.empty())
                    lastWater = storage.peek();
                else
                    lastWater = null;
            }
            if (lastWater != null) {
                consumeAction = new ConsumeAction(lastWater);
                this.addAction(consumeAction);
            }
        }
    }

    /**
     * add water in bottle
     * @param water
     */
    public void addWater(Consumable water){
        storage.push(water);
    }
}
