package SupercarJava;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class UICommandes {

	private JFrame uicommandes;
	private JTable table;
	private JTextField upID;
	private JTextField upDate;
	private JTextField upModele;
	private JTextField upNomEmp;
	private JTextField upPays;
	private JTextField upQuantite;
	private JTextField upPrix;
	private int upIDCommande;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UICommandes window = new UICommandes();
					window.uicommandes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UICommandes() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		uicommandes = new JFrame();
		uicommandes.addWindowListener (new WindowAdapter () {
			@Override
			/**
			 * Appel de la methode ShowData
			 */
			public void windowOpened (WindowEvent arg0) {
				ShowData();
			}
		});
		uicommandes.setBounds(100, 100, 992, 600);
		uicommandes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		uicommandes.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Overview Commandes");
		lblNewLabel.setBounds(421, 11, 294, 14);
	    uicommandes.getContentPane().add(lblNewLabel);
	    
	    JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 60, 892, 244);
		uicommandes.getContentPane().add(scrollPane);
		/**
		 * Table permettant de visualiser toutes les commandes faites
		 */
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
		
		JLabel lblNewLabel_1 = new JLabel("ID Entrepot");
		lblNewLabel_1.setBounds(31, 334, 106, 14);
		uicommandes.getContentPane().add(lblNewLabel_1);
		
		JLabel lblDateCommande = new JLabel("Date Commande");
		lblDateCommande.setBounds(31, 357, 106, 14);
		uicommandes.getContentPane().add(lblDateCommande);
		
		JLabel lblModele = new JLabel("Modele");
		lblModele.setBounds(31, 404, 106, 14);
		uicommandes.getContentPane().add(lblModele);
		
		JLabel lblPaysDorigine = new JLabel("Pays D'Origine");
		lblPaysDorigine.setBounds(31, 426, 106, 14);
		uicommandes.getContentPane().add(lblPaysDorigine);
		
		JLabel lblQuantite = new JLabel("Quantite");
		lblQuantite.setBounds(31, 450, 106, 14);
		uicommandes.getContentPane().add(lblQuantite);
		
		JLabel lblPrix = new JLabel("Prix");
		lblPrix.setBounds(31, 475, 106, 14);
		uicommandes.getContentPane().add(lblPrix);
		/**
		 * @param upID Contient l'identifiant de l'entrepot
		 */
		upID = new JTextField();
		upID.setBounds(147, 331, 190, 20);
		uicommandes.getContentPane().add(upID);
		upID.setColumns(10);
		/**
		 * @param upDate Contient la date de la commande
		 */
		upDate = new JTextField();
		upDate.setColumns(10);
		upDate.setBounds(147, 354, 190, 20);
		uicommandes.getContentPane().add(upDate);
		/**
		 * @param upModele Contient le modele commande
		 */
		upModele = new JTextField();
		upModele.setColumns(10);
		upModele.setBounds(147, 401, 190, 20);
		uicommandes.getContentPane().add(upModele);
		/**
		 * @param upPays Contient le pays d'origine de la commande 
		 */
		upPays = new JTextField();
		upPays.setColumns(10);
		upPays.setBounds(147, 423, 190, 20);
		uicommandes.getContentPane().add(upPays);
		/**
		 * @param upQuantite Contient la quantite de modele commandee
		 */
		upQuantite = new JTextField();
		upQuantite.setColumns(10);
		upQuantite.setBounds(147, 447, 190, 20);
		uicommandes.getContentPane().add(upQuantite);
		/**
		 * @param inPwd Contient le prix de la comande
		 */
		upPrix = new JTextField();
		upPrix.setColumns(10);
		upPrix.setBounds(147, 472, 190, 20);
		uicommandes.getContentPane().add(upPrix);
		/**
		 * Bouton pour enclancher la mise a jour
		 */
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			/**
			 * Methode appelant la methode updateCommande pour la mise a jour
			 */
			public void actionPerformed(ActionEvent e) {
				UpdateCommande();
			}
		});
		btnUpdate.setBounds(31, 515, 89, 23);
		uicommandes.getContentPane().add(btnUpdate);
		/**
		 * Bouton pour supprimer la commande
		 */
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			/**
			 * Methode appelant la methode deleteData pour la suppression de la commande
			 */
			public void actionPerformed(ActionEvent e) {
				
				deleteData();
			}
		});
		btnDelete.setBounds(275, 515, 89, 23);
		uicommandes.getContentPane().add(btnDelete);
		
		JLabel lblNomEmploye = new JLabel("Nom Employe");
		lblNomEmploye.setBounds(31, 382, 106, 14);
		uicommandes.getContentPane().add(lblNomEmploye);
		/**
		 * @param upNomEmp Contient le nom de l'employe qui a formule la commande
		 */	
		upNomEmp = new JTextField();
		upNomEmp.setColumns(10);
		upNomEmp.setBounds(147, 379, 190, 20);
		uicommandes.getContentPane().add(upNomEmp);
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
		Object[] column = {"ID Commande", "ID Entrepot","Date De Commande","Nom De L'employe","Modele", "Pays D'Origine", "Quantite", "Prix De Commande"};
      	model.setColumnIdentifiers(column);
    	String showQuery;
        showQuery= "SELECT * FROM `commandes`;";
       
       
      	
	try {	
		  
		PreparedStatement s = Connect.prepareStatement(showQuery);
		ResultSet r = s.executeQuery();
		  
		while (r.next()) {
			model.addRow(new Object[] {
			     r.getString("IDCommande"),
	            r.getString("IDEntrepot"),
	            r.getString("DateCommande"),
	            r.getString("NomEmp"),
	            r.getString("NomModele"),
	            r.getString("PaysDorigine"),
	             r.getString("Quantite"),
	            r.getString("PrixCommande"),
	            /**
	        	 * @param upIDCommande contient l'identifiant de la commande
	        	 */
	            upIDCommande = r.getInt("IDCommande"),
			} );
			
	    	table.setModel(model);
	              
	              
		}
		
	table.setBounds(81,250, 561, 400);	
	} catch (Exception e) {
		System.err.println(e);
	}
	}	
	
	/**
	 * Methode pour inserer une ligne de donnees dans le formulaire
	 * @param Con Connexion a la base de donnees
	 * @param showQuery Attribut pour stocker la requete MySQL afin d'extraire les donnees
	 */
	private void selectData(String ID) {
		Connection Con = Conn();
		try {
			String SelectQuery = "SELECT * FROM commandes WHERE IDCommande = ?";
			PreparedStatement ps = Con.prepareStatement(SelectQuery);
			ps.setString(1,ID);
			ResultSet r = ps.executeQuery();
			
			while (r.next()) {
				upID.setText(r.getString("IDEntrepot"));
				upDate.setText(r.getString("DateCommande"));
				upNomEmp.setText(r.getString("NomEmp"));
				upModele.setText(r.getString("NomModele"));
				upPays.setText(r.getString("PaysDorigine"));
				upQuantite.setText(r.getString("Quantite"));
				upPrix.setText(r.getString("PrixCommande"));
				
		           
			} 
			
		} catch (Exception e) {
			System.err.println(e);
			
		}  
	}
	
	/**
	 * @param updatedCommande Object de la classe Commandes
	 */
Commandes updatedCommande = new Commandes();

/**
 * Methode afin de mettre a jour une commande
 * @param NewDate contient la valeur de UpDate
 * @param NewModele contient la valeur de UpModele
 * @param NewPays contient la valeur de UpPays 
 * @param NewNomEmp contient la valeur de UpNomEmp
 * @param NewID contient la valeur de upIDCommande
 * @param NewEntrepot contient la valeur de upID
 * @param NewQuantite contient la valeur de upQuantite
 * @param NewPrix contient la valeur de upPrix
 * 
 */
    public void addCommande() {
    	String NewDate =   upDate.getText();
    	String NewModele = upModele.getText();
    	String NewPays = upPays.getText();
    	String NewNomEmp = upNomEmp.getText();
    	int NewID = upIDCommande;
    	int NewEntrepot = Integer.parseInt(upID.getText());
    	int NewQuantite = Integer.parseInt(upQuantite.getText());
    	int NewPrix = Integer.parseInt(upPrix.getText());
    	/**
    	 * Appel de la methode updateCommande
    	 */
    	updatedCommande.updateCommande(NewID, NewEntrepot, NewDate,NewModele, NewPays,NewNomEmp, NewQuantite, NewPrix);
    }
	
    
    /**
	 * Methode pour inserer une ligne de donnees dans le formulaire
	 * @param Connect Connexion a la base de donnees
	 * @param query Attribut pour stocker la requete MySQL afin de mettre a jour les donnees
	 */
    private void UpdateCommande() {
    	/**
    	 * Appel de la methode addCommande
    	 */
		addCommande();
		
		
		
		Connection Connect = Conn();
	try {	
		String query = "UPDATE commandes SET IDEntrepot = ?, DateCommande = ?, NomModele = ?, PaysDorigine = ?, NomEmp = ? , Quantite = ? , PrixCommande = ? WHERE IDCommande = ?";
		PreparedStatement in = Connect.prepareStatement(query);
		in.setInt(1, updatedCommande.getIDEntrepot());
		in.setString(2, updatedCommande.getDateCommande());
		in.setString(3, updatedCommande.getNomModele());
		in.setString(4, updatedCommande.getPaysDorigine());
		in.setString(5, updatedCommande.getNomEmp());
		in.setInt(6,  updatedCommande.getQuantite());
		in.setInt(7, updatedCommande.getPrixCommande());
		in.setInt(8, updatedCommande.getIDCommande());
		in.execute();
		determineStock();
		JOptionPane.showMessageDialog(null, "Updated");
	
	}catch (Exception e) {
		System.err.println("Error!!" + e);
	}
		}
    /**
     * @param updatedStock Object de la classe Stock
	 */  
Stock updatedStock = new Stock();
/**
 * Methode pour mettre a jour le stock de l'entrepot
 * @param NomModele Attribut pour stocker la valeur de  updatedCommande.NomModele appele en utilisant la methode getter de cet attribut
 * @param NomModele Attribut pour stocker la valeur de  updatedCommande.PrixCommande appele en utilisant la methode getter de cet attribut
 * @param NomModele Attribut pour stocker la valeur de  updatedCommande.Quantite appele en utilisant la methode getter de cet attribut
 * @param PrixParModele Attribut pour stocker le prix par modele de voiture
 */
	private void StockUpdate() {
		String NomModele = updatedCommande.getNomModele();
		int TotalPrix = updatedCommande.getPrixCommande();
		int QuantiteModele = updatedCommande.getQuantite();
		int PrixParModele = TotalPrix/QuantiteModele;
		/**
		 * Appel De La Methode setStock
		 */
		updatedStock.setStock(NomModele, PrixParModele, updatedCommande.getPaysDorigine(), updatedCommande.getQuantite(),updatedCommande.getIDEntrepot());
		System.out.println(updatedStock.getNomModele());
	}
	/**
	 * Methode afin de determiner si le nombre de modeles restants apres suppression = 0
	 * @param Connect Connexion a la base de donnees
	 * @param searchQuery Attribut pour stocker la requete MySQL afin de chercher les donnees dans la base
	 */
	 private void  checkIfZero(){
			Connection Connect = Conn();
		try {	
			String searchQuery= "SELECT Quantite FROM stock WHERE IDEntrepot = ? AND NomModele LIKE ? AND PaysDorigine LIKE ?";
			PreparedStatement ps = Connect.prepareStatement(searchQuery);
	      	
			ps.setInt(1, updatedCommande.getIDEntrepot());
			ps.setString(2, updatedCommande.getNomModele());

			ps.setString(3, updatedCommande.getPaysDorigine());
			ResultSet r = ps.executeQuery();
			if (r.next()) {
			int StockCount = r.getInt("Quantite") + updatedCommande.getQuantite();
			if (StockCount == 0) {
				/**
			     * Si StockCount = 0, alors on appele la methode deleteData
			  	 */
				deleteData();
			} 
			}
		} catch (Exception e) {
			System.err.println(e);
			
		}  
		}
	 /**
	 * Methode pour determiner le nombre de modeles venant du meme pays dans le meme entrepot
     * @param Connect Connexion a la base de donnees
	 * @param searchQuery Attribut pour stocker la requete MySQL afin de chercher les donnees
	 */ 
    private void  determineStock(){
		Connection Connect = Conn();
	try {	
		String searchQuery= "SELECT COUNT(*) FROM stock WHERE IDEntrepot = ? AND NomModele LIKE ? AND PaysDorigine LIKE ?";
		PreparedStatement ps = Connect.prepareStatement(searchQuery);
      	
		ps.setInt(1, updatedCommande.getIDEntrepot());
		ps.setString(2, updatedCommande.getNomModele());
		ps.setString(2, updatedCommande.getNomModele());
		ps.setString(3, updatedCommande.getPaysDorigine());
		ResultSet r = ps.executeQuery();
		if (r.next()) {
	    /**
		 * Appel de la methode updateData
		  */
	     updateData();
			} else {
				System.out.println("Ca ne marche Pas");
			}	
		
	} catch (Exception e) {
		System.err.println(e);
		
	}  	
	}
    
    /**
   	 * Methode pour mettre a jour les donnees
     * @param Connect Connexion a la base de donnees
   	 * @param UpdateQuery Attribut pour stocker la requete MySQL afin de mettre a jour le stock
   	 */ 
    private void updateData() {
		StockUpdate();
		Connection Connect = Conn();
		
		try {
			String UpdateQuery = "UPDATE stock SET Quantite = Quantite + ?, PrixModele = ? WHERE NomModele LIKE ? AND PaysDorigine LIKE ? AND IDEntrepot = ?";
			PreparedStatement ps = Connect.prepareStatement(UpdateQuery);
			ps.setInt(1,updatedStock.getQuantite());
			ps.setInt(2, updatedStock.getPrixModele());
			ps.setString(3, updatedStock.getNomModele());
			ps.setString(4, updatedStock.getPaysDorigine());
			ps.setInt(5, updatedStock.getIDEntrepot());
			ps.execute();			
			
		} catch (Exception e) {
			System.err.println(e);
		}	
		   checkIfZero();
		   setEntrepot();
		  
		}  	
    
   
    /**
   	 * Methode pour supprimmer les donees 
     * @param Con Connexion a la base de donnees
   	 * @param DeleteQuery Attribut pour stocker la requete MySQL afin de supprimer les donnees
   	 */ 
    private void deleteData () {
  	  Connection Con = Conn();
  	  try {
  			String DeleteQuery = "DELETE  FROM commandes WHERE IDCommande = ?";
  			PreparedStatement ps = Con.prepareStatement(DeleteQuery);

  			ps.setInt(1, updatedCommande.getIDCommande());
  			ps.execute();
  			
  			JOptionPane.showMessageDialog(null, "Deleted");
  			ShowData();
  			
  		} catch (Exception e) {
  			System.err.println(e);
  			
  		}  
  	/**
  	 * Appel de la methode updateDataAfterDelete
     */ 
  	  updateDataAfterDelete();
    }
    /**
   	 * Methode pour inserer une ligne de donnees dans le formulaire
     * @param Connect Connexion a la base de donnees
   	 * @param UpdateQuery Attribut pour stocker la requete MySQL afin de mettre a jour les donnees dans la base de donnees stock
   	 */ 
    private void updateDataAfterDelete() {
		Connection Connect = Conn();
		
		try {
			String UpdateQuery = "UPDATE stock SET Quantite = Quantite - ? WHERE NomModele LIKE ?, PaysDorigine LIKE ? AND IDEntrepot = ?";
			PreparedStatement ps = Connect.prepareStatement(UpdateQuery);
			ps.setInt(1,updatedCommande.getQuantite());
			ps.setString(2, updatedCommande.getNomModele());
			ps.setString(3, updatedCommande.getPaysDorigine());
			ps.setInt(4, updatedCommande.getIDEntrepot());
			ps.execute();			
			
		} catch (Exception e) {
			System.err.println(e);
		}	
		/**
		 * Appel de la methode setEntrepot
		 */ 
		   setEntrepot();
		  
		}  	
    /**
     * @param Lentrepot Object de la classe Entrepot
   	 */ 
	Entrepot Lentrepot = new Entrepot();
	/**
   	 * Methode pour mettre a jour le nombre de modeles dans un entrepot
     * @param Connect Connexion a la base de donnees
   	 * @param getCount Attribut pour stocker la requete MySQL afin de recevoir la quantite totale du stock 
   	 * @param StockCount Attribut pour stocker le nombre de voitures dans l'entrepot
   	 */ 
	private void setEntrepot() {
		Connection Connect = Conn();
		try {
			String getCount = "select SUM(Quantite) As NbVoitures from stock where IDEntrepot = ? ";
			int StockCount = 0;
			PreparedStatement ps = Connect.prepareStatement(getCount);
			ps.setInt(1, updatedCommande.getIDEntrepot());
			
			ResultSet r = ps.executeQuery();
			if (r.next()) {
		    StockCount = r.getInt("NbVoitures");
			}
			/**
			 * Appel de la methode updateNbVoitures
			 */ 
			Lentrepot.updateNbVoitures(updatedCommande.getIDEntrepot(), StockCount);
		} catch (Exception e) {
			System.err.println(e);
			
		} 
		/**
		 *Appel de la methode updateEntrepotStock
	   	 */ 
		updateEntrepotStock();
	}
	/**
   	 * Methode pour mettre a jour la table entrepot
     * @param Connect Connexion a la base de donnees
   	 * @param UpdateQuery Attribut pour stocker la requete MySQL afin de mettre a jour les donnees dans la table entrepot
   	 */ 
	private void updateEntrepotStock() {
		
		Connection Connect = Conn();
		try {	
			String searchQuery= "UPDATE entrepot SET NbVoitures = ? WHERE IDEntrepot = ?";
			PreparedStatement ps = Connect.prepareStatement(searchQuery);
	      	ps.setInt(1, Lentrepot.getNbVoitures());
			ps.setInt(2, Lentrepot.getIDEntrepot());
			ps.execute();
	} catch (Exception e) {
		System.err.println(e);
		
	}
		/**
		 * Appel de la methode ShowData
	   	 */ 
		 ShowData();
	}
	/**
	 * Methode getter de ce module
   	 */ 
	public void getCommandeUI() {
		 uicommandes.setVisible(true);
	  }
}
