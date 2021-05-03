//PPE 3: Supercar Java Version 5.75
//Created By: Aakash Chady
//Date Created:22/03/2021
//Date Modified (Version 5.75): 03/05/2021
package SuperJava;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Commande {

	private JFrame RapportCommande;
	private JTextField textFieldID;
	private JTextField textFieldDate;
	private JTextField textFieldNomEmp;
	private JTextField textFieldModele;
	private JTextField textFieldQuantite;
	private JTextField textFieldPrix;
	private JTable table;
	JComboBox comboBox = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Commande window = new Commande();
					window.RapportCommande.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Commande() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		RapportCommande = new JFrame();
		RapportCommande.addWindowListener (new WindowAdapter () {
			@Override
			public void windowOpened (WindowEvent arg0) {
				ShowData();
			}
		});
		RapportCommande.setBounds(00, 100, 900, 700);
		RapportCommande.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		RapportCommande.getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblId.setBounds(54, 49, 48, 14);
		RapportCommande.getContentPane().add(lblId);
		
		
		comboBox.setMaximumRowCount(4);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"None", "Day", "Week", "Month"}));
		comboBox.setSelectedIndex(0);
		ActionListener actionListener = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        ShowData();
		      }
		    };
		    comboBox.addActionListener(actionListener);
		comboBox.setBounds(472, 55, 119, 22);
		RapportCommande.getContentPane().add(comboBox);
		
		JLabel lblDateDeCommande = new JLabel("Date De Commande");
		lblDateDeCommande.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDateDeCommande.setBounds(54, 77, 127, 14);
		RapportCommande.getContentPane().add(lblDateDeCommande);
		
		JLabel lblNomDeLemploye = new JLabel("Nom De L'Employe");
		lblNomDeLemploye.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNomDeLemploye.setBounds(54, 102, 127, 14);
		RapportCommande.getContentPane().add(lblNomDeLemploye);
		
		JLabel lblModele = new JLabel("Modele");
		lblModele.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblModele.setBounds(54, 127, 127, 14);
		RapportCommande.getContentPane().add(lblModele);
		
		JLabel lblQuantite = new JLabel("Quantite");
		lblQuantite.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQuantite.setBounds(54, 152, 127, 14);
		RapportCommande.getContentPane().add(lblQuantite);
		
		JLabel lblPrix = new JLabel("Prix");
		lblPrix.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrix.setBounds(54, 177, 127, 14);
		RapportCommande.getContentPane().add(lblPrix);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(192, 46, 154, 20);
		RapportCommande.getContentPane().add(textFieldID);
		textFieldID.setColumns(10);
		
		textFieldDate = new JTextField();
		textFieldDate.setColumns(10);
		textFieldDate.setBounds(191, 74, 154, 20);
		RapportCommande.getContentPane().add(textFieldDate);
		
		textFieldNomEmp = new JTextField();
		textFieldNomEmp.setColumns(10);
		textFieldNomEmp.setBounds(191, 99, 154, 20);
		RapportCommande.getContentPane().add(textFieldNomEmp);
		
		textFieldModele = new JTextField();
		textFieldModele.setColumns(10);
		textFieldModele.setBounds(192, 124, 154, 20);
		RapportCommande.getContentPane().add(textFieldModele);
		
		textFieldQuantite = new JTextField();
		textFieldQuantite.setColumns(10);
		textFieldQuantite.setBounds(192, 149, 154, 20);
		RapportCommande.getContentPane().add(textFieldQuantite);
		
		textFieldPrix = new JTextField();
		textFieldPrix.setColumns(10);
		textFieldPrix.setBounds(192, 174, 154, 20);
		RapportCommande.getContentPane().add(textFieldPrix);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveToDB();
			}
		});
		btnSave.setBounds(54, 219, 89, 23);
		RapportCommande.getContentPane().add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow()>= 0) {
				updateData(textFieldID.getText());
				}
			}
		});
		btnUpdate.setBounds(224, 219, 89, 23);
		RapportCommande.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow()>= 0) {
					deleteData(textFieldID.getText());
					}
				
			}
		});
		btnDelete.setBounds(403, 219, 89, 23);
		RapportCommande.getContentPane().add(btnDelete);
		
		JLabel lblFilterBy = new JLabel("Filter By");
		lblFilterBy.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFilterBy.setBounds(505, 30, 48, 14);
		RapportCommande.getContentPane().add(lblFilterBy);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10,253, 860, 200);
		RapportCommande.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String ID = table.getValueAt(table.getSelectedRow(),0).toString();
				selectData(ID);
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(388, 502, 89, 23);
		RapportCommande.getContentPane().add(btnExit);
	
	}
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
	
	private void SaveToDB () {
		String text = textFieldDate.getText();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate local = LocalDate.parse(text, formatter);
		Date date = Date.valueOf(local);

	
		Connection Connect = Conn();
	try {	
		String query = "insert into commandes (IDCommande, DateCommande, NomEmp, Model, Quantite, Prix) values(?,?,?,?,?,?) ";
		PreparedStatement in = Connect.prepareStatement(query);
		in.setString(1, textFieldID.getText());
		in.setDate(2, date);
		in.setString(3, textFieldNomEmp.getText());
		in.setString(4, textFieldModele.getText());
		in.setString(5, textFieldQuantite.getText());
		in.setString(6, textFieldPrix.getText());
		in.execute();
		JOptionPane.showMessageDialog(null, "Saved!!");
} catch (Exception e) {
	System.err.println("Error!!" + e);
}
}
	private void ShowData () {
		Connection Connect = Conn();
		DefaultTableModel model = new DefaultTableModel();
		Object[] column = {"ID","Date De Commande","Nom De L'employe","Modele", "Quantite", "Prix"};
      	model.setColumnIdentifiers(column);
    	String[] showQuery = new String[4];
        showQuery[0] = "SELECT * FROM `commandes`;";
        showQuery[1] = "SELECT * FROM `commandes` WHERE DateCommande = CURDATE();";
        showQuery[2] = "SELECT * FROM `commandes` WHERE YEARWEEK(DateCommande) = YEARWEEK(CURDATE());";
        showQuery[3]= "SELECT * FROM `commandes` WHERE MONTH(DateCommande) = MONTH(CURDATE()) AND YEAR(DateCommande) = YEAR(CURDATE());";
       
      	
	try {	
		model.setRowCount(0);
		model.fireTableDataChanged();   
		PreparedStatement s = Connect.prepareStatement(showQuery[comboBox.getSelectedIndex()]);
		ResultSet r = s.executeQuery();
		while (r.next()) {
			model.addRow(new Object[] {
			     r.getString("IDCommande"),
	            r.getString("DateCommande"),
	            r.getString("NomEmp"),
	            r.getString("Model"),
	             r.getString("Quantite"),
	            r.getString("Prix"),
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
			String SelectQuery = "SELECT * FROM commandes WHERE IDCommande = ?";
			PreparedStatement ps = Con.prepareStatement(SelectQuery);
			ps.setString(1,ID);
			ResultSet r = ps.executeQuery();
			
			while (r.next()) {
				textFieldID.setText(r.getString("IDCommande"));
				textFieldDate.setText(r.getString("DateCommande"));
				textFieldNomEmp.setText(r.getString("NomEmp"));
				textFieldModele.setText(r.getString("Model"));
				textFieldQuantite.setText(r.getString("Quantite"));
				textFieldPrix.setText(r.getString("Prix"));
				
		           
			} 
			
		} catch (Exception e) {
			System.err.println(e);
			
		}  
	}	
	private void updateData(String ID) {
		Connection Con = Conn();
		
		try {
			String UpdateQuery = "UPDATE commandes SET DateCommande = ?, NomEmp = ?, Model = ?, Quantite = ?, Prix = ? WHERE IDCommande = ?";
			PreparedStatement ps = Con.prepareStatement(UpdateQuery);

			ps.setString(1, textFieldDate.getText());
			ps.setString(2, textFieldNomEmp.getText());
			ps.setString(3, textFieldModele.getText());
			ps.setString(4, textFieldQuantite.getText());
			ps.setString(5, textFieldPrix.getText());
			ps.setString(6, ID);
			ps.execute();
			
			JOptionPane.showMessageDialog(null, "Data Updated");
			
			ShowData();
			
		} catch (Exception e) {
			System.err.println(e);
			
		}  	

}
	
  private void deleteData (String ID) {
	  Connection Con = Conn();
	  try {
			String DeleteQuery = "DELETE FROM commandes WHERE IDCommande = ?";
			PreparedStatement ps = Con.prepareStatement(DeleteQuery);

			ps.setString(1, ID);
			ps.execute();
			
			JOptionPane.showMessageDialog(null, "Deleted");
			ShowData();
			
		} catch (Exception e) {
			System.err.println(e);
			
		}  	
	  
  }
  public void getCommandes() {
	   RapportCommande.setVisible(true);
  }
}
