package src.controleur;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

qimport javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import src.modele.Bataille;
import src.player.Humain;
import src.player.IAPlayer;
import src.player.Joueur;
import src.player.RandomPlayer;
import src.vue.AffichePlateau;
import src.jeu.Coord;
import src.jeu.IntOBJ;

/**
 * Cette classe permet de controler la partie depuis l'interface.
 */
public class ControleurInterface extends MouseAdapter{

    private Bataille modele;
    private IntOBJ tailleCase;          //utile pour déterminer quelle case a été touché
    ControleurBateauInterface cb;       //controlleur pour placer les bateaux
    private AffichePlateau plateauJ1;   //on en a besoin pour ajouter les mouse listener, c'est tout, pas de fonction de dessin.
    private AffichePlateau plateauJ2;   //on en a besoin pour ajouter les mouse listener, c'est tout, pas de fonction de dessin.

    public ControleurInterface(Bataille modele,IntOBJ taillecase){

        this.modele = modele;
        tailleCase = taillecase;
        initPlayers();

    }
    
    /**
     * Cette méthode permet de lancer une partie
     * son éxécution se termine quand la partie est terminée
     */
    public void start() {
        Joueur listJ[]={modele.getp1(),modele.getp2()};
        cb = new ControleurBateauInterface(modele,tailleCase);
        for(Joueur joueur:listJ){
            if(joueur instanceof Humain) joueur.setShouldBoatsBeVisible(true);
            playerPlaceBoats(joueur);
            while(!joueur.getPlaceBoat())delai(5);
            if(modele.getp1() instanceof Humain && modele.getp2() instanceof Humain) joueur.setShouldBoatsBeVisible(false);
            modele.changement();
        }

        plateauJ1.getPlateauJoueur().addMouseListener(this);
        plateauJ2.getPlateauJoueur().addMouseListener(this);
        JOptionPane.showMessageDialog(null,modele.getCurrentP().getName() + " tu commences!","Information",1);
        while(!modele.isOver()){
            //Joueur J = modele.getCurrentP();
            delai(5);
            if(!(modele.getCurrentP() instanceof Humain) ){
                Joueur JNJ = (modele.getCurrentP() == modele.getp1()) ? modele.getp2() : modele.getp1();
                if(!(modele.getp1() instanceof Humain ||modele.getp2() instanceof Humain ) ) delai(500);
                else delai(100);
                Coord robot = modele.getCurrentP().coup(-1,-1);
                modele.attackP(robot, modele.getCurrentP(), JNJ);
                modele.setCurrentP(JNJ);
                modele.changement();
                if(modele.getp1() instanceof Humain && modele.getp2() instanceof Humain ) JOptionPane.showMessageDialog(null,modele.getCurrentP().getName() + " a ton tour!","Information",1);
            }
        }

        if(modele.isOver()){
            JOptionPane.showMessageDialog(null,"Bravo "+modele.getCurrentP().getName()+"!","END",1);
            System.exit(0);
        }
    }



    /**
     * Cette méthode permet de placer les bateaux d'un joueur.
     * @param J Joueur qui place les bateaux
     */
    private void playerPlaceBoats(Joueur J){
        if(cb.placeBoats(J)){
            if(J == modele.getp1())plateauJ1.addMouseListener(cb);
            if(J == modele.getp2())plateauJ2.addMouseListener(cb);
            modele.setCurrentP(J);
        }else{
            cb.placeBoatsAutomatically(J);
        }
    }

    /**
     * Cette méthode permet d'initialiser les joueurs
     */
    public void initPlayers(){
        if(modele.getp1() instanceof Humain){
            String name = JOptionPane.showInputDialog("Joueur 1 quel est votre nom ?");
            if(name!=null && !name.equals("")) modele.getp1().setName(name);
            else modele.getp1().setName("Joueur 1");
        }
        if(!(modele.getp1() instanceof Humain))choixIA(modele.getp1());
        if(modele.getp2() instanceof Humain){
            String name = JOptionPane.showInputDialog("Joueur 2 quel est votre nom ?");
            if(name!=null &&  !name.equals("")) modele.getp2().setName(name);
            else modele.getp2().setName("Joueur 2");
        }
        if(!(modele.getp2() instanceof Humain))choixIA(modele.getp2());
    }


    /**
     * Cette méthode permet de choisir le niveau de l'IA.
     * @param p IA actuelle
     */
    private void choixIA(Joueur p){
        JCheckBox IA = new JCheckBox("IA",true);
        int adversaire = JOptionPane.showConfirmDialog(IA,"Voulez vous jouer contre une IA intelligente?","Choix de l'adversaire",0);
        if(adversaire == 0){
            if (p == modele.getp1()){
                modele.setP1(new IAPlayer("Terminator"));
                modele.setCurrentP(modele.getp1());
            }else{
                modele.setP2(new IAPlayer("Terminator"));
            }
            
        }
    }

    /**
     * Cette méthode permet d'attaquer à l'emplacement cliqué.
     * @param e Action de cliquer sur une case
     */
    public void mouseClicked(MouseEvent e) {
        String Pname = e.getComponent().getName();

        int tX = modele.getCurrentP().getPlateau().getTailleX();
        int tY = modele.getCurrentP().getPlateau().getTailleY();
        
        if (e.getX() > tailleCase.val*tX || e.getY() > tailleCase.val*tY)
            return;
        if(modele.getCurrentP().getPlaceBoat()){
            Joueur JNJ = (modele.getCurrentP() == modele.getp1()) ? modele.getp2() : modele.getp1();
            if(Pname.equals(JNJ.getName() + " " + JNJ.hashCode())){
                if(modele.attackP(modele.getCurrentP().coup(e.getY()/tailleCase.val,e.getX()/tailleCase.val),modele.getCurrentP(),JNJ)){;
                    modele.setCurrentP(JNJ);
                    if(JNJ instanceof Humain)JOptionPane.showMessageDialog(null,modele.getCurrentP().getName() + " a ton tour!","Information",1);
                }else{
                    JOptionPane.showMessageDialog(null,"Vous avez déjà tiré sur cette case !","Attention",1);
                }
            }else{
                JOptionPane.showMessageDialog(null,"Vous ne pouvez pas vous attaquer vous même!","Attention",1);
            }
        }else{
            JOptionPane.showMessageDialog(null,"Veuillez placer tous vos bateaux avant d'attaquer l'adversaire !","Attention",1);
        }
    }


    //ici on prend la référence des plateaux de l'interface dans le but de pouvoir les controller
    //je ré-itère : ils ne servent pas a déssiner!
    public void setPlateauJ1(AffichePlateau p1) {
        plateauJ1 = p1;
    }
    //ici on prend la référence des plateaux de l'interface dans le but de pouvoir les controller
    //je ré-itère : ils ne servent pas a déssiner!
    public void setPlateauJ2(AffichePlateau p2) {
        plateauJ2 = p2;
    }


    /**
     * Cette méthode permet de créer un delai.
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
