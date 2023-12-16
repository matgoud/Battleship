package src;
import src.modele.Bataille;
import src.player.*;
import src.controleur.*;
import src.vue.*;
import src.jeu.*;

/**
 * Cette classe permet d'exécuter le jeu avec différents affichages.
 */
public class execute {
    
    String nomParDefault ="mega man";
    String nomParROBOT="ROBOT";
    int nbVue;
    IntOBJ tailleCase;
    Bataille B;
    /**
       * Cette méthode permet d'exécuter le jeu avec les paramètres par défaut.
       */
    public execute(){
        B=initBataille();
        System.out.println("Variable par défault");
        ControleurInterface ci = new ControleurInterface(B,tailleCase);
        new VueInterface(B,ci,tailleCase);
        System.out.println("Merci d'avoir joué à notre jeu !");
    }
    /**
     * Ce constructeur permet de lancer le jeu avec un affichage choisi.
     * @param affichage affichage du jeu.
     */
    public execute(String affichage) {
        if(tailleCase!= new IntOBJ(1000)){
            B=initBataille();
        }
        if(affichage.equals("interface")){
            ControleurInterface ci = new ControleurInterface(B,tailleCase);
            new VueInterface(B,ci,tailleCase);
            System.out.println("Merci d'avoir joué à notre jeu !");
        }
        else if(affichage.equals("terminal")){
            new VueTerminal(B);
            new ControleurTerminal(B,nbVue);
            System.out.println("Merci d'avoir joué à notre jeu !");
        }
        else if(affichage.equals("double")){
            ControleurInterface ci = new ControleurInterface(B,tailleCase);
            if(!(B.getp1().getName().equals(nomParDefault))){
                nbVue +=1;
            }
            new VueTerminal(B);
            new VueInterface(B,ci,tailleCase); 
            System.out.println("Merci d'avoir joué à notre jeu!");
        }
        else{
            new execute();
        }   
    }
    /**
     * Ce constructeur permet de lancer le jeu avec un affichage choisi et un adversaire choisi.
     * @param affichage Affichage du jeu.
     * @param adversaire Adversaire du jeu.
     */
    public execute(String affichage,String adversaire) {
        initBataille(adversaire);
        if(affichage.equals("interface")){
            ControleurInterface ci = new ControleurInterface(B,tailleCase);
            new VueInterface(B,ci,tailleCase);
            System.out.println("Merci d'avoir joué à notre jeu !");
        }
        else if(affichage.equals("terminal")){
            new VueTerminal(B);
            new ControleurTerminal(B,nbVue);
            System.out.println("Merci d'avoir joué à notre jeu !");
        }
        else if(affichage.equals("double")){
            ControleurInterface ci = new ControleurInterface(B,tailleCase);
            if(!(B.getp1().getName().equals(nomParDefault))){
                nbVue +=1;
            }
            new VueTerminal(B);
            new VueInterface(B,ci,tailleCase);
            new ControleurTerminal(B,nbVue);
            System.out.println("Merci d'avoir joué à notre jeu!");
        }
        else{
            new execute();
        }
    }
    
    /**
     * Cette méthode permet d'initialiser la bataille.
     * @return La bataille par défaut.
     */
    public Bataille initBataille(){
        nbVue = 0;
        tailleCase = new IntOBJ(1000);
        Joueur p1 = new Humain(nomParDefault);
        Joueur p2 = new RandomPlayer(nomParROBOT);
        return B = new Bataille(p1,p2);
        
    }

    /**
     * Cette méthode permet d'initialiser la bataille avec un adversaire.
     * @param adversaire Entier contenant le choix d'adversaire.
     * @return La bataille avec un Humain si adversaire ==1 et la bataille par défaut sinon.
     */
    public Bataille initBataille(String adversaire){
        nbVue = 0;
        tailleCase = new IntOBJ(1000);
        if(adversaire.equals("hvsh")){
            Joueur p1 = new Humain(nomParDefault);
            Joueur p2 = new Humain(nomParDefault);
            return B = new Bataille(p1,p2);
        }
        else if(adversaire.equals("rvsr")){
            Joueur p1 = new RandomPlayer(nomParROBOT);
            Joueur p2 = new RandomPlayer(nomParROBOT);
            return B = new Bataille(p1,p2);
        }
        else{
            return initBataille();
        }
        
    }
}

