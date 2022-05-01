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

    public DormantKoopa() {
        super("Dormant Koopa", 'D', 1);
        this.addItemToInventory(new SuperMushroom());
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.getWeapon() instanceof Wrench) {
            actions.add(new DestroyShellAction(this, direction));
        }
        return actions;
    }
}
