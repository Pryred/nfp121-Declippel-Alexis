package allumettes;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/** Stratégie Swing : permet à un humain de jouer via une interface graphique.
 * Gère la synchronisation entre le thread principal de l'arbitre et l'EDT de Swing.
 * @author Alexis Declippel
 */
public class StrategieSwing implements Strategie {

	/** Verrou pour la synchronisation entre les threads main et Swing. */
	private final Object verrou = new Object();
	
	/** Le choix d'allumettes mémorisé après un clic. */
	private int choix = 0;
	
	/** Stocke l'exception si la triche est bloquée par la procuration. */
	private RuntimeException tricheException = null;
	
	/** Le jeu courant manipulé par l'IHM. */
	private Jeu jeuCourant;

	/** Fenêtre principale de l'interface. */
	private JFrame fenetre;
	
	/** Label affichant le nombre d'allumettes. */
	private JLabel labelAllumettes;
	
	/** Bouton pour prendre 1 allumette. */
	private JButton b1;
	
	/** Bouton pour prendre 2 allumettes. */
	private JButton b2;
	
	/** Bouton pour prendre 3 allumettes. */
	private JButton b3;
	
	/** Bouton pour déclencher la triche. */
	private JButton bTricher;
	
	/** Zone de saisie du nombre d'allumettes à enlever ou ajouter. */
	private JTextField champTriche;

	/** Initialiser la stratégie et programmer la création de l'IHM. */
	public StrategieSwing() {
		SwingUtilities.invokeLater(this::creerIHM);
	}

	/** Construire les composants graphiques de l'IHM. */
	private void creerIHM() {
		this.fenetre = new JFrame();
		this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.fenetre.setLayout(new BorderLayout());
		this.fenetre.setSize(250, 150);

		JPanel panelTriche = new JPanel();
		this.bTricher = new JButton("Tricher");
		this.champTriche = new JTextField("1", 2);
		panelTriche.add(this.bTricher);
		panelTriche.add(this.champTriche);
		this.fenetre.add(panelTriche, BorderLayout.NORTH);

		this.labelAllumettes = new JLabel("", SwingConstants.CENTER);
		this.labelAllumettes.setFont(new Font("Arial", Font.BOLD, 36));
		this.fenetre.add(this.labelAllumettes, BorderLayout.CENTER);

		JPanel panelBoutons = new JPanel();
		this.b1 = new JButton("1");
		this.b2 = new JButton("2");
		this.b3 = new JButton("3");
		panelBoutons.add(this.b1);
		panelBoutons.add(this.b2);
		panelBoutons.add(this.b3);
		this.fenetre.add(panelBoutons, BorderLayout.SOUTH);

		this.b1.addActionListener(e -> validerChoix(1));
		this.b2.addActionListener(e -> validerChoix(2));
		this.b3.addActionListener(e -> validerChoix(3));
		this.bTricher.addActionListener(e -> actionTricher());
	}

	/** Enregistrer le choix et réveiller l'arbitre.
	 * @param valeur le nombre d'allumettes choisies
	 */
	private void validerChoix(int valeur) {
		synchronized (this.verrou) {
			this.choix = valeur;
			this.verrou.notify();
		}
	}

	/** Gérer l'action du bouton tricher en modifiant directement le jeu. */
	private void actionTricher() {
		try {
			int nb = Integer.parseInt(this.champTriche.getText());
			if (nb < 0) {
				System.out.println("[Je triche... " + (-nb) + " allumette en plus]");
			} else {
				System.out.println("[Je triche... " + nb + " allumettes en moins]");
			}
			this.jeuCourant.retirer(nb);
			this.labelAllumettes.setText(String.valueOf(this.jeuCourant.getNombreAllumettes()));
		} catch (OperationInterditeException e) {
			// Si la procuration intercepte, on réveille l'arbitre avec l'erreur
			synchronized (this.verrou) {
				this.tricheException = e;
				this.choix = -1;
				this.verrou.notify();
			}
		} catch (Exception e) {
			// Saisies non entières ou CoupInvalideException ignorés ici
		}
	}

	@Override
	public int getPrise(Jeu jeu, String nomJoueur) {
		this.jeuCourant = jeu;
		this.choix = 0;

		// Configuration et affichage de la fenêtre sur l'EDT de Swing
		SwingUtilities.invokeLater(() -> {
			this.fenetre.setTitle(nomJoueur + " ?");
			this.labelAllumettes.setText(String.valueOf(jeu.getNombreAllumettes()));
			this.b1.setEnabled(jeu.getNombreAllumettes() >= 1);
			this.b2.setEnabled(jeu.getNombreAllumettes() >= 2);
			this.b3.setEnabled(jeu.getNombreAllumettes() >= 3);
			this.fenetre.setVisible(true);
		});

		// Blocage du thread main en attendant l'action utilisateur
		synchronized (this.verrou) {
			try {
				while (this.choix == 0) {
					this.verrou.wait();
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		SwingUtilities.invokeLater(() -> this.fenetre.setVisible(false));

		// Relance l'erreur capturée si la triche a échoué face au proxy
		if (this.tricheException != null) {
			RuntimeException e = this.tricheException;
			this.tricheException = null;
			throw e;
		}

		return this.choix;
	}

}