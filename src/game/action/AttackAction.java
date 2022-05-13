package game.action;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actor.DormantKoopa;
import game.actor.Koopa;
import game.item.Fire;
import game.item.Status;

// last modified by NgZuShen on 22/4/2022 (add documentation and implement instant kill)
/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * this method is responsible to check the weapon(damage) of attacking actor
	 * and hurt the attacked actor. This method also implement the chance of missing an attack
	 * and guaranteed kill when actor consume power star.
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a brief sentence in string of what happened after the action is executed.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		//Author:NgZuShen------------------------22/4/2022------------------------
		//kill enemies 100% when player have power star ability
		if (actor.hasCapability(Status.INSTANT_KILL)){
			ActionList dropActions = new ActionList();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction(actor));
			for (Action drop : dropActions)
				drop.execute(target, map);
			map.removeActor(target);
			String result = actor + " kill "+ target + " using power star ability.";
			if (target instanceof Koopa){
				Location here = map.locationOf(target);
				here.addActor(new DormantKoopa());
				result += System.lineSeparator() + target + " hides in its shell.";
				return result;
			}
			return result;
		}

		// if no power star then use weapon
		Weapon weapon = actor.getWeapon();

		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		if (target.hasCapability(Status.INVINCIBLE)){
			return actor + " tried to attack " + target + ". " + target + " receive no damage because of power star.";
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		//if koopa died turn into dormant koopa
		target.hurt(damage);
		if (actor.hasCapability(Status.FIRE_ATTACK)){
			map.locationOf(target).addItem(new Fire());
		}
		if (!target.isConscious()) {
			if (target instanceof Koopa){
				Location here = map.locationOf(target);
				map.removeActor(target);
				here.addActor(new DormantKoopa());
				result += System.lineSeparator() + target + " hides in its shell.";
				return result;
			}
			ActionList dropActions = new ActionList();
			// drop all items
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction(actor));
			for (Action drop : dropActions)
				drop.execute(target, map);
			// remove actor
			map.removeActor(target);
			result += System.lineSeparator() + target + " is killed.";
		}
		//NgZuShen\--------------------------------------------------------------

		return result;
	}

	/**
	 *
	 * @param actor The actor performing the action.
	 * @return A sentence in string explaining what the action is.
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}
