package game.actor;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.action.MonologueAction;
import game.action.RescueAction;
import game.interfaces.Speakable;
import game.item.Status;

import java.util.ArrayList;
import java.util.Random;

public class PrincessPeach extends Actor implements Speakable {

    ArrayList<String> dialog = new ArrayList<>();
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

    @Override
    public String allDialog() {
        dialog.clear();
        dialog.add("Dear Mario, I'll be waiting for you...");
        dialog.add("Never gonna give you up!");
        dialog.add("Release me, or I will kick you!");
        int index = new Random().nextInt(dialog.size());
        String string = dialog.get(index);
        return string;
    }
}
