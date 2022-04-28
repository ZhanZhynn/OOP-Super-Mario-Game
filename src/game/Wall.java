package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

public class Wall extends Ground {

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


	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		ActionList actions = new ActionList();
		if(otherActor instanceof Player) {
			actions.add(new JumpAction(otherActor, this));
		}
		return actions;
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
