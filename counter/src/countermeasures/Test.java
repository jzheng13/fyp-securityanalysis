package countermeasures;

import org.jpl7.*;

public class Test {
	
	public static void main(String[] args) {
		PLQuery q = new PLQuery("../engine/policy.pl");
		q.consult();
		q.query("leash(-all)");
		q.query("trace(hasAccess/2)");
		q.query("trace(hasAccessTo/3)");
		q.query("trace(knowsAll/4)");
		q.query("trace(knows/4)");
		q.query("trace(singleSignOn/2)");
		q.query("trace(resetInfo/3)");
		q.query("trace(vulExists/2)");
		q.query("trace(publicInfo/2)");
		q.query("trace(canRetrieve/3)");
		q.query("trace(accountConn/4)");
		q.query("trace(userPublic/1)");
		// q.query("trace(accountEmail/3)");
		q.query("trace(privateInfo/2)");
		q.query("trace(pwContains/2)");
		q.printOutput("policyViolation", new Term[] {
				new Atom("apple1"), new Atom("attacker")});
		GraphGenerator.makeTree("output1.txt", "tree1.txt");
		GraphGenerator.dotRepresentation("tree1.txt", "graph1.dot");
		GraphGenerator.dotFigure("tree1.txt", "ag1");
	}
	

}
