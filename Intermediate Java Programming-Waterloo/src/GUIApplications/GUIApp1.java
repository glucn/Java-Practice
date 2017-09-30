package GUIApplications;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIApp1 implements ActionListener {
	private JFrame frame;
	private JButton button;
	
	public static void main(String[] args) {
		GUIApp1 gui = new GUIApp1();
		gui.start();
	}
	
	public void start() {
		frame = new JFrame("My First GUI Application");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		
		JLabel label = new JLabel("Hello, World!");
		contentPane.add(label);
		
		button = new JButton("Click Me!");
		button.addActionListener(this);  // listen for actions connected to the button
		contentPane.add(button);
		
		frame.setSize(300,300);
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {  // e - information about the event and its source
		String textToShow;
		textToShow = JOptionPane.showInputDialog(
						frame,
						"Enter the text you want to display:",
						"Input Dialog",
						JOptionPane.QUESTION_MESSAGE);  // if user click [Cancel], the dialog will return null
		if(textToShow != null) {
			button.setForeground(Color.RED);
			button.setText(textToShow);
		}
	}
	
}
