package com.geoGraph.GeographicalAccessGraph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.jgrapht.DirectedGraph;

import com.geoGraph.GeographicalAccessGraph.MyGraph.MyOwnEdge;

public class Datas {

	private HashMap<Integer, User> users; // Record every users
	private int nbUsers; // users number
	private int POINB; // Pois number
	private HashMap<Integer, Long> userID; // users --> [new_id] = old_id
	private HashMap<Long, Integer> reverseUserID;// users -->[old_id] = new_id
	private HashMap<Integer, Long> POIID; // pois --> [new_id] = old_id
	private HashMap<Long, Integer> reversePOIID; // pois --> [old_id] = new_id
	@SuppressWarnings("rawtypes")
	private DirectedGraph<Integer, MyOwnEdge> graph; // Geographical graph
	private Parameters myParams; // Setup
	private int[] poi_occurences;// [poi_id] ---> frequency

	public Datas(Parameters myParams) {
		// System.out.println("Init datas :\n");
		this.myParams = myParams;
		users = new HashMap<Integer, User>();
		nbUsers = 0;
		POINB = 0;
		userID = new HashMap<Integer, Long>();
		POIID = new HashMap<Integer, Long>();
		reverseUserID = new HashMap<Long, Integer>();
		reversePOIID = new HashMap<Long, Integer>();
		// System.out.println("Loading Users ...");
		this.init(this.myParams.getTripsFile());
		this.nbUsers = this.users.size();
		this.POINB = this.POIID.size();
		poi_occurences = new int[this.POINB];
		for (int i = 0; i < this.POINB; i++) // for each POI from dataset
			poi_occurences[i] = 0; // Ni
	}

	/**
	 * Getter users
	 * 
	 * @return users
	 */
	public HashMap<Integer, User> getUsers() {
		return users;
	}

	/**
	 * Setter users
	 * 
	 * @param users
	 */
	public void setUsers(HashMap<Integer, User> users) {
		this.users = users;
	}

	/**
	 * Getter usersNB
	 * 
	 * @return usersNB
	 */
	public int getNbUsers() {
		return nbUsers;
	}

	/**
	 * Setter usersNB
	 * 
	 * @param usersNB
	 */
	public void setNbUsers(int usersNB) {
		this.nbUsers = usersNB;
	}

	/**
	 * Getter POINB
	 * 
	 * @return POINB
	 */
	public int getNbPois() {
		return POINB;
	}

	public void setNbPois(int POINB) {
		this.POINB = POINB;
	}

	/**
	 * Getter map userID [new_id] = old_id
	 * 
	 * @return map userID
	 */
	public HashMap<Integer, Long> getUserID() {
		return userID;
	}

	/**
	 * Setter map userID [new_id] = old_id
	 * 
	 * @param map
	 *            userID
	 */
	public void setUserID(HashMap<Integer, Long> userID) {
		this.userID = userID;
	}

	/**
	 * Getter map reverse userID [old_id] = new_id
	 * 
	 * @return reverse map userID
	 */
	public HashMap<Long, Integer> getReverseUserID() {
		return reverseUserID;
	}

	/**
	 * Setter map reverse userID [old_id] = new_id
	 * 
	 * @param reverse
	 *            user id
	 */
	public void setReverseUserID(HashMap<Long, Integer> reverseUserID) {
		this.reverseUserID = reverseUserID;
	}

	/**
	 * Getter map POIID [new_id] = old_id
	 * 
	 * @return POIID map
	 */
	public HashMap<Integer, Long> getPOIID() {
		return POIID;
	}

	/**
	 * Setter map POIID [new_id] = old_id
	 * 
	 * @param POIID
	 *            map
	 */
	public void setPOIID(HashMap<Integer, Long> POIID) {
		this.POIID = POIID;
	}

	/**
	 * Getter reverse map POIID [old_id] = new_id
	 * 
	 * @return reverse map POIID [old_id] = new_id
	 */
	public HashMap<Long, Integer> getReversePOIID() {
		return reversePOIID;
	}

	/**
	 * Setter reverse map POIID [old_id] = new_id
	 * 
	 * @param reverse
	 *            map POIID
	 */
	public void setReversePOIID(HashMap<Long, Integer> reversePOIID) {
		this.reversePOIID = reversePOIID;
	}

	/**
	 * Getter geographical graph
	 * 
	 * @return geographical graph
	 */
	@SuppressWarnings("rawtypes")
	public DirectedGraph<Integer, MyOwnEdge> getGraph() {
		return graph;
	}

	/**
	 * Setter geographical graph
	 * 
	 * @param geographical
	 *            graph
	 */
	@SuppressWarnings("rawtypes")
	public void setGraph(DirectedGraph<Integer, MyOwnEdge> graph) {
		this.graph = graph;
	}

	/**
	 * Getter setup
	 * 
	 * @return setup
	 */
	public Parameters getMyParams() {
		return myParams;
	}

	/**
	 * Setter setup
	 * 
	 * @param myParams
	 */
	public void setMyParams(Parameters myParams) {
		this.myParams = myParams;
	}

	/**
	 * Getter visits for each POI
	 * 
	 * @return visits visits for each POI
	 */
	public int[] getPoi_occurences() {
		return poi_occurences;
	}

	/**
	 * Setter visits for each POI
	 * 
	 * @param poi_occurences
	 */
	public void setPoi_occurences(int[] poi_occurences) {
		this.poi_occurences = poi_occurences;
	}

	/**
	 * Read trips file. Initialize each users and POI and store them. Map new_id
	 * - old_id for users and POI.
	 * 
	 * @param tripsFileName
	 */
	void init(String tripsFileName) {
		try {

			BufferedReader file = new BufferedReader(new FileReader(Parser.getOutputFolder() + tripsFileName));
			String[] elem; // Store each lines's elems
			String line; // usefull to catch each line in the file
			int comptUser = -1; // usefull to compute new id for each users
			int comptPoi = -1; // useffull to compute new id for each POI
			while ((line = file.readLine()) != null) { // while a line is not
														// already read

				elem = line.split("\t");
				if (elem.length == 1) {// If we found a user
					comptUser++;
					users.put(comptUser, new User());
					users.get(comptUser).getTrips().add(new Trip());
					userID.put(comptUser, Long.parseLong(elem[0]));
					reverseUserID.put(Long.parseLong(elem[0]), comptUser);
				}

				else {

					if (!this.reversePOIID.containsKey(Long.parseLong(elem[0]))) { // if
																					// we
																					// didn't
																					// compute
																					// new_id
																					// for
																					// the
																					// current
																					// POI
						comptPoi++;
						this.POIID.put(comptPoi, Long.parseLong(elem[0]));
						this.reversePOIID.put(Long.parseLong(elem[0]), comptPoi);
					}

					if (!this.reversePOIID.containsKey(Long.parseLong(elem[1]))) { // if
																					// we
																					// didn't
																					// compute
																					// new_id
																					// for
																					// the
																					// current
																					// user
						comptPoi++;
						this.POIID.put(comptPoi, Long.parseLong(elem[1]));
						this.reversePOIID.put(Long.parseLong(elem[1]), comptPoi);
					}

					int firstPoi = this.reversePOIID.get(Long.parseLong(elem[0]));
					int secondPoi = this.reversePOIID.get(Long.parseLong(elem[1]));
					if (firstPoi != secondPoi) // if start POI and end POI are
												// not the same
						try {
							users.get(comptUser).getTrips().get(0).getRoads().add(new Road(firstPoi, secondPoi,
									Double.parseDouble(elem[2]), Double.parseDouble(elem[3])));
						} catch (NumberFormatException e) {

						} catch (Exception e) {
							e.printStackTrace();
						}
				}

			}

			file.close();

		}

		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Delete wrong roads for each users.
	 */
	public void splitUsersTips() {
		for (Integer mapKey : this.users.keySet()) { // for each users
			ArrayList<Trip> newTrips = new ArrayList<Trip>(); // usefull to
																// store new
																// trips without
																// wrong roads
																// from user
			// System.out.println(this.userID.get(mapKey));

			if (this.users.get(mapKey).getTrips().get(0).getRoads().size() > 1) { // if
																					// there
																					// is
																					// at
																					// least
																					// one
																					// road
																					// in
																					// the
																					// trip
				try {
					this.users.get(mapKey).setTrips(this.users.get(mapKey)
							.splitTrips(this.users.get(mapKey).getTrips().get(0), 0, 1, newTrips, this.myParams));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
