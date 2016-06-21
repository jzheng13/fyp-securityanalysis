package visualisation;

import java.util.List;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.view.Viewer;

import application.Account;
import application.Name;
import countermeasures.Countermeasures;
import countermeasures.Utils;

public class Network {
	
	private Name user;
	private Graph graph;
	private Viewer viewer;

	public Network(Name user) {
		
		// System.setProperty("gs.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		this.user = user;
		this.graph = new MultiGraph(user.id());
		graph.addAttribute("ui.stylesheet", "url('file:///" 
		    + Utils.resolvePath(System.getProperty("user.dir"), "src/application/stylesheet.css")
		    + "')");
		graph.addAttribute("ui.antialias");
		this.viewer = graph.display();
		build();
	}
	
	private void build() {
		List<String> allAccounts = Countermeasures.getAllAccounts();
		for (String account : allAccounts) {
			if (graph.getNode(account) == null) {
			    Node n = graph.addNode(account);
			    Account a = new Account(user, account);
			    n.addAttribute("ui.label", a.getSP() + ": " + a.getUsername());
			}
			int eid = 0;
			List<String[]> connections = Countermeasures.getConnections(account);
			for (String[] c : connections) {
				if (graph.getNode(c[0]) == null) {
				    Node n = graph.addNode(c[0]);
				    Account a = new Account(user, c[0]);
				    n.addAttribute("ui.label", a.getSP() + ": " + a.getUsername());
				}
				eid++;
				String connection = c[2] + " " + c[1];
                Edge e = graph.addEdge(account + eid, account, c[0], true);
                e.addAttribute("ui.label", connection);
			}
		}
		
	}
	
	public void labelCompromisedAccounts(List<String> compromised) {
		for (String c : compromised) {
			graph.getNode(c).addAttribute("ui.class", "compromised");
		}
	}
	
	public Graph graph() {
		return graph;
	}
	
	public Viewer view() {
		viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
		return viewer;
	}
	
}
