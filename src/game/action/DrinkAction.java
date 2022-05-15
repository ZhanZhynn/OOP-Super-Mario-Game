package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ground.FountainZS;
import game.interfaces.CanDrinkFountain;
import game.interfaces.Drinkable;

public class DrinkAction extends Action {

    private FountainZS fountain;

    public DrinkAction(FountainZS fountainZS){
        fountain = fountainZS;
    }

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

    @Override
    public String menuDescription(Actor actor) {
        return fountain.getAmountLeft() +  "/10. Drink water from " + fountain;
    }
}
