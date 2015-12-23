package projeyy.genetique;

import java.util.ArrayList;

import projeyy.generator.Generator;
import projeyy.projeyyInterface.Point;

public class Population {
	private ArrayList<Individu> maPop;
	private double[][] matriceDistance;
	private ArrayList<Point> villes;
	
	public Population(ArrayList<Point> villes){
		maPop = new ArrayList<Individu>();
		this.villes = villes;
		matriceDistance = Generator.generateMatrice(villes);
	}
	
	private void genererPopulationBase(int taillePop){
		maPop.clear();
		for(int i = 0; i < taillePop; i++){
			maPop.add(new Individu(matriceDistance));
		}
	}
	
	public static void genererPopulationSuivante1(){
		
	}
}
