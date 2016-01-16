package projey.algorithme;

import java.util.ArrayList;

import projeyy.generator.Generator;
import projeyy.genetique.Population;
import projeyy.projeyyInterface.Point;

public class Genetique extends Algorithme{
	private Population p;
	private int nbGenerations;
	private ArrayList<Point> listePoints;
	private int nbIndividus;
	private int nbPoints;
	
	public static void main(String[] args) {
		Genetique test = new Genetique (5,10,10);
		test.execute();
		System.out.println(test.getDistancePlusCourtChemin());
	}
	
	public Genetique(int nbPoints, int nbIndividus, int nbGenerations){
		this.nbGenerations = nbGenerations;
		this.nbPoints = nbPoints;
		listePoints = Generator.generatePlane(nbPoints);
		this.nbIndividus = nbIndividus;
	}
	
	@Override
	public void execute() {
		listePoints = Generator.generatePlane(nbPoints);
		p = new Population(listePoints, nbIndividus);
		for(int i = 0; i < nbGenerations; i++){
			p.genererPopulationSuivante1();
			//System.out.println(getPlusCourtChemin());
			notifierObservateurs();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public ArrayList<Point> getPoints() {
		return listePoints;
	}

	@Override
	public ArrayList<Integer> getPlusCourtChemin() {
		return p.getBestIndividu().getMonChemin();
	}

	@Override
	public double getDistancePlusCourtChemin() {
		return p.getBestIndividu().getDistanceTotale();
	}

}
