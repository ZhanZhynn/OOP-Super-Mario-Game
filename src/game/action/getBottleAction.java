package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actor.Toad;
import game.item.Bottle;

public class getBottleAction extends Action {

    Toad toad;

    public getBottleAction(Toad toad){
        this.toad = toad;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addItemToInventory(new Bottle());
        toad.giveBottle();
        return actor + " acquired Bottle!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Get bottle from Toad";
    }
}
