package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.action.DrinkAction;
import game.action.RefillActionZS;
import game.item.BottleZS;
import game.item.HealWaterZS;

/**
 * @author Ng zu shen
 * healing fountain
 */
public class HealFountainZS extends FountainZS{
    /**
     * Constructor.
     */
    public HealFountainZS() {
        super('H');
    }

    /**
     * only allow people with bottle to refill. allow drink action
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return
     */
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        for (Item item : actor.getInventory()){
            if(item instanceof BottleZS){
                actions.add(new RefillActionZS(new HealWaterZS(), this));
                break;
            }
        }
        actions.add(new DrinkAction(this));
        return actions;
    }

    /**
     * to string method
     * @return
     */
    @Override
    public String toString() {
        return "Heal Fountain";
    }
}
