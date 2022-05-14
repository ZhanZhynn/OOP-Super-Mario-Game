package game.actor;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.action.RescueAction;
import game.item.Status;

import java.util.ArrayList;
import java.util.Random;

public class PrincessPeach extends Actor {


    /**
     * Constructor.
     */
    public PrincessPeach() {
        super("Princess Peach", 'P', 0);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.RESCUE_PRINCESS)){
            actions.add(new RescueAction(this));
        }
        return actions;
    }

}
