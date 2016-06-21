package application;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class EditUser extends JPanel {
	
	private AppFrame parent;
	private Name user;
	private Database db;
	private JTextField firstname;
	private JTextField lastname;
	private JDateChooser birthday;
	private JTextField field;
	private JTextField value;
	private JTable fvTab;

	/**
	 * Create the panel.
	 */
	public EditUser(AppFrame parent) {

		// this.setBounds(0, 0, 0, 0);
		this.setBounds(100, 100, 1312, 772);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		
		this.parent = parent;
		this.user = parent.getUser();
		this.db = parent.getDatabase();

		JLabel lblSelectField = new JLabel("Select a field to edit for user " + parent.getUser());
		lblSelectField.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblSelectField.setBounds(26, 28, 828, 41);
		this.add(lblSelectField);
		
		JLabel instructions1 = new JLabel("Edit the required fields and press 'Confirm' to commit changes");
		instructions1.setBounds(83, 89, 1146, 33);
		add(instructions1);
		
		JLabel lblName = new JLabel("First Name");
		lblName.setBounds(83, 177, 231, 33);
		this.add(lblName);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(83, 238, 147, 33);
		add(lblLastName);

		JLabel lblBirthday = new JLabel("Birthday");
		lblBirthday.setBounds(83, 299, 173, 33);
		this.add(lblBirthday);
		
		firstname = new JTextField();
		firstname.setBounds(327, 171, 325, 39);
		this.add(firstname);
		firstname.setColumns(10);
		firstname.setEditable(false);
		
		List<String[]> results = db.selectValues("SELECT value FROM USER_" + user.id() 
		    + " WHERE field='firstname'", 1);
		if (!results.isEmpty()) {
			firstname.setText(results.get(0)[0]);
		}

		lastname = new JTextField();
		lastname.setBounds(327, 235, 325, 39);
		add(lastname);
		lastname.setColumns(10);
		lastname.setEditable(false);
		
		results = db.selectValues("SELECT value FROM USER_" + user.id() 
	        + " WHERE field='lastname'", 1);
		if (!results.isEmpty()) {
		    lastname.setText(results.get(0)[0]);
		}
		
		birthday = new JDateChooser();
		birthday.setBounds(327, 293, 325, 39);
		add(birthday);
		
		results = db.selectValues("SELECT value FROM USER_" + user.id() 
        	+ " WHERE field='birthday'", 1);
		if (!results.isEmpty()) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy, mm, dd");
				try {
					birthday.setDate(format.parse(results.get(0)[0]));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
		} else {
			birthday.setDate(new Date());
		}
		
		DefaultTableModel dtm = new DefaultTableModel();
		String[] colNames = {"field", "value"};
		dtm.setColumnIdentifiers(colNames);
		results = db.selectValues("SELECT * FROM USER_" + user.id(), 2);
		for (String[] r : results) {
			if (!r[0].equals("firstname") && !r[0].equals("lastname") && !r[0].equals("birthday")) {
				dtm.addRow(r);
			}	
		}
		
		JButton btnBack = new JButton("< Back");
		btnBack.setBounds(83, 587, 171, 41);
		add(btnBack);
		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parent.setUser(null);
				parent.addPanel(new ExistUser(parent));
			}
			
		});
		
		field = new JTextField();
		field.setBounds(83, 417, 236, 39);
		add(field);
		field.setColumns(10);
		
		value = new JTextField();
		value.setBounds(345, 417, 236, 39);
		add(value);
		value.setColumns(10);
		
		JLabel lblField = new JLabel("Field");
		lblField.setBounds(83, 360, 115, 33);
		add(lblField);
		
		JLabel lblValue = new JLabel("Value");
		lblValue.setBounds(345, 356, 115, 33);
		add(lblValue);
		
		JButton plus = new JButton("+");
		plus.setBounds(607, 416, 53, 41);
		add(plus);
		plus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String f = field.getText();
				String v = value.getText();
				if (!f.isEmpty() && !v.isEmpty()) {
				    DefaultTableModel m = (DefaultTableModel) fvTab.getModel();
				    m.addRow(new String[] {f, v});
				}
			}
			
		});
		
		
		fvTab = new JTable(dtm);
		fvTab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fvTab.setRowHeight(30);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(fvTab);
		scrollPane.setBounds(678, 168, 399, 288);
		add(scrollPane);
		
		JButton minus = new JButton("-");
		minus.setBounds(1100, 416, 53, 41);
		add(minus);
		minus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel) fvTab.getModel();
				int row = fvTab.getSelectedRow();
				if (row >= 0) {
					m.removeRow(row);
				}
			}
			
		});
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(1038, 587, 171, 41);
		add(btnConfirm);
		btnConfirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (confirm() == JOptionPane.YES_OPTION ) {
					parent.setAccount(null);
					parent.addPanel(new ExistUser(parent));
				}
			}
			
		});
	}
	
	public int confirm() {
		int conf = JOptionPane.showConfirmDialog(parent, "Confirm changes?");
		if (conf == JOptionPane.YES_OPTION) {
			db.query("TRUNCATE TABLE USER_" + user.id());
			db.query("INSERT INTO USER_" + user.id() + " (field, value) VALUE ('firstname' , '" 
				+ firstname.getText() + "')");
			db.query("INSERT INTO USER_" + user.id() + " (field, value) VALUE ('lastname' , '" 
			    + lastname.getText() + "')");
			SimpleDateFormat format = new SimpleDateFormat("yyyy, mm, dd");
			String bday = format.format(birthday.getDate()); 
			db.query("INSERT INTO USER_" + user.id() + " (field, value) VALUE ('birthday' , '" 
					+ bday + "')");
			DefaultTableModel m = (DefaultTableModel) fvTab.getModel();
			for (int i = 0; i < m.getRowCount(); i++) {
				db.query("INSERT INTO USER_" + user.id() + " (field, value) VALUE ('" 
			        + m.getValueAt(i, 0) + "' , '" + m.getValueAt(i, 1) + "')");
			}
		}
		return conf;
	}

}
