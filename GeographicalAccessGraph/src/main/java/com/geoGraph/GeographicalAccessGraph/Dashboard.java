package com.geoGraph.GeographicalAccessGraph;

import java.util.ArrayList;

/**
 * Dashboard, usefull to organized informations
 * 
 * @author dosne
 *
 */
public class Dashboard {

	private static final String VIEW = "------------------------------------------------------------------------\n";

	private ArrayList<WindowImpl> windows; // Windows to print

	/**
	 * Constructor
	 * 
	 * @param data
	 * @throws Exception
	 *             if data is null
	 */

	public Dashboard(Datas data) throws Exception {
		if (data == null) {
			throw new Exception("Data must be not null");
		}
		windows = data.getMyParams().initPanneaux(data);

	}

	/**
	 * @return Print datas
	 */

	public String toString() {
		String recap = VIEW;
		for (WindowImpl panneau : windows)
			recap += panneau.toString();
		recap += VIEW;
		return recap;
	}

}
