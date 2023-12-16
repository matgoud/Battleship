package src.vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import src.jeu.IntOBJ;
import src.player.Joueur;

/**
 * Cette Classe représente un JPanel qui contient le plateau d'un joueur.
 */
public class AffichePlateau extends JPanel{

    private JPanel plateauJoueur;
    private DrawPlateau drawP;
    private int margin;
    private Color backColor;


    public AffichePlateau(Joueur j,IntOBJ tailleCase){
        this(j,tailleCase,tailleCase.val*10+13,50);
    }

    public AffichePlateau(Joueur j,IntOBJ tailleCase,int tailleGrille){
        this(j,tailleCase,tailleGrille,50);
    }

    public AffichePlateau(Joueur j,IntOBJ tailleCase,int tailleGrille,int margin){
        this.margin=margin;
        this.backColor = new Color( 40,116,166);

        Border LoweredBevelBorder = BorderFactory.createLoweredBevelBorder();
        Border EtchedBorderRaised = BorderFactory.createTitledBorder(LoweredBevelBorder,""+j.getName(),TitledBorder.LEFT,TitledBorder.TOP, new Font("Serif", Font.BOLD ,15), Color.black);

        setLayout(null);
        setBorder(EtchedBorderRaised);
        setBackground(backColor);

        this.plateauJoueur = new JPanel();
        plateauJoueur.setName(j.getName() + " " + j.hashCode());
        plateauJoueur.setBounds(margin,margin,tailleGrille,tailleGrille);
        plateauJoueur.setLayout(new GridLayout());
        drawP = new DrawPlateau(j.getPlateau(),tailleCase);
        plateauJoueur.add(drawP);
        setLabels(tailleCase.val);
    }

    /**
     * Cette méthode renvoie le JPanel qui contient le plateau d'un joueur.
     * @return Le JPanel qui contient le plateau d'un joueur.
     */
    public JPanel getPlateauJoueur() {
        return plateauJoueur;
    }
    
    /**
     * Cette méthode renvoie le DrawPlateau qui déssine le plateau d'un joueur.
     * @return Le DrawPlateau qui déssine le plateau d'un joueur.
     */
    public DrawPlateau getDrawPlateau(){
        return drawP;
    }

    /**
     * Cette méthode permet d'afficher les "titres" des lignes et colonnes en fonction de la taille d'une case.
     * @param tCase Taille d'une case.
     */
    public void setLabels(int tCase){
        this.removeAll();
        add(plateauJoueur);
        if(tCase>=10){
            Font serif = new Font("Serif", Font.BOLD, 10+tCase/8);
            JLabel colonne;
            JLabel ligne;
            for (int i = 0; i < 10; i++) {
                colonne = new JLabel((char)('A' + i) + "");
                ligne = new JLabel(""+(i+1));
                ligne.setFont(serif);
                colonne.setFont(serif);
                colonne.setBounds(margin+((2*i+1)*tCase)/2,0,margin,margin); 
                ligne.setBounds(margin/3,margin/3+((2*i+1)*tCase)/2,margin,margin);
                add(colonne);
                add(ligne);
            }
        }
    }
}