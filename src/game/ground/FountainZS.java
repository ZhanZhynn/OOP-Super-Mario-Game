package game.ground;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * @author Ng Zu Shen
 * this class can be abstarct as well. A base class of fountain
 */
public class FountainZS extends Ground {

    /**
     * capacity of a fountain
     */
    public static final int CAPACITY = 10;
    /**
     * how much water left in this fountain
     */
    private int amountLeft = 10;
    /**
     * 5 round counter when the water runs out
     */
    private int cooldown = 5;

    /**
     * Constructor.
     */
    public FountainZS(char displaychar) {
        super(displaychar);
    }

    /**
     * start cooldown when fountain is empty
     * @param location The location of the Ground
     */
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

    /**
     * cooldown time
     * @return
     */
    public int getCooldown() {
        return cooldown;
    }

    /**
     * amount of water left
     * @return
     */
    public int getAmountLeft() {
        return amountLeft;
    }

    /**
     * 1 drink or refill will cost 5 amount
     */
    public void used(){
        amountLeft -= 5;
    }

    /**
     * is the fountain empty
     * @return
     */
    public boolean isEmpty(){
        return amountLeft == 0;
    }

    /**
     * refill the fountain
     */
    public void replenish(){
        amountLeft = CAPACITY;
    }

    /**
     * does the fountain has more than 5 water
     * @return
     */
    public Boolean enoughWater(){
        return amountLeft >= 5;
    }
}
