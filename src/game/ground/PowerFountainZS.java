package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.action.DrinkAction;
import game.action.RefillActionZS;
import game.actor.Player;
import game.interfaces.CanDrinkFountain;
import game.interfaces.Drinkable;
import game.item.BottleZS;
import game.item.PowerWaterZS;

/**
 * @author Ng Zu Shen
 * power fountain
 */
public class PowerFountainZS extends FountainZS{
    /**
     * Constructor.
     */
    public PowerFountainZS() {
        super('A');
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
                actions.add(new RefillActionZS(this));
                break;
            }
        }
        actions.add(new DrinkAction(this));
        return actions;
    }

    public void drank(Actor actor) {
        super.used();
        ((CanDrinkFountain)actor).incrementPowerBuff();
    }

    @Override
    public String RefillBy(Actor actor) {
        super.used();
        Player player = (Player) actor;
        player.getBottleZS().addWater(new PowerWaterZS());
        return "bottle is filled with Power Water";
    }

    @Override
    public String toString() {
        return "Power Fountain";
    }
}
