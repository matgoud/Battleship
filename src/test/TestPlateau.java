package src.test;
import src.jeu.*;
import src.player.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Cette classe effectue des tests sur les différentes méthodes de la classe Plateau.
 */
public class TestPlateau {
    
    
    /**
     * Cette méthode teste la méthode setOwner() de la classe Plateau.
     * @return true si la méthode setOwner() a fonctionné correctement, false sinon.
     */
    @Test
    public static boolean TestSetOwner(){
        Plateau test1 = new Plateau(10, 10);
        Plateau test2 = new Plateau(10, 10);
        Joueur jtest1 = new Humain("test1");
        Joueur jtest2 = new Humain("test2");
        test1.setOwner(jtest2);
        test2.setOwner(jtest1);
        
        System.out.println("Test Propriété Commencé");
        try{
            assertEquals("test2",test1.getOwner().getName());
            assertFalse("test1"==test1.getOwner().getName());
        }
        catch (AssertionError e){
            System.out.println("Test Propriété échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test Propriété Réussi");
        return true;
    }

    /**
     * Cette méthode teste la méthode ajoutBateau() de la classe Plateau.
     * @return true si la méthode ajoutBateau() a fonctionné correctement, false sinon.
     */
    @Test
    public static boolean TestAjoutBateau(){
        Bateau Boat1 = new Bateau(new Coord(0, 0), Orientation.VERTICAL, 5);
        Bateau Boat2 = new Bateau(new Coord(5, 0), Orientation.VERTICAL, 4);
        Plateau test1 = new Plateau(10, 10);
        Plateau test2 = new Plateau(10, 10);
        System.out.println("Test Ajout de Bateau Commencé");
        test1.ajoutBateau(Boat1);
        test2.ajoutBateau(Boat2);
        try{
            assertTrue(StateShot.SHIP==test1.getTabYX(0, 0));
            assertTrue(StateShot.SHIP==test1.getTabYX(1, 0));
            assertTrue(StateShot.SHIP==test1.getTabYX(2, 0));
            assertTrue(StateShot.SHIP==test1.getTabYX(3, 0));
            assertTrue(StateShot.SHIP==test1.getTabYX(4, 0));

            assertFalse(StateShot.SHIP==test1.getTabYX(5, 0));
            assertFalse(StateShot.SHIP==test1.getTabYX(6, 0));
            assertFalse(StateShot.SHIP==test1.getTabYX(7, 0));
            assertFalse(StateShot.SHIP==test1.getTabYX(8, 0));

            assertTrue(StateShot.SHIP==test2.getTabYX(5, 0));
            assertTrue(StateShot.SHIP==test2.getTabYX(6, 0));
            assertTrue(StateShot.SHIP==test2.getTabYX(7, 0));
            assertTrue(StateShot.SHIP==test2.getTabYX(8, 0));

            assertFalse(StateShot.SHIP==test2.getTabYX(0, 0));
            assertFalse(StateShot.SHIP==test2.getTabYX(1, 0));
            assertFalse(StateShot.SHIP==test2.getTabYX(2, 0));
            assertFalse(StateShot.SHIP==test2.getTabYX(3, 0));
            assertFalse(StateShot.SHIP==test2.getTabYX(4, 0));

        }
        catch (AssertionError e){
            System.out.println("Test Ajout de Bateau échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test Ajout de Bateau Réussi");
        return true;
    }

    /**
     * Cette méthode teste la méthode retraitBateau() de la classe Plateau.
     * @return true si la méthode retraitBateau() a fonctionné correctement, false sinon.
     */
    @Test
    public static boolean TestRetraitBateau(){

        Bateau Boat1 = new Bateau(new Coord(0, 0), Orientation.VERTICAL, 5);
        Bateau Boat2 = new Bateau(new Coord(5, 0), Orientation.VERTICAL, 4);
        Plateau test1 = new Plateau(10, 10);
        Plateau test2 = new Plateau(10, 10);
        System.out.println("Test Retrait de Bateau Commencé");
        test1.ajoutBateau(Boat1);
        test1.ajoutBateau(Boat2);
        test2.ajoutBateau(Boat2);
        test1.retraitBateau(0);
        try{
            assertFalse(StateShot.SHIP==test1.getTabYX(0, 0));
            assertFalse(StateShot.SHIP==test1.getTabYX(1, 0));
            assertFalse(StateShot.SHIP==test1.getTabYX(2, 0));
            assertFalse(StateShot.SHIP==test1.getTabYX(3, 0));
            assertFalse(StateShot.SHIP==test1.getTabYX(4, 0));

            assertTrue(StateShot.SHIP==test1.getTabYX(5, 0));
            assertTrue(StateShot.SHIP==test1.getTabYX(6, 0));
            assertTrue(StateShot.SHIP==test1.getTabYX(7, 0));
            assertTrue(StateShot.SHIP==test1.getTabYX(8, 0));

            assertTrue(StateShot.SHIP==test2.getTabYX(5, 0));
            assertTrue(StateShot.SHIP==test2.getTabYX(6, 0));
            assertTrue(StateShot.SHIP==test2.getTabYX(7, 0));
            assertTrue(StateShot.SHIP==test2.getTabYX(8, 0));

            assertFalse(StateShot.SHIP==test2.getTabYX(0, 0));
            assertFalse(StateShot.SHIP==test2.getTabYX(1, 0));
            assertFalse(StateShot.SHIP==test2.getTabYX(2, 0));
            assertFalse(StateShot.SHIP==test2.getTabYX(3, 0));
            assertFalse(StateShot.SHIP==test2.getTabYX(4, 0));

        }
        catch (AssertionError e){
            System.out.println("Test Retrait de Bateau échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test Retrait de Bateau Réussi");
        return true;
    
    }

    /**
     * Cette méthode teste la méthode plusDeBateau() de la classe Plateau.
     * @return true si la méthode plusDeBateau() a fonctionné correctement, false sinon.
     */
    @Test
    public static boolean TestPlusDeBateau(){
        Bateau Boat1 = new Bateau(new Coord(0, 0), Orientation.VERTICAL, 5);
        Bateau Boat2 = new Bateau(new Coord(5, 0), Orientation.VERTICAL, 4);
        Plateau test1 = new Plateau(10, 10);
        Plateau test2 = new Plateau(10, 10);
        System.out.println("Test Tous les Bateaux sont coulés Commencé");
        test1.ajoutBateau(Boat1);
        test1.ajoutBateau(Boat2);
        test2.ajoutBateau(Boat1);
        test2.ajoutBateau(Boat2);

        test1.setTabYX(0, 0, StateShot.SHIP_HIT);
        test1.setTabYX(1, 0, StateShot.SHIP_HIT);
        test1.setTabYX(2, 0, StateShot.SHIP_HIT);
        test1.setTabYX(3, 0, StateShot.SHIP_HIT);
        test1.setTabYX(4, 0, StateShot.SHIP_HIT);

        test1.setTabYX(5, 0, StateShot.SHIP_HIT);
        test1.setTabYX(6, 0, StateShot.SHIP_HIT);
        test1.setTabYX(7, 0, StateShot.SHIP_HIT);
        test1.setTabYX(8, 0, StateShot.SHIP_HIT);

        test2.setTabYX(0, 0, StateShot.SHIP_HIT);
        test2.setTabYX(1, 0, StateShot.SHIP_HIT);
        test2.setTabYX(2, 0, StateShot.SHIP_HIT);
        test2.setTabYX(3, 0, StateShot.SHIP_HIT);
        test2.setTabYX(4, 0, StateShot.SHIP_HIT);

        try{
            assertTrue(test1.plusDeBateaux());
            assertFalse(test2.plusDeBateaux());
        }
        catch (AssertionError e){
            System.out.println("Test Tous les Bateaux sont coulés échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test Tous les Bateaux sont coulés Réussi");
        return true;
    }

}