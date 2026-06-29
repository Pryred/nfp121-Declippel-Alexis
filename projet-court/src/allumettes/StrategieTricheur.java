package allumettes;

/** Stratégie tricheur : le joueur retire directement des allumettes du jeu
 * pour n'en laisser que deux, puis annonce qu'il n'en prend qu'une seule,
 * s'assurant ainsi la victoire.
 * @author Alexis Declippel
 */
public class StrategieTricheur implements Strategie {

	/** Nombre d'allumettes que le tricheur veut laisser avant de prendre. */
	private static final int ALLUMETTES_VISEES = 2;

	@Override
	public int getPrise(Jeu jeu, String nomJoueur) {
		assert jeu != null : "Le jeu ne doit pas être null";
		System.out.println("[Je triche...]");
		retirerJusqua(jeu, ALLUMETTES_VISEES);
		System.out.println("[Allumettes restantes : "
				+ jeu.getNombreAllumettes() + "]");
		return 1;
	}

	/** Retirer des allumettes du jeu, par prises valides, jusqu'à atteindre
	 * le nombre cible.
	 * @param jeu le jeu sur lequel tricher
	 * @param cible nombre d'allumettes à laisser
	 */
	private void retirerJusqua(Jeu jeu, int cible) {
		try {
			while (jeu.getNombreAllumettes() > cible) {
				int reste = jeu.getNombreAllumettes() - cible;
				jeu.retirer(Math.min(Jeu.PRISE_MAX, reste));
			}
		} catch (CoupInvalideException e) {
			// Ne devrait pas arriver : les prises restent valides.
			throw new IllegalStateException(e);
		}
	}

}
