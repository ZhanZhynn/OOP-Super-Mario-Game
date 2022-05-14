package game.actor;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.action.AttackAction;
import game.behavior.AttackBehaviour;
import game.behavior.Behaviour;
import game.interfaces.Speakable;
import game.item.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PiranhaPlant extends Actor implements Speakable {
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
    private boolean reset = false;
    ArrayList<String> dialog = new ArrayList<>();
    /**
     * Constructor.
     */
    public PiranhaPlant() {
        super("Piranha Plant", 'Y', 150);
        this.behaviours.put(10, new AttackBehaviour());
    }

    public Weapon getWeapon() {
        return new IntrinsicWeapon(90, "chomps");
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Action action = null;
        int priority = 0;
        for(Map.Entry<Integer, Behaviour> set : behaviours.entrySet()) {
            if (set.getKey() > priority && set.getValue().getAction(this, map)!=null) {
                action = set.getValue().getAction(this, map);
                priority = set.getKey();
            }
        }
        if (action != null) {
            return action;
        }
        return new DoNothingAction();
    }

    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

    @Override
    public String allDialog() {
        dialog.clear();
        dialog.add("Slsstssthshs~! (Never gonna say goodbye~)");
        dialog.add("Ohmnom nom nom nom.");
        int index = new Random().nextInt(dialog.size());
        String string = dialog.get(index);
        return string;
    }
}
