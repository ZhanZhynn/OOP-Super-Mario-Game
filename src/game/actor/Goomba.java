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
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.action.SpeakAction;

/**
 * A little fungus guy.
 */
public class Goomba extends WanderingEnemy {

	boolean speak = true;
	SpeakAction speakAction = new SpeakAction();

	/**
	 * Constructor.
	 */
	public Goomba() {
		super("Goomba", 'g', 20);
	}

	/**
	 * This is Goomba's weapon, the kick.
	 */
	public Weapon getWeapon() {
		return new IntrinsicWeapon(10+getPowerBuff()*15, "kick");
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

		if (speak){
			System.out.println(this + ": " + speakAction.dialogGoomba());
		}
		speak = !speak;
		// if player rests, then GOOMBA has 10% chance to be removed.
		if (Math.random() <= 0.1 || !this.isConscious()){ //10% to suicide
			map.removeActor(this);
			return new DoNothingAction();
		}
		return super.playTurn(actions, lastAction, map, display);
	}
}

