package src.test;
import src.modele.*;
import src.jeu.*;
import src.player.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Cette classe effectue des tests sur la classe AbstractPlayer.
 */
public class TestAbstractPlayer {
    
    /**
     * Cette méthode teste la méthode setLastHitCoule() et la méthode setLastHitTouche() de la classe AbstractPlayer.
     * @return true si les méthodes sont effectuées correctement, false sinon.
     */
    @Test
    public static boolean TestSetLastHit(){
        AbstractPlayer H = new Humain("Humain");
        AbstractPlayer R = new RandomPlayer("Random");
        AbstractPlayer IA = new IAPlayer("IA");
        System.out.println("Test Dernier Coup Commencé");
        try {
            assertTrue(!H.isLastHitCoule());
            assertTrue(!H.isLastHitTouche());
            assertTrue(!R.isLastHitCoule());
            assertTrue(!R.isLastHitTouche());
            assertTrue(!IA.isLastHitCoule());
            assertTrue(!IA.isLastHitTouche());
            H.setLastHitCoule(true);
            H.setLastHitTouche(true);
            R.setLastHitCoule(true);
            R.setLastHitTouche(true);
            IA.setLastHitCoule(true);
            IA.setLastHitTouche(true);
            assertTrue(H.isLastHitCoule());
            assertTrue(H.isLastHitTouche());
            assertTrue(R.isLastHitCoule());
            assertTrue(R.isLastHitTouche());
            assertTrue(IA.isLastHitCoule());
            assertTrue(IA.isLastHitTouche());



        } catch (AssertionError e) {
            System.out.println("Test Dernier Coup échoué");
            e.printStackTrace();
            return false;    
        }

        System.out.println("Test Dernier Coup Réussi");
        return true;

    }
}
