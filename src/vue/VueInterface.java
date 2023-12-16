package src.vue;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;

import src.controleur.ControleurInterface;
import src.controleur.ControleurBateauInterface;
import src.modele.Bataille;
import src.jeu.IntOBJ;

/**
 * Classe permettant de créer une vue de la bataille sur une interface.
 */
public class VueInterface implements ComponentListener,Ecouteur{

    private final JFrame frame;
    private AffichePlateau p1;
    private AffichePlateau p2;
    private Bataille b;
    private int margin; //Pour le Top Left, au minimum
    private int marginBR; // Bottom Right
    private int tailleGrille;
    private IntOBJ tailleCase;
    private int largExteGrille;
    private int largInteGrille;

    public VueInterface(Bataille b,ControleurInterface controleur,IntOBJ taillecase){

        tailleCase = taillecase;
        this.b=b;

        marginBR=50;
        margin=Math.max(marginBR,25);
        largExteGrille=2;
        largInteGrille=1;
        tailleGrille=tailleCase.val*10+largExteGrille*2+largInteGrille*9;
 
        frame = new JFrame("Bataille navale");
        frame.setSize(1000,500);
        //frame.setSize(width,height);

        frame.addComponentListener(this);
        b.ajoutEcouteur(this);

        Image icon = Toolkit.getDefaultToolkit().getImage("img/gameicon.png");

        this.p1 = new AffichePlateau(b.getp1(),tailleCase,tailleGrille,margin);
        this.p2 = new AffichePlateau(b.getp2(),tailleCase,tailleGrille,margin);
        controleur.setPlateauJ1(p1);
        controleur.setPlateauJ2(p2);
        frame.repaint();

        frame.add(p1);
        frame.add(p2);
        frame.setIconImage(icon);
        frame.setLayout(new GridLayout(1,2));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controleur.start();

    }

    public void componentHidden(ComponentEvent ce) {};
    public void componentShown(ComponentEvent ce) {};
    public void componentMoved(ComponentEvent ce) {};
    public void componentResized(ComponentEvent ce) {
        int h = frame.getHeight();
        int w = frame.getWidth();
        int h2=h-margin-marginBR-largExteGrille*2-largInteGrille*9-36;
        int w2=w-(margin+marginBR)*2-largExteGrille*4-largInteGrille*18;
        int newTailleCase=Math.min(w2/20,h2/10);
        if(newTailleCase!=tailleCase.val){
            tailleCase.val = newTailleCase;
            //System.out.println("taille dans vue : " + tailleCase.val);
            p1.setLabels(newTailleCase);
            p2.setLabels(newTailleCase);
            frame.repaint();
        }
    }

    /**
     * Cette méthode permet de mettre à jour la vue de la bataille.
     */
    @Override
    public void MiseAJour(Object source){
        frame.repaint();
    }

}
