package projeyy.backtrack;

import java.util.ArrayList;
import projeyy.generator.*;

//Cette version de BruteForce ne stocke pas tous les chemins dans un tableau, et sectionnes les branches de l'arbre des chemins
//quand la distance est déjà plus longue que celle minimum en cours de génération, ce qui permet d'améliorer le temps
//d'exécution et empêche la surcharge de la mémoire.

public class BrutForce {
	private static final int NOMBRE_VILLES = 8;
	private static int nombreExec = 0;
	private static ArrayList<ArrayList<Integer>> listeCheminsOptimums = new ArrayList<ArrayList<Integer>>();
	private static double distanceOptimum;
	private static double[][] maMatrice = Generator.generateMatrice(NOMBRE_VILLES);
	
	
	
	
	
	
	
	
	static ArrayList maListe = new ArrayList();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public static void main(String[] args) {
		generateTree();
		System.out.println(listeCheminsOptimums);
		System.out.println(distanceOptimum);
		System.out.println(nombreExec);		
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
		//System.out.println(cheminActuel);
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
				if(calculerDistance(cheminActuelS) < distanceOptimum){
					generateTree(listeVilles, cheminActuelS);
				}
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
}
