package com.brandenhuggins;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridLayout;

public class StarBoard {

	private JFrame frame;
	private JLabel[][] grid;

	final static int rows = 18;
	final static int cols = 30;
	private static NodeMap map;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		// Populate the map with nodes.
		map = new NodeMap(rows, cols);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Create visual.
					StarBoard window = new StarBoard();
					window.frame.setVisible(true);
					window.frame.getContentPane().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		// Choose a starting node.
		map.assignRandom('@');
		// Choose node to find.
		map.assignRandom('$');
		// Traverse the map.
		map.traverse();
	}

	/**
	 * Create the application.
	 */
	public StarBoard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Visual A*");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 500, 500);
		frame.getContentPane().setLayout(new GridLayout(rows, cols));

		grid = new JLabel[rows][cols];
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				grid[i][j] = new JLabel();
				Color bgColor = Color.BLACK;
				switch (map.getMap()[i][j].getValue())
				{
				case '*':
				{
					bgColor = Color.GREEN;
					break;
				}
				case '#':
				{
					bgColor = Color.GRAY;
					break;
				}
				default:
				{
					break;
				}
				}
				grid[i][j].setOpaque(true);
				grid[i][j].setBackground(bgColor);
				grid[i][j].setVisible(true);
				frame.getContentPane().add(grid[i][j]);
			}
		} 
		frame.revalidate();
		frame.repaint();
	}

}
