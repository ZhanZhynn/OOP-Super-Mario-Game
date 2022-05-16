package game.actor;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.action.SpeakAction;

/**
 * @author Ng Zu Shen
 * @version 1.0
 * Koopa behaves pretty similar to Goomba except for the stat different.
 * When Koopa dies, they got turn into DormantKoopa but that is in attack action class.
 */
public class Koopa extends WanderingEnemy {

    boolean speak = true;
    //instantiating an instance of SpeakAction here.
    SpeakAction speakAction = new SpeakAction();


    /**
     * Constructor.
     */
    public Koopa() {
        super("Koopa", 'K', 100);
    }

    public Koopa(String name, char displayChar, int hitPoints){
        super(name, displayChar, hitPoints);
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

        //for Koopa to speak every two turns.
        if (speak){
            System.out.println(this + ": " + speakAction.dialogKoopa());
        }
        speak = !speak;

        return super.playTurn(actions, lastAction, map, display);
    }

    public Weapon getWeapon() {
        return new IntrinsicWeapon(30+getPowerBuff()*15, "punches");
    }
}
