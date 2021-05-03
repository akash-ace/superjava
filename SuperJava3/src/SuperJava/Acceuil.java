//PPE 3: Supercar Java Version 5.75
//Created By: Aakash Chady
//Date Created:22/03/2021
//Date Modified (Version 5.75): 03/05/2021

package SuperJava;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Acceuil {

	private JFrame Acceuil;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Acceuil window = new Acceuil();
					window.Acceuil.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Acceuil() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Acceuil = new JFrame();
		Acceuil.setBounds(100, 100, 900, 700);
		Acceuil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Acceuil.getContentPane().setLayout(null);
		
		JLabel lblSupercar = new JLabel("Supercar");
		lblSupercar.setForeground(Color.BLUE);
		lblSupercar.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblSupercar.setBounds(350, 35, 316, 95);
		Acceuil.getContentPane().add(lblSupercar);
		
		JLabel lblChoisissezLeMenu = new JLabel("Choisissez Le Menu");
		lblChoisissezLeMenu.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblChoisissezLeMenu.setBounds(295, 104, 459, 86);
		Acceuil.getContentPane().add(lblChoisissezLeMenu);
		
		JButton btnRapportDeVente = new JButton("Rapport De Vente");
		btnRapportDeVente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Acceuil.setVisible(false);
                Home RDeVente = new Home();
                RDeVente.getVente();
			}
		});
		btnRapportDeVente.setBounds(350, 200, 191, 65);
		Acceuil.getContentPane().add(btnRapportDeVente);
		
        JButton btnRapportDeCommandes = new JButton("Rapport De Commandes");
        btnRapportDeCommandes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Acceuil.setVisible(false);
                Commande RDeCommande = new Commande();
                RDeCommande.getCommandes();
			}
		});
		btnRapportDeCommandes.setBounds(350, 294, 191, 65);
		Acceuil.getContentPane().add(btnRapportDeCommandes);
	}
	public void getAcceuil() {
		Acceuil.setVisible(true);
	}
}
