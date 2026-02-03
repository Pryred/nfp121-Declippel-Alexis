import org.junit.*;
import static org.junit.Assert.*;

import java.awt.Color;

/** Tests pour les exigences E12, E13 et E14 de la classe Cercle.
 * @author Alexis Declippel
 */
public class CercleTest {
    
    private static final double EPSILON = 0.001;
    
    private Point p1;
    private Point p2;
    private Point p3;
    private Point p4;
    
    @Before
    public void setUp() {
        p1 = new Point(0, 0);
        p2 = new Point(4, 0);
        p3 = new Point(1, 1);
        p4 = new Point(5, 5);
    }
    
    @Test
    public void testE12_ConstructeurDeuxPointsDiametraux() {
        Cercle c = new Cercle(p1, p2);
        
        assertEquals(2.0, c.getCentre().getX(), EPSILON);
        assertEquals(0.0, c.getCentre().getY(), EPSILON);
        
        assertEquals(2.0, c.getRayon(), EPSILON);
        
        assertEquals(Color.blue, c.getCouleur());
    }
    
    @Test
    public void testE12_DiametreDeuxPointsDiametraux() {
        Cercle c = new Cercle(p3, p4);
        
        double distanceAttendue = p3.distance(p4);
        assertEquals(distanceAttendue, c.getDiametre(), EPSILON);
    }
    
    @Test
    public void testE13_ConstructeurDeuxPointsAvecCouleur() {
        Cercle c = new Cercle(p1, p2, Color.red);
        
        assertEquals(2.0, c.getCentre().getX(), EPSILON);
        assertEquals(0.0, c.getCentre().getY(), EPSILON);
        
        assertEquals(2.0, c.getRayon(), EPSILON);
        
        assertEquals(Color.red, c.getCouleur());
    }
    
    @Test
    public void testE13_ConstructeurCouleurVerte() {
        Cercle c = new Cercle(p3, p4, Color.green);
        
        assertEquals(Color.green, c.getCouleur());
        
        assertEquals(3.0, c.getCentre().getX(), EPSILON);
        assertEquals(3.0, c.getCentre().getY(), EPSILON);
    }
    
    @Test
    public void testE14_CreerCercle() {
        Point centre = new Point(2, 3);
        Point circonference = new Point(5, 3);
        
        Cercle c = Cercle.creerCercle(centre, circonference);
        
        assertEquals(2.0, c.getCentre().getX(), EPSILON);
        assertEquals(3.0, c.getCentre().getY(), EPSILON);
        
        assertEquals(3.0, c.getRayon(), EPSILON);
        
        assertEquals(Color.blue, c.getCouleur());
    }
    
    @Test
    public void testE14_CreerCercleRayonDiagonal() {
        Point centre = new Point(0, 0);
        Point circonference = new Point(3, 4);
        
        Cercle c = Cercle.creerCercle(centre, circonference);
        
        assertEquals(5.0, c.getRayon(), EPSILON);
        
        assertEquals(10.0, c.getDiametre(), EPSILON);
    }
}