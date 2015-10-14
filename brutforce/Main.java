package projeyy.brutforce;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import projeyy.generator.*;
import projeyy.brutforce.*;
import java.util.ArrayList;

public class Main{

  public static void main(String[] args) {
    ArrayList<ArrayList<Integer>> listeDesChemins = new ArrayList<ArrayList<Integer>>();
    ArrayList<ArrayList<Integer>> listeDesCheminsLesPlusCourts = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> listeVilles = new ArrayList<Integer>();

    ThreadMXBean bean = ManagementFactory.getThreadMXBean();
    long t1 = bean.getCurrentThreadUserTime();

    for(int i = 0; i < 10; i++){
      listeVilles.add(i);
    }
    double[][] matriceDistances = Generator.generateMatrice(listeVilles.size());

    BrutForce.generateTree(listeVilles, listeDesChemins);
    double distance = BrutForce.brutForce(matriceDistances, listeDesChemins, listeDesCheminsLesPlusCourts);
    System.out.println(distance);
    System.out.println(listeDesCheminsLesPlusCourts);
    System.out.println(bean.getCurrentThreadUserTime() - t1);
  }
}
