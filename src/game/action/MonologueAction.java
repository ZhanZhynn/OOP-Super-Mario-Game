package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.item.Status;
import game.item.Wrench;
import game.monologue.Monologue;


public class MonologueAction extends Action {
    Monologue x = new Monologue();
    private final Actor speaksTo;

    /**
     * add three trade actions to allow player to trade three items.
     * @param speaksTo for constructor
     */

    public MonologueAction(Actor speaksTo){
        this.speaksTo = speaksTo;
    }

    /**
     * add three trade actions to allow player to trade three items.
     * @param actor the Actor that stands close to Toad
     * @param map        current GameMap
     * @return a line from toad randomly/depending on conditions
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        String talkBack = "";
        for (Item item : actor.getInventory()) {
            if (item.toString().equals(Wrench.name)) {
                talkBack += x.playerWithWrench();
            }
        }

        if (actor.hasCapability(Status.INVINCIBLE)) {
            talkBack += x.playerAtePS();
        }
        else {
            talkBack += x.allDialog();
        }

        return "toad :" + talkBack;
    }

    /**
     * add three trade actions to allow player to trade three items.
     * @param actor the Actor that stands close to Toad
     * @return a line to console menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " talks with " + speaksTo;
    }
}