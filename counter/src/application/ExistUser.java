package application;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class ExistUser extends JPanel {

	private AppFrame parent;

	/**
	 * Create the frame.
	 */
	public ExistUser(AppFrame parent) {
		// this.setBounds(0, 0, 0, 0);
		this.setBounds(100, 100, 1312, 772);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		
		JLabel lblSelectUser = new JLabel("Select an existing user");
		lblSelectUser.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblSelectUser.setBounds(26, 28, 445, 41);
		this.add(lblSelectUser);
		
		DefaultListModel<Name> model = new DefaultListModel<Name>();
		List<String[]> result = parent.getDatabase().selectValues("SELECT firstname, lastname FROM USERS", 2);
		for (String[] r : result) {
			System.out.println(r[0] + r[1]);
			model.addElement(new Name(r[0], r[1]));
			System.out.println(r[0] + r[1]);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(83, 186, 584, 350);
		
		JList<Name> userList = new JList<Name>(model);
		/*
		userList.setBounds(155, 186, 656, 399);
		userList.setBackground(Color.WHITE);
		userList.setVisibleRowCount(10);
		userList.setFixedCellHeight(15);
		userList.setFixedCellWidth(100);
		add(userList);
		*/
		
		scrollPane.setViewportView(userList);
		add(scrollPane);
		
		JButton btnSelectUser = new JButton("Select");
		btnSelectUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (userList.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(parent, "Please select a user.");
				} else {
					Name selected = (Name) userList.getSelectedValue();
					parent.setUser(selected);
					AnalysisMain analysis = new AnalysisMain(parent);
					parent.addPanel(analysis);
				}
			}
		});
		btnSelectUser.setBounds(985, 585, 244, 41);
		this.add(btnSelectUser);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (userList.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(parent, "Please select a user.");
				} else {	
					Name selected = (Name) userList.getSelectedValue();
					int confirm = JOptionPane.showConfirmDialog(parent, "Delete user " 
							+ selected.toString() + " from the database?");
					switch(confirm) {
						case JOptionPane.YES_OPTION: 
							DefaultListModel<Name> lmodel = (DefaultListModel<Name>) userList.getModel();
							lmodel.remove(userList.getSelectedIndex());
							parent.getDatabase().query("DROP TABLE USER_" + selected.id());
							parent.getDatabase().query("DROP TABLE ACCOUNTS_LOGIN_" + selected.id());
							parent.getDatabase().query("DROP TABLE ACCOUNTS_INFO_" + selected.id());
							parent.getDatabase().query("DROP TABLE ACCOUNTS_RESET_" + selected.id());
							parent.getDatabase().query("DELETE FROM USERS WHERE CONCAT(firstname, lastname)='" + selected.id() + "'");
							break;
						default: break;
					}
				}
			}
		});
		btnRemove.setBounds(719, 184, 204, 41);
		add(btnRemove);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (userList.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(parent, "Please select a user.");
				} else {	
					Name selected = (Name) userList.getSelectedValue();
					parent.setUser(selected);
                    parent.addPanel(new EditUser(parent));
				}
			}
		});
		btnEdit.setBounds(719, 267, 204, 41);
		add(btnEdit);
		
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
