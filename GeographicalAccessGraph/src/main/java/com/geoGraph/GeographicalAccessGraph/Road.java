package com.geoGraph.GeographicalAccessGraph;

/**
 * Represents a Road. A Road start from a POI and end to a final POI A Road is
 * determined by only two POI
 * 
 * @author dosne
 */
public class Road {

	private int firstPOI, secondPOI; // POI A and POI B
	private double distance; // Distance between two POI
	private double time; // Time to perform road from POI A to POI B

	/**
	 * Constructor
	 * 
	 * @param firstPOI
	 *            Start POI
	 * @param secondPOI
	 *            Final POI
	 * @param time
	 *            Time to perform road from firstPOI to secondPOI. time must be
	 *            positive
	 * @param distance
	 *            Distance between two POI Distance must be positive
	 * @throws Exception
	 *             distance and time must be positive firstPOI and secondPOI
	 *             must be different
	 */
	public Road(int firstPOI, int secondPOI, double time, double distance) throws Exception {

		if (firstPOI == secondPOI)// firstPOI and secondPOI must be different
			throw new Exception("firstPOI and secondPOI must be different");

		this.firstPOI = firstPOI;
		this.secondPOI = secondPOI;

		if (distance < 0) // distance must be positive
			throw new Exception("Distance must be positive");
		this.distance = distance;

		if (time < 0) // time must be positive
			throw new Exception("Time must be positive");
		this.time = time;
	}

	/**
	 * Getter firstPOI
	 * 
	 * @return firstPOI Start POI
	 */
	public int getFirstPOI() {
		return firstPOI;
	}

	/**
	 * Setter firstPOI
	 * 
	 * @param firstPOI
	 *            Start POI
	 * @throws Exception
	 *             firstPOI and secondPOI must be different
	 */
	public void setFirstPOI(int firstPOI) throws Exception {

		if (firstPOI == this.secondPOI) // firstPOI and secondPOI must be
										// different
			throw new Exception("firstPOI and secondPOI must be different");
		this.firstPOI = firstPOI;
	}

	/**
	 * Getter secondPOI
	 * 
	 * @return secondPOI Final POI
	 */
	public int getSecondPOI() {
		return secondPOI;
	}

	/**
	 * Setter secondPOI
	 * 
	 * @param secondPOI
	 *            Final POI
	 * @throws Exception
	 *             firstPOI and secondPOI must be different
	 */
	public void setSecondPOI(int secondPOI) throws Exception {

		if (secondPOI == this.firstPOI)// firstPOI and secondPOI must be
			throw new Exception("firstPOI and secondPOI must be different");
		this.secondPOI = secondPOI;
	}

	/**
	 * Getter distance
	 * 
	 * @return distance Distance between firstPOI and secondPOI
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * Setter distance
	 * 
	 * @param distance
	 *            Distance betweend firstPOI and secondPOI
	 * @throws Exception
	 *             Distance must be positive
	 */
	public void setDistance(double distance) throws Exception {

		if (distance < 0) // distance must be positive
			throw new Exception("distance must be positive");

		this.distance = distance;
	}

	/**
	 * Getter Time
	 * 
	 * @return time Time to perform the road
	 */
	public double getTime() {
		return time;
	}

	/**
	 * Setter time
	 * 
	 * @param time
	 *            Time to perform the road
	 * @throws Exception
	 *             Time must be positive
	 */
	public void setTime(double time) throws Exception {

		if (time < 0) // time must be positive
			throw new Exception("time must be positive");
		this.time = time;
	}

}
