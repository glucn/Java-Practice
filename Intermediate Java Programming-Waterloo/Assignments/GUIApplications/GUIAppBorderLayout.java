package GUIApplications;

import java.awt.*;
import javax.swing.*;

public class GUIAppBorderLayout {

	private JFrame frame;
	
	public static void main(String[] args) {
		GUIAppBorderLayout gui = new GUIAppBorderLayout();
		gui.start();

	}

	public void start() {
		frame = new JFrame("Border Layout");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container pane = frame.getContentPane();
		pane.setLayout(new BorderLayout());
		
		pane.add(new JButton("North Button"), BorderLayout.NORTH);
		pane.add(new JButton("South Button"), BorderLayout.SOUTH);
		pane.add(new JButton("West Button"), BorderLayout.WEST);
		pane.add(new JButton("East Button"), BorderLayout.EAST);
		pane.add(new JButton("Center Button"), BorderLayout.CENTER);
		
		frame.pack();
		frame.setVisible(true);
	}
	
}
