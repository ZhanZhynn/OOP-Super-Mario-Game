package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

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
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
