package countermeasures;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GraphGenerator {

	public static void makeTree(String src, String dest){
		Path path = Paths.get(src);
		Stack<String> reversed = new Stack<String>();

		try (BufferedReader reader = Files.newBufferedReader(path)) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		    	reversed.push(line);
		    }
		} catch (IOException x) {
		    System.err.format(x.getMessage());
		}
		
		try {
			PrintWriter writer = new PrintWriter(dest);
			PrintWriter debug = new PrintWriter("debug_single.txt");
		    int minLvl = 100;
		    int fail = 0;
		    int redo = 0;
		    String redoString = "";
			while (!reversed.isEmpty()) {
				debug.println(reversed.peek());
				String[] elements = reversed.pop().split(" ");
		        if (!elements[0].equals("")) {
		        	break;
		        }
		        int lvl = Integer.parseInt(elements[3].replaceAll("[()]", ""));
		        if (lvl < minLvl) {
		        	minLvl = lvl;
		        }
		        String query = lvl + " ";
	        	for (int i = 4; i < elements.length; i++) {
	        		query += elements[i];
	        	}
	        	
	        	if (redo == 0 && elements[2].equals("Fail:")) {
	        		fail++;
	        		debug.println("fail = " + fail);
	        	} else if (redo == 0 && elements[2].equals("Redo:")) {
	        		redo++;
	        		debug.println("redo = " + redo);
	        		for (int i = 4; i < elements.length; i++) {
	        			redoString += elements[i];
	        		}
	        	}
	        	
	        	if (redo > 0) {
	        		String comp = "";
	        		for (int i = 4; i < elements.length; i++) {
	        			comp += elements[i];
	        		}
	        		if (elements[2].equals("Call:") && comp.equals(redoString)) {	
	        			redo = 0;
	        			debug.println("redo = " + redo);
	        			redoString = "";
	        		}
	        	}
	        	
	        	if (fail == 0 && redo == 0) {
			        if (elements[2].equals("Exit:")) {
			        	writer.println(query);
			        }
	        	} else if (redo == 0){
	        		if (elements[2].equals("Exit;")) {
	        			fail++;
	        			debug.println("fail = " + fail);
	        		} else if (elements[2].equals("Call:")) {
	        			fail--;
	        			debug.println("fail = " + fail);
	        		}
	        	}
			}
			debug.close();
			writer.close();
		} catch (FileNotFoundException e) {
			System.err.format(e.getMessage());
		}

	}
	
	public static void makeTreeAll(String src, String dest){
		Path path = Paths.get(src);
		Stack<String> reversed = new Stack<String>();

		try (BufferedReader reader = Files.newBufferedReader(path)) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		    	reversed.push(line);
		    }
		} catch (IOException x) {
		    System.err.format(x.getMessage());
		}
		
		try {
			PrintWriter debug = new PrintWriter("debug.txt");
			List<PrintWriter> writers = new ArrayList<PrintWriter>();
			List<Integer> fails = new ArrayList<Integer>();
			List<Integer> redos = new ArrayList<Integer>();
			List<String> redoStrings = new ArrayList<String>();
			int n = 0;
			int minLvl = 100;
			writers.add(new PrintWriter(n + dest));
		    fails.add(0);
		    redos.add(0);
		    redoStrings.add("");
		    while (!reversed.isEmpty()) {
		    	String start = reversed.peek();
		    	String[] elements = start.split(" ");
		    	int lvl = Integer.parseInt(elements[3].replaceAll("[()]", ""));
		        minLvl = lvl < minLvl ? lvl : minLvl;
		    	if (elements[2].equals("Exit:") && lvl == minLvl) {
		    		break;
		    	} else {
		    		System.out.println(start);
		    		reversed.pop();
		    	}
		    }
			while (!reversed.isEmpty()) {
				String debugString = reversed.peek();
				debug.println(debugString);
				String[] elements = reversed.pop().split(" ");
		        if (!elements[0].equals("")) {
		        	break;
		        }
		        int lvl = Integer.parseInt(elements[3].replaceAll("[()]", ""));
		        if (lvl < minLvl) {
		        	minLvl = lvl;
		        }
		        String query = lvl + " ";
	        	for (int i = 4; i < elements.length; i++) {
	        		query += elements[i];
	        	}
	        	
	        	if (lvl == minLvl && elements[2].equals("Exit:")) {
	        		n++;
	        		writers.get(n - 1).close();
	        		writers.add(new PrintWriter(n + dest));
	    		    fails.add(0);
	    		    redos.add(0);
	    		    redoStrings.add("");
	        	}

	        	for (int i = 0; i <= n; i++) {
		        	if (redos.get(i) == 0 && elements[2].equals("Fail:")) {
		        		fails.add(i, fails.remove(i) + 1);
		        		debug.println("fails " + i + " = " + fails.get(i));
		        	} else if (redos.get(i) == 0 && elements[2].equals("Redo:")) {
		        		redos.add(i, redos.remove(i) + 1);
		        		debug.println("redos " + i + " = " + redos.get(i));
		        		String redoString = "";		
		        		for (int j = 4; j < elements.length; j++) {
		        			redoString += elements[j];	
		        		}
		        		redoStrings.remove(i);
	        			redoStrings.add(i, redoString);
		        		debug.println("redoString " + i + " = " + redoStrings.get(i));
		        	}
		        	
		        	if (redos.get(i) > 0) {
		        		String comp = "";
		        		for (int j = 4; j < elements.length; j++) {
		        			comp += elements[j];
		        		}
		        		if (elements[2].equals("Call:") && comp.equals(redoStrings.get(i))) {
		        			redos.remove(i);
		        			redos.add(i, 0);
		        			debug.println("redos " + i + " = " + redos.get(i));
		        			redoStrings.remove(i);
		        			redoStrings.add(i, "");
		        		}
		        	}
		        	
		        	if (fails.get(i) == 0 && redos.get(i) == 0) {
				        if (elements[2].equals("Exit:")) {
				        	writers.get(i).println(query);
					        debug.println(i + " output print");
				        }
		        	} else if (redos.get(i) == 0){
		        		if (elements[2].equals("Exit;")) {
		        			fails.add(i, fails.remove(i) + 1);
		        		} else if (elements[2].equals("Call:")) {
		        			fails.add(i, fails.remove(i) - 1);
		        		}
		        		debug.println("fails " + i + " = " + fails.get(i));
		        	}
	        	}
			}
			debug.close();
        	for(PrintWriter writer : writers) {
        		writer.close();
        	}
		} catch (FileNotFoundException e) {
			System.err.format(e.getMessage());
		}

	}
	
	public static void dotRepresentation(String src, String dest) {
		Path path = Paths.get(src);
		List<String> dotRep = new ArrayList<String>();
		try (BufferedReader reader = Files.newBufferedReader(path)) {
		    String line = null;
		    Stack<Integer> lvl = new Stack<Integer>();
		    Stack<String> cur = new Stack<String>();
		    lvl.push(0);
		    while ((line = reader.readLine()) != null) {
		    	String[] predicates = line.split(" ");
		    	int curLvl = Integer.parseInt(predicates[0]);
	    		while (curLvl < lvl.peek()) {
	    			cur.pop();
	    			lvl.pop();
	    		}
		    	if ( curLvl > lvl.peek()) {
		    		if (!cur.isEmpty()) {
		    			dotRep.add("\"" + cur.peek() + "\"" + " -> " + "\"" 
		    					+ predicates[1]+ "\"");
		    		}
		    		cur.push(predicates[1]);
		    		lvl.push(curLvl);
		    	} else if (curLvl == lvl.peek()) {
		    		cur.pop();
		    		dotRep.add("\"" + cur.peek() + "\"" + " -> " + "\"" 
		    				+ predicates[1]+ "\"");
		    		cur.push(predicates[1]);
		    	} 
		    }
		} catch (IOException x) {
		    System.err.format(x.getMessage());
		}
	
		try {
			PrintWriter writer = new PrintWriter(dest);
			writer.println("digraph G {");
			while (!dotRep.isEmpty()){
				writer.println(dotRep.remove(0) + ";");
			}
			writer.println("}");
			writer.close();
		} catch (FileNotFoundException e) {
			System.err.format(e.getMessage());
		}
		Runtime rt = Runtime.getRuntime();
		try {
			Process p = rt.exec("dot -Tpng " + dest + " > ag1.png", null, 
					new File(System.getProperty("user.dir")));
		} catch (IOException e) {
			System.err.format(e.getMessage());
		}
	}
	
	public static void dotRepresentationAll(String src, String dest) {
		int n = 0;
		Path path = Paths.get(n + src);
		List<String> dotRep = new ArrayList<String>();
		while (Files.exists(path)) {
			try (BufferedReader reader = Files.newBufferedReader(path)) {
			    String line = null;
			    Stack<Integer> lvl = new Stack<Integer>();
			    Stack<String> cur = new Stack<String>();
			    lvl.push(0);
			    while ((line = reader.readLine()) != null) {
			    	String[] predicates = line.split(" ");
			    	int curLvl = Integer.parseInt(predicates[0]);
		    		while (curLvl < lvl.peek()) {
		    			cur.pop();
		    			lvl.pop();
		    		}
			    	if ( curLvl > lvl.peek()) {
			    		if (!cur.isEmpty()) {
			    			dotRep.add("\"" + cur.peek() + "\"" + " -> " + "\"" 
			    					+ predicates[1]+ "\"");
			    		}
			    		cur.push(predicates[1]);
			    		lvl.push(curLvl);
			    	} else if (curLvl == lvl.peek()) {
			    		cur.pop();
			    		dotRep.add("\"" + cur.peek() + "\"" + " -> " + "\"" 
			    				+ predicates[1]+ "\"");
			    		cur.push(predicates[1]);
			    	} 
			    }
			} catch (IOException x) {
			    System.err.format(x.getMessage());
			}
			n++;
			path = Paths.get(n + src);
		}
	
		try {
			PrintWriter writer = new PrintWriter(dest);
			writer.println("digraph G {");
			while (!dotRep.isEmpty()){
				writer.println(dotRep.remove(0) + ";");
			}
			writer.println("}");
			writer.close();
		} catch (FileNotFoundException e) {
			System.err.format(e.getMessage());
		}
		Runtime rt = Runtime.getRuntime();
		try {
			Process p = rt.exec("dot -Tpng " + dest + " > ag1.png", null, 
					new File(System.getProperty("user.dir")));
		} catch (IOException e) {
			System.err.format(e.getMessage());
		}
	}
	
	public static void dotFigure(String src, String dest) {
		Path path = Paths.get(src);
		GraphViz graph = new GraphViz();
		graph.addln(graph.start_graph());
		try (BufferedReader reader = Files.newBufferedReader(path)) {
		    String line = null;
		    Stack<Integer> lvl = new Stack<Integer>();
		    Stack<String> cur = new Stack<String>();
		    lvl.push(0);
		    while ((line = reader.readLine()) != null) {
		    	String[] predicates = line.split(" ");
		    	int curLvl = Integer.parseInt(predicates[0]);
	    		while (curLvl < lvl.peek()) {
	    			cur.pop();
	    			lvl.pop();
	    		}
		    	if ( curLvl > lvl.peek()) {
		    		if (!cur.isEmpty()) {
		    			graph.addln("\"" + cur.peek() + "\"" + " -> " + "\"" 
		    					+ predicates[1]+ "\"");
		    		}
		    		cur.push(predicates[1]);
		    		lvl.push(curLvl);
		    	} else if (curLvl == lvl.peek()) {
		    		cur.pop();
		    		graph.addln("\"" + cur.peek() + "\"" + " -> " + "\"" 
		    				+ predicates[1]+ "\"");
		    		cur.push(predicates[1]);
		    	} 
		    }
		} catch (IOException x) {
		    System.err.format(x.getMessage());
		}
		graph.addln(graph.end_graph());
		System.out.println(graph.getDotSource());
		// String type = "png";
		// String representationType = "dot";
		// File out = new File(dest + type);
		// graph.writeGraphToFile(graph.getGraph(graph.getDotSource(), type, 
		//	representationType), out);
	}
	
}
