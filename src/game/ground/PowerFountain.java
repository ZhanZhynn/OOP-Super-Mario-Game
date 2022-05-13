package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.action.RefillAction;
import game.interfaces.Water;
import game.item.Bottle;

public class PowerFountain extends Ground {

    private Bottle bottle;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public PowerFountain(char displayChar, Bottle bottle) {
        super('A');
        this.bottle = bottle;
    }

    public Water getWater(){
        return new PowerWater();
    }

    /**
     * At the moment, we only make it can be attacked by Player.
     * You can do something else with this method.
     * @param otherActor the Actor that might perform an action.
     * @param location location of tree
     * @param direction  String representing the direction of the other Actor
     * @return list of actions
     */
    public ActionList allowableActions(Actor otherActor, Location location, String direction) {
        ActionList actions = new ActionList();
        if(otherActor == location.getActor()) {
            actions.add(new RefillAction(getWater(), bottle));
        }
        return actions;
    }

}

