package application;

import countermeasures.Utils;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Stack;

public class ParseData {
	
	private Database db;
	private Name user;
	
	public ParseData(Database db, Name user) {
		this.db = db;
		this.user = user;
	}
	
	public void parseUserInfo() {
		String dest = Utils.resolvePath(System.getProperty("user.dir")
				, "../engine/user.pl"); 
		try {
			PrintWriter writer = new PrintWriter(dest);
			writer.println("%% USER: " + user.toString());
			writer.println();
			List<String[]> userInfo = db.selectValues("SELECT field, value FROM USER_" 
					+ user.id(), 2);
			for (String[] u : userInfo) {
				writer.println("userInfo(" + u[0] + ", \"" + u[1] + "\").");
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void parseAccountInfo() {
		String dest = Utils.resolvePath(System.getProperty("user.dir")
				, "../engine/accounts.pl"); 
		try {
			PrintWriter writer = new PrintWriter(dest);
			writer.println("%% ACCOUNTS INFO: " + user.toString());
			List<String[]> accountInfo = db.selectValues("SELECT sp, username, id FROM ACCOUNTS_LOGIN_" + user.id(), 3);
			writer.println();
			for (String[] acc : accountInfo) {
				writer.println("hasAccount(" + acc[0] + ", \"" + acc[1] + "\", " + acc[2] + ").");
			}
			writer.println();
			accountInfo = db.selectValues("SELECT id, username, password FROM ACCOUNTS_LOGIN_" + user.id(), 3);
			writer.println();
			for (String[] acc : accountInfo) {
				writer.println("accountLogin(" + acc[0] + ", \"" + acc[1] + "\", \"" + acc[2] + "\").");
			}
			writer.println();
			accountInfo = db.selectValues("SELECT id, mod_date FROM ACCOUNTS_LOGIN_" + user.id(), 2);
			writer.println();
			for (String[] acc : accountInfo) {
				writer.println("pwLastModified(" + acc[0] + ", (" + acc[1] + ")).");
			}
			writer.println();
			accountInfo = db.selectValues("SELECT id, pwinf FROM ACCOUNTS_LOGIN_" + user.id() 
				+ " WHERE pwinf IS NOT NULL", 2);
			for (String[] acc : accountInfo) {
				if (!acc[1].equals("")) {
					writer.println("pwContains(" + acc[0] + ", [" + acc[1] + "]).");
				}
			}
			writer.println();
			accountInfo = db.selectValues("SELECT id, sso FROM ACCOUNTS_LOGIN_" + user.id() + " WHERE sso IS NOT NULL", 2);
			for (String[] acc : accountInfo) {
				System.out.println("singleSignOn(" + acc[0] + ", " + acc[1] + ").");
				if (!acc[1].equals("")) {
					writer.println("singleSignOn(" + acc[0] + ", " + acc[1] + ").");
				}
			}
			writer.println();
			accountInfo = db.selectValues("SELECT id, email, empublic FROM ACCOUNTS_INFO_" + user.id() +
					" WHERE email IS NOT NULL", 3);
			for (String[] acc : accountInfo) {
				String empublic = acc[2].equals("0") ? "private" : "public";
				writer.println("accountEmail(" + acc[0] + ", " + acc[1] + ", " + empublic + ").");
			}
			writer.println();
			accountInfo = db.selectValues("SELECT id FROM ACCOUNTS_LOGIN_" + user.id() + " WHERE usrpublic=1", 1);
			for (String[] acc : accountInfo) {
				writer.println("userPublic(" + acc[0] + ").");
			}
			writer.println();
			accountInfo = db.selectValues("SELECT id, pub FROM ACCOUNTS_INFO_" + user.id(), 2);
			for (String[] acc : accountInfo) {
				writer.println("publicInfo(" + acc[0] + ", [" + acc[1] + "]).");
			}
			writer.println();
			accountInfo = db.selectValues("SELECT id, priv FROM ACCOUNTS_INFO_" + user.id(), 2);
			for (String[] acc : accountInfo) {
				writer.println("privateInfo(" + acc[0] + ", [" + acc[1] + "]).");
			}
			writer.println();
			accountInfo = db.selectValues("SELECT id, resetacc, resetinf FROM ACCOUNTS_RESET_" + user.id(), 3);
			for (String[] acc : accountInfo) {
				writer.println("resetInfo(" + acc[0] + ", [" + acc[2] + "], " + acc[1] +").");
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
