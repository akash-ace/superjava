package SupercarJava;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Entrepot4 {

	private JFrame plsentrepot;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Entrepot4 window = new Entrepot4();
					window.plsentrepot.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Entrepot4() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		plsentrepot = new JFrame();
		plsentrepot.addWindowListener (new WindowAdapter () {
			@Override
			/**
			 * Appel de la methode ShowData
			 */
			public void windowOpened (WindowEvent arg0) {
				ShowData();
			}
		});
		plsentrepot.setBounds(100, 100, 754, 461);
		plsentrepot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		plsentrepot.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Overview Entrepot Plaisance");
		lblNewLabel.setBounds(298, 11, 294, 14);
		plsentrepot.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 60, 662, 244);
		plsentrepot.getContentPane().add(scrollPane);
		/**
		 * Table permettant de visualiser le stock de l'entrepot 3
		 */	
		table = new JTable();
		scrollPane.setViewportView(table);
		/**
		 * Bouton permettant l'acces aux formulaire pour ajouter une commande
		 */
		JButton btnFaireUneCommande = new JButton("Faire Une Commande");
		btnFaireUneCommande.addActionListener(new ActionListener() {
			/**
			 * Methode permettant l'acces aux formulaire pour ajouter une commande
			 */
			public void actionPerformed(ActionEvent e) {
				plsentrepot.setVisible(false);
				UICreateCommande NewCommande = new UICreateCommande();
				NewCommande.getCreateCommande();
			}
		});
		btnFaireUneCommande.setBounds(121, 330, 199, 23);
		plsentrepot.getContentPane().add(btnFaireUneCommande);
		
		/**
		 * Bouton de retour au menu principal entrepots
		 */
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			/**
			 * Bouton de retour au menu principal entrepots
			 */
			public void actionPerformed(ActionEvent e) {
						plsentrepot.setVisible(false);
						UIEntrepot ThisUI = new UIEntrepot();
						ThisUI.getEntrepotUI();
				}
		});
		btnRetour.setBounds(460, 330, 156, 23);
		plsentrepot.getContentPane().add(btnRetour);
		
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
		Object[] column = {"Modele","Prix Du Modele (TTC)", "Pays D'Origine","Quantite En Stock" };
      	model.setColumnIdentifiers(column);
    	String showQuery;
        showQuery= "SELECT NomModele, PrixModele, PaysDorigine, Quantite FROM `stock` WHERE IDEntrepot = 4;";
       
      	
	try {	
		model.setRowCount(0);
		model.fireTableDataChanged();   
		PreparedStatement s = Connect.prepareStatement(showQuery);
		ResultSet r = s.executeQuery();
		while (r.next()) {
			model.addRow(new Object[] {
			    r.getString("NomModele"),
	            r.getString("PrixModele"),
	            r.getString("PaysDorigine"),
	            r.getString("Quantite"),
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
	public void getEntrepot4() {
		 plsentrepot.setVisible(true);
	  }
}
