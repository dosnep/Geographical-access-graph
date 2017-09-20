package com.geoGraph.GeographicalAccessGraph;

import java.util.HashMap;

/**
 * 
 * @author dosne
 *
 */
public class UsersInfos extends WindowAbs {

	/**
	 * Print informations about users from dataset
	 * 
	 * @param datas
	 */
	public UsersInfos(Datas datas) {
		this.data = datas;
		this.name = "Users infos";
		this.scores = new HashMap<String, String>();
		this.scores.put("NbUsers", "" + this.data.getNbUsers());

	}
}
