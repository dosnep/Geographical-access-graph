package com.geoGraph.GeographicalAccessGraph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.geoGraph.GeographicalAccessGraph.MyGraph.MyOwnEdge;

public class DatasTest {

	private Parameters param;
	private Datas data;

	@Before
	public void init() {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("all_seq_java.csv").getFile());
			// Parser.setOutputFolder(file.getAbsolutePath());
			Parser.setOutputFolder("/home/dosne/Bureau/DOSNE_package/demo/output/jUnitTest/");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Parser.setParamFile();
		String[] tab = { "1", "2" };
		param = new Parameters(2, 0, 0, tab, tab);
		data = new Datas(param);

	}

	@Test
	public void testNBTrips() {

		for (int i = 0; i < data.getNbUsers(); i++) {
			assertEquals("Erreur initialement il ne doit y avoir q'un voyage", 1,
					data.getUsers().get(i).getTrips().size());
		}
	}

	@Test
	public void testNBRoads() {
		int newId = data.getReverseUserID().get((long) 10);
		assertEquals("Erreur le nombre de chemins n'est pas le bon", 6,
				data.getUsers().get(newId).getTrips().get(0).getRoads().size());
	}

	@Test
	public void testNBUsers() {
		assertEquals("Erreur chargement fichier pas le bon nombre d'utilisateurs", 3, data.getNbUsers());
	}

	@Test
	public void testNBPOIs() {
		assertEquals("Erreur chargement fichier pas le bon nombre de POIs", 5, data.getNbPois());
	}

	@Test
	public void testMapIDUser() {
		Long oldID = data.getUserID().get(0);
		assertEquals("Erreur Map user oldID - newID", (int) 0, (int) data.getReverseUserID().get(oldID));
	}

	@Test
	public void testMapIDPOI() {
		Long oldID = data.getPOIID().get(0);
		assertEquals("Erreur Map poi oldID - newID", (int) 0, (int) data.getReversePOIID().get(oldID));
	}

	@Test
	public void testOccurenceVisitNode() {
		int newID = data.getReversePOIID().get((long) 1);
		data.setGraph(MyGraph.createAllPOISStringGraph(data));
		assertEquals("Erreur chargement fichier pas le bon nombre d'occurence Ni", 2, data.getPoi_occurences()[newID]);
	}

	@Test
	public void testOccurenceVisitEdge() {
		data.setGraph(MyGraph.createAllPOISStringGraph(data));
		int firstNode = data.getReversePOIID().get((long) 5);
		int secondNode = data.getReversePOIID().get((long) 6);
		MyOwnEdge edge = data.getGraph().getEdge(firstNode, secondNode);
		assertEquals("Erreur fabrication graphe sur les occurences de visites Nij", 3, edge.getNbUsers());
	}

	@Test
	public void testDistOnGrah() {
		data.setGraph(MyGraph.createAllPOISStringGraph(data));
		int firstNode = data.getReversePOIID().get((long) 5);
		int secondNode = data.getReversePOIID().get((long) 6);
		MyOwnEdge edge = data.getGraph().getEdge(firstNode, secondNode);
		assertEquals("Erreur fabrication graphe sur la distance entre 2 points", 5, (int) edge.getDistance());
	}

	@Test
	public void testAVGTimeOnGrah() {
		data.setGraph(MyGraph.createAllPOISStringGraph(data));
		int firstNode = data.getReversePOIID().get((long) 5);
		int secondNode = data.getReversePOIID().get((long) 6);
		MyOwnEdge edge = data.getGraph().getEdge(firstNode, secondNode);
		assertEquals("Erreur fabrication graphe sur le temps moyen entre 2 points", 5, (int) edge.getAVGTime());
	}

	@Test
	public void testSameEdge() {
		MyOwnEdge<Integer> firstEdge = new MyOwnEdge<Integer>(1, 2);
		MyOwnEdge<Integer> secondEdge = new MyOwnEdge<Integer>(1, 2);
		assertTrue("Erreur les deux arêtes devraient être egales", firstEdge.equals(secondEdge));
	}

	@Test
	public void testNotSameEdge() {
		MyOwnEdge<Integer> firstEdge = new MyOwnEdge<Integer>(1, 2);
		MyOwnEdge<Integer> secondEdge = new MyOwnEdge<Integer>(2, 1);
		assertFalse("Erreur les deux arêtes ne pas devraient être egales", firstEdge.equals(secondEdge));
	}

	@Test
	public void testDeleteFalseRoadsNBTrips() {
		data.splitUsersTips();
		int newID = data.getReverseUserID().get((long) 10);
		// System.out.println(data.getUsers().get(newID).getTrips().size());

		assertEquals("Erreur après suppression des chemins factices le nombre de voyages n'est pas le bon", 2,
				data.getUsers().get(newID).getTrips().size());

	}

	@Test
	public void testDeleteFalseRoadsNBRoads() {
		data.splitUsersTips();
		int newID = data.getReverseUserID().get((long) 10);
		assertEquals("Erreur après suppression des chemins factices le nombre de chemins n'est pas le bon", 2,
				data.getUsers().get(newID).getTrips().get(0).getRoads().size());

		assertEquals("Erreur après suppression des chemins factices le nombre de chemins n'est pas le bon", 3,
				data.getUsers().get(newID).getTrips().get(1).getRoads().size());

	}

	@After
	public void destroy() {
		param = null;
		data = null;
	}

}
