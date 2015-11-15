package projeyy.genetique;

import java.util.ArrayList;

import projeyy.generator.Generator;


//Pour plus d'information sur les Individus et les moyens de sélection, voir cette page:
//http://perso.ensta-paristech.fr/~lunevill/sim_numerique/projets/vdc.pdf
public class Individu {
	private ArrayList<Integer> monChemin;
	private double distanceTotale;
	static double[][] matriceDistances;
	
	public Individu(ArrayList<Integer> monChemin) {
		this.monChemin = monChemin;
		calculerDistance();
	}
	
	public static void setMatriceDistances(int nbPoints){
		matriceDistances = Generator.generateMatrice(nbPoints);
	}
	
	public static void setMatriceDistances(double[][] matriceDistances){
		Individu.matriceDistances = matriceDistances;
	}
	
	public static double[][] getMatriceDistance(){
		return Individu.matriceDistances;
	}
	
	public double getDistanceTotale() {
		return distanceTotale;
	}

	public ArrayList<Integer> getMonChemin() {
		return monChemin;
	}
	
	public static ArrayList<Individu> selectionParRang(ArrayList<Individu> listeSelection){
		ArrayList<Individu> listeSelectionnee = new ArrayList<Individu>(listeSelection);
		
		return listeSelectionnee;
	}
	
	//Il y aura plusieurs genererPopulationSuivante en changeant l'algorithme pour tester différentes efficacités.
	public static ArrayList<Individu> genererPopulationSuivante1(ArrayList<Individu> listeParents){
		
	}
	
	// Permet de mettre la bonne distance deans la variable Individu.distanceTotale
	// Il faut avoir généré la matrice static de Individu pour pouvoir utiliser cette méthode. (Voir setMatriceDistance)
	private void calculerDistance(){ 
		this.distanceTotale = 0;
		for(int i = 1; i < this.monChemin.size(); i++){
			this.distanceTotale += matriceDistances[this.monChemin.get(i-1)][this.monChemin.get(i)];
		}
		this.distanceTotale += matriceDistances[this.monChemin.get(this.monChemin.size()-1)][this.monChemin.get(0)];
	}
}
