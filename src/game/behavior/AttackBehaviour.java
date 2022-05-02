package game.behavior;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.action.AttackAction;
import game.actor.Player;

/**
 * @author Ng Zu Shen
 * @version 1.0
 * if got player nearby, attack the players.
 */
public class AttackBehaviour implements Behaviour {

    /**
     * damage of the weapon
     */
    int damage;

    /**
     * what action to get if got player nearby
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return attack action or null depending whether the player is around
     */
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

    /**
     * what should be printed if this happened
     * @param actor
     * @return description of what happened
     */
    public String menuDescription(Actor actor) {
        return "Kick player";
    }

    /**
     * print after executed
     * @param actor that actor that has this behaviours
     * @param map
     * @return the description
     */
    public String execute(Actor actor, GameMap map) {
        return menuDescription(actor);
    }
}
