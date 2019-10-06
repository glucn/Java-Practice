package GUIApplications;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TextEditor implements ActionListener{

	private JFrame frame;
	private JLabel label;
	private JPanel leftPanel, rightPanel;
	private JButton buttonWrap, buttonNoWrap, buttonClear, buttonScrollVer, buttonScrollHori, buttonScrollBoth, buttonNoScroll;
	private JTextArea textArea;
	private JScrollPane scrollArea;
	
	public static void main(String[] args) {
		TextEditor gui = new TextEditor();
		gui.start();

	}

	public void start() {
		frame = new JFrame("Text Editor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container pane = frame.getContentPane();
		pane.setLayout(new BorderLayout());
		
		label = new JLabel("Use the buttons to control the layout of the text you type.");
		pane.add(label, BorderLayout.NORTH);
		
		leftPanel = new JPanel();
		pane.add(leftPanel, BorderLayout.WEST);
		leftPanel.setLayout(new GridLayout(4,1));
		
		
		buttonWrap = new JButton("Wrap Text");
		buttonWrap.addActionListener(this);
		leftPanel.add(buttonWrap);
		
		buttonNoWrap = new JButton("Do Not Wrap Text");
		buttonNoWrap.addActionListener(this);
		leftPanel.add(buttonNoWrap);
		
		JButton blank = new JButton(" ");
		blank.setEnabled(false);
		leftPanel.add(blank);
		
		buttonClear = new JButton("Clear Text");
		buttonClear.addActionListener(this);
		leftPanel.add(buttonClear);
		
		rightPanel = new JPanel();
		pane.add(rightPanel, BorderLayout.EAST);
		rightPanel.setLayout(new GridLayout(4,1));
	
		buttonScrollVer = new JButton("Scroll Vertically");
		buttonScrollVer.addActionListener(this);
		rightPanel.add(buttonScrollVer);
		
		buttonScrollHori = new JButton("Scroll Horizontally");
		buttonScrollHori.addActionListener(this);
		rightPanel.add(buttonScrollHori);
		
		buttonScrollBoth = new JButton("Scroll Both Ways");
		buttonScrollBoth.addActionListener(this);
		rightPanel.add(buttonScrollBoth);
		
		buttonNoScroll = new JButton("Do Not Scroll");
		buttonNoScroll.addActionListener(this);
		rightPanel.add(buttonNoScroll);
		
		textArea = new JTextArea(10,25);
		scrollArea = new JScrollPane(textArea);
		scrollArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.add(scrollArea, BorderLayout.CENTER);

		frame.pack();
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();
		//buttonWrap, buttonNoWrap, buttonClear, buttonScrollVer, buttonScrollHori, buttonScrollBoth, buttonNoScroll;
		if(b==buttonWrap) {
			this.WrapText();
		}
		else if(b==buttonNoWrap) {
			this.UnWrapText();
		}
		else if(b==buttonClear) {
			this.ClearText();
		}
		else if(b==buttonScrollVer) {
			this.EnableVerticalScroll();
		}
		else if(b==buttonScrollHori) {
			this.EnableHorizontalScroll();
		}
		else if(b==buttonScrollBoth) {
			this.EnableVerticalScroll();
			this.EnableHorizontalScroll();
		}
		else if(b==buttonNoScroll) {
			this.DisableScroll();
		}
		else {
			//not supposed to be here
		}
	}
	
	private void WrapText() {
		textArea.setLineWrap(true);
	}
	
	private void UnWrapText() {
		textArea.setLineWrap(false);
	}
	
	private void ClearText() {
		textArea.setText("");
	}
	
	private void EnableVerticalScroll() {
		scrollArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	}
	
	private void EnableHorizontalScroll() {
		scrollArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}
	
	private void DisableScroll() {
		scrollArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	}
}
