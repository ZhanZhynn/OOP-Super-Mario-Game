package game.item;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.action.ConsumeAction;
import game.interfaces.Consumable;
import game.interfaces.Water;

import java.util.Deque;
import java.util.Stack;

public class BottleZS extends Item{

    private Stack<Consumable> storage = new Stack<>();
    private ConsumeAction consumeAction = null;
    private Consumable lastWater;

    /***
     * Constructor.
     */
    public BottleZS() {
        super("Bottle", 'b', false);
    }

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

    public void addWater(Consumable water){
        storage.push(water);
    }
}
