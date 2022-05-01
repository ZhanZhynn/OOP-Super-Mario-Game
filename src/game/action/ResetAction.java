package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.resetmanager.ResetManager;

public class ResetAction extends Action {
    //class added by Zz

    public ResetAction(){
    }

    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run();
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " resets game";
    }

    @Override
    public String hotkey() {
        return "r";
    }


}
