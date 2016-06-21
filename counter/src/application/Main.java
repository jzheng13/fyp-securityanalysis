package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JPanel{

	/**
	 * Create the panel.
	 */
	public Main(AppFrame parent) {
		//this.setBackground(Color.white);
		this.setBounds(0, 0, 0, 0);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		
		JLabel lblStart = new JLabel("Proceed by choosing any of the following");
		lblStart.setBounds(26, 28, 769, 57);
		lblStart.setFont(new Font("Tahoma", Font.BOLD, 34));
		this.add(lblStart);
		
		JButton btnNewUser = new JButton("Add new user");
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewUser newUser = new NewUser(parent);
				parent.addPanel(newUser);
			}
		});
		btnNewUser.setBounds(551, 267, 209, 41);
		this.add(btnNewUser);
		
		JButton btnExistingUser = new JButton("Existing user");
		btnExistingUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ExistUser existUser = new ExistUser(parent);
				existUser.setVisible(true);
				parent.addPanel(existUser);
			}
		});
		btnExistingUser.setBounds(551, 370, 209, 41);
		this.add(btnExistingUser);
	}
}
