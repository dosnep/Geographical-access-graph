package com.geoGraph.GeographicalAccessGraph;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TripTest {

	Trip sequence;

	@Before
	public void init() {

		sequence = new Trip();
		for (int i = 0; i < 10; i++) {
			try {
				sequence.getRoads().add(new Road(0, 1, 1, 1));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testGetDist() {
		assertEquals(10, (int) sequence.getDist());

	}

	@Test
	public void testGetTime() {
		assertEquals(10, (int) sequence.getTime());

	}

	@After
	public void destroy() {
		sequence = null;
	}

}
