package com.geoGraph.GeographicalAccessGraph;

import java.util.ArrayList;

/**
 * Represents a Trip perform by a user. A trip includes at least one road
 * 
 * @author dosne
 *
 */
public class Trip {

	private ArrayList<Road> roads; // Roads from a trip

	/**
	 * Constructor
	 */
	public Trip() {
		this.roads = new ArrayList<Road>();
	}

	/**
	 * Getter roads
	 * 
	 * @return roads Roads from the trip
	 */
	public ArrayList<Road> getRoads() {
		return roads;
	}

	/**
	 * Setter roads
	 * 
	 * @param roads
	 *            Roads from the trip
	 */
	public void setRoads(ArrayList<Road> roads) {
		this.roads = roads;
	}

	/**
	 * Return distance of the entire trip
	 * 
	 * @return trip distance
	 */
	public double getDist() {

		double avg = 0.0; // Usefull to compute cumulate distance

		for (int i = 0; i < this.roads.size(); i++)// for each roads in the trip
			avg += this.getRoads().get(i).getDistance();

		assert avg > 0 : "Total distance must be positive";

		return avg;
	}

	/**
	 * Return time of the entire trip
	 * 
	 * @return trip time
	 */
	public double getTime() {

		double avg = 0.0; // Usefull to compute cumulate time

		for (int i = 0; i < this.roads.size(); i++) // for each roads in the
													// trip
			avg += this.getRoads().get(i).getTime();

		assert avg > 0 : "Total time must be positive";

		return avg;
	}

}
