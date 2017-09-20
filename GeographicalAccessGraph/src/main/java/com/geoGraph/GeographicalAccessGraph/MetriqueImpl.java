package com.geoGraph.GeographicalAccessGraph;

/**
 * 
 * @author dosne
 *
 */
public interface MetriqueImpl {

	/**
	 * Compute score for edge i-j Increment score
	 * 
	 * @param datas
	 *            Usefull to get infos from geographical graph
	 * @param i
	 *            first node
	 * @param j
	 *            second node
	 */
	public void incrScore(Datas datas, int i, int j);

	/**
	 * 
	 * @param datas
	 *            Usefull to get infos from geographical graph
	 * @param i
	 *            first node
	 * @param j
	 *            second node
	 * @return score for edge i-j
	 */
	public double getScore(Datas datas, int i, int j);

	/**
	 * Score = 0
	 */
	public void resetScore();

	/**
	 * Getter average score
	 * 
	 * @return average score
	 */
	public double getAVG();

	/**
	 * Summarize var
	 * 
	 * @return summary
	 */
	public String toString();

	/**
	 * Getter metric name
	 * 
	 * @return metric name
	 */
	public String getName();

}
