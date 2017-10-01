package GUIApplications;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe implements ActionListener{

	private JFrame frame;
	private JButton[][] button;
	private boolean isO;
	
	public static void main(String[] args) {
		TicTacToe gui = new TicTacToe();
		gui.start();

	}

	public void start() {
		frame = new JFrame("Grid Layout");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container pane = frame.getContentPane();
		pane.setLayout(new GridLayout(3,3));
		
		button = new JButton[3][3];
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				button[i][j] = new JButton(" ");
				button[i][j].addActionListener(this); 
				pane.add(button[i][j]);
			}
		}

		frame.pack();
		frame.setSize(300, 300);
		frame.setVisible(true);
	}

	
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();
		if(b.getText()==" ") {
			//String state;
			if(isO) {
				b.setText("O");
				//state = "O";
			}
			else {
				b.setText("X");
				//state = "X";
			}
			isO = !isO;
			b.setEnabled(false);
		}
		
	}
}
