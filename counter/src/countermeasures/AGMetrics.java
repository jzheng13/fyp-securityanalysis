package countermeasures;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class AGMetrics {
	
	private String filename;
	private int paths = 0;
	private List<Integer> lengths;
	private List<List<String>> ags;
	
	public AGMetrics(String filename) {
		this.filename = filename;
		// Memoization for quicker implementation
		this.lengths = new ArrayList<Integer>();
		this.ags = new ArrayList<List<String>>();
		countAllPaths();
	}
	
	public Set<String> getMinCritSet() {
		
		Set<String> critSet = new HashSet<String>();
		
		// Copy of all attack graphs
		List<List<String>> agscopy = ags;
		// List of all attack graph nodes
		List<String> set = allNodes();
		
		// While attack graphs are not covered
		while(!agscopy.isEmpty()) {
			// Initialise count for number of covered graphs
			int[] count = new int[set.size()];
			// Initialise list of covered graphs for all nodes
			List<List<List<String>>> covered = new ArrayList<List<List<String>>>();
			int index = 0;
			// For every node in the set
			for (String s : set) {
				//Initialise covered graphs for each node
				List<List<String>> cover = new ArrayList<List<String>>();
				//For every attack graph in list of attack graphs
				if (!s.contains("hasAccess")) {
					for (List<String> ag : agscopy) {
						//If graph contains node
						if (ag.contains(s)) {
							// Add counter for node
							count[index]++;
							// Add graph to covered graph
							cover.add(ag);
						} 
					}
				}
				// Add list of covered graphs to all lists
				covered.add(cover);
				index++;
			}
			// Get node with maximum coverage
			int max = getMaxIndex(count);
			// Add node to crit set
			critSet.add(set.get(max));
			// Update set by removing node from arsenal
			set.remove(max);
			// Remove all covered attack graphs
			agscopy.removeAll(covered.get(max));
			// Clear covered graphs for next iteration
			covered.clear();
		}
		// WORKING System.out.println("critset=" + critSet); but need to store value since
		// on second iteration becomes empty (fix not necessary)
		return critSet;
	}
	
	public String minCounter(Set<String> minCrit) {
		String counter = "";
		for (String c : minCrit) {
			String[] tokens = c.split("[\",()]");
			//System.out.println(tokens[1]);
			switch (tokens[1]) {
			    case "vulExists":
			    	if (tokens[3].equals("publicUser")) {
			    		counter += "- Change username privacy";
			    	} else if (tokens[3].equals("publicEmail")) {
			    		counter += "- Change email privacy";
			    	} else {
			    		counter += "- Change password";
			    	}
			    	counter += " for " + tokens[2] + "\n";
			    	break;
			    case "userPublic": 
			    	counter += "- Change username privacy for " + tokens[2] + "\n";
			    	break;
			    case "accountConn": 
			    	if (tokens[4].equals("password") || tokens[4].equals("email")) {
			    		counter += "- Change " + tokens[4] + " for " + tokens[3] + "\n";
			    	} else if (!tokens[4].equals("username")) {
			    		counter += "- Change privacy of " + tokens[4] + " for " + tokens[3] + "\n";
			    	}
			    	break;
				default: //System.out.println("tokens=" + tokens[1]);
			}
		}
		return counter;
	}
	
	public Set<String> getSingleAction() {
        Set<String> singleAct = new HashSet<String>();
		
		// Copy of all attack graphs
		List<List<String>> agscopy = ags;
		// List of all attack graph nodes
		List<String> set = allNodes();
		
		// Initialise count for number of covered graphs
		int[] count = new int[set.size()];
		int index = 0;
		// For every node in the set
		for (String s : set) {
			if (s.contains("hasAccessTo")) {
				continue;
			}
			for (List<String> ag : agscopy) {
				//If graph contains node
				if (ag.contains(s)) {
					// Add counter for node
					count[index]++;
				} 
			}
		}
		// Get node with maximum coverage
		List<Integer> maxes = getMaxIndices(count);
		// Add node to crit set
		for (int m : maxes) {
			singleAct.add(set.get(m));
		}
		return singleAct;
	}
	
	private void countAllPaths() {
		Path p = Paths.get(filename);
		try (BufferedReader reader = Files.newBufferedReader(p)) {
		    String line = null;
		    int l = 0;
		    List<String> nodes = new ArrayList<String>();
		    while ((line = reader.readLine()) != null) {
		    	String[] tokens = line.split(" ");
		    	for (String t : tokens) {
		    		if (t.equals("subgraph")) {
		    			paths++;
		    			lengths.add(l);
		    			ags.add(nodes);
		    			nodes = new ArrayList<String>();
		    			//System.out.println("ags=" + ags);
		    			//nodes.clear();
		    			l = 0;
		    		} else if (t.equals("->")) {
		    			l++;
		    		} else if (t.charAt(0) == '"'){
		    			if (!nodes.contains(t)) {
		    			    nodes.add(t);
		    			}
		    		}
		    	}
		    }
		    lengths.remove(0);
		    ags.remove(0);
		} catch (IOException x) {
		    System.err.format(x.getMessage());
		}
		
	}
	
	public int getNoPaths() {
		return paths;
	}
	
	public int getShortestPath() {
		//System.out.print(lengths);
		return Collections.min(lengths);
	}
	
	public double getMeanPath() {
		double sum = 0;
		for (int l : lengths) {
			sum += l;
		}
		return round2dp(sum / paths);
	}
	
	public int getMode() {
		// TODO multiple modes
		Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
		int count = 1;
		int mode = lengths.get(0);
		for (int l : lengths) {
			if (!freq.containsKey(l)) {
				freq.put(l, 1);
			} else {
				freq.replace(l, freq.get(l) + 1);
			}
		}
		for (Map.Entry<Integer, Integer> f : freq.entrySet()) {
			if (f.getValue() > count) {
				mode = f.getKey();
			}
		}
		return mode;
	}
	
	public double getMedian() {
		Collections.sort(lengths);
		if (paths % 2 == 0) {
			return (lengths.get(paths / 2) + lengths.get(paths/2 + 1)) / 2;
		}
		return round2dp(lengths.get(paths / 2 + 1));
	}
	
	public double getStdDev() {
		double mean = getMeanPath();
		double sum = 0;
		for (int l : lengths) {
			sum += (l - mean) * (l - mean);
		}
		return round2dp(sum / paths);
	}
	
	public double getNormMean() {
		return round2dp(getMeanPath() / paths);
	}
	
	private double round2dp(double d) {
		return Math.round(d * 100.0 ) / 100.0;
	}
	
	private List<String> allNodes() {
		Set<String> set = new HashSet<String>();
		for (List<String> ag : ags) {
			set.addAll(ag);
		}
		List<String> setList = new ArrayList<String>(set);
		return setList;
	}
	
	private List<Integer> getMaxIndices(int[] numbers) {
		List<Integer> indices = new ArrayList<Integer>();
		assert(numbers.length >= 1);
		int max = numbers[0];
		indices.add(0);
		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i] >= max) {
				if (numbers[i] > max) {
					max = numbers[i];
					indices.clear();
				} 
				indices.add(i);
			}
		}
		return indices;
	}
	
	private int getMaxIndex(int[] numbers) {
		assert(numbers.length >= 1);
		int max = numbers[0];
		int index = 0;
		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i] > max) {
				max = numbers[i];
				index = i;
			}
		}
		return index;
	}

}
