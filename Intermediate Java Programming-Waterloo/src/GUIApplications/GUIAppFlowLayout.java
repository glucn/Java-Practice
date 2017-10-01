package GUIApplications;

import java.awt.*;
import javax.swing.*;

public class GUIAppFlowLayout {

	private JFrame frame;
	
	public static void main(String[] args) {
		GUIAppFlowLayout gui = new GUIAppFlowLayout();
		gui.start();

	}

	public void start() {
		frame = new JFrame("Flow Layout");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container pane = frame.getContentPane();
		pane.setLayout(new FlowLayout());
		
		pane.add(new JButton("First Button"));
		pane.add(new JButton("Second Button - Longer than others"));
		pane.add(new JButton("Third Button"));
		pane.add(new JButton("Last Button"));
		
		frame.pack();
		frame.setVisible(true);
	}
	
}
