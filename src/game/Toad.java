package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

public class Toad extends Actor {

    public Toad() {
        super("toad", 'O', 0);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor instanceof Player) {
            actions.add(new TradeAction(this,direction, new PowerStar("Power Star", '*', false)));
            actions.add(new TradeAction(this,direction, new SuperMushroom("Super Mushroom", '^', false)));
            actions.add(new TradeAction(this, direction, new Wrench("Wrench", '|', true)));
        }
        return actions;
    }
}
