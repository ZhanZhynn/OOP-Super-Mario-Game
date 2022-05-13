package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actor.Player;
import game.ground.FountainZS;
import game.interfaces.Consumable;
import game.interfaces.WaterZS;
import game.item.BottleZS;
import game.item.PowerWaterZS;

public class RefillActionZS extends Action {

    private Consumable water;
    private FountainZS fountain;

    public RefillActionZS(Consumable water, FountainZS fountain){
        this.water = water;
        this.fountain = fountain;
    }

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

    @Override
    public String menuDescription(Actor actor) {
        return fountain.getAmountLeft() + "/10, Fill bottle with " + water;
    }
}
