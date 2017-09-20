package com.geoGraph.GeographicalAccessGraph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;

import com.geoGraph.GeographicalAccessGraph.MyGraph.MyOwnEdge;

/**
 * Place to record additionnal Functions. Mainly to handle list and graph
 * 
 * @author dosne
 */
public class AdditionnalFunctions {

	/**
	 * Perform a union
	 * 
	 * @param list1
	 *            first list
	 * @param list2
	 *            second list
	 * @return union between list1 and list2
	 */
	public static List<Integer> union(List<Integer> list1, List<Integer> list2) {
		List<Integer> listFinal = new ArrayList<Integer>(list1);// union to
																// return
		listFinal.removeAll(list2);
		listFinal.addAll(list2);

		return listFinal;
	}

	/**
	 * Find neighboors from a graph node
	 * 
	 * @param centralNode
	 *            we find neighboors from this node
	 * @param level
	 *            level = 1 means direct neighboors, level = 2 means p-2
	 *            neighboors
	 * @param graph
	 *            geographical graph where to work
	 * @return neighboors list from centralNode
	 */
	@SuppressWarnings("rawtypes")
	public static List<Integer> getMyneghbor(int centralNode, int level, Graph<Integer, MyOwnEdge> graph) {
		List<Integer> newList; // neighboors list to return

		if (level == 0) { // level == 0 means that we don't want to find
							// neighboors from centralNode
			newList = new ArrayList<Integer>();
			return newList;
		}

		else { // we have neighboors to find

			newList = Graphs.neighborListOf(graph, centralNode);
			Iterator<Integer> iterator = newList.iterator();
			while (iterator.hasNext()) { // while there is a Node in the
											// neighboors list

				Integer newPoiID = iterator.next();
				newList = union(newList, getMyneghbor(newPoiID, level - 1, graph));
			}

			return newList;
		}

	}

}
