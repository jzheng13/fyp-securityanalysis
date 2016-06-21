package application;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class EditDetails extends JPanel {
	
	private AppFrame parent;
	
	private Name user;
	private Account account;
	private Database db;
	
	private JList<String> privateList;
	private JList<String> publicList;

	/**
	 * Create the panel.
	 */
	public EditDetails(AppFrame parent) {
		
		this.setBounds(100, 100, 1312, 772);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		
		this.user = parent.getUser();
		this.account = parent.getAccount();
		this.db = parent.getDatabase();
		
		JLabel lblInfoPriv = new JLabel("Edit privacy of information");
		lblInfoPriv.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblInfoPriv.setBounds(26, 28, 595, 41);
		this.add(lblInfoPriv);
		
		JLabel lblPrivateInformation = new JLabel("Private information");
		lblPrivateInformation.setBounds(117, 132, 269, 33);
		add(lblPrivateInformation);
		
		JLabel lblPublicInformation = new JLabel("Public information");
		lblPublicInformation.setBounds(688, 132, 269, 33);
		add(lblPublicInformation);
		
		DefaultListModel<String> pub = new DefaultListModel<String>();
		DefaultListModel<String> priv = new DefaultListModel<String>();
		List<String[]> result = db.selectValues("SELECT pub, priv FROM ACCOUNTS_INFO_" + user.id() 
				+ " WHERE id='" + account.id() + "'", 2);
		if (!result.isEmpty()) {
			String[] pubInfo = result.get(0)[0].split(", ");
			String[] privInfo = result.get(0)[1].split(", ");
			for (String i : pubInfo) {
				pub.addElement(i);
			}
			for (String i : privInfo) {
				priv.addElement(i);
			}
		}
		
		privateList = new JList<String>(priv);
		privateList.setBounds(151, 289, 400, 225);
		privateList.setBackground(Color.WHITE);
		privateList.setFixedCellWidth(100);
		add(privateList);
		
		publicList = new JList<String>(pub);
		publicList.setBounds(699, 289, 400, 225);
		publicList.setBackground(Color.WHITE);
		publicList.setFixedCellWidth(100);
		add(publicList);
		
		JButton privRemove = new JButton("Remove");
		privRemove.setBounds(380, 525, 171, 41);
		add(privRemove);
		privRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!privateList.isSelectionEmpty()) {
					DefaultListModel<String> priv = (DefaultListModel<String>) privateList.getModel();
					priv.remove(privateList.getSelectedIndex());
				}
			}
			
		});
		
		JButton pubRemove = new JButton("Remove");
		pubRemove.setBounds(928, 525, 171, 41);
		add(pubRemove);
		pubRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!publicList.isSelectionEmpty()) {
					DefaultListModel<String> pub = (DefaultListModel<String>) publicList.getModel();
					pub.remove(publicList.getSelectedIndex());
				}
			}
			
		});
		
		JComboBox<String> privateInfo = new JComboBox<String>();
		privateInfo.setBounds(151, 193, 397, 39);
		add(privateInfo);
		
		JComboBox<String> publicInfo = new JComboBox<String>();
		publicInfo.setBounds(698, 193, 400, 39);
		add(publicInfo);
		
		result = db.selectValues("SELECT field FROM USER_" + user.id(), 1);
		for (String[] r : result) {
			privateInfo.addItem(r[0]);
            publicInfo.addItem(r[0]);
		}
		
		JButton privAdd = new JButton("Add");
		privAdd.setBounds(380, 238, 171, 41);
		add(privAdd);
		privAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (privateInfo.getSelectedIndex() >= 0) {
					DefaultListModel<String> priv = (DefaultListModel<String>) privateList.getModel();
					if (!priv.contains(privateInfo.getSelectedItem())) {
						priv.addElement((String) privateInfo.getSelectedItem());
					}
				}
			}
			
		});
		
		JButton pubAdd = new JButton("Add");
		pubAdd.setBounds(928, 238, 171, 41);
		add(pubAdd);
		pubAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (publicInfo.getSelectedIndex() >= 0) {
					DefaultListModel<String> pub = (DefaultListModel<String>) publicList.getModel();
					if (!pub.contains(publicInfo.getSelectedItem())) {
						pub.addElement((String) publicInfo.getSelectedItem());
					}
				}
			}
			
		});
		
		JButton btnAddDetails = new JButton("Edit Reset >");
		btnAddDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (confirm() == JOptionPane.YES_OPTION ) {
					parent.addPanel(new EditReset(parent));
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
				if (confirm() == JOptionPane.YES_OPTION ) {
					parent.setAccount(null);
					parent.addPanel(new ExistAcc(parent));
				}
			}
			
		});

	}
	
	public int confirm() {
		int conf = JOptionPane.showConfirmDialog(parent, "Confirm changes?");
		
		if (conf == JOptionPane.YES_OPTION) {
		
			// Private information on account
			Object[] objects = ((DefaultListModel<String>) privateList.getModel()).toArray();
			List<String> priv = new ArrayList<String>();
			for (Object o : objects) {
				priv.add((String) o);
			}
		    db.query("UPDATE ACCOUNTS_INFO_" + user.id() + " SET priv='" + String.join(",", priv) 
		        + "' WHERE id='" + account + "'");
		    
		    // Public information on account
			objects = ((DefaultListModel<String>) publicList.getModel()).toArray();
			List<String> pub = new ArrayList<String>();
			for (Object o : objects) {
				pub.add((String) o);
			}
			db.query("UPDATE ACCOUNTS_INFO_" + user.id() + " SET pub='" + String.join(",", pub) 
			    + "' WHERE id='" + account + "'");
		}
		
		return conf;
	}

}
