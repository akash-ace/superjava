//PPE 3: Supercar Java Version 6.0
//Created By: Aakash Chady
//Date Created:22/03/2021
//Date Modified (Version 6.0): 03/05/2021



package SuperJava;
import java.sql.*;
import java.awt.EventQueue;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;




public class Home {

	private JFrame Ventes;
	private JTextField textFieldID;
	private JTextField textFieldDate;
	private JTextField textFieldPrix;
	private JTextField textFieldNomClient;
	private JTextField textFieldNomEmp;
	private JTextField textFieldModele;
	private JTextField textFieldStatus;
	private JTable table;
	private JScrollPane scrollPane;
	JComboBox comboBox;
	JComboBox comboBox_1 = new JComboBox();
	int filterIndex;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.Ventes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Ventes = new JFrame();
		Ventes.addWindowListener (new WindowAdapter () {
			@Override
			/**
			 * Appel de la methode ShowData
			 */
			public void windowOpened (WindowEvent arg0) {
				ShowData();
			}
		});
		Ventes.setBounds(100, 100, 900, 700);
		Ventes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Ventes.getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblId.setBounds(10, 41, 48, 14);
		Ventes.getContentPane().add(lblId);
		
		JLabel lblDateVente = new JLabel("Date Vente");
		lblDateVente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDateVente.setBounds(10, 66, 100, 14);
		Ventes.getContentPane().add(lblDateVente);
		
		JLabel lblPrixVentettc = new JLabel("Prix Vente (TTC)");
		lblPrixVentettc.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrixVentettc.setBounds(10, 91, 100, 14);
		Ventes.getContentPane().add(lblPrixVentettc);
		
		JLabel lblNomClient = new JLabel("Nom Client");
		lblNomClient.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNomClient.setBounds(10, 116, 100, 14);
		Ventes.getContentPane().add(lblNomClient);
		
		JLabel lblNomEmploye = new JLabel("Nom Employe");
		lblNomEmploye.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNomEmploye.setBounds(10, 141, 100, 14);
		Ventes.getContentPane().add(lblNomEmploye);
		
		JLabel lblModele = new JLabel("Modele");
		lblModele.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblModele.setBounds(10, 166, 100, 14);
		Ventes.getContentPane().add(lblModele);
		
		JLabel lblStatut = new JLabel("Statut");
		lblStatut.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatut.setBounds(10, 191, 100, 14);
		Ventes.getContentPane().add(lblStatut);
		
		textFieldID = new JTextField();
		textFieldID.addKeyListener(new KeyAdapter() {
			@Override
			/**
			 * Methode pour assurer que seulement les chiffres peuvent etre entrees
			 */
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();		
				if (Character.isDigit(c) || Character.isISOControl(c)) {
					textFieldID.setEditable(true);
				} else {
					textFieldID.setEditable(false);
				}
	   	
			}
		});
		textFieldID.setBounds(120, 38, 465, 20);
		Ventes.getContentPane().add(textFieldID);
		textFieldID.setColumns(10);
		
		textFieldDate = new JTextField();
		textFieldDate.setColumns(10);
		textFieldDate.setBounds(120, 63, 465, 20);
		Ventes.getContentPane().add(textFieldDate);
		
		textFieldPrix = new JTextField();
		textFieldPrix.addKeyListener(new KeyAdapter() {
			@Override
			/**
			 * Methode pour assurer que seulement les chiffres peuvent etre entrees
			 */
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();		
				if (Character.isDigit(c) || Character.isISOControl(c)) {
					textFieldPrix.setEditable(true);
				} else {
					textFieldPrix.setEditable(false);
				}
	   	
			}
		});
		textFieldPrix.setColumns(10);
		textFieldPrix.setBounds(120, 88, 465, 20);
		Ventes.getContentPane().add(textFieldPrix);
		
		textFieldNomClient = new JTextField();
		textFieldNomClient.addKeyListener(new KeyAdapter() {
			@Override
			/**
			 * Methode pour assurer que seulement les lettres peuvent etre entrees
			 */
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();		
				if (Character.isLetter(c) || Character.isWhitespace(c) || Character.isISOControl(c)) {
					textFieldNomClient.setEditable(true);
				} else {
					textFieldNomClient.setEditable(false);
				}
	   	
			}
		});
		textFieldNomClient.setColumns(10);
		textFieldNomClient.setBounds(120, 113, 465, 20);
		Ventes.getContentPane().add(textFieldNomClient);
		
		textFieldNomEmp = new JTextField();
		textFieldNomEmp.addKeyListener(new KeyAdapter() {
			@Override
			/**
			 * Methode pour assurer que seulement les lettres peuvent etre entrees
			 */
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();		
				if (Character.isLetter(c) || Character.isWhitespace(c) || Character.isISOControl(c)) {
					textFieldNomEmp.setEditable(true);
				} else {
					textFieldNomEmp.setEditable(false);
				}
	   	
			}
		});
		textFieldNomEmp.setColumns(10);
		textFieldNomEmp.setBounds(120, 138, 465, 20);
		Ventes.getContentPane().add(textFieldNomEmp);
		
		textFieldModele = new JTextField();
		textFieldModele.setColumns(10);
		textFieldModele.setBounds(120, 163, 465, 20);
		Ventes.getContentPane().add(textFieldModele);
		
		textFieldStatus = new JTextField();
		textFieldStatus.setColumns(10);
		textFieldStatus.setBounds(120, 188, 465, 20);
		Ventes.getContentPane().add(textFieldStatus);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			/**
			 * Appel de la methode SaveToDB
			 */
			public void actionPerformed(ActionEvent e) {
				SaveToDB();
			}
		});
		btnSave.setBounds(130, 219, 89, 23);
		Ventes.getContentPane().add(btnSave);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10,253, 860, 200);
		Ventes.getContentPane().add(scrollPane);
		table = new JTable();
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/**
				 * Appel de la methode selectData avec come parametre la variable ID
				 * @param ID Identifiant de l'entree selectionee
				 */
				String ID = table.getValueAt(table.getSelectedRow(),0).toString();
				selectData(ID);
			}
		});
		
	scrollPane.setViewportView(table);
	
	JButton buttonUpdate = new JButton("Update");
	buttonUpdate.addActionListener(new ActionListener() {
		/**
		 * Appel de la methode updateData
		 */
		public void actionPerformed(ActionEvent e) {
			if (table.getSelectedRow()>= 0) {
			updateData(textFieldID.getText());
			}
		}
	});
	buttonUpdate.setBounds(285, 219, 89, 23);
	Ventes.getContentPane().add(buttonUpdate);
	
	JButton buttonDelete = new JButton("Delete");
	buttonDelete.addActionListener(new ActionListener() {
		/**
		 * Appel de la methode deleteData
		 */
		public void actionPerformed(ActionEvent e) {
			if (table.getSelectedRow()>= 0) {
				deleteData(textFieldID.getText());
				}
			
		}
	});
	buttonDelete.setBounds(467, 219, 89, 23);
	Ventes.getContentPane().add(buttonDelete);
	
	JLabel lblFilter = new JLabel("FILTER BY");
	lblFilter.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblFilter.setBounds(741, 41, 111, 14);
	Ventes.getContentPane().add(lblFilter);
	

	comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"None", "Day", "Week", "Month"}));
	comboBox_1.setSelectedIndex(0);
	comboBox_1.setMaximumRowCount(4);
	ActionListener actionListener = new ActionListener() {
		/**
		 * Appel de la methode ShowData selon les criteres de triage 
		 */
	      public void actionPerformed(ActionEvent actionEvent) {
	        ShowData();
	      }
	    };
	    comboBox_1.addActionListener(actionListener);
	comboBox_1.setBounds(741, 62, 82, 22);
	Ventes.getContentPane().add(comboBox_1);
	
	JButton btnExit = new JButton("Exit");
	btnExit.addActionListener(new ActionListener() {
		/**
		 * Methode de sortie du module (Retour a la page acceuil)
		 */
		public void actionPerformed(ActionEvent e) {
		    Ventes.setVisible(false);
            Acceuil BackAcceuil = new Acceuil();
            BackAcceuil.getAcceuil();
		}
	});
	btnExit.setBounds(388, 502, 89, 23);
	Ventes.getContentPane().add(btnExit);
 
		
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
			String url = "jdbc:mysql://localhost/superjava";
			Class.forName (Driver);
			return DriverManager.getConnection(url, "akash", "2HelVn6HDMAyPBRR");
			} catch(Exception e) {
			System.err.println("Connection Failed!!");
		}
	return null;	
	}
	
	
	/**
	 * Methode pour sauvegarder les entrees dans la base de donnees
	 * @param text la date entree dans le formlaire
	 * @param date La date de vente
	 * @param Connect Connexion a la base de donnees
	 * @param query La requete MySQL afin de sauvegarder les donnees
	 */
	private void SaveToDB () {
		String text = textFieldDate.getText();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate local = LocalDate.parse(text, formatter);
		Date date = Date.valueOf(local);

	
		Connection Connect = Conn();
	try {	
		String query = "insert into ventes (IDVente, DateVente, PrixVente, NomClient, NomEmp, Modele, Status) values(?,?,?,?,?,?,?) ";
		PreparedStatement in = Connect.prepareStatement(query);
		in.setString(1, textFieldID.getText());
		in.setDate(2, date);
		in.setString(3, textFieldPrix.getText());
		in.setString(4, textFieldNomClient.getText());
		in.setString(5, textFieldNomEmp.getText());
		in.setString(6, textFieldModele.getText());
		in.setString(7, textFieldStatus.getText());
		in.execute();
		JOptionPane.showMessageDialog(null, "Saved!!");
		ShowData();

	}catch (Exception e) {
		System.err.println("Error!!" + e);
	}
		}
	
	/**
	 * Methode pour extraire les donnees de la base de donnees et ensuite les mettre dans une table
	 * @param Connect Connexion a la base de donnees
	 * @param showQuery La requete MySQL afin d'extraire les donnees. Ceci est un tableau de chaine de caracteres afin de stocker plusieurs requetes MySQL
	 */
	private void ShowData () {
		Connection Connect = Conn();
		DefaultTableModel model = new DefaultTableModel();
		Object[] column = {"ID","Date De Vente","Prix De Vente (TTC)","Nom Du Client", "Nom De L'employe", "Modele", "Statut"};
      	model.setColumnIdentifiers(column);
    	String[] showQuery = new String[4];
        showQuery[0] = "SELECT * FROM `ventes`;";
        showQuery[1] = "SELECT * FROM `ventes` WHERE DateVente = CURDATE();";
        showQuery[2] = "SELECT * FROM `ventes` WHERE YEARWEEK(DateVente) = YEARWEEK(CURDATE());";
        showQuery[3]= "SELECT * FROM `ventes` WHERE MONTH(DateVente) = MONTH(CURDATE()) AND YEAR(DateVente) = YEAR(CURDATE());";
       
      	
	try {	
		model.setRowCount(0);
		model.fireTableDataChanged();   
		PreparedStatement s = Connect.prepareStatement(showQuery[comboBox_1.getSelectedIndex()]);
		ResultSet r = s.executeQuery();
		while (r.next()) {
			model.addRow(new Object[] {
			     r.getString("IDVente"),
	            r.getString("DateVente"),
	            r.getString("PrixVente"),
	            r.getString("NomClient"),
	             r.getString("NomEmp"),
	            r.getString("Modele"),
	            r.getString("Status"),
			} );
			
	    	table.setModel(model);
	              
	              
		}
		
	table.setBounds(81,250, 561, 400);	
	} catch (Exception e) {
		System.err.println(e);
	}
	}	
	
	/**
	 * Methode pour enyoyer les donnees dans une ligne de la table sur le formulaire
	 * @param Connect Connexion a la base de donnees
	 * @param showQuery La requete MySQL afin de selectionner les donnees
	 */
	private void selectData(String ID) {
		Connection Con = Conn();
		try {
			String SelectQuery = "SELECT * FROM ventes WHERE IDVente = ?";
			PreparedStatement ps = Con.prepareStatement(SelectQuery);
			ps.setString(1,ID);
			ResultSet r = ps.executeQuery();
			
			while (r.next()) {
				textFieldID.setText(r.getString("IDVente"));
				textFieldDate.setText(r.getString("DateVente"));
				textFieldPrix.setText(r.getString("PrixVente"));
				textFieldNomClient.setText(r.getString("NomClient"));
				textFieldNomEmp.setText(r.getString("NomEmp"));
				textFieldModele.setText(r.getString("Modele"));
				textFieldStatus.setText(r.getString("Status"));
		           
			} 
			
		} catch (Exception e) {
			System.err.println(e);
			
		}  
	}	
	/**
	 * Methode pour la mise a jour de donnees
	 * @param Con Connexion a la base de donnees
	 * @param updateQuery La requete MySQL pour la mise a jour
	 */
		private void updateData(String ID) {
			Connection Con = Conn();
			
			try {
				String UpdateQuery = "UPDATE ventes SET DateVente = ?, PrixVente = ?, NomClient = ?, NomEmp = ?, Modele = ?, Status = ? WHERE IDVente = ?";
				PreparedStatement ps = Con.prepareStatement(UpdateQuery);
	
				ps.setString(1, textFieldDate.getText());
				ps.setString(2, textFieldPrix.getText());
				ps.setString(3, textFieldNomClient.getText());
				ps.setString(4, textFieldNomEmp.getText());
				ps.setString(5, textFieldModele.getText());
				ps.setString(6, textFieldStatus.getText());
				ps.setString(7, ID);
				ps.execute();
				
				JOptionPane.showMessageDialog(null, "Data Updated");
				
				ShowData();
				
			} catch (Exception e) {
				System.err.println(e);
				
			}  	
	
	}
		/**
		 * Methode pour la suppression des donnees
		 * @param Con Connexion a la base de donnees
		 * @param updateQuery La requete MySQL pour la suppression
		 */
	  private void deleteData (String ID) {
		  Connection Con = Conn();
		  try {
				String DeleteQuery = "DELETE FROM ventes WHERE IDVente = ?";
				PreparedStatement ps = Con.prepareStatement(DeleteQuery);
	
				ps.setString(1, ID);
				ps.execute();
				
				JOptionPane.showMessageDialog(null, "Deleted");
				ShowData();
				
			} catch (Exception e) {
				System.err.println(e);
				
			}  	
		  
	  }
	  

		/**
		 *  Methode getter de ce module
		 *  
		 */
	  public void getVente() {
		 Ventes.setVisible(true);
	  }
	}


