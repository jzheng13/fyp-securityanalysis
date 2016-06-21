package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class AccountDetails extends JPanel {

	private AppFrame parent;
	
	private Name user;
	private String account;
	private ServiceProvider serviceProvider;
	private List<String> accuEntries;
	private Database db;

	/**
	 * Create the panel.
	 */
	public AccountDetails(AppFrame parent) {
		// this.setBounds(0, 0, 0, 0);
		this.setBounds(100, 100, 1312, 772);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		this.user = parent.getUser();
		this.accuEntries = new ArrayList<String>();
		this.db = parent.getDatabase();
		
		JLabel lblInfoPriv = new JLabel("Input privacy of information");
		lblInfoPriv.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblInfoPriv.setBounds(26, 28, 595, 41);
		this.add(lblInfoPriv);
		
		JLabel lblPrivateInformation = new JLabel("Private information");
		lblPrivateInformation.setBounds(117, 132, 269, 33);
		add(lblPrivateInformation);
		
		JLabel lblPublicInformation = new JLabel("Public information");
		lblPublicInformation.setBounds(688, 132, 269, 33);
		add(lblPublicInformation);
		
		JList<String> privateList = new JList<String>(new DefaultListModel<String>());
		privateList.setBounds(151, 289, 400, 225);
		privateList.setBackground(Color.WHITE);
		privateList.setFixedCellWidth(100);
		add(privateList);
		
		JList<String> publicList = new JList<String>(new DefaultListModel<String>());
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
/*		privateInfo.addItem("firstname");
		privateInfo.addItem("lastname");
		privateInfo.addItem("address");
		privateInfo.addItem("mobile");
		privateInfo.addItem("currentcity");
		privateInfo.addItem("hometown");
		privateInfo.addItem("job");
		privateInfo.addItem("workplace");*/
		add(privateInfo);
		
		JComboBox<String> publicInfo = new JComboBox<String>();
		publicInfo.setBounds(698, 193, 400, 39);
/*		publicInfo.addItem("firstname");
		publicInfo.addItem("lastname");
		publicInfo.addItem("address");
		publicInfo.addItem("mobile");
		publicInfo.addItem("currentcity");
		publicInfo.addItem("hometown");
		publicInfo.addItem("job");
		publicInfo.addItem("workplace");*/
		add(publicInfo);
		
		List<String[]> result = db.selectValues("SELECT field FROM USER_" + user.id(), 1);
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
		
		JButton next = new JButton("Next");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				AccReset accReset = new AccReset(parent);
				accReset.addQueuedQueries(accuEntries);
				accReset.setAccount(account);
				
				// Private information on account
				Object[] objects = ((DefaultListModel<String>) privateList.getModel()).toArray();
				List<String> priv = new ArrayList<String>();
				for (Object o : objects) {
					priv.add((String) o);
				}
			    accReset.addQueuedQuery("UPDATE ACCOUNTS_INFO_" + user.id() + " SET priv='" + String.join(",", priv) + "' WHERE id='" 
			    		+ account + "'");
			    
			    // Public information on account
				objects = ((DefaultListModel<String>) publicList.getModel()).toArray();
				List<String> pub = new ArrayList<String>();
				for (Object o : objects) {
					pub.add((String) o);
				}
				accReset.addQueuedQuery("UPDATE ACCOUNTS_INFO_" + user.id() + " SET pub='" + String.join(",", pub) + "' WHERE id='" 
			    		+ account + "'");
				
				parent.addPanel(accReset);
			}
		});
		next.setBounds(980, 585, 249, 41);
		this.add(next);
		
		JButton btnBack = new JButton("< Back");
		btnBack.setBounds(83, 587, 171, 41);
		add(btnBack);
		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parent.addPanel(new NewAccount(parent));	
			}
			
		});
	}
	
	public void setSP(ServiceProvider sp) {
		serviceProvider = sp;
	}
	
	public void setAccount(String acc) {
		account = acc;
	}
	
	public void addQueuedQuery(String query) {
		accuEntries.add(query);
	}

}
