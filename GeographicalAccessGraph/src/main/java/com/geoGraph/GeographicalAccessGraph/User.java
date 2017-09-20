package com.geoGraph.GeographicalAccessGraph;

import java.util.ArrayList;

/**
 * Represents a user
 * 
 * @author dosne
 *
 */
public class User {

	private ArrayList<Trip> trips; // Trips list

	/**
	 * Constructor
	 */
	public User() {
		this.trips = new ArrayList<Trip>();
	}

	/**
	 * Getter Trips
	 * 
	 * @return Trips trips performed by the user
	 */
	public ArrayList<Trip> getTrips() {
		return trips;
	}

	/**
	 * Setter Trips
	 * 
	 * @param trips
	 */
	public void setTrips(ArrayList<Trip> trips) {
		this.trips = trips;
	}

	/**
	 * Compute average roads number per trip
	 * 
	 * @return average roads number per Trip
	 */
	public int getAVGRoadsPerTrip() {

		int avg = 0;// Usefull to compute average roads number per trip

		for (int i = 0; i < this.trips.size(); i++)// For each trips performed
													// by the user
			avg += this.getTrips().get(i).getRoads().size();

		avg = (int) ((double) avg / (double) this.trips.size());

		assert avg > 0 : "average must be positive";

		return avg;
	}

	/**
	 * Compute cumulate roads number from all user s trip
	 * 
	 * @return roads number from all user s Trip
	 */
	public int getRoadsNumberPerformed() {

		int totalRoadsNB = 0;// Usefull to compute average roads number per trip

		for (int i = 0; i < this.trips.size(); i++)// For each trips performed
													// by the user
			totalRoadsNB += this.getTrips().get(i).getRoads().size();

		assert totalRoadsNB > 0 : "total road number must be positive";

		return totalRoadsNB;
	}

	/**
	 * Compute average distance per trip
	 * 
	 * @return average distance per trip
	 */
	public double getAVGDist() {

		double avg = 0.0;// Usefull to compute average distance per trip

		for (int i = 0; i < this.getTrips().size(); i++)// For each trips
														// performed by the user
			avg += this.getTrips().get(i).getDist();

		avg = avg / (double) this.trips.size();

		assert avg > 0 : "average must be positive";

		return avg;
	}

	/**
	 * Compute average time per trip
	 * 
	 * @return average average time per trip
	 */
	public double getAVGTime() {

		double avg = 0.0;// Usefull to compute average time per trip

		for (int i = 0; i < this.getTrips().size(); i++)// For each trips
														// performed by the user
			avg += this.getTrips().get(i).getDist();

		avg = avg / (double) this.trips.size();

		assert avg > 0 : "average must be positive";

		return avg;
	}

	/**
	 * Delete wrong roads from the unique trip. When a road is deleted, split
	 * the trip into two trips.
	 * 
	 * @param init
	 *            First unique trips wich might be splited
	 * @param lastCut
	 *            index where we errased a roads
	 * @param nextCut
	 *            next index where we may errase a roads
	 * @param tripsToReturn
	 *            final trips list to return
	 * @param param
	 *            usefull to catch conditions to errase a road
	 * @return Each trips performed by a user
	 * @throws Exception
	 *             init must have at least one road
	 */
	public ArrayList<Trip> splitTrips(Trip init, int lastCut, int nextCut, ArrayList<Trip> tripsToReturn,
			Parameters param) throws Exception {

		if (init.getRoads().size() == 0) {
			throw new Exception("A trip must have at least one road");
		}

		if (nextCut == init.getRoads().size() - 1) // if we are at the last road
		{
			Trip newAdd = new Trip();
			newAdd.setRoads(new ArrayList<Road>(init.getRoads().subList(lastCut, init.getRoads().size())));
			tripsToReturn.add(newAdd);
			return tripsToReturn;

		}

		else if (init.getRoads().get(nextCut).getTime() >= param.GetTimeMax()
				&& nextCut - lastCut >= param.getRoadsMinNB()
				&& (init.getRoads().size() - 1) - (nextCut) >= param.getRoadsMinNB())// if
																						// the
																						// road
																						// can
																						// be
																						// delete
																						// from
																						// the
																						// trip

		{
			Trip newAdd = new Trip();
			newAdd.setRoads(new ArrayList<Road>(init.getRoads().subList(lastCut, nextCut)));
			tripsToReturn.add(newAdd);
			return splitTrips(init, nextCut + 1, nextCut + 2, tripsToReturn, param);

		}

		else // jump to the next road
			return splitTrips(init, lastCut, nextCut + 1, tripsToReturn, param);
	}

}
