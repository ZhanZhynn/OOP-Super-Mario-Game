package game.actor;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.action.SpeakAction;

public class FlyingKoopa extends Koopa {

    boolean speak = true;
    //instantiating an instance of SpeakAction here.
    SpeakAction speakAction = new SpeakAction();

    public FlyingKoopa(){
        super("Flying Koopa", 'F', 150);
    }

    /**
     * Figure out what to do next.
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     *  @param actions    collection of possible Actions for this Actor
     *  @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     *  @param map        the map containing the Actor
     *  @param display    the I/O object to which messages may be written
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        //for Flying Koopa to speak every two turns.
        if (speak){
            System.out.println(this + ": " + speakAction.dialogFlyingKoopa());
        }
        speak = !speak;

        return super.playTurn(actions, lastAction, map, display);
    }
}
