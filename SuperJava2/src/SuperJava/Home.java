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




public class Home {

	private JFrame frame;
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
	public Home() {
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
		
		JLabel lblDateVente = new JLabel("Date Vente");
		lblDateVente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDateVente.setBounds(10, 66, 100, 14);
		frame.getContentPane().add(lblDateVente);
		
		JLabel lblPrixVentettc = new JLabel("Prix Vente (TTC)");
		lblPrixVentettc.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrixVentettc.setBounds(10, 91, 100, 14);
		frame.getContentPane().add(lblPrixVentettc);
		
		JLabel lblNomClient = new JLabel("Nom Client");
		lblNomClient.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNomClient.setBounds(10, 116, 100, 14);
		frame.getContentPane().add(lblNomClient);
		
		JLabel lblNomEmploye = new JLabel("Nom Employe");
		lblNomEmploye.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNomEmploye.setBounds(10, 141, 100, 14);
		frame.getContentPane().add(lblNomEmploye);
		
		JLabel lblModele = new JLabel("Modele");
		lblModele.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblModele.setBounds(10, 166, 100, 14);
		frame.getContentPane().add(lblModele);
		
		JLabel lblStatut = new JLabel("Statut");
		lblStatut.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatut.setBounds(10, 191, 100, 14);
		frame.getContentPane().add(lblStatut);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(120, 38, 465, 20);
		frame.getContentPane().add(textFieldID);
		textFieldID.setColumns(10);
		
		textFieldDate = new JTextField();
		textFieldDate.setColumns(10);
		textFieldDate.setBounds(120, 63, 465, 20);
		frame.getContentPane().add(textFieldDate);
		
		textFieldPrix = new JTextField();
		textFieldPrix.setColumns(10);
		textFieldPrix.setBounds(120, 88, 465, 20);
		frame.getContentPane().add(textFieldPrix);
		
		textFieldNomClient = new JTextField();
		textFieldNomClient.setColumns(10);
		textFieldNomClient.setBounds(120, 113, 465, 20);
		frame.getContentPane().add(textFieldNomClient);
		
		textFieldNomEmp = new JTextField();
		textFieldNomEmp.setColumns(10);
		textFieldNomEmp.setBounds(120, 138, 465, 20);
		frame.getContentPane().add(textFieldNomEmp);
		
		textFieldModele = new JTextField();
		textFieldModele.setColumns(10);
		textFieldModele.setBounds(120, 163, 465, 20);
		frame.getContentPane().add(textFieldModele);
		
		textFieldStatus = new JTextField();
		textFieldStatus.setColumns(10);
		textFieldStatus.setBounds(120, 188, 465, 20);
		frame.getContentPane().add(textFieldStatus);
		
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
	
    private void ChangeIndex() {
    	
    }
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
	}


