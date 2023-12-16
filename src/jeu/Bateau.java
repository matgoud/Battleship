package src.jeu;
import java.util.ArrayList;
/**
 * Cette classe permet de représenter un bateau pour la bataille navale et les méthodes pour l'utiliser
 */
public class Bateau{
    
    private ArrayList<Coord> cases = new ArrayList<>(); //Liste des coordonnées du bateau
    private int taille; //Taille du bateau
    private Orientation orientation; //Orientation du bateau

    public Bateau (Coord depart,Orientation orientation,int t){
        int x = 1;
        int y = 1;
        taille = t;
        this.orientation = orientation;
     
        if(orientation == Orientation.HORIZONTAL){
            x = 1;
            y = 0;
        }else if(orientation == Orientation.VERTICAL){
            x = 0;
            y = 1;
        }
        for(int i = 0; i < taille;i++){
            Coord d = new Coord(depart.Y + y*i,depart.X + x*i);
            cases.add(d);
        }

    }
    
    /**
     * Cette méthode renvoie la liste des coordonnées du bateau.
     * @return La liste des coordonnées du bateau sous ce format : [(Y:y1,X:X1),...,(Y:yn,X:Xn)]
     */

    public String toString(){
        String str="[";
        for(int i = 0; i < taille; i++){
            str+=cases.get(i).toString();
            if(i!=taille-1) str+=",";
        }
        str+="]";
        return str;
    }

    /**
     * Cette méthode vérifie si les coordonnées données en paramètre sont dans le bateau.
     * @param C Coordonnées à vérifier
     * @return true si les coordonnées sont dans le bateau, false sinon
     */
    public boolean isCoordInside(Coord C){
        for(int i = 0; i < taille; i++){
            if(C.equals(cases.get(i))){
                return true;
            }
        }
        return false;
    }

    /**
     * Cette méthode vérifie si les coordonnées données en paramètre sont adjacentes orthogonalement ou dans le bateau.
     * @param C Coordonnées à vérifier
     * @return true si les coordonnées sont adjacentes orthogonalement ou dans le bateau, false sinon
     */
    public boolean isCoordNear(Coord C){
        for(int i = 0; i < taille; i++){
            if(cases.get(i).near(C) || cases.get(i).equals(C)){
                return true;
            }
        }
        return false;
    }
    /**
     * Cette méthode vérifie si le plateau donné en paramètre contient les coordonnées du bateau.
     * @param P Plateau à vérifier
     * @return true si le plateau contient les coordonnées du bateau, et false sinon
     */
    public boolean isInPlateau(Plateau P){
        if(cases.get(0).X >= 0 && cases.get(0).X < P.getTailleX() && cases.get(0).Y >= 0 && cases.get(0).Y < P.getTailleY()){
            if(cases.get(taille-1).X >= 0 && cases.get(taille-1).X < P.getTailleX() && cases.get(taille-1).Y >= 0 && cases.get(taille-1).Y < P.getTailleY()){
                return true;
            }    
        }
        return false;
    }
    /**
     * Cette méthode renvoie la liste des coordonnées du bateau.
     * @return La liste des coordonnées du bateau
     */
    public ArrayList<Coord> getCases(){
        return cases;
    }
    /**
     * Cette méthode renvoie la taille du bateau
     * @return La taille du bateau
     */
    public int getTaille(){
        return taille;
    }
    /**
     * Cette méthode renvoie l'orientation du bateau
     * @return L'orientation du bateau
     */
    public Orientation getOrientation() {
        return orientation;
    }
    /**
     * Cette méthode vérifie si le bateau est coulé ou non
     * @param P Plateau où le bateau se situe
     * @return true si le bateau est coulé, et false sinon
     */
    public boolean isSunk(Plateau P){
        for(int i = 0; i < taille; i++){
            Coord c = cases.get(i);
            if(P.getTabYX(c.Y, c.X) == StateShot.SHIP)return false;
        }
        return true;
    }
    
}
