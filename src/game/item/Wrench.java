package game.item;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.interfaces.Sellable;

public class Wrench extends Item implements Sellable, Weapon {

    public static final int price = 200;

    public Wrench(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int damage() {
        return 50;
    }

    @Override
    public String verb() {
        return "whack";
    }

    @Override
    public int chanceToHit() {
        return 80;
    }
}
