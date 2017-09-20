package com.geoGraph.GeographicalAccessGraph;

/**
 * 
 * @author dosne
 *
 */
public abstract class MetriqueAbstr implements MetriqueImpl {

	String name;
	double score;
	int nb;

	public double getAVG() {

		if (this.nb > 0)
			return score = score / (double) nb;
		else
			return 0;
	}

	public void resetScore() {
		this.score = 0.0;
		this.nb = 0;
	}

	public String getName() {
		return this.name;
	}

	public String toString() {

		String recap = "";
		recap += "Metrique : " + name + "\n";
		recap += "Score : " + score + "\n";
		recap += "------------------------------------------------\n";
		return recap;
	}

}
