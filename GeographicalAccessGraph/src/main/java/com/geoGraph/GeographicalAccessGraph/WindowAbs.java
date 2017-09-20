package com.geoGraph.GeographicalAccessGraph;

import java.util.HashMap;

/**
 * 
 * @author dosne
 *
 */
public abstract class WindowAbs implements WindowImpl {

	private static final String VIEW = "--------\n";

	public Datas data;
	public String name;

	public HashMap<String, String> scores;

	/**
	 * @return print datas
	 */
	public String toString() {
		String recap = "";
		recap += VIEW;
		recap += name + "\n";
		for (String label : scores.keySet())
			recap += label + " : " + scores.get(label) + "\n";
		recap += VIEW;
		return recap;
	}

}
