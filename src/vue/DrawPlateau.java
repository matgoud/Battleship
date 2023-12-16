package src.vue;

import src.jeu.*;
import src.player.*;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

/**
 * Cette Classe permet de dessiner un plateau.
 */
public class DrawPlateau extends JPanel{

    private final Plateau p;
    private IntOBJ tailleCase;
    private final Color BLEU = new Color(31, 97, 141);
    private final Color ROUGE = new Color( 176, 58, 46);
    private final Color VERT = new Color( 35, 155, 86);
    private final Color GRIS = new Color(  179, 182, 183 );
    private final Color BACKCOLOR = new Color(40,116,166);
        
    /*public DrawPlateau(Plateau p){
        this(p,80);
    }*/

    public DrawPlateau(Plateau p,IntOBJ tailleCase){

        super();
        this.p = p;
        this.tailleCase=tailleCase;
        setBackground(BACKCOLOR);
        
    }
    
    /**
     * Cette méthode permet de dessiner le plateau.
     */
    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        int x = 0;
        int y = 0;

        for (int i = 0; i < p.getTailleX(); i++) {
            x = 0;
            if(i != 0){
                y = y + tailleCase.val;
            }
            for (int j = 0; j < p.getTailleY(); j++) {
                g.setColor(BLEU);
                g.fillRect(x,y,tailleCase.val,tailleCase.val);
                if(p.getTabYX(i, j) == StateShot.WATER_HIT){
                    g.setColor(VERT);
                    g.fillOval(x+tailleCase.val/6,y+tailleCase.val/6,2*tailleCase.val/3,2*tailleCase.val/3);
                }else if(p.getTabYX(i, j) == StateShot.SHIP_HIT){
                    g.setColor(ROUGE);
                    g.fillOval(x+tailleCase.val/6,y+tailleCase.val/6,2*tailleCase.val/3,2*tailleCase.val/3);
                }
                g.setColor(Color.black);
                g.drawRect(x,y,tailleCase.val,tailleCase.val);
                x = x + tailleCase.val;
            }

        }

        g.setColor(GRIS);

        for(int i = 0; i<p.getNavires().size(); i++){

            Bateau b = p.getNavires().get(i);
            int t = b.getTaille();
            Orientation o = b.getOrientation();
            Coord depart = b.getCases().get(0);
    
            boolean isShipVisible = p.getOwner().getShouldBoatsBeVisible() || b.isSunk(p);

            if (isShipVisible) {
                if(o == Orientation.HORIZONTAL ){
                    g.drawRoundRect((16*depart.X+1)*tailleCase.val/16,(16*depart.Y+1)*tailleCase.val/16,(8*t-1)*tailleCase.val/8,tailleCase.val*7/8,tailleCase.val,tailleCase.val);
                }else if(o == Orientation.VERTICAL){
                    g.drawRoundRect((16*depart.X+1)*tailleCase.val/16,(16*depart.Y+1)*tailleCase.val/16,tailleCase.val*7/8,(8*t-1)*tailleCase.val/8,tailleCase.val,tailleCase.val);
                }
            }

        }

    }

}