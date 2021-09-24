package trading;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Trader trader = new Trader();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		trader.addRandomTrade();
		

		
		Citizen harry = new Citizen(9);
		
		Trade trade = new Trade(3, 1, Goods.SWORD);
		Trade trade1 = new Trade(3, 1, Goods.SWORD);
		
		System.out.println(harry.getInventory());
		trade.execute(trader, harry);

		System.out.println(harry.getInventory());
		trade.execute(trader, harry);
		System.out.println(harry.getInventory());
	}

}
