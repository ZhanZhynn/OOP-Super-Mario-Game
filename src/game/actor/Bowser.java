package game.actor;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.action.AttackAction;
import game.action.SpeakAction;
import game.behavior.AttackBehaviour;
import game.behavior.Behaviour;
import game.behavior.FollowBehaviour;
import game.interfaces.Resettable;
import game.item.Key;
import game.item.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bowser extends Actor implements Resettable {

    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
    private boolean reset = false;
    private final int startX, startY;
    private Player followTarget;
    boolean speak = true;
    private SpeakAction speakAction = new SpeakAction();



    /**
     * Constructor.
     */
    public Bowser(Location location) {
        super("Bowser", 'B', 500);
        this.behaviours.put(10, new AttackBehaviour());
        this.addItemToInventory(new Key());
        this.addCapability(Status.FIRE_ATTACK);
        startX = location.x();
        startY = location.y();
        this.registerInstance();
    }

    /**
     * set koopa base attack to 80
     * @return
     */
    public Weapon getWeapon() {
        return new IntrinsicWeapon(80, "punch");
    }

    /**
     * just to set reset to true when player reset the game.
     */
    public void resetInstance() {
        this.reset = true;
    }


    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        if (speak){
            System.out.println(this + ": " + speakAction.dialogBowser());
        }
        speak = !speak;

        if (this.reset){
            this.reset = false;
            if (!map.at(startX, startY).containsAnActor()) {
                map.moveActor(this, map.at(startX, startY));
            }
            this.heal(500);
            return new DoNothingAction();
        }

        Action action = new DoNothingAction();
        if (followTarget == null) {
            for (Exit exit : map.locationOf(this).getExits()) {
                if (exit.getDestination().getActor() instanceof Player) {
                    Player p = (Player) exit.getDestination().getActor();
                    followTarget = p;
                    this.behaviours.put(9, new FollowBehaviour(followTarget));
                    break;
                }
            }
        }

        int priority = 0;
        for(Map.Entry<Integer, Behaviour> set : behaviours.entrySet()) {
            if (set.getKey() > priority && set.getValue().getAction(this, map)!=null) {
                action = set.getValue().getAction(this, map);
                priority = set.getKey();
            }
        }
        if (followTarget != null) {
            Location here = map.locationOf(this);
            Location there = map.locationOf(followTarget);
            if (Math.abs(here.x() - there.x()) + Math.abs(here.y() - there.y()) > 3) {
                followTarget = null;
                this.behaviours.remove(9);
            }
        }
        if (action != null) {
            return action;
        }



        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this,direction));
        }


        return actions;
    }


}
