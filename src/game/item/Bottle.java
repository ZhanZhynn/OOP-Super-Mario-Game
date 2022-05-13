package game.item;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.action.ConsumeAction;
import game.interfaces.Consumable;
import game.interfaces.Water;

import java.util.ArrayDeque;
import java.util.Deque;

public class Bottle extends Item {

    private Deque<Water> this_bottle;
    //NEED TO FIND A WAY TO ADD DAMAGE, WHAT IS THE BASE DAMAGE?


//    /**
//     * Constructor of Bottle
//     * @param name name of this power star(can be anything but preferably "Power Star")
//     * @param displayChar Character to be display on map
//     * @param portable is this portable?
//     */
    public Bottle() {
        super("Bottle", 'b', false);
        this_bottle = new ArrayDeque<>();
    }

    public boolean newBottle(Actor actor){
        Bottle b = new Bottle();
        if (!actor.getInventory().contains(b)){
            actor.addItemToInventory(b);

        }
        return false;
    }

    public void consume(Actor actor, GameMap map){

        if (!this_bottle.isEmpty()) {
            Water fountainWater = this_bottle.removeLast();
            fountainWater.drink(actor);
        }
    }



    /**
     * append water into the stack
     */
    public void addFountainWater (Water fountainWater) {
        this_bottle.addLast(fountainWater);
    }


    public String addFountainWater(Actor actor, Water fountainWater){
        addFountainWater(fountainWater);
        return actor + " refills " + fountainWater;
    }

}
