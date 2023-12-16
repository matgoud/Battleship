package src.test;
import src.modele.*;
import src.jeu.*;
import src.player.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Cette classe effectue des tests sur les différentes méthodes de la classe Coord.
 */
public class TestCoord {
    /**
     * Cette méthode teste la méthode equals() de la classe Coord.
     * @return true si la méthode equals() a fonctionné correctement, false sinon.
     */
    @Test
    public static boolean TestEquals(){
        Coord test1 = new Coord(0, 0);
        Coord test2 = new Coord(0, 0);
        Coord test3 = new Coord(1, 0);
        System.out.println("Test Egalité Commencé");
        try{
            assertTrue(test1.equals(test2));
            assertFalse(test1.equals(test3));
        }
        catch (AssertionError e){
            System.out.println("Test Egalité échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test Egalité Réussi");
        return true;
    }

    /**
     * Cette méthode teste la méthode near() de la classe Coord.
     * @return true si la méthode near() a fonctionné correctement, false sinon.
     */
    @Test
    public static boolean TestNear(){
        Coord test1 = new Coord(0, 0);
        Coord test2 = new Coord(0, 0);
        Coord test3 = new Coord(1, 0);
        Coord test4 = new Coord(0, 1);
        Coord test5 = new Coord(1, 1);
        Coord test6 = new Coord(5, 5);
        System.out.println("Test Coordonnées adjacentes Commencé");
        try{
            assertFalse(test1.near(test2));
            assertTrue(test1.near(test3));
            assertTrue(test1.near(test4));
            assertFalse(test1.near(test5));
            assertFalse(test1.near(test6));
        }
        catch (AssertionError e){
            System.out.println("Test Coordonnées adjacentes échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test Coordonnées adjacentes Réussi");
        return true;
    }

    /**
     * Cette méthode teste la méthode estAligneAvec() de la classe Coord.
     * @return true si la méthode estAligneAvec() à fonctionné correctement, false sinon.
     */
    @Test
    public static boolean TestEstAlignerAvec(){
        Coord test1 = new Coord(0, 0);
        Coord test2 = new Coord(0, 0);
        Coord test3 = new Coord(1, 0);
        Coord test4 = new Coord(0, 1);
        Coord test5 = new Coord(1, 1);
        Coord test6 = new Coord(5, 5);
        System.out.println("Test Coordonnées alignées Commencé");
        try{
            assertTrue(0==test1.estAligneAvec(test2));
            assertTrue(1==test1.estAligneAvec(test3));
            assertTrue(2==test1.estAligneAvec(test4));
            assertTrue(-1==test1.estAligneAvec(test5));
            assertTrue(-1==test1.estAligneAvec(test6));
        }
        catch (AssertionError e){
            System.out.println("Test Coordonnées alignées échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test Coordonnées alignées Réussi");
        return true;
    }
}
