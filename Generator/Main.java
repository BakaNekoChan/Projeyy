//Classe de test pour la classe Generator, seul Generator est utile pour le projet.
//Lancer Main pour voir comment fonctionne Generator, ou lisez la doc de Generator.

class Main {
   public static void main (String[] args){
     double maMatrice [][];
     maMatrice = Generator.generateMatrice(5);
     for(int i = 0; i < 5; i++){
       for(int j = 0; j < 5; j++){
         System.out.print(maMatrice[i][j] + " ");
       }
       System.out.println();
     }
   }
}
