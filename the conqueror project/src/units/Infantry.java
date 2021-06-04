package units;

public class Infantry extends Unit {

	// constructor
	public Infantry(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
		super(level, maxSoldierCount, idleUpkeep, marchingUpkeep, siegeUpkeep);
	}
	

	public Infantry(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep,
			Army parentArmy) {
		super(level, maxSoldierCount, idleUpkeep, marchingUpkeep, siegeUpkeep, parentArmy);
	}


	// make an infantry given the level only
	public static Infantry create(String level,Army parentArmy) {
		switch (level) {
		case "1":
			return new Infantry(1, 50, 0.5, 0.6, 0.7,parentArmy);
		case "2":
			return new Infantry(2, 50, 0.5, 0.6, 0.7,parentArmy);
		default:
			return new Infantry(3, 60, 0.6, 0.7, 0.8,parentArmy);
		}
	}
}
