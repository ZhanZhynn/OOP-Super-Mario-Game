package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actor.Player;
import game.ground.Fountain;

/**
 * @author Ng Zu Shen
 * refill water to bottle
 */
public class RefillAction extends Action {

    /**
     * from from which fountain
     */
    private Fountain fountain;

    /**
     * constructor
     * @param fountain from which fountain
     */
    public RefillAction(Fountain fountain){
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
            return fountain.RefillBy(actor);
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
        return fountain.getAmountLeft() + "/10, Fill bottle with water";
    }
}
