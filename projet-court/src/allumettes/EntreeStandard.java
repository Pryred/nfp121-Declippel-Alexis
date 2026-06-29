package allumettes;

import java.util.Scanner;

/** Fournit l'unique Scanner de l'application lisant l'entrée standard.
 * Un seul scanner doit exister car un Scanner fait des lectures en avance
 * qui consommeraient l'entrée d'un éventuel second scanner.
 * @author Alexis Declippel
 */
public final class EntreeStandard {

	/** L'unique scanner sur l'entrée standard. */
	private static final Scanner SCANNER = new Scanner(System.in);

	/** Constructeur privé : classe utilitaire non instanciable. */
	private EntreeStandard() {
	}

	/** Obtenir l'unique scanner de l'application.
	 * @return le scanner sur l'entrée standard
	 */
	public static Scanner getScanner() {
		return SCANNER;
	}

}
