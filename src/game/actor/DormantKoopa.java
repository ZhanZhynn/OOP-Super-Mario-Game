package game.actor;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.item.SuperMushroom;
import game.item.Wrench;
import game.action.DestroyShellAction;

public class DormantKoopa extends Actor {

    /**
     * constructor of this class with predefined inputs
     */
    public DormantKoopa() {
        super("Dormant Koopa", 'D', 1);
        this.addItemToInventory(new SuperMushroom());
    }

    /**
     * what to do next?
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * only allowed destroy shell action
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.getWeapon() instanceof Wrench) {
            actions.add(new DestroyShellAction(this, direction));
        }
        return actions;
    }
}
