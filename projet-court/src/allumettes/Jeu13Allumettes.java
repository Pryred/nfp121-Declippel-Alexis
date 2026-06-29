package allumettes;

/** Plateau du jeu des allumettes.  Le jeu retient le nombre d'allumettes
 * encore en jeu et fait respecter les règles de prise.
 * @author Alexis Declippel
 */
public class Jeu13Allumettes implements Jeu {

	/** Nombre d'allumettes encore en jeu. */
	private int nombreAllumettes;

	/** Initialiser le jeu avec un nombre d'allumettes donné.
	 * @param nombreAllumettes nombre initial d'allumettes
	 */
	public Jeu13Allumettes(int nombreAllumettes) {
		assert nombreAllumettes > 0 : "Il faut au moins une allumette";
		this.nombreAllumettes = nombreAllumettes;
	}

	@Override
	public int getNombreAllumettes() {
		return this.nombreAllumettes;
	}

	@Override
	public void retirer(int nbPrises) throws CoupInvalideException {
		if (nbPrises < 1) {
			throw new CoupInvalideException(nbPrises, "< 1");
		}
		if (nbPrises > this.nombreAllumettes) {
			throw new CoupInvalideException(nbPrises, "> " + this.nombreAllumettes);
		}
		if (nbPrises > PRISE_MAX) {
			throw new CoupInvalideException(nbPrises, "> " + PRISE_MAX);
		}
		this.nombreAllumettes -= nbPrises;
	}

	@Override
	public String toString() {
		return "Jeu13Allumettes[" + this.nombreAllumettes + "]";
	}

}
