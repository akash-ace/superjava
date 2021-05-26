package SupercarJava;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame loginframe;
	private JTextField typeLogin;
	private JPasswordField typePassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.loginframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		loginframe = new JFrame();
		loginframe.setBounds(100, 100, 992, 600);
		loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginframe.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(95, 113, 48, 14);
		loginframe.getContentPane().add(lblLogin);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(95, 169, 75, 14);
		loginframe.getContentPane().add(lblPassword);
		
		typeLogin = new JTextField();
		typeLogin.setBounds(238, 110, 203, 20);
		loginframe.getContentPane().add(typeLogin);
		typeLogin.setColumns(10);
		
		typePassword = new JPasswordField();
		typePassword.setBounds(238, 166, 203, 20);
		loginframe.getContentPane().add(typePassword);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkLoginDetails();
			}
		});
		btnEnter.setBounds(301, 263, 89, 23);
		loginframe.getContentPane().add(btnEnter);
	}
	
	Accounts ThisUser = new Accounts();
	String loginText = typeLogin.getText();
	String passText = new String(typePassword.getPassword()); 
	String InputPWD =ThisUser.hashPassword(passText);
	

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
	
	private void checkLoginDetails() {
		Connection Con = Conn();
		try {
			String SelectQuery = "SELECT * FROM login WHERE login = ? and password = ?";
			PreparedStatement ps = Con.prepareStatement(SelectQuery);
			ps.setString(1,loginText);
			ps.setString(2, InputPWD);
			ResultSet r = ps.executeQuery();
			
			while (r.next()) {
			ThisUser.setRole(r.getString("role"));	
				
			if (ThisUser.getRole().equalsIgnoreCase("Admin")) {
				loginframe.setVisible(false);
                AdminMenu NewMenu = new AdminMenu();
                NewMenu.getMenuAdmin();
			}
		           
			} 
			
		} catch (Exception e) {
			System.err.println(e);
			
		}  
	}
	
}