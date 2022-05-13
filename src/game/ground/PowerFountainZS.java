package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.action.RefillActionZS;
import game.item.BottleZS;
import game.item.PowerWaterZS;
import game.item.SuperMushroom;

public class PowerFountainZS extends FountainZS{
    /**
     * Constructor.
     */
    public PowerFountainZS() {
        super('A');
    }

    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        for (Item item : actor.getInventory()){
            if(item instanceof BottleZS){
                actions.add(new RefillActionZS(new PowerWaterZS(), this));
                break;
            }
        }
        return actions;
    }

    @Override
    public String toString() {
        return "Power Fountain";
    }
}
