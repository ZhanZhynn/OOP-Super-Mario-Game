package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;

/**
 * Class representing the Player.
 */
public class Player extends Actor  {

	private final Menu menu = new Menu();
	private Wallet wallet = new Wallet("wallet", 'w', false, 600);
	private int lastRoundHp = getMaxHp();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
	}

	public int getCurrentHp(){
		String s = printHp();
		s = s.substring(s.indexOf("(") + 1);
		s = s.substring(0, s.indexOf("/"));
		return Integer.parseInt(s);
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		for (int i = 0; i<getInventory().size(); i++){
			Item item = getInventory().get(i);
			if (item instanceof PowerStar){
				PowerStar ps = (PowerStar) item;
				if (ps.getCounter() == 0){
					ps.fade();
					removeItemFromInventory(item);
				}
			}else if (item instanceof SuperMushroom){
				SuperMushroom sm = (SuperMushroom) item;
				if(sm.getIsConsumed()) {
					if (getCurrentHp() < lastRoundHp) {
						sm.fade();
						removeItemFromInventory(item);
					}
				}
			}else if (item instanceof Coin){
				Coin coin = (Coin) item;
				wallet.increaseBalance(coin.getValue());
				removeItemFromInventory(item);
			}
		}
		lastRoundHp = getCurrentHp();
		if (map.locationOf(this).getGround() instanceof Wall){
			map.locationOf(this).setGround(new Dirt());
			map.locationOf(this).addItem(new Coin(5));
		}
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		System.out.println(this+this.printHp() +" at "+map.locationOf(this));
		System.out.println("Wallet: "+this.getWallet().getBalance());

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

	public Wallet getWallet() {
		return wallet;
	}
}
