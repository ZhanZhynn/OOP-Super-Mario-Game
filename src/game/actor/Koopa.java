package game.actor;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.Weapon;

/**
 * @author Ng Zu Shen
 * @version 1.0
 * Koopa behaves pretty similar to Goomba except for the stat different.
 * When Koopa dies, they got turn into DormantKoopa but that is in attack action class.
 */
public class Koopa extends WanderingEnemy {


    /**
     * Constructor.
     */
    public Koopa() {
        super("Koopa", 'K', 100);
    }

    public Koopa(String name, char displayChar, int hitPoints){
        super(name, displayChar, hitPoints);
    }

    public Weapon getWeapon() {
        return new IntrinsicWeapon(30+getPowerBuff()*15, "punches");
    }
}
