package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import game.interfaces.Water;
import game.item.Status;

public class HealWater implements Water {
    @Override
    public void drink(Actor actor) {
        actor.heal(50);
    }

    public String toString(){
        return "Healing water";
    }
}
