package com.geoGraph.GeographicalAccessGraph;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;

/**
 * Allow to build a geographical graph where each node are a POI (Point of
 * interest) and an edge between two POIs A and B means that at least one user
 * performed road from A to B
 * 
 * A geographical graph is a weighted directed graph
 * 
 * Informations ; On edges : time, distance an visit frequencies; On nodes
 * :visit frequencies
 * 
 * @author dosne
 *
 */
public class MyGraph {

	/**
	 * 
	 * Build a geographical graph from users's trips
	 * 
	 * @param datas
	 *            All datas we need to build the graph
	 * @return Geographical graph
	 */
	public static DirectedGraph<Integer, MyOwnEdge> createAllPOISStringGraph(Datas datas) {

		DirectedGraph<Integer, MyOwnEdge> g = (DirectedGraph<Integer, MyOwnEdge>) new DefaultDirectedGraph<Integer, MyOwnEdge>(
				MyOwnEdge.class);// Geographical graph to return

		for (Integer user_id : datas.getUsers().keySet()) {// for each users in
			// the dataset

			int[] poi_occurences = new int[datas.getNbPois()]; // usefull to see
			// if a user
			// visited a POI

			for (int i = 0; i < datas.getNbPois(); i++)// for each POI in the
				// dataset
				poi_occurences[i] = -1;

			for (int i = 0; i < datas.getUsers().get(user_id).getTrips().size(); i++) { // for
				// each
				// user
				// s
				// trips
				for (int j = 0; j < datas.getUsers().get(user_id).getTrips().get(i).getRoads().size(); j++) { // for
					// each
					// roads
					// in
					// a
					// trip
					Road courant = datas.getUsers().get(user_id).getTrips().get(i).getRoads().get(j);

					if (!g.containsVertex(courant.getFirstPOI())) { // if
						// firstPOI
						// node is
						// not
						// already
						// in the
						// graph
						poi_occurences[courant.getFirstPOI()] = user_id;
						datas.getPoi_occurences()[courant.getFirstPOI()]++;
						g.addVertex(courant.getFirstPOI());
					}

					else if (poi_occurences[courant.getFirstPOI()] != user_id) { // if
						// current
						// user
						// has
						// not
						// visited
						// the
						// firstPOI
						datas.getPoi_occurences()[courant.getFirstPOI()]++;
						poi_occurences[courant.getFirstPOI()] = user_id;
					}

					if (!g.containsVertex(courant.getSecondPOI())) { // if
						// secondPOI
						// node
						// is
						// not
						// already
						// int
						// the
						// graph
						poi_occurences[courant.getSecondPOI()] = user_id;
						datas.getPoi_occurences()[courant.getSecondPOI()]++;
						g.addVertex(courant.getSecondPOI());
					}

					else if (poi_occurences[courant.getSecondPOI()] != user_id) {// if
						// current
						// user
						// did
						// not
						// visit
						// the
						// secondPOI
						datas.getPoi_occurences()[courant.getSecondPOI()]++;
						poi_occurences[courant.getSecondPOI()] = user_id;
					}

					if (!g.containsEdge(courant.getFirstPOI(), courant.getSecondPOI())) { // if
						// edge
						// firstPOI-secondPOI
						// is
						// not
						// in
						// the
						// graph
						g.addEdge(courant.getFirstPOI(), courant.getSecondPOI(),
								new MyOwnEdge(courant.getFirstPOI(), courant.getSecondPOI()));
						try {
							g.getEdge(courant.getFirstPOI(), courant.getSecondPOI()).setDistance(courant.getDistance());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					if (g.getEdge(courant.getFirstPOI(), courant.getSecondPOI()).getLastVisitor() != user_id) { // If
						// user
						// did not already perform road from firstPOI to
						// secondPOI
						try {
							g.getEdge(courant.getFirstPOI(), courant.getSecondPOI()).incrTime(courant.getTime());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						g.getEdge(courant.getFirstPOI(), courant.getSecondPOI()).incrNbUsers();
						g.getEdge(courant.getFirstPOI(), courant.getSecondPOI()).setLastVisitor(user_id);

					}
					//

				}
			}

		}

		return g;
	}

	/**
	 * 
	 * @author dosne
	 *
	 * @param <V>
	 */
	public static class MyOwnEdge<V> extends DefaultEdge {

		private V v1; // first Node
		private V v2; // second Node
		private int time; // time to perform road from v1 to v2
		private double distance; // distance between v1 and v2
		private int nbUsers; // users number who tooke the road v1 to v2
		private int lastVisitor; // Last user who tooke the road v1 to v2

		/**
		 * Constructor
		 * 
		 * @param v1
		 *            first Node
		 * @param v2
		 *            second Node
		 */
		public MyOwnEdge(V v1, V v2) {
			this.v1 = v1;
			this.v2 = v2;
			this.time = 0;
			this.distance = 0.0;
			this.nbUsers = 0;
			this.lastVisitor = -1;
		}

		/**
		 * Getter last POI visitor
		 * 
		 * @return last visitor
		 */
		public int getLastVisitor() {
			return lastVisitor;
		}

		/**
		 * Setter last POI visitor
		 * 
		 * @param last
		 *            visitor
		 */
		public void setLastVisitor(int lastVisitor) {
			this.lastVisitor = lastVisitor;
		}

		/**
		 * Getter first node
		 * 
		 * @return first node
		 */
		public V getV1() {
			return v1;
		}

		/**
		 * Getter second node
		 * 
		 * @return second node
		 */
		public V getV2() {
			return v2;
		}

		/**
		 * Compute average time to perform road
		 * 
		 * @return average time to perform road from one POI to another
		 */
		public double getAVGTime() {
			double avgTime = 0.0;
			avgTime = (double) time / (double) nbUsers;

			assert avgTime > 0 : "Time between two nodes should be positive";

			return avgTime;

		}

		/**
		 * Add time
		 * 
		 * @param newTime
		 *            Time to add
		 * @throws Exception
		 *             time to add must be positive
		 */
		public void incrTime(double newTime) throws Exception {

			if (newTime < 0) {
				throw new Exception("Time must be positive");
			}

			this.time += newTime;
		}

		/**
		 * Getter distance
		 * 
		 * @return distance between two nodes
		 */
		public double getDistance() {
			return this.distance;
		}

		/**
		 * Setter distance
		 * 
		 * @param distance
		 *            between two nodes
		 * @throws Exception
		 *             distance must be positive
		 */
		public void setDistance(double distance) throws Exception {

			if (distance < 0) {
				throw new Exception("distance must be positive");
			}

			this.distance = distance;
		}

		/**
		 * Getter users number
		 * 
		 * @return users number
		 */
		public int getNbUsers() {
			return nbUsers;
		}

		/**
		 * Incremente users number
		 */
		public void incrNbUsers() {
			this.nbUsers++;
		}

		/**
		 * @return Edge summary
		 */
		public String toString() {
			if (this.getNbUsers() != 0)
				return "" + this.getV1() + " " + this.getV2();
			else
				return "";
		}

		public boolean equals(Object other) {
			if (!(other instanceof MyOwnEdge)) {
				return false;
			}
			return (v1 == ((MyOwnEdge) other).v1) && (v2 == ((MyOwnEdge) other).v2);
		}

		// public int hashCode() {
		// return ((int) v1 * 5 + (int) v2 * 3);
		// }

	}

}
