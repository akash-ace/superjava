package SupercarJava;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UIAddUser {

	private JFrame uiuser;
	private JTextField inNom;
	private JTextField inSurnom;
	private JTextField inLogin;
	private JTextField inPwd;
	private JTextField inRole;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIAddUser window = new UIAddUser();
					window.uiuser.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UIAddUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		uiuser = new JFrame();
		uiuser.setBounds(100, 100, 1276, 461);
		uiuser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		uiuser.getContentPane().setLayout(null);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(71, 59, 48, 14);
		uiuser.getContentPane().add(lblNom);
		
		JLabel lblSurnom = new JLabel("Surnom");
		lblSurnom.setBounds(71, 100, 48, 14);
		uiuser.getContentPane().add(lblSurnom);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(71, 142, 48, 14);
		uiuser.getContentPane().add(lblLogin);
		
		JLabel lblMotDePasse = new JLabel("Mot De Passe");
		lblMotDePasse.setBounds(71, 193, 111, 14);
		uiuser.getContentPane().add(lblMotDePasse);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setBounds(71, 230, 48, 14);
		uiuser.getContentPane().add(lblRole);
		
		JLabel lblNotePourAjouter = new JLabel("NOTE: Pour ajouter un nouvel administrateur, le role a saisir est \"Admin\", pour ajouter employer, le role est e\"numero entrepot\" (e.g: e1, pour employe travaillant a l'entrepot 1)");
		lblNotePourAjouter.setBounds(58, 307, 1192, 42);
		uiuser.getContentPane().add(lblNotePourAjouter);
		
		/**
		 * @param inNom Saisie Du Prenom de l'utilisateur
		 */
		inNom = new JTextField();
		inNom.setBounds(200, 56, 314, 20);
		uiuser.getContentPane().add(inNom);
		inNom.setColumns(10);
		
		/**
		 * @param inSurnom Saisie Du Surnom de l'utilisateur
		 */
		inSurnom = new JTextField();
		inSurnom.setColumns(10);
		inSurnom.setBounds(200, 100, 314, 20);
		uiuser.getContentPane().add(inSurnom);
		
		/**
		 * @param inLogin Saisie Du Login de l'utilisateur
		 */
		inLogin = new JTextField();
		inLogin.setColumns(10);
		inLogin.setBounds(200, 139, 314, 20);
		uiuser.getContentPane().add(inLogin);
		
		/**
		 * @param inPwd Saisie du Mot De Passe de l'utilisateur
		 */
		inPwd = new JTextField();
		inPwd.setColumns(10);
		inPwd.setBounds(200, 190, 314, 20);
		uiuser.getContentPane().add(inPwd);
		
		/**
		 * @param inRole Saisie du Role de l'utilisateur
		 */
		inRole = new JTextField();
		inRole.setColumns(10);
		inRole.setBounds(200, 227, 314, 20);
		uiuser.getContentPane().add(inRole);
		
		/**
		 * Bouton pout ajouter le nouvel utilisateur
		 */
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Methode appelant la methode SaveUser pour la sauvegarde des donnees
				 */
				SaveUser();
			}
		});
		btnAdd.setBounds(93, 273, 89, 23);
		uiuser.getContentPane().add(btnAdd);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(335, 273, 89, 23);
		uiuser.getContentPane().add(btnCancel);
	}
	
	/**
	 * @param NewUser Object de la classe Accounts
	 */
	Accounts NewUser = new Accounts();
	/**
	 * Methode afin de creer un nouvel utilisateur
	 */
	public void createUser() {
		/**
		 * @param inName contient la valeur de inNom
		 */
		String inName = inNom.getText();
		/**
		 * @param inSurname contient la valeur de inSurnom
		 */
		String inSurname = inSurnom.getText();
		/**
		 *  @param inLgn contient la valeur de inLogin
		 */
		String inLgn = inLogin.getText(); 
		/**
		 * @param inPass contient la valeur de inPws
		 */
		String inPass = inPwd.getText();
		/**
		 * @param newRole contient la valeur de inRole
		 */
		String newRole = inRole.getText();
		/**
		 * Appel de la methode addUser pour stocker les donnees dans les differents attributs
		 */
		NewUser.addUser(inName, inSurname, inLgn, inPass, newRole);
		
	}
	/**
	 * Connexion entre l'application et la base de donnee MySQL
	 * @param Driver Connexion au JDBC Driver
	 *  @param url Lien a la base de donnees
	 *  @return Connexion reussie
	 */
	static Connection Conn() {
		try {
			String Driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost/supercarjava";
			Class.forName (Driver);
			return DriverManager.getConnection(url, "akash", "2HelVn6HDMAyPBRR");
			} catch(Exception e) {
			System.err.println("Connection Failed!!");
		}
	return null;	
	}
	/**
	 * Methode afin de verifier la validite des donnees entrees
	 * @param Connect Connexion a la base de donnees
	 * @param query Attribut pour stocker la requete MySQL afin d'inserer les donnees dans la base 
	 */
	private void SaveUser() {
	/**
	* Appel de la methode createUser
	*/
	createUser();
	System.out.println(NewUser.getPassword());
	
	Connection Connect = Conn();
	
	try {	
		String query = "INSERT INTO `login` (`name`, `surname`, `login`, `password`, `role`) VALUES (?, ?, ?, ?, ?); ";
		PreparedStatement in = Connect.prepareStatement(query);
		in.setString(1, NewUser.name);
		in.setString(2, NewUser.surname);
		in.setString(3, NewUser.login);
		in.setString(4, NewUser.getPassword());
		in.setString(5, NewUser.getRole());
		in.execute();
				
	
		
		
		JOptionPane.showMessageDialog(null, "Saved!!");
	
	}catch (Exception e) {
		System.err.println("Error!!" + e);
	}
		}
	/**
	 * Methode getter de ce module
   	 */ 
	public void getAddUserUI() {
		 uiuser.setVisible(true);
	  }
}
