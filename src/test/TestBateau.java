package src.test;
import src.jeu.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Cette classe effectue des tests sur les différentes méthodes de la classe Bateau.
 */
public class TestBateau {

        /**
         * Cette méthode teste la méthode IsCoordInside() de la classe Bateau.
         * @return true si la méthode IsCoordInside() a fonctionné correctement, false sinon.
         */
    @Test
    public static boolean TestCoordinBoat(){
        Bateau test1 = new Bateau(new Coord(0, 0), Orientation.VERTICAL, 5);
        Bateau test2 = new Bateau(new Coord(-4, 0), Orientation.VERTICAL, 5);
        System.out.println("Test Coordonnée touchant le bateau Commencé");
        try{
            assertTrue(test1.isCoordInside(new Coord(0, 0)));
            assertTrue(test1.isCoordInside(new Coord(1, 0)));
            assertTrue(test1.isCoordInside(new Coord(2, 0)));
            assertTrue(test1.isCoordInside(new Coord(3, 0)));
            assertTrue(test1.isCoordInside(new Coord(4, 0)));

            assertTrue(test2.isCoordInside(new Coord(0, 0)));
            assertTrue(test2.isCoordInside(new Coord(-1, 0)));
            assertTrue(test2.isCoordInside(new Coord(-2, 0)));
            assertTrue(test2.isCoordInside(new Coord(-3, 0)));
            assertTrue(test2.isCoordInside(new Coord(-4, 0)));

            assertFalse(test1.isCoordInside(new Coord(-4, 0)));;
            assertFalse(test1.isCoordInside(new Coord(-3, 0)));
            assertFalse(test1.isCoordInside(new Coord(-2, 0)));
            assertFalse(test1.isCoordInside(new Coord(-1, 0)));

            assertFalse(test2.isCoordInside(new Coord(4, 0)));
            assertFalse(test2.isCoordInside(new Coord(3, 0)));
            assertFalse(test2.isCoordInside(new Coord(2, 0)));
            assertFalse(test2.isCoordInside(new Coord(1, 0)));
        }
        catch (AssertionError e){
            System.out.println("Test Coordonnée touchant le bateau échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test Coordonnée touchant le bateau réussi");
        return true;
    }

    /**
     * Cette méthode teste la méthode IsCoordNear() de la classe Bateau.
     * @return true si la méthode IsCoordNear() a fonctionné correctement, false sinon.
     */
    @Test
    public static boolean TestCoordNextToBoat(){
        Bateau test1 = new Bateau(new Coord(0, 0), Orientation.VERTICAL, 5);
        Bateau test2 = new Bateau(new Coord(-4, 0), Orientation.VERTICAL, 5);
        System.out.println("Test Coordonnée Proche du bateau Commencé");
        try{
            assertTrue(test1.isCoordNear(new Coord(0, 0)));
            assertTrue(test1.isCoordNear(new Coord(1, 0)));
            assertTrue(test1.isCoordNear(new Coord(2, 0)));
            assertTrue(test1.isCoordNear(new Coord(3, 0)));
            assertTrue(test1.isCoordNear(new Coord(4, 0)));

            assertTrue(test2.isCoordNear(new Coord(0, 0)));
            assertTrue(test2.isCoordNear(new Coord(-1, 0)));
            assertTrue(test2.isCoordNear(new Coord(-2, 0)));
            assertTrue(test2.isCoordNear(new Coord(-3, 0)));
            assertTrue(test2.isCoordNear(new Coord(-4, 0)));
            
            assertTrue(test1.isCoordNear(new Coord(0, 1)));
            assertTrue(test1.isCoordNear(new Coord(1, 1)));
            assertTrue(test1.isCoordNear(new Coord(2, 1)));
            assertTrue(test1.isCoordNear(new Coord(3, 1)));
            assertTrue(test1.isCoordNear(new Coord(4, 1)));

            assertTrue(test2.isCoordNear(new Coord(0, 1)));
            assertTrue(test2.isCoordNear(new Coord(-1, 1)));
            assertTrue(test2.isCoordNear(new Coord(-2, 1)));
            assertTrue(test2.isCoordNear(new Coord(-3, 1)));
            assertTrue(test2.isCoordNear(new Coord(-4, 1)));

            assertFalse(test1.isCoordNear(new Coord(0, 2)));
            assertFalse(test1.isCoordNear(new Coord(1, 2)));
            assertFalse(test1.isCoordNear(new Coord(2, 2)));
            assertFalse(test1.isCoordNear(new Coord(3, 2)));
            assertFalse(test1.isCoordNear(new Coord(4, 2)));

            assertFalse(test1.isCoordNear(new Coord(0, 2)));
            assertFalse(test1.isCoordNear(new Coord(-1, 2)));
            assertFalse(test1.isCoordNear(new Coord(-2, 2)));
            assertFalse(test1.isCoordNear(new Coord(-3, 2)));
            assertFalse(test1.isCoordNear(new Coord(-4, 2)));

            assertFalse(test1.isCoordNear(new Coord(0, -2)));
            assertFalse(test1.isCoordNear(new Coord(1, -2)));
            assertFalse(test1.isCoordNear(new Coord(2, -2)));
            assertFalse(test1.isCoordNear(new Coord(3, -2)));
            assertFalse(test1.isCoordNear(new Coord(4, -2)));

            assertFalse(test1.isCoordNear(new Coord(0, -2)));
            assertFalse(test1.isCoordNear(new Coord(-1, -2)));
            assertFalse(test1.isCoordNear(new Coord(-2, -2)));
            assertFalse(test1.isCoordNear(new Coord(-3, -2)));
            assertFalse(test1.isCoordNear(new Coord(-4, -2)));

        }
        catch (AssertionError e){
            System.out.println("Test Coordonnée Proche du bateau échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test Coordonnée Proche du bateau réussi");
        return true;
    }

    /**
     * Cette méthode teste la méthode isInPlateau() de la classe Bateau.
     * @return true si la méthode isInPlateau() a fonctionné correctement, false sinon.
     */
    @Test
    public static boolean TestBoatinPlateau(){
        Bateau test1 = new Bateau(new Coord(0, 0), Orientation.VERTICAL, 5);
        Bateau test2 = new Bateau(new Coord(-4, 0), Orientation.VERTICAL, 5);
        Plateau test = new Plateau(10, 10);
        System.out.println("Test Bateau sur le Plateau Commencé");
        try{
            assertTrue(test1.isInPlateau(test));
            assertFalse(test2.isInPlateau(test));

        }
        catch (AssertionError e){
            System.out.println("Test Bateau sur le Plateau échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test Bateau sur le Plateau réussi");
        return true;

    }
    
}
