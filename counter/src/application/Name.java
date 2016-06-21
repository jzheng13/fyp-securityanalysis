package application;

public class Name {
	
	private String first;
	private String last;
	
	public Name(String first, String last) {
		this.first = first;
		this.last = last;
	}
	
	public String toString() {
		return first + " " + last;
	}
	
	public String id() {
		return first + last;
	}

}
