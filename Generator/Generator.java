class Generator {

  private static int sizePlan = 100;

  public static int[][] generateMatrice (int nbPoints){
    int[][] plan;
    plan = generatePlane(nbPoints, this.sizePlan);
  }

  private static int[][] generatePlane (int nbPoints, int sizePlan){
    int[][] points = new int[nbPoints][2];
    for (int i = 0; i < nbPoints; i++){
      points[i][0] = (int)Math.random()*sizePlan;
      points[i][1] = (int)Math.random()*sizePlan;
    }
    return points;
  }
}
