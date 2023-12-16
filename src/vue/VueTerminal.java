package src.vue;

import src.controleur.ControleurBateauxTerminal;
import src.jeu.StateShot;
import src.jeu.terminalutil;
import src.modele.Bataille;
import src.player.*;

/**
 * Classe permettant de créer une vue de la bataille sur un terminal.
 */
public class VueTerminal implements Ecouteur{

    private Bataille modele;                //on doit avoir L'objet qu'on écoute en réference
    private terminalutil term;

    public VueTerminal(Bataille mod){
        modele = mod;                       //on donne la référence au moment de l'initialisation
        modele.ajoutEcouteur(this);         //vue s'ajoute en listener
        int tailleX = modele.getp1().getPlateau().getTailleX()*8;
        int tailleY = modele.getp1().getPlateau().getTailleY()*2 + 3;
        term = new terminalutil(tailleY, tailleX);
        term.clear();
    }

    /**
     * Cette méthode permet de mettre a jour la vue de la bataille dans le terminal
     */
    public void MiseAJour(Object source){
        term.clear();
        term.gotoyx(0,0);
        for(int y = 0; y < modele.getp1().getPlateau().getTailleY();y++){      //la taille des deux modèles devraient être la même, il est vrai qu'il serait techniquement
            term.gotoyx(y+3,9);
            System.out.println((y+1));
            int tx = modele.getp1().getPlateau().getTailleX();
            term.gotoyx(y+3,-2 + term.getTailleX() - (2*(tx) + 8));
            System.out.println((y+1));
            for(int x = 0; x < tx;x++){     //possible d'avoir deux tailles différentes mais pour maintenant ca ira...
                //plateau 1 en prems :
                term.gotoyx(y+3,2*x + 11);
                StateShot element = modele.getp1().getPlateau().getTabYX(y,x);
                if(modele.getp1() instanceof Humain && modele.getp2() instanceof Humain)printHiddenElement(element);
                else if(modele.getp1() instanceof Humain) printElement(element); else printHiddenElement(element);

                term.gotoyx(y+3,term.getTailleX() - (2*(tx - x) + 8));
                element = modele.getp2().getPlateau().getTabYX(y,x);
                if(modele.getp1() instanceof Humain && modele.getp2() instanceof Humain)printHiddenElement(element);
                else if(modele.getp2() instanceof Humain) printElement(element); else printHiddenElement(element);

            }
            for(int x = 0; x < tx;x++){
                term.gotoyx(2,2*x + 11);
                System.out.println((x+1));
                term.gotoyx(2,term.getTailleX() - (2*(tx - x) + 8));
                System.out.println((x+1));
            }
        }
        term.gotoyx(modele.getp1().getPlateau().getTailleY() + 4,modele.getp1().getPlateau().getTailleX()+4);
    }


    /**
     * Cette méthode permet d'afficher l'élément de la bataille dans le terminal, pour une grille dont les bateaux sont visible pour l'utilisateur. (grille de l'utilisateur)
     * @param element L'élément à afficher
     */
    private void printElement(StateShot element){
        if( element == StateShot.WATER){
            System.out.printf(term.BLUE + "." + term.RESET);
        }

        if( element == StateShot.SHIP){
            System.out.printf(term.BLACK + "#" + term.RESET);
        }

        if( element == StateShot.WATER_HIT){
            System.out.printf(term.WHITE + "~" + term.RESET);
        }

        if( element == StateShot.SHIP_HIT){
            System.out.printf(term.RED + "X" + term.RESET);
        }
    }


    /**
     * Cette méthode permet d'afficher l'élément de la bataille dans le terminal, pour une grille dont les bateaux sont invisible pour l'utilisateur. (grille de l'adversaire)
     * @param element L'élément à afficher
     */
    private void printHiddenElement(StateShot element){
        if( element == StateShot.WATER){
            System.out.printf(term.BLUE + "." + term.RESET);
        }

        if( element == StateShot.SHIP){
            System.out.printf(term.BLUE + "." + term.RESET);
        }

        if( element == StateShot.WATER_HIT){
            System.out.printf(term.WHITE + "~" + term.RESET);
        }

        if( element == StateShot.SHIP_HIT){
            System.out.printf(term.RED + "X" + term.RESET);
        }
   }
}
