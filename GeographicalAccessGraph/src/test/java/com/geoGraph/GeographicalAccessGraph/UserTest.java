package com.geoGraph.GeographicalAccessGraph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

	private Parameters param;
	private Datas data;

	@Before
	public void init() {
		try {

			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("all_seq_java.csv").getFile());
			// Parser.setOutputFolder(file.getAbsolutePath());
			Parser.setOutputFolder("/home/dosne/Bureau/DOSNE_package/demo/output/jUnitTest/");

			Parser.setParamFile();
			String[] tab = { "1", "2" };
			param = new Parameters(2, 0, 0, tab, tab);
			data = new Datas(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testGetAVGRoadsPerTrip() {
		int newID = data.getReverseUserID().get((long) 11);
		assertEquals("Erreur, la moyenne de chemins par voyage n'est pas la bonne", 3,
				data.getUsers().get(newID).getRoadsNumberPerformed());

	}

	@Test
	public void testGetRoadsNumberPerformed() {
		int newID = data.getReverseUserID().get((long) 11);
		assertEquals("Erreur, le nombre total de chemins effectués n'est pas le bon", 3,
				data.getUsers().get(newID).getRoadsNumberPerformed());
	}

	@Test
	public void testGetAVGTime() {
		int newID = data.getReverseUserID().get((long) 11);
		assertEquals("Erreur, le temps moyen par voyage n'est pas le bon", 10,
				(int) data.getUsers().get(newID).getAVGTime());
	}

	@Test
	public void testGetAVGDist() {
		int newID = data.getReverseUserID().get((long) 11);
		assertEquals("Erreur, la distance moyenne par voyage n'est pas le bon", 10,
				(int) data.getUsers().get(newID).getAVGTime());
	}

	@Test
	public void testSplitTripsOneRoad() {
		User user = new User();
		try {
			user.splitTrips(new Trip(), 0, 1, new ArrayList<Trip>(), param);
			fail("input init must have at least one road");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
