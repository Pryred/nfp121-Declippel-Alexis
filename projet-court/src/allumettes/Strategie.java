package allumettes;

/** Stratégie de jeu : façon de déterminer le nombre d'allumettes à prendre.
 * Permettre d'ajouter de nouvelles stratégies sans modifier les classes
 * existantes et de changer la stratégie d'un joueur en cours de partie.
 * @author Alexis Declippel
 */
public interface Strategie {

	/** Déterminer le nombre d'allumettes à prendre pour le jeu donné.
	 * @param jeu le jeu sur lequel jouer
	 * @param nomJoueur le nom du joueur qui joue (utile pour l'affichage)
	 * @return le nombre d'allumettes à prendre
	 */
	int getPrise(Jeu jeu, String nomJoueur);

}
