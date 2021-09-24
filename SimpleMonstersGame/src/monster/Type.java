package monster;
/**
 * 
 * @author amazi
 *
 */
public enum Type {

	NORMAL,
	FIRE,
	WATER,
	ELECTRIC,
	GRASS;

	public static double NOT_EFFECTIVE = 0.5;
	public static double SUPER_EFFECTIVE = 2.0;
	public static double NORMAL_EFFECTIVE = 1.0;

	/**
	 * 
	 * @param otherType
	 * @return
	 */
	public double getEffectiveness(Type otherType) {
		double effectiveness = NORMAL_EFFECTIVE;

		if(otherType.equals(null)) {
			return 0;
		}
		
		switch(this) {
		case FIRE:		
			if(otherType==FIRE || otherType==WATER) {
				effectiveness = NOT_EFFECTIVE;
			}
			else if(otherType==GRASS) {
				effectiveness = SUPER_EFFECTIVE;
			}
			break;

		case WATER:
			if (otherType==WATER || otherType==GRASS) {
				effectiveness = NOT_EFFECTIVE;
			} 
			else if (otherType==FIRE) {
				effectiveness = SUPER_EFFECTIVE;
			}
			break;

		case ELECTRIC:
			if (otherType==ELECTRIC || otherType==GRASS) {
				effectiveness = NOT_EFFECTIVE;
			} 
			else if (otherType==WATER) {
				effectiveness = SUPER_EFFECTIVE;
			}
			break;

		case GRASS:
			if (otherType==FIRE || otherType==GRASS) {
				effectiveness = NOT_EFFECTIVE;
			} else if (otherType==Type.WATER) {
				effectiveness = SUPER_EFFECTIVE;
			}
			break;
			
		default:
			break;
		}

		return effectiveness;

	}
}

