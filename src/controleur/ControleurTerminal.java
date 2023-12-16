package src.controleur;

import src.modele.Bataille;
import src.player.*;
import src.jeu.Coord;
import src.jeu.terminalutil;

import java.util.Scanner;

/**
 * Cette classe permet de gérer le jeu dans le terminal.
 */
public class ControleurTerminal {
    private Bataille modele;
    private Scanner scan;
    terminalutil term;

    public ControleurTerminal(Bataille mod, int nbVue) {

        scan = new Scanner(System.in); // Create a Scanner object
        modele = mod;
        int tailleX = modele.getp1().getPlateau().getTailleX() * 8;
        int tailleY = modele.getp1().getPlateau().getTailleY() * 2 + 3;
        term = new terminalutil(tailleY, tailleX);
        term.setScanner(scan);
        if (nbVue == 0) {
            if(modele.getp1() instanceof Humain){
                String inputName = term.InputdialogBox("Joueur 1 quel est votre nom?");
                if(inputName!=null &&  !inputName.equals("")) modele.getp1().setName(inputName);
                else modele.getp1().setName("Joueur 1");
            }
            if(modele.getp2() instanceof Humain){
                String inputName = term.InputdialogBox("Joueur 2 quel est votre nom?");
                if(inputName!=null &&  !inputName.equals("")) modele.getp2().setName(inputName);
                else modele.getp2().setName("Joueur 2");
            }

            if(modele.getp1() instanceof RandomPlayer){
                if (term.YNdialogBox("Voulez vous jouer contre une IA intelligente?")) {
                    modele.setP1(new IAPlayer("Terminator"));
                    modele.setCurrentP(modele.getp1());
                }
            }
            if(modele.getp2() instanceof RandomPlayer){
                if (term.YNdialogBox("Voulez vous jouer contre une IA intelligente?")) {
                    modele.setP2(new IAPlayer("Terminator"));
                }
            }
            ControleurBateauxTerminal cb = new ControleurBateauxTerminal(modele, scan);

        }

        modele.changement(); // on affiche le jeu tel quel

        while (!modele.isOver()) {
            if(!(modele.getCurrentP() instanceof Humain) ){
                Joueur JNJ = (modele.getCurrentP() == modele.getp1()) ? modele.getp2() : modele.getp1();
                Coord robot = modele.getCurrentP().coup(-1,-1);
                modele.attackP(robot, modele.getCurrentP(), JNJ);
                modele.setCurrentP(JNJ);
                delai(500);
                modele.changement();
            }
            else {
                Joueur JNJ = (modele.getCurrentP() == modele.getp1()) ? modele.getp2() : modele.getp1();
                Coord coup = coup(modele.getCurrentP());
                modele.attackP(coup, modele.getCurrentP(), JNJ);
                modele.setCurrentP(JNJ);
                modele.changement();
            }
            
            
        }

        term.OKdialogBox("Bravo " + modele.getCurrentP().getName() + " tu as gagné!");

    }

    /**
     * Cette méthode permet d'attaquer le joueur adverse.
     * @param J Joueur qui attaque
     * @return les coordonnées ou l'on veut attaquer ou les coordonnées (-1,-1) si le joueur n'est pas humain
     */
    private Coord coup(Joueur J) {
        if (J instanceof Humain) {
            term.OKdialogBox(J.getName() + " c'est ton tour!");
            int ligne = 0;
            int colomne = 0;
            boolean valideligne = false;
            while (!valideligne) {
                ligne = term.NumdialogBox("Rentrez la ligne que vous desirez attaquer : (1-"
                + J.getPlateau().getTailleY() + ")") -1;
                if(ligne >= 0 && ligne < J.getPlateau().getTailleY()){
                    valideligne = true;
                }else{
                    modele.changement();
                    term.OKdialogBox("Veuillez rentrer une valeur entre 1 et " + J.getPlateau().getTailleY());
                }
            }
            modele.changement();
            boolean validecolomne = false;
            while (!validecolomne) {
                colomne = term.NumdialogBox("Rentrez la colonne que vous desirez attaquer : (1-"
                + J.getPlateau().getTailleY() + ")") -1;
                if(colomne >= 0 && colomne < J.getPlateau().getTailleX()){
                    validecolomne = true;
                }else{
                    modele.changement();
                    term.OKdialogBox("Veuillez rentrer une valeur entre 1 et " + J.getPlateau().getTailleX());
                }
            }

            return J.coup(ligne, colomne);
        }
        return J.coup(-1, -1); // -1 = sortie par défaut

    }

    /**
     * Cette méthode permet de faire attendre l'IA
     * @param milliseconds Temps en millisecondes que l'on veut faire attendre
     */
      public static void delai(int milliseconds){
        try {
              Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
              e.printStackTrace();
        }
  }
}
