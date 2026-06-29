package allumettes;

import java.util.Scanner;

/** Stratégie humaine : demander à l'utilisateur le nombre d'allumettes
 * à prendre.  L'utilisateur peut aussi taper « triche » pour retirer
 * discrètement une allumette du jeu.
 * @author Alexis Declippel
 */
public class StrategieHumain implements Strategie {

	/** Mot-clé permettant à l'utilisateur de tricher. */
	private static final String TRICHE = "triche";

	@Override
	public int getPrise(Jeu jeu, String nomJoueur) {
		assert jeu != null : "Le jeu ne doit pas être null";
		Scanner scanner = EntreeStandard.getScanner();
		int prise = 0;
		boolean choixFait = false;
		while (!choixFait) {
			System.out.print(nomJoueur + ", combien d'allumettes ? ");
			if (scanner.hasNextInt()) {
				prise = scanner.nextInt();
				choixFait = true;
			} else {
				choixFait = traiterEntreeNonEntiere(scanner, jeu);
			}
		}
		return prise;
	}

	/** Traiter une entrée qui n'est pas un entier : soit une demande de
	 * triche, soit une saisie erronée.
	 * @param scanner le scanner d'entrée
	 * @param jeu le jeu courant
	 * @return false : aucun choix valide n'a été fait, il faut redemander
	 */
	private boolean traiterEntreeNonEntiere(Scanner scanner, Jeu jeu) {
		String mot = scanner.next();
		if (mot.equals(TRICHE)) {
			tricher(jeu);
		} else {
			System.out.println("Vous devez donner un entier.");
		}
		return false;
	}

	/** Retirer discrètement une allumette du jeu et l'annoncer.
	 * @param jeu le jeu sur lequel tricher
	 */
	private void tricher(Jeu jeu) {
		try {
			jeu.retirer(1);
			System.out.println("[Une allumette en moins, plus que "
					+ jeu.getNombreAllumettes() + ". Chut !]");
		} catch (CoupInvalideException e) {
			System.out.println("[Impossible de tricher : " + e.getMessage() + "]");
		}
	}

}
