package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import game.interfaces.Water;
import game.item.Status;

public class PowerWater implements Water {
    @Override
    public void drink(Actor actor) {
        actor.addCapability(Status.DMG_UP);
    }

    public String toString(){
        return "Power water";
    }
}
