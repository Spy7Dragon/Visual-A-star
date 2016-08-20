package com.brandenhuggins;

public class Node implements Comparable{

	private int x;
	private int y;
	private char value;
	private double g;
	private double h;
	private Node parent;
	
	public Node getParent() {
		return parent;
	}

	public double getG() {
		return g;
	}

	public void setG(double g) {
		this.g = g;
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}

	Node(int theX, int theY, char theValue)
	{
		x = theX;
		y = theY;
		value = theValue;
		g = 0.0;
		h = 0.0;
		
		parent = null;
	}

	public char getValue() {
		return value;
	}

	public void setValue(char value) {
		this.value = value;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public double getF()
	{
		return getG() + getH();
	}

	public void setParent(Node q) {
		parent = q;
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Node)
		{
			return (int) (this.getF() - (((Node) o).getF()));
		}
		else
		{
			System.out.println("Comparing invalid Node.");
			return 34404;
		}
	}
}
