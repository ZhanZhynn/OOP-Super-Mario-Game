package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ground.FountainZS;
import game.interfaces.CanDrinkFountain;

public class DrinkAction extends Action {

    private FountainZS fountain;

    public DrinkAction(FountainZS fountainZS){
        fountain = fountainZS;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        fountain.used();
        CanDrinkFountain temp = (CanDrinkFountain) actor;
        temp.incrementPowerBuff();
        return actor + " drank water from "+ fountain;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "drink water from " + fountain;
    }
}
