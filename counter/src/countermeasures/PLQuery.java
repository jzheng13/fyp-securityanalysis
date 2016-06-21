package countermeasures;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jpl7.*;

public class PLQuery {
	
	private String relativePath;
	private PL plAtom;
	private Query query;
	
	public PLQuery(String relativePath){
		this.relativePath = relativePath;
		this.plAtom = new PL(relativePath);
	}
	
	public void consult() {
		query = new Query("consult", new Term[]{plAtom});
		System.out.println("Consult " + plAtom.toString()+ " " 
		    + (query.hasSolution() ? "succeeded." : "failed."));
	}
	
	public void query(String ask) {
		Query q;
		try {
			q = new Query(ask);
			if (q.hasSolution()){
				System.out.println("?- " + ask + ".");
			}
		} catch(PrologException pe){
			System.err.println(pe.getMessage());
		}
	}
	
	public void printOutput(String function, Term[] terms) {
		printFunction(function, terms);
		int n = 1;
		this.query("protocol('output" + n + ".txt').");
		Query q = new Query(function, terms);
		q.open();
		Map<String, Term> solution = q.getSolution();
		printSolution(terms, solution);
		/*
		while (q.hasNext()){
			n++;
			solution = q.nextSolution();
			printSolution(terms, solution);
		}
		*/
		this.query("noprotocol");
	}
	
	public void printAllOutput(String function, Term[] terms) {
		printFunction(function, terms);
		this.query("protocol('outputall.txt').");
		Query q = new Query(function, terms);
		q.open();
		Map<String, Term> solution;
		while (q.hasNext()){
			solution = q.nextSolution();
			printSolution(terms, solution);
		}
		this.query("noprotocol");
	}
	
	private void printFunction(String function, Term[] terms) {
		String qString = "?- " + function + "(";
		for (int i = 0; i < terms.length; i++) {
			qString += terms[i];
			qString += i < terms.length - 1 ? ", " : ").";
		}
		System.out.println(qString);
	}
	
	private void printSolution(Term[] terms
			, Map<String, Term> soln) {
		for (Term t : terms) {
			System.out.println(!t.isAtom() ? t.name() + " = " 
		        + soln.get(t.name()) : "");
		}
	}
	
	public List<String> hasAccount() {
		Term[] terms = new Term[] {new Variable("_"), new Variable("_"), new Variable("Account")};
		String function = "hasAccount";
		List<String> accs = new ArrayList<String>();
		printFunction(function, terms);
		Query q = new Query(function, terms);
		q.open();
		Map<String, Term> solution;
		while (q.hasNext()) {
			solution = q.nextSolution();
			String acc = solution.get("Account").toString();
			if (!accs.contains(acc)) {
				accs.add(acc);
			}
		}
		return accs;
	}
	
	public List<String[]> accountConn(String account) {
		Term[] terms = new Term[] {new Atom(account), new Variable("Acc"), 
				                   new Variable("Field"), new Variable("Conn")};
		String function = "accountConn";
		List<String[]> conns = new ArrayList<String[]>();
		printFunction(function, terms);
		Query q = new Query(function, terms);
		q.open();
		Map<String, Term> solution;
		while (q.hasNext()){
			solution = q.nextSolution();
			String[] c = {solution.get("Acc").toString()
					, solution.get("Field").toString(), solution.get("Conn").toString()};
			if (!conns.contains(c)) {
				conns.add(c);
			}
		}
		return conns;
	}
	
	public List<String> policyViolation(Atom person) {
		this.query("protocol('outputall.txt').");
		Term[] terms = new Term[] {new Variable("Acc"), person};
		String function = "policyViolation";
		List<String> accs = new ArrayList<String>();
		printFunction(function, terms);
		Query q = new Query(function, terms);
		q.open();
		Map<String, Term> solution;
		while (q.hasNext()){
			solution = q.nextSolution();
			String acc = solution.get("Acc").toString();
			if (!accs.contains(acc)) {
				accs.add(solution.get("Acc").toString());
			}
		}
		this.query("noprotocol");
		return accs;
	}
	
	public List<String[]> counterVul(String account) {
		Term[] terms = new Term[] {new Atom(account), new Variable("Vulnerability")
				, new Variable("Assert"), new Variable("Remove")};
		String function = "counter";
		List<String[]> counter = new ArrayList<String[]>();
		printFunction(function, terms);
		Query q = new Query(function, terms);
		q.open();
		Map<String, Term> solution;
		while (q.hasNext()){
			solution = q.nextSolution();
			String[] c = {solution.get("Vulnerability").toString()
					, solution.get("Assert").toString(), solution.get("Remove").toString()};
			if (!counter.contains(c)) {
				counter.add(c);
			}
		}
		return counter;
	}
	
	public List<String[]> counterLink(String account) {
		Term[] terms = new Term[] {new Atom(account), new Variable("LA"),
				new Variable("Field"), new Variable("Relation"), 
				new Variable("Assert"), new Variable("Remove")};
		String function = "counterConn";
		printFunction(function, terms);
		Query q = new Query(function, terms);
		q.open();
		List<String[]> counter = new ArrayList<String[]>();
		Map<String, Term> solution;
		while (q.hasNext()){
			solution = q.nextSolution();
			String[] c = {solution.get("LA").toString(), solution.get("Field").toString(), 
					solution.get("Relation").toString(), solution.get("Assert").toString(), 
					solution.get("Remove").toString()};
			if (!counter.contains(c)) {
				counter.add(c);
			}
		}
		return counter;
		
	}

}
