package com.geoGraph.GeographicalAccessGraph;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Build datas from xml parse Print informations build outputs files
 * 
 * @author dosne
 *
 */
public class Factory {

	private HashMap<Integer, Datas> execs = new HashMap<Integer, Datas>(); // Record
	// each
	// geographical
	// graph
	// metadatas

	/**
	 * Collect and init datas from XML file
	 */
	public void initDatas() {
		System.out.println("init Datas from xml file");
		Parser.ToParse(execs);
	}

	// Modif datas --> delete false roads

	/**
	 * Delete false roads Build geographical graph
	 */
	public void modifUsersDatas() {
		System.out.println("delete wrong roads");
		for (Integer mapKey : execs.keySet()) { // for each geographical graph
			// metadatas
			execs.get(mapKey).splitUsersTips();
			execs.get(mapKey).setGraph(MyGraph.createAllPOISStringGraph(execs.get(mapKey)));
		}
	}

	/**
	 * Print informations
	 */
	public void printDashboard() {
		System.out.println("Print dashboard");

		for (Integer mapKey : execs.keySet()) { // for each geographical graph
			// metadatas
			Dashboard dash; // dashboard to print
			try {
				dash = new Dashboard(execs.get(mapKey));
				System.out.println(dash.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Build outputs files
	 */
	public void makeOutFiles() {

		System.out.println("Build output files");

		for (Integer mapKey : execs.keySet()) { // for each geographical graph
			// metadatas

			String path = Parser.getOutputFolder() + "/" + execs.get(mapKey).getMyParams().getTestNumber(); // folder
			// path
			File dir = new File(path);
			dir.mkdirs();

			ArrayList<OutputImpl> outputs = execs.get(mapKey).getMyParams().initOutputs(execs.get(mapKey)); // Catch
			// outputs
			// files
			// to
			// record

			for (int i = 0; i < outputs.size(); i++) // for each output files to
				// record
				outputs.get(i).getOutput();

		}

	}

}
