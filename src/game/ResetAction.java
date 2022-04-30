package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class ResetAction extends Action {
    //class added by Zz

    public ResetAction(){
        ResetManager reseter = new ResetManager();
        reseter.run();
    }

    public String execute(Actor actor, GameMap map) {
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
