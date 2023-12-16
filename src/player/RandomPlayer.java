package src.player;
import src.jeu.*;
import java.util.ArrayList;

/**
 * Cette classe représente un robot tirant au hasard ses coups.
 */
public class RandomPlayer extends AbstractPlayer{

    private ArrayList<Coord> Pcases = new ArrayList<>();

    public RandomPlayer(String nom){
        super(nom);
    }

    /**
     * 
     * Cette méthode initialise la liste contenant toutes les cases du plateau.
     */
     
    @Override
    public void generatePossibleCases(){
        for(int i = 0; i < super.getPlateau().getTailleX(); i++)
            for(int j = 0; j < super.getPlateau().getTailleY(); j++){
                Coord C = new Coord(i, j);
                Pcases.add(C);
            }

    }

    /**
     * Cette méthode permet au joueur de lancer un coup (tir pour la bataille navale).
     * @param Y Ordonnée du coup.
     * @param X Abscisse du coup.
     * @return Une Coordonnée correspondant au coup ou une Coordonnée.
     */
    @Override
    public Coord coup(int Y,int X){
        if(Y < 1 || X < 1){
            Coord C = Pcases.get(super.getRand().nextInt(Pcases.size()));
            Pcases.remove(C);
            return C;
        }
        else{
            Coord C = new Coord(Y-1,X-1);
            for(int i=0;i<Pcases.size();i++){
                Coord delete = Pcases.get(i);
                if(C.equals(delete)){
                    Pcases.remove(i);
                }
            }
            return C;
        }
    }

    /**
     * Cette méthode renvoie la liste de toutes les cases où on peut tirer.
     * @return La liste de toutes les cases où on peut tirer.
     */
    public ArrayList<Coord> getPcases() {
        return Pcases;
    }

}