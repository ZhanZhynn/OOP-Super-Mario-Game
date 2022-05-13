package game.item;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

public class Key extends Item {
    /***
     * Constructor.
     */
    public Key() {
        super("key", '&', true);
        this.addCapability(Status.RESCUE_PRINCESS);
    }
}
