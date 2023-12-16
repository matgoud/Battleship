package src.test;
import src.modele.*;
import src.jeu.*;
import src.player.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Cette classe effectue des tests sur les différentes méthodes de la classe RandomPlayer.
 */

public class TestRandomPlayer{


    /**
     * Cette méthode teste la méthode generatePossibleCases() de la classe RandomPlayer.
     * @return true si la méthode generatePossibleCases() a fonctionné correctement, false sinon.
     */
    @Test
    public static boolean TestGeneratePossibleCases(){
        RandomPlayer p1 = new RandomPlayer("mega man");
        int totalCase= 100;
        System.out.println("Test Génération des cases possibles Commencé");
        try {
            assertTrue(0==p1.getPcases().size());
            assertFalse(0!=p1.getPcases().size());
            p1.generatePossibleCases();
            assertFalse(0==p1.getPcases().size());
            assertTrue(totalCase==p1.getPcases().size());

        } catch (AssertionError e) {
            System.out.println("Test Génération des cases possibles échoué");
            e.printStackTrace();
            return false;    
        }

        System.out.println("Test Génération des cases possibles Réussi");
        return true;
    }

    /**
     * Cette méthode teste la méthode coup() de la classe RandomPlayer.
     * @return true si la méthode coup() a fonctionné correctement, false sinon.
     */
    @Test
    public static boolean TestCoup(){
        RandomPlayer p1 = new RandomPlayer("mega man");
        int totalCase =100;
        System.out.println("Test Coup Commencé");
        try {
            p1.generatePossibleCases();
            Coord C1 = p1.coup(10, 10);
            assertTrue(C1.equals(new Coord(9, 9)));
            assertFalse(totalCase==p1.getPcases().size());
            assertTrue(totalCase-1==p1.getPcases().size());
            Coord C2 = p1.coup(0,0);
            assertTrue(totalCase-2==p1.getPcases().size());
            assertFalse(totalCase==p1.getPcases().size());

            
            
        } catch (AssertionError e) {
            System.out.println("Test Coup échoué");
            e.printStackTrace();
            return false;
        }

        System.out.println("Test Coup Réussi");
        return true;
    }
}