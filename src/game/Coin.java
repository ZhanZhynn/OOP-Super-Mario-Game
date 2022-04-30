package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
//created by Ng Zu Shen on 22/4/2022

/**
 * This class act as coins in the game. Player can pick up the coin to increase their wallet balance.
 * Different coin might have different value.
 * @author Ng Zu Shen
 * @version 1.0
 */
public class Coin extends Item{

    /**
     * value of the coin as an integer
     */
    private int value;
    private boolean isPickedUp;
    private boolean reset;

    /**
     * Constructor that only take in one parameter, which is the value of coin
     * @param value intended value of the coin as an integer
     */
    public Coin(int value){
        super("coin", '$', true);
        this.value = value;
    }

    /**
     * getter of instance variable value
     * @return coin value as an integer
     */
    public int getValue(){return value;}


}

