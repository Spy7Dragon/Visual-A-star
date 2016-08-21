package com.brandenhuggins;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class NodeMap{

	private Node[][] map;

	private int rows;
	private int cols;

	private Node start;
	private Node finish;

	NodeMap(int theRows, int theCols)
	{
		rows = theRows;
		cols = theCols;
		map = new Node[rows][cols];
		populate();
		start = null;
		finish = null;
	}

	private void populate() {
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				int random = (int) (Math.random() * 4);
				if (random == 1)
				{
					Node newNode = new Node(i, j, '#');
					map[i][j] = newNode;
				}
				else
				{
					Node newNode = new Node(i, j, '*');
					map[i][j] = newNode;
				}
				
			}
		}
		
	}

	public void assignRandom( char c) {
		boolean assigned = false;

		while (!assigned)
		{
			int x = (int) (Math.random() * rows);
			int y = (int) (Math.random() * cols);
			if (map[x][y].getValue() == '*')
			{
				map[x][y].setValue(c);
				assigned = true;

				if (c == '$')
				{
					finish = map[x][y];
					assignH();
				}

				if (c == '@')
				{
					start = map[x][y];
				}
			}
		}
	}

	private void assignH() {
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{				
				map[i][j].setH(difference(finish, map[i][j]));
			}
		}
	}

	public void traverse() {
		Queue<Node> nodeList = new PriorityQueue<Node>();
		ArrayList<Node> closedList = new ArrayList<Node>();
		nodeList.add(start);

		while (!nodeList.isEmpty())
		{
			clearTrail();
			// find the node with the least f
			Node q = nodeList.poll();
			// Generate q's successors and set their parent to q.
			ArrayList<Node> successors = new ArrayList<Node>();
			// Check top.
			if (q.getX() > 0)
			{
				if ((map[q.getX() - 1][q.getY()].getValue() == '*' ||
						map[q.getX() - 1][q.getY()].getValue() == '$') &&
						!map[q.getX() - 1][q.getY()].equals(q.getParent()))
				{
					successors.add(map[q.getX() - 1][q.getY()]);
				}
			}
			// Check right.
			if (q.getY() < cols - 1)
			{
				if ((map[q.getX()][q.getY() + 1].getValue() == '*' ||
						map[q.getX()][q.getY() + 1].getValue() == '$') &&
						!map[q.getX()][q.getY() + 1].equals(q.getParent()))
				{
					successors.add(map[q.getX()][q.getY() + 1]);
				}
			}	
			// Check bottom.
			if (q.getX() < rows - 1)
			{
				if ((map[q.getX() + 1][q.getY()].getValue() == '*' ||
						map[q.getX() + 1][q.getY()].getValue() == '$') &&
						!map[q.getX() + 1][q.getY()].equals(q.getParent()))
				{
					successors.add(map[q.getX() + 1][q.getY()]);
				}
			}
			// Check left.
			if (q.getY() > 0)
			{
				if ((map[q.getX()][q.getY() - 1].getValue() == '*' ||
						map[q.getX()][q.getY() - 1].getValue() == '$') &&
						!map[q.getX()][q.getY() - 1].equals(q.getParent()))
				{
					successors.add(map[q.getX()][q.getY() - 1]);
				}
			}

			for (Node successor: successors)
			{
				boolean add = true;
				boolean success = false;
				if (successor.getValue() == '$')
				{
					success = true;
				}
				double g = q.getG() + 1;
				
				if (success)
				{			
					successor.setG(g);
					successor.setParent(q);
					printTrail(successor);
					return;
				}

				for (Node open: nodeList)
				{
					if (open.getX() == successor.getX() &&
							open.getY() == successor.getY())
					{
						if (open.getF() < g + successor.getH())
						{
							add = false;
							break;
						}
					}
				}

				for (Node closed: closedList)
				{
					if (closed.getX() == successor.getX() &&
							closed.getY() == successor.getY())
					{
						if (closed.getF() < g + successor.getH())
						{	
							add = false;
							break;
						}
					}
				}

				if (add)
				{			
					successor.setG(g);
					successor.setParent(q);
					nodeList.add(successor);
				}
			}
			closedList.add(q);
			printTrail(q);
			printMap();
		}
	}
	
	private void clearTrail()
	{
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				if (map[i][j].getValue() == '+')
				{
					map[i][j].setValue('*');
				}
			}
		}
	}

	private void printTrail(Node success) {

		Node trailNode = success;

		while (trailNode.getParent() != null)
		{
			if (trailNode.equals(start) ||
					trailNode.equals(finish))
			{
				
			}
			else
			{
				trailNode.setValue('+');			
			}	
			trailNode = trailNode.getParent();
		}
	}

	private double difference(Node node, Node node2) {
		return Math.sqrt(Math.pow(node.getX() - node2.getX(), 2) + Math.pow(node.getY() - node2.getY(), 2));
	}

	public Node[][] getMap() {
		return map;
	}
	
	public void printMap() {
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				System.out.print(map[i][j].getValue());
			}
			System.out.println();
		}
		System.out.println();
	}
}
