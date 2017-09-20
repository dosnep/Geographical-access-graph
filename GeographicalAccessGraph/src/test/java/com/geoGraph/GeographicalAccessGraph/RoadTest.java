package com.geoGraph.GeographicalAccessGraph;

import static org.junit.Assert.fail;

import org.junit.Test;

public class RoadTest {

	private Road road;

	@Test
	public void testRoadSamePOI() {
		try {
			road = new Road(0, 0, 0, 1);
			fail("firstPOI and second are equal, expect an error, firstPOI and secondPOI must be different");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testRoadNegativeDistance() {
		try {
			road = new Road(0, 1, -4, 0);
			fail("distance is negative");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testRoadNegativeTime() {
		try {
			road = new Road(0, 1, 1, -4);
			fail("Time is negative");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSetFirstPOI() {
		try {
			road = new Road(0, 1, 0, 1);
			road.setFirstPOI(1);
			fail("firstPOI and second are equal, expect an error, firstPOI and secondPOI must be different");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testSetSecondPOI() {
		try {
			road = new Road(0, 1, 0, 1);
			road.setSecondPOI(0);
			fail("firstPOI and second are equal, expect an error, firstPOI and secondPOI must be different");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testSetDistance() {
		try {
			road = new Road(0, 1, 0, 1);
			road.setDistance(-5);
			fail("distance is negative");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testSetTime() {
		try {
			road = new Road(0, 1, 0, 1);
			road.setTime(-5);
			fail("time is negative");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
