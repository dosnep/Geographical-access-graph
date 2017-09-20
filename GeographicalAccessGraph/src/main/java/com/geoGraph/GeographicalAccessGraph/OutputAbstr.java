package com.geoGraph.GeographicalAccessGraph;

/**
 * Output abstract implements OutputImpl
 * 
 * @author dosne
 *
 */
public abstract class OutputAbstr implements OutputImpl {

	Datas datas; // we build file from datas
	String folderName; // Folder where to store the file
	String path; // Path where to store the file

	private static final String VIEW = "------------------------------------------------\n";

	/**
	 * 
	 * @return folder name where to record file
	 */
	public String getFolderName() {
		return folderName;
	}

	/**
	 * 
	 * @param folderName
	 * @throws Exception
	 *             folderName cannot be null or empty
	 */
	public void setFolderName(String folderName) throws Exception {

		if (folderName == null || folderName.equals(""))
			throw new Exception("folderName cannot be null or empty");

		this.folderName = folderName;
	}

	/**
	 * @return toString Summarize all var from class
	 */
	public String toString() {

		String recap = "";
		recap += "Path : " + Parser.getOutputFolder() + "/" + this.getFolderName() + "\n";
		recap += VIEW;
		return recap;
	}
}
