package com.geoGraph.GeographicalAccessGraph;

import java.util.HashMap;

/**
 * 
 * @author dosne
 *
 */
public class PoisInfos extends WindowAbs {

	/**
	 * Print informations about POIs from dataset
	 * 
	 * @param datas
	 */
	public PoisInfos(Datas datas) {
		this.data = datas;
		this.name = "PoisInfos";
		this.scores = new HashMap<String, String>();
		this.scores.put("NbPois", "" + this.data.getNbPois());
	}

}
