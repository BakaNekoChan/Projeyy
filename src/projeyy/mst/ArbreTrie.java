package projeyy.mst;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


public class ArbreTrie {

		
	
	//rempli ts en triant les arêtes par distance
	public static void listeAretes(double [][] m, TreeSet<CoupleDis> ts){
		for (int i=0; i<m.length; i++){
			for(int j=0; j<i; j++){
				ts.add(new CoupleDis(i, j, m[i][j]));
			}
		}
	}
	
	//retourne une liste d'arêtes correspondant au circuit le plus court
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

