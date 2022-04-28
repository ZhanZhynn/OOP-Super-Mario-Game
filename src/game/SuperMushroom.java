package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

//added by Ng Zu Shen on 20/4/2022

/**
 * magical item sper mushroom
 * @author Ng Zu Shen
 * @version 1.0
 */
public class SuperMushroom extends Item implements Sellable,Consumable{

    /**
     * price sold by toad
     */
    public final static int PRICE = 400;
    /**
     * is this superMushroom consumed?
     */
    private Boolean isConsumed = false;
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
    public SuperMushroom(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        this.addAction(consumeAction);
    }

    public SuperMushroom() {
        super("Super mushroom", '^', false);
        this.addAction(consumeAction);
    }

    public Boolean getIsConsumed(){return isConsumed;}

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

//    public void fade(){
//        removeCapability(Status.TALL);
//        removeCapability(Status.GUARANTEED_JUMP);
//    }
}
