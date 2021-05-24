package SupercarJava;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		btnOverviewEntrepots.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuadmin.setVisible(false);
				UIEntrepot ThisUI = new UIEntrepot();
				ThisUI.getEntrepotUI();
			}
		});
		btnOverviewEntrepots.setBounds(356, 77, 194, 23);
		menuadmin.getContentPane().add(btnOverviewEntrepots);
		
		JButton btnOverviewCommandes = new JButton("Overview Commandes");
		btnOverviewCommandes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuadmin.setVisible(false);
				UICommandes ThisUI = new UICommandes();
				ThisUI.getCommandeUI();
			}
		});
		btnOverviewCommandes.setBounds(356, 127, 194, 23);
		menuadmin.getContentPane().add(btnOverviewCommandes);
		
		JButton btnOverviewUtilisateur = new JButton("Overview Utilisateur");
		btnOverviewUtilisateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuadmin.setVisible(false);
				UIUserTable SeeUsers = new UIUserTable();
				SeeUsers.getUserUI();
			}
		});
		btnOverviewUtilisateur.setBounds(356, 171, 194, 23);
		menuadmin.getContentPane().add(btnOverviewUtilisateur);
		
		JButton btnAjouterUnUtilisateur = new JButton("Ajouter Un Utilisateur");
		btnAjouterUnUtilisateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuadmin.setVisible(false);
				UIAddUser AddUsers = new UIAddUser();
				AddUsers.getAddUserUI();
			}
		});
		btnAjouterUnUtilisateur.setBounds(356, 217, 194, 23);
		menuadmin.getContentPane().add(btnAjouterUnUtilisateur);
	}
	
	/**
	 *  Methode getter de ce module
	 *  
	 */
  public void getMenuAdmin() {
	 menuadmin.setVisible(true);
  }
}
