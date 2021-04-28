package units;

public abstract class Unit {
	
	//instance variables
	private int level;	//READ ONLY
	private int maxSoldierCount;	//READ ONLY
	private int currentSoldierCount = 0;	//needs to be set when initializing the defending city army
	private double idleUpkeep;	//READ ONLY
	private double marchingUpkeep;  //READ ONLY
	private double siegeUpkeep;  //READ ONLY
	
	//constructor
	public Unit(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
		this.level = level;
		this.maxSoldierCount = maxSoldierCount;
		this.idleUpkeep = idleUpkeep;
		this.marchingUpkeep = marchingUpkeep;
		this.siegeUpkeep = siegeUpkeep;
	}

	//getters and setters
	public int getCurrentSoldierCount() {
		return currentSoldierCount;
	}
	
	public void setCurrentSoldierCount(int currentSoldierCount) {
		this.currentSoldierCount = currentSoldierCount;
	}

	public int getLevel() {
		return level;
	}

	public int getMaxSoldierCount() {
		return maxSoldierCount;
	}

	public double getIdleUpkeep() {
		return idleUpkeep;
	}

	public double getMarchingUpkeep() {
		return marchingUpkeep;
	}

	public double getSiegeUpkeep() {
		return siegeUpkeep;
	}
	
}
