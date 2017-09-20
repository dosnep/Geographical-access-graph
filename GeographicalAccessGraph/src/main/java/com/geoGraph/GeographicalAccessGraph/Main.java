package com.geoGraph.GeographicalAccessGraph;

public class Main {

	public static void main(String[] args) {

		if (args.length == 2) { // Need 2 args <path main fold> <path output>
			Factory f = new Factory();
			try {
				System.out.println("begin");
				Parser.setFolder(args[0]);
				Parser.setConfigFolder();
				Parser.setOutputFolder(args[1]);
				Parser.setParamFile();
				f.initDatas();
				f.modifUsersDatas();
				f.printDashboard();
				f.makeOutFiles();
				System.out.println("end");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else
			System.out.println("Need two args : java -jar app.jar <Path fold> <Path output>");
	}

}