package GUIApplications;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CountDown implements ActionListener {

	private JFrame frame;
	private Container pane;
	private JButton button;
	private JLabel label;
	
	public static void main(String[] args) {
		CountDown gui = new CountDown();
		gui.start();
	}
	
	public void start() {
		frame = new JFrame("test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane = frame.getContentPane();
		
		label = new JLabel("Hello World!");
		
		button = new JButton("5");
		button.addActionListener(this);
		pane.add(button);
		
		frame.setSize(200, 200);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		Integer num = Integer.parseInt(button.getText());
		if (num>=1) {
			button.setText(Integer.toString(num-1));
		}
		else {
			pane.add(label);
			button.setVisible(false);
		}
			
	}
}
