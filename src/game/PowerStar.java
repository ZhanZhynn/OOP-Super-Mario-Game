package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.capabilities.CapabilitySet;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

import java.util.List;

public class PowerStar extends Item implements Sellable,Consumable{

    public static final int PRICE = 600;
    private int counter = 10;
    private boolean isConsumed = false;
    private Action consumeAction = new ConsumeAction(this);

    public PowerStar(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        this.addAction(consumeAction);
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        counter -= 1;
    }

    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation);
        counter -= 1;
    }

    public int getCounter(){return counter;}

    public void consume(Actor actor){
        isConsumed = true;
        counter = 10;
        addCapability(Status.INSTANT_KILL);
        addCapability(Status.PATH_OF_GOLD);
        addCapability(Status.INVINCIBLE);
        addCapability(Status.DESTROY_HIGH_GROUND);
        this.removeAction(consumeAction);
    }

    public void fade(){
        removeCapability(Status.INSTANT_KILL);
        removeCapability(Status.PATH_OF_GOLD);
        removeCapability(Status.INVINCIBLE);
        removeCapability(Status.DESTROY_HIGH_GROUND);
    }

    public Boolean getIsConsumed(){return isConsumed;}

    @Override
    public int getPrice() {
        return PRICE;
    }
}
