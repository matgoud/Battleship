package src.controleur;

import src.jeu.*;
import src.modele.Bataille;
import src.player.Humain;
import src.player.Joueur;

import java.io.Console;
import java.util.Scanner;

/**
 * Cette classe permet de gérer les bateaux sur le terminal.
 */

public class ControleurBateauxTerminal {

    private Bataille modele;
    terminalutil term;

    public ControleurBateauxTerminal(Bataille modele, Scanner sc) {
        this.modele = modele;
        int tailleX = modele.getp1().getPlateau().getTailleX() * 8;
        int tailleY = modele.getp1().getPlateau().getTailleY() * 2 + 3;
        term = new terminalutil(tailleY, tailleX);
        term.setScanner(sc);
        placeBoats(modele.getp1());
        placeBoats(modele.getp2());
    }

    /**
     * Cette méthode permet savoir si un joueur veut placer un bateau dans le plateau.
     * @param p Joueur auquel on demande
     */
    public void placeBoats(Joueur p) {
        if (p instanceof Humain) {
            modele.changement();
            if (term.YNdialogBox(p.getName() + " Voulez vous placer vos bateaux vous même ?")) {
                manualPlaceBoat(p);
            } else {
                p.placeBoats();
            }
        } else {
            p.placeBoats();
        }

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

    /**
     * Cette méthode permet de placer un bateau dans le plateau.
     * @param j Joueur qui veut placer le bateau
     */
    private void manualPlaceBoat(Joueur j) {
        int shipSize[] = new int[] { 5, 4, 3, 3, 2 };
        int nbrBateau = 0;
        while (nbrBateau < 5) {
            boolean boatOK = false;
            while (!boatOK) {
                Coord depart = new Coord(0, 0);
                boolean ligneOK = false;
                while (!ligneOK) {
                    modele.changement();
                    int ligne = term.NumdialogBox("sur quelle ligne doit commencer votre bateau?");
                    if (ligne > 0 && ligne <= j.getPlateau().getTailleY()) {
                        ligneOK = true;
                        depart.Y = ligne-1;
                    }
                }
                boolean colomneOK = false;
                while (!colomneOK) {
                    modele.changement();
                    int colomne = term.NumdialogBox("Sur quelle colonne doit commencer votre bateau?");
                    if (colomne > 0 && colomne <= j.getPlateau().getTailleX()) {
                        colomneOK = true;
                        depart.X = colomne-1;
                    }
                }
                String input = term
                        .InputdialogBox("Quelle sera l'orientation de votre bateau?(Haut/Bas/Droite/Gauche)?");
                Orientation O = Orientation.VERTICAL;
                if (input.equals("Haut") || input.equals("H") || input.equals("h")) {
                    depart.Y -= shipSize[nbrBateau] - 1;
                }
                if (input.equals("Bas") || input.equals("B") || input.equals("b")) {
                    ;// l'orientation de base est vers le BAS
                }
                if (input.equals("Gauche") || input.equals("G") || input.equals("g")) {
                    O = Orientation.HORIZONTAL;
                    depart.X -= shipSize[nbrBateau] - 1;
                }
                if (input.equals("Droite") || input.equals("D") || input.equals("d")) {
                    O = Orientation.HORIZONTAL;
                }
                Bateau bateau = new Bateau(depart, O, shipSize[nbrBateau]);
                if (placeBoatInPlateau(bateau, j.getPlateau())){
                    boatOK = true;
                }else{
                    modele.changement();
                    term.OKdialogBox("Le bateau ne peut pas être placé ici!");
                }
                modele.changement();
            }
            nbrBateau++;
        }
    }

}