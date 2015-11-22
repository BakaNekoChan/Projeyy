package projeyy.brutforceObservable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.ArrayList;
import projeyy.generator.*;

public class BrutForce {
	private int nbVilles;
	private  int nombreExec;
	private  ArrayList<ArrayList<Integer>> listeCheminsOptimums;
	private  double distanceOptimum;
	private  double[][] maMatrice;
	private  MemoryMXBean memoryBean;
	private  PrintWriter pw;

	public static void main(String[] args) {
		BrutForce bruteForce = new BrutForce(7);
		bruteForce.execute();
		System.out.println(bruteForce.listeCheminsOptimums);
		System.out.println(bruteForce.distanceOptimum);
		System.out.println(bruteForce.nombreExec);		
	}
	
	public BrutForce(int nbVilles){
		this.nbVilles = nbVilles;
		this.distanceOptimum = 0;
		this.listeCheminsOptimums = new ArrayList<ArrayList<Integer>>();
		nombreExec = 0;
	}
	
	public  void execute() {
		nombreExec = 0;
		memoryBean = ManagementFactory.getMemoryMXBean();
		pw = createPrintWriter();
		
		maMatrice = Generator.generateMatrice(nbVilles);
		generateTree();
		pw.close();
	}
	
	public  void generateTree (){
		ArrayList<Integer> listeVilles = new ArrayList<Integer>();
		for(int i = 0; i < nbVilles; i++){
			listeVilles.add(i);
		}
		distanceOptimum = calculerDistance(listeVilles);
		listeCheminsOptimums.add(listeVilles);
		generateTree(listeVilles, new ArrayList<Integer>());
	}
	
	private  void generateTree (ArrayList<Integer> listeVilles,ArrayList<Integer> cheminActuel){
		int villeRetenue = 0; 
		
		pw.println(memoryBean.getHeapMemoryUsage().getUsed());
		nombreExec ++; 
		if(listeVilles.isEmpty()){
			
			if(calculerDistance(cheminActuel) < distanceOptimum){
				listeCheminsOptimums.clear();
				listeCheminsOptimums.add(new ArrayList<Integer>(cheminActuel));
				distanceOptimum = calculerDistance(cheminActuel);
			}
			else if(calculerDistance(cheminActuel) == distanceOptimum){
				listeCheminsOptimums.add(new ArrayList<Integer>(cheminActuel));
			}
		}
		
		else{
			for(int i = 0; i < listeVilles.size(); i++){
				cheminActuel.add(listeVilles.get(i));
				villeRetenue = listeVilles.remove(i);
				generateTree(listeVilles, cheminActuel);
				cheminActuel.remove((Integer) villeRetenue);
				listeVilles.add(i, villeRetenue);
			}
		}
	}
	
	private  double calculerDistance (ArrayList<Integer> listeVilles){
		double distance = 0;
		for(int i = 1; i < listeVilles.size(); i++){
			distance += maMatrice[listeVilles.get(i-1)][listeVilles.get(i)];
		}
		distance += maMatrice[listeVilles.get(0)][listeVilles.get(listeVilles.size()-1)];
		return distance;
	}
	
	private  PrintWriter createPrintWriter(){
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
