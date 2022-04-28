package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

//Created by Ng Zu Shen on 20/4/2022
/**
 * Interface for item that are consumable
 * @author Ng Zu Shen
 */
public interface Consumable {
    public void consume(Actor actor, GameMap map);
}
