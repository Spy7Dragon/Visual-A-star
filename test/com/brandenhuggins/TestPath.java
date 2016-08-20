package com.brandenhuggins;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestPath {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPrintNewMap() {
		NodeMap testMap = new NodeMap(Path.rows, Path.cols);
		testMap.printMap();
		System.out.println();
	}

	@Test
	public void testAssignAt()
	{
		NodeMap testMap = new NodeMap(Path.rows, Path.cols);
		testMap.assignRandom('@');
		testMap.printMap();
		System.out.println();
	}
	
	@Test
	public void testAssignGoal()
	{
		NodeMap testMap = new NodeMap(Path.rows, Path.cols);
		testMap.assignRandom('@');
		testMap.assignRandom('$');
		testMap.printMap();
		System.out.println();
	}
}
