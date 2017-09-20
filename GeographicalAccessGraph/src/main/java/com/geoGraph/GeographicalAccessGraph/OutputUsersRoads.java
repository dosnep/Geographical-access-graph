package com.geoGraph.GeographicalAccessGraph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Build outputUsersRoads file. Summarize every roads performed by each users
 * 
 * @author dosne
 *
 */
public class OutputUsersRoads extends OutputAbstr {

	/**
	 * Constructor
	 * 
	 * @param datas
	 *            usefull to watch users s roads
	 */
	public OutputUsersRoads(Datas datas) {
		this.datas = datas;
		this.folderName = "UsersRoads";
		this.path = Parser.getOutputFolder() + "/" + datas.getMyParams().getTestNumber() + "/" + this.folderName;
		File dir = new File(this.path);
		dir.mkdirs();
	}

	/**
	 * Build a file wich contains each poi visited by each users
	 * 
	 * userID firstPoiID seconPoiID (1/0.5+Dij) Nij/Ni
	 * 
	 */
	public void getOutput() {
		try {
			MetriqueImpl metricsDist = new DistanceMetric("Distance");
			MetriqueImpl metricsPop = new PopularityMetric("pop");
			BufferedWriter ecrire = new BufferedWriter(new FileWriter(this.path + "/UsersRoads.csv"));
			for (int key : this.datas.getUsers().keySet()) { // for each users
				System.out.println(key);
				for (int i = 0; i < this.datas.getUsers().get(key).getTrips().size(); i++) { // for
																								// each
																								// users
																								// s
																								// trips
					for (int j = 0; j < this.datas.getUsers().get(key).getTrips().get(i).getRoads().size(); j++) { // for
																													// each
																													// roads
						int firstPoi, secondPoi;
						firstPoi = this.datas.getUsers().get(key).getTrips().get(i).getRoads().get(j).getFirstPOI();
						secondPoi = this.datas.getUsers().get(key).getTrips().get(i).getRoads().get(j).getSecondPOI();
						double scoreDist = metricsDist.getScore(datas, firstPoi, secondPoi);
						double scorePop = metricsPop.getScore(datas, firstPoi, secondPoi);
						double score = scoreDist * scorePop;

						ecrire.write("" + this.datas.getUserID().get(key));
						ecrire.write("\t" + this.datas.getPOIID().get(firstPoi));
						ecrire.write("\t" + this.datas.getPOIID().get(secondPoi));
						ecrire.write("\t" + score);

						ecrire.newLine();

					}

				}
			}

			ecrire.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
