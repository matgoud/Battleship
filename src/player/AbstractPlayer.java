package src.player;

import java.util.Random;
import src.jeu.Bateau;
import src.jeu.Coord;
import src.jeu.Orientation;
import src.jeu.Plateau;

/**
 * Cette Classe abstraite implémente l'interface Joueur et définit les méthodes générales que les différents types de joueurs utiliseront.
 */
public abstract class AbstractPlayer implements Joueur{
    
    private String name;
    protected Plateau board;
    private Random rand;
    private boolean placeBoat;
    protected boolean lastHitTouche;
    protected boolean lastHitCoule;
    protected boolean shouldBoatsBeVisible;

    public AbstractPlayer(String name){
        this.name = name;
        this.rand = new Random();
        this.board = new Plateau(10,10);
        this.placeBoat = false;
        board.setOwner(this);
    }

    /**
     * Cette méthode abstraite initialisera la liste contenant toutes les cases du plateau (utile uniquement pour les joueurs robots (RandomPlayer et IAPlayer)).
     */
    public abstract void generatePossibleCases();

    /**
     * Cette méthode permet d'indiquer si le dernier coup du joueur a touché un bateau ennemi.
     * @param b Indique si le dernier coup touche un bateau ennemi.
     */
    public void setLastHitTouche(boolean b){
        lastHitTouche=b;
    }
    /**
     * Cette méthode permet d'indiquer si le dernier coup du joueur a coulé un bateau ennemi.
     * @param b Indique si le dernier coup coule un bateau ennemi.
     */
    public void setLastHitCoule(boolean b){
        lastHitCoule=b;
    }

    /**
     * Cette méthode va placer aléatoirement les 5 bateaux sur le plateau du Joueur.
     */
    @Override
    public void placeBoats(){
        board.setOwner(this);
        int nbrBateau = 0;
        int tab[] = new int[] {5, 4, 3, 3, 2};
        Orientation orientation[] = new Orientation[] {Orientation.HORIZONTAL,Orientation.VERTICAL};

        while(nbrBateau != 5){
            boolean valide = false;
            while(!valide){
                Coord position = new Coord(rand.nextInt(board.getTailleY()),rand.nextInt(board.getTailleX()));
                Bateau ship = new Bateau(position,orientation[rand.nextInt(2)],tab[nbrBateau]);

                if(ship.isInPlateau(board) && nbrBateau == 0){
                    board.ajoutBateau(ship);
                    valide = true;
                }
                else if(ship.isInPlateau(board)){
                    boolean valide2 = true;
                    // on teste si les cases de notre bateau sont déjà utilisées par un autre bateau
                    for(int i = 0; i < board.getNavires().size();i++){
                        for(int j = 0; j < ship.getTaille();j++){
                            if(board.getNavires().get(i).isCoordNear(ship.getCases().get(j))){
                                valide2 = false;
                            }
                        }
                    }
                    if(valide2){
                        board.ajoutBateau(ship);
                        valide = valide2;
                    }
                }

            }
            nbrBateau += 1;
        }
        placeBoat = true;
        generatePossibleCases();
    }

    /**
     * Cette méthode permet de changer le nom du joueur.
     * @param newName Nouveau nom du joueur.
     */
    @Override
    public void setName(String newName){
        name = newName;
    }

    /**
     * Cette méthode renvoie le nom du joueur.
     * @return Le nom du joueur.
     */
    @Override
    public String getName(){
        return name;
    }

    /**
     * Cette méthode renvoie le plateau associé au joueur.
     * @return Le plateau du joueur.
     */
    @Override
    public Plateau getPlateau(){
        return board;
    }

    /**
     * Cette méthode renvoie le générateur aléatoire associé au joueur.
     * @return Le générateur aléatoire du joueur.
     */
    @Override
    public Random getRand() {
        return rand;
    }

    /**
     * Cette méthode vérifie si tous les bateaux du plateau sont placés correctement.
     * @return true si tous les bateaux du plateau sont placés correctement, false sinon.
     */
    @Override
    public boolean getPlaceBoat(){
        return placeBoat;
    }

     /**
     * Cette méthode permet d'indiquer que les bateaux du plateau sont placés correctement.
     * @param b Indique si tous les bateaux du plateau sont placés correctement.
     */
    @Override
    public void setPlaceBoat(boolean b){
        placeBoat = b;
    }

    /**
     * Cette méthode vérifie si le dernier coup du joueur a touché un bateau.
     * @return true si le dernier coup du joueur a touché un bateau, false sinon.
     */
    public boolean isLastHitTouche() {
        return lastHitTouche;
    }

    /**
     * Cette méthode vérifie si le dernier coup du joueur a coulé un bateau.
     * @return true si le dernier coup du joueur a coulé un bateau, false sinon.
     */
    public boolean isLastHitCoule() {
        return lastHitCoule;
    }

    /**
     * Cette méthode vérifie si les bateaux devrait être visible s'ils ne sont pas coulé.
     * @return true si les bateaux devrait être visible s'ils ne sont pas coulé, false sinon.
     */
    public boolean getShouldBoatsBeVisible(){
        return shouldBoatsBeVisible;
    }

    /**
     * Cette méthode permet de changer si les bateaux doivent être visible s'ils ne sont pas coulé.
     * @param b La nouvelle valeur de shouldBoatsBeVisible
     */
    public void setShouldBoatsBeVisible(boolean  b){
        shouldBoatsBeVisible=b;
    }


}
