package units;

import java.util.ArrayList;

public class Army {
	
	//instance variables
	private Status currentStatus = Status.IDLE;
	private ArrayList<Unit> units = new ArrayList<>();
	private int distancetoTarget = -1;
	private String target = "";
	private String currentLocation;
	private final int maxToHold = 10;	//READ ONLY
	
	//constructor
	public Army(String currentLocation) {
		this.currentLocation = currentLocation;
		//initialize all types of units
		units.add(Archer.archer("1"));
		units.add(Archer.archer("2"));
		units.add(Archer.archer("3"));
		units.add(Cavalry.cavalry("1"));
		units.add(Cavalry.cavalry("2"));
		units.add(Cavalry.cavalry("3"));
		units.add(Infantry.infantry("1"));
		units.add(Infantry.infantry("2"));
		units.add(Infantry.infantry("3"));
	}
	
	//getters and setters
	public Status getCurrentStatus() {
		return currentStatus;
	}
	
	public void setCurrentStatus(Status currentStatus) {
		this.currentStatus = currentStatus;
	}
	
	public ArrayList<Unit> getUnits() {
		return units;
	}
	
	public void setUnits(ArrayList<Unit> units) {
		this.units = units;
	}
	
	public int getDistancetoTarget() {
		return distancetoTarget;
	}
	
	public void setDistancetoTarget(int distancetoTarget) {
		this.distancetoTarget = distancetoTarget;
	}
	
	public String getTarget() {
		return target;
	}
	
	public void setTarget(String target) {
		this.target = target;
	}
	
	public String getCurrentLocation() {
		return currentLocation;
	}
	
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	
	public int getMaxToHold() {
		return maxToHold;
	}
	
}
