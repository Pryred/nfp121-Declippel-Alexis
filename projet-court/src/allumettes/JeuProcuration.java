package allumettes;

/** Procuration (patron Proxy) sur un jeu.  Elle relaie les opérations de
 * consultation vers le jeu réel mais interdit toute modification : un appel
 * à retirer lève une OperationInterditeException.  Elle permet à l'arbitre
 * de confier le jeu à un joueur sans risque de triche.
 * @author Alexis Declippel
 */
public class JeuProcuration implements Jeu {

	/** Le jeu réel vers lequel les consultations sont relayées. */
	private final Jeu sujetReel;

	/** Initialiser la procuration sur un jeu réel.
	 * @param sujetReel le jeu réel
	 */
	public JeuProcuration(Jeu sujetReel) {
		assert sujetReel != null : "Le jeu réel ne doit pas être null";
		this.sujetReel = sujetReel;
	}

	@Override
	public int getNombreAllumettes() {
		return this.sujetReel.getNombreAllumettes();
	}

	@Override
	public void retirer(int nbPrises) throws CoupInvalideException {
		throw new OperationInterditeException(
				"Un joueur ne peut pas retirer d'allumettes lui-même !");
	}

	@Override
	public String toString() {
		return this.sujetReel.toString();
	}

}
