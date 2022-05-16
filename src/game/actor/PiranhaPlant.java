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
import game.action.SpeakAction;
import game.behavior.AttackBehaviour;
import game.behavior.Behaviour;
import game.item.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PiranhaPlant extends Actor{
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
    private boolean reset = false;
    boolean speak = true;
    //instantiating an instance of SpeakAction here.
    private SpeakAction speakAction = new SpeakAction();


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

    /**
     * Figure out what to do next.
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     *  @param actions    collection of possible Actions for this Actor
     *  @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     *  @param map        the map containing the Actor
     *  @param display    the I/O object to which messages may be written
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        //for piranhaPlant to speak at every two turns.
        if (speak){
            System.out.println(this + ": " + speakAction.dialogPiranha());
        }
        speak = !speak;

        Action action = null;
        int priority = 0;
        for(Map.Entry<Integer, Behaviour> set : behaviours.entrySet()) {
            if (set.getKey() > priority && set.getValue().getAction(this, map)!=null) {
                System.out.println("test");
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



}
