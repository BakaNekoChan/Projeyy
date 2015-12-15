package projeyy.mst2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class ArbreTrie {

	
	private TreeSet<CoupleDis> ts= new TreeSet<CoupleDis>();
	private final int nbVille;
	
	//rempli ts en triant les arêtes par distance
	public ArbreTrie(double [][] m){
		nbVille=m.length;
		for (int i=0; i<m.length; i++){
			for(int j=0; j<i; j++){
				ts.add(new CoupleDis(i, j, m[i][j]));
			}
		}
	}
	
	//retourne une liste d'arêtes correspondant à un arbre couvrant minimum
	public List<CoupleDis> minArbreCouvrant(){
		ArrayList<CoupleDis> list = new ArrayList<CoupleDis>();
		HashSet<Integer> connexClasse[] = new HashSet[nbVille];
		for (int i=0; i<nbVille; i++) {
			connexClasse[i] = new HashSet<Integer>();
			connexClasse[i].add(i);
		}
		//ajout premiere arete (i,j)/7
		int i = ts.first().getI();
		int j = ts.first().getJ();
		connexClasse[i].add(j) ; //)(connexClasse[ts.first().getJ()]);
		connexClasse [j] = connexClasse[i];
		list.add(ts.first());
		//on ^prends les arretes pâr ordre croissant
		for (CoupleDis cur : ts){
			//si les extremitesd el'arrete sont dans des CC differents
			if(!connexClasse[cur.getJ()].contains(cur.getI())){
				connexClasse[cur.getI()].addAll(connexClasse[cur.getJ()]);
				for (int j2 : connexClasse[cur.getJ()]){
					connexClasse[j2]=connexClasse[cur.getI()];
				}
				list.add(cur);
			}
		}
		return list;
	}
	

	public int getNbVille(){
		return nbVille;
	}
	
	public TreeSet<CoupleDis> getTs(){
		return ts;
	}
	
}


