package test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import monster.Monster;
import monster.MonsterCollection;
import monster.Type;
import monster.TypedItem;

public class TestLab8 {
	
	@Test
	public void testTypeIsAnEnum() {
		Assertions.assertTrue(Type.class.isEnum(), "Type is not an enum");
	}
	
	@Test
	public void testTypeEnumConstantsCorrect() {
		String[] correctNames = { "NORMAL", "FIRE", "WATER", "ELECTRIC", "GRASS" };
		String[] typeNames = new String[Type.values().length];
		for (int i = 0; i < typeNames.length; i++) {
			typeNames[i] = Type.values()[i].name();
		}
		Assertions.assertArrayEquals(correctNames, typeNames, "Type does not have correct values");
	}
	
	private void testEffectiveness (Type type1, Type type2, double expected) {
		Assertions.assertEquals(expected, type1.getEffectiveness(type2), type1 + ".getEffectiveness(" + type2 + ")");
	}
	
	@Test
	public void getEffectivenessCoversAllOptions() {
		double low = 0.5;
		double medium = 1.0;
		double high = 2.0;
		
		// Normal is easy
		for (Type type : Type.values()) {
			testEffectiveness(Type.NORMAL, type, medium);
			testEffectiveness(type, Type.NORMAL, medium);
		}
		
		// For our subset, every type is weak against itself
		for (Type type : Arrays.copyOfRange(Type.values(), 1, Type.values().length)) {
			testEffectiveness(type, type, low);
		}
		
		// Okay, now do the rest of them (ugh)
		testEffectiveness(Type.FIRE, Type.WATER, low);
		testEffectiveness(Type.FIRE, Type.ELECTRIC, medium);
		testEffectiveness(Type.FIRE, Type.GRASS, high);
		testEffectiveness(Type.WATER, Type.FIRE, high);
		testEffectiveness(Type.WATER, Type.ELECTRIC, medium);
		testEffectiveness(Type.WATER, Type.GRASS, low);
		testEffectiveness(Type.ELECTRIC, Type.FIRE, medium);
		testEffectiveness(Type.ELECTRIC, Type.WATER, high);
		testEffectiveness(Type.ELECTRIC, Type.GRASS, low);
		testEffectiveness(Type.GRASS, Type.FIRE, low);
		testEffectiveness(Type.GRASS, Type.WATER, high);
		testEffectiveness(Type.GRASS, Type.ELECTRIC, medium);
	}
	
	@Test
	public void getEffectivenessHasRightSignature() {
		try {
			Method m = Type.class.getMethod("getEffectiveness", Type.class);
			Assertions.assertFalse(Modifier.isStatic(m.getModifiers()), "Type.getEffectiveness should not be static");
		} catch (NoSuchMethodException | SecurityException e) {
			Assertions.fail("Type.getEffectiveness missing or has wrong signature");
		}
	}
	
	@Test
	public void typedItemHasNoStaticMethods() {
		for (Method m : TypedItem.class.getDeclaredMethods()) {
			Assertions.assertFalse(Modifier.isStatic(m.getModifiers()), "TypedItem has static method " + m.getName());
		}
	}
	
	@Test
	public void monsterCollectionConstructorDoesNotLeak() {
		Set<Monster> monsters = new HashSet<>();
		for (int i = 0; i < 10; i++) {
			monsters.add(Utils.getRandomMonster());
		}
		MonsterCollection mc = new MonsterCollection(monsters);
		Monster newMonster = Utils.getRandomMonster();
		monsters.add(newMonster);
		
		Set<Monster> testMonsters = mc.getMonsters();
		Assertions.assertFalse(testMonsters.contains(newMonster), "MonsterCollection constructor leaks internal field");
	}
	
	@Test
	public void monsterCollectionGetMonstersWorks() {
		Set<Monster> monsters = new HashSet<>();
		for (int i = 0; i < 10; i++) {
			monsters.add(Utils.getRandomMonster());
		}
		MonsterCollection mc = new MonsterCollection(monsters);
		Assertions.assertEquals(monsters, mc.getMonsters(), "MonsterCollection getMonsters() does not return correct value");
	}

	@Test
	public void monsterCollectionGetMonstersDoesNotLeak() {
		Set<Monster> monsters = new HashSet<>();
		for (int i = 0; i < 10; i++) {
			monsters.add(Utils.getRandomMonster());
		}
		MonsterCollection mc = new MonsterCollection(monsters);

		Set<Monster> testMonsters = mc.getMonsters();
		Monster newMonster = Utils.getRandomMonster();
		testMonsters.add(newMonster);
		
		Assertions.assertFalse(mc.getMonsters().contains(newMonster), "MonsterCollection.getMonsters() leaks internal field");
	}
	
	@Test
	public void monsterCollectionFieldsAllPrivateAndFinal() {
		for (Field f : MonsterCollection.class.getDeclaredFields()) {
			Assertions.assertTrue(Modifier.isPrivate(f.getModifiers()), "MonsterCollection field not private: " + f.getName());
			Assertions.assertTrue(Modifier.isFinal(f.getModifiers()), "MonsterCollection field not final: " + f.getName());
		}
	}
	
	@Test
	public void monsterCollectionIsFinal() {
		Assertions.assertTrue(Modifier.isFinal(MonsterCollection.class.getModifiers()), "MonsterCollection is not final");
	}
	
	@Test
	public void monsterCollectionAverageHPWorks() {
		MonsterCollection mc = new MonsterCollection(Collections.emptySet());
		Assertions.assertEquals(0, mc.getAverageHP(), "getAverageHP does not work with empty set");
		
		Set<Monster> monsters = new HashSet<>();
		for (int i = 0; i < 10; i++) {
			monsters.add(Utils.getRandomMonster());
		}
		mc = new MonsterCollection(monsters);
		
		double totalHP = 0;
		for (Monster m : monsters) {
			totalHP += m.getHP();
		}
		double averageHP = totalHP / monsters.size();
		
		Assertions.assertEquals(averageHP, mc.getAverageHP(), "Average HP not computed correctly");
	}
	
	@Test
	public void monsterCollectionChooseMonsterWorks() {
		MonsterCollection mc = new MonsterCollection(Collections.emptySet());
		Assertions.assertNull(mc.chooseBattleMonster(), "chooseBattleMonster does not work with empty set");
		
		Set<Monster> monsters = new HashSet<>();
		for (int i = 0; i < 10; i++) {
			monsters.add(Utils.getRandomMonster(0));
		}
		mc = new MonsterCollection(monsters);
		Assertions.assertNull(mc.chooseBattleMonster(), "chooseBattleMonster does not work with all fainted monsters");

		monsters = new HashSet<>();
		for (int i = 0; i < 10; i++) {
			monsters.add(Utils.getRandomMonster());
		}
		mc = new MonsterCollection(monsters);
		Monster chosen = mc.chooseBattleMonster();
		Assertions.assertTrue(chosen != null && chosen.getHP() > 0, "chooseBattleMonster does not choose a valid monster");
	}
	
	@Test
	public void monsterCollectionGetStrongestMonsterWorks() {
		MonsterCollection mc = new MonsterCollection(Collections.emptySet());
		Assertions.assertNull(mc.chooseBattleMonster(), "getStrongestMonster does not work with empty set");
		
		Set<Monster> monsters = new HashSet<>();
		double maxHP = 0;
		for (int i = 0; i < 10; i++) {
			Monster m = Utils.getRandomMonster();
			maxHP = Math.max(maxHP, m.getHP());
			monsters.add(m);
		}
		mc = new MonsterCollection(monsters);
		Monster chosen = mc.getStrongestMonster();
		Assertions.assertTrue(chosen != null && chosen.getHP() == maxHP, "getStrongestMonster does not choose the strongest monster");
	}
	
	@Test
	public void monsterCollectionGetMonstersOfTypeWorks() {
		MonsterCollection mc = new MonsterCollection(Collections.emptySet());
		for (Type t : Type.values()) {
			Assertions.assertTrue(mc.getMonstersOfType(t).isEmpty(), "getMonstersOfType does not work with empty set");
		}

		Set<Monster> monsters = new HashSet<>();
		Map<Type, Set<Monster>> monstersByType = new HashMap<>();
		for (int i = 0; i < 20; i++) {
			Monster m = Utils.getRandomMonster();
			for (Type t : m.getTypes()) {
				Set<Monster> typeSet = monstersByType.get(t);
				if (typeSet == null) {
					typeSet = new HashSet<>();
					monstersByType.put(t, typeSet);
				}
				typeSet.add(m);
			}
			monsters.add(m);
		}
		mc = new MonsterCollection(monsters);
		
		for (Type t : Type.values()) {
			Assertions.assertEquals(monstersByType.get(t), mc.getMonstersOfType(t), "getMonstersOfType does not work properly");
		}
	}
}
