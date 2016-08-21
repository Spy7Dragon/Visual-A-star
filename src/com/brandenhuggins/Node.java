package com.brandenhuggins;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Node implements Comparable<Node>, PropertyChangeListener
{
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

	public int compareTo(Node object) {
		return (int) (this.getF() - object.getF());
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
}
