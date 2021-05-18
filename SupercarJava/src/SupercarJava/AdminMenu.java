package SupercarJava;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

public class AdminMenu {

	private JFrame menuadmin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMenu window = new AdminMenu();
					window.menuadmin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		menuadmin = new JFrame();
		menuadmin.setBounds(100, 100, 992, 600);
		menuadmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuadmin.getContentPane().setLayout(null);
		
		JLabel lblBienvenueAdmin = new JLabel("Bienvenue Admin");
		lblBienvenueAdmin.setBounds(406, 11, 184, 14);
		menuadmin.getContentPane().add(lblBienvenueAdmin);
		
		JButton btnOverviewEntrepots = new JButton("Overview Entrepots");
		btnOverviewEntrepots.setBounds(356, 77, 194, 23);
		menuadmin.getContentPane().add(btnOverviewEntrepots);
		
		JButton btnOverviewCommandes = new JButton("Overview Commandes");
		btnOverviewCommandes.setBounds(356, 127, 194, 23);
		menuadmin.getContentPane().add(btnOverviewCommandes);
	}
	
	/**
	 *  Methode getter de ce module
	 *  
	 */
  public void getMenuAdmin() {
	 menuadmin.setVisible(true);
  }
}
