package units;

import java.util.ArrayList;

import exceptions.MaxCapacityException;

public class Army {
	
	//instance variables
	private Status currentStatus = Status.IDLE;
	private ArrayList<Unit> units;
	private int distancetoTarget = -1;
	private String target = "";
	private String currentLocation;
	private final int maxToHold = 10;	//READ ONLY
	
	//constructor
	public Army(String currentLocation) {
		this.currentLocation = currentLocation;
		units = new ArrayList<>();
	}
	//methods
	public void relocateUnit(Unit unit) throws MaxCapacityException{
		if(this.units.size()==10) {				//checks if the current units are already at max size
			throw new MaxCapacityException();
		}
		
		Army oldArmy = unit.getParentArmy();
		unit.setParentArmy(this);
		this.units.add(unit);
		oldArmy.getUnits().remove(unit);
	}
	
	public void handleAttackedUnit(Unit u) {
		if(u.getCurrentSoldierCount()<=0) {
			this.units.remove(u);
		}
	}
	
	public double foodNeeded() {
		double foodNeeded = 0;
		switch (this.currentStatus) {
		case IDLE:
			for (int i = 0; i < units.size(); i++) {
				foodNeeded+=units.get(i).getIdleUpkeep()*units.get(i).getCurrentSoldierCount();
			}
			break;
		case BESIEGING:
			for (int i = 0; i < units.size(); i++) {
				foodNeeded+=units.get(i).getSiegeUpkeep()*units.get(i).getCurrentSoldierCount();
			}
			break;
		case MARCHING:
			for (int i = 0; i < units.size(); i++) {
				foodNeeded+=units.get(i).getMarchingUpkeep()*units.get(i).getCurrentSoldierCount();
			}
			break;
		}
		return foodNeeded;
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
