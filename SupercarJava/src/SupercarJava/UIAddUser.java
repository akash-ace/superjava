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
		
		inNom = new JTextField();
		inNom.setBounds(200, 56, 314, 20);
		uiuser.getContentPane().add(inNom);
		inNom.setColumns(10);
		
		inSurnom = new JTextField();
		inSurnom.setColumns(10);
		inSurnom.setBounds(200, 100, 314, 20);
		uiuser.getContentPane().add(inSurnom);
		
		inLogin = new JTextField();
		inLogin.setColumns(10);
		inLogin.setBounds(200, 139, 314, 20);
		uiuser.getContentPane().add(inLogin);
		
		inPwd = new JTextField();
		inPwd.setColumns(10);
		inPwd.setBounds(200, 190, 314, 20);
		uiuser.getContentPane().add(inPwd);
		
		inRole = new JTextField();
		inRole.setColumns(10);
		inRole.setBounds(200, 227, 314, 20);
		uiuser.getContentPane().add(inRole);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveUser();
			}
		});
		btnAdd.setBounds(93, 273, 89, 23);
		uiuser.getContentPane().add(btnAdd);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(335, 273, 89, 23);
		uiuser.getContentPane().add(btnCancel);
	}
	
	Accounts NewUser = new Accounts();
	public void createUser() {
		String inName = inNom.getText();
		String inSurname = inSurnom.getText();
		String inLgn = inLogin.getText(); 
		String inPass = inPwd.getText();
		String newRole = inRole.getText();
		
		NewUser.addUser(inName, inSurname, inLgn, inPass, newRole);
		
	}
	
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
	
	private void SaveUser() {
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
	
}
