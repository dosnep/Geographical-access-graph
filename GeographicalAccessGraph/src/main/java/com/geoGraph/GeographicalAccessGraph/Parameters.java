package com.geoGraph.GeographicalAccessGraph;

import java.util.ArrayList;

/**
 * Class Parameters This is where we store informations from XML file
 * 
 * @author dosne
 *
 */
public class Parameters {

	private static final String TRIPS_FILE_NAME = "all_seq_java.csv";

	private int roadsMinNB;// min roads number in a Trip
	private int timeMax;// Time limit to perform a roads
	private int testNumber;// test number
	private String[] infosToPrintIndex;// Informations Index to print. Each
										// index references an element of the
										// dashboard
	private String[] outputsIndex;// Outputs file index. Each index references a
									// output file

	private String tripsFile; // Trips file name

	/**
	 * Constructor
	 * 
	 * @param roadsMinNB
	 *            a trip can't contain less than roadsMinNB roads
	 * @param timeMax
	 *            a road can't exceed timeMax
	 * @param testNumber
	 *            Parameter ID
	 * @param infosToPrint
	 *            informations that need to be print
	 * @param outputs
	 *            outputs files that need to be build
	 */
	public Parameters(int roadsMinNB, int timeMax, int testNumber, String[] infosToPrintIndex, String[] outputsIndex) {
		this.roadsMinNB = roadsMinNB;
		this.timeMax = timeMax;
		this.testNumber = testNumber;
		this.tripsFile = TRIPS_FILE_NAME;
		this.infosToPrintIndex = infosToPrintIndex;
		this.outputsIndex = outputsIndex;
	}

	/**
	 * Getter roadsMinNB
	 * 
	 * @return roadsMinNB
	 */
	public int getRoadsMinNB() {
		return this.roadsMinNB;
	}

	/**
	 * Setter roadsMinNB
	 * 
	 * @param roadsMinNB
	 * @throws Exception
	 *             roadsMinNB must be positive
	 */
	public void setRoadsMinNB(int roadsMinNB) throws Exception {

		if (roadsMinNB < 0) {
			throw new Exception("roadsMinNB must be positive");
		}

		this.roadsMinNB = roadsMinNB;
	}

	/**
	 * Getter timeMax
	 * 
	 * @return timeMax
	 */
	public int GetTimeMax() {
		return this.timeMax;
	}

	/**
	 * Setter timeMax
	 * 
	 * @param timeMax
	 * @throws Exception
	 *             timeMax must be positive
	 */
	public void SetTimeMax(int timeMax) throws Exception {
		if (timeMax < 0) {
			throw new Exception("minMaxTemps must be positive");
		}

		this.timeMax = timeMax;
	}

	/**
	 * Getter testNumber
	 * 
	 * @return testNumber
	 */
	public int getTestNumber() {
		return testNumber;
	}

	/**
	 * Setter testNumber
	 * 
	 * @param testNumber
	 */
	public void setTestNumber(int testNumber) {
		this.testNumber = testNumber;
	}

	/**
	 * Setter tripsFile
	 * 
	 * @return tripsFile
	 */
	public String getTripsFile() {
		return tripsFile;
	}

	/**
	 * Getter tripsFile
	 * 
	 * @param tripsFile
	 */
	public void SetTripsFile(String tripsFile) {
		this.tripsFile = tripsFile;
	}

	/**
	 * Getter infosToPrintIndex
	 * 
	 * @return infosToPrintIndex
	 */
	public String[] getInfosToPrintIndex() {
		return infosToPrintIndex;
	}

	/**
	 * Setter infosToPrintIndex
	 * 
	 * @param infosToPrintIndex
	 */
	public void setInfosToPrintIndex(String[] infosToPrintIndex) {
		this.infosToPrintIndex = infosToPrintIndex;
	}

	/**
	 * Getter getOutputsIndex
	 * 
	 * @return getOutputsIndex
	 */
	public String[] getOutputsIndex() {
		return outputsIndex;
	}

	/**
	 * Setter outputsIndex
	 * 
	 * @param outputsIndex
	 */
	public void setOutputsIndex(String[] outputsIndex) {
		this.outputsIndex = outputsIndex;
	}

	/**
	 * Build an array list with every informations to print. Map
	 * index-corresponding information : 1 means users infos 2 means POI infos
	 * 
	 * @param data
	 *            Based on this datas we can prepare informations
	 * @return informations to print
	 */
	public ArrayList<WindowImpl> initPanneaux(Datas data) {
		ArrayList<WindowImpl> infosToPrint = new ArrayList<WindowImpl>();

		for (String elem : this.getInfosToPrintIndex()) { // for each index
			if (Integer.parseInt(elem) == 1)
				infosToPrint.add(new UsersInfos(data));
			else if (Integer.parseInt(elem) == 2)
				infosToPrint.add(new PoisInfos(data));
		}

		return infosToPrint;
	}

	/**
	 * Build an array list with every outputs files to build Map
	 * index-corresponding output files : 1 means to output graph 2 means to
	 * output user s roads
	 * 
	 * @param datas
	 *            Bases on this datas we can build outputs files
	 * @return output files to print
	 */
	public ArrayList<OutputImpl> initOutputs(Datas datas) {
		ArrayList<OutputImpl> outputs = new ArrayList<OutputImpl>();

		for (String elem : this.getOutputsIndex()) { // for each output index
			if (Integer.parseInt(elem) == 1)
				outputs.add(new OutputGraph(datas));
			else if (Integer.parseInt(elem) == 2)
				outputs.add(new OutputUsersRoads(datas));
		}

		return outputs;
	}

}
