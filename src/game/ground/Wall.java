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

	public Wall() {
		super('#');
		this.registerInstance();
	}

	public void resetInstance(){
		this.reset = true;
	}


	@Override
	public boolean canActorEnter(Actor actor) {
		//added by Ng Zu Shen on 20/4/2022----------------
		if(actor.hasCapability(Status.DESTROY_HIGH_GROUND)){
			return true;
		}
		//NgZuShen\----------------------------------------
		return false;
	}

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

	public ActionList allowableActions(Actor otherActor, Location location, String direction) {
		ActionList actions = new ActionList();
		if(otherActor instanceof Player && !location.containsAnActor()) {
			if (!otherActor.hasCapability(Status.DESTROY_HIGH_GROUND)) {
				actions.add(new JumpAction(this, location, direction));
			}
		}
		return actions;
	}

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
