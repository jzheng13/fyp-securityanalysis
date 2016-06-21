package application;

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

import org.graphstream.ui.view.View;

import countermeasures.Countermeasures;
import countermeasures.GraphGenerator;
import visualisation.Network;
import visualisation.NetworkViewer;

import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Analysis extends JPanel {

	/**
	 * Create the panel.
	 */
	public Analysis(AppFrame parent) {
		// this.setBounds(0, 0, 0, 0);
		this.setBounds(100, 100, 1312, 772);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		ParseData pd = new ParseData(parent.getDatabase(), parent.getUser());
		pd.parseUserInfo();
		pd.parseAccountInfo();
		
		JLabel analysisResults = new JLabel("Analysis results");
		analysisResults.setBounds(26, 28, 1154, 57);
		analysisResults.setFont(new Font("Tahoma", Font.BOLD, 34));
		this.add(analysisResults);
		
		JLabel instructions1 = new JLabel("The following is the list of compromised accounts. Click on the account name to view more. ");
		instructions1.setBounds(83, 89, 1146, 33);
		add(instructions1);
		
		JLabel instructions2 = new JLabel("details.");
		instructions2.setBounds(83, 130, 1146, 33);
		add(instructions2);
		
		DefaultListModel<Account> model = new DefaultListModel<Account>();
		List<String> result = Countermeasures.getCompromisedAccounts();
		if (result != null) {
			for (String r : result) {
				model.addElement(new Account(parent.getUser(), r));
			}
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(83, 186, 503, 280);
		
		JList<Account> accList = new JList<Account>(model);
		
		
		JTextPane textPane = new JTextPane();
		JScrollPane txtScrollPane = new JScrollPane(textPane);
		txtScrollPane.setBounds(726, 186, 503, 280);
		add(txtScrollPane);
		//textPane.setVisible(false);
		
		accList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Account selected = accList.getSelectedValue();
				String text = "";
				List<String> counterInfo = Countermeasures.getCounterMeasures(selected.id());
				for (String c: counterInfo) {
					text += c;
				}
				textPane.setText(text);
			}
			
		});
		
		scrollPane.setViewportView(accList);
		add(scrollPane);
		
		JButton btnFinish = new JButton("Finish");
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.getContentPane().removeAll();
				parent.addPanel(new Main(parent));
			}
		});
		btnFinish.setBounds(985, 585, 244, 41);
		this.add(btnFinish);
		
		JButton btnAGMetrics = new JButton("Advanced >");
		btnAGMetrics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GraphGenerator.makeTreeAll("outputall.txt", "treeall.txt");
				GraphGenerator.dotRepresentationAll("treeall.txt", "graphall.dot");
				parent.addPanel(new Graph(parent));
			}
		});
		btnAGMetrics.setBounds(726, 585, 244, 41);
		this.add(btnAGMetrics);
		
		JButton btnBack = new JButton("< Back");
		btnBack.setBounds(83, 587, 171, 41);
		add(btnBack);
		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parent.addPanel(new AnalysisMain(parent));
			}
		});
		
		JButton btnViewNetworkGraph = new JButton("View Network Graph");
		btnViewNetworkGraph.setBounds(325, 585, 316, 41);
		add(btnViewNetworkGraph);
		
		btnViewNetworkGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Network network = new Network(parent.getUser());
				network.labelCompromisedAccounts(result);
				NetworkViewer nViewer = new NetworkViewer(network);
			}
		});
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(415, 494, 171, 41);
		add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Account selected = accList.getSelectedValue();
				parent.setAccount(selected);
				parent.addPanel(new EditAccount(parent));
			}
			
		});
	}
}
