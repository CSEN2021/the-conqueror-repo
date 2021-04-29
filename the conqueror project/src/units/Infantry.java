package units;

public class Infantry extends Unit {

	// constructor
	public Infantry(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
		super(level, maxSoldierCount, idleUpkeep, marchingUpkeep, siegeUpkeep);
	}

	// make an infantry given the level only
	public static Infantry create(String level) {
		switch (level) {
		case "1":
			return new Infantry(1, 50, 0.5, 0.6, 0.7);
		case "2":
			return new Infantry(2, 50, 0.5, 0.6, 0.7);
		default:
			return new Infantry(3, 60, 0.6, 0.7, 0.8);
		}
	}
}
