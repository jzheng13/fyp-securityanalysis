package countermeasures;

import java.util.ArrayList;
import java.util.List;

import org.jpl7.Atom;
import org.jpl7.Term;
import org.jpl7.Variable;

import application.Account;

public class Countermeasures {
	
	public static List<String> getAllAccounts() {
		PLQuery q = new PLQuery("../engine/policy.pl");
		q.consult();
		return q.hasAccount();
	}
	
	public static List<String[]> getConnections(String account) {
		PLQuery q = new PLQuery("../engine/policy.pl");
		q.consult();
		return q.accountConn(account);
	}
	
	public static List<String> getCompromisedAccounts() {
		PLQuery q = new PLQuery("../engine/policy.pl");
		q.consult();
		q.query("leash(-all)");
		q.query("trace(policyViolation/2)");
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
		return q.policyViolation(new Atom("attacker"));
		
	}
	
	public static List<String> getCounterMeasures(String account) {
		List<String> results = new ArrayList<String>();
		PLQuery q = new PLQuery("../engine/countermeasures.pl");
		q.consult();
		List<String[]> counters = q.counterVul(account);
		for (String[] c : counters) {
			String r = "Vulnerability: " + c[0] + "\nCountermeasure: ";
			switch(c[0]) {
			    case "publicUser": r += "edit username privacy\n"; break;
			    case "publicEmail": r += "edit email privacy\n"; break;
			    default: r += "change password\n"; break;
			}
			results.add(r);
		}
		counters = q.counterLink(account);
		for (String[] c : counters) {
			String r = "Linked account: " + c[0] + "\nConnection: " + c[1] + " " + c[2] + "\nCountermeasure: ";
			switch(c[2]) {
			    case "same": r += "change password\n"; break;
			    case "public": r += "edit " + c[1] + " privacy\n"; break;
			}
			results.add(r);
		}
		return results;
		
	}

}
