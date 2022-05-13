package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actor.PrincessPeach;
import game.item.Key;

public class RescueAction extends Action {

    private PrincessPeach pp;

    public RescueAction(PrincessPeach pp) {
        this.pp = pp;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return pp+" is now free!! mission complete!!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Free " + pp;
    }
}
