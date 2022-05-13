package game.item;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actor.Player;
import game.interfaces.Consumable;
import game.interfaces.Resettable;
import game.interfaces.Sellable;
import game.action.ConsumeAction;

//added by Ng Zu Shen on 20/4/2022

/**
 * magical item sper mushroom
 * @author Ng Zu Shen
 * @version 1.0
 */
public class SuperMushroom extends Item implements Sellable, Consumable, Resettable {

    /**
     * price sold by toad
     */
    public final static int PRICE = 400;

    private boolean reset = false;
    /**
     * is this superMushroom consumed?
     */
    private boolean isConsumed = false;
    /**
     * consume action used to consume this super mushroom
     */
    private Action consumeAction = new ConsumeAction(this);

    /**
     * Constructor of super mushroom
     * @param name name of this super mushroom
     * @param displayChar Character to be display on map
     * @param portable is this portable?
     */
//    public SuperMushroom(String name, char displayChar, boolean portable) {
//        super(name, displayChar, portable);
//        this.addAction(new ConsumeAction(this));
//        this.registerInstance();
//    }

    /**
     * constructor with no parameters, generate super mushroom with pre defined inputs.
     */
    public SuperMushroom() {
        super("Super mushroom", '^', false);
        this.addAction(consumeAction);
        this.registerInstance();
    }

    public Boolean getIsConsumed(){return isConsumed;}


    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        if(getIsConsumed()) {
            if (((Player)actor).getCurrentHp() < ((Player)actor).getLastRoundHp() || this.reset) {
                this.fade(actor);
                actor.removeItemFromInventory(this);
                this.reset = false;
            }
        }
    }

    @Override
    public int getPrice() {
        return PRICE;
    }

    /**
     * Increase the HP of actor that consumed this superMushroom
     * and add the correct capabilities to this item after consumed.
     * @param actor
     */
    @Override
    public void consume(Actor actor, GameMap map) {
        if (!actor.getInventory().contains(this)){
            actor.addItemToInventory(this);
            map.locationOf(actor).removeItem(this);
        }
        isConsumed = true;
        addCapability(Status.TALL);
        addCapability(Status.GUARANTEED_JUMP);
        actor.increaseMaxHp(50);
        this.removeAction(consumeAction);
    }

    @Override
    public void resetInstance() {
        reset = true;
    }

    public void fade(Actor actor){
        removeCapability(Status.TALL);
        removeCapability(Status.GUARANTEED_JUMP);
    }
}
