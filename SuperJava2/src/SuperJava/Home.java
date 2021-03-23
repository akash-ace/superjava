//PPE 3: Supercar Java Version 1.0
//Created By: Aakash Chady
//Date:22/03/2021



package SuperJava;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.DriverManager;
import java.awt.EventQueue;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class Home {

	private JFrame frame;
	private JTextField textFieldID;
	private JTextField textFieldDate;
	private JTextField textFieldPrix;
	private JTextField textFieldNomClient;
	private JTextField textFieldNomEmp;
	private JTextField textFieldModele;
	private JTextField textFieldStatus;

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
		frame.setBounds(100, 100, 450, 300);
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
		textFieldID.setBounds(120, 38, 183, 20);
		frame.getContentPane().add(textFieldID);
		textFieldID.setColumns(10);
		
		textFieldDate = new JTextField();
		textFieldDate.setColumns(10);
		textFieldDate.setBounds(120, 63, 183, 20);
		frame.getContentPane().add(textFieldDate);
		
		textFieldPrix = new JTextField();
		textFieldPrix.setColumns(10);
		textFieldPrix.setBounds(120, 88, 183, 20);
		frame.getContentPane().add(textFieldPrix);
		
		textFieldNomClient = new JTextField();
		textFieldNomClient.setColumns(10);
		textFieldNomClient.setBounds(120, 113, 183, 20);
		frame.getContentPane().add(textFieldNomClient);
		
		textFieldNomEmp = new JTextField();
		textFieldNomEmp.setColumns(10);
		textFieldNomEmp.setBounds(120, 138, 183, 20);
		frame.getContentPane().add(textFieldNomEmp);
		
		textFieldModele = new JTextField();
		textFieldModele.setColumns(10);
		textFieldModele.setBounds(120, 163, 183, 20);
		frame.getContentPane().add(textFieldModele);
		
		textFieldStatus = new JTextField();
		textFieldStatus.setColumns(10);
		textFieldStatus.setBounds(120, 188, 183, 20);
		frame.getContentPane().add(textFieldStatus);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveToDB();
			}
		});
		btnSave.setBounds(155, 219, 89, 23);
		frame.getContentPane().add(btnSave);
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
	}

