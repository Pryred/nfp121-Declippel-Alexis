package allumettes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** Tests unitaires de la stratégie rapide.  La stratégie rapide prend le
 * maximum d'allumettes possible, donc PRISE_MAX si le jeu en contient assez,
 * sinon toutes les allumettes restantes.
 * @author Alexis Declippel
 */
public class StrategieRapideTest {

	/** La stratégie testée. */
	private final Strategie strategie = new StrategieRapide();

	/** Beaucoup d'allumettes : on prend le maximum autorisé. */
	@Test
	public void testBeaucoupAllumettes() {
		Jeu jeu = new Jeu13Allumettes(13);
		assertEquals(Jeu.PRISE_MAX, strategie.getPrise(jeu, "Test"));
	}

	/** Exactement PRISE_MAX allumettes : on prend tout. */
	@Test
	public void testExactementPriseMax() {
		Jeu jeu = new Jeu13Allumettes(Jeu.PRISE_MAX);
		assertEquals(Jeu.PRISE_MAX, strategie.getPrise(jeu, "Test"));
	}

	/** Une de plus que PRISE_MAX : on prend PRISE_MAX. */
	@Test
	public void testUnDeTropPourLeMaximum() {
		Jeu jeu = new Jeu13Allumettes(Jeu.PRISE_MAX + 1);
		assertEquals(Jeu.PRISE_MAX, strategie.getPrise(jeu, "Test"));
	}

	/** Deux allumettes restantes : on prend les deux. */
	@Test
	public void testDeuxAllumettes() {
		Jeu jeu = new Jeu13Allumettes(2);
		assertEquals(2, strategie.getPrise(jeu, "Test"));
	}

	/** Une seule allumette restante : on la prend. */
	@Test
	public void testUneAllumette() {
		Jeu jeu = new Jeu13Allumettes(1);
		assertEquals(1, strategie.getPrise(jeu, "Test"));
	}

}
