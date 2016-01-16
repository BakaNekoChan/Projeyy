package projeyy.brutforce;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;

import projeyy.generator.Generator;

public class BrutForce {
	private static PrintWriter pw = createPrintWriter();
	private static MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
	private static int nbExec = 0;
	/*entrees: demi-matrice contenant les distances entre les differentes villes
	sorties: retourne la valeur du chemin le plus cours
	execution: calcul la valeur de chaque chemin et stocke le plus court
	*/
	public static double brutForce (double[][] matriceDistances, ArrayList<ArrayList<Integer>> listeDesChemins, ArrayList<ArrayList<Integer>> listeDesCheminsLesPlusCourts){ //Le nombre de villes dans les chemins doit être égal à la largeur de la matrice
		double [] listeDesDistancesTotales = new double[listeDesChemins.size()];
		
		for(int i = 0; i < listeDesChemins.size(); i++){
			ArrayList<Integer> cheminActuel = listeDesChemins.get(i);
			int villePrecedente = -1;
			for(int villeActuelle : cheminActuel){
				if(villePrecedente != -1) listeDesDistancesTotales[i] += matriceDistances[villePrecedente][villeActuelle];
				villePrecedente = villeActuelle;
			}
			listeDesDistancesTotales[i] += matriceDistances[cheminActuel.get(0)][cheminActuel.get(cheminActuel.size()-1)];
		}

		double distanceMinimum = listeDesDistancesTotales[0];
		listeDesCheminsLesPlusCourts.add(listeDesChemins.get(0));

		for(int i = 1; i < listeDesDistancesTotales.length; i++){
			if(listeDesDistancesTotales[i] == distanceMinimum){
				listeDesCheminsLesPlusCourts.add(listeDesChemins.get(i));
			}
			else if(listeDesDistancesTotales[i] < distanceMinimum){
				distanceMinimum = listeDesDistancesTotales[i];
				listeDesCheminsLesPlusCourts.clear();
				listeDesCheminsLesPlusCourts.add(listeDesChemins.get(i));
			}
		}
		
		pw.close();
		return distanceMinimum;
	}

	//methode qui permet de générer un tableau de tous les chemins possible (cheminsTab) par rapport à une liste de villes données (représentées par un tableau de nombres)
	public static void generateTree (ArrayList<Integer> listeVilles, ArrayList<ArrayList<Integer>> cheminsTab){
		//cheminsTab = new ArrayList<ArrayList<Integer>>();
		generateTree(listeVilles, cheminsTab, new ArrayList<Integer>());
	}

	//methode annexe utilisée par le premier generateTree
	private static void generateTree (ArrayList<Integer> listeVilles, ArrayList<ArrayList<Integer>> cheminsTab,ArrayList<Integer> cheminActuel){
		int x = 0;
		pw.println(memoryBean.getHeapMemoryUsage().getUsed()); //écriture de la mémoire dans le fichier.
		nbExec ++;
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
	
	private static PrintWriter createPrintWriter(){ //fonction qui permet d'initialiser l'ouverture du fichier.
		PrintWriter pw = null;
		try{
			pw = new PrintWriter (new BufferedWriter (new FileWriter (new File("monGraph"))));
		}
		catch(IOException o){
			System.out.println("ça marche pas");
			System.exit(0);
		}
		return pw;
	}

	public static void main(String[] args) {
		for(int i = 8; i<9; i++){
			System.out.println(i + "villes:");
			testFunction("Points:" + i + "_Matrices:20");
		}
	}
	
	public static void testFunction(String testFile){
	    ArrayList<ArrayList<Integer>> listeDesChemins = new ArrayList<ArrayList<Integer>>();
		ArrayList<double[][]> testMatrix = Generator.useFileSerialize(testFile);
	    ArrayList<ArrayList<Integer>> listeDesCheminsLesPlusCourts = new ArrayList<ArrayList<Integer>>();
	    ThreadMXBean bean = ManagementFactory.getThreadMXBean(); //Objet permettant de mesurer le temps CPU prit par le programme
	    ArrayList<Integer> listeVilles = new ArrayList<Integer>();
		long avgCPUTime = 0;
		double distance = 0;
		
	    for(int j = 0; j < testMatrix.get(0).length; j++){ //Création de villes
		      listeVilles.add(j);
		 }
		
		for(int i = 0; i<testMatrix.size(); i++){
		    long t1 = bean.getCurrentThreadUserTime();		    
		    double[][] matriceDistances = testMatrix.get(i);
		    nbExec = 0;
		    BrutForce.generateTree(listeVilles, listeDesChemins);
		    System.out.println(nbExec);
		    distance += BrutForce.brutForce(matriceDistances, listeDesChemins, listeDesCheminsLesPlusCourts);
		    avgCPUTime += bean.getCurrentThreadUserTime() - t1;
		    System.out.println(avgCPUTime);
		}
		distance = distance/testMatrix.size();
		avgCPUTime = avgCPUTime / testMatrix.size();
		System.out.println("Moyenne temps: " + avgCPUTime);
		System.out.println("Moyenne distance: " + distance);
	}
}