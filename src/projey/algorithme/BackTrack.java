package projey.algorithme;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.ArrayList;
import projeyy.generator.*;
import projeyy.projeyyInterface.Point;

//Cette version de BruteForce ne stocke pas tous les chemins dans un tableau, et sectionnes les branches de l'arbre des chemins
//quand la distance est déjà plus longue que celle minimum en cours de génération, ce qui permet d'améliorer le temps
//d'exécution et empêche la surcharge de la mémoire.

public class BackTrack extends Algorithme{
	
	private int nombreExec = 0;
	private ArrayList<ArrayList<Integer>> listeCheminsOptimums = new ArrayList<ArrayList<Integer>>();
	private double distanceOptimum;
	private  double[][] maMatrice;
	private PrintWriter pw;
	private MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
	private ArrayList<Point> mesPoints;
	private int nbVilles;
	
	public static void main(String[] args) {
		BackTrack bruteForce = new BackTrack(13);
		bruteForce.execute();
		System.out.println(bruteForce.listeCheminsOptimums);
		System.out.println(bruteForce.distanceOptimum);
	}
	
	public BackTrack(int nbVilles){
		this.nbVilles = nbVilles;
		this.distanceOptimum = 0;
		this.listeCheminsOptimums = new ArrayList<ArrayList<Integer>>();
		nombreExec = 0;
		this.mesPoints = new ArrayList<Point>();
	}
	
	@Override
	public ArrayList<Point> getPoints() {
		return mesPoints;
	}

	@Override
	public ArrayList<Integer> getPlusCourtChemin() {
		return listeCheminsOptimums.get(0);
	}

	@Override
	public double getDistancePlusCourtChemin() {
		return distanceOptimum;
	}
	
	@Override
	public void execute() {
		nombreExec = 0;
		memoryBean = ManagementFactory.getMemoryMXBean();
		pw = createPrintWriter();
		mesPoints = Generator.generatePlane(nbVilles);
		maMatrice = Generator.generateMatrice(mesPoints);
		generateTree();
		pw.close();
	}

	//méthode qui permet de générer les chemins les plus courts disponnibles dans le tableau static listCheminsOptimums.
	public void generateTree (){
		ArrayList<Integer> listeVilles = new ArrayList<Integer>();
		for(int i = 0; i < nbVilles; i++){
			listeVilles.add(i);
		}
		//System.out.println(glouton(listeVilles));
		distanceOptimum = calculerDistance(glouton(listeVilles));
		listeCheminsOptimums.add(listeVilles);


		generateTree(listeVilles, new ArrayList<Integer>());
	}

	//methode annexe utilisée par le premier generateTree
	private void generateTree (ArrayList<Integer> listeVilles,ArrayList<Integer> cheminActuel){
		int x = 0;
		
		//nombreExec ++;
		//pw.println(memoryBean.getHeapMemoryUsage().getUsed()); //écriture de la mémoire dans le fichier.
		
		if(listeVilles.isEmpty()){
			if(calculerDistance(cheminActuel) < distanceOptimum){
				listeCheminsOptimums.clear();
				listeCheminsOptimums.add(cheminActuel);
				distanceOptimum = calculerDistance(cheminActuel);
				notifierObservateurs();
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
				if(calculerDistance(cheminActuelS) <= distanceOptimum){
					generateTree(listeVilles, cheminActuelS);
				}
				listeVilles.add(i,x);
			}
		}
	}

	private double calculerDistance (ArrayList<Integer> listeVilles){
		double distance = 0;
		for(int i = 1; i < listeVilles.size(); i++){
			distance += maMatrice[listeVilles.get(i-1)][listeVilles.get(i)];
		}
		distance += maMatrice[listeVilles.get(0)][listeVilles.get(listeVilles.size()-1)];
		return distance;
	}

	private ArrayList<Integer> glouton(ArrayList<Integer> listeVilles){
		ArrayList<Integer> monCheminGlouton = new ArrayList<Integer>();
		ArrayList<Integer> listeVillesRestantes = new ArrayList<Integer>(listeVilles);
		monCheminGlouton.add(listeVillesRestantes.remove(0));
		for(int j = 0; j < nbVilles-1; j++){
			double distanceMinimum = Double.MAX_VALUE;
			Integer villeMoinsLoin = 0;
			for(Integer i:listeVillesRestantes){
				if(maMatrice[monCheminGlouton.get(monCheminGlouton.size()-1)][i] < distanceMinimum){
					distanceMinimum = maMatrice[monCheminGlouton.get(monCheminGlouton.size()-1)][i];
					villeMoinsLoin = i;
				}
			}
			listeVillesRestantes.remove((Integer)villeMoinsLoin);
			monCheminGlouton.add(villeMoinsLoin);
		}
		return monCheminGlouton;
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
