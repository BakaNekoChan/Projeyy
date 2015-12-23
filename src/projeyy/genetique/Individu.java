package projeyy.genetique;

import java.util.ArrayList;

import projeyy.generator.Generator;


//Pour plus d'information sur les Individus et les moyens de sélection, voir cette page:
//http://perso.ensta-paristech.fr/~lunevill/sim_numerique/projets/vdc.pdf

public class Individu implements Comparable<Individu>{
	private ArrayList<Integer> monChemin;
	private double[][] matriceDistances;
	
	public static void main(String[] args) {
		System.out.println(new Individu(Generator.generateMatrice(5)));
	}
	
	public Individu(ArrayList<Integer> monChemin, double[][] matriceDistances) {
		this.monChemin = monChemin;
		this.matriceDistances = matriceDistances;
	}
	
	public Individu(double [][] matriceDistances){
		this.matriceDistances = matriceDistances;
		this.monChemin = new ArrayList<Integer>();
		randomChemin();
	}

	private void randomChemin() {
			monChemin.clear();
			ArrayList<Integer> villesRestantes = new ArrayList<Integer>();
			for (int i = 0; i < matriceDistances.length; i++){
				villesRestantes.add(i);
			}
			
			for(int i = 0; i < matriceDistances.length; i++){
				monChemin.add(villesRestantes.remove((int) (Math.random()*villesRestantes.size())));
			}
		}

	public double getDistanceTotale() {
		double distanceTotale = 0;
		for(int i = 1; i < this.monChemin.size(); i++){
			distanceTotale += matriceDistances[this.monChemin.get(i-1)][this.monChemin.get(i)];
		}
		distanceTotale += matriceDistances[this.monChemin.get(this.monChemin.size()-1)][this.monChemin.get(0)];
		return distanceTotale;
	}

	public ArrayList<Integer> getMonChemin() {
		return monChemin;
	}

	public int compareTo(Individu i) {
		int resultat = 0;
		double distanceThis = getDistanceTotale();
		double distanceI = i.getDistanceTotale();
		if(distanceThis - distanceI <0 && distanceThis - distanceI  > -1) resultat = -1;
		else if(distanceThis - distanceI  > 0 && distanceThis - distanceI  < 1) resultat = 1;
		else resultat = (int)(distanceThis - distanceI);
		return resultat;
	}
	
	public String toString(){
		return monChemin.toString();
	}
}
