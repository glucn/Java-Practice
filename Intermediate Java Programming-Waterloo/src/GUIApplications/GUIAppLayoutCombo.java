package GUIApplications;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIAppLayoutCombo implements ActionListener{

	private JFrame frame;
	private JLabel label;
	private JPanel leftPanel;
	private JButton[] button;
	private JTextArea textArea;
	private JScrollPane scrollArea;
	
	public static void main(String[] args) {
		GUIAppLayoutCombo gui = new GUIAppLayoutCombo();
		gui.start();

	}

	public void start() {
		frame = new JFrame("Border Layout");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container pane = frame.getContentPane();
		pane.setLayout(new BorderLayout());
		
		label = new JLabel("Click the button and check the text area.");
		pane.add(label, BorderLayout.NORTH);
		
		leftPanel = new JPanel();
		pane.add(leftPanel, BorderLayout.WEST);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		
		button = new JButton[4];
		for(int i=0; i<4; i++) {
			button[i] = new JButton("Button " + (i+1));
			button[i].addActionListener(this);
			leftPanel.add(button[i]);
		}
		
		textArea = new JTextArea(10,25);
		scrollArea = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.add(scrollArea, BorderLayout.CENTER);

		frame.pack();
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();
		
		for(int i=0; i<4; i++) {
			if (button[i] == b) {
				textArea.append("Button "+ (i+1) + " is clicked.\n");
			}
		}
	}
}
