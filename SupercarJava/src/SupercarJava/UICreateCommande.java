package SupercarJava;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class UICreateCommande {

	private JFrame uicommande;
	private JTextField inEntrepot;
	private JTextField inDate;
	private JTextField inModele;
	private JTextField inPays;
	private JTextField inNomEmp;
	private JTextField inQuantite;
	private JTextField inPrix;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UICreateCommande window = new UICreateCommande();
					window.uicommande.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UICreateCommande() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		uicommande = new JFrame();
		uicommande.setBounds(100, 100, 754, 461);
		uicommande.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		uicommande.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Date");
		label.setBounds(64, 81, 54, 14);
		uicommande.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Modele");
		label_1.setBounds(64, 106, 54, 14);
		uicommande.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Pays D'origine");
		label_2.setBounds(64, 131, 107, 14);
		uicommande.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Nom De L'Employe");
		label_3.setBounds(64, 156, 148, 14);
		uicommande.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Quantite");
		label_4.setBounds(64, 181, 54, 14);
		uicommande.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("Prix");
		label_5.setBounds(64, 206, 54, 14);
		uicommande.getContentPane().add(label_5);
		/**
		 * @param inDate Contient la date de la commande
		 */
		inDate = new JTextField();
		inDate.addKeyListener(new KeyAdapter() {
			@Override
			/**
			 * Methode pour assurer que seulement les chiffres peuvent etre entrees
			 */
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();		
				if (Character.isDigit(c) || Character.isISOControl(c)) {
					inDate.setEditable(true);
				} else {
					inDate.setEditable(false);
				}
	   	
			}
		});
		inDate.setBounds(201, 78, 258, 20);
		uicommande.getContentPane().add(inDate);
		inDate.setColumns(10);
		/**
		 * @param inModele Contient le modele a commander
		 */
		inModele = new JTextField();
		inModele.setColumns(10);
		inModele.setBounds(201, 103, 258, 20);
		uicommande.getContentPane().add(inModele);
		/**
		 * @param inPays Contient le pays d'origine du modele a commander 
		 */
		inPays = new JTextField();
		inPays.addKeyListener(new KeyAdapter() {
			@Override
			/**
			 * Methode pour assurer que seulement les chiffres peuvent etre entrees
			 */
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();		
				if (Character.isLetter(c) || Character.isISOControl(c)) {
					inPays.setEditable(true);
				} else {
					inPays.setEditable(false);
				}
	   	
			}
		});
		inPays.setColumns(10);
		inPays.setBounds(201, 128, 258, 20);
		uicommande.getContentPane().add(inPays);
		/**
		 * @param inNomEmp Contient le nom de l'employe qui fait la commande
		 */
		inNomEmp = new JTextField();
		inNomEmp.setColumns(10);
		inNomEmp.setBounds(201, 151, 258, 20);
		uicommande.getContentPane().add(inNomEmp);
		/**
		 * @param inQuantite Contient la quantite de la commande
		 */
		inQuantite = new JTextField();
		inQuantite.addKeyListener(new KeyAdapter() {
			@Override
			/**
			 * Methode pour assurer que seulement les chiffres peuvent etre entrees
			 */
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();		
				if (Character.isDigit(c) || Character.isISOControl(c)) {
					inQuantite.setEditable(true);
				} else {
					inQuantite.setEditable(false);
				}
	   	
			}
		});
		inQuantite.setColumns(10);
		inQuantite.setBounds(201, 178, 258, 20);
		uicommande.getContentPane().add(inQuantite);
		/**
		 * @param inPrix Contient le prix total de la commadne
		 */
		inPrix = new JTextField();
		inPrix.addKeyListener(new KeyAdapter() {
			@Override
			/**
			 * Methode pour assurer que seulement les chiffres peuvent etre entrees
			 */
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();		
				if (Character.isDigit(c) || Character.isISOControl(c)) {
					inPrix.setEditable(true);
				} else {
					inPrix.setEditable(false);
				}
	   	
			}
		});
		inPrix.setColumns(10);
		inPrix.setBounds(201, 203, 258, 20);
		uicommande.getContentPane().add(inPrix);
		/**
		 * Bouton pour sauvegarder la commande
		 */
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			/**
			 * Methode pour appeler la methode de sauvegarde
			 */
			public void actionPerformed(ActionEvent e) {
				SaveCommande();
			}
		});
		btnConfirm.setBounds(82, 272, 89, 23);
		uicommande.getContentPane().add(btnConfirm);
		/**
		 * Bouton afin d'annuler la commande
		 */
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			/**
			 * Methode pour appeler la methode d'annulation
			 */
			public void actionPerformed(ActionEvent e) {
				inDate.setText("");
				inModele.setText("");
				inEntrepot.setText("");
				inNomEmp.setText("");
				inQuantite.setText("");
				inPays.setText("");
				inPrix.setText("");
			}
		});
		btnCancel.setBounds(339, 272, 89, 23);
		uicommande.getContentPane().add(btnCancel);
		
		JLabel label_7 = new JLabel("ID Entrepot");
		label_7.setBounds(64, 56, 95, 14);
		uicommande.getContentPane().add(label_7);
		/**
		 * @param inEntrepot Contient l'identifiant de l'entrepot
		 */
		inEntrepot = new JTextField();
		inEntrepot.addKeyListener(new KeyAdapter() {
			@Override
			/**
			 * Methode pour assurer que seulement les chiffres peuvent etre entrees
			 */
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();		
				if (Character.isDigit(c) || Character.isISOControl(c)) {
					inEntrepot.setEditable(true);
				} else {
					inEntrepot.setEditable(false);
				}
	   	
			}
		});
		inEntrepot.setColumns(10);
		inEntrepot.setBounds(201, 53, 258, 20);
		uicommande.getContentPane().add(inEntrepot);
		
		
	}
	
  

	/**
	 * @param newCommande Object de la classe Commandes
	 */
	Commandes newCommande = new Commandes();
	/**
	 * Methode pour creer une nouvelle commande
	 * @param newCommande Object de la classe Commandes
	 *  @param NewName contient la valeur de inDate
	 * @param NewModele contient la valeur de inModele
	 * @param NewPays contient la valeur de inPays
	 * @param NewNomEmp contient la valeur de inNomEmp
	 * @param NewEntrepot contient la valeur de inEntrepot
	 * @param NewQuantite contient la valeur de inQuantite
	 * @param NewPrix contient la valeur de inPrix
	 */
    public void addCommande() {
    	String NewDate =   inDate.getText();
    	String NewModele = inModele.getText();
    	String NewPays = inPays.getText();
    	String NewNomEmp = inNomEmp.getText();
    	int NewEntrepot = Integer.parseInt(inEntrepot.getText());
    	int NewQuantite = Integer.parseInt(inQuantite.getText());
    	int NewPrix = Integer.parseInt(inPrix.getText());
    	/**
    	 * Appel De la methode createCommande
    	 */
    	newCommande.createCommande(NewEntrepot, NewDate,NewModele, NewPays,NewNomEmp, NewQuantite, NewPrix);
    }
    
    /**
	 * Connexion entre l'application et la base de donnee MySQL
	 * @param Driver Connexion au JDBC Driver
	 * @param url Lien a la base de donnees
	 * @return Connexion reussie
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
	 * @param date Valeur de la date de la commande en format date
	 * @param Connect Connexion a la base de donnees
	 * @param query Attribut pour stocker la requete MySQL afin d'inserer les donnees
	 */
	private void SaveCommande() {
		/**
		 * Appel de la methode addCommande
		 */
		addCommande();
		
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate local = LocalDate.parse(newCommande.getDateCommande(), formatter);
		Date date = Date.valueOf(local);
		Connection Connect = Conn();
	try {	
		String query = "insert into commandes (IDEntrepot, DateCommande	, NomEmp, NomModele, PaysDorigine, Quantite, PrixCommande) values(?,?,?,?,?,?,?) ";
		PreparedStatement in = Connect.prepareStatement(query);
		in.setInt(1, newCommande.getIDEntrepot());
		in.setDate(2, date);
		in.setString(3, newCommande.getNomEmp());
		in.setString(4, newCommande.getNomModele());
		in.setString(5, newCommande.getPaysDorigine());
		in.setInt(6, newCommande.getQuantite());
		in.setInt(7, newCommande.getPrixCommande());
		in.execute();
		determineStock();
		JOptionPane.showMessageDialog(null, "Saved!!");
	
	}catch (Exception e) {
		System.err.println("Error!!" + e);
	}
		}
	/**
	 * @param newStock Object de la classe Commande
	 */
	Stock newStock = new Stock();
/**
 * Methode pour inserer des valeurs dans les attrbuts de newStock
 * @param NomModele Attribut pour stocker la valeur de  newCommande.NomModele appele en utilisant la methode getter de cet attribut
 * @param NomModele Attribut pour stocker la valeur de  newCommande.PrixCommande appele en utilisant la methode getter de cet attribut
 * @param NomModele Attribut pour stocker la valeur de  newCommande.Quantite appele en utilisant la methode getter de cet attribut
 * @param PrixParModele Attribut pour stocker le prix par modele de voiture
 */
	private void StockUpdate() {
		String NomModele = newCommande.getNomModele();
		int TotalPrix = newCommande.getPrixCommande();
		int QuantiteModele = newCommande.getQuantite();
		int PrixParModele = TotalPrix/QuantiteModele;
		/**
		 * Appel de la methode setStock
		 */
		newStock.setStock(NomModele, PrixParModele, newCommande.getPaysDorigine(), newCommande.getQuantite(),newCommande.getIDEntrepot());
		System.out.println(newStock.getNomModele());
	}
	
	/**
	 * Methode afin de determiner le nombre de modeles venant d'un meme pays dans le meme entrepot
	 * @param Connect Connexion a la base de donnees
	 * @param searchQuery Attribut pour stocker la requete MySQL afin de chercher les donnees dans la base
	 */
	private void  determineStock(){
		Connection Connect = Conn();
	try {	
		String searchQuery= "SELECT COUNT(*) FROM stock WHERE IDEntrepot = ? AND NomModele LIKE ? AND PaysDorigine LIKE ?";
		PreparedStatement ps = Connect.prepareStatement(searchQuery);
      	
		ps.setInt(1, newCommande.getIDEntrepot());
		ps.setString(2, newCommande.getNomModele());
		ps.setString(2, newCommande.getNomModele());
		ps.setString(3, newCommande.getPaysDorigine());
		System.out.println(newCommande.getNomModele());
		ResultSet r = ps.executeQuery();
		if (r.next()) {
	     int StockCount = r.getInt("COUNT(*)");
	      System.out.println(StockCount);
	     /**
	     * Si StockCount = 0, alors on appele la methode addData
	  	 */
	     if (StockCount == 0) {
				addData();
		   /**
			* Sinon, appel de la methode updateData
			*/	
			} else if (StockCount ==1) {
				updateData();
			} else {
				System.out.println("Ca ne marche Pas");
			}
		}
		
		
		
			
		
	} catch (Exception e) {
		System.err.println(e);
		
	}  	
	}
	
	/**
	 * Methode afin de determiner le nombre de modeles venant d'un meme pays dans le meme entrepot
	 * @param Connect Connexion a la base de donnees
	 * @param UpdateQuery Attribut pour stocker la requete MySQL afin de mettre a jour la base
	 */
	private void updateData() {
		StockUpdate();
		Connection Connect = Conn();
		
		try {
			String UpdateQuery = "UPDATE stock SET Quantite = Quantite + ? WHERE NomModele LIKE ? AND IDEntrepot = ?";
			PreparedStatement ps = Connect.prepareStatement(UpdateQuery);

			ps.setInt(1, newStock.getQuantite());
			ps.setString(2, newStock.getNomModele());
			ps.setInt(3, newStock.getIDEntrepot());
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
	 * Methode afin de determiner le nombre de modeles venant d'un meme pays dans le meme entrepot
	 * @param Connect Connexion a la base de donnees
	 * @param UAddQuery Attribut pour stocker la requete MySQL afin d'ajouter les donees dans la base
	 */	
	private void addData() {
			StockUpdate();
			Connection Connect = Conn();
			
			try {
				String AddQuery = "insert into stock (NomModele, PrixModele, PaysDorigine, Quantite, IDEntrepot) values (?,?,?,?,?)";
				PreparedStatement ps = Connect.prepareStatement(AddQuery);
				ps.setString(1, newStock.getNomModele());
				ps.setInt(2, newStock.getPrixModele());
				ps.setString(3, newStock.getPaysDorigine());
				ps.setInt(4, newStock.getQuantite());
				ps.setInt(5, newStock.getIDEntrepot());
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
			ps.setInt(1, newStock.getIDEntrepot());
			
			ResultSet r = ps.executeQuery();
			if (r.next()) {
		    StockCount = r.getInt("NbVoitures");
			}
			/**
			 * Appel de la methode updateNbVoitures
			 */
			Lentrepot.updateNbVoitures(newStock.getIDEntrepot(), StockCount);
		} catch (Exception e) {
			System.err.println(e);
			
		} 
		/**
		 * Appel de la methode updateEntrepotStock
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
	}
	/**
	 * Methode getter de ce module
   	 */ 
	public void getCreateCommande() {
		 uicommande.setVisible(true);
	  }
}

