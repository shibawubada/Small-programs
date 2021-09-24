package trading;

import java.util.Objects;

/**
 * Trade representing a single trade, including the following properties:
 * 
 * @author Harry Goodwin - 2557827G
 * 30/11/2020
 *
 */
public class Trade {

	private int gemsQuantity;
	private int tradeQuantity;
	private Goods goodsType;

	/**
	 * constructor for all fields
	 * @param gemsQuantity
	 * @param tradeGoods
	 * @param goodsType
	 */
	public Trade(int gemsQuantity, int tradeGoods, Goods goodsType) {
		this.gemsQuantity = gemsQuantity;
		this.tradeQuantity = tradeGoods;
		this.goodsType = goodsType;
	}

	/**
	 * @return the gemsQuantity
	 */
	public int getGemsQuantity() {
		return gemsQuantity;
	}

	/**
	 * @return the tradeGoods
	 */
	public int getTradeGoods() {
		return tradeQuantity;
	}

	/**
	 * @return the goodsType
	 */
	public Goods getGoodsType() {
		return goodsType;
	}

	/**
	 * If the current Trade is not included in the list of trades supported by trader, 
	 * this method should throw an IllegalArgmentException
	 * Otherwise, it should call citizen.executeTrade() with the current trade
	 * If executeTrade() returns true, the method should also call trader.addRandomTrade()
	 * @param trader
	 * @param citizen
	 */
	public void execute(Trader trader, Citizen citizen) {

		Trade trade = new Trade(this.gemsQuantity, this.tradeQuantity, this.goodsType);

		if(trader.getTrades().contains(trade)) {

			if(citizen.executeTrade(trade)) {

				trader.addRandomTrade();
			}	
		}
		else {
			throw new IllegalArgumentException("Trade not present");
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(gemsQuantity, goodsType, tradeQuantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Trade)) {
			return false;
		}
		Trade other = (Trade) obj;
		return gemsQuantity == other.gemsQuantity && goodsType == other.goodsType && tradeQuantity == other.tradeQuantity;
	}

	@Override
	public String toString() {
		return "gems: " + gemsQuantity + "\namount: " + tradeQuantity + "\ngoods: Goods." + goodsType;
	}	
}
