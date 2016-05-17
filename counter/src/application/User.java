package application;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class User extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField birthday;
	private JTextField currentCity;
	private JTextField mobile;
	private JTextField hometown;
	private JTextField job;
	private JTextField workplace;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User frame = new User();
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
	public User() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1312, 772);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddUser = new JLabel("Add a user");
		lblAddUser.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblAddUser.setBounds(26, 28, 307, 33);
		contentPane.add(lblAddUser);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(83, 96, 231, 33);
		contentPane.add(lblName);
		
		JLabel lblBirthday = new JLabel("Birthday");
		lblBirthday.setBounds(83, 157, 173, 33);
		contentPane.add(lblBirthday);
		
		JLabel lblCurrCity = new JLabel("Current City");
		lblCurrCity.setBounds(83, 279, 147, 33);
		contentPane.add(lblCurrCity);
		
		name = new JTextField();
		name.setBounds(327, 93, 325, 39);
		contentPane.add(name);
		name.setColumns(10);
		
		// TODO: change to date selector
		birthday = new JTextField();
		birthday.setBounds(327, 154, 325, 39);
		contentPane.add(birthday);
		birthday.setColumns(10);
		
		currentCity = new JTextField();
		currentCity.setBounds(327, 276, 325, 39);
		contentPane.add(currentCity);
		currentCity.setColumns(10);
		
		JButton btnAddUser = new JButton("Finish");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO perform database input
			}
		});
		btnAddUser.setBounds(985, 585, 244, 41);
		contentPane.add(btnAddUser);
		
		JLabel lblMobileNo = new JLabel("Mobile no.");
		lblMobileNo.setBounds(83, 218, 173, 33);
		contentPane.add(lblMobileNo);
		
		mobile = new JTextField();
		mobile.setBounds(327, 215, 325, 39);
		contentPane.add(mobile);
		mobile.setColumns(10);
		
		JLabel lblHometown = new JLabel("Hometown");
		lblHometown.setBounds(83, 340, 147, 33);
		contentPane.add(lblHometown);
		
		hometown = new JTextField();
		hometown.setBounds(327, 337, 325, 39);
		contentPane.add(hometown);
		hometown.setColumns(10);
		
		JLabel lblJob = new JLabel("Job");
		lblJob.setBounds(83, 401, 115, 33);
		contentPane.add(lblJob);
		
		job = new JTextField();
		job.setBounds(327, 398, 325, 39);
		contentPane.add(job);
		job.setColumns(10);
		
		JLabel lblWorkplace = new JLabel("Workplace");
		lblWorkplace.setBounds(83, 465, 147, 33);
		contentPane.add(lblWorkplace);
		
		workplace = new JTextField();
		workplace.setBounds(327, 462, 325, 39);
		contentPane.add(workplace);
		workplace.setColumns(10);
		
		JButton btnAddMoreDetails = new JButton("Add More Details");
		btnAddMoreDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: frame for miscellaneous information
			}
		});
		btnAddMoreDetails.setBounds(726, 585, 244, 41);
		contentPane.add(btnAddMoreDetails);
	}

}
