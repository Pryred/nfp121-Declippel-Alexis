package allumettes;

/** Exception levée lorsqu'un joueur tente une opération interdite sur le jeu,
 * typiquement retirer des allumettes en passant par la procuration.
 * Elle n'est pas vérifiée par le compilateur car la procuration implémente
 * l'interface Jeu, dont la méthode retirer ne déclare que CoupInvalideException.
 * @author Alexis Declippel
 */
public class OperationInterditeException extends RuntimeException {

	/** Initialiser l'exception avec un message explicatif.
	 * @param message le message explicatif
	 */
	public OperationInterditeException(String message) {
		super(message);
	}

}
