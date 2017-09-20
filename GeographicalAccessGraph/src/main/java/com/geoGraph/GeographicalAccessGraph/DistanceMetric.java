package com.geoGraph.GeographicalAccessGraph;

/**
 * Metric = (1 / 0.5 + dij), dij = distance between node i and j
 * 
 * @author dosne
 *
 *
 */

/**
 * Constructor
 * 
 * @author dosne
 *
 */
public class DistanceMetric extends MetriqueAbstr {

	public DistanceMetric(String name) {
		this.score = 0;
		this.nb = 0;
		this.name = name;
	}

	public void incrScore(Datas datas, int i, int j) {
		this.nb++;
		this.score += (double) 1 / (double) (0.5 + datas.getGraph().getEdge(i, j).getDistance());

	}

	public double getScore(Datas datas, int i, int j) {

		return (double) 1 / (double) (0.5 + datas.getGraph().getEdge(i, j).getDistance());
	}

}
