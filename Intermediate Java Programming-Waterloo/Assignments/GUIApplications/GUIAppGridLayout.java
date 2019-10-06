package GUIApplications;

import java.awt.*;
import javax.swing.*;

public class GUIAppGridLayout {

	private JFrame frame;
	
	public static void main(String[] args) {
		GUIAppGridLayout gui = new GUIAppGridLayout();
		gui.start();

	}

	public void start() {
		frame = new JFrame("Grid Layout");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container pane = frame.getContentPane();
		pane.setLayout(new GridLayout(3,2));
		
		pane.add(new JButton("First Button"));
		pane.add(new JButton("Second Button - Longer than others"));
		pane.add(new JButton("Third Button"));
		pane.add(new JButton("Last Button"));
		
		frame.pack();
		frame.setVisible(true);
	}
	
}
