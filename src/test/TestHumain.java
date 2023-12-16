package src.test;
import src.modele.*;
import src.jeu.*;
import src.player.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Cette classe effectue les tests de la classe Humain.
 */
public class TestHumain {

    /**
     * Cette méthode teste la méthode coup() de la classe Humain.
     * @return true si la méthode coup() a fonctionné correctement, false sinon.
     */
    @Test
    public static boolean TestCoup(){
        Humain p1 = new Humain("mega man");
        Coord verif1 = p1.coup(0, 0);
        Coord verif2 = p1.coup(9, 9);
        Coord verif3 = p1.coup(10, 10);
        System.out.println("Test Coup Commencé");
        try{
            Coord test1_1 = new Coord(0, 0);
            Coord test2_1 =new Coord(1, 0);
            Coord test3_1 =new Coord(1, 1);
            Coord test4_1 =new Coord(0, 1);
            Coord test1_2 = new Coord(10, 10);
            Coord test2_2 =new Coord(9, 10);
            Coord test3_2 =new Coord(9, 9);
            Coord test4_2 =new Coord(0, 9);
            Coord test1_3 = new Coord(-1, -1);
            Coord test2_3 =new Coord(9, 0);
            Coord test3_3 =new Coord(9, 1);
            Coord test4_3 =new Coord(0, 1);


            assertTrue(test1_1.equals(verif1));
            assertFalse(test2_1.equals(verif1));
            assertFalse(test3_1.equals(verif1));
            assertFalse(test4_1.equals(verif1));
            assertFalse(test1_2.equals(verif2));
            assertFalse(test2_2.equals(verif2));
            assertTrue(test3_2.equals(verif2));
            assertFalse(test4_2.equals(verif2));
            assertTrue(test1_3.equals(verif3));
            assertFalse(test2_3.equals(verif3));
            assertFalse(test3_3.equals(verif3));
            assertFalse(test4_3.equals(verif3));


        }
        catch (AssertionError e){
            System.out.println("Test Coup échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test Coup Réussi");
        return true;
    }
    
}
