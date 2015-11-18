package projeyy.brutforce;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;

import projeyy.generator.*;

public class Main{

	private static final int NOMBRE_VILLES = 9;
	
  public static void main(String[] args) {
    ArrayList<ArrayList<Integer>> listeDesChemins = new ArrayList<ArrayList<Integer>>();
    ArrayList<ArrayList<Integer>> listeDesCheminsLesPlusCourts = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> listeVilles = new ArrayList<Integer>();

    ThreadMXBean bean = ManagementFactory.getThreadMXBean(); //Objet permettant de mesurer le temps CPU prit par le programme
    long t1 = bean.getCurrentThreadUserTime();

    for(int i = 0; i < NOMBRE_VILLES; i++){ //Création de villes
      listeVilles.add(i);
    }
    
    double[][] matriceDistances = Generator.generateMatrice(listeVilles.size());

    BrutForce.generateTree(listeVilles, listeDesChemins);
    double distance = BrutForce.brutForce(matriceDistances, listeDesChemins, listeDesCheminsLesPlusCourts);
    
    
    System.out.println(distance);
    System.out.println(listeDesCheminsLesPlusCourts);
    
    
    System.out.println(bean.getCurrentThreadUserTime() - t1); //Affichage du temps passé
  }
}