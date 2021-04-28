//PPE 3: Supercar Java Version 4.0
//Created By: Aakash Chady
//Date Created:22/03/2021
//Date Modified (Version 4.0): 28/04/2021


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


public class Commandes {
	private JFrame frame;
	private JTextField textFieldID;
	private JTextField textFieldDate;
	private JTextField textFieldNomEmp;
	private JTextField textFieldModel;
	private JTextField textFieldQuantite;
	private JTextField textFieldPrix;
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
		frame.addWindowListener (new WindowAdapter () {
			@Override
			public void windowOpened (WindowEvent arg0) {
				ShowData();
			}
		});
		frame.setBounds(100, 100, 900, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblId.setBounds(10, 41, 48, 14);
		frame.getContentPane().add(lblId);
		
		JLabel lblDateCommande = new JLabel("Date Vente");
		lblDateCommande.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDateCommande.setBounds(10, 66, 100, 14);
		frame.getContentPane().add(lblDateCommande);
		
		JLabel lblNomEmp = new JLabel("Nom Employe");
		lblNomEmp.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNomEmp.setBounds(10, 91, 100, 14);
		frame.getContentPane().add(lblNomEmp);
		
		JLabel lblModele = new JLabel("Modele");
		lblModele.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblModele.setBounds(10, 116, 100, 14);
		frame.getContentPane().add(lblModele);
		
		JLabel lblQuantite = new JLabel("Quantite");
		lblQuantite.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQuantite.setBounds(10, 141, 100, 14);
		frame.getContentPane().add(lblQuantite);
		
		JLabel lbPrix = new JLabel("Prix(TTC)");
		lbPrix.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbPrix.setBounds(10, 166, 100, 14);
		frame.getContentPane().add(lbPrix);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(120, 38, 465, 20);
		frame.getContentPane().add(textFieldID);
		textFieldID.setColumns(10);
		
		textFieldDate = new JTextField();
		textFieldDate.setColumns(10);
		textFieldDate.setBounds(120, 63, 465, 20);
		frame.getContentPane().add(textFieldDate);
		
		textFieldNomEmp = new JTextField();
		textFieldNomEmp.setColumns(10);
		textFieldNomEmp.setBounds(120, 88, 465, 20);
		frame.getContentPane().add(textFieldNomEmp);
		
		textFieldModel = new JTextField();
		textFieldModel.setColumns(10);
		textFieldModel.setBounds(120, 113, 465, 20);
		frame.getContentPane().add(textFieldModel);
		
		textFieldQuantite = new JTextField();
		textFieldQuantite.setColumns(10);
		textFieldQuantite.setBounds(120, 138, 465, 20);
		frame.getContentPane().add(textFieldQuantite);
		
		textFieldPrix = new JTextField();
		textFieldPrix.setColumns(10);
		textFieldPrix.setBounds(120, 163, 465, 20);
		frame.getContentPane().add(textFieldPrix);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveToDB();
			}
		});
		btnSave.setBounds(130, 219, 89, 23);
		frame.getContentPane().add(btnSave);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10,253, 860, 200);
		frame.getContentPane().add(scrollPane);
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String ID = table.getValueAt(table.getSelectedRow(),0).toString();
				selectData(ID);
			}
		});
		
	scrollPane.setViewportView(table);
	
	JButton buttonUpdate = new JButton("Update");
	buttonUpdate.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			if (table.getSelectedRow()>= 0) {
			updateData(textFieldID.getText());
			}
		}
	});
	buttonUpdate.setBounds(285, 219, 89, 23);
	frame.getContentPane().add(buttonUpdate);
	
	JButton buttonDelete = new JButton("Delete");
	buttonDelete.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (table.getSelectedRow()>= 0) {
				deleteData(textFieldID.getText());
				}
			
		}
	});
	buttonDelete.setBounds(467, 219, 89, 23);
	frame.getContentPane().add(buttonDelete);
	
	JLabel lblFilter = new JLabel("FILTER BY");
	lblFilter.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblFilter.setBounds(741, 41, 111, 14);
	frame.getContentPane().add(lblFilter);
	

	comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"None", "Day", "Week", "Month"}));
	comboBox_1.setSelectedIndex(0);
	comboBox_1.setMaximumRowCount(4);
	ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        ShowData();
	      }
	    };
	    comboBox_1.addActionListener(actionListener);
	comboBox_1.setBounds(741, 62, 82, 22);
	frame.getContentPane().add(comboBox_1);
 
		
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
		in.setString(4, textFieldModel.getText());
		in.setString(5, textFieldQuantite.getText());
		in.setString(6, textFieldPrix.getText());
		in.execute();
		JOptionPane.showMessageDialog(null, "Saved!!");
		ShowData();

	}catch (Exception e) {
		System.err.println("Error!!" + e);
	}
		}
	
    private void ChangeIndex() {
    	
    }
	private void ShowData () {
		Connection Connect = Conn();
		DefaultTableModel model = new DefaultTableModel();
		Object[] column = {"ID","Date Commande","Nom De L'employe","Modele", "Quantite", "Prix(TTC)"};
      	model.setColumnIdentifiers(column);
    	String[] showQuery = new String[4];
        showQuery[0] = "SELECT * FROM `commandes`;";
        showQuery[1] = "SELECT * FROM `commandes` WHERE DateCommande = CURDATE();";
        showQuery[2] = "SELECT * FROM `commandes` WHERE YEARWEEK(DateCommande) = YEARWEEK(CURDATE());";
        showQuery[3]= "SELECT * FROM `commandes` WHERE MONTH(DateCommande) = MONTH(CURDATE()) AND YEAR(DateCommande)= YEAR(CURDATE());";
       
      	
	try {	
		
		PreparedStatement s = Connect.prepareStatement(showQuery[comboBox_1.getSelectedIndex()]);
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
				textFieldModel.setText(r.getString("Model"));
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
				ps.setString(3, textFieldModel.getText());
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
	}


