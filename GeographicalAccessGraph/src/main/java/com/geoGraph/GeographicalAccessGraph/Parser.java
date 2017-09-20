package com.geoGraph.GeographicalAccessGraph;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import org.jdom2.input.SAXBuilder;

/**
 * Load XML File
 * 
 * @author dosne
 *
 */
public class Parser {

	private static String XML_GRAPH_NODE = "graph"; // tag graph
	private static String XML_PANNEAU_NODE = "windows"; // tag panneaux
	private static String XML_OUTPUT_NODE = "outputs"; // tag outputs
	private static String XML_MIN_NB_CHEMIN_NODE = "roadsMinNB"; // tag
	// minNBChemin
	private static String XML_MAX_TEMPS_NODE = "timeMax"; // tag MaxTemps
	private static String XML_NUMERO = "ExperienceTestnumber"; // tag numero

	private static String mainFolder; // main folder name where we find output
	// and config folders
	private static String outputFolder; // output folder name
	private static String configFolder; // config folder name
	private static String paramFile; // param file name

	private static org.jdom2.Document document;
	private static org.jdom2.Element racine;

	private static int compt = 0; // Usefull to determine ID of our models

	/**
	 * Getter param file name
	 * 
	 * @return param file name
	 */
	public static String getParamFile() {
		return paramFile;
	}

	/**
	 * Setter param file name
	 */
	public static void setParamFile() {
		Parser.paramFile = "param.xml";
	}

	/**
	 * Getter output folder name
	 * 
	 * @return output folder name
	 */
	public static String getOutputFolder() {
		return outputFolder;
	}

	/**
	 * Setter output folder name
	 * 
	 * @param output
	 * @throws Exception
	 *             output can't be null or empty
	 */
	public static void setOutputFolder(String output) throws Exception {
		if (output == null || output.equals("")) {
			throw new Exception("output foder name can't be null or empty");
		}
		Parser.outputFolder = output;
	}

	/**
	 * Getter config folder name
	 * 
	 * @return config folder name
	 */
	public static String getConfigFolder() {
		return configFolder;
	}

	/**
	 * Setter config folder name
	 * 
	 * @throws Exception
	 *             main folder name can't be null or empty
	 */
	public static void setConfigFolder() throws Exception {

		if (Parser.getMainFolder() == null || Parser.getMainFolder().equals("")) {
			throw new Exception("main foder name can't be null or empty");
		}

		Parser.configFolder = Parser.getMainFolder() + "config/";
	}

	/**
	 * Getter main folder name
	 * 
	 * @return main folder name
	 */
	public static String getMainFolder() {
		return mainFolder;
	}

	/**
	 * Setter main folder name
	 * 
	 * @param main
	 *            folder name
	 * @throws Exception
	 *             main folder name can't be null or empty
	 */
	public static void setFolder(String mainFolder) throws Exception {
		if (mainFolder == null || mainFolder.equals("")) {
			throw new Exception("main foder name can't be null or empty");
		}
		Parser.mainFolder = mainFolder;
	}

	/**
	 * Parse xml file and init each models
	 * 
	 * @param modelsList
	 *            Record every models
	 */
	public static void ToParse(Map<Integer, Datas> modelsList) {
		SAXBuilder sxb = new SAXBuilder();
		try {
			document = sxb.build(new File(Parser.configFolder + paramFile));
			racine = document.getRootElement();

			java.util.List<org.jdom2.Element> listAlgos = racine.getChildren(XML_GRAPH_NODE);

			Iterator iAlgos = ((java.util.List<org.jdom2.Element>) listAlgos).iterator();

			while (iAlgos.hasNext()) { // for each models
				org.jdom2.Element courant = (org.jdom2.Element) iAlgos.next();
				String panneaux = courant.getChild(XML_PANNEAU_NODE).getText();
				String outputs = courant.getChild(XML_OUTPUT_NODE).getText();
				Parameters param = new Parameters(Integer.parseInt(courant.getChild(XML_MIN_NB_CHEMIN_NODE).getText()),
						Integer.parseInt(courant.getChild(XML_MAX_TEMPS_NODE).getText()),
						Integer.parseInt(courant.getChild(XML_NUMERO).getText()), panneaux.split(","),
						outputs.split(","));

				modelsList.put(Parser.compt, new Datas(param));
				Parser.compt++;

			}
		}

		catch (Exception e) {
		}

	}

}
