package projeyy.brutforce2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.ArrayList;
import projeyy.generator.*;

//Cette version de BruteForce ne stocke pas tous les chemins dans un tableau.

public class BrutForce {
	private static final int NOMBRE_VILLES = 9;
	private static int nombreExec = 0;
	private static ArrayList<ArrayList<Integer>> listeCheminsOptimums = new ArrayList<ArrayList<Integer>>();
	private static double distanceOptimum;
	private static double[][] maMatrice = Generator.generateMatrice(NOMBRE_VILLES);
	private static MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
	private static PrintWriter pw = createPrintWriter();


	public static void main(String[] args) {
		generateTree();
		//Décomenter pour afficher la matrice.
		/*for(int i = 0; i < maMatrice.length; i++){
			for(int j = 0; j < maMatrice.length; j++){
				System.out.print(maMatrice[i][j] + " ");
			}
			System.out.println();
		}*/
		
		System.out.println(listeCheminsOptimums);
		System.out.println(distanceOptimum);
		System.out.println(nombreExec);		
		pw.close();
	}
	
	//méthode qui permet de générer les chemins les plus courts disponnibles dans le tableau static listCheminsOptimums.
	public static void generateTree (){
		ArrayList<Integer> listeVilles = new ArrayList<Integer>();
		for(int i = 0; i < NOMBRE_VILLES; i++){
			listeVilles.add(i);
		}
		distanceOptimum = calculerDistance(listeVilles);
		listeCheminsOptimums.add(listeVilles);
		
		
		generateTree(listeVilles, new ArrayList<Integer>());
	}

	//methode annexe utilisée par le premier generateTree
	private static void generateTree (ArrayList<Integer> listeVilles,ArrayList<Integer> cheminActuel){
		int x = 0;
		
		pw.println(memoryBean.getHeapMemoryUsage().getUsed()); //écriture de la mémoire dans le fichier.
		nombreExec ++;
		if(listeVilles.isEmpty()){
			if(calculerDistance(cheminActuel) < distanceOptimum){
				listeCheminsOptimums.clear();
				listeCheminsOptimums.add(cheminActuel);
				distanceOptimum = calculerDistance(cheminActuel);
			}
			else if(calculerDistance(cheminActuel) == distanceOptimum){
				listeCheminsOptimums.add(cheminActuel);
			}
		}
		else{
			for(int i = 0; i < listeVilles.size(); i++){
				ArrayList<Integer> cheminActuelS = new ArrayList<Integer>(cheminActuel);
				cheminActuelS.add(listeVilles.get(i));
				x = listeVilles.remove(i);
				//if(calculerDistance(cheminActuelS) < distanceOptimum){ //Unique différence avec BackTrack
					generateTree(listeVilles, cheminActuelS);
				//}
				listeVilles.add(i,x);
			}
		}
	}
	
	private static double calculerDistance (ArrayList<Integer> listeVilles){
		double distance = 0;
		for(int i = 1; i < listeVilles.size(); i++){
			distance += maMatrice[listeVilles.get(i-1)][listeVilles.get(i)];
		}
		distance += maMatrice[listeVilles.get(0)][listeVilles.get(listeVilles.size()-1)];
		return distance;
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
}
