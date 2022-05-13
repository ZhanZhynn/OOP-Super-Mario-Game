package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.action.JumpAction;
import game.actor.FlyingKoopa;
import game.interfaces.Jumpable;
import game.actor.Player;
import game.interfaces.Resettable;
import game.item.Coin;
import game.item.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ng Zu shen
 * @version 1.0
 */
public abstract class Tree extends Ground implements Jumpable, Resettable {

    private boolean reset = false;
    private boolean resetTree = false;
    /**
     * used to keep track of when the tree should evolve or grow new sprout.
     */
    private int counter=0;
    /**
     * a list that contain all the location of neighbour dirt. randomly pick from this list to
     * grow sprout
     */
    private ArrayList<Location> dirt = new ArrayList<>();


//    public Tree() { //Zz: do I just use this as sprout class? or need a new class?
//        super('+');
//        this.addCapability(Capabilities.SPAWN_GOOMBA);
//    }
    /**
     * Constructor.
     *
     */
    public Tree(char displayChar) {
        super(displayChar);
        this.registerInstance();
        this.registerInstance();
    }

    /**
     * Tree has 50% chance of turning into dirt if player resets.
     */

    public void resetInstance(){ //50% chance to turn into dirt
        this.reset = true;
        if (Math.random() <0.5){
            this.resetTree = true;
        }
    }

    /**
     * Player can destroy highground if consumed Power Star
     */
    public boolean canActorEnter(Actor actor) {
        if(actor.hasCapability(Status.DESTROY_HIGH_GROUND) || actor instanceof FlyingKoopa){
            return true;
        }
        return false;
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
        if (this.reset){
            if (this.resetTree){
                location.setGround((new Dirt()));
            }
            List<Item> items = location.getItems();
        }
        this.reset = false;

//        counter+=1;
//        if (counter % 10 == 0){
//            location.setGround(new Sapling());
////            this.setDisplayChar('t');
////            this.removeCapability(Capabilities.SPAWN_GOOMBA);
////            this.addCapability(Capabilities.SPAWN_COIN);
////        }else if (counter==20){
////            this.setDisplayChar('T');
////            this.removeCapability(Capabilities.SPAWN_COIN);
////            this.addCapability(Capabilities.SPAWN_KOOPA);
////            this.addCapability(Capabilities.GROW_SPROUT);
//        }
//        if (!location.containsAnActor()) {
//            if (this.hasCapability(Capabilities.SPAWN_GOOMBA)) {
//                //10% chance to spawn Goomba if actor is not standing on it
////                boolean spawnGoomba = new Random().nextInt(10) == 0; //how was this work?
////                if (spawnGoomba) {
//                if (Math.random() <= 0.1)
//                    location.addActor(new Goomba());
//                }
//            }
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
    /**
     * getter to get the dirt locations
     */
    public ArrayList<Location> getDirt(){
        //
        return this.dirt;
    }

    /**
     * Function for Mario to jump.
     * There is a 90% success rate, if fail, there will be a 10 damage incurred.
     * If jump is successful, a message will be printed to prompt the player, if fail, another message will be printed.
     * @param by is the actor
     * @param at is the location of where the player jumped over.
     */

    public String jumped(Actor by, Location at) {
        Actor actor = by;
        Location location = at;
        if(Math.random() <= 0.9) {
            location.map().moveActor(actor, location);
            return actor + " jumped over " +  location +"(" + location.x() + ","+ location.y() + ")"  + " successfully.";
        }
        else {
            int damage = 10;
            actor.hurt(damage);
            return actor + " fell from Sprout. Received " + damage + " damage.";
        }
    }
    /**
     * At the moment, we only make it can be attacked by Player.
     * You can do something else with this method.
     * @param otherActor the Actor that might perform an action.
     * @param location location of tree
     * @param direction  String representing the direction of the other Actor
     * @return list of actions
     */
    public ActionList allowableActions(Actor otherActor, Location location, String direction) {
        ActionList actions = new ActionList();
        if(otherActor instanceof Player && !location.containsAnActor()) {
            if (!otherActor.hasCapability(Status.DESTROY_HIGH_GROUND)) {
                actions.add(new JumpAction(this, location, direction));
            }
        }
        return actions;
    }


    public String toString() {
        return "Sprout";
    }
}
