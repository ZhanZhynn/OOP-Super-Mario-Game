package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;

public class Coin extends Item {

    private int value;

    public Coin(int value){
        super("coin", '$', true);
        this.value = value;
    }

    public int getValue(){return value;}

    public PickUpItemAction getPickUpAction(Actor actor) {
        PickUpItemAction res = super.getPickUpAction(actor);
        togglePortability();
        return res;
    }
}
