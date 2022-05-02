package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.action.JumpAction;
import game.actor.Player;
import game.interfaces.Destroyable;
import game.interfaces.Jumpable;
import game.interfaces.Resettable;
import game.item.Coin;
import game.item.Status;

import java.util.List;

public class Wall extends Ground implements Jumpable, Destroyable, Resettable {
	private boolean reset = false;

	/**
	 * Constructor for wall
	 */
	public Wall() {
		super('#');
		this.registerInstance();
	}

	public void resetInstance(){
		this.reset = true;
	}

	/**
	 * Player can destroy highground if consumed Power Star
	 * @param actor is the player.
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		//added by Ng Zu Shen on 20/4/2022----------------
		if(actor.hasCapability(Status.DESTROY_HIGH_GROUND)){
			return true;
		}
		//NgZuShen\----------------------------------------
		return false;
	}

	/**
	 * Function for Mario to jump.
	 * There is a 80% success rate, if fail, there will be a 20 damage incurred.
	 * If jump is successful, a message will be printed to prompt the player, if fail, another message will be printed.
	 * @param by is the actor
	 * @param at is the location of where the player jumped over.
	 */

	//added by Hee Zhan Zhynn on 28/4/2022----------------
	public String jumped(Actor by, Location at) {
		Actor actor = by;
		Location location = at;
		if(Math.random() <= 0.8) {
			location.map().moveActor(actor, location);
			return actor + " jumped over " +  location +"(" + location.x() + ","+ location.y() + ")"  + " successfully.";
		}
		else {
			int damage = 20;
			actor.hurt(20);
			return actor + " fell from wall. Received " + damage + " damage.";
		}
	}

	/**
	 * At the moment, we only make it can be attacked by Player.
	 * You can do something else with this method.
	 * @param otherActor the Actor that might perform an action.
	 * @param location location of wall
	 * @param direction  String representing the direction of the other Actor
	 * @return list of actions
	 */

	public ActionList allowableActions(Actor otherActor, Location location, String direction) {
		ActionList actions = new ActionList();
		if(otherActor instanceof Player && !location.containsAnActor()) {
			if (!otherActor.hasCapability(Status.DESTROY_HIGH_GROUND)) {
				actions.add(new JumpAction(this, location, direction));
			}
		}
		return actions;
	}

	/**
	 * handle all the behaviour of trees as stated in assignment specification
	 * @param location The location of the wall
	 */
	public void tick(Location location){
		if (this.reset){
			List<Item> items = location.getItems();
			if (items.size() >0) {
				for (int i = 0; i < items.size(); i++) {
					if (items.get(i) instanceof Coin){
						location.removeItem(items.get(i));
					}
				}
			}
		}
		this.reset = false;

	}

	public String toString() {
		return "Wall";
	}

	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
