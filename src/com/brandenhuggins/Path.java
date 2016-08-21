package com.brandenhuggins;

public class Path {

	final static int rows = 13;
	final static int cols = 30;
	
	public static void main(String[] args) {
		
		// Populate the map with nodes.
		NodeMap map = new NodeMap(rows, cols);
		// Choose a starting node.
		map.assignRandom('@');
		// Choose node to find.
		map.assignRandom('$');
		// Traverse the map.
		map.traverse();
		// Print map.
		map.printMap();
	}
}
