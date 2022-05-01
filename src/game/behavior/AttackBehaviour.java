package game.behavior;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.action.AttackAction;
import game.actor.Player;

public class AttackBehaviour implements Behaviour {

    int damage;

    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            if (exit.getDestination().containsAnActor()){
                if(exit.getDestination().getActor() instanceof Player){
                    damage = actor.getWeapon().damage();
                    return new AttackAction(exit.getDestination().getActor(), exit.getName());
                }
            }
        }
        return null;
    }

    public String menuDescription(Actor actor) {
        return "Kick player";
    }

    public String execute(Actor actor, GameMap map) {
        return menuDescription(actor);
    }
}
