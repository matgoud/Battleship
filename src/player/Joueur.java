package src.player;
import java.util.Random;
import src.jeu.*;

/**
 * Cette interface initialise les différentes méthodes que chaque type de joueur va utiliser.
 */
public interface Joueur{

    /**
     * Cette méthode va placer aléatoirement les 5 bateaux sur le plateau du Joueur.
     */
    public void placeBoats();
    /**
     * Cette méthode permet au joueur de lancer un coup (tir pour la bataille navale).
     * @param Y Ordonnée du coup.
     * @param X Abscisse du coup.
     * @return une Coordonnée correspondant au coup ou une Coordonnée.
     */
    public Coord coup(int Y,int X);
    /**
     * Cette méthode permet de changer le nom du joueur.
     * @param name Nouveau nom du joueur.
     */
    public void setName(String name);
    /**
     * Cette méthode renvoie le nom du joueur.
     * @return Le nom du joueur.
     */
    public String getName();
    /**
     * Cette méthode renvoie le plateau associé au joueur.
     * @return Le plateau du joueur.
     */
    public Plateau getPlateau();
    /**
     * Cette méthode renvoie le générateur aléatoire associé au joueur.
     * @return Le générateur aléatoire du joueur.
     */
    public Random getRand();
    /**
     * Cette méthode vérifie si tous les bateaux du plateau sont placés correctement.
     * @return true si tous les bateaux du plateau sont placés correctement, false sinon.
     */
    public boolean getPlaceBoat();
    /**
     * Cette méthode permet d'indiquer que les bateaux du plateau sont placés correctement.
     * @param b Indique si tous les bateaux du plateau sont placés correctement.
     */
    public void setPlaceBoat(boolean b);
    /**
     * Cette méthode permet d'indiquer si le dernier coup du joueur a touché un bateau.
     * @param b true si le dernier coup du joueur a touché un bateau, false sinon.
     */
    public void setLastHitTouche(boolean b);
   /**
     * Cette méthode permet d'indiquer si le dernier coup du joueur a coulé un bateau.
     * @param b true si le dernier coup du joueur a coulé un bateau, false sinon.
     */
    public void setLastHitCoule(boolean b);
    
    /**
     * Cette méthode vérifie si les bateaux devrait être visible s'ils ne sont pas coulé.
     * @return true si les bateaux devrait être visible s'ils ne sont pas coulé, false sinon.
     */
    public boolean getShouldBoatsBeVisible();

    /**
     * Cette méthode permet de changer si les bateaux doivent être visible s'ils ne sont pas coulé.
     * @param b La nouvelle valeur de shouldBoatsBeVisible
     */
    public void setShouldBoatsBeVisible(boolean  b);
    
}
