package src.player;
import src.jeu.*;

/**
 * Cette classe représente un joueur Humain et définit les méthodes que l'Humain peut faire.
 */
public class Humain extends AbstractPlayer {

    public Humain(String nom){
        super(nom);
    }

    /**
     * Cette méthode initialise la liste contenant toutes les cases du plateau (utile uniquement pour les joueurs robots (RandomPlayer et IAPlayer)).
     */
    @Override
    public void generatePossibleCases(){
    }

    /**
     * Cette méthode permet au joueur de lancer un coup (tir pour la bataille navale).
     * @param Y Ordonnée du coup.
     * @param X Abscisse du coup.
     * @return Coordonnée correspondant au coup ou une Coordonnée (Y:-1,X:-1) si le coup n'est pas valide.
     */
    @Override
    public Coord coup(int Y,int X){
        if(Y>=0 && Y<board.getTailleY() && X>=0 && X<board.getTailleX()) return new Coord(Y,X);
        return new Coord(-1,-1);
    }

}
