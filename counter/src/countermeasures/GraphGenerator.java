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

	public static void makeTree(String file){
		Path path = Paths.get(file);
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
			PrintWriter writer = new PrintWriter("tree1.txt");
		    int minLvl = 100;
		    int fail = 0;
		    int redo = 0;
		    String redoString = "";
			while (!reversed.isEmpty()) {
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
	        	} else if (redo == 0 && elements[2].equals("Redo:")) {
	        		redo++;
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
	        		} else if (elements[2].equals("Call:")) {
	        			fail--;
	        		}
	        	}
			}
			writer.close();
		} catch (FileNotFoundException e) {
			System.err.format(e.getMessage());
		}

	}
	
	public static void dotRepresentation(String file) {
		Path path = Paths.get(file);
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
		    			dotRep.add("\"" + cur.peek() + "\"" + " -> " + "\"" + predicates[1]+ "\"");
		    		}
		    		cur.push(predicates[1]);
		    		lvl.push(curLvl);
		    	} else if (curLvl == lvl.peek()) {
		    		cur.pop();
		    		dotRep.add("\"" + cur.peek() + "\"" + " -> " + "\"" + predicates[1]+ "\"");
		    		cur.push(predicates[1]);
		    	} 
		    }
		} catch (IOException x) {
		    System.err.format(x.getMessage());
		}
	
		try {
			PrintWriter writer = new PrintWriter("dot1.txt");
			writer.println("digraph G {");
			while (!dotRep.isEmpty()){
				writer.println(dotRep.remove(0) + ";");
			}
			writer.println("}");
			writer.close();
		} catch (FileNotFoundException e) {
			System.err.format(e.getMessage());
		}
	}
	
	public static void dotFigure(String file) {
		Path path = Paths.get(file);
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
		    			graph.addln("\"" + cur.peek() + "\"" + " -> " + "\"" + predicates[1]+ "\"");
		    		}
		    		cur.push(predicates[1]);
		    		lvl.push(curLvl);
		    	} else if (curLvl == lvl.peek()) {
		    		cur.pop();
		    		graph.addln("\"" + cur.peek() + "\"" + " -> " + "\"" + predicates[1]+ "\"");
		    		cur.push(predicates[1]);
		    	} 
		    }
		} catch (IOException x) {
		    System.err.format(x.getMessage());
		}
		graph.addln(graph.end_graph());
		System.out.println(graph.getDotSource());
		String type = "gif";
		String representationType = "dot";
		File out = new File("graph1." + type);
		graph.writeGraphToFile(graph.getGraph(graph.getDotSource(), type, representationType), out);
	}
	
}
