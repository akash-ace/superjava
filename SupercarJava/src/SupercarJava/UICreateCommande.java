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
		
		inDate = new JTextField();
		inDate.setBounds(201, 78, 258, 20);
		uicommande.getContentPane().add(inDate);
		inDate.setColumns(10);
		
		inModele = new JTextField();
		inModele.setColumns(10);
		inModele.setBounds(201, 103, 258, 20);
		uicommande.getContentPane().add(inModele);
		
		inPays = new JTextField();
		inPays.setColumns(10);
		inPays.setBounds(201, 128, 258, 20);
		uicommande.getContentPane().add(inPays);
		
		inNomEmp = new JTextField();
		inNomEmp.setColumns(10);
		inNomEmp.setBounds(201, 151, 258, 20);
		uicommande.getContentPane().add(inNomEmp);
		
		inQuantite = new JTextField();
		inQuantite.setColumns(10);
		inQuantite.setBounds(201, 178, 258, 20);
		uicommande.getContentPane().add(inQuantite);
		
		inPrix = new JTextField();
		inPrix.setColumns(10);
		inPrix.setBounds(201, 203, 258, 20);
		uicommande.getContentPane().add(inPrix);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveCommande();
			}
		});
		btnConfirm.setBounds(82, 272, 89, 23);
		uicommande.getContentPane().add(btnConfirm);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(339, 272, 89, 23);
		uicommande.getContentPane().add(btnCancel);
		
		JLabel label_7 = new JLabel("ID Entrepot");
		label_7.setBounds(64, 56, 95, 14);
		uicommande.getContentPane().add(label_7);
		
		inEntrepot = new JTextField();
		inEntrepot.setColumns(10);
		inEntrepot.setBounds(201, 53, 258, 20);
		uicommande.getContentPane().add(inEntrepot);
		
		
	}
	
  
	
	Commandes newCommande = new Commandes();
	
    public void addCommande() {
    	String NewDate =   inDate.getText();
    	String NewModele = inModele.getText();
    	String NewPays = inPays.getText();
    	String NewNomEmp = inNomEmp.getText();
    	int NewEntrepot = Integer.parseInt(inEntrepot.getText());
    	int NewQuantite = Integer.parseInt(inQuantite.getText());
    	int NewPrix = Integer.parseInt(inPrix.getText());
    	newCommande.createCommande(NewEntrepot, NewDate,NewModele, NewPays,NewNomEmp, NewQuantite, NewPrix);
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
	
	private void SaveCommande() {
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
	
	Stock newStock = new Stock();
	
	private void StockUpdate() {
		String NomModele = newCommande.getNomModele();
		int TotalPrix = newCommande.getPrixCommande();
		int QuantiteModele = newCommande.getQuantite();
		int PrixParModele = TotalPrix/QuantiteModele;
		newStock.setStock(NomModele, PrixParModele, newCommande.getPaysDorigine(), newCommande.getQuantite(),newCommande.getIDEntrepot());
		System.out.println(newStock.getNomModele());
	}
	
	
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
	     if (StockCount == 0) {
				addData();
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
		   setEntrepot();
		}  	
	
		
	private void addData() {
			StockUpdate();
			Connection Connect = Conn();
			
			try {
				String UpdateQuery = "insert into stock (NomModele, PrixModele, PaysDorigine, Quantite, IDEntrepot) values (?,?,?,?,?)";
				PreparedStatement ps = Connect.prepareStatement(UpdateQuery);

				ps.setString(1, newStock.getNomModele());
				ps.setInt(2, newStock.getPrixModele());
				ps.setString(3, newStock.getPaysDorigine());
				ps.setInt(4, newStock.getQuantite());
				ps.setInt(5, newStock.getIDEntrepot());
				ps.execute();			
				
			} catch (Exception e) {
				System.err.println(e);
				
			}  	
       setEntrepot();
}
	
	Entrepot Lentrepot = new Entrepot();
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
			Lentrepot.updateNbVoitures(newStock.getIDEntrepot(), StockCount);
		} catch (Exception e) {
			System.err.println(e);
			
		} 
		updateEntrepotStock();
	}
	
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
	
	public void getCreateCommande() {
		 uicommande.setVisible(true);
	  }
}

