package application;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

public class EditAccount extends JPanel {
	
	private AppFrame parent;
	private Name user;
	private Account account;
	private Database db;
	
	private JTextField serviceProvider;
	private JTextField username;
	private JCheckBox pubUser;
	private JCheckBox pubEmail;
	private JPasswordField password;
	private JComboBox<Account> email;
	private JDateChooser dateChooser;

	/**
	 * Create the panel.
	 */
	public EditAccount(AppFrame parent) {
		
		this.parent = parent;
		this.user = parent.getUser();
		this.account = parent.getAccount();
		this.db = parent.getDatabase();
		
		// this.setBounds(0, 0, 0, 0);
		this.setBounds(100, 100, 1312, 772);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		
		JLabel lblSelectField = new JLabel("Edit account " + parent.getAccount());
		lblSelectField.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblSelectField.setBounds(26, 28, 828, 41);
		this.add(lblSelectField);
		
		JLabel instructions1 = new JLabel("Edit the required fields if necessary and press 'Edit details' to proceed or 'Confirm' to commit");
		instructions1.setBounds(83, 89, 1146, 33);
		add(instructions1);
		
		JLabel instructions2 = new JLabel("changes.");
		instructions2.setBounds(83, 130, 1146, 33);
		add(instructions2);
		
		JLabel lblServiceProvider = new JLabel("Service Provider");
		lblServiceProvider.setBounds(85, 199, 231, 33);
		this.add(lblServiceProvider);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(85, 260, 173, 33);
		this.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(83, 321, 115, 33);
		this.add(lblPassword);
		
		serviceProvider = new JTextField();
		List<String[]> result = db.selectValues("SELECT sp, username FROM ACCOUNTS_LOGIN_" 
		    + user.id() + " WHERE id='" + account.id() + "'", 2);
		serviceProvider.setText(result.get(0)[0]);
		serviceProvider.setEditable(false);
		serviceProvider.setBounds(327, 196, 325, 39);
		this.add(serviceProvider);
		
		username = new JTextField();
		username.setBounds(327, 257, 325, 39);
		username.setText(result.get(0)[1]);
		username.setColumns(10);
		username.setEditable(false);
		this.add(username);
		
		JComboBox<Account> sso = new JComboBox<Account>();
		sso.setMaximumRowCount(100);
		sso.setBounds(937, 199, 272, 41);
		sso.setVisible(false);
		sso.setEditable(false);
		this.add(sso);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(83, 382, 115, 33);
		this.add(lblEmail);
		
		email = new JComboBox<Account>();
		email.setMaximumRowCount(100);
		email.setEditable(false);
		email.setBounds(327, 379, 325, 39);
		this.add(email);
		
		result = db.selectValues("SELECT id FROM ACCOUNTS_LOGIN_" + user.id(), 1);
		for (String[] r : result) {
			Account acc = new Account(user, r[0]);
			email.addItem(acc);
		}
		sso.setSelectedIndex(-1);
		email.setSelectedIndex(-1);
		
		JCheckBox chckbxSSO = new JCheckBox("Single Sign-on");
		chckbxSSO.setEnabled(false);
		result = db.selectValues("SELECT sso FROM ACCOUNTS_LOGIN_" + user.id() 
		    + " WHERE sso IS NOT NULL", 1);
		if (!result.isEmpty()) {
			chckbxSSO.setSelected(true);
			sso.setSelectedItem(result.get(0)[0]);
		}
		chckbxSSO.setBounds(686, 195, 221, 41);
		this.add(chckbxSSO);
		
		password = new JPasswordField();
		password.setBounds(327, 318, 325, 39);
		this.add(password);
		
		JLabel lblDateModified = new JLabel("Date Modified");
		lblDateModified.setBounds(696, 321, 211, 33);
		add(lblDateModified);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(937, 318, 272, 39);
		add(dateChooser);
		dateChooser.setDate(new Date());
		
		pubUser = new JCheckBox("Public");
		pubUser.setBounds(686, 256, 221, 41);
		add(pubUser);
		
		pubEmail = new JCheckBox("Public");
		pubEmail.setBounds(686, 378, 221, 41);
		add(pubEmail);
		
		JButton btnAddDetails = new JButton("Edit Details >");
		btnAddDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (confirm() == JOptionPane.YES_OPTION) {
				    parent.addPanel(new EditDetails(parent));
				}
			}
		});
		btnAddDetails.setBounds(747, 587, 249, 41);
		this.add(btnAddDetails);
		
		JButton btnBack = new JButton("< Back");
		btnBack.setBounds(83, 587, 171, 41);
		add(btnBack);
		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parent.setAccount(null);
				parent.addPanel(new ExistAcc(parent));
				
			}
			
		});
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(1038, 587, 171, 41);
		add(btnConfirm);
		btnConfirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (confirm() == JOptionPane.YES_OPTION) {
					parent.setAccount(null);
					parent.addPanel(new ExistAcc(parent));
				}
			}
			
		});

	}
	
	public int confirm() {
		
		int conf = JOptionPane.showConfirmDialog(parent, "Confirm changes?");
		
		if (conf == JOptionPane.YES_OPTION) {
			String pw = new String(password.getPassword());
			
			db.query("UPDATE ACCOUNTS_LOGIN_" + user.id() + " SET usrpublic=" + pubUser.isSelected()
				+ " WHERE id='" + account.id() + "'");
			if (!pw.isEmpty()) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy, mm, dd");
				String date = format.format(dateChooser.getDate());
			    db.query("UPDATE ACCOUNTS_LOGIN_" + user.id() + " SET password='" + pw 
		            + "', mod_date='" + date + "' WHERE id='" + account.id() + "'");
			}
			if (email.getSelectedIndex() >= 0) {
				Account em = (Account) email.getSelectedItem();
				db.query("UPDATE ACCOUNTS_INFO_" + user.id() + " SET email='" + em.id() 
				    + "', empublic=" + pubEmail.isSelected() + " WHERE id='" + account.id() + "'");
			}
		}	
		return conf;

	}

}
