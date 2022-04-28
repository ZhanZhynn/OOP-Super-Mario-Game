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
	/**
	 * Player's wallet
	 */
	private Wallet wallet = new Wallet(600);
	/**
	 * Helper instance variable to know when player got hurt in a round.
	 */
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

	//ZuShen
	/**
	 * this method is to get the current hp of player
	 * @return current hp as integer
	 */
	public int getCurrentHp(){
		String s = printHp();
		s = s.substring(s.indexOf("(") + 1);
		s = s.substring(0, s.indexOf("/"));
		return Integer.parseInt(s);
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		//Added by Ng Zu Shen on 21/4/2022--------------------------------------------
		for (int i = 0; i<getInventory().size(); i++){
			Item item = getInventory().get(i);
			if (item instanceof PowerStar){
				PowerStar ps = (PowerStar) item;
				if (ps.getCounter() == 0){
					//ps.fade();
					removeItemFromInventory(item);
				}
			}else if (item instanceof SuperMushroom){
				SuperMushroom sm = (SuperMushroom) item;
				if(sm.getIsConsumed()) {
					if (getCurrentHp() < lastRoundHp) {
						//sm.fade();
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

		//Zz: bug over here. Removed. What is this for?
//		if (map.locationOf(this).getGround() instanceof Wall){
//			map.locationOf(this).setGround(new Dirt());
//			map.locationOf(this).addItem(new Coin(5));
//		}
		//NgZuShen\-------------------------------------------------------------------

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		//Added by NgZuShen on 21/4/2022------------------------------------------------
		System.out.println(this+this.printHp() +" at "+map.locationOf(this));
		System.out.println("Wallet: "+this.getWallet().getBalance());
		//NgZuShen\--------------------------------------------------------------------

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

	//ZuShen
	/**
	 * getter for player's wallet
	 * @return Wallet object of this player
	 */
	public Wallet getWallet() {
		return wallet;
	}
}
