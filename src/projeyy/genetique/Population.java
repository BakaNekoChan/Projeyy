package projeyy.genetique;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import projeyy.generator.Generator;
import projeyy.projeyyInterface.Point;

public class Population {
	private MyArrayList<Individu> maPop;
	private double[][] matriceDistance;
	private ArrayList<Point> villes;

	public static void main(String[] args) {
		ThreadMXBean bean = ManagementFactory.getThreadMXBean(); 

		for(int i = 3; i<11; i++){
			ArrayList<double[][]> testMatrix = Generator.useFileSerialize("Points:" + i + "_Matrices:20");
			long avgCPUTime = 0;
			double avgDist = 0;
			long moyNbrExec = 0;
			for(double[][] matrice : testMatrix){
				long t1 = bean.getCurrentThreadCpuTime();
				Population p = new Population(matrice, 500);
				for(int j = 0; j < 100; j++){
					p.genererPopulationSuivante1();
				}
				avgCPUTime += bean.getCurrentThreadCpuTime() - t1;
				avgDist += p.getBestIndividu().getDistanceTotale();
			}
			
			System.out.println(moyNbrExec / testMatrix.size());
			System.out.println("Moyenne distance " + i + " villes " + avgDist/testMatrix.size());
			System.out.println("Moyenne temps CPU "+ i + " villes " +avgCPUTime/testMatrix.size());
		}
	}
	
	public Population(double[][] matrice, int nbIndividuBase){
		this.villes = null;
		this.maPop = new MyArrayList<Individu>();
		matriceDistance = matrice;
		genererPopulationBase(nbIndividuBase);
	}
	
	public Population(ArrayList<Point> villes, int nbIndividuBase){
		this.villes = villes;
		this.maPop = new MyArrayList<Individu>();
		matriceDistance = Generator.generateMatrice(villes);
		genererPopulationBase(nbIndividuBase);
	}
	
	private void genererPopulationBase(int taillePop){
		maPop.clear();
		for(int i = 0; i < taillePop; i++){
			maPop.add(new Individu(matriceDistance));
		}
	}
	
	public void genererPopulationSuivante1(){
		reproductionLocal();
		selectionParRang();
	}
	
	//supprimer la moitié la plus faible des individus
	public void selectionParRang(){
		Collections.sort(maPop);
		maPop.supprimerRang((maPop.size())/2, maPop.size());
	}
	
	//génère une modification locale aléatoire sur chaque membre présent de la population pour créer un fils.
	//La population est donc doublée
	
	public void reproductionLocal(){
		MyArrayList<Individu> newPop = new MyArrayList<Individu>(maPop);
		for(Individu i : maPop){
			newPop.add(i.genererEnfant());
		}
		maPop = newPop;
	}
	
	public Individu getBestIndividu(){
		return this.maPop.get(0);
	}
	
	public String toString(){
		String maChaine = "";
		int j = maPop.size();
//		for(Individu i:maPop){
//			maChaine += i.toString() + "\n";
//			j++;
//		}
		maChaine += "Il y a exactement " + j + " individus.";
		return maChaine;
	}
}
