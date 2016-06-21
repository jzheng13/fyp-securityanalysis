package application;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class EditReset extends JPanel {
	
	private AppFrame parent;
	
	private Name user;
	private Account account;
	private Database db;
	
	private JTable resTable;

	/**
	 * Create the panel.
	 */
	public EditReset(AppFrame parent) {
		
		this.setBounds(100, 100, 1312, 772);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		
		this.user = parent.getUser();
		this.account = parent.getAccount();
		this.db = parent.getDatabase();
		
        JLabel resetInfo = new JLabel("Edit account reset requirements");
		resetInfo.setFont(new Font("Tahoma", Font.BOLD, 34));
		resetInfo.setBounds(26, 28, 595, 41);
		this.add(resetInfo);
		
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		String[] colNames = {"Reset Account", "Reset Information"};
		dtm.setColumnIdentifiers(colNames);
		List<String[]> result = db.selectValues("SELECT resetacc, resetinf FROM ACCOUNTS_RESET_" 
		    + user.id() + " WHERE id='" + account.id() + "'", 2);
		for (String[] r : result) {
			Account rA = new Account(user, r[0]);
			dtm.addRow(new Object[] {rA, r[1]});
		}
		
		resTable = new JTable(dtm);
		resTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		resTable.setRowHeight(30);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(resTable);
		scrollPane.setBounds(83, 300, 728, 248);
		add(scrollPane);
		
		JLabel lblResetAccount = new JLabel("Reset account");
		lblResetAccount.setBounds(83, 173, 171, 33);
		add(lblResetAccount);
		
		JLabel lblResetInformation = new JLabel("Reset information");
		lblResetInformation.setBounds(83, 234, 223, 33);
		add(lblResetInformation);
		
		JComboBox<Account> resetAccount = new JComboBox<Account>();
		resetAccount.setBounds(342, 170, 279, 39);
		add(resetAccount);
		resetAccount.addItem(null);
		result = db.selectValues("SELECT id FROM ACCOUNTS_LOGIN_" + user.id(), 1);
		for (String[] r : result) {
			Account acc = new Account(user, r[0]);
			resetAccount.addItem(acc);
		}
		resetAccount.setSelectedIndex(-1);
		
		JTextField resetInfoReq = new JTextField();
		resetInfoReq.setBounds(342, 231, 673, 39);
		add(resetInfoReq);
		resetInfoReq.setText("e.g. username, email");
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(844, 169, 171, 41);
		add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Account rAcc = null;
				if (resetAccount.getSelectedIndex() >= 0) {
					rAcc = ((Account) resetAccount.getSelectedItem());
				} 
				String rInfo = resetInfoReq.getText();
				dtm.addRow(new Object[]{rAcc, rInfo});
			}
			
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(844, 302, 171, 41);
		add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel) resTable.getModel();
				int row = resTable.getSelectedRow();
				if (row >= 0) {
					m.removeRow(row);
				}
			}
			
		});
		
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
				int conf = JOptionPane.showConfirmDialog(parent, "Confirm changes?");
				
				if (conf == JOptionPane.YES_OPTION) {
				    db.query("DELETE FROM ACCOUNTS_RESET_" + user.id() + " WHERE id='" 
				        + account.id() + "'");
				    DefaultTableModel m = (DefaultTableModel) resTable.getModel();
				    for (int i = 0; i < m.getRowCount(); i++) {
						Account racc = (Account) m.getValueAt(i, 0);
						String resetAcc = racc==null ? "default" : racc.id();
						String resetInf = (String) m.getValueAt(i, 1);
						db.query("INSERT INTO ACCOUNTS_RESET_" + user.id() 
						    + " (id, resetacc, resetinf) VALUE ('" + account + "', '" 
						    + resetAcc + "', '" + resetInf + "')");
					}
				    parent.setAccount(null);
					parent.addPanel(new ExistAcc(parent));
				}
				
			}
			
		});

	}

}
