package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actor.Player;
import game.ground.FountainZS;
import game.interfaces.Consumable;

/**
 * @author Ng Zu Shen
 * refill water to bottle
 */
public class RefillActionZS extends Action {

    /**
     * water to be put inside a bottle
     */
    private Consumable water;
    /**
     * from from which fountain
     */
    private FountainZS fountain;

    /**
     * constructor
     * @param water the water to get
     * @param fountain from which fountain
     */
    public RefillActionZS(Consumable water, FountainZS fountain){
        this.water = water;
        this.fountain = fountain;
    }

    /**
     * if fountain has enough water. can refill.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor instanceof Player && fountain.enoughWater()){
            Player player = (Player) actor;
            player.getBottleZS().addWater(water);
            fountain.used();
            return "bottle is filled with " + water;
        }
        return fountain + " has dried out. Come back in " + (fountain.getCooldown()+1) + " turns";
    }

    /**
     * description of this action
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return fountain.getAmountLeft() + "/10, Fill bottle with " + water;
    }
}
