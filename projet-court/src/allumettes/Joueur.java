package allumettes;

/** Un joueur du jeu des allumettes.  Il possède un nom et une stratégie
 * qui détermine le nombre d'allumettes qu'il prend.  La stratégie peut
 * être changée en cours de partie.
 * @author Alexis Declippel
 */
public class Joueur {

	/** Nom du joueur. */
	private final String nom;

	/** Stratégie suivie par le joueur. */
	private Strategie strategie;

	/** Initialiser un joueur avec un nom et une stratégie.
	 * @param nom le nom du joueur
	 * @param strategie la stratégie suivie
	 */
	public Joueur(String nom, Strategie strategie) {
		assert nom != null : "Le nom ne doit pas être null";
		assert strategie != null : "La stratégie ne doit pas être null";
		this.nom = nom;
		this.strategie = strategie;
	}

	/** Obtenir le nom du joueur.
	 * @return le nom du joueur
	 */
	public String getNom() {
		return this.nom;
	}

	/** Changer la stratégie suivie par le joueur.
	 * @param strategie la nouvelle stratégie
	 */
	public void setStrategie(Strategie strategie) {
		assert strategie != null : "La stratégie ne doit pas être null";
		this.strategie = strategie;
	}

	/** Demander au joueur le nombre d'allumettes qu'il veut prendre.
	 * @param jeu le jeu sur lequel jouer
	 * @return le nombre d'allumettes choisi
	 */
	public int getPrise(Jeu jeu) {
		return this.strategie.getPrise(jeu, this.nom);
	}

}
