package projeyy.brutforce3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import projeyy.generator.*;

public class BrutForce {
	private static int NOMBRE_VILLES;
	private static int nombreExec = 0;
	private static ArrayList<ArrayList<Integer>> listeCheminsOptimums = new ArrayList<ArrayList<Integer>>();
	private static double distanceOptimum;
	private static double[][] maMatrice = Generator.generateMatrice(NOMBRE_VILLES);
	private static MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
	private static ThreadMXBean bean = ManagementFactory.getThreadMXBean(); 
	private static PrintWriter pw = createPrintWriter();


	public static void main(String[] args) {
		for(int i = 3; i<11; i++){
			ArrayList<double[][]> testMatrix = Generator.useFileSerialize("Points:" + i + "_Matrices:20");
			NOMBRE_VILLES = testMatrix.get(0).length;
			long avgCPUTime = 0;
			double avgDist = 0;
			double moyExec = 0;
			for(double[][] matrice : testMatrix){
				long t1 = bean.getCurrentThreadCpuTime();
				maMatrice = matrice;
				nombreExec = 0;
				generateTree();
				pw.close();
				System.out.println(bean.getCurrentThreadCpuTime() - t1);
				moyExec += nombreExec;
				avgCPUTime += bean.getCurrentThreadCpuTime() - t1;
				avgDist += distanceOptimum;
			}
			System.out.println(moyExec / testMatrix.size());
			System.out.println("Moyenne distance " + i + " villes " + avgDist/testMatrix.size());
			System.out.println("Moyenne temps CPU "+ i + " villes " +avgCPUTime/testMatrix.size());
		}
	
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
