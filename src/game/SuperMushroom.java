package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

import java.util.List;

public class SuperMushroom extends Item implements Sellable,Consumable{

    public final static int PRICE = 400;
    private Boolean isConsumed = false;
    private Action consumeAction = new ConsumeAction(this);

    public SuperMushroom(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        this.addAction(consumeAction);
    }

    public Boolean getIsConsumed(){return isConsumed;}

    @Override
    public int getPrice() {
        return PRICE;
    }

    @Override
    public void consume(Actor actor) {
        isConsumed = true;
        addCapability(Status.TALL);
        addCapability(Status.GUARANTEED_JUMP);
        actor.increaseMaxHp(50);
        this.removeAction(consumeAction);

    }

    public void fade(){
        removeCapability(Status.TALL);
        removeCapability(Status.GUARANTEED_JUMP);
    }
}
