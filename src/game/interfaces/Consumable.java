package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface for item that are consumable
 * @author Ng Zu Shen
 */
public interface Consumable {
    public void consume(Actor actor, GameMap map);
    public boolean getIsConsumed();
}
