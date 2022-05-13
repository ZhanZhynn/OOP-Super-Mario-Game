package game.actor;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.action.MonologueAction;
import game.action.TradeAction;
import game.item.PowerStar;
import game.item.SuperMushroom;
import game.item.Wrench;
//created by Ng Zu Shen on 20/4/2022

/**
 * Toad that will trigger the trade action when in range of player.
 * @author Ng Zu Shen
 * @version 1.0
 */
public class Toad extends Actor {

    /**
     * constructor
     */
    public Toad() {
        super("toad", 'O', 0);
    }

    /**
     * What does toad do in each turn?
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return do nothing action because toad do nothing each turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * add three trade actions to allow player to trade three items.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of trade action
     * Added monologueAction to the list for toad to speak.
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        actions.add(new ActionList(new MonologueAction(this)));
        if(otherActor instanceof Player) {
            actions.add(new TradeAction(new PowerStar("Power Star", '*', false)));
            actions.add(new TradeAction(new SuperMushroom()));
            actions.add(new TradeAction(new Wrench("Wrench", '|', true)));
        }

        return actions;
    }

}
