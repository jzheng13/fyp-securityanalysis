package application;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JComboBox;

public class Account extends JFrame {

	private JPanel contentPane;
	private JTextField serviceProvider;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Account frame = new Account();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Account() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1312, 772);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddDetails = new JButton("Add Details");
		btnAddDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO perform database input
			}
		});
		btnAddDetails.setBounds(980, 585, 249, 41);
		contentPane.add(btnAddDetails);
		
		JLabel lblServiceProvider = new JLabel("Service Provider");
		lblServiceProvider.setBounds(83, 96, 231, 33);
		contentPane.add(lblServiceProvider);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(83, 157, 173, 33);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(83, 218, 115, 33);
		contentPane.add(lblPassword);
		
		serviceProvider = new JTextField();
		serviceProvider.setBounds(327, 93, 325, 39);
		contentPane.add(serviceProvider);
		serviceProvider.setColumns(10);
		
		username = new JTextField();
		username.setBounds(327, 154, 325, 39);
		contentPane.add(username);
		username.setColumns(10);
		
		JCheckBox chckbxSingleSignon = new JCheckBox("Single Sign-on");
		chckbxSingleSignon.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Show SSO input
			}
		});
		chckbxSingleSignon.setBounds(709, 153, 221, 41);
		contentPane.add(chckbxSingleSignon);
		
		password = new JPasswordField();
		password.setBounds(327, 215, 325, 39);
		contentPane.add(password);
		
		JLabel lblAddAccount = new JLabel("Add an account");
		lblAddAccount.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblAddAccount.setBounds(26, 28, 307, 33);
		contentPane.add(lblAddAccount);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(83, 279, 115, 33);
		contentPane.add(lblEmail);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setMaximumRowCount(100);
		comboBox.setEditable(true);
		comboBox.setBounds(327, 276, 325, 39);
		contentPane.add(comboBox);
	}
}
