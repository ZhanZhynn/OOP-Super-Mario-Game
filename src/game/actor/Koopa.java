package game.actor;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
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

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

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
