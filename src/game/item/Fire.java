package game.item;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

/**
 * @author Ng Zu Shen
 */
public class Fire extends Item {

    /**
     * counter of 2 turns.
     */
    private int counter = 3;

    /***
     * Constructor.
     */
    public Fire() {
        super("Fire", 'v', false);
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        if (currentLocation.containsAnActor()){
            if (!currentLocation.getActor().hasCapability(Status.INVINCIBLE)) {
                currentLocation.getActor().hurt(20);
                System.out.println(currentLocation.getActor() + " burnt by fire. Decrease 20 hp.");
            }
        }
        if (counter == 0){
            currentLocation.removeItem(this);
        }
        counter -= 1;
    }
}
