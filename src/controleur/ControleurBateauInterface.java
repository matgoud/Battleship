package src.controleur;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import src.jeu.*;
import src.modele.Bataille;
import src.player.Humain;
import src.player.Joueur;

/**
 * Cette classe permet de gérer les bateaux dans l'interface
 */

public class ControleurBateauInterface extends MouseAdapter{

    private Bataille modele;
    private int nbrBateauP1;
    private int nbrBateauP2;
    private IntOBJ tailleCase;

    public ControleurBateauInterface(Bataille modele,IntOBJ taillecase){
        this.modele = modele;
        this.nbrBateauP1 = 0;
        this.nbrBateauP2 = 0;
        tailleCase = taillecase;
    }

    /**
     * Cette méthode permet de savoir si un joueur Humain veut placer lui même sa flotte
     * @param J Joueur dont on veut savoir si il veut placer lui même sa flotte
     * @return true si il veut placer lui même sa flotte, false sinon ou si le joueur n'est pas Humain.
     */
    public boolean placeBoats(Joueur J){
        if(J instanceof Humain){
            int answer = JOptionPane.showConfirmDialog(null,J.getName() + " voulez vous placer vos bateaux vous même ?","Placement des bateaux", JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    return true;
                }else{
                    return false;
                }
            }
        return false;
    }

    /**
     * Cette méthode va placer automatiquement les bateaux sur le plateau.
     * @param J Joueur dont on veut placer automatiquement les bateaux sur le plateau.
     */
    public void placeBoatsAutomatically(Joueur J){
        J.placeBoats();
    }

    /**
     * Cette méthode permet de placer un bateau sur le plateau à l'endroit cliqué.
     * @param e Action de cliquer sur une case.
     */
    public void mouseClicked(MouseEvent e){
        putBoat(e, (modele.getp1() == modele.getCurrentP()));
    
    }

    /**
     * Cette méthode permet de placer les bateaux sur le plateau.
     * @param e Action de cliquer sur une case.
     * @param isP1 Vérifie qui veut placer les bateaux.
     */
    private void putBoat(MouseEvent e, boolean isP1){
        int shipSize[] = new int[] {5,4,3,3,2};
        int nbrBateau = 0;
        Plateau plateau= modele.getCurrentP().getPlateau();
        if(isP1){
            nbrBateau = nbrBateauP1;
        }else nbrBateau = nbrBateauP2;
        int tx = modele.getp1().getPlateau().getTailleX();
        int ty = modele.getp1().getPlateau().getTailleY();
        Coord click = new Coord(e.getY() - 50, e.getX() - 50);
        //System.out.println("coord X : " + click.X + "coord Y : " + click.Y );
        if(nbrBateau < 5){
            if (click.X > tailleCase.val*tx || click.Y > tailleCase.val*ty || click.X < 0 || click.Y < 0 )
                return;

            Orientation orientation2;
            Coord depart = new Coord(click.Y/tailleCase.val,click.X/tailleCase.val);
            String[] orientations = {"Haut","Bas","Droite","Gauche"};
            String orientation = (String) JOptionPane.showInputDialog(null,"Choisissez l'orientation de votre bateau de taille "+shipSize[nbrBateau]+" :", "Liste des choix",JOptionPane.PLAIN_MESSAGE, null,orientations,orientations[0]);
            if(orientation == null)
                return;
            if(orientation == "Haut"){
                orientation2 = Orientation.VERTICAL;
                depart.Y -= shipSize[nbrBateau]-1;
            }else if(orientation == "Bas"){
                orientation2 = Orientation.VERTICAL;
            }else if(orientation == "Gauche"){
                orientation2 = Orientation.HORIZONTAL;
                depart.X -= shipSize[nbrBateau]-1;
            }else{
                orientation2 = Orientation.HORIZONTAL;
            }
        
            Bateau bateau = new Bateau(depart,orientation2,shipSize[nbrBateau]);

            if (placeBoatInPlateau(bateau, plateau)){
                modele.changement();
                nbrBateau++;
                if(nbrBateau == 5){
                    modele.getCurrentP().setPlaceBoat(true);
                }
            }else{
                JOptionPane.showMessageDialog(null,"Placement du bateau invalide","Attention!",1);
            }

        }else{
            JOptionPane.showMessageDialog(null,"Tous les bateaux ont été placé!","Attention",1);
        }
        if(isP1){
            nbrBateauP1 = nbrBateau;
        }else  nbrBateauP2 = nbrBateau;
    }

    /**
     * Cette méthode vérifie si un bateau peut être placé sur le plateau en respectant les règles, et le place si oui.
     * @param b Bateau à vérifier
     * @param p Plateau où on veut placer le bateau
     * @return true si le bateau a été placé et false sinon.
     */
    private boolean placeBoatInPlateau(Bateau b, Plateau p) {
        if (b.isInPlateau(p)) {
            for (int i = 0; i < p.getNavires().size(); i++) {
                for (int j = 0; j < b.getTaille(); j++) {
                    if (p.getNavires().get(i).isCoordNear(b.getCases().get(j))) {
                        return false;
                    }
                }
            }

            p.ajoutBateau(b);
            return true;
        }
        return false;
    }
}