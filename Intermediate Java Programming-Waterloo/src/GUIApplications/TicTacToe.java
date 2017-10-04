package GUIApplications;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TicTacToeButton extends JButton{
	private static final long serialVersionUID = 1L;
	private int row; // start from 0
	private int col; // start from 0
	
	TicTacToeButton(String text, int row, int col){
		super(text);
		this.row = row;
		this.col = col;
	}
	
	public void setIndex(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getCol() {
		return this.col;
	}
}

public class TicTacToe implements ActionListener{
	private static final int SIZE = 5;
	private JFrame frame;
	private TicTacToeButton[][] button;
	private boolean isO;
	
	public static void main(String[] args) {
		TicTacToe gui = new TicTacToe();
		gui.start();

	}

	public void start() {
		frame = new JFrame("Tic-Tac-Toe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container pane = frame.getContentPane();
		pane.setLayout(new GridLayout(SIZE,SIZE));
		
		button = new TicTacToeButton[SIZE][SIZE];
		for(int i=0; i<SIZE; i++) {
			for (int j=0; j<SIZE; j++) {
				TicTacToeButton b = new TicTacToeButton(" ", i, j);
				b.addActionListener(this); 
				pane.add(b);
				button[i][j] = b;
			}
		}

		frame.pack();
		frame.setSize(SIZE*100, SIZE*100);
		frame.setVisible(true);
	}

	
	public void actionPerformed(ActionEvent e) {
		TicTacToeButton b = (TicTacToeButton)e.getSource();
		if(b.getText()==" ") {
			if(isO) {
				b.setText("O");
			}
			else {
				b.setText("X");
			}
			
			int row = b.getRow();
			int col = b.getCol();
			int i, j;
			// check row
			for (j = 0; j < SIZE; ++j) {
				if (!(isO && button[row][j].getText().equals("O")) && !(!isO && button[row][j].getText().equals("X"))) break;
			}
			if(j==SIZE) {
				restart(isO);
				return;
			}
			// check row
			for (i = 0; i < SIZE; ++i) {
				if (!(isO && button[i][col].getText().equals("O")) && !(!isO && button[i][col].getText().equals("X"))) break;
			}
			if(i==SIZE) {
				restart(isO);
				return;
			}
			
			// diagonal
			if(row == col) {
				for(i = 0; i < SIZE; ++i) {
					if (!(isO && button[i][i].getText().equals("O")) && !(!isO && button[i][i].getText().equals("X"))) break;
				}
				if(i==SIZE) {
					restart(isO);
					return;
				}
			}
			
			// counter-diagonal
			if(row + col == SIZE-1) {
				for(i = 0; i < SIZE; ++i) {
					if (!(isO && button[i][SIZE-i-1].getText().equals("O")) && !(!isO && button[i][SIZE-i-1].getText().equals("X"))) break;
				}
				if(i==SIZE) {
					restart(isO);
					return;
				}
			}
			
			isO = !isO;
			b.setEnabled(false);
		}
		
	}
	
	void restart(boolean isO) {
		String message;
		if (isO) {
			message = "O wins the game! Restart?";
		}
		else {
			message = "X wins the game! Restart?";
		}
		int option = JOptionPane.showConfirmDialog(frame, message, "WIN", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					button[i][j].setText(" ");
					button[i][j].setEnabled(true);
				}
			}
		}
		else {
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					button[i][j].setEnabled(false);
				}
			}
		}
	}
}
