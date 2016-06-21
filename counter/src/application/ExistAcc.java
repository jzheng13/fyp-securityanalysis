package application;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ExistAcc extends JPanel {
	
	private Name user;

	/**
	 * Create the panel.
	 */
	public ExistAcc(AppFrame parent) {
		// this.setBounds(0, 0, 0, 0);
		this.user = parent.getUser();
		this.setBounds(100, 100, 1312, 772);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		
		JLabel lblSelectAcc = new JLabel("Select an existing account");
		lblSelectAcc.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblSelectAcc.setBounds(26, 28, 648, 41);
		this.add(lblSelectAcc);
		
		DefaultListModel<Account> model = new DefaultListModel<Account>();
		List<String[]> result = parent.getDatabase().selectValues("SELECT id FROM ACCOUNTS_LOGIN_" 
		    + user.id(), 1);
		for (String[] r : result) {
			Account exist = new Account(user, r[0]);
			if (!model.contains(exist)) {
				model.addElement(exist);
			}
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(83, 186, 584, 350);
		
		JList<Account> accList = new JList<Account>(model);
		
		scrollPane.setViewportView(accList);
		add(scrollPane);
		
		accList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Account selected = accList.getSelectedValue();
				// TODO: Retrieve details of account?
			}
			
		});
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (accList.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(parent, "Please select an account.");
				} else {	
					Account selected = (Account) accList.getSelectedValue();
					int confirm = JOptionPane.showConfirmDialog(parent, "Delete account " 
							+ selected.toString() + " from the database?");
					switch(confirm) {
						case JOptionPane.YES_OPTION: 
							DefaultListModel<Account> lmodel = (DefaultListModel<Account>) accList.getModel();
							lmodel.remove(accList.getSelectedIndex());
							parent.getDatabase().query("DELETE FROM ACCOUNTS_LOGIN_" + user.id() 
								+ " WHERE id='" + selected.id() + "'");
							parent.getDatabase().query("DELETE FROM ACCOUNTS_INFO_" + user.id() 
							    + " WHERE id='" + selected.id() + "'");
							parent.getDatabase().query("DELETE FROM ACCOUNTS_RESET_" + user.id() 
							    + " WHERE id='" + selected.id() + "'");
							break;
						default: break;
					}
				}
			}
		});
		btnRemove.setBounds(721, 184, 204, 41);
		add(btnRemove);
		
		JButton btnSelectAcc = new JButton("Edit");
		btnSelectAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (accList.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(parent, "Please select an account.");
				} else {
					Account selected = (Account) accList.getSelectedValue();
					parent.setAccount(selected);
					parent.addPanel(new EditAccount(parent));
				}
			}
		});
		btnSelectAcc.setBounds(721, 253, 204, 41);
		this.add(btnSelectAcc);

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
