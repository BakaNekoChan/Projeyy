package projeyy.genetique;

import java.util.ArrayList;

import projeyy.generator.Generator;


//Pour plus d'information sur les Individus et les moyens de sélection, voir cette page:
//http://perso.ensta-paristech.fr/~lunevill/sim_numerique/projets/vdc.pdf

public class Individu implements Comparable<Individu>{
	private ArrayList<Integer> monChemin;
	private double[][] matriceDistances;
	
	public static void main(String[] args) {
		Individu i = new Individu(Generator.generateMatrice(5));
		System.out.println(i);
		System.out.println(i.genererEnfant());
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
	
	public Individu genererEnfant(){
		ArrayList<Integer> cheminRepare = new ArrayList<Integer>(monChemin);
		if(monChemin.size() > 3){
				//Selection de la première arrête.
				int point1 = (int)(Math.random() * (double)monChemin.size());
				int point2 = (point1 != monChemin.size()-1)? point1+1 : 0;

				//Selection de la seconde arrête.
				int point3 = (int)(point2 + (double)(1 + Math.random() * (monChemin.size() - 3))) % monChemin.size();
				int point4 = point3+1;

				int echange = 0;
				int max1 = Math.max(point1, point2);
				int min2 = Math.min(point3, point4);
				echange = cheminRepare.get(max1);
				cheminRepare.set(max1, cheminRepare.get(min2));
				cheminRepare.set(min2, echange);
		}
		return new Individu(cheminRepare, matriceDistances);
	}
}
