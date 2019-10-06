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

public class TicTacToe{
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
				b.addActionListener(new buttonListener()); 
				pane.add(b);
				button[i][j] = b;
			}
		}

		makeMenu();
		
		frame.pack();
		frame.setSize(SIZE*100, SIZE*100);
		frame.setVisible(true);
	}
	
	private void win(boolean isO) {
		String message;
		if (isO) {
			message = "O wins the game! Restart?";
		}
		else {
			message = "X wins the game! Restart?";
		}
		int option = JOptionPane.showConfirmDialog(frame, message, "WIN", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			restart(true);
		}
		else {
			restart(false);
		}
	}
	
	private void restart(boolean option) {  
		// option - 
		//   true: clear text on buttons, enable all buttons; 
		//   false: keep text on button, disable all button;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if(option) button[i][j].setText(" ");
				button[i][j].setEnabled(option);
			}
		}
		isO = false;
	}
	
	private void makeMenu() {
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		menu = new JMenu("Game");
		menu.setMnemonic(KeyEvent.VK_G);
		menuBar.add(menu);
		
		menuItem = new JMenuItem("New Game");
		menuItem.addActionListener(new newGameListener());
		menuItem.setMnemonic(KeyEvent.VK_N);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
		menu.add(menuItem);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new exitListener());
		menuItem.setMnemonic(KeyEvent.VK_X);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.CTRL_MASK));
		menu.add(menuItem);
		
		menu = new JMenu("Help");
		menu.setMnemonic(KeyEvent.VK_H);
		menuBar.add(menu);
		
		menuItem = new JMenuItem("About");
		menuItem.addActionListener(new aboutListener());
		menuItem.setMnemonic(KeyEvent.VK_A);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Event.CTRL_MASK));
		menu.add(menuItem);
	}
	
	private class newGameListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			restart(true);
		}
	}
	
	private class exitListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	private class aboutListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(frame, "Tic-Tac-Toe Version 0.1", "About", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	private class buttonListener implements ActionListener{
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
					win(isO);
					return;
				}
				// check row
				for (i = 0; i < SIZE; ++i) {
					if (!(isO && button[i][col].getText().equals("O")) && !(!isO && button[i][col].getText().equals("X"))) break;
				}
				if(i==SIZE) {
					win(isO);
					return;
				}
				
				// diagonal
				if(row == col) {
					for(i = 0; i < SIZE; ++i) {
						if (!(isO && button[i][i].getText().equals("O")) && !(!isO && button[i][i].getText().equals("X"))) break;
					}
					if(i==SIZE) {
						win(isO);
						return;
					}
				}
				
				// counter-diagonal
				if(row + col == SIZE-1) {
					for(i = 0; i < SIZE; ++i) {
						if (!(isO && button[i][SIZE-i-1].getText().equals("O")) && !(!isO && button[i][SIZE-i-1].getText().equals("X"))) break;
					}
					if(i==SIZE) {
						win(isO);
						return;
					}
				}
				isO = !isO;
				b.setEnabled(false);
			}
		}
	}
	
}
