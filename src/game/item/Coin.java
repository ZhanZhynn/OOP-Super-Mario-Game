package game.item;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actor.Player;
import game.interfaces.Resettable;
//created by Ng Zu Shen on 22/4/2022

/**
 * This class act as coins in the game. Player can pick up the coin to increase their wallet balance.
 * Different coin might have different value.
 * @author Ng Zu Shen
 * @version 1.0
 */
public class Coin extends Item implements Resettable {

    /**
     * value of the coin as an integer
     */
    private int value;
    private boolean isPickedUp;
    private boolean reset = false;

    /**
     * Constructor that only take in one parameter, which is the value of coin
     * @param value intended value of the coin as an integer
     */
    public Coin(int value){
        super("coin", '$', true);
        this.value = value;
        this.registerInstance();
    }

    /**
     * to check whether player reset the game and remove all coins on map.
     * @param currentLocation The location of each coin on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        //remove coin from all locations
        if (reset) {
            currentLocation.removeItem(this);
            reset = false;
        }
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        ((Player)actor).getWallet().increaseBalance(this.getValue());
        actor.removeItemFromInventory(this);
    }

    /**
     * getter of instance variable value
     * @return coin value as an integer
     */
    public int getValue(){return value;}


    @Override
    public void resetInstance() {
        reset = true;
    }
}

