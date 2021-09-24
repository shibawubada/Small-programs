package monster;

import java.util.HashSet;
import java.util.OptionalDouble;
import java.util.Set;

import test.Utils;

/**
 * 
 * @author amazi
 *
 */
public final class MonsterCollection {
	private final Set<Monster> monsters;

	/**
	 * 
	 * @param monsters
	 */
	public MonsterCollection (Set<Monster> monsters) {		
		this.monsters = new HashSet<>(monsters);
	}

	/**
	 * 
	 * @return
	 */
	public Set<Monster> getMonsters(){

		return new HashSet<>(monsters);
	}

	/**
	 * 
	 * @return
	 */
	public Monster chooseBattleMonster() {

		for(Monster monster : monsters) {
			if(!monster.isFainted()) {
				return monster;
			}
		}
		return null;
	}
	/**
	 * iterate through monsters and then h.ps, storing monster
	 * with highest hp
	 * @return
	 */
	public Monster getStrongestMonster() {

		OptionalDouble sum = monsters.stream()
				.map(x -> x.getHP())
				.mapToDouble(x -> x)
				.max();
		
		double value = sum.orElse(1);
		
		for(Monster monster : monsters) {
			if(monster.getHP()==value) {
				return monster;
			}
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public double getAverageHP() {

		if(!monsters.isEmpty()) {
			OptionalDouble sum = monsters.stream()
					.map(x -> x.getHP())
					.mapToDouble(x -> x)
					.average();

			double value = sum.orElse(1);

			return value;
		}
		return 0;
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	public Set<Monster> getMonstersOfType (Type type){
		Set<Monster> temp = new HashSet<>();
		
		if (monsters.isEmpty()){
			return temp;
		}

		for(Monster monster : monsters) {
			for(Type typeComp : monster.getTypes()) {
				if(typeComp.equals(type)) {
					temp.add(monster);
					break;
				}
			}			
		}
		return temp;		
	}
}
