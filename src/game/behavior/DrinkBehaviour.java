package game.behavior;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.action.DrinkAction;
import game.ground.FountainZS;

/**
 * @author Ng Zu Shen
 * drink behaviour for wandering enemies
 */
public class DrinkBehaviour implements Behaviour{

    /**
     * get drink action if actor go to a fountain.
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (map.locationOf(actor).getGround() instanceof FountainZS){
            FountainZS temp = (FountainZS) map.locationOf(actor).getGround();
            return new DrinkAction(temp);
        }
        return null;
    }
}
