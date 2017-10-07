package GUIApplications;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

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
		
	}
	
	private class NewOrderListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	private class SaveOrderListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
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
