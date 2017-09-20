package com.geoGraph.GeographicalAccessGraph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.geoGraph.GeographicalAccessGraph.MyGraph.MyOwnEdge;

/**
 * Build outputGraph file. Summarize every weighted edges of the graph
 * 
 * @author dosne
 *
 */
public class OutputGraph extends OutputAbstr {

	/**
	 * Constructor
	 * 
	 * @param datas
	 *            usefull to get all informations from geographical graph
	 */
	public OutputGraph(Datas datas) {
		this.datas = datas;
		this.folderName = "Graph";
		this.path = Parser.getOutputFolder() + "/" + datas.getMyParams().getTestNumber() + "/" + this.folderName;
		File dir = new File(this.path);
		dir.mkdirs();
	}

	/**
	 * Build a file wich contains each edges of the graph
	 * 
	 * firstPoiID seconPoiID (1/0.5+Dij)*Nij/Ni FreqVisit
	 * 
	 */
	public void getOutput() {

		try {
			BufferedWriter ecrire = new BufferedWriter(new FileWriter(this.path + "/outputGraph.csv"));
			MetriqueImpl metricsDist = new DistanceMetric("Distance");
			MetriqueImpl metricsPop = new PopularityMetric("pop");

			@SuppressWarnings("rawtypes")
			ArrayList<MyOwnEdge> allEdges = new ArrayList<MyOwnEdge>(datas.getGraph().edgeSet());
			for (int i = 0; i < allEdges.size(); i++) { // for each edges
				int firstPoi = (int) allEdges.get(i).getV1();
				int secondPoi = (int) allEdges.get(i).getV2();
				System.out.println((int) allEdges.get(i).getV1() + " " + (int) allEdges.get(i).getV2());

				double scoreDist = metricsDist.getScore(datas, firstPoi, secondPoi);
				double scorePop = metricsPop.getScore(datas, firstPoi, secondPoi);
				double score = scoreDist * scorePop;
				String toWrite = "";
				toWrite = this.datas.getPOIID().get(firstPoi) + "\t" + this.datas.getPOIID().get(secondPoi) + "\t"
						+ score + "\t" + this.datas.getGraph().getEdge(firstPoi, secondPoi).getNbUsers();

				ecrire.write(toWrite);
				ecrire.newLine();

			}

			ecrire.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
