package src.test;
import src.modele.*;
import src.jeu.*;
import src.player.*;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
/**
 * Cette classe effectue des tests sur les différentes méthodes de la classe Bataille.
 */
public class TestBataille {
    

        /**
         * Cette méthode teste la méthode attackP() de la classe Bataille.
         * @return true si la méthode attackP() a fonctionné correctement, false sinon.
         */
    @Test
    public static boolean TestAttack(){
        Joueur p1 = new Humain("mega man");
        Joueur p2 = new RandomPlayer("terminator");
        Bataille test1 = new Bataille(p1,p2);

        System.out.println("Test Attaque Commencé");
        try{
            ArrayList<Coord> valide = new ArrayList<Coord>();
            for (int i =0;i<10;i++){
                for (int j =0;j<10;j++){
                    test1.attackP(new Coord(i, j), test1.getp2(),test1.getp1());
                    if (test1.getp1().getPlateau().getTabYX(i, j)==StateShot.SHIP_HIT){
                        valide.add(new Coord(i, j));
                    }
                }

            }
            for (int k = 0; k<valide.size();k++){
                Coord C = valide.get(k);
                assertTrue(StateShot.SHIP_HIT==test1.getp1().getPlateau().getTabYX(C.Y, C.X));
                assertFalse(StateShot.SHIP_HIT==test1.getp2().getPlateau().getTabYX(C.Y, C.X));
            }
            

        }
        catch (AssertionError e){
            System.out.println("Test Attaque échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test Attaque réussi");
        return true;
    }

    /**
     * Cette méthode teste la méthode isOver() de la classe Bataille.
     * @return true si la méthode isOver() a fonctionné correctement, false sinon.
     */
    @Test
    public static boolean TestIsOver(){
        Joueur p1 = new Humain("mega man");
        Joueur p2 = new RandomPlayer("terminator");
        Bataille test1 = new Bataille(p1,p2);

        System.out.println("Test Terminée Commencé");
        try{
            ArrayList<Coord> valide = new ArrayList<Coord>();
            for (int i =0;i<10;i++){
                for (int j =0;j<10;j++){
                    test1.attackP(new Coord(i, j), test1.getp2(),test1.getp1());
                    if (test1.getp1().getPlateau().getTabYX(i, j)==StateShot.SHIP_HIT){
                        valide.add(new Coord(i, j));
                    }
                }
            }
            boolean verif =test1.isOver();
            assertTrue(verif);
            assertTrue(test1.getCurrentP()==test1.getp2());
            assertFalse(test1.getCurrentP()==test1.getp1());
        }
        catch (AssertionError e){
            System.out.println("Test Terminée échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test Terminée réussi");
        return true;
    }
}
