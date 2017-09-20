package com.geoGraph.GeographicalAccessGraph;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

public class MetricTest {

	@Test
	public void testGetScoreDistanceMetric() {
		try {

			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("all_seq_java.csv").getFile());
			// Parser.setOutputFolder(file.getAbsolutePath());
			Parser.setOutputFolder("/home/dosne/Bureau/DOSNE_package/demo/output/jUnitTest/");

			Parser.setParamFile();
			String[] tab = { "1", "2" };
			Parameters param = new Parameters(2, 0, 0, tab, tab);
			Datas data = new Datas(param);
			data.setGraph(MyGraph.createAllPOISStringGraph(data));

			DistanceMetric metric = new DistanceMetric("distance");
			int firstNode = data.getReversePOIID().get((long) 5);
			int secondNode = data.getReversePOIID().get((long) 6);
			assertEquals((double) 0.18, (double) metric.getScore(data, firstNode, secondNode), 0.05);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetScorePopMetric() {
		try {

			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("all_seq_java.csv").getFile());
			// Parser.setOutputFolder(file.getAbsolutePath());
			Parser.setOutputFolder("/home/dosne/Bureau/DOSNE_package/demo/output/jUnitTest/");

			Parser.setParamFile();
			String[] tab = { "1", "2" };
			Parameters param = new Parameters(2, 0, 0, tab, tab);
			Datas data = new Datas(param);
			data.setGraph(MyGraph.createAllPOISStringGraph(data));

			PopularityMetric metric = new PopularityMetric("popularity");
			int firstNode = data.getReversePOIID().get((long) 5);
			int secondNode = data.getReversePOIID().get((long) 6);
			assertEquals((double) 1.0, (double) metric.getScore(data, firstNode, secondNode), 0.05);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
