package src;
import src.test.*;



/**
 * Cette Classe exécute tous les tests de la Bataille navale afin de s'assurer du bon fonctionnement de toutes les méthodes.
 */
public class maintest {
    
    public static void main(String[] args){
        int bon =0;
        boolean ok = true;
        System.out.println("Test Coord");
        TestCoord testCoord = new TestCoord();
        ok = ok && testCoord.TestEquals();
        ok = ok && testCoord.TestNear();
        ok = ok && testCoord.TestEstAlignerAvec();
        if(ok) bon+=1;
        System.out.println(ok ? "All_test_OK" : "At_least_one_test_KO");
        ok = true;
        System.out.println("Test Bateau");
        TestBateau testBateau = new TestBateau();
        ok = ok && testBateau.TestCoordinBoat();
        ok = ok && testBateau.TestCoordNextToBoat();
        ok = ok && testBateau.TestBoatinPlateau();
        if(ok) bon+=1;
        System.out.println(ok ? "All_test_OK" : "At_least_one_test_KO");
        ok = true;
        System.out.println("Test Plateau");
        TestPlateau testPlateau = new TestPlateau();
        ok = ok && testPlateau.TestSetOwner();
        ok = ok && testPlateau.TestAjoutBateau();
        ok = ok && testPlateau.TestRetraitBateau();
        ok = ok && testPlateau.TestPlusDeBateau();
        if(ok) bon+=1;
        System.out.println(ok ? "All_test_OK" : "At_least_one_test_KO");
        ok = true;
        System.out.println("Test AbstractPlayer");
        TestAbstractPlayer testAbstractPlayer = new TestAbstractPlayer();
        ok = ok && testAbstractPlayer.TestSetLastHit();
        if(ok) bon+=1; 
        System.out.println(ok ? "All_test_OK" : "At_least_one_test_KO");
        ok = true;
        System.out.println("Test Humain");
        TestHumain testHumain = new TestHumain();
        ok = ok && testHumain.TestCoup();
        if(ok) bon+=1; 
        System.out.println(ok ? "All_test_OK" : "At_least_one_test_KO");
        ok = true;
        System.out.println("Test RandomPlayer");
        TestRandomPlayer testRandomPlayer = new TestRandomPlayer();
        ok = ok && testRandomPlayer.TestGeneratePossibleCases();
        ok = ok && testRandomPlayer.TestCoup();
        if(ok) bon+=1; 
        System.out.println(ok ? "All_test_OK" : "At_least_one_test_KO");
        ok = true;
        System.out.println("Test IAPlayer");
        TestIAPlayer testIAPlayer = new TestIAPlayer();
        ok = ok && testIAPlayer.TestGeneratePossibleCases();
        ok = ok && testIAPlayer.TestInitCasesAdjLibres();
        ok = ok && testIAPlayer.TestRemoveCasePossible();
        ok = ok && testIAPlayer.TestCoupInit();
        ok = ok && testIAPlayer.TestCoup();
        if(ok) bon+=1;
        System.out.println(ok ? "All_test_OK" : "At_least_one_test_KO");
        ok = true;
        System.out.println("Test Bataille");
        TestBataille testBataille = new TestBataille();
        ok = ok && testBataille.TestAttack();
        ok = ok && testBataille.TestIsOver();
        if(ok) bon+=1; 
        System.out.println(ok ? "All_test_OK" : "At_least_one_test_KO");

        if(bon==8) System.out.println("TOUT LES TESTS OK");
        else System.out.println("DES TESTS KO");
    }
}
