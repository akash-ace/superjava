package SupercarJava;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class UIUserTable {

	private JFrame uitableusers;
	private JTable table;
	private JTextField upNom;
	private JTextField upSurnom;
	private JTextField upLogin;
	private JTextField upPwd;
	private JTextField upRole;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIUserTable window = new UIUserTable();
					window.uitableusers.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UIUserTable() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		uitableusers = new JFrame();
		uitableusers.addWindowListener (new WindowAdapter () {
			@Override
			/**
			 * Appel de la methode ShowData
			 */
			public void windowOpened (WindowEvent arg0) {
				ShowData();
			}
		});
		uitableusers.setBounds(100, 100, 992, 600);
		uitableusers.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		uitableusers.getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("Overview Users");
		lblNewLabel.setBounds(421, 11, 294, 14);
	    uitableusers.getContentPane().add(lblNewLabel);
	    
	    JScrollPane scrollPane = new JScrollPane();
	  		scrollPane.setBounds(43, 60, 892, 244);
	  		uitableusers.getContentPane().add(scrollPane);
	  		
	  		table = new JTable();
	  		table.addMouseListener(new MouseAdapter() {
				@Override
				/**
				 * Appel de la methode selectData avec come parametre la variable ID
				 * @param ID Identifiant de l'entree selectionee
				 */
				public void mouseClicked(MouseEvent e) {
					String ID = table.getValueAt(table.getSelectedRow(),0).toString();
					selectData(ID);
				}
			});
	  		scrollPane.setViewportView(table);
	  		
	  		JLabel lblNom = new JLabel("Nom");
	  		lblNom.setBounds(96, 355, 48, 14);
	  		uitableusers.getContentPane().add(lblNom);
	  		
	  		JLabel lblSurnom = new JLabel("Surnom");
	  		lblSurnom.setBounds(96, 380, 48, 14);
	  		uitableusers.getContentPane().add(lblSurnom);
	  		
	  		JLabel lblLogin = new JLabel("Login");
	  		lblLogin.setBounds(96, 405, 48, 14);
	  		uitableusers.getContentPane().add(lblLogin);
	  		
	  		JLabel lblPasword = new JLabel("Pasword");
	  		lblPasword.setBounds(96, 429, 48, 14);
	  		uitableusers.getContentPane().add(lblPasword);
	  		
	  		JLabel lblRole = new JLabel("Role\r\n");
	  		lblRole.setBounds(96, 454, 48, 14);
	  		uitableusers.getContentPane().add(lblRole);
	  		
	  		upNom = new JTextField();
	  		upNom.setBounds(171, 352, 193, 20);
	  		uitableusers.getContentPane().add(upNom);
	  		upNom.setColumns(10);
	  		
	  		upSurnom = new JTextField();
	  		upSurnom.setColumns(10);
	  		upSurnom.setBounds(171, 377, 193, 20);
	  		uitableusers.getContentPane().add(upSurnom);
	  		
	  		upLogin = new JTextField();
	  		upLogin.setColumns(10);
	  		upLogin.setBounds(171, 402, 193, 20);
	  		uitableusers.getContentPane().add(upLogin);
	  		
	  		upPwd = new JTextField();
	  		upPwd.setColumns(10);
	  		upPwd.setBounds(171, 426, 193, 20);
	  		uitableusers.getContentPane().add(upPwd);
	  		
	  		upRole = new JTextField();
	  		upRole.setColumns(10);
	  		upRole.setBounds(171, 451, 193, 20);
	  		uitableusers.getContentPane().add(upRole);
	  		
	  		JButton btnUpdate = new JButton("Update");
	  		btnUpdate.addActionListener(new ActionListener() {
	  			public void actionPerformed(ActionEvent e) {
	  			SaveUser();
	  			}
	  		});
	  		btnUpdate.setBounds(79, 508, 89, 23);
	  		uitableusers.getContentPane().add(btnUpdate);
	  		
	  		JButton btnDelete = new JButton("Delete");
	  		btnDelete.addActionListener(new ActionListener() {
	  			public void actionPerformed(ActionEvent e) {
	  				deleteData();
	  			}
	  		});
	  		btnDelete.setBounds(219, 508, 89, 23);
	  		uitableusers.getContentPane().add(btnDelete);
	  		
	  		JButton btnCancel = new JButton("Cancel");
	  		btnCancel.setBounds(346, 508, 89, 23);
	  		uitableusers.getContentPane().add(btnCancel);
	  		

			
	  		
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
	private void ShowData () {
		Connection Connect = Conn();
		DefaultTableModel model = new DefaultTableModel();
		Object[] column = {"ID", "Nom ","Surnom", "Login", "Password", "Role"};
      	model.setColumnIdentifiers(column);
    	String showQuery;
        showQuery= "SELECT * FROM `login`;";
       
      	
	try {	
		  
		PreparedStatement s = Connect.prepareStatement(showQuery);
		ResultSet r = s.executeQuery();
		  
		while (r.next()) {
			model.addRow(new Object[] {
			     r.getString("id"),
	            r.getString("Name"),
	            r.getString("Surname"),
	            r.getString("Login"),
	            r.getString("Password"),
	            r.getString("Role"),
	          
			} );
			
	    	table.setModel(model);
	              
	              
		}
		
	table.setBounds(81,250, 561, 400);	
	} catch (Exception e) {
		System.err.println(e);
	}
	}	
	
	private void selectData(String ID) {
		Connection Con = Conn();
		try {
			String SelectQuery = "SELECT * FROM login WHERE id = ?";
			PreparedStatement ps = Con.prepareStatement(SelectQuery);
			ps.setString(1,ID);
			ResultSet r = ps.executeQuery();
			
			while (r.next()) {
				upNom.setText(r.getString("name"));
				upSurnom.setText(r.getString("surname"));
				upLogin.setText(r.getString("login"));
				upPwd.setText(r.getString("password"));
				upRole.setText(r.getString("role"));
				
				
		           
			} 
			
		} catch (Exception e) {
			System.err.println(e);
			
		}  
	}
	
	Accounts UpdatedUser = new Accounts();
	public void updateUser() {
		String upName = upNom.getText();
		String upSurname = upSurnom.getText();
		String upLgn = upLogin.getText(); 
		String upPass = upPwd.getText();
		String moddedRole = upRole.getText();
		
		UpdatedUser.addUser(upName, upSurname, upLgn, upPass, moddedRole);
		
	}
	
	
	private void SaveUser() {
	updateUser();
	
	
	Connection Connect = Conn();
	
	try {	
		String query = "INSERT INTO `login` (`name`, `surname`, `login`, `password`, `role`) VALUES (?, ?, ?, ?, ?); ";
		PreparedStatement in = Connect.prepareStatement(query);
		in.setString(1, UpdatedUser.name);
		in.setString(2, UpdatedUser.surname);
		in.setString(3, UpdatedUser.login);
		in.setString(4, UpdatedUser.getPassword());
		in.setString(5, UpdatedUser.getRole());
		in.execute();
				
	
		
		
		JOptionPane.showMessageDialog(null, "Saved!!");
	
	}catch (Exception e) {
		System.err.println("Error!!" + e);
	}
	}
	
	private void deleteData () {
	  	  Connection Con = Conn();
	  	  try {
	  			String DeleteQuery = "DELETE  FROM login where password = ?";
	  			PreparedStatement ps = Con.prepareStatement(DeleteQuery);

	  			ps.setString(1, UpdatedUser.getPassword());
	  			ps.execute();
	  			
	  			JOptionPane.showMessageDialog(null, "Deleted");
	  			ShowData();
	  			
	  		} catch (Exception e) {
	  			System.err.println(e);
	  			
	  		}  	
	    }
	public void getUserUI() {
		 uitableusers.setVisible(true);
	  }
	
}
