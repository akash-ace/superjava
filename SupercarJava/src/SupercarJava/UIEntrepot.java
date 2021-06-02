package SupercarJava;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UIEntrepot {

	private JFrame uientrepot;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIEntrepot window = new UIEntrepot();
					window.uientrepot.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UIEntrepot() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		uientrepot = new JFrame();
		uientrepot.addWindowListener (new WindowAdapter () {
			@Override
			/**
			 * Appel de la methode ShowData
			 */
			public void windowOpened (WindowEvent arg0) {
				ShowData();
			}
		});
		uientrepot.setBounds(100, 100, 754, 461);
		uientrepot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		uientrepot.getContentPane().setLayout(null);
		
		JLabel lblEntrepotOverview = new JLabel("Entrepot Overview");
		lblEntrepotOverview.setBounds(320, 11, 128, 14);
		uientrepot.getContentPane().add(lblEntrepotOverview);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 524, 244);
		uientrepot.getContentPane().add(scrollPane);
		/**
		 * Table permettant de visualiser le stock de tous les entrepots
		 */
		table = new JTable();
		scrollPane.setViewportView(table);
		/**
		 * Bouton permettant l'acces aux menu de l'entrepot 1
		 */
		JButton btnEntrepot1 = new JButton("Voir Plus");
		btnEntrepot1.addActionListener(new ActionListener() {
			/**
			 * Methode permettant l'acces au menu de l'entrepot 1
			 */
			public void actionPerformed(ActionEvent e) {
				uientrepot.setVisible(false);
				Entrepot1 ThisEntrepot = new Entrepot1();
				ThisEntrepot.getEntrepot1();
			}
		});
		btnEntrepot1.setBounds(544, 74, 89, 18);
		uientrepot.getContentPane().add(btnEntrepot1);
		/**
		 * Bouton permettant l'acces aux menu de l'entrepot 2
		 */
		JButton btnEntrepot2 = new JButton("Voir Plus");
		btnEntrepot2.addActionListener(new ActionListener() {
			/**
			 * Methode permettant l'acces au menu de l'entrepot 2
			 */
			public void actionPerformed(ActionEvent e) {
				uientrepot.setVisible(false);
				Entrepot2 ThisEntrepot = new Entrepot2();
				ThisEntrepot.getEntrepot2();
			}
		});
		btnEntrepot2.setBounds(544, 90, 89, 18);
		uientrepot.getContentPane().add(btnEntrepot2);
		/**
		 * Bouton permettant l'acces aux menu de l'entrepot 3
		 */
		JButton btnEntrepot3 = new JButton("Voir Plus");
		btnEntrepot3.addActionListener(new ActionListener() {
			/**
			 * Methode permettant l'acces au menu de l'entrepot 3
			 */
			public void actionPerformed(ActionEvent e) {
				uientrepot.setVisible(false);
				Entrepot3 ThisEntrepot = new Entrepot3();
				ThisEntrepot.getEntrepot3();
			}
		});
		btnEntrepot3.setBounds(544, 103, 89, 18);
		uientrepot.getContentPane().add(btnEntrepot3);
		/**
		 * Bouton permettant l'acces aux menu de l'entrepot 4
		 */
		JButton btnEntrepot4 = new JButton("Voir Plus");
		btnEntrepot2.addActionListener(new ActionListener() {
			/**
			 * Methode permettant l'acces au menu de l'entrepot 4
			 */
			public void actionPerformed(ActionEvent e) {
				uientrepot.setVisible(false);
				Entrepot4 ThisEntrepot = new Entrepot4();
				ThisEntrepot.getEntrepot4();
			}
		});
		btnEntrepot4.setBounds(544, 119, 89, 18);
		uientrepot.getContentPane().add(btnEntrepot4);
		/**
		 * Bouton de retour au menu principal
		 */
		JButton btnRetuour = new JButton("Retour");
		btnRetuour.addActionListener(new ActionListener() {
			/**
			 * Methode retour au menu principal
			 */
			public void actionPerformed(ActionEvent e) {
				uientrepot.setVisible(false);
				AdminMenu BackAdmin = new AdminMenu();
				BackAdmin.getMenuAdmin();
			}
		});
		btnRetuour.setBounds(320, 330, 89, 23);
		uientrepot.getContentPane().add(btnRetuour);
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
	 * Methode pour extraire les donnees de la base de donnees et ensuite les mettre dans une table
	 * @param Connect Connexion a la base de donnees
	 * @param showQuery Attribut pour stocker la requete MySQL afin d'extraire les donnees
	 */
	private void ShowData () {
		Connection Connect = Conn();
		DefaultTableModel model = new DefaultTableModel();
		Object[] column = {"Lieu De L'entrepot","Nombre De Voitures"};
      	model.setColumnIdentifiers(column);
    	String showQuery;
        showQuery= "SELECT Adresse, NbVoitures FROM `entrepot`;";
       
      	
	try {	
		model.setRowCount(0);
		model.fireTableDataChanged();   
		PreparedStatement s = Connect.prepareStatement(showQuery);
		ResultSet r = s.executeQuery();
		while (r.next()) {
			model.addRow(new Object[] {
			    r.getString("Adresse"),
	            r.getString("NbVoitures"),
			} );
			
	    	table.setModel(model);
	              
	              
		}
		
	table.setBounds(81,250, 561, 400);	
	} catch (Exception e) {
		System.err.println(e);
	}
	}	
	/**
	 * Methode getter de ce module
   	 */ 
	public void getEntrepotUI() {
		 uientrepot.setVisible(true);
	  }
}
