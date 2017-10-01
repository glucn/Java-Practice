package GUIApplications;

import java.awt.*;
import javax.swing.*;

public class GUIAppBoxLayout {

	private JFrame frame;
	
	public static void main(String[] args) {
		GUIAppBoxLayout gui = new GUIAppBoxLayout();
		gui.start();

	}

	public void start() {
		frame = new JFrame("Box Layout");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container pane = frame.getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		//pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
		
		pane.add(new JButton("First Button"));
		pane.add(new JButton("Second Button - Longer than others"));
		pane.add(new JButton("Third Button"));
		pane.add(new JButton("Last Button"));
		
		frame.pack();
		frame.setVisible(true);
	}
	
}
