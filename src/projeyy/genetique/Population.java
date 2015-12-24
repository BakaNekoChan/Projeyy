package projeyy.genetique;

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
		Population p = new Population(Generator.generatePlane(10), 50);
	}
	
	public Population(ArrayList<Point> villes, int nbIndividuBase){
		this.villes = villes;
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
			newPop.add(i);
			newPop.add(i.genererEnfant());
		}
	}
	
	public String toString(){
		String maChaine = "";
		for(Individu i:maPop){
			maChaine += i.toString() + "\n";
		}
		return maChaine;
	}
}
