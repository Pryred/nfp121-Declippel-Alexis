package allumettes;

import java.util.Random;

/** Stratégie naïve : choisir aléatoirement un nombre entre 1 et 3,
 * dans la limite du nombre d'allumettes restantes.
 * @author Alexis Declippel
 */
public class StrategieNaif implements Strategie {

	/** Générateur de nombres aléatoires. */
	private final Random random = new Random();

	@Override
	public int getPrise(Jeu jeu, String nomJoueur) {
		assert jeu != null : "Le jeu ne doit pas être null";
		int maximum = Math.min(Jeu.PRISE_MAX, jeu.getNombreAllumettes());
		return 1 + this.random.nextInt(maximum);
	}

}
