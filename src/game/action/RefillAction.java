package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Water;
import game.item.Bottle;

public class RefillAction extends Action {

    private Water fountainWater;
    private Bottle bottle;


    public RefillAction(Water fountainWater, Bottle bottle){
        this.fountainWater = fountainWater;
        this.bottle = bottle;
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        return bottle.addFountainWater(actor, fountainWater);
    }

    @Override
    public String menuDescription(Actor actor) {
            return actor + " refill " + fountainWater;
    }
}
