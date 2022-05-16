package game.actor;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.action.SpeakAction;

public class FlyingKoopa extends Koopa {

    boolean speak = true;
    SpeakAction speakAction = new SpeakAction();

    public FlyingKoopa(){
        super("Flying Koopa", 'F', 150);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        if (speak){
            System.out.println(this + ": " + speakAction.dialogFlyingKoopa());
        }
        speak = !speak;

        return super.playTurn(actions, lastAction, map, display);
    }
}
