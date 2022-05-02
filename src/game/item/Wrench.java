package game.item;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.interfaces.Sellable;

/**
 * @author Ng Zu Shen
 * @version 1.0
 * this class implementing wrench as a sellable weapon
 */
public class Wrench extends Item implements Sellable, Weapon {

    /**
     * price of wrench sold by toad
     */
    public static final int price = 200;
    /**
     * name of this item
     */
    public static final String name = "Wrench";

    /**
     * constructor
     * @param name name
     * @param displayChar display character
     * @param portable is this portable?
     */
    public Wrench(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    /**
     * getter of price
     * @return the price
     */
    @Override
    public int getPrice() {
        return price;
    }

    /**
     * damage of this weapon
     * @return
     */
    @Override
    public int damage() {
        return 50;
    }

    /**
     * return the verb that describes how this weapon is used
     * @return verb as String
     */
    @Override
    public String verb() {
        return "whack";
    }

    /**
     * return the chance of hitting in percentage
     * @return chance as integer
     */
    @Override
    public int chanceToHit() {
        return 80;
    }
}
