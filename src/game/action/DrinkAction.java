package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ground.Fountain;
import game.interfaces.CanDrinkFountain;

public class DrinkAction extends Action {

    private Fountain fountain;

    /**
     * constructor for DrinkAction.
     */
    public DrinkAction(Fountain fountain){
        this.fountain = fountain;
    }

    /**
     * actor drinks from fountain if not yet dried out.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return description of what happened as a String
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (fountain.enoughWater()) {
            fountain.drank(actor);
            CanDrinkFountain temp = (CanDrinkFountain) actor;
            temp.incrementPowerBuff();
            return actor + " drank water from " + fountain;
        }
        return fountain + " has dried out. Come back in " + (fountain.getCooldown()+1) + " turns";
    }

    /**
     * description of what this action does
     * @param actor The actor performing the action.
     * @return result as a String
     */
    @Override
    public String menuDescription(Actor actor) {
        return fountain.getAmountLeft() +  "/10. Drink water from " + fountain;
    }
}
