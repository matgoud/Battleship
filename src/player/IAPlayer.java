package src.player;
import src.jeu.*;
import java.util.ArrayList;

/**
 * Cette classe représente un IA intelligente plus difficile à battre qu'un robot tirant au hasard ses coups.
 */

public class IAPlayer extends AbstractPlayer{
    
    //Toutes les cases possibles où un bateau peut se situer.
    private ArrayList<Coord> Pcases = new ArrayList<>();
    //Toutes les cases possibles où un bateau en particulier que l'on a touché mais pas coulé peut se situer; PcasesAdj est vide si aucun bateau n'est touché mais pas coulé.
    private ArrayList<Coord> PcasesAdj = new ArrayList<>();
    //Tous les coups déja effectué.
    private ArrayList<Coord> coupsEffectue = new ArrayList<>();
    //Tous les coups déja effectué qui ont touché le bateau que l'on a pas encore coulé ; posRepere est vide si aucun bateau n'est touché mais pas coulé.
    private ArrayList<Coord> posRepere = new ArrayList<>();
    //Chaque case de ce tableau indique le nombre de cases adjacentes qui n'est ni su comme vide, ni touché (que ce soit de l'eau ou un bateau).
    private int[][] casesAdjLibres;
    //Total de la somme des case de casesAdjLibres (CAL = Cases Adjacentes Libres).
    private int totCAL;

    public IAPlayer(String nom){
        super(nom);
        initCasesAdjLibres();
    }

    /**
     * Cette méthode initialise le nombre le nombre de cases adjacente libres pour chaque case dans un tableau, ainsi que le total de ce tableau.
     */
    public void initCasesAdjLibres(){
        casesAdjLibres= new int[super.getPlateau().getTailleY()][super.getPlateau().getTailleX()];
        totCAL=0;
        for(int i = 0; i < super.getPlateau().getTailleY(); i++){
            for(int j = 0; j < super.getPlateau().getTailleX(); j++){
                casesAdjLibres[i][j]=4;
                if(i==0||i==super.getPlateau().getTailleY()-1) casesAdjLibres[i][j]--;
                if(j==0||j==super.getPlateau().getTailleX()-1) casesAdjLibres[i][j]--;
                totCAL+=casesAdjLibres[i][j];
                
            }
        }
    }
    /**
     * Cette méthode retire la Coordonnée donnée en paramètre de la liste Pcases et met a jour le nombre de cases adjacente libres.
     * @param C Coordonnée à retirer
     */
    public void removeCasePossible(Coord C){
        Pcases.remove(C);
        if(casesAdjLibres[C.Y][C.X]!=0){
            totCAL-=casesAdjLibres[C.Y][C.X];
            casesAdjLibres[C.Y][C.X]=0;
            C.listNear().forEach(c ->{
                if(casesAdjLibres[c.Y][c.X]>0){
                    casesAdjLibres[c.Y][c.X]--;
                    totCAL--;
                }
            });
        }
    }

    /**
     * Cette méthode tire une coordonnée au hasard pondéré selon le nombre de cases adjacentes non vide ou touché de chaque case.
     * @return Une coordonnée tiré
     */
    public Coord randomCasePondere(){
        int rnd = super.getRand().nextInt(totCAL);
        int tot = 0;
        for(int i = 0; i < super.getPlateau().getTailleY(); i++){
            for(int j = 0; j < super.getPlateau().getTailleX(); j++){
                tot+=casesAdjLibres[i][j];
                if(tot>rnd) return new Coord(i,j);
            }
        }
        //au cas où, return une coordonnée possible aléatoire
        return Pcases.get(super.getRand().nextInt(Pcases.size())); 
    }

    /**
     * Cette méthode initialise la liste contenant toutes les cases du plateau.
     */
    @Override
    public void generatePossibleCases(){
        for(int i = 0; i < super.getPlateau().getTailleY(); i++)
            for(int j = 0; j < super.getPlateau().getTailleX(); j++){
                Coord C = new Coord(i, j);
                Pcases.add(C);
            }

    }

    /**
     * Cette méthode est appelée lorsque ce joueur a coulé un bateau ; elle permet de remettre à zéro certaines variables de l'IA.
     * @return Un nouveau coup
     */
    public Coord coupInit(){
        lastHitTouche=false;
        lastHitCoule=false;
        posRepere.clear();
        PcasesAdj.clear();
        return coup(-1,-1);
    }

    /**
     * Cette méthode permet au joueur de lancer un coup (tir pour la bataille navale).
     * @param Y Ordonnée du coup.
     * @param X Abscisse du coup.
     * @return Coordonnée correspondant au coup.
     */
    @Override
    public Coord coup(int Y,int X){
        Coord C;
        if(Y < 1 || X < 1){ //Si on souhaite un coup aléatoire, on appelle coup avec Y et X = -1 ; normalement, on appelle toujours coup ainsi
            //On regarde si le dernier coup joué a coulé un bateau adverse
            if(lastHitCoule){
                        posRepere.add(coupsEffectue.get(coupsEffectue.size()-1));
                        posRepere.forEach(c ->{
                            c.listNear().forEach(c2 -> {
                                //Les bateaux ne peuvent pas se toucher, on enlève donc des cases possibles toutes les cases voisines au bateau coulé
                                removeCasePossible(c2);
                            });
                        });
                        return coupInit();
                    }
            else{
                //On regarde si on ne possède pas d'information qu'un bateau se situe à un endroit, information que on possède si on a touché mais pas coulé un bateau
                if(PcasesAdj.size()==0){
                    //Si on a touché un bateau en dernier coup, on contruit la liste des cases possibles où peut se situer une autre case de ce bateau
                    if(lastHitTouche){
                        posRepere.add(coupsEffectue.get(coupsEffectue.size()-1));
                        posRepere.get(posRepere.size()-1).listNear().forEach(c ->{
                            if(Pcases.contains(c))PcasesAdj.add(c);
                        });
                        C = PcasesAdj.get(super.getRand().nextInt(PcasesAdj.size()));
                    }
                    else{
                        //Si on a aucune information, on attaque une case au hasard
                        C = randomCasePondere();
                    }
                }
                else{ //Si on a des informations dans PcasesAdj :
                    PcasesAdj.remove(coupsEffectue.get(coupsEffectue.size()-1));
                    if(lastHitTouche){ 
                    //Si on a touché au moins 2 fois un bateau sans le couler, on peut savoir s'il est placé verticalement ou horizontalement, 
                    //Ce qui laisse au plus 2 cases possibles pour la suite de ce bateau
                        posRepere.add(coupsEffectue.get(coupsEffectue.size()-1));
                        if(posRepere.get(0).estAligneAvec(posRepere.get(posRepere.size()-1)) == 1){ //Bateau positionné verticalement
                            PcasesAdj.clear();
                            posRepere.forEach(c ->{
                                Coord tmp1= new Coord(c.Y-1,c.X);
                                if(Pcases.contains(tmp1)) PcasesAdj.add(tmp1);
                                Coord tmp2= new Coord(c.Y+1,c.X);
                                if(Pcases.contains(tmp2)) PcasesAdj.add(tmp2);
                            });
                        }
                        else if(posRepere.get(0).estAligneAvec(posRepere.get(posRepere.size()-1)) == 2){ //Bateau positionné horizontalement
                            PcasesAdj.clear();
                            posRepere.forEach(c ->{
                                Coord tmp1= new Coord(c.Y,c.X-1);
                                if(Pcases.contains(tmp1)) PcasesAdj.add(tmp1);
                                Coord tmp2= new Coord(c.Y,c.X+1);
                                if(Pcases.contains(tmp2)) PcasesAdj.add(tmp2);
                            });
                        }
                        if(PcasesAdj.size()!=0){ // Cette condition est une sécurité, mais normalement il n'est jamais égal a 0
                            C = PcasesAdj.get(super.getRand().nextInt(PcasesAdj.size()));
                        }
                        else return coupInit();
                    }
                    else{ //Si on connait des cases proche d'un bateau, mais que le dernier coup était raté
                        if(PcasesAdj.size()!=0){ // Cette condition est une sécurité, mais normalement il n'est jamais égal a 0
                            C = PcasesAdj.get(super.getRand().nextInt(PcasesAdj.size()));
                        }
                        else return coupInit();
                    }
                }
            }
        }
        else { 
            C = new Coord(Y-1,X-1);
            //System.out.println(C+"PAS NORMAL");
        }
        removeCasePossible(C);
        coupsEffectue.add(C);
        return C;
    }

    /**
     * Cette méthode renvoie la liste de toutes les cases possibles où un bateau peut se situer.
     * @return La liste de toutes les cases où un bateau peut se situer
     */
    public ArrayList<Coord> getPcases() {
        return Pcases;
    }

    /**
     * Cette méthode renvoie la liste de toutes les cases adjacentes à un bateau touché non coulé et où ce bateau peut se prolonger, s'il y en a un.
     * @return La liste de toutes les cases adjacentes à un bateau touché non coulé et où ce bateau peut se prolonger.
     */
    public ArrayList<Coord> getPcasesAdj() {
        return PcasesAdj;
    }

    /**
     * Cette méthode renvoie la liste de toutes les coups déjà effectués.
     * @return La liste de toutes les coups déjà effectués.
     */
    public ArrayList<Coord> getCoupsEffectue() {
        return coupsEffectue;
    }

    /**
     * Cette méthode renvoie la liste de toutes les coordonnées qui font partie d'un bateau touché mais pas coulé,s'il y en a un.
     * @return La liste de toutes les coordonnées qui font partie d'un bateau touché mais pas coulé.
     */
    public ArrayList<Coord> getPosRepere() {
        return posRepere;
    }

    /**
     * Cette méthode renvoie la somme des case du tableau casesAdjLibres.
     * @return La somme des case du tableau casesAdjLibres.
     */
    public int getTotCAL() {
        return totCAL;
    }

}
