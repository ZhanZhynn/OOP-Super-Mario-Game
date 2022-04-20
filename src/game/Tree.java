package game;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;
import java.util.Random;

public class Tree extends Ground {

    private int counter=0;
    private ArrayList<Location> dirt = new ArrayList<>();

    /**
     * Constructor.
     *
     */
    public Tree() {
        super('+');
        this.addCapability(Capabilities.SPAWN_GOOMBA);
    }

    public void addNeighbour(Location location, int x, int y){
        if (location.map().getXRange().contains(x) && location.map().getYRange().contains(y)) {
            Location temp = location.map().at(x,y);
            if (temp.getGround() instanceof Dirt) {
                dirt.add(temp);
            }
        }
    }

    public void addDirtNeighbour(Location location){
        dirt = new ArrayList<Location>();
        addNeighbour(location, location.x(), location.y() - 1);
        addNeighbour(location, location.x() + 1, location.y() - 1);
        addNeighbour(location, location.x() + 1, location.y());
        addNeighbour(location, location.x() + 1, location.y() + 1);
        addNeighbour(location, location.x(), location.y() + 1);
        addNeighbour(location, location.x() - 1, location.y() + 1);
        addNeighbour(location, location.x() - 1, location.y());
        addNeighbour(location, location.x() - 1, location.y() - 1);

    }

    public void tick(Location location){
        counter+=1;
        if (counter==10){
            this.setDisplayChar('t');
            this.removeCapability(Capabilities.SPAWN_GOOMBA);
            this.addCapability(Capabilities.SPAWN_COIN);
        }else if (counter==20){
            this.setDisplayChar('T');
            this.removeCapability(Capabilities.SPAWN_COIN);
            this.addCapability(Capabilities.SPAWN_KOOPA);
            this.addCapability(Capabilities.GROW_SPROUT);
        }
        if (!location.containsAnActor()) {
            if (this.hasCapability(Capabilities.SPAWN_GOOMBA)) {
                boolean spawnGoomba = new Random().nextInt(10) == 0;
                if (spawnGoomba) {
                    location.addActor(new Goomba());
                }
            } else if (this.hasCapability(Capabilities.SPAWN_COIN)) {
                boolean spawnCoin = new Random().nextInt(10) == 0;
                if (spawnCoin) {
                    location.addItem(new Coin(20));
                }
            } else if (this.hasCapability(Capabilities.SPAWN_KOOPA)) {
                int random = new Random().nextInt(20);
                boolean spawnKoopa = random == 0 || random == 1 || random == 2;
                if (spawnKoopa) {
                    location.addActor(new Koopa());
                }
            }
        }
        if (this.hasCapability(Capabilities.GROW_SPROUT) && this.counter%5==0) {
            addDirtNeighbour(location);
            if (dirt.size() != 0) {
                Random ran = new Random();
                int randomNumber = ran.nextInt(dirt.size());
                Location grow = dirt.get(randomNumber);
                grow.setGround(new Tree());
            }
        }
        if (this.hasCapability(Capabilities.SPAWN_KOOPA)){
            boolean wither = new Random().nextInt(5) == 0;
            if (wither) {
                location.setGround(new Dirt());
            }
        }

    }
}
