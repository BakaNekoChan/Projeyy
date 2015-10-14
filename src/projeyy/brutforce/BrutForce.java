package projeyy.brutforce;

import java.util.ArrayList;

public class BrutForce {

	/*entrees: demi-matrice contenant les distances entre les differentes villes
	sorties: retourne la valeur du chemin le plus cours
	execution: calcul la valeur de chaque chemin et stocke le plus court
	*/
	public static double brutForce (double[][] matriceDistances, ArrayList<ArrayList<Integer>> listeDesChemins, ArrayList<ArrayList<Integer>> listeDesCheminsLesPlusCourts){ //Le nombre de villes dans les chemins doit être égal à la largeur de la matrice
		double [] listeDesDistancesTotales = new double[listeDesChemins.size()];
		for(int i = 0; i < listeDesChemins.size(); i++){
			ArrayList<Integer> cheminActuel = listeDesChemins.get(i);
			int villePrecedente = -1;
			for(int villeActuelle : cheminActuel){
				if(villePrecedente != -1) listeDesDistancesTotales[i] += matriceDistances[villePrecedente][villeActuelle];
				villePrecedente = villeActuelle;
			}
		}

		double distanceMinimum = listeDesDistancesTotales[0];

		for(int i = 1; i < listeDesDistancesTotales.length; i++){
			if(listeDesDistancesTotales[i] == distanceMinimum){
				listeDesCheminsLesPlusCourts.add(listeDesChemins.get(i));
			}
			else if(listeDesDistancesTotales[i] < distanceMinimum){
				distanceMinimum = listeDesDistancesTotales[i];
				listeDesCheminsLesPlusCourts.clear();
				listeDesCheminsLesPlusCourts.add(listeDesChemins.get(i));
			}
		}
		return distanceMinimum;
	}

	//methode qui permet de générer un tableau de tous les chemins possible (cheminsTab) par rapport à une liste de villes données (représentées par un tableau de nombres)
	public static void generateTree (ArrayList<Integer> listeVilles, ArrayList<ArrayList<Integer>> cheminsTab){
		//cheminsTab = new ArrayList<ArrayList<Integer>>();
		generateTree(listeVilles, cheminsTab, new ArrayList<Integer>());
	}

	//methode annexe utilisée par le premier generateTree
	private static void generateTree (ArrayList<Integer> listeVilles, ArrayList<ArrayList<Integer>> cheminsTab,ArrayList<Integer> cheminActuel){
		int x = 0;
		if(listeVilles.isEmpty()){
			cheminsTab.add(cheminActuel);
		}
		else{
			for(int i = 0; i < listeVilles.size(); i++){
				ArrayList<Integer> cheminActuelS = new ArrayList<Integer>(cheminActuel);
				cheminActuelS.add(listeVilles.get(i));
				x = listeVilles.remove(i);
				generateTree(listeVilles, cheminsTab, cheminActuelS);
				listeVilles.add(i,x);
			}
		}
	}
}
