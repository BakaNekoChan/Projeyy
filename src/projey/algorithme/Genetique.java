package projey.algorithme;

import java.util.ArrayList;

import projeyy.generator.Generator;
import projeyy.genetique.Population;
import projeyy.projeyyInterface.Point;

public class Genetique extends Algorithme{
	private Population p;
	private int nbGenerations;
	private ArrayList<Point> listePoints;
	
	public Genetique(int nbPoints, int nbIndividus, int nbGenerations){
		listePoints = Generator.generatePlane(nbPoints);
		p = new Population(listePoints, nbIndividus);
	}
	
	@Override
	public void execute() {
		for(int i = 0; i < nbGenerations; i++){
			p.genererPopulationSuivante1();
			notifierObservateurs();
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
