package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.actor.Goomba;
import game.actor.Koopa;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {
	public Floor() {
		super('_');
	}

	/**
	 * Enemies cannot enter floor
	 */
	public boolean canActorEnter(Actor actor){
		//Added by Ng Zu Shen on 20/4/2022-----------------------------------
		if (actor instanceof Goomba || actor instanceof Koopa){
			return false;
		}
		//-------------------------------------------------------------------
		return true;
	}
}
