package units;

public class Cavalry extends Unit {

	// constructor
	public Cavalry(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
		super(level, maxSoldierCount, idleUpkeep, marchingUpkeep, siegeUpkeep);
	}
	

	public Cavalry(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep,
			Army parentArmy) {
		super(level, maxSoldierCount, idleUpkeep, marchingUpkeep, siegeUpkeep, parentArmy);
	}


	// make a cavalry given the level only
	public static Cavalry create(String level,Army parentArmy) {
		switch (level) {
		case "1":
			return new Cavalry(1, 40, 0.6, 0.7, 0.75,parentArmy);
		case "2":
			return new Cavalry(2, 40, 0.6, 0.7, 0.75,parentArmy);
		default:
			return new Cavalry(3, 60, 0.7, 0.8, 0.9,parentArmy);
		}
	}

}
