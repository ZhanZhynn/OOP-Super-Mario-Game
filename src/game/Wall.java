package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

public class Wall extends Ground implements Jumpable, Destroyable{

	public Wall() {
		super('#');
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
	public String jumped(Actor by, Location at) { //bug: wall is destroyed even without capability
		Actor actor = by;
		Location location = at;
		if(Math.random() <= 0.8) {
			location.map().moveActor(actor, location);
			return actor + " jumped over " +  location.getGround().getClass().getSimpleName() +"(" + location.x() + ","+ location.y() + ")"  + " successfully.";
		}
		else {
			int damage = 20;
			actor.hurt(20);
			return actor + " fell from wall. Received " + damage + " damage.";
		}
	}

	public ActionList allowableActions(Actor otherActor, Location location, String direction) {
		ActionList actions = new ActionList();
		if(otherActor instanceof Player) {
			actions.add(new JumpAction(this, location, direction));
		}
		return actions;
	}

	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
