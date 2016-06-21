package application;

import java.util.List;

public class Account {
	
	private Name owner;
	private String id;
	private String serviceProvider;
	private String username;
	
	public Account(Name owner, String id) {
		this.owner = owner;
		this.id = id;
		
		Database db = new Database();
		db.startDB();
		List<String[]> r = db.selectValues("SELECT sp, username FROM ACCOUNTS_LOGIN_" 
		    + owner.id() + " WHERE id='" + id + "'", 2);
		this.serviceProvider = r.get(0)[0];
		this.username = r.get(0)[1];
		
	}
	
	public String getSP() {
		return serviceProvider;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String id() {
		return id;
	}
	
	public String toString() {
		return serviceProvider + ": " + username;
	}

}
