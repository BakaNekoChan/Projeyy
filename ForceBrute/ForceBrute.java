import java.util.*;

//Classe qui permet d'éxécuter ForceBrute
class ForceBrute{

  private static final int nbVilles = 4;

  //Teste de la génération des arbres
  public static void main (String[] args){
    ArrayList<ArrayList<Integer>> cheminsTab = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> listeVilles = new ArrayList<Integer>();
    for(int i = 0; i < nbVilles; i++){
      listeVilles.add(i);
    }
    generateTree(listeVilles, cheminsTab);
    System.out.println(cheminsTab);
    }

  //methode qui permet de générer un tableau de tous les chemins possible (cheminsTab) par rapport à une liste de villes données (représentées par un tableau de nombres)
  public static void generateTree (ArrayList<Integer> listeVilles, ArrayList<ArrayList<Integer>> cheminsTab){
    generateTree(listeVilles, cheminsTab, new ArrayList<Integer>());
  }

  //methode annexe utilisée par le premier generateTree
  public static void generateTree (ArrayList<Integer> listeVilles, ArrayList<ArrayList<Integer>> cheminsTab,ArrayList<Integer> cheminActuel){
    int x = 0;
    if(listeVilles.isEmpty()){
      cheminsTab.add(cheminActuel);
    }
    else{
      for(int i = 0; i < listeVilles.size(); i++){
        ArrayList<Integer> cheminActuelS = new ArrayList<Integer>(cheminActuel);
        cheminActuelS.add(listeVilles.get(i));
        x = listeVilles.remove(i);
        generateTree(listeVilles, cheminsTab, cheminActuelS);
        listeVilles.add(i,x);
        }
    }
  }
}
