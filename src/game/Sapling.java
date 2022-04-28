package game;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;

public class Sapling extends Tree{
    private int counter;

    public Sapling(){
        super('t');
        this.addCapability(Capabilities.SPAWN_COIN);
        counter = 0;
    }

    public void tick(Location location) {
        //grow into mature after 10 turns
        counter += 1;
        if (counter % 10 == 0) {
//            location.addActor(new Bug());
            location.setGround(new Mature());
        }

        if (this.hasCapability(Capabilities.SPAWN_COIN)) {
            //10% chance to spawn coin
            if (Math.random() <= 0.1) { //if already got coin, can spawn again?
                location.addItem(new Coin(20));
            }
        }


    }

}
