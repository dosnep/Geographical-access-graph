package com.geoGraph.GeographicalAccessGraph;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.geoGraph.GeographicalAccessGraph.MyGraph.MyOwnEdge;

public class MyOwnEdgeTest {

	MyOwnEdge<Integer> edgeTest;

	@Before
	public void init() {

		edgeTest = new MyOwnEdge<Integer>(0, 1);

	}

	@Test
	public void testEqualsObject() {

		MyOwnEdge<Integer> edgeTestToCompare = new MyOwnEdge<Integer>(0, 1);
		assertTrue("The two edges should be equal", edgeTest.equals(edgeTestToCompare));

	}

	@Test
	public void testIncrTimeNegative() {
		try {
			edgeTest.incrTime(-5);
			fail("time to add must be positive");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testSetDistanceNegative() {
		try {
			edgeTest.setDistance(-5);
			fail("distance must be positive");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
