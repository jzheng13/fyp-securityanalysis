package application;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AnalysisMain extends JPanel {

	/**
	 * Create the panel.
	 */
		
	public AnalysisMain(AppFrame parent) {
		// this.setBounds(0, 0, 0, 0);
		this.setBounds(100, 100, 1312, 772);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		
		JLabel lblOptions = new JLabel("Welcome " + parent.getUser() +", select one of the following");
		lblOptions.setBounds(26, 28, 1154, 57);
		lblOptions.setFont(new Font("Tahoma", Font.BOLD, 34));
		this.add(lblOptions);
		
		JButton addAccount = new JButton("Add new account");
		addAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewAccount newAcc = new NewAccount(parent);
				parent.addPanel(newAcc);
			}
		});
		addAccount.setBounds(518, 255, 275, 41);
		this.add(addAccount);
		
		JButton editExisting = new JButton("Edit existing account");
		editExisting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ExistAcc existAcc = new ExistAcc(parent);
				parent.addPanel(existAcc);
			}
		});
		editExisting.setBounds(518, 358, 275, 41);
		this.add(editExisting);
		
		JButton btnAnalysis = new JButton("Analysis");
		btnAnalysis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Analysis analysis = new Analysis(parent);
				parent.addPanel(analysis);
			}
		});
		btnAnalysis.setBounds(518, 454, 275, 41);
		add(btnAnalysis);
		
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
	}
}
