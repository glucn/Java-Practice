package GUIApplications;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class PizzaOrder {

	private JFrame frame;
	private JPanel contentPane;
	
	// radio buttons and button group
	private JRadioButton regularCrustButton;
	private JRadioButton thinCrustButton;
	private JRadioButton handCrustButton;
	private JRadioButton deepCrustButton;
	private ButtonGroup crustButtonGroup;

	// check boxes
	private JCheckBox pepperoniBox;
	private JCheckBox sausageBox;
	private JCheckBox cheeseBox;
	private JCheckBox pepperBox;
	private JCheckBox onionBox;
	private JCheckBox mushroomBox;
	private JCheckBox oliveBox;
	private JCheckBox anchovyBox;

	// text fields
	private JTextField breadSticksText;
	private JTextField buffaloWingsText;
	private JTextField nameText;
	private JTextField addressText;
	private JTextField cityText;
	
	public static void main(String[] args) {
		PizzaOrder po = new PizzaOrder();
		po.start();
	}

	private void start() {
		frame = new JFrame("Pizza Order");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setMenu();
		
		contentPane = (JPanel)frame.getContentPane();
		contentPane.setLayout(new BorderLayout(6,6));
		
		setContent();
		
		frame.pack();
		frame.setVisible(true);
	}
	
	private void setMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu;
		JMenuItem menuItem;
		
		frame.setJMenuBar(menuBar);
		
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);
		
		menuItem = new JMenuItem("New Order");
		menuItem.addActionListener(new NewOrderListener());
		menuItem.setMnemonic(KeyEvent.VK_N);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Save Order");
		menuItem.addActionListener(new SaveOrderListener());
		menuItem.setMnemonic(KeyEvent.VK_S);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
		menu.add(menuItem);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ExitListener());
		menuItem.setMnemonic(KeyEvent.VK_X);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.CTRL_MASK));
		menu.add(menuItem);
		
		menu = new JMenu("Help");
		menu.setMnemonic(KeyEvent.VK_H);
		menuBar.add(menu);
		
		menuItem = new JMenuItem("About Pizza Order");
		menuItem.addActionListener(new AboutListener());
		menuItem.setMnemonic(KeyEvent.VK_A);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Event.CTRL_MASK));
		menu.add(menuItem);
	}
	
	private void setContent() {
		setNorthContent();
		setWestContent();
		setCenterContent();
		setEastContent();
		setSouthContent();
	}
	
	private void setNorthContent() {
		JLabel imgLabel = new JLabel(new ImageIcon("Resources-njv-1-L08-06.jpg"), JLabel.CENTER);
		contentPane.add(imgLabel, BorderLayout.NORTH);
	}
	
	private void setWestContent() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createTitledBorder("Choose a Crust"));
		
		crustButtonGroup = new ButtonGroup();
		
		// regularCrustButton
		regularCrustButton = new JRadioButton("Regular Crust", true);
		crustButtonGroup.add(regularCrustButton);
		panel.add(regularCrustButton);
		
		// thinCrustButton
		thinCrustButton = new JRadioButton("Thin Crust", false);
		crustButtonGroup.add(thinCrustButton);
		panel.add(thinCrustButton);
		
		// handCrustButton
		handCrustButton = new JRadioButton("Hand-Tossed Crust", false);
		crustButtonGroup.add(handCrustButton);
		panel.add(handCrustButton);
		
		// deepCrustButton
		deepCrustButton = new JRadioButton("Deep-Dish Crust", false);
		crustButtonGroup.add(deepCrustButton);
		panel.add(deepCrustButton);
		
		contentPane.add(panel, BorderLayout.WEST);
	}
	
	private void setCenterContent() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createTitledBorder("Select Toppings"));
		
		pepperoniBox = new JCheckBox("Pepperoni", false);
		panel.add(pepperoniBox);
		
		sausageBox = new JCheckBox("Sausage", false);
		panel.add(sausageBox);
		
		cheeseBox = new JCheckBox("Extra Cheese", false);
		panel.add(cheeseBox);

		pepperBox = new JCheckBox("Bell Peppers", false);
		panel.add(pepperBox);

		onionBox = new JCheckBox("Onions", false);
		panel.add(onionBox);
		
		mushroomBox = new JCheckBox("Mushrooms", false);
		panel.add(mushroomBox);

		oliveBox = new JCheckBox("Olives", false);
		panel.add(oliveBox);

		anchovyBox = new JCheckBox("Anchovies", false);
		panel.add(anchovyBox);
		
		contentPane.add(panel, BorderLayout.CENTER);
	}
	
	private void setEastContent() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createTitledBorder("Sides (Enter Quantity)"));
		panel.setPreferredSize(new Dimension(150, 0));
		
		JPanel linePanel = new JPanel();
		linePanel.setLayout(new BoxLayout(linePanel,BoxLayout.X_AXIS));
		
		breadSticksText = new JTextField("",2);
		breadSticksText.setMaximumSize(new Dimension(20,24));
		linePanel.add(breadSticksText);
		linePanel.add(new JLabel(" Bread Sticks"));
		linePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(linePanel);
		
		linePanel = new JPanel();
		linePanel.setLayout(new BoxLayout(linePanel,BoxLayout.X_AXIS));
		
		buffaloWingsText = new JTextField("",2);
		buffaloWingsText.setMaximumSize(new Dimension(20,24));
		linePanel.add(buffaloWingsText);
		linePanel.add(new JLabel(" Buffalo Wings"));
		linePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(linePanel);
		
		contentPane.add(panel, BorderLayout.EAST);
	}
	
	private void setSouthContent() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
		panel.setBorder(BorderFactory.createTitledBorder("Deliver To:"));
		
		JPanel smallPanel = new JPanel();
		smallPanel.setLayout(new BoxLayout(smallPanel,BoxLayout.Y_AXIS));
		smallPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		smallPanel.add(new JLabel("Name:"));
		smallPanel.add(new JLabel("Address:"));
		smallPanel.add(new JLabel("City, St, Zip:"));
		panel.add(smallPanel);
		
		smallPanel = new JPanel();
		smallPanel.setLayout(new BoxLayout(smallPanel,BoxLayout.Y_AXIS));
		smallPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		nameText = new JTextField();
		addressText = new JTextField();
		cityText = new JTextField();
		smallPanel.add(nameText);
		smallPanel.add(addressText);
		smallPanel.add(cityText);
		panel.add(smallPanel);
		
		contentPane.add(panel, BorderLayout.SOUTH);
	}
	
	private class NewOrderListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			regularCrustButton.setSelected(true);
			pepperoniBox.setSelected(false);
			sausageBox.setSelected(false);
			cheeseBox.setSelected(false);
			pepperBox.setSelected(false);
			onionBox.setSelected(false);
			mushroomBox.setSelected(false);
			oliveBox.setSelected(false);
			anchovyBox.setSelected(false);
			breadSticksText.setText("");
			buffaloWingsText.setText("");
			nameText.setText("");
			addressText.setText("");
			cityText.setText("");
		}
	}
	
	private class SaveOrderListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String order =	"Pizza Order\n" +
							"===========\n" +
					        "Crust:\n";
			if (regularCrustButton.isSelected())
				   order += "     Regular\n";
			else if (thinCrustButton.isSelected())
				   order += "     Thin\n";
			else if (deepCrustButton.isSelected())
				   order += "     Deep-Dish\n";
			else if (handCrustButton.isSelected())
				   order += "     Hand-Tossed\n";
			else
				   JOptionPane.showMessageDialog(frame, 
				   "You must select a crust type!",
				   "Crust Type Error", 
				   JOptionPane.ERROR_MESSAGE);
			order += "Toppings:\n";
			if (pepperoniBox.isSelected())
			   order += "     Pepperoni\n";
			if (sausageBox.isSelected())
			   order += "     Sausage\n";
			if (cheeseBox.isSelected())
			   order += "     Extra Cheese\n";
			if (pepperBox.isSelected())
			   order += "     Peppers\n";
			if (onionBox.isSelected())
			   order += "     Onions\n";
			if (mushroomBox.isSelected())
			   order += "     Mushrooms\n";
			if (oliveBox.isSelected())
			   order += "     Olives\n";
			if (anchovyBox.isSelected())
			   order += "     Anchovies\n";
			int bs = 0;
			int bw = 0;
			try
			{
			   if (!breadSticksText.getText().isEmpty())
			      bs = Integer.parseInt(breadSticksText.getText());
			   if (!buffaloWingsText.getText().isEmpty())
			      bw = Integer.parseInt(buffaloWingsText.getText());
			}
			catch (NumberFormatException nfe)
			{
			   JOptionPane.showMessageDialog(frame, 
			      "Side order entries must be numeric,\n" +
			      "and must be whole numbers", 
			      "Side Order Error", 
			      JOptionPane.ERROR_MESSAGE);
			}
			if (bs > 0 || bw > 0)
			{
			   order += "Sides:\n";
			   if (bs > 0)
			      order += "     " + bs + " Bread Sticks\n";
			   if (bw > 0)
			      order += "     " + bw + " Buffalo Wings\n";
			}
			
			if (nameText.getText().isEmpty() ||
					   addressText.getText().isEmpty() ||
					   cityText.getText().isEmpty())
					JOptionPane.showMessageDialog(frame, 
					   "Address fields may not be empty.",
					   "Address Error", 
					   JOptionPane.ERROR_MESSAGE);
			else{
				order += "Deliver To:\n";
				order += "     " + nameText.getText() + "\n";
				order += "     " + addressText.getText() + "\n";
				order += "     " + cityText.getText() + "\n";
			}
			order += "\n***END OF ORDER ***\n";
					   
			try {
				PrintStream oFile = new PrintStream("PizzaOrder.txt");
				oFile.print(order);
				oFile.close();
			}
			catch(IOException ioe) {
				System.out.println("\n*** I/O Error ***\n" + ioe);
			}
		}
	}
	
	private class ExitListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	private class AboutListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(frame, "Pizza Order\n\nVersion 1.0\n(c) Copyright Gary Lu 2017", "About Pizza Order", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
