package countermeasures;

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

}
