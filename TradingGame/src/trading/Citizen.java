package trading;

import java.util.HashMap;
import java.util.Map;

/**
 * The constructor for Citizen should take a single parameter, 
 * an integer representing the number of gems, and should create 
 * a new Citizen with that many gems and an empty inventory
 * 
 * @author Harry Goodwin - 2557827G
 * 30/11/2020
 *
 */
public class Citizen {

	private int gemQuantity;
	private Map<Goods, Integer> inventory = new HashMap<Goods, Integer>();

	/**
	 * 
	 * @param gemQuantity
	 */
	public Citizen(int gemQuantity) {
		this.gemQuantity = gemQuantity;
	}

	public int getGems() {
		return gemQuantity;
	}

	/**
	 * returns the current amount of the indicated Goods type in 
	 * the inventory. Should return 0 if the Citizen does not have 
	 * any of the indicated Goods.
	 * @param goods
	 * @return integer quantity of said good
	 */
	public int getAmount (Goods goods) {

		for(Goods good : inventory.keySet()) {
			if(good.equals(goods)) {
				return inventory.get(goods);
			}
			else {
				return 0;
			}
		}
		return 0;
	}

	/**
	 * If the amount of gems is not enough, return false and do not change 
	 * anything.  Otherwise, update the amount of gems and the inventory 
	 * based on the details of the Trade and return true
	 * @param trade
	 * @return
	 */
	public boolean executeTrade (Trade trade) {

		if(this.gemQuantity>=trade.getGemsQuantity()) {
			this.gemQuantity -= trade.getGemsQuantity();
			
			//if inventory has goods, increase amount rather than add to quantity
			if(inventory.containsKey(trade.getGoodsType())){
				inventory.put(trade.getGoodsType(), 
						inventory.get(trade.getGoodsType())+trade.getTradeGoods());
			}
			else {
				inventory.put(trade.getGoodsType(), trade.getTradeGoods());
			}

			return true;

		}
		else {
			return false;
		}
	}

	/**
	 * @return the inventory
	 */
	public Map<Goods, Integer> getInventory() {
		return inventory;
	}
}
