package projeyy.mst2;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


public class ArbreTrie {

	
	private TreeSet<CoupleDis> ts;
	
	//rempli ts en triant les arêtes par distance
	public ArbreTrie(double [][] m){
		for (int i=0; i<m.length; i++){
			for(int j=0; j<i; j++){
				ts.add(new CoupleDis(i, j, m[i][j]));
			}
		}
	}
	
	
	
	
	//retourne une liste d'arêtes correspondant à un arbre couvrant minimum
	public static List<CoupleDis> minCircuit(TreeSet<CoupleDis> ts){
		ArrayList<CoupleDis> aretes = new ArrayList<CoupleDis>();
		ArrayList<Integer> sousTree = new ArrayList<Integer>();
		aretes.add(ts.first());
		sousTree.add(ts.first().getI());
		sousTree.add(ts.first().getJ());		
		for (CoupleDis cur : ts){
			if ( (sousTree.contains(cur.getI()) && !sousTree.contains(cur.getJ())) ){
				aretes.add(cur);
				sousTree.add(cur.getJ());
				cur=ts.first();
			}
			if ( (!sousTree.contains(cur.getI()) && sousTree.contains(cur.getJ())) ) {
				aretes.add(cur);
				sousTree.add(cur.getI());
				cur=ts.first();
			}
		}		
		return aretes;
	}
	
}

/*set<Integer>[] connexClass = new set<Integer>[n]
 * for (i) {
 * connexClasse[i] = new HashSet;
 * connexClasse.add(i);
 */
// ajout arÃªte (i,j)
/*
 * connexClasse[i].addAll(connexClasse[j])
 *for (int j2 : connexClasse[j])
 *connexClasse[j2]=connexClasse[i];
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */