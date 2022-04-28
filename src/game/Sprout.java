package game;

import edu.monash.fit2099.engine.positions.Location;

public class Sprout extends Tree {
    private int counter;

    public Sprout(){
        super('+');
        this.addCapability(Capabilities.SPAWN_GOOMBA);
        counter = 0;
    }

    public void tick(Location location){
        counter+=1;
        if (counter % 10 == 0){
            location.setGround(new Sapling());
        }
        if (!location.containsAnActor()) {
            if (this.hasCapability(Capabilities.SPAWN_GOOMBA)) {
                if (Math.random() <= 0.1)
                    location.addActor(new Goomba());
            }
        }
    }

}
