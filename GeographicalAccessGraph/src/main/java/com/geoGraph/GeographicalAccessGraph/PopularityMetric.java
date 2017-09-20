package com.geoGraph.GeographicalAccessGraph;

/**
 * Metric = (Ni/ Nij), Ni = nb of distinct user who visited i, Nij nb of
 * distinct user who went from i to j
 * 
 * @author dosne
 * 
 * 
 * 
 */
public class PopularityMetric extends MetriqueAbstr {

	/**
	 * Constructor
	 * 
	 * @param name
	 *            metric
	 */
	public PopularityMetric(String name) {
		this.score = 0;
		this.nb = 0;
		this.name = name;
	}

	public void incrScore(Datas datas, int i, int j) {
		this.nb++;
		if (datas.getGraph().containsEdge(i, j))
			this.score += (double) datas.getGraph().getEdge(i, j).getNbUsers() / (double) datas.getPoi_occurences()[i];
		else
			this.score += 0;
	}

	public double getScore(Datas datas, int i, int j) {
		if (datas.getGraph().containsEdge(i, j)) {
			return (double) datas.getGraph().getEdge(i, j).getNbUsers() / (double) datas.getPoi_occurences()[i];
		} else
			return 0.0;
	}
}
