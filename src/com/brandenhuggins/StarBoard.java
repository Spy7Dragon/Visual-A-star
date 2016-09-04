package com.brandenhuggins;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import java.awt.GridLayout;
import java.lang.reflect.InvocationTargetException;

public class StarBoard implements Observer {

	private JFrame frame;

	final static int rows = 50;
	final static int cols = 50;
	private static NodeMap map;

	private JLabel[][] labelNodes;

	/**
	 * Launch the application.
	 * @throws InterruptedException 
	 * @throws InvocationTargetException 
	 */
	public static void main(String[] args) throws InvocationTargetException, InterruptedException {

		while(true)
		{


			try {
				// Populate the map with nodes.
				map = new NodeMap(rows, cols);
				// Create visual.
				StarBoard window = new StarBoard();
				window.frame.setVisible(true);
				window.frame.getContentPane().setVisible(true);	
				window.frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

				// Choose a starting node.
				map.assignRandom('@');
				// Choose node to find.
				map.assignRandom('$');
				// Traverse the map.
				map.traverse();

				window.frame.dispose();

			} catch (Exception e) {
				e.printStackTrace();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
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
		frame.setBounds(0, 0, 800, 800);
		frame.getContentPane().setLayout(new GridLayout(rows, cols));

		labelNodes = new JLabel[rows][cols];
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				labelNodes[i][j] = new JLabel();
				Color bgColor;
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
					bgColor = Color.BLACK;
					break;
				}
				}
				labelNodes[i][j].setOpaque(true);
				labelNodes[i][j].setBackground(bgColor);
				labelNodes[i][j].setVisible(true);
				frame.getContentPane().add(labelNodes[i][j]);
				map.getMap()[i][j].addObserver(this);
			}
		} 
		frame.revalidate();
		frame.repaint();
	}

	@Override
	public void update(Subject o) throws InterruptedException {
		int x = ((Node) o).getX();
		int y = ((Node) o).getY();
		char value = ((Node) o).getValue();
		Color newColor;

		switch (value)
		{
		case '*':
		{
			newColor = Color.GREEN;
			break;
		}
		case '#':
		{
			newColor = Color.GRAY;
			break;
		}
		case '$':
		{
			newColor = Color.YELLOW;
			break;
		}
		case '@':
		{
			newColor = Color.RED;
			break;
		}
		case '+':
		{
			newColor = Color.ORANGE;
			break;
		}
		default:
		{
			newColor = Color.BLACK;
			break;
		}
		}
		labelNodes[x][y].setBackground(newColor);
		frame.revalidate();
		frame.repaint();
	}

}
