package trading;

import java.util.LinkedList;

/**
 * Trader representing a trader in the world. Traders have a 
 * list of trades that can be used.
 * 
 * @author Harry Goodwin - 2557827G
 * 30/11/2020
 *
 */
public class Trader {

	private LinkedList<Trade> trades = new LinkedList<Trade>();

	public Trader() {		
	}

	/**
	 * 
	 * @return trades
	 */
	public LinkedList<Trade> getTrades(){
		
		return trades;
	}
	
	/**
	 * use random class, to create each field used in the trade constructor,
	 * math.int and calling a static random method in the enum class
	 */
	public void addRandomTrade() {

		Trade trade = new Trade(((int) (Math.random()*(6 - 1))) + 1,
								((int) (Math.random()*(6 - 1))) + 1 ,
								Goods.chooseGoods());
		trades.add(trade);		
	}
}
