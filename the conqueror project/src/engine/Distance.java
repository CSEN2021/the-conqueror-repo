package engine;

public class Distance {
	
	//instance variables
	private String from;    //read only
	private String to;    //read only
	private int distance;	//read only
	
	//constructor
	public Distance(String from,String to, int distance) {
		this.from = from;
		this.to = to;
		this.distance = distance;
	}
}
