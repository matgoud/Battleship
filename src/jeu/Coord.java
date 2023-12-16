package src.jeu;
import java.util.ArrayList;
/**
 * Cette classe représente une des coordonnées de la carte de la bataille navale et les méthodes pour les utiliser.
 */
public class Coord{
    public int X;
    public int Y;

    public Coord(int y,int x){
        Y = y;
        X = x;
    }

    /**
     * Cette méthode renvoie les coordonnées du point.
     * @return Les coordonnées du point sous cette forme : (Y:y,X:x)
     */
    public String toString(){
        return "(Y:"+Y+",X:"+X+")";
    }

    /**
     * Cette méthode permet de vérifier si les coordonnées sont égales ; cette définition est spécialement  utile pour les ArrayList.contains().
     * @param o Object, qui est normalemnt la coordonnée du point à comparer
     * @return true si les coordonnées sont égales, false sinon
     */
    public boolean equals(Object o){ 
        if(!(o instanceof Coord)) return false;
        return equals((Coord) o);
    }
    
    /**
     * Cette méthode permet de vérifier si les coordonnées sont égales.
     * @param C Coordonnée du point à comparer
     * @return true si les coordonnées sont égales, false sinon
     */

    public boolean equals(Coord C){
        //System.out.println("Coord");
        if(Y == C.Y && X == C.X) return true;
        return false;
    }
    
    /**
     * Cette méthode permet de vérifier que les coordonnées sont adjacentes.
     * @param C Coordonnée du point à comparer
     * @return true si les coordonnées sont adjacentes orthogonalement (cas d'égalité exclu), false sinon
     */
    
    public boolean near(Coord C){
        int tmp=Math.abs(Y-C.Y)+Math.abs(X-C.X);
        if(tmp==1) return true;
        return false;
    }

    /**
     * Cette méthode permet d'obtenir la liste des coordonnées voisines d'un point.
     * @return La liste des coordonnées voisines d'un point
     */

    public ArrayList<Coord> listNear(){
        ArrayList<Coord> casesNear = new ArrayList<>();
        if(Y!=0) casesNear.add(new Coord(Y-1,X));
        if(Y!=9) casesNear.add(new Coord(Y+1,X));
        if(X!=0) casesNear.add(new Coord(Y,X-1));
        if(X!=9) casesNear.add(new Coord(Y,X+1));

        return casesNear;
    }

    /**
     * Cette méthode permet de savoir l'alignement des coordonnées sur une ligne ou une colonne.
     * @param C Coordonnée du point à comparer
     * @return 0 si les coordonnées sont égales, 1 si les points sont sur la même colonne, 2 si les points sont sur la même ligne, -1 sinon
     */

    public int estAligneAvec(Coord C){
        if(equals(C)) return 0; 
        if(C.X==X) return 1; 
        if(C.Y==Y) return 2; 
        return -1;
    }
    
}
