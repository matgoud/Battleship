package src.test;
import src.modele.*;
import src.jeu.*;
import src.player.*;
import org.junit.*;
import static org.junit.Assert.*;


/**
 * Cette classe effectue des tests sur les différentes méthodes de la classe IAPlayer.
 */
public class TestIAPlayer {
    

    /**
     * Cette méthode teste la méthode generatePossibleCases() de la classe IAPlayer.
     * @return true si la méthode generatePossibleCases() a fonctionné correctement, false sinon.
     */
    @Test
    public static boolean TestGeneratePossibleCases(){
        IAPlayer p1 = new IAPlayer("mega man");
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
     * Cette méthode teste la méthode initCasesAdjLibres() de la classe IAPlayer.
     * @return true si la méthode initCasesAdjLibres() a fonctionné correctement, false sinon.
     */
    @Test
    public static boolean TestInitCasesAdjLibres(){
        IAPlayer p1 = new IAPlayer("mega man");
        int verif = (8*4)*3+4*2+64*4;
        System.out.println("Test Initialisation des cases Adjacentes Libres Commencé");
        try {
            assertTrue(verif==p1.getTotCAL());
            assertFalse(0==p1.getTotCAL());

        } catch (AssertionError e) {
            System.out.println("Test Initialisation des cases Adjacentes Libres échoué");
            e.printStackTrace();
            return false;    
        }

        System.out.println("Test Initialisation des cases Adjacentes Libres Réussi");
        return true;
    }

    /**
     * Cette méthode teste la méthode removeCasePossible() de la classe IAPlayer.
     * @return true si la méthode removeCasePossible() a fonctionné correctement, false sinon.
     */
    @Test
    public static boolean TestRemoveCasePossible(){
        IAPlayer p1 = new IAPlayer("mega man");
        int verif = (8*4)*3+4*2+64*4;
        int totalCase = 100;
        System.out.println("Test Retrait de case Possible Commencé");

        try {
            p1.generatePossibleCases();
            p1.removeCasePossible(new Coord(0, 0));
            assertTrue(totalCase-1==p1.getPcases().size());
            assertTrue(verif-4==p1.getTotCAL());
            assertFalse(totalCase==p1.getPcases().size());
            p1.removeCasePossible(new Coord(0, 8));
            totalCase-=1;
            verif-=4;
            assertTrue(totalCase-1==p1.getPcases().size());
            assertTrue(verif-6==p1.getTotCAL());
            p1.removeCasePossible(new Coord(5, 5));
            totalCase-=1;
            verif-=6;
            assertTrue(totalCase-1==p1.getPcases().size());
            assertTrue(verif-8==p1.getTotCAL());

        } catch (AssertionError e) {
            System.out.println("Test Retrait de case Possible échoué");
            e.printStackTrace();
            return false;    
        }

        System.out.println("Test Retrait de case Possible Réussi");
        return true;
    }

    /**
     * Cette méthode teste la méthode coupInit() de la classe IAPlayer.
     * @return true si la méthode coupInit() a fonctionné correctement, false sinon.
     */
    @Test
    public static boolean TestCoupInit(){
        IAPlayer p1 = new IAPlayer("mega man");
        p1.generatePossibleCases();
        p1.getPosRepere().add(new Coord(0, 0));
        p1.getPosRepere().add(new Coord(0, 1));
        p1.getPosRepere().add(new Coord(0, 2));
        p1.getPcasesAdj().add(new Coord(0, 0));
        p1.getPcasesAdj().add(new Coord(0, 1));
        p1.getPcasesAdj().add(new Coord(0, 2));
        p1.setLastHitCoule(true);
        p1.setLastHitTouche(true);
        System.out.println("Test Réinitialisation des variables Commencé");
        try {
            assertTrue(p1.isLastHitCoule());
            assertTrue(p1.isLastHitTouche());
            assertTrue(p1.getPosRepere().size()!=0);
            assertTrue(p1.getPcasesAdj().size()!=0);
            Coord C = p1.coupInit();
            assertFalse(p1.isLastHitCoule());
            assertFalse(p1.isLastHitTouche());
            assertTrue(p1.getPosRepere().size()==0);
            assertTrue(p1.getPcasesAdj().size()==0);

        } catch (AssertionError e) {
            System.out.println("Test Réinitialisation des variables échoué");
            e.printStackTrace();
            return false;    
        }

        System.out.println("Test Réinitialisation des variables Réussi");
        return true;
        
    }

    /**
     * Cette méthode teste la méthode coup() de la classe IAPlayer.
     * @return true si la méthode coup() a fonctionné correctement, false sinon.
     */
    @Test
    public static boolean TestCoup(){
        IAPlayer p1 = new IAPlayer("mega man");
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
