package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Sellable;
import game.actor.Player;

/**
 * trade action class, triggered when next to toad
 * @author Ng Zu Shen
 * @version 1.0
 */
public class TradeAction extends Action {

    /**
     * the item to be traded
     */
    private Sellable item;

    /**
     * constructor of this class
     * @param item sellable item
     */
    public TradeAction(Sellable item){
        this.item = item;
    }

    /**
     * if actor has sufficient balance, decrease the balance of wallet and add the item to buyer
     * inventory, else return a string to prompt player the trade is not done
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string that indicate whether the trade is done or not.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player p = (Player) actor;
        if (p.getWallet().getBalance()>=item.getPrice()){
            p.getWallet().decreaseBalance(item.getPrice());
            Item i = (Item) item;
            p.addItemToInventory(i);
            return i.toString() + " bought";
        }else{
            return "insufficient money, failed to buy";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        String res= actor + " buys "+item.toString()+" ($" + item.getPrice()+")";
        return res;
    }
}
