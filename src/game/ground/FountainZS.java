package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.action.RefillActionZS;
import game.item.PowerWaterZS;

public class FountainZS extends Ground {

    public static final int CAPACITY = 10;
    private int amountLeft = 10;
    private int cooldown = 5;

    /**
     * Constructor.
     */
    public FountainZS(char displaychar) {
        super(displaychar);
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        if (cooldown == 0){
            replenish();
            cooldown = 5;
        }
        if (isEmpty()){
            cooldown -= 1;
        }
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getAmountLeft() {
        return amountLeft;
    }

    public void used(){
        amountLeft -= 5;
    }

    public boolean isEmpty(){
        return amountLeft == 0;
    }

    public void replenish(){
        amountLeft = CAPACITY;
    }

    public Boolean enoughWater(){
        return amountLeft >= 5;
    }
}
