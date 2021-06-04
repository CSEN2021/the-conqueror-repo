package units;

public class Archer extends Unit {

	// constructor
	public Archer(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
		super(level, maxSoldierCount, idleUpkeep, marchingUpkeep, siegeUpkeep);
	}
	
	


	public Archer(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep,
			Army parentArmy) {
		super(level, maxSoldierCount, idleUpkeep, marchingUpkeep, siegeUpkeep, parentArmy);
	}


	// make an archer given the level only

	public static Archer create(String level ) {
		switch (level) {
		case "1":
			return new Archer(1, 60, 0.4, 0.5, 0.6);
		case "2":
			return new Archer(2, 60, 0.4, 0.5, 0.6);
		default:
			return new Archer(3, 70, 0.5, 0.6, 0.7);
		}
	}
	
	// make an archer given the level and parent army
	public static Archer create(String level,Army parentArmy) {
		switch (level) {
		case "1":
			return new Archer(1, 60, 0.4, 0.5, 0.6,parentArmy);
		case "2":
			return new Archer(2, 60, 0.4, 0.5, 0.6,parentArmy);
		default:
			return new Archer(3, 70, 0.5, 0.6, 0.7,parentArmy);
		}
	}
}
