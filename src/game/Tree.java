package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Ng Zu shen
 * @version 1.0
 */
public class Tree extends Ground implements Jumpable{

    /**
     * used to keep track of when the tree should evolve or grow new sprout.
     */
    private int counter=0;
    /**
     * a list that contain all the location of neighbour dirt. randomly pick from this list to
     * grow sprout
     */
    private ArrayList<Location> dirt = new ArrayList<>();

    /**
     * Constructor.
     *
     */
    public Tree() { //Zz: do I just use this as sprout class? or need a new class?
        super('+');
        this.addCapability(Capabilities.SPAWN_GOOMBA);
    }

    public Tree(char displayChar) {
        super(displayChar);
    }

    /**
     * check whether a location is in range of map and whether is it a dirt, if yes
     * add to dirt arraylist
     * @param location location of the tree, also used to get the map
     * @param x x coordinate of neighbour location
     * @param y y coordinate of neighbour location
     */
    public void addNeighbour(Location location, int x, int y){
        if (location.map().getXRange().contains(x) && location.map().getYRange().contains(y)) {
            Location temp = location.map().at(x,y);
            if (temp.getGround() instanceof Dirt) {
                dirt.add(temp);
            }
        }
    }

    /**
     * check all 8 possible exits (neighbours) to see whether is it a dirt
     * @param location location of this tree
     */
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

    /**
     * handle all the behaviour of trees as stated in assignment specification
     * @param location The location of the Ground
     */
    public void tick(Location location){
        counter+=1;
        if (counter % 10 == 0){
            location.setGround(new Sapling());
//            this.setDisplayChar('t');
//            this.removeCapability(Capabilities.SPAWN_GOOMBA);
//            this.addCapability(Capabilities.SPAWN_COIN);
//        }else if (counter==20){
//            this.setDisplayChar('T');
//            this.removeCapability(Capabilities.SPAWN_COIN);
//            this.addCapability(Capabilities.SPAWN_KOOPA);
//            this.addCapability(Capabilities.GROW_SPROUT);
        }
        if (!location.containsAnActor()) {
            if (this.hasCapability(Capabilities.SPAWN_GOOMBA)) {
                //10% chance to spawn Goomba if actor is not standing on it
//                boolean spawnGoomba = new Random().nextInt(10) == 0; //how was this work?
//                if (spawnGoomba) {
                if (Math.random() <= 0.1)
                    location.addActor(new Goomba());
                }
            }
        }

//            else if (this.hasCapability(Capabilities.SPAWN_COIN)) {
//                //10% chance
//                boolean spawnCoin = new Random().nextInt(10) == 0;
//                if (spawnCoin) {
//                    location.addItem(new Coin(20));
//                }
//            } else if (this.hasCapability(Capabilities.SPAWN_KOOPA)) {
//                //15% chance
//                int random = new Random().nextInt(20);
//                boolean spawnKoopa = random == 0 || random == 1 || random == 2; //what is this?
//                if (spawnKoopa) {
//                    location.addActor(new Koopa());
//                }
//            }
//        }
        //grow sprout at random neighbour dirt
//        if (this.hasCapability(Capabilities.GROW_SPROUT) && this.counter%5==0) {
//            addDirtNeighbour(location);
//            if (dirt.size() != 0) {
//                Random ran = new Random();
//                int randomNumber = ran.nextInt(dirt.size());
//                Location grow = dirt.get(randomNumber);
//                grow.setGround(new Tree());
//            }
//        }
//        if (this.hasCapability(Capabilities.SPAWN_KOOPA)){
//            //20% chance
//            boolean wither = new Random().nextInt(5) == 0;  //Zz: why is new Random().nextInt(5) == 0 and not <= 2?
//            if (wither) {
//                location.setGround(new Dirt());
//            }
//        }
//    }
    public ArrayList<Location> getDirt(){
        //getter to get the dirt locations
        return this.dirt;
    }

    public String jumped(Actor by, Location at) {
        Actor actor = by;
        Location location = at;
        if(Math.random() <= 0.9) {
            location.map().moveActor(actor, location);
            return actor + " jumped over Mature successfully.";
        }
        else {
            int damage = 10;
            actor.hurt(damage);
            return actor + " fell from Sprout. Received " + damage + " damage.";
        }
    }

    public ActionList allowableActions(Actor otherActor, Location location, String direction) {
        ActionList actions = new ActionList();
        if(otherActor instanceof Player) {
            actions.add(new JumpAction(this, location, direction));
        }
        return actions;
    }
}
