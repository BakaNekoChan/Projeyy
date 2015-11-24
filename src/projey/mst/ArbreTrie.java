package projey.mst;

import java.util.ArrayList;
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
	public static ArrayList<CoupleDis> minCircuit(TreeSet<CoupleDis> ts){
		ArrayList<CoupleDis> aretes = new ArrayList<CoupleDis>();
		ArrayList<Integer> classeConnec = new ArrayList<Integer>();
		aretes.add(ts.first());
		classeConnec.add(ts.first().getI());
		classeConnec.add(ts.first().getJ());		
		for (CoupleDis cur : ts){
			if ( (classeConnec.contains(cur.getI()) && !classeConnec.contains(cur.getJ())) ){
				aretes.add(cur);
				classeConnec.add(cur.getJ());
				cur=ts.first();
			}
			if ( (!classeConnec.contains(cur.getI()) && classeConnec.contains(cur.getJ())) ) {
				aretes.add(cur);
				classeConnec.add(cur.getI());
				cur=ts.first();
			}
			
		}		
		return aretes;
	}
	
}
