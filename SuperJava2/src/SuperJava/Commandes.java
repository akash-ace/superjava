//PPE 3: Supercar Java Version 4.5
//Created By: Aakash Chady
//Date Created:22/03/2021
//Date Modified (Version 4.5): 30/04/2021
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
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Commandes {

	private JFrame frame;
	private JTextField textFieldID;
	private JTextField textFieldDate;
	private JTextField textFieldNomEmp;
	private JTextField textFieldQuantite;
	private JTextField textFieldPrix;
	private JTextField textFieldModele;
	private JTable table;
	JComboBox comboBox = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Commandes window = new Commandes();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Commandes() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblId.setBounds(33, 41, 48, 14);
		frame.getContentPane().add(lblId);
		
		JLabel lblDate = new JLabel("Date De Commande");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDate.setBounds(33, 66, 114, 14);
		frame.getContentPane().add(lblDate);
		
		JLabel lblNomEmp = new JLabel("Nom De L'employe");
		lblNomEmp.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNomEmp.setBounds(33, 91, 114, 14);
		frame.getContentPane().add(lblNomEmp);
		
		JLabel lblQuantite = new JLabel("Quantite");
		lblQuantite.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQuantite.setBounds(33, 138, 73, 14);
		frame.getContentPane().add(lblQuantite);
		
		JLabel lblPrix = new JLabel("Prix");
		lblPrix.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrix.setBounds(33, 163, 48, 14);
		frame.getContentPane().add(lblPrix);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(148, 38, 210, 20);
		frame.getContentPane().add(textFieldID);
		textFieldID.setColumns(10);
		
		textFieldDate = new JTextField();
		textFieldDate.setColumns(10);
		textFieldDate.setBounds(148, 63, 210, 20);
		frame.getContentPane().add(textFieldDate);
		
		textFieldNomEmp = new JTextField();
		textFieldNomEmp.setColumns(10);
		textFieldNomEmp.setBounds(148, 88, 210, 20);
		frame.getContentPane().add(textFieldNomEmp);
		
		textFieldQuantite = new JTextField();
		textFieldQuantite.setColumns(10);
		textFieldQuantite.setBounds(148, 135, 210, 20);
		frame.getContentPane().add(textFieldQuantite);
		
		textFieldPrix = new JTextField();
		textFieldPrix.setColumns(10);
		textFieldPrix.setBounds(148, 160, 210, 20);
		frame.getContentPane().add(textFieldPrix);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveToDB();
			}
		});
		btnSave.setBounds(38, 208, 89, 23);
		frame.getContentPane().add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow()>= 0) {
				updateData(textFieldID.getText());
				}
			}
		});
		btnUpdate.setBounds(148, 208, 89, 23);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow()>= 0) {
					deleteData(textFieldID.getText());
					}
				
			}
		});
		btnDelete.setBounds(269, 208, 89, 23);
		frame.getContentPane().add(btnDelete);
		
		JLabel lblFilterBy = new JLabel("FILTER BY");
		lblFilterBy.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFilterBy.setBounds(505, 41, 89, 14);
		frame.getContentPane().add(lblFilterBy);
		
	
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"None", "Day", "Week", "Month"}));
		comboBox.setSelectedIndex(0);
		comboBox.setMaximumRowCount(4);
		ActionListener actionListener = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        ShowData();
		      }
		    };
		    comboBox.addActionListener(actionListener);
		comboBox.setBounds(486, 62, 89, 22);
		frame.getContentPane().add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 268, 600, 300);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String ID = table.getValueAt(table.getSelectedRow(),0).toString();
				selectData(ID);
			}
		});
		
		scrollPane.setViewportView(table);
		
		JLabel lblModele = new JLabel("Modele");
		lblModele.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblModele.setBounds(33, 116, 73, 14);
		frame.getContentPane().add(lblModele);
		
		textFieldModele = new JTextField();
		textFieldModele.setColumns(10);
		textFieldModele.setBounds(148, 113, 210, 20);
		frame.getContentPane().add(textFieldModele);
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
			String DeleteQuery = "DELETE FROM commandes WHERE IDVente = ?";
			PreparedStatement ps = Con.prepareStatement(DeleteQuery);

			ps.setString(1, ID);
			ps.execute();
			
			JOptionPane.showMessageDialog(null, "Deleted");
			ShowData();
			
		} catch (Exception e) {
			System.err.println(e);
			
		}  	
	  
  }
}

