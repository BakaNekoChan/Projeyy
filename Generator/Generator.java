class Generator {

  private static int sizePlan = 100;


  //Cette méthode est celle utilisé pour générer la matrice. Elle retourne une matrice complète pour ne pas avoir à se soucier de que côté de la matrice est remplie.
  //Pour l'utilister, il suffit de donner le nombre de points que l'on veut traiter, et le générateur renvoie une matrice carrée de taille nbPoints avec la distance entre chaque points.
  public static double[][] generateMatrice (int nbPoints){
    int[][] plan;
    plan = generatePlane(nbPoints, Generator.sizePlan);
    int i = 0;
    int j = 0;
    double [][] resultat = new double[nbPoints][nbPoints];

    while(i < nbPoints){
      j = 0;
      while(j < nbPoints){
        if(i == j){
          resultat[i][j] = 0;
        }
        else{
          resultat [i][j] = Math.sqrt(Math.pow(plan[i][0]-plan[j][0], 2) + Math.pow(plan[i][1]-plan[j][1], 2));
        }
        j++;
      }
      i++;
    }
    return resultat;
  }

  private static int[][] generatePlane (int nbPoints, int sizePlan){
    int[][] points = new int[nbPoints][2];
    for (int i = 0; i < nbPoints; i++){
      points[i][0] = (int)(Math.random()*sizePlan);
      points[i][1] = (int)(Math.random()*sizePlan);
      System.out.println(points[i][0] + " " + points[i][1]);
    }
    return points;
  }
}
