package game.item;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.action.ConsumeAction;
import game.interfaces.Consumable;

import java.util.Stack;

public class Bottle extends Item implements Consumable {


    // Creating a Stack
    Stack<Integer> waterStack = new Stack<>();


    //NEED TO FIND A WAY TO ADD DAMAGE, WHAT IS THE BASE DAMAGE?



    /**
     * Constructor of Bottle
     * @param name name of this power star(can be anything but preferably "Power Star")
     * @param displayChar Character to be display on map
     * @param portable is this portable?
     */
    public Bottle(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        this.addAction(new ConsumeAction(this));
        //this.registerInstance();
    }

    /**
     * whether is this power star is consumed
     */
    private boolean isConsumed = false;

    public void consume(Actor actor, GameMap map){
        isConsumed = true;
        //actor.addCapability(Status.POWER_UP);
        actor.heal(200);
        this.removeAction(new ConsumeAction(this));
    }



    /**
     * has the item been consumed?
     *
     * @return whether the item has been consumed
     */
    public Boolean getIsConsumed(){return isConsumed;}

}
