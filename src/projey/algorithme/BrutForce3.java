package projey.algorithme;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.ArrayList;
import java.util.Observable;

import projeyy.generator.*;
import projeyy.projeyyInterface.Point;

public class BrutForce3 extends Algorithme {
	private int nbVilles;
	private  int nombreExec;
	private ArrayList<Point> mesPoints;
	private  ArrayList<ArrayList<Integer>> listeCheminsOptimums;
	private  double distanceOptimum;
	private  double[][] maMatrice;
	private  MemoryMXBean memoryBean;
	private  PrintWriter pw;

	public static void main(String[] args) {
		BrutForce3 bruteForce = new BrutForce3(7);
		bruteForce.execute();
		System.out.println(bruteForce.listeCheminsOptimums);
		System.out.println(bruteForce.distanceOptimum);
		System.out.println(bruteForce.nombreExec);		
	}
	
	public BrutForce3(int nbVilles){
		this.nbVilles = nbVilles;
		this.distanceOptimum = 0;
		this.listeCheminsOptimums = new ArrayList<ArrayList<Integer>>();
		nombreExec = 0;
		this.mesPoints = new ArrayList<Point>();
	}
	
	public  void execute() {
		nombreExec = 0;
		memoryBean = ManagementFactory.getMemoryMXBean();
		pw = createPrintWriter();
		mesPoints = Generator.generatePlane(nbVilles);
		maMatrice = Generator.generateMatrice(mesPoints);
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
				notifierObservateurs();
				System.out.println("Notification !");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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
	
	public void notifierObservateurs()
    {
            setChanged();
            notifyObservers();
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
	
	public ArrayList<Point> getPoints(){
		return mesPoints;
	}
	
	public ArrayList<Integer> getPlusCourtChemin(){
		return listeCheminsOptimums.get(0);
	}

	@Override
	public double getDistancePlusCourtChemin() {
		return calculerDistance(listeCheminsOptimums.get(0));
	}
}
