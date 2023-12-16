package src;


/**
 * Cette classe permet d'exécuter le jeu avec différents affichages et différents adversaires.
 */
public class main{

      /**
       * Cette méthode permet d'exécuter le jeu avec différents affichages et différents adversaires.
       * @param args Tableau de paramètres contenant le choix d'affichage et d'adversaire
       */
      public static void main(String args[]){
            // On gère les arguments ici et on les passe a la fonction execute
            if(args.length==1){
                  String affichage;
                  String tmp1 = args[0].toLowerCase();
                  if(tmp1.equals("interface")|| tmp1.equals("terminal")|| tmp1.equals("double")){
                        affichage = tmp1;
                  }
                  else{
                        System.out.println("Veuillez saisir un affichage valide.");
                        return;
                  }
                  
                  new execute(affichage);
            }
            else if(args.length==2){
                  String affichage;
                  String adversaire;
                  String tmp1 = args[0].toLowerCase();
                  String tmp2 = args[1].toLowerCase();
                  if(tmp1.equals("interface")|| tmp1.equals("terminal")||tmp1.equals("double")){
                        affichage = tmp1;
                  }
                  else{System.out.println("Veuillez saisir un affichage valide.");
                        return;
                  }
                  if(tmp2.equals("hvsh")|| tmp2.equals("hvsr")||tmp2.equals("rvsr")){
                        adversaire = tmp2;
                  }
                  else{System.out.println("Veuillez saisir un adversaire valide.");
                        return;
                  }
                  new execute(affichage,adversaire);
            }
            else{
                  new execute();
            }
      }

}