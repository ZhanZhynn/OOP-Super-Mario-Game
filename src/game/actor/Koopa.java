package game.actor;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.action.AttackAction;
import game.behavior.AttackBehaviour;
import game.behavior.Behaviour;
import game.behavior.FollowBehaviour;
import game.behavior.WanderBehaviour;
import game.interfaces.Resettable;
import game.item.Status;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ng Zu Shen
 * @version 1.0
 * Koopa behaves pretty similar to Goomba except for the stat different.
 * When Koopa dies, they got turn into DormantKoopa but that is in attack action class.
 */
public class Koopa extends Actor implements Resettable {
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
    private boolean reset = false;

    /**
     * Constructor.
     */
    public Koopa() {
        super("Koopa", 'K', 100);
        this.behaviours.put(10, new WanderBehaviour());
        this.behaviours.put(13, new AttackBehaviour());
        this.registerInstance();
    }

    public void resetInstance(){
        this.reset = true;
    }

    public Weapon getWeapon() {
        return new IntrinsicWeapon(30, "punches");
    }

    /**
     * At the moment, we only make it can be attacked by Player.
     * You can do something else with this method.
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

    /**
     * Figure out what to do next.
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (this.reset==true){
            this.reset = false;
            map.removeActor(this);
            return new DoNothingAction();
        }

        Action action = new DoNothingAction();
        //can detect up to 2 step away. even the diagonal 2 steps.
        for (Exit exit : map.locationOf(this).getExits()) {
            for(Exit exitLayer2 : exit.getDestination().getExits()) {
                if (exitLayer2.getDestination().containsAnActor()) {
                    if (exit.getDestination().getActor() instanceof Player) {
                        Player p = (Player) exit.getDestination().getActor();
                        this.behaviours.put(12, new FollowBehaviour(p));
                        break;
                    }
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
        this.behaviours.remove(12);
        if (action != null) {
            return action;
        }
        return new DoNothingAction();
    }
}
