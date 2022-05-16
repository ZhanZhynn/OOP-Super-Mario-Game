package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.action.DrinkAction;
import game.action.RefillAction;
import game.actor.Player;
import game.item.Bottle;
import game.item.HealWater;

/**
 * @author Ng zu shen
 * healing fountain
 */
public class HealFountain extends Fountain {
    /**
     * Constructor.
     */
    public HealFountain() {
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
            if(item instanceof Bottle){
                actions.add(new RefillAction(this));
                break;
            }
        }
        actions.add(new DrinkAction(this));
        return actions;
    }

    public void drank(Actor actor) {
        super.used();
        actor.heal(50);
    }

    @Override
    public String RefillBy(Actor actor) {
        super.used();
        Player player = (Player) actor;
        player.getBottleZS().addWater(new HealWater());
        return "bottle is filled with Heal Water";
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
