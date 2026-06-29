package allumettes;

/** Lance une partie des 13 allumettes en fonction des arguments fournis
 * sur la ligne de commande.
 * @author	Xavier Crégut
 * @version	$Revision: 1.5 $
 */
public class Jouer {

	/** Nombre initial d'allumettes du jeu. */
	private static final int NB_ALLUMETTES = 13;

	/** Option rendant l'arbitre confiant. */
	private static final String OPTION_CONFIANT = "-confiant";

	/** Lancer une partie. En argument sont donnés les deux joueurs sous
	 * la forme nom@stratégie.
	 * @param args la description des deux joueurs
	 */
	public static void main(String[] args) {
		try {
			verifierNombreArguments(args);

			boolean confiant = args[0].equals(OPTION_CONFIANT);
			if (args.length == 3 && !confiant) {
				throw new ConfigurationException(
						"Option inconnue (attendu " + OPTION_CONFIANT + ") : "
						+ args[0]);
			}
			int debut = confiant ? 1 : 0;

			Joueur joueur1 = creerJoueur(args[debut]);
			Joueur joueur2 = creerJoueur(args[debut + 1]);

			Arbitre arbitre = new Arbitre(joueur1, joueur2);
			arbitre.setConfiant(confiant);
			arbitre.arbitrer(new Jeu13Allumettes(NB_ALLUMETTES));

		} catch (ConfigurationException e) {
			System.out.println();
			System.out.println("Erreur : " + e.getMessage());
			afficherUsage();
			System.exit(1);
		}
	}

	/** Créer un joueur à partir de sa description « nom@stratégie ».
	 * @param description la description du joueur
	 * @return le joueur créé
	 */
	private static Joueur creerJoueur(String description) {
		String[] morceaux = description.split("@");
		if (morceaux.length != 2) {
			throw new ConfigurationException(
					"Joueur incorrect (nom@stratégie attendu) : " + description);
		}
		String nom = morceaux[0];
		String nomStrategie = morceaux[1];
		return new Joueur(nom, creerStrategie(nomStrategie));
	}

	/** Créer la stratégie correspondant au nom donné.
	 * @param nomStrategie le nom de la stratégie
	 * @return la stratégie créée
	 */
	private static Strategie creerStrategie(String nomStrategie) {
		switch (nomStrategie) {
			case "humain":
				return new StrategieHumain();
			case "naif":
				return new StrategieNaif();
			case "rapide":
				return new StrategieRapide();
			case "expert":
				return new StrategieExpert();
			case "tricheur":
				return new StrategieTricheur();
			case "supertricheur":
				return new StrategieSupertricheur();
			case "swing":
				return new StrategieSwing();
			default:
				throw new ConfigurationException(
						"Stratégie inconnue : " + nomStrategie);
		}
	}

	private static void verifierNombreArguments(String[] args) {
		final int nbJoueurs = 2;
		if (args.length < nbJoueurs) {
			throw new ConfigurationException("Trop peu d'arguments : "
					+ args.length);
		}
		if (args.length > nbJoueurs + 1) {
			throw new ConfigurationException("Trop d'arguments : "
					+ args.length);
		}
	}

	/** Afficher des indications sur la manière d'exécuter cette classe. */
	public static void afficherUsage() {
		System.out.println("\n" + "Usage :"
				+ "\n\t" + "java allumettes.Jouer joueur1 joueur2"
				+ "\n\t\t" + "joueur est de la forme nom@stratégie"
				+ "\n\t\t" + "strategie = naif | rapide | expert | humain | tricheur"
				+ "\n"
				+ "\n\t" + "Exemple :"
				+ "\n\t" + "	java allumettes.Jouer Xavier@humain "
					   + "Ordinateur@naif"
				+ "\n"
				);
	}

}
