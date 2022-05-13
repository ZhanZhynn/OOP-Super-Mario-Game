package game.item;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

public class Fire extends Item {

    private int counter = 3;

    /***
     * Constructor.
     */
    public Fire() {
        super("Fire", '@', false);
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        if (currentLocation.containsAnActor()){
            currentLocation.getActor().hurt(20);
            System.out.println(currentLocation.getActor() + " burnt by fire. Decrease 20 hp.");
        }
        if (counter == 0){
            currentLocation.removeItem(this);
        }
        counter -= 1;
    }
}
