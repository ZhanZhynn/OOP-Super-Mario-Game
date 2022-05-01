package game.actor;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.action.ResetAction;
import game.ground.Dirt;
import game.interfaces.Destroyable;
import game.interfaces.Resettable;
import game.item.*;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Resettable {

	private final Menu menu = new Menu();
	/**
	 * Player's wallet
	 */
	private Wallet wallet = new Wallet(1200);
	/**
	 * Helper instance variable to know when player got hurt in a round.
	 */
	private int lastRoundHp = getMaxHp();

	private boolean reset = false;

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
		this.registerInstance();
	}

	public void resetInstance(){ //50% chance to turn into dirt
		this.reset = true;
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
	public void addItemToInventory(Item item) {
		if (item instanceof Coin){
			item.togglePortability();
		}
		super.addItemToInventory(item);
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		actions.add(new ResetAction());
		//reset for player capabilities not working.
		if (this.reset) {
			this.resetMaxHp(this.getMaxHp());
//			this.removeCapability(Status.INSTANT_KILL);
//			this.removeCapability(Status.PATH_OF_GOLD);
//			this.removeCapability(Status.INVINCIBLE);
//			this.removeCapability(Status.DESTROY_HIGH_GROUND);
//			this.removeCapability(Status.TALL);
//			this.removeCapability(Status.GUARANTEED_JUMP);
//			this.addCapability(Status.HOSTILE_TO_ENEMY);
		}

		//Added by Ng Zu Shen on 21/4/2022--------------------------------------------
		for (int i = 0; i<getInventory().size(); i++){ //please comment this block of code
			Item item = getInventory().get(i);
			if (item instanceof PowerStar){
				PowerStar ps = (PowerStar) item;
				if (ps.getCounter() == 0){
					//ps.fade();
					removeItemFromInventory(item);
				}
				if (ps.getIsConsumed()){
					if (this.reset) {
						removeItemFromInventory(item);
					}
				}
			}else if (item instanceof SuperMushroom){
				SuperMushroom sm = (SuperMushroom) item;
				if(sm.getIsConsumed()) {
					if (getCurrentHp() < lastRoundHp || this.reset) {
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

		if (map.locationOf(this).getGround() instanceof Destroyable){
			if(hasCapability(Status.DESTROY_HIGH_GROUND)){
				map.locationOf(this).setGround(new Dirt());
				map.locationOf(this).addItem(new Coin(5));}
		}

		//NgZuShen\-------------------------------------------------------------------

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		//Added by NgZuShen on 21/4/2022------------------------------------------------
		System.out.println(this+this.printHp() +" at "+map.locationOf(this));
		System.out.println("Wallet: "+this.getWallet().getBalance());
		//NgZuShen\--------------------------------------------------------------------

//		this.reset = false;

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

//	@Override
//	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
//		ActionList actions = new ActionList();
//		// it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
//		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
//			actions.add(new AttackAction(this,direction));
//		}
//		return actions;
//	}

	@Override
	public char getDisplayChar(){
		char character = this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();

		if (this.reset){
			character = super.getDisplayChar();
			return character;
		}
		this.reset = false;

		if (getCurrentHp() < lastRoundHp) {
			character = super.getDisplayChar();
		}
		//this else if statement is to deal with a specific situation when we consume supermushroom and got attack at the same round
		//might not be the best way to do it but it works
		else if (getCurrentHp() - lastRoundHp < 50 && getCurrentHp() > lastRoundHp){
			character = super.getDisplayChar();
		}

		return character;
		//return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
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