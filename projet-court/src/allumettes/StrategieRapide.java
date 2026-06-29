package allumettes;

/** Stratégie rapide : prendre le maximum d'allumettes possible pour
 * terminer la partie le plus vite possible.
 * @author Alexis Declippel
 */
public class StrategieRapide implements Strategie {

	@Override
	public int getPrise(Jeu jeu, String nomJoueur) {
		assert jeu != null : "Le jeu ne doit pas être null";
		return Math.min(Jeu.PRISE_MAX, jeu.getNombreAllumettes());
	}

}
