package engine;

import java.io.*;
import java.util.*;
import units.*;

public class ReadCSV {

	// Reads into ArrayList<City> availableCities & ArrayList<Distance> distances
	public static void readFile(String path, ArrayList<City> availableCities, ArrayList<Distance> distances)
			throws IOException {
		String currentLine = "";
		FileReader fileReader = new FileReader(path); // open file "path"
		BufferedReader br = new BufferedReader(fileReader); // read from it

		while ((currentLine = br.readLine()) != null) {

			String[] result = currentLine.split(","); // save to result

			distances.add(new Distance(result[0], result[1], Integer.parseInt(result[2]))); // fill distances

			// check if city already added if not then add it
			//needs to be rewritten because it obviously doesn't contain something written with keyword 'new'
			if (!availableCities.contains(new City(result[0]))) {
				availableCities.add(new City(result[0]));
			}
			if (!availableCities.contains(new City(result[1]))) {
				availableCities.add(new City(result[1]));
			}
		}
	}

	public static void readFile(City city) throws IOException {
		String currentLine = "";
		String path = city.getName() + "_army"; // set path to the naming scheme of the file
		FileReader fileReader = new FileReader(path); // open file "path"
		BufferedReader br = new BufferedReader(fileReader); // read from it

		ArrayList<Unit> units = city.getDefendingArmy().getUnits();	// units of the army of the city
		while ((currentLine = br.readLine()) != null) {

			String[] result = currentLine.split(","); // save to result
			if(units.contains(result))
			
			// adding units
			switch (result[0]) {
			// initialize units with corresponding level
			case "Archer":
				units.add(Archer.archer(result[1]));
				break;
			case "Infantry":
				units.add(Infantry.infantry(result[1]));
				break;
			case "Cavalry":
				units.add(Cavalry.cavalry(result[1]));
				break;
			}

		}
	}
}