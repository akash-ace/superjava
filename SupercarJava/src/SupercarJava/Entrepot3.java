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

public class Entrepot3 {

	private JFrame phoenixentrepot;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Entrepot3 window = new Entrepot3();
					window.phoenixentrepot.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Entrepot3() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		phoenixentrepot = new JFrame();
		phoenixentrepot.addWindowListener (new WindowAdapter () {
			@Override
			/**
			 * Appel de la methode ShowData
			 */
			public void windowOpened (WindowEvent arg0) {
				ShowData();
			}
		});
		phoenixentrepot.setBounds(100, 100, 754, 461);
		phoenixentrepot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		phoenixentrepot.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Overview Entrepot Phoenix");
		lblNewLabel.setBounds(298, 11, 294, 14);
		phoenixentrepot.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 60, 662, 244);
		phoenixentrepot.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnFaireUneCommande = new JButton("Faire Une Commande");
		btnFaireUneCommande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				phoenixentrepot.setVisible(false);
				UICreateCommande NewCommande = new UICreateCommande();
				NewCommande.getCreateCommande();
			}
		});
		btnFaireUneCommande.setBounds(121, 330, 199, 23);
		phoenixentrepot.getContentPane().add(btnFaireUneCommande);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						phoenixentrepot.setVisible(false);
						UIEntrepot ThisUI = new UIEntrepot();
						ThisUI.getEntrepotUI();
				}
		});
		btnRetour.setBounds(460, 330, 156, 23);
		phoenixentrepot.getContentPane().add(btnRetour);
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
		Object[] column = {"Modele","Prix Du Modele (TTC)", "Pays D'Origine","Quantite En Stock" };
      	model.setColumnIdentifiers(column);
    	String showQuery;
        showQuery= "SELECT NomModele, PrixModele, PaysDorigine, Quantite FROM `stock` WHERE IDEntrepot = 3;";
       
      	
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
	public void getEntrepot3() {
		 phoenixentrepot.setVisible(true);
	  }
}