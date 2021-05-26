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
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Entrepot1 {

	private JFrame entrepotpl;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Entrepot1 window = new Entrepot1();
					window.entrepotpl.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Entrepot1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		entrepotpl = new JFrame();
		entrepotpl.addWindowListener (new WindowAdapter () {
			@Override
			/**
			 * Appel de la methode ShowData
			 */
			public void windowOpened (WindowEvent arg0) {
				ShowData();
			}
		});
		entrepotpl.setBounds(100, 100, 754, 461);
		entrepotpl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		entrepotpl.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Overview Entrepot Port Louis");
		lblNewLabel.setBounds(298, 11, 199, 14);
		entrepotpl.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 60, 662, 244);
		entrepotpl.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnFaireUneCommande = new JButton("Faire Une Commande");
		btnFaireUneCommande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entrepotpl.setVisible(false);
				UICreateCommande NewCommande = new UICreateCommande();
				NewCommande.getCreateCommande();
			}
		});
		btnFaireUneCommande.setBounds(121, 330, 199, 23);
		entrepotpl.getContentPane().add(btnFaireUneCommande);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						entrepotpl.setVisible(false);
						UIEntrepot ThisUI = new UIEntrepot();
						ThisUI.getEntrepotUI();
				}
		});
		btnRetour.setBounds(460, 330, 156, 23);
		entrepotpl.getContentPane().add(btnRetour);
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
		Object[] column = {"Modele","Prix Du Modele (TTC)", "Pays D'Origine", "Quantite En Stock" };
      	model.setColumnIdentifiers(column);
    	String showQuery;
        showQuery= "SELECT NomModele, PrixModele, PaysDorigine, Quantite FROM `stock` WHERE IDEntrepot = 1;";
       
      	
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
	public void getEntrepot1() {
		 entrepotpl.setVisible(true);
	  }
}	
