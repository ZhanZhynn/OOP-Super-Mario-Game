//package game;
//
//
//import edu.monash.fit2099.engine.actions.Action;
//import edu.monash.fit2099.engine.actions.ActionList;
//import edu.monash.fit2099.engine.actors.Actor;
//import edu.monash.fit2099.engine.displays.Display;
//import edu.monash.fit2099.engine.actions.DoNothingAction;
//import edu.monash.fit2099.engine.positions.GameMap;
//
//import java.util.HashMap;
//import java.util.Map;
///**
// * A little fungus guy.
// */
//public class Goomba extends Actor {
//	private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
//
//	/**
//	 * Constructor.
//	 */
//	public Goomba() {
//		super("Goomba", 'g', 50);
//		this.behaviours.put(10, new WanderBehaviour());
//	}
//
//	/**
//	 * At the moment, we only make it can be attacked by Player.
//	 * You can do something else with this method.
//	 * @param otherActor the Actor that might perform an action.
//	 * @param direction  String representing the direction of the other Actor
//	 * @param map        current GameMap
//	 * @return list of actions
//	 * @see Status#HOSTILE_TO_ENEMY
//	 */
//	@Override
//	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
//		ActionList actions = new ActionList();
//		// it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
//		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
//			actions.add(new AttackAction(this,direction));
//		}
//		return actions;
//	}
//
//	/**
//	 * Figure out what to do next.
//	 * @see Actor#playTurn(ActionList, Action, GameMap, Display)
//	 */
//	@Override
//	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
//		for(Behaviour behaviour : behaviours.values()) {
//			Action action = behaviour.getAction(this, map);
//			if (action != null)
//				return action;
//		}
//		return new DoNothingAction();
//	}
//
//}

package game.actor;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.action.AttackAction;
import game.action.DrinkAction;
import game.behavior.*;
import game.interfaces.CanDrinkFountain;
import game.interfaces.Resettable;
import game.item.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * A little fungus guy.
 */
public class Goomba extends Actor implements Resettable, CanDrinkFountain{
	private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
	private boolean reset = false;
	private Player followTarget;
	private int powerBuff=0;
	private int thirst = 14;


	/**
	 * Constructor.
	 */
	public Goomba() {
		super("Goomba", 'g', 20);
		this.behaviours.put(10, new WanderBehaviour());
		this.behaviours.put(13, new AttackBehaviour());
		this.behaviours.put(thirst, new DrinkBehaviour());
		this.registerInstance();
	}

	public void resetInstance(){
		this.reset = true;
	}
	/**
	 * This is Goomba's weapon, the kick.
	 */
	public Weapon getWeapon() {
		return new IntrinsicWeapon(10+powerBuff*15, "kick");
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
	 * If player hits reset, there is a 10% chance that Goomba will be removed from the map.
	 * @see Actor#playTurn(ActionList, Action, GameMap, Display)
	 *  @param actions    collection of possible Actions for this Actor
	 *  @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 *  @param map        the map containing the Actor
	 *  @param display    the I/O object to which messages may be written
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

		if (this.reset == true){
			this.reset = false;
			map.removeActor(this);
			return new DoNothingAction();
		}
		// if player rests, then GOOMBA has 10% chance to be removed.
		if (Math.random() <= 0.1 || !this.isConscious()){ //10% to suicide
			map.removeActor(this);
			return new DoNothingAction();
		}
		Action action = new DoNothingAction();
		if (followTarget == null) {
			for (Exit exit : map.locationOf(this).getExits()) {
				if (exit.getDestination().getActor() instanceof Player) {
					Player p = (Player) exit.getDestination().getActor();
					followTarget = p;
					this.behaviours.put(12, new FollowBehaviour(followTarget));
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
		this.behaviours.remove(thirst);
		thirst+=1;
		this.behaviours.put(thirst, new DrinkBehaviour());
		if (followTarget != null) {
			Location here = map.locationOf(this);
			Location there = map.locationOf(followTarget);
			if (Math.abs(here.x() - there.x()) + Math.abs(here.y() - there.y()) > 3) {
				followTarget = null;
				this.behaviours.remove(12);
			}
		}
		if (action != null) {
			if (action instanceof DrinkAction){
				this.behaviours.remove(thirst);
				thirst = 0;
				this.behaviours.put(thirst, new DrinkBehaviour());
			}
			return action;
		}
		return new DoNothingAction();
	}

	@Override
	public void incrementPowerBuff() {
		powerBuff += 1;
	}


}
