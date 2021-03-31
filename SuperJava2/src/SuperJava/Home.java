//PPE 3: Supercar Java Version 2.0
//Created By: Aakash Chady
//Date Created:22/03/2021
//Date Modified (Version 2.0): 31/03/2021



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
		btnSave.setBounds(314, 216, 89, 23);
		frame.getContentPane().add(btnSave);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10,253, 860, 200);
		frame.getContentPane().add(scrollPane);
		table = new JTable();
		
	scrollPane.setViewportView(table);
		
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
	}catch (Exception e) {
		System.err.println("Error!!" + e);
	}
		}
	
	private void ShowData () {
		Connection Connect = Conn();
		DefaultTableModel model = new DefaultTableModel();
		Object[] column = {"ID","Date De Vente","Prix De Vente (TTC)","Nom Du Client", "Nom De L'employe", "Modele", "Statut"};
      	model.setColumnIdentifiers(column);
	try {	
		String showQuery ="SELECT * FROM `ventes`";
		PreparedStatement s = Connect.prepareStatement(showQuery);
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
	}

