package src.jeu;

import src.player.*;
import java.util.Random;
import java.util.ArrayList;

/**
 * Cette classe représente le plateau de jeu d'un joueur et les méthodes pour l'utiliser.
 */
public class Plateau{

    private int tailleX; //Largeur du plateau
    private int tailleY; //Hauteur du plateau
    private StateShot[][] tab; //Tableau de jeu
    private Joueur proprio; //Joueur propriétaire du plateau
    private ArrayList<Bateau> navires = new ArrayList<>(); //Liste des bateaux du plateau

    //Un plateau doit faire au moins 10 par 10 cases
    public Plateau(int tX,int tY){
        tailleX = 10;
        tailleY = 10;

        if(tX > 10 && tY > 10){
            tailleX = tX;
            tailleY = tY;
        }
        /**
         * Cette boucle initialise un tableau de jeu ayant pour toute case la valeur StateShot.WATER
         */
        tab = new StateShot [tailleY][tailleX]; 
        for (int lig = 0; lig < tailleY; lig++) {
            for (int col = 0; col < tailleX; col++) {
                tab[lig][col] = StateShot.WATER;
            }
        }
    }
    /**
     * Cette méthode renvoie la largeur du plateau
     * @return La largeur du plateau
     */
    public int getTailleX(){
        return tailleX;
    }
    /**
     * Cette méthode renvoie la hauteur du plateau
     * @return La hauteur du plateau
     */
    public int getTailleY(){
        return tailleY;
    }
    /**
     * Cette méthode renvoie l'état de la case identifiée par les coordonnées (cY,cX)
     * @param cY Ordonnée de la case
     * @param cX Abscisse de la case
     * @return L'état de la case
     */
    public StateShot getTabYX(int cY,int cX){
        if(cY >= 0 && cY < tailleY && cX >= 0 && cX < tailleX){
            return tab[cY][cX];
        }
        return StateShot.WATER;
    }
    /**
     * Cette méthode permet de modifier l'état de la case identifiée par les coordonnées (cY,cX) par le nouvel état val
     * @param cY Ordonnée de la case
     * @param cX Abscisse de la case
     * @param val Nouvel état de la case
     */
    public void setTabYX(int cY,int cX,StateShot val){
        if(cY >= 0 && cY < tailleY && cX >= 0 && cX < tailleX){
            tab[cY][cX] = val;
        }
    }
    /**
     * Cette méthode renvoie la liste des navires du plateau
     * @return La liste des navires du plateau
     */
    public ArrayList<Bateau> getNavires() {
        return navires;
    }

    /**
     * Cette méthode permet d'ajouter un bateau au plateau
     * @param obj Bateau à ajouter
     */
    public void ajoutBateau(Bateau obj){
        navires.add(obj);
        for(int i = 0; i < obj.getTaille(); i++){
            Coord C = obj.getCases().get(i);
            setTabYX(C.Y,C.X,StateShot.SHIP);
        }
    }
    /**
     * Cette méthode permet de retirer un bateau du plateau
     * @param idx Index du navire à retirer
     */
    public void retraitBateau(int idx){
        for(int i = 0; i < navires.get(idx).getTaille(); i++){
            Coord C = navires.get(idx).getCases().get(i);
            setTabYX(C.Y,C.X,StateShot.WATER);
        }
        navires.remove(idx);
    }
    /**
     * Cette méthode vérifie si tous les bateaux du plateau ont été coulées
     * @return true si tous les bateaux ont été coulées, false sinon
     */
    public boolean plusDeBateaux(){
        for(int i = 0; i < navires.size();i++){
            if(!navires.get(i).isSunk(this))return false;
        }
        return true;
    }
    /**
     * Cette méthode renvoie le joueur propriétaire du plateau
     * @return le joueur propriétaire du plateau
     */
    public Joueur getOwner(){
        return proprio;
    }
    /**
     * Cette méthode permet de modifier le joueur propriétaire du plateau
     * @param J Nouveau joueur propriétaire du plateau
     */
    public void setOwner(Joueur J){
        proprio = J;
    }
}
