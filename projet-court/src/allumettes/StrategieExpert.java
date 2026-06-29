package allumettes;

/** Stratégie experte : jouer du mieux possible.  Le but est de laisser à
 * l'adversaire un nombre d'allumettes congru à 1 modulo (PRISE_MAX + 1),
 * position perdante pour lui.  Si ce n'est pas possible, on prend une seule
 * allumette pour prolonger la partie.
 * @author Alexis Declippel
 */
public class StrategieExpert implements Strategie {

	@Override
	public int getPrise(Jeu jeu, String nomJoueur) {
		assert jeu != null : "Le jeu ne doit pas être null";
		int restantes = jeu.getNombreAllumettes();
		final int cycle = Jeu.PRISE_MAX + 1;
		int prise = (restantes - 1) % cycle;
		if (prise < 1 || prise > restantes) {
			// Position déjà perdante : on prend le minimum.
			prise = 1;
		}
		return prise;
	}

}
