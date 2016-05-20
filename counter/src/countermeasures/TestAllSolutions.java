package countermeasures;

import org.jpl7.Atom;
import org.jpl7.Term;
import org.jpl7.Variable;

public class TestAllSolutions {
	
	public static void main(String[] args) {
		PLQuery q = new PLQuery("../engine/policy.pl");
		q.consult();
		/*
		 * 		leash(-all), trace(hasAccess/2), trace(hasAccessTo/3), trace(knowsAll/4), trace(knows/4).
				trace(singleSignOn/2), trace(resetInfo/3), trace(vulExists/2), trace(publicInfo/2).
				trace(canRetrieve/3), trace(accountConn/4), trace(userPublic/1), trace(privateInfo/2), trace(pwContains/2).
		 */
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
		q.query("trace(privateInfo/2)");
		q.query("trace(pwContains/2)");
		q.printAllOutput("policyViolation", new Term[] {
				new Variable("X"), new Atom("attacker")});
		GraphGenerator.makeTreeAll("outputall.txt", "treeall.txt");
		GraphGenerator.dotRepresentationAll("treeall.txt", "graphall.dot");
		// GraphGenerator.dotFigure("treeall.txt", "agall");
	}
}
