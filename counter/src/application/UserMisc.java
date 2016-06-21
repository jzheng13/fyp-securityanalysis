package application;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.JButton;

public class UserMisc extends JPanel {

	private AppFrame parent;

	private String user;
	private List<String> accuEntries;

	private List<JTextField> fields;
	private List<JTextField> values;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Create the panel.
	 */
	public UserMisc(AppFrame parent) {
		// this.setBounds(0, 0, 0, 0);
		this.setBounds(100, 100, 1312, 772);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		this.accuEntries = new ArrayList<String>();
		this.fields = new ArrayList<JTextField>();
		this.values = new ArrayList<JTextField>();

		JLabel lblAddInfo = new JLabel("Add additional user information");
		lblAddInfo.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblAddInfo.setBounds(26, 28, 595, 33);
		this.add(lblAddInfo);

		JLabel lblField = new JLabel("Field");
		lblField.setBounds(95, 127, 115, 33);
		add(lblField);

		JLabel lblContent = new JLabel("Content");
		lblContent.setBounds(399, 127, 115, 33);
		add(lblContent);

		textField_1 = new JTextField();
		textField_1.setBounds(95, 209, 236, 39);
		add(textField_1);
		textField_1.setColumns(10);
		fields.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setBounds(95, 308, 236, 39);
		add(textField_2);
		textField_2.setColumns(10);
		fields.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setBounds(95, 407, 236, 39);
		add(textField_3);
		textField_3.setColumns(10);
		fields.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setBounds(95, 503, 236, 39);
		add(textField_4);
		textField_4.setColumns(10);
		fields.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setBounds(399, 209, 454, 39);
		add(textField_5);
		textField_5.setColumns(10);
		values.add(textField_5);

		textField_6 = new JTextField();
		textField_6.setBounds(399, 308, 454, 39);
		add(textField_6);
		textField_6.setColumns(10);
		values.add(textField_6);

		textField_7 = new JTextField();
		textField_7.setBounds(399, 407, 454, 39);
		add(textField_7);
		textField_7.setColumns(10);
		values.add(textField_7);

		textField_8 = new JTextField();
		textField_8.setBounds(399, 503, 454, 39);
		add(textField_8);
		textField_8.setColumns(10);
		values.add(textField_8);

		Iterator<JTextField> f = fields.iterator();
		Iterator<JTextField> v = values.iterator();

		JButton btnAddUser = new JButton("Next");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (String q : accuEntries) {
					parent.getDatabase().query(q);
				}
				while (f.hasNext() && v.hasNext()) {
					String col1 = f.next().getText();
					String col2 = v.next().getText();
					if (!col1.isEmpty() && !col2.isEmpty()) {
						parent.getDatabase().query("INSERT INTO USER_" + user 
								+ "(field, value) VALUE ('" + col1
								+ "', '" + col2 + "'" + ")");
					}
				}
				parent.backToMain();
			}
		});
		btnAddUser.setBounds(985, 585, 244, 41);
		this.add(btnAddUser);

		JButton btnAddMoreDetails = new JButton("Add More >");
		btnAddMoreDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMisc userMisc = new UserMisc(parent);
				userMisc.setUser(user);
				boolean valid = true;
				while (f.hasNext() && v.hasNext()) {
					String col1 = f.next().getText();
					String col2 = v.next().getText();
					if (col1.isEmpty() || col2.isEmpty()) {
						valid = false;
						JOptionPane.showMessageDialog(parent, "Please complete all fields.");
						userMisc.clearQueuedQueries();
						break;
					} else {
						userMisc.addQueuedQuery("INSERT INTO USER_" + user 
								+ "(field, value) VALUE ('" + col1
								+ "', '" + col2 + "'" + ")");
					}
				}
				if (valid) {
					userMisc.addQueuedQueries(accuEntries);
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
				parent.addPanel(new NewUser(parent));
				
			}
			
		});
	}

	public void setUser(String u) {
		user = u;
	}

	public void addQueuedQuery(String query) {
		accuEntries.add(query);
	}

	public void addQueuedQueries(List<String> queries) {
		accuEntries.addAll(queries);
	}
	
	public void clearQueuedQueries() {
		accuEntries.clear();
	}
}
