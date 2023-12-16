package src.jeu;
import java.io.Console;
import java.util.Scanner; 

/**
 * Classe utilitaire pour l'utilisation de la console
 */
public class terminalutil {
    
    //Déclaration des couleurs ANSI
	public static String RESET = "\u001B[0m";
	public static String BLACK = "\u001B[30m";
	public static String RED = "\u001B[31m";
	public static String GREEN = "\u001B[32m";
	public static String YELLOW = "\u001B[33m";
	public static String BLUE = "\u001B[34m";
	public static String PURPLE = "\u001B[35m";
	public static String CYAN = "\u001B[36m";
	public static String WHITE = "\u001B[37m";
    
    private int tailleX;
    private int tailleY;
    public static Scanner sc;  
    
    public terminalutil(int tailleY,int tailleX){
        this.tailleX = tailleX;
        this.tailleY = tailleY;
    }

    /**
     * Cette méthode permet de modifier le scanner utilisé pour l'utilisation de la console
     * @param scan Scanner utilisé pour l'utilisation de la console
     */
    public void setScanner(Scanner scan){
        sc = scan;
    }

    /**
     * Cette méthode permet tracer la base du jeu
     */
    public void clear(){
        for(int y = 0; y < tailleY;y++)
            for(int x = 0; x < tailleX;x++){
                gotoyx(y,x);
                if((y == 1 || y == tailleY-1) && (x == 1 || x == tailleX-1)){
                    System.out.printf("+");
                }else if(y == 1 || y == tailleY-1 ){
                    System.out.printf("-");
                }else if(x == 1 || x == tailleX-1){
                System.out.printf("|");
                }else{
                    System.out.printf(" ");
                }
            }
    }

    /**
     * Cette méthode permet de changer la couleur de la case
     * @param y Ordonnée de la case
     * @param x Abscisse de la case
     */
    public void gotoyx(int y, int x)
    {
        System.out.printf("%c[%d;%df",0x1B,y,x);
    }

    /**
     * Cette méthode permet d'afficher une boîte de dialogue
     * @param contenu contenu à afficher
     */
    private void dialogBox(String contenu){
        int len = contenu.length();
        int boxSizeX = len + 4;
        int boxSizeY = 4;
        int posX = tailleX/2 - len/2 -2;
        int posY = (tailleY -(tailleY/3)) - 1;
        for(int y = 0; y < boxSizeY; y++)
            for(int x = 0; x < boxSizeX; x++){
                gotoyx(posY+y,posX + x);
                if((y == 0 || y == boxSizeY-1) && (x == 0 || x == boxSizeX-1)){
                    System.out.print("+");
                }else if(y == 0 || y == boxSizeY-1){
                    System.out.print("-");
                }else if(x == 0 || x == boxSizeX-1){
                    System.out.print("|");
                }else{
                    System.out.print(" ");
                }
            }
        int dia_posX = posX + 2;
        int dia_posY = posY + 1;
        gotoyx(dia_posY, dia_posX);
        System.out.print(contenu);
    }

    /**
     * Cette méthode permet d'afficher une boîte de dialogue contenant une Yes/No question et renvoie la réponse
     * @param contenu Question à afficher
     * @return true si la réponse est Y, false sinon
     */
    public boolean YNdialogBox(String contenu){
        int len = contenu.length();
        int posX = tailleX/2 - len/2 -2;
        int posY = (tailleY -(tailleY/3)) - 1;
        dialogBox(contenu);
        gotoyx(posY+2, posX + len/2 -1);
        System.out.print("(Y/N)");  
        char c = sc.next().charAt(0);
        if (c == 'y' || c == 'Y')return true;
        return false;
    }

    /**
     * Cette méthode permet d'afficher une boîte de dialogue contenant une question et renvoie la réponse
     * @param contenu Question à afficher
     * @return La réponse à la question
     */
    public String InputdialogBox(String contenu){
        int len = contenu.length();
        int posX = tailleX/2 - len/2 -2;
        int posY = (tailleY -(tailleY/3)) - 1;
        dialogBox(contenu);
        gotoyx(posY+2, posX + len/2 -1); 
        String inputStr = sc.next ();
        return inputStr;
    }

    /**
     * Cette méthode permet d'afficher une boîte de dialogue contenant une affirmation
     * @param contenu Texte à afficher
     */
    public void OKdialogBox(String contenu){
        int len = contenu.length();
        int posX = tailleX/2 - len/2 -2;
        int posY = (tailleY -(tailleY/3)) - 1;
        dialogBox(contenu);
        gotoyx(posY+2, posX + len/2 -1);
        System.out.print("[OK]");
        Console c = System.console();
        c.readLine();
    }

    /**
     * Cette méthode permet d'afficher une boîte de dialogue contenant une question demandant de répondre par un entier
     * @param input Question à afficher
     * @return La réponse à la question
     */
    public int NumdialogBox(String input) {
        String inputStr = InputdialogBox(input);
        int num = 0;
        try {
            num = Integer.parseInt(inputStr);
        }catch (Exception e) {
            OKdialogBox("veuillez rentrer une valeur numérique!");
            return NumdialogBox(input);
        }
        return num;
    }

    /**
     * Cette méthode renvoie la largeur du tableau
     * @return La largeur du tableau
     */
    public int getTailleX() {
        return tailleX;
    }

    /**
     * Cette méthode permet de modifier la largeur du tableau
     * @param tailleX
     */
    public void setTailleX(int tailleX) {
        this.tailleX = tailleX;
    }

    /**
     * Cette méthode renvoie la hauteur du tableau
     * @return La hauteur du tableau
     */
    public int getTailleY() {
        return tailleY;
    }

    /**
     * Cette méthode permet de modifier la hauteur du tableau
     * @param tailleY
     */
    public void setTailleY(int tailleY) {
        this.tailleY = tailleY;
    }

}