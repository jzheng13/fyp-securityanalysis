package application;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import countermeasures.AGMetrics;


import javax.swing.JTable;
import javax.swing.JTextPane;

public class Graph extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public Graph(AppFrame parent) {
		// this.setBounds(0, 0, 0, 0);
		this.setBounds(100, 100, 1312, 772);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		
		JLabel lblMetricsIntro = new JLabel("Attack graph metrics");
		lblMetricsIntro.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblMetricsIntro.setBounds(26, 28, 445, 41);
		this.add(lblMetricsIntro);
		
		JLabel instructions1 = new JLabel("The table below show the metrics of the resulting attack graph. The textbox show the minimum ");
		instructions1.setBounds(83, 89, 1146, 33);
		add(instructions1);
		
		JLabel instructions2 = new JLabel("countermeasures required to secure whole network of accounts.");
		instructions2.setBounds(83, 130, 1146, 33);
		add(instructions2);
		
		AGMetrics gMetrics = new AGMetrics("graphall.dot");
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(new String[] {"Metric", "Value"});
		String[] metrics = {"No. of paths", "Mean path length", "Shortest path",
				"Mode path length", "Median path length", "Standard deviation", "Normalised mean"
		};
		double[] values = {gMetrics.getNoPaths(), gMetrics.getMeanPath(), gMetrics.getShortestPath(),
				gMetrics.getMode(), gMetrics.getMedian(), gMetrics.getStdDev(), gMetrics.getNormMean()
		};
		for (int i = 0; i < metrics.length; i++) {
			dtm.addRow(new Object[]{metrics[i], values[i]});
		}
		Set<String> critSet = gMetrics.getMinCritSet();
		System.out.println(critSet);
		
		
		JTextPane textPane = new JTextPane();
		textPane.setText("Minimum countermeasures required: \n" + gMetrics.minCounter(critSet));
		JScrollPane txtScrollPane = new JScrollPane(textPane);
		txtScrollPane.setBounds(726, 186, 503, 280);
		add(txtScrollPane);
		
		JButton btnFinish = new JButton("Finish");
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parent.backToMain();
			}
		});
		btnFinish.setBounds(985, 585, 244, 41);
		this.add(btnFinish);
		
		table = new JTable(dtm);
		table.setRowHeight(30);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(83, 186, 503, 280);
		add(scrollPane);
		
		JButton btnBack = new JButton("< Back");
		btnBack.setBounds(83, 587, 171, 41);
		add(btnBack);
		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parent.addPanel(new Analysis(parent));
				
			}
			
		});
		
		JButton btnViewAG = new JButton("View attack graph");
		btnViewAG.setBounds(325, 585, 316, 41);
		add(btnViewAG);
		
		btnViewAG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Runtime rt = Runtime.getRuntime();
				try {
					Process p = rt.exec("cmd.exe /c " + "ag1.svg", null, 
							new File(System.getProperty("user.dir")));
				} catch (IOException e) {
					System.err.format(e.getMessage());
				}
			}
		});
	}
}
