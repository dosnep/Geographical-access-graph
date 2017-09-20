package com.geoGraph.GeographicalAccessGraph;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class utilsTest {

	@Test
	public void testUnion() {
		List<Integer> firstList = new ArrayList<Integer>();
		List<Integer> secondList = new ArrayList<Integer>();
		List<Integer> toTest = new ArrayList<Integer>();

		for (int i = 1; i < 11; i++) {
			if (i < 6)
				firstList.add(i);
			else
				secondList.add(i);
			toTest.add(i);
		}

		List<Integer> union = AdditionnalFunctions.union(firstList, secondList);
		assertEquals("Erreur lors de l'union de deux listes", toTest, union);

	}

	@Test
	public void testGetMyneghbor() {
		try {
			Parser.setParamFile();
			String[] tab = { "1", "2" };
			Parameters param = new Parameters(2, 0, 0, tab, tab);
			Datas data = new Datas(param);
			data.setGraph(MyGraph.createAllPOISStringGraph(data));
			int newID = data.getReversePOIID().get((long) 2);
			List<Integer> neighboors = AdditionnalFunctions.getMyneghbor(newID, 1, data.getGraph());
			List<Integer> toTest = new ArrayList<Integer>();
			toTest.add(data.getReversePOIID().get((long) 1));
			toTest.add(data.getReversePOIID().get((long) 3));
			assertEquals("", toTest, neighboors);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
