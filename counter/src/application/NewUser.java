package application;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;

public class NewUser extends JPanel {

	private JTextField firstname;
	private JTextField lastname;
	private JTextField currentCity;
	private JTextField mobile;
	private JTextField hometown;
	private JTextField job;
	private JTextField workplace;
	private Database db;

	/**
	 * Create the panel.
	 */
	public NewUser(AppFrame parent) {
		// this.setBounds(0, 0, 0, 0);
		this.setBounds(100, 100, 1312, 772);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		this.db = parent.getDatabase();

		JLabel lblAddUser = new JLabel("Add a user");
		lblAddUser.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblAddUser.setBounds(26, 28, 307, 33);
		this.add(lblAddUser);

		JLabel lblName = new JLabel("First Name");
		lblName.setBounds(83, 96, 231, 33);
		this.add(lblName);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(83, 160, 147, 33);
		add(lblLastName);

		JLabel lblBirthday = new JLabel("Birthday");
		lblBirthday.setBounds(83, 221, 173, 33);
		this.add(lblBirthday);

		JLabel lblCurrCity = new JLabel("Current City");
		lblCurrCity.setBounds(83, 343, 147, 33);
		this.add(lblCurrCity);

		firstname = new JTextField();
		firstname.setBounds(327, 93, 325, 39);
		this.add(firstname);
		firstname.setColumns(10);

		lastname = new JTextField();
		lastname.setBounds(327, 151, 325, 39);
		add(lastname);
		lastname.setColumns(10);
		
		JDateChooser birthday = new JDateChooser();
		birthday.setBounds(327, 221, 325, 39);
		add(birthday);

		currentCity = new JTextField();
		currentCity.setBounds(327, 340, 325, 39);
		this.add(currentCity);
		currentCity.setColumns(10);

		JLabel lblMobileNo = new JLabel("Mobile no.");
		lblMobileNo.setBounds(83, 282, 173, 33);
		this.add(lblMobileNo);

		mobile = new JTextField();
		mobile.setBounds(327, 279, 325, 39);
		this.add(mobile);
		mobile.setColumns(10);

		JLabel lblHometown = new JLabel("Hometown");
		lblHometown.setBounds(83, 404, 147, 33);
		this.add(lblHometown);

		hometown = new JTextField();
		hometown.setBounds(327, 401, 325, 39);
		this.add(hometown);
		hometown.setColumns(10);

		JLabel lblJob = new JLabel("Job");
		lblJob.setBounds(83, 465, 115, 33);
		this.add(lblJob);

		job = new JTextField();
		job.setBounds(327, 462, 325, 39);
		this.add(job);
		job.setColumns(10);

		JLabel lblWorkplace = new JLabel("Workplace");
		lblWorkplace.setBounds(83, 526, 147, 33);
		this.add(lblWorkplace);

		workplace = new JTextField();
		workplace.setBounds(327, 523, 325, 39);
		this.add(workplace);
		workplace.setColumns(10);

		JButton btnAddUser = new JButton("Finish");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String first = firstname.getText();
				String last = lastname.getText();
				if (first.isEmpty() || last.isEmpty()) {
					JOptionPane.showMessageDialog(parent, "Please enter your full name.");
				} else {
					String name = first + last;
					// Add user into user list
					db.query(
							"INSERT INTO USERS (firstname, lastname) VALUE " + "( '" + first + "', '" + last + "')");
					
					// Create table for user info
					db.query("CREATE TABLE USER_" + name + " (field VARCHAR(40), value VARCHAR(100));");
					
					// Create table for account ids
					db.query("CREATE TABLE ACCOUNT_IDS_" + name + " (sp VARCHAR(40), count INTEGER);");
					
					// Create tables for accounts
					// Columns id, service provider, sso, username, password, password expiry date, password information
					db.query("CREATE TABLE ACCOUNTS_LOGIN_" + name
							+ " (id VARCHAR(160), sp VARCHAR(40), sso VARCHAR(160), " 
							+ "username VARCHAR(100), usrpublic BOOLEAN, password VARCHAR(100), mod_date VARCHAR(16), "
							+ "pwinf VARCHAR(500));");
					// Columns id, public info, private info, email, email public 
					db.query("CREATE TABLE ACCOUNTS_INFO_" + name
							+ " (id VARCHAR(160), pub VARCHAR(500), priv VARCHAR(500), "
							+ "email VARCHAR(160), empublic BOOLEAN);");
					// Columns id, reset account, reset info
					db.query("CREATE TABLE ACCOUNTS_RESET_" + name
							+ " (id VARCHAR(160), resetacc VARCHAR(160), resetinf VARCHAR(500));");
					
					// Add attributes to user table
					db.query("INSERT INTO USER_" + name + "(field, value) VALUE ('firstname' , " + "'" + first + "')");
					db.query("INSERT INTO USER_" + name + "(field, value) VALUE ('lastname', '" + last + "')");
					if (birthday.getDate() != null) {
						db.query("INSERT INTO USER_" + name + "(field, value) VALUE ('birthday', '"
								+ birthday.getDate() + "'" + ")");
					}
					if (!mobile.getText().isEmpty()) {
						db.query("INSERT INTO USER_" + name + "(field, value) VALUE ('mobile', '"
								+ mobile.getText() + "'" + ")");
					}
					if (!currentCity.getText().isEmpty()) {
						db.query("INSERT INTO USER_" + name + "(field, value) VALUE ('currentCity', '"
								+ currentCity.getText() + "'" + ")");
					}
					if (!hometown.getText().isEmpty()) {
						db.query("INSERT INTO USER_" + name + "(field, value) VALUE ('hometown', '"
								+ hometown.getText() + "'" + ")");
					}
					if (!job.getText().isEmpty()) {
						db.query("INSERT INTO USER_" + name + "(field, value) VALUE ('job', '" 
					            + job + "'" + ")");
					}
					if (!workplace.getText().isEmpty()) {
						db.query("INSERT INTO USER_" + name + "(field, value) VALUE ('workplace', '"
								+ workplace.getText() + "'" + ")");
					}
					parent.backToMain();
				}
			}
		});
		btnAddUser.setBounds(985, 585, 244, 41);
		this.add(btnAddUser);

		JButton btnAddMoreDetails = new JButton("Add More >");
		btnAddMoreDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String first = firstname.getText();
				String last = lastname.getText();
				if (first.isEmpty() || last.isEmpty()) {
					JOptionPane.showMessageDialog(parent, "Please enter your full name.");
				} else {
					
					// Set-up next panel
					UserMisc userMisc = new UserMisc(parent);
					String name = first + last;
					userMisc.setUser(name);
					
					// Add user into user list
					userMisc.addQueuedQuery(
							"INSERT INTO USERS (firstname, lastname) VALUE " + "( '" + first + "', '" + last + "')");
					
					// Create table for user info
					userMisc.addQueuedQuery("CREATE TABLE USER_" + name + " (field VARCHAR(40), value VARCHAR(100));");
					
					// Create table for account ids
					userMisc.addQueuedQuery("CREATE TABLE ACCOUNT_IDS_" + name + " (sp VARCHAR(40), count INTEGER);");
					
					// Create tables for accounts
					// Columns id, service provider, sso, username, password, password expiry date, password information
					userMisc.addQueuedQuery("CREATE TABLE ACCOUNTS_LOGIN_" + name
							+ " (id VARCHAR(160), sp VARCHAR(40), sso VARCHAR(160), " 
							+ "username VARCHAR(100), usrpublic BOOLEAN, password VARCHAR(100), mod_date DATE, "
							+ "pwinf VARCHAR(500));");
					// Columns id, public info, private info, email, email public 
					userMisc.addQueuedQuery("CREATE TABLE ACCOUNTS_INFO_" + name
							+ " (id VARCHAR(160), pub VARCHAR(500), priv VARCHAR(500), "
							+ "email VARCHAR(160), empublic BOOLEAN);");
					// Columns id, reset account, reset info
					userMisc.addQueuedQuery("CREATE TABLE ACCOUNTS_RESET_" + name
							+ " (id VARCHAR(160), resetacc VARCHAR(160), resetinf VARCHAR(500));");
					
					// Add attributes to user table
					userMisc.addQueuedQuery(
							"INSERT INTO USER_" + name + "(field, value) VALUE ('firstname' , " + "'" + first + "')");
					userMisc.addQueuedQuery("INSERT INTO USER_" + name + "(field, value) VALUE ('lastname', '" + last + "')");
					if (birthday.getDate() != null) {
						userMisc.addQueuedQuery("INSERT INTO USER_" + name + "(field, value) VALUE ('birthday', '"
								+ birthday.getDate() + "'" + ")");
					}
					if (!mobile.getText().isEmpty()) {
						userMisc.addQueuedQuery("INSERT INTO USER_" + name + "(field, value) VALUE ('mobile', '"
								+ mobile.getText() + "'" + ")");
					}
					if (!currentCity.getText().isEmpty()) {
						userMisc.addQueuedQuery("INSERT INTO USER_" + name + "(field, value) VALUE ('currentCity', '"
								+ currentCity.getText() + "'" + ")");
					}
					if (!hometown.getText().isEmpty()) {
						userMisc.addQueuedQuery("INSERT INTO USER_" + name + "(field, value) VALUE ('hometown', '"
								+ hometown.getText() + "'" + ")");
					}
					if (!job.getText().isEmpty()) {
						userMisc.addQueuedQuery("INSERT INTO USER_" + name + "(field, value) VALUE ('job', '" 
								+ job.getText() + "'" + ")");
					}
					if (!workplace.getText().isEmpty()) {
						userMisc.addQueuedQuery("INSERT INTO USER_" + name + "(field, value) VALUE ('workplace', '"
								+ workplace.getText() + "'" + ")");
					}
					
					// Add next panel
					parent.addPanel(userMisc);
				}
			}
		});
		btnAddMoreDetails.setBounds(726, 585, 244, 41);
		this.add(btnAddMoreDetails);
		
		JButton btnBack = new JButton("< Back");
		btnBack.setBounds(83, 587, 171, 41);
		add(btnBack);
		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parent.backToMain();
			}
			
		});


	}
}
