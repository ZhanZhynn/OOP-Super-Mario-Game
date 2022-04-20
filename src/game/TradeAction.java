package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

public class TradeAction extends Action {

    private Actor target;
    private String direction;
    private Sellable item;

    public TradeAction(Actor toad, String direction, Sellable item){
        this.target = toad;
        this.direction = direction;
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Player p = (Player) actor;
        if (p.getWallet().getBalance()>=item.getPrice()){
            p.getWallet().decreaseBalance(item.getPrice());
            Item i = (Item) item;
            p.addItemToInventory(i);
            return i.toString() + " bought";
        }else{
            return "insufficient money";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        String res= actor + " buys "+item.toString()+" ($" + item.getPrice()+")";
        return res;
    }
}
