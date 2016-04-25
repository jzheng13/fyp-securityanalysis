package countermeasures;

import org.jpl7.*;

public class Interpreter {
	
	
	public static void main(String[] args) {
		Query q1 = new Query("consult", 
				new Term[] {new Atom("../../engine/policy.pl")});
		System.out.println("Hello world!");
		System.out.println( "consult " + (q1.isOpen() ? "succeeded" : "failed"));
	}
	
	
	String interpret(String ploutput) {
		return ploutput;
	}

}
