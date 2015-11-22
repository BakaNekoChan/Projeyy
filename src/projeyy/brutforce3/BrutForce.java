package projeyy.brutforce3;

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
	private static final int NOMBRE_VILLES = 9;
	private static int nombreExec = 0;
	private static ArrayList<ArrayList<Integer>> listeCheminsOptimums = new ArrayList<ArrayList<Integer>>();
	private static double distanceOptimum;
	private static double[][] maMatrice = Generator.generateMatrice(NOMBRE_VILLES);
	private static MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
	private static PrintWriter pw = createPrintWriter();


	public static void main(String[] args) {
		generateTree();
		System.out.println(listeCheminsOptimums);
		System.out.println(distanceOptimum);
		System.out.println(nombreExec);		
		pw.close();
	}
	
	public static void generateTree (){
		ArrayList<Integer> listeVilles = new ArrayList<Integer>();
		for(int i = 0; i < NOMBRE_VILLES; i++){
			listeVilles.add(i);
		}
		distanceOptimum = calculerDistance(listeVilles);
		listeCheminsOptimums.add(listeVilles);
		generateTree(listeVilles, new ArrayList<Integer>());
	}
	
	private static void generateTree (ArrayList<Integer> listeVilles,ArrayList<Integer> cheminActuel){
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
	
	private static double calculerDistance (ArrayList<Integer> listeVilles){
		double distance = 0;
		for(int i = 1; i < listeVilles.size(); i++){
			distance += maMatrice[listeVilles.get(i-1)][listeVilles.get(i)];
		}
		distance += maMatrice[listeVilles.get(0)][listeVilles.get(listeVilles.size()-1)];
		return distance;
	}
	
	private static PrintWriter createPrintWriter(){
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
