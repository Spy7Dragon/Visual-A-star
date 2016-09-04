package com.brandenhuggins;

import java.util.ArrayList;
import java.util.Iterator;

public class Node implements Subject, Comparable<Node>
{
	private int x;
	private int y;
	private char value;
	private double g;
	private double h;
	private Node parent;
	
	private ArrayList<Observer> observers;
	private ArrayList<?> list;
	
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
		
		observers = new ArrayList<Observer>();
		list = new ArrayList<Object>();
	}

	public char getValue() {
		return value;
	}

	public void setValue(char value) throws InterruptedException {
		this.value = value;
		notifyObservers();
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
	public void addObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}
	
	public Iterator<?> iterator()
	{
		return list.iterator();
	}
	
	
	private void notifyObservers() throws InterruptedException
	{
		Iterator<Observer> i = observers.iterator();
		while (i.hasNext())
		{
			Observer o = (Observer) i.next();
			o.update(this);
		}
	}
}
