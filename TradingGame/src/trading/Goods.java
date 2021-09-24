package trading;

import java.util.Random;

/**
 * enumerated type Goods with the following values:
 * 
 * @author Harry Goodwin - 2557827G
 * 30/11/2020
 *
 */
public enum Goods {

	BREAD, COAL, FISH, HELMET, IRON, PAPER, SHIELD, SWORD, WOOD, WOOL;

	private static final Random RAND = new Random();

	/**
	 * used when creating a trade, random goods selected using this static
	 * method
	 * @return random goods enum
	 */
	public static Goods chooseGoods() {
		return values()[RAND.nextInt(values().length)];
	}
}
