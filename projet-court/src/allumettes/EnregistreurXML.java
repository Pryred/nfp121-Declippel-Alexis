package allumettes;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/** Classe chargée de générer le fichier XML du déroulement de la partie.
 * @author Alexis Declippel
 */
public class EnregistreurXML {

	/** Liste des balises correspondant aux coups joués. */
	private final List<String> coups = new ArrayList<>();
	
	/** Numéro du coup en cours. */
	private int numeroCoup = 1;

	/** Enregistrer un coup valide.
	 * @param nom le nom du joueur
	 * @param prise le nombre d'allumettes prises
	 */
	public void enregistrerCoup(String nom, int prise) {
		this.coups.add("  <coup numero=\"" + (this.numeroCoup++) 
				+ "\" joueur=\"" + nom + "\" prise=\"" + prise + "\"/>");
	}

	/** Générer le fichier deroulement.xml.
	 * @param gagnant le nom du vainqueur (null si triche)
	 * @param tricheur le nom du tricheur (null si pas de triche)
	 */
	public void ecrireFichier(String gagnant, String tricheur) {
		try (PrintWriter out = new PrintWriter(new FileWriter("deroulement.xml"))) {
			out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			out.println("<!DOCTYPE partie SYSTEM \"deroulement.dtd\">");
			out.println("<partie>");
			for (String coup : this.coups) {
				out.println(coup);
			}
			if (tricheur != null) {
				out.println("  <resultat tricheur=\"" + tricheur + "\"/>");
			} else {
				out.println("  <resultat vainqueur=\"" + gagnant + "\"/>");
			}
			out.println("</partie>");
		} catch (IOException e) {
			System.out.println("Erreur lors de la génération du XML : " + e.getMessage());
		}
	}

}