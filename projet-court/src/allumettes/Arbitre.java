package allumettes;

/** Arbitre d'une partie des allumettes. Il fait jouer les deux joueurs à
 * tour de rôle, vérifie le respect des règles et annonce le résultat.
 * Enregistre également le déroulement au format XML.
 * @author Alexis Declippel
 */
public class Arbitre {

	/** Premier joueur à jouer. */
	private final Joueur joueur1;

	/** Second joueur. */
	private final Joueur joueur2;

	/** Indique si l'arbitre transmet directement le jeu réel. */
	private boolean confiant;

	/** Initialiser l'arbitre avec les deux joueurs qui vont s'affronter.
	 * @param joueur1 le premier joueur (joue en premier)
	 * @param joueur2 le second joueur
	 */
	public Arbitre(Joueur joueur1, Joueur joueur2) {
		assert joueur1 != null && joueur2 != null : "Joueurs requis";
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		this.confiant = false;
	}

	/** Préciser si l'arbitre est confiant.
	 * @param confiant true si l'arbitre fait confiance aux joueurs
	 */
	public void setConfiant(boolean confiant) {
		this.confiant = confiant;
	}

	/** Arbitrer une partie sur le jeu donné et écrire le rapport XML.
	 * @param jeu le jeu à arbitrer
	 */
	public void arbitrer(Jeu jeu) {
		assert jeu != null : "Le jeu ne doit pas être null";
		Joueur joueurCourant = this.joueur1;
		boolean premierTour = true;
		EnregistreurXML xml = new EnregistreurXML();
		
		try {
			while (jeu.getNombreAllumettes() > 0) {
				if (!premierTour) {
					System.out.println();
				}
				premierTour = false;
				System.out.println("Allumettes restantes : "
						+ jeu.getNombreAllumettes());
				
				int prise = faireJouer(joueurCourant, jeu);
				xml.enregistrerCoup(joueurCourant.getNom(), prise);
				
				joueurCourant = autreJoueur(joueurCourant);
			}
			annoncerResultat(joueurCourant, autreJoueur(joueurCourant));
			xml.ecrireFichier(joueurCourant.getNom(), null);
			
		} catch (OperationInterditeException e) {
			System.out.println("Abandon de la partie car "
					+ joueurCourant.getNom() + " triche !");
			xml.ecrireFichier(null, joueurCourant.getNom());
		}
	}

	/** Faire jouer un joueur jusqu'à obtenir une prise valide.
	 * @param joueur le joueur courant
	 * @param jeu le jeu réel
	 * @return le nombre d'allumettes finalement prises
	 */
	private int faireJouer(Joueur joueur, Jeu jeu) {
		Jeu jeuConfie = this.confiant ? jeu : new JeuProcuration(jeu);
		boolean coupValide = false;
		int prise = 0;
		while (!coupValide) {
			prise = joueur.getPrise(jeuConfie);
			System.out.println(joueur.getNom() + " prend "
					+ prise + allumetteMot(prise) + ".");
			coupValide = retirer(jeu, prise);
		}
		return prise;
	}

	/** Retirer les allumettes du jeu réel et gérer l'exception de coup invalide.
	 * @param jeu le jeu réel
	 * @param prise le nombre d'allumettes à retirer
	 * @return true si le coup est valide, false sinon
	 */
	private boolean retirer(Jeu jeu, int prise) {
		try {
			jeu.retirer(prise);
			return true;
		} catch (CoupInvalideException e) {
			System.out.println("Impossible ! Nombre invalide : "
					+ e.getCoup() + " (" + e.getProbleme() + ")");
			System.out.println();
			System.out.println("Allumettes restantes : "
					+ jeu.getNombreAllumettes());
			return false;
		}
	}

	/** Annoncer le résultat de la partie.
	 * @param gagnant le joueur gagnant
	 * @param perdant le joueur perdant
	 */
	private void annoncerResultat(Joueur gagnant, Joueur perdant) {
		System.out.println();
		System.out.println(perdant.getNom() + " perd !");
		System.out.println(gagnant.getNom() + " gagne !");
	}

	/** Obtenir l'autre joueur.
	 * @param joueur un des deux joueurs
	 * @return l'autre joueur
	 */
	private Joueur autreJoueur(Joueur joueur) {
		return joueur == this.joueur1 ? this.joueur2 : this.joueur1;
	}

	/** Donner le mot « allumette » au bon nombre (singulier/pluriel).
	 * @param nombre le nombre d'allumettes
	 * @return " allumette" ou " allumettes"
	 */
	private String allumetteMot(int nombre) {
		return nombre > 1 ? " allumettes" : " allumette";
	}

}