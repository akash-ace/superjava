package SupercarJava;

import java.math.BigInteger;
import java.security.MessageDigest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Classe account
 */
public class Accounts {
	/**
	 * 
	 * @param PASSWORD_PATTERN Attribut pour le "pattern" du mot de passe (Mot De Passe doit contenir une majuscule, une minuscule, un chiffre et un caractere special)
	 */

	private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{12,40})";
	/**
	 * @param name Attribut pour stocker le prenom de l'utilisateur
	 */
	public String name;
	/**
	 * @param surname Attribut pour stocker le surnom de l'utilisateur
	 */
	public String surname;
	/**
	 * @param login Attribut pour stocker le login de l'utilisateur
	 */
	public String login;
	/**
	 * @param pasword Attribut pour stocker le mot de passe de l'utilisateur
	 */
	private String password;
	/**
	 * @param role Attribut pour stocker le role de cet utilisateur (il peut etre un employe d'un entrepot ou un administrateur)
	 */
	private String role;
	/**
	 * Methode getter pour password
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Methode setter pout le mot de passe
	 */
	public void setPassword(String textPwd) {
		this.password = textPwd;
	}
	/**
	 * Methode getter pour role
	 * @return role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Methode setter pout le mot de passe
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * Methode de verification pour les attributs
	 * @return error = true si un des attributs ne reponds pas aux criteres ci dessous
	 */
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
	/**
	 * Methode de hashage du mot de passe (pour la cybersecurite)
	 * @return chaine (Mot de passe hashee)
	 */
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
	/**
	 * Methode  pour ajouter un utilisateur
	 */
	public void addUser(String name, String surname, String login, String pwd, String Role) {
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = hashPassword(pwd);
		this.role = Role;
		
	}


}



