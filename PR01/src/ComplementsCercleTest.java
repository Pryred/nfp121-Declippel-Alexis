import org.junit.*;
import static org.junit.Assert.*;

import java.awt.Color;

/** Tests complémentaires pour la classe Cercle.
 * @author Alexis Declippel
 */
public class ComplementsCercleTest {
    
    private static final double EPSILON = 0.001;
    
    private Cercle c1;
    private Point centre;
    private static final double PI = Math.PI;
    
    @Before
    public void setUp() {
        centre = new Point(1, 2);
        c1 = new Cercle(centre, 2.5, Color.blue);
    }
    
    @Test
    public void testE15_ToString() {
        Cercle c = new Cercle(new Point(1.0, 2.0), 2.5, Color.blue);
        assertEquals("C2.5@(1.0, 2.0)", c.toString());
    }
    
    @Test
    public void testE15_ToStringAutresValeurs() {
        Cercle c = new Cercle(new Point(5.0, 10.0), 3.0, Color.red);
        assertEquals("C3.0@(5.0, 10.0)", c.toString());
    }
    
    @Test
    public void testE15_ToStringCoordonneesNegatives() {
        Cercle c = new Cercle(new Point(-2.5, -3.5), 1.5, Color.green);
        assertEquals("C1.5@(-2.5, -3.5)", c.toString());
    }
    
    @Test
    public void testE16_SetRayon() {
        c1.setRayon(5.0);
        assertEquals(5.0, c1.getRayon(), EPSILON);
        
        assertEquals(10.0, c1.getDiametre(), EPSILON);
    }
    
    @Test
    public void testE16_SetRayonModifieAireEtPerimetre() {
        c1.setRayon(3.0);
        
        assertEquals(2 * PI * 3.0, c1.perimetre(), EPSILON);
        
        assertEquals(PI * 9.0, c1.aire(), EPSILON);
    }
    
    @Test
    public void testE17_SetDiametre() {
        c1.setDiametre(10.0);
        
        assertEquals(5.0, c1.getRayon(), EPSILON);
        
        assertEquals(10.0, c1.getDiametre(), EPSILON);
    }
    
    @Test
    public void testE17_SetDiametreAutreValeur() {
        c1.setDiametre(7.0);
        assertEquals(3.5, c1.getRayon(), EPSILON);
        assertEquals(7.0, c1.getDiametre(), EPSILON);
    }
    
    @Test
    public void testE18_EncapsulationCentre() {
        Point centreObtenu = c1.getCentre();
        double xOriginal = centreObtenu.getX();
        double yOriginal = centreObtenu.getY();
        
        centreObtenu.translater(100, 100);
        
        assertEquals(xOriginal, c1.getCentre().getX(), EPSILON);
        assertEquals(yOriginal, c1.getCentre().getY(), EPSILON);
    }
    
    @Test
    public void testE18_DiametreCalculeDynamiquement() {
        c1.setRayon(4.0);
        
        assertEquals(8.0, c1.getDiametre(), EPSILON);
        
        c1.setDiametre(6.0);
        
        assertEquals(3.0, c1.getRayon(), EPSILON);
    }
    
    @Test
    public void testComplementaire_ContientApresSetRayon() {
        Cercle c = new Cercle(new Point(0, 0), 5.0, Color.blue);
        Point p = new Point(4, 0);
        
        assertTrue(c.contient(p));
        
        c.setRayon(3.0);
        assertFalse(c.contient(p));
    }
    
    @Test
    public void testComplementaire_TranslationGardeRayon() {
        double rayonInitial = c1.getRayon();
        c1.translater(10, 20);
        assertEquals(rayonInitial, c1.getRayon(), EPSILON);
    }
    
    @Test
    public void testComplementaire_AireEtPerimetreRayon1() {
        Cercle c = new Cercle(new Point(0, 0), 1.0, Color.blue);
        
        assertEquals(2 * PI, c.perimetre(), EPSILON);
        
        assertEquals(PI, c.aire(), EPSILON);
    }
    
    @Test
    public void testComplementaire_ConstructeurCouleurParDefaut() {
        Cercle c = new Cercle(new Point(0, 0), 5.0);
        assertEquals(Color.blue, c.getCouleur());
    }
    
    @Test
    public void testComplementaire_ModificationCouleur() {
        c1.setCouleur(Color.red);
        assertEquals(Color.red, c1.getCouleur());
        
        c1.setCouleur(Color.green);
        assertEquals(Color.green, c1.getCouleur());
    }
    
    @Test
    public void testComplementaire_CreerCerclePuisModifier() {
        Cercle c = Cercle.creerCercle(new Point(0, 0), new Point(3, 0));
        assertEquals(3.0, c.getRayon(), EPSILON);
        
        c.setRayon(5.0);
        assertEquals(5.0, c.getRayon(), EPSILON);
        
        c.setCouleur(Color.yellow);
        assertEquals(Color.yellow, c.getCouleur());
    }
    
    @Test
    public void testComplementaire_DiametreCoherentDeuxPoints() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(6, 0);
        
        Cercle c = new Cercle(p1, p2);
        
        assertEquals(6.0, c.getDiametre(), EPSILON);
        assertEquals(3.0, c.getRayon(), EPSILON);
    }
}