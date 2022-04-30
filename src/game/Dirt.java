package game;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import java.util.List;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground implements Resettable {
	private boolean reset = false;

	public Dirt() {
		super('.');
		this.registerInstance();
	}

	public void resetInstance(){
		this.reset = true;
	}

//	public void tick(Location location) {
//		if (this.reset){
//			List<Item> items = location.getItems();
//			if (items.size() >0) {
//				for (Item item : items) {
//					System.out.println(item);
//					if (item instanceof Coin) {
//						location.removeItem(item);
//					}
//				}
//			}
//		}
//		this.reset = false;
//	}

}
