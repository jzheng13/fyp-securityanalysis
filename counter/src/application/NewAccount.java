package application;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

public class NewAccount extends JPanel {

	private Name user;
	private ServiceProvider preSP;
	private Database db;
	
	private JPanel contentPane;
	private JComboBox serviceProvider;
	private JTextField username;
	private JPasswordField password;
	private JComboBox<Account> email;
	private JComboBox<Account> sso;
	private JCheckBox chckbxSSO;

	/**
	 * Create the panel.
	 */
	public NewAccount(AppFrame parent) {
		this.contentPane = this;
		// this.setBounds(0, 0, 0, 0);
		this.setBounds(100, 100, 1312, 772);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		
		this.user = parent.getUser();
		this.db = parent.getDatabase();
		
		JLabel instructions1 = new JLabel("Please enter details of your new account and select the corresponding email from an existing");
		instructions1.setBounds(83, 89, 1146, 33);
		add(instructions1);
		
		JLabel instructions2 = new JLabel("email account. If it is not yet in the database, you can edit the field at a later instance.");
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
		
		serviceProvider = new JComboBox(ServiceProvider.values());
		serviceProvider.setSelectedIndex(-1);
		serviceProvider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (serviceProvider.getSelectedIndex() >= 0) {
					preSP = (ServiceProvider) serviceProvider.getSelectedItem();
				}
			}
		});
		serviceProvider.setMaximumRowCount(100);
		serviceProvider.setEditable(true);
		serviceProvider.setBounds(327, 196, 325, 39);
		this.add(serviceProvider);
		
		username = new JTextField();
		username.setBounds(327, 257, 325, 39);
		this.add(username);
		username.setColumns(10);
		
		JComboBox<Account> sso = new JComboBox<Account>();
		sso.setMaximumRowCount(100);
		sso.setBounds(937, 199, 272, 41);
		sso.setVisible(false);
		this.add(sso);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(83, 382, 115, 33);
		this.add(lblEmail);
		
		JComboBox<Account> email = new JComboBox<Account>();
		email.setMaximumRowCount(100);
		email.setEditable(false);
		email.setBounds(327, 379, 325, 39);
		this.add(email);
		
		List<String[]> result = db.selectValues("SELECT id FROM ACCOUNTS_LOGIN_" + user.id(), 1);
		for (String[] r : result) {
			Account acc = new Account(user, r[0]);
			sso.addItem(acc);
			email.addItem(acc);
		}
		sso.setSelectedIndex(-1);
		email.setSelectedIndex(-1);
		
		JCheckBox chckbxSSO = new JCheckBox("Single Sign-on");
		chckbxSSO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sso.isVisible()) {
					sso.setVisible(false);
				} else {
					sso.setVisible(true);
				}
			}
		});
		chckbxSSO.setBounds(686, 195, 221, 41);
		this.add(chckbxSSO);
		
		sso.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					List<String[]> result = db.selectValues("SELECT sp, username FROM ACCOUNTS_" + user
							+ " WHERE id='" + ((Account) sso.getSelectedItem()).id() + "'", 2);
					serviceProvider.setSelectedItem(result.get(0)[0]);
					username.setText(result.get(0)[1]);
			    }
			}
		});
		
		password = new JPasswordField();
		password.setBounds(327, 318, 325, 39);
		this.add(password);
		
		JLabel lblAddAccount = new JLabel("Add an account");
		lblAddAccount.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblAddAccount.setBounds(26, 28, 307, 33);
		this.add(lblAddAccount);
		
		JLabel lblDateModified = new JLabel("Date Modified");
		lblDateModified.setBounds(696, 321, 211, 33);
		add(lblDateModified);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(937, 318, 272, 39);
		add(dateChooser);
		dateChooser.setDate(new Date());
		
		JCheckBox pubUser = new JCheckBox("Public");
		pubUser.setBounds(686, 256, 221, 41);
		add(pubUser);
		
		JCheckBox pubEmail = new JCheckBox("Public");
		pubEmail.setBounds(686, 378, 221, 41);
		add(pubEmail);
		
		JButton btnAddDetails = new JButton("Add Details >");
		btnAddDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sp = serviceProvider.getSelectedItem().toString().toLowerCase();
				if (chckbxSSO.isSelected() && sso.getSelectedIndex() < 0) {
					JOptionPane.showMessageDialog(parent, "You need to select the single sign-on account.");
				} else {
					String usr = username.getText();
					String pw = new String(password.getPassword());
					if (sp.isEmpty() || usr.isEmpty() || pw.isEmpty()) {
						JOptionPane.showMessageDialog(parent, 
								"Service provider, username and password are required fields.");
					} else {
						AccountDetails accountDetails = new AccountDetails(parent);
						
						// Set account id
						List<String[]> result = db.selectValues("SELECT count FROM ACCOUNT_IDS_" + user.id() 
						    + " WHERE sp='" + sp + "'", 1);
						int count = 0;
						if (result.isEmpty()) {
							accountDetails.addQueuedQuery("INSERT INTO ACCOUNT_IDS_" + user.id() 
							    + " (sp, count) VALUE ('" + sp + "', '" + 1 + "')");
						} else {
							count = Integer.parseInt(result.get(0)[0]);
							accountDetails.addQueuedQuery("UPDATE ACCOUNT_IDS_" + user.id() 
						        + " SET count='" + count + "' WHERE sp='" + sp + "')");
						}
						accountDetails.setAccount(sp + count);
						
						// Insert login information
						if (!chckbxSSO.isSelected()) {
							accountDetails.addQueuedQuery("INSERT INTO ACCOUNTS_LOGIN_" + user.id() 
								+ " (sp, username, usrpublic, id) VALUE ('" + sp + "', '" + usr + "', " 
								+ pubUser.isSelected() + " , '" + sp + count + "')");
							SimpleDateFormat format = new SimpleDateFormat("yyyy, mm, dd");
							String date = format.format(dateChooser.getDate());
							accountDetails.addQueuedQuery("UPDATE ACCOUNTS_LOGIN_" + user.id()
							    + " SET password='" + pw + "', " + "mod_date='" + date + "' WHERE id='" 
								+ sp + count + "'");
						} else {
							Account ssoAcc = (Account) sso.getSelectedItem();
							accountDetails.addQueuedQuery("INSERT INTO ACCOUNTS_LOGIN_" + user.id() 
								+ " (sp, username, sso, id) VALUE ('" + sp + "', '" + ssoAcc.getUsername() 
								+ "' , '" + ssoAcc.id() + "' , '" + sp + count + "')");
						}
						
						// Insert email information
						if (email.getSelectedIndex() >= 0) {
							Account emAcc = (Account) email.getSelectedItem();
							accountDetails.addQueuedQuery("INSERT INTO ACCOUNTS_INFO_" + user.id() 
								+ " (email, empublic, id) VALUE ('" + emAcc.id() + "', " + pubEmail.isSelected() 
								+ " ,'" + sp + count + "')");
						} else {
							accountDetails.addQueuedQuery("INSERT INTO ACCOUNTS_INFO_" + user.id() 
							    + " (id) VALUE ('" + sp + count + "')");
						}
						
						parent.addPanel(accountDetails);
					}

				}
			}
		});
		btnAddDetails.setBounds(980, 585, 249, 41);
		this.add(btnAddDetails);
		
		JButton btnBack = new JButton("< Back");
		btnBack.setBounds(83, 587, 171, 41);
		add(btnBack);
		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parent.addPanel(new AnalysisMain(parent));
			}
			
		});
		
	}
}
