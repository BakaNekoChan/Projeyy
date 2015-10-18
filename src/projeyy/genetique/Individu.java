package projeyy.genetique;

import java.util.ArrayList;

public class Individu {
	ArrayList<Integer> monChemin;

	public Individu(ArrayList<Integer> monChemin) {
		this.monChemin = monChemin;
	}

	public ArrayList<Integer> getMonChemin() {
		return monChemin;
	}

	public void setMonChemin(ArrayList<Integer> monChemin) {
		this.monChemin = monChemin;
	}
	
	public ArrayList<ArrayList<Integer>> selection(ArrayList<ArrayList<Integer>> listeSelection){
		ArrayList<ArrayList<Integer>> listeSelectionnee = new ArrayList<ArrayList<Integer>>(listeSelection);
		return listeSelectionnee;
	}
}
