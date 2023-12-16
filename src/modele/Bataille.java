package src.modele;
import src.jeu.*;
import src.player.Joueur;

/**
 * Cette classe représente la Bataille avec les joueurs et les méthodes itinérantes à une bataille navale.
 */
public class Bataille extends ModeleEcoutable{

    private Joueur p1; //Joueur 1
    private Joueur p2; //Joueur 2
    private Joueur currentP; //Joueur actuel

    public Bataille(Joueur J1,Joueur J2){
        this.p1 = J1;
        this.p2 = J2;
        this.currentP = p1;
        changement();
    }
    /**
     * Cette méthode renvoie le Joueur 1.
     * @return Le Joueur 1.
     */
    public Joueur getp1(){
        return p1;
    }
    /**
     * Cette méthode renvoie le Joueur 2.
     * @return Le Joueur 2.
     */
    public Joueur getp2(){
        return p2;
    }
    
    /**
     * Cette méthode permet de changer le joueur 1.
     * @param p1 Nouveau joueur.
     */
    public void setP1(Joueur p1) {
        this.p1 = p1;
    } 

    /**
     * Cette méthode permet de changer le joueur 2.
     * @param p2 Nouveau joueur.
     */
    public void setP2(Joueur p2) {
        this.p2 = p2;
    }  
    /**
     * Cette méthode renvoie le Joueur actuel.
     * @return Le Joueur actuel.
     */
    public Joueur getCurrentP(){
        return currentP;
    }

    /**
     * Cette méthode modifie le Joueur actuel.
     */
    public void setCurrentP(Joueur J){
        currentP = J;
    }
    /**
     * Cette méthode permet au joueur d'attaquer le plateau d'un autre joueur.
     * @param c Coordonnées du tir du Joueur attaquant sur le plateau du joueur attaqué.
     * @param jAttaquant Joueur effectuant l'attaque.
     * @param jAttaque Joueur subissant l'attaque.
     */
    public boolean attackP(Coord c,Joueur jAttaquant,Joueur jAttaque){
        
        StateShot val = jAttaque.getPlateau().getTabYX(c.Y, c.X);

        if(val == StateShot.WATER || val == StateShot.SHIP){
            jAttaque.getPlateau().setTabYX(c.Y,c.X,( jAttaque.getPlateau().getTabYX(c.Y,c.X) == StateShot.WATER) ? StateShot.WATER_HIT : StateShot.SHIP_HIT);
            //Ces variables ne servent qu'a l'IA intelligente
            jAttaquant.setLastHitTouche(false);
            jAttaquant.setLastHitCoule(false);
            if(jAttaque.getPlateau().getTabYX(c.Y,c.X) == StateShot.SHIP_HIT){
                jAttaquant.setLastHitTouche(true);
                int index=-1;
                for (int i=0;i<jAttaque.getPlateau().getNavires().size();i++){
                    if (jAttaque.getPlateau().getNavires().get(i).isCoordInside(c)){
                        index=i; 
                    }
                }
                if(index!=-1 && jAttaque.getPlateau().getNavires().get(index).isSunk(jAttaque.getPlateau())) jAttaquant.setLastHitCoule(true);
            }
            changement();
            return true;
        }
        return false;
    }
    /**
     * Cette méthode vérifie l'état de la bataille.
     * @return true si la bataille est fini, false sinon.
     */
    public boolean isOver(){
        if(p1.getPlateau().plusDeBateaux()){
            currentP = p2;
            return true;
        }else if(p2.getPlateau().plusDeBateaux()){
            currentP = p1;
            return true;
        }
        return false;
    }


}
