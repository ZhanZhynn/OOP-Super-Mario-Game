package game.item;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.interfaces.Consumable;
import game.interfaces.Resettable;
import game.interfaces.Sellable;
import game.action.ConsumeAction;

/**
 * magical item power star
 * @author Ng Zu Shen
 * @version 1.0
 */
public class PowerStar extends Item implements Sellable, Consumable, Resettable {

    /**
     * price sold by toad
     */
    public static final int PRICE = 600;
    public static final String name = "PowerStar";

    /**
     * Instance for reset action
     */
    private boolean reset = false;
    /**
     * counter to keep track of 10 rounds fading time limit
     */
    private int counter = 10;
    /**
     * whether is this power star is consumed
     */
    private boolean isConsumed = false;
    /**
     * consume action to consume this power star
     */
    private Action consumeAction = new ConsumeAction(this);


    /**
     * Constructor of power star
     * @param name name of this power star(can be anything but preferably "Power Star")
     * @param displayChar Character to be display on map
     * @param portable is this portable?
     */
    public PowerStar(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        this.addAction(consumeAction);
        this.registerInstance();
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

        if (this.getIsConsumed() && this.reset){
            this.fade(actor);
            actor.removeItemFromInventory(this);
        }
        else {
            System.out.println("Power star ability round remaining: " + (counter + 1));
            System.out.println("Mario is invincible!");
        }
    }

    /**
     * getter for counter
     * @return
     */
    public int getCounter(){return counter;}

    /**
     * add the correct capabilities to this item when consumed by an actor
     * reset the counter to 10
     * @param actor the actor that consume this
     */
    public void consume(Actor actor, GameMap map){
        isConsumed = true;
        counter = 10;
        addCapability(Status.INSTANT_KILL);
        addCapability(Status.PATH_OF_GOLD);
        addCapability(Status.INVINCIBLE);
        addCapability(Status.DESTROY_HIGH_GROUND);
        actor.heal(200);
        this.removeAction(consumeAction);
    }

    /**
     * a method to remove the capabilites in actor
     * @param actor the actor having the capabilities
     */
    public void fade(Actor actor){
        removeCapability(Status.INSTANT_KILL);
        removeCapability(Status.PATH_OF_GOLD);
        removeCapability(Status.INVINCIBLE);
        removeCapability(Status.DESTROY_HIGH_GROUND);
    }

    /**
     * has the item been consumed?
     *
     * @return whether the item has been consumed
     */
    public boolean getIsConsumed(){return isConsumed;}

    @Override
    public int getPrice() {
        return PRICE;
    }

    @Override
    public void resetInstance() {
        reset = true;
    }
}
