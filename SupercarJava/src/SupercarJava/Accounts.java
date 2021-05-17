package SupercarJava;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Accounts {
	private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{12,40})";
	public String name;
	public String surname;
	public String login;
	private String password;
	private String role;
	
	public String getPassword() {
		return password;
	}

	
	public void setPassword(String textPwd) {
		this.password = textPwd;
	}

	public String getRole() {
		return role;
	}

	
	public void setRole(String role) {
		this.role = role;
	}
	
	public boolean verification() {
		boolean error = false;
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher;
		matcher = pattern.matcher(password);

		if (Pattern.matches("[a-z]*", name.substring(1, name.length())) == false
				|| Pattern.matches("[A-Z]", name.substring(0, 1)) == false || name.equalsIgnoreCase("")) {
			
			error = true;
		}
		if (Pattern.matches("[a-z]*", surname.substring(1, surname.length())) == false
				|| Pattern.matches("[A-Z]", surname.substring(0, 1)) == false || surname.equalsIgnoreCase("")) {
		
			error = true;
		}
		if (Pattern.matches("^[a-z0-9-.]+@[a-z0-9]+\\.[a-z0-9-.]+$", login) == false || login.equalsIgnoreCase("")) {
			error = true;
			
		
		}
		if ((matcher.matches()) == false || password.equalsIgnoreCase("")) {
			error = true;
		}
		
		return error;
	}
	
	public String hashPassword(String chaine) {
		try {
			byte[] donneeOctet = chaine.getBytes();
			MessageDigest monHash = MessageDigest.getInstance("SHA");
			monHash.update(donneeOctet);
			byte[] condenser = monHash.digest();
			chaine = new BigInteger(condenser).toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chaine;
	}
	
	public void addUser(String name, String surname, String login, String pwd, String Role) {
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = hashPassword(pwd);
		this.role = Role;
		
	}


}



