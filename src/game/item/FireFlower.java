package game.item;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.action.ConsumeAction;
import game.interfaces.Consumable;

public class FireFlower extends Item implements Consumable {
    private int counter = 20;
    private boolean isConsumed = false;
    private Action consumeAction = new ConsumeAction(this);


    public FireFlower() {
        super("Fire Flower", 'f', true);
        this.addAction(consumeAction);
    }



    /**
     * this tick is used when the powerstar is on the map
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        counter -= 1;
    }

    /**
     * this tick is used when the item is carried by an actor
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation);
        counter -= 1;

        if (this.getCounter() == 0){
            this.fade(actor);
            actor.removeItemFromInventory(this);
        }

        else if (this.getIsConsumed()) {
            System.out.println("Fire Flower ability round remaining: " + (counter + 1));
            System.out.println("Mario has Fire Attack!");
        }
    }

    /**
     * add the correct capabilities to this item when consumed by an actor
     * reset the counter to 10
     * @param actor the actor that consume this
     */
    public void consume(Actor actor, GameMap map){
        if (!actor.getInventory().contains(this)){
            actor.addItemToInventory(this);
            map.locationOf(actor).removeItem(this);
        }
        this.isConsumed = true;
        this.counter = 20;
        this.addCapability(Status.FIRE_ATTACK);
        this.removeAction(consumeAction);
    }

    public void fade(Actor actor){
        removeCapability(Status.FIRE_ATTACK);
    }

    /**
     * getter for counter
     * @return
     */
    public int getCounter(){return counter;}

    /**
     * has the item been consumed?
     *
     * @return whether the item has been consumed
     */
    public boolean getIsConsumed(){return isConsumed;}


}
