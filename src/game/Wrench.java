package game;

import edu.monash.fit2099.engine.items.Item;

public class Wrench extends Item implements Sellable{

    public static final int price = 200;

    public Wrench(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    @Override
    public int getPrice() {
        return price;
    }
}
