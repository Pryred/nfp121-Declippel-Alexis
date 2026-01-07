import java.awt.Color;

/** Classe modélisant un cercle dans un plan cartésien.
 * Un cercle est défini par son centre (un point), son rayon (un réel
 * strictement positif) et sa couleur.
 * Un cercle peut être translaté, on peut vérifier si un point est
 * à l'intérieur du cercle, et on peut obtenir son périmètre et son aire.
 *
 * @author Alexis Declippel
 */
public class Cercle implements Mesurable2D {
    private Point centre;
    private double rayon;
    private Color couleur;
    private static final double PI = Math.PI;

    /** Constructeur du cercle
     * @param centre
     * @param rayon
     * @param couleur
     */
    public Cercle(Point centre, double rayon, Color couleur) {
        assert centre != null : "Le centre ne peut pas être null";
        assert rayon > 0 : "Le rayon doit être strictement positif";
        assert couleur != null : "La couleur ne peut pas être null";
        
        this.centre = centre;
        this.rayon = rayon;
        this.couleur = couleur;
    }
    
    /** Constructeur du cercle avec couleur par défaut (bleu)
     * @param centre
     * @param rayon
     */
    public Cercle(Point centre, double rayon) {
        assert centre != null : "Le centre ne peut pas être null";
        assert rayon > 0 : "Le rayon doit être strictement positif";

        this.centre = centre;
        this.rayon = rayon;
        this.couleur = Color.blue;
    }

    /** Construit un cercle à partir de deux points diamétralement opposés.
     * La couleur est bleu par défaut.
     * @param p1 premier point diamétralement opposé
     * @param p2 deuxième point diamétralement opposé
     */
    public Cercle(Point p1, Point p2) {
        assert p1 != null : "Le premier point ne peut pas être null";
        assert p2 != null : "Le deuxième point ne peut pas être null";

        this.centre = new Point((p1.getX() + p2.getX()) / 2, 
                                (p1.getY() + p2.getY()) / 2);
        this.rayon = p1.distance(p2) / 2;
        this.couleur = Color.blue;
    }

    /** Construit un cercle à partir de deux points diamétralement opposés.
     * La couleur est bleu par défaut.
     * @param p1 premier point diamétralement opposé
     * @param p2 deuxième point diamétralement opposé
     * @param couleur la couleur du cercle
     */
    public Cercle(Point p1, Point p2, Color couleur) {
        this.centre = new Point((p1.getX() + p2.getX()) / 2, 
                                (p1.getY() + p2.getY()) / 2);
        this.rayon = p1.distance(p2) / 2;
        this.couleur = couleur;
    }

    /** Retourne le centre du cercle 
     * @return le centre du cercle 
     */
    public Point getCentre() {
        return new Point(this.centre.getX(), this.centre.getY());
    }

    /** Retourne le rayon du cercle 
     * @return le rayon du cercle 
     */
    public double getRayon() {
        return this.rayon;
    }

    /** Retourne le diametre du cercle 
     * @return le diametre du cercle 
     */
    public double getDiametre() {
        return 2 * this.rayon;
    }

    /** Permet de translater le cercle
     * @param dx
     * @param dy
     */ 
    public void translater(double dx, double dy) {
        this.centre.translater(dx, dy);
    }

    /** Permet de savoir si un point est dans le cercle
     * @param p
     * @return true si le point est dans le cercle, false sinon
     */
    public boolean contient(Point p) {
        return this.centre.distance(p) <= this.rayon;
    }

    /** Obtenir le périmètre du cercle.
     * @return le périmètre
     */    
    @Override
    public double perimetre() {
        return 2 * PI * this.rayon;
    }

    /** Obtenir l'aire du cercle.
     * @return l'aire
     */
    @Override
    public double aire() {
        return PI * this.rayon * this.rayon;
    }

    /** Retourne la couleur du cercle
     * @return la couleur du cercle
     */
    public Color getCouleur() {
        return this.couleur;
    }

    /** Modifie la couleur du cercle
     * @param couleur
     */
    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    /** Crée un cercle à partir d'un centre et d'un point sur la circonférence.
     * Le rayon du cercle correspond à la distance entre les deux points.
     * La couleur est bleu par défaut.
     * @param centre le centre du cercle
     * @param pointCirconference un point situé sur la circonférence du cercle
     * @return un nouveau cercle
     */
    public static Cercle creerCercle(Point centre, Point pointCirconference) {
        double rayon = centre.distance(pointCirconference);
        return new Cercle(centre, rayon);
    }

    /** Retourne une représentation textuelle du cercle.
     * @return la chaîne de caractères représentant le cercle sous la forme Cr@(a, b)
     */
    @Override
    public String toString() {
        return "C" + this.rayon + "@(" + this.centre.getX() + ", " + this.centre.getY() + ")";
    }

    /** Modifie le rayon du cercle.
     * @param rayon le nouveau rayon (doit être strictement positif)
     */
    public void setRayon(double rayon) {
        assert rayon > 0 : "Le rayon doit être strictement positif";
        this.rayon = rayon;
    }

    /** Modifie le diamètre du cercle.
     * @param diametre le nouveau diamètre (doit être strictement positif)
     */
    public void setDiametre(double diametre) {
        assert diametre > 0 : "Le diamètre doit être strictement positif";
        this.rayon = diametre / 2;
    }
}