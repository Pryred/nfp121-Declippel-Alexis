package allumettes;

import java.lang.reflect.Field;

/** Stratégie supertricheur : triche même en mode non confiant en accédant
 * directement au jeu réel caché derrière la procuration grâce à la réflexion.
 * @author Alexis Declippel
 */
public class StrategieSupertricheur implements Strategie {

	/** Nombre d'allumettes que le tricheur veut laisser avant de prendre. */
	private static final int ALLUMETTES_VISEES = 2;

	@Override
	public int getPrise(Jeu jeu, String nomJoueur) {
		assert jeu != null : "Le jeu ne doit pas être null";
		System.out.println("[Je triche...]");
		try {
			Jeu vraiJeu = jeu;
			
			// Si le jeu est encapsulé dans une procuration, on extrait le sujet réel
			if (jeu instanceof JeuProcuration) {
				Field champSujetReel = jeu.getClass().getDeclaredField("sujetReel");
				champSujetReel.setAccessible(true);
				vraiJeu = (Jeu) champSujetReel.get(jeu);
			}
			
			// Modification directe du jeu réel pour contourner le proxy
			while (vraiJeu.getNombreAllumettes() > ALLUMETTES_VISEES) {
				int reste = vraiJeu.getNombreAllumettes() - ALLUMETTES_VISEES;
				vraiJeu.retirer(Math.min(Jeu.PRISE_MAX, reste));
			}
		} catch (Exception e) {
			// On ignore en cas d'échec
		}
		
		System.out.println("[Allumettes restantes : "
				+ jeu.getNombreAllumettes() + "]");
		return 1;
	}

}