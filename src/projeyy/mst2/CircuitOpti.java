package projeyy.mst2;


import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class CircuitOpti {
	
	private final ArbreTrie arbre;
	private List<CoupleDis> circuit;
	
	public CircuitOpti(ArbreTrie at, List<CoupleDis> aretes){
		arbre= at;
		ArrayList<CoupleDis> circuitOpti = new ArrayList<CoupleDis>();
		int [] tab = new int[arbre.getNbVille()];
		for (int i=0; i<aretes.size(); i++){
			if (tab[aretes.get(i).getI()]<2 && tab[aretes.get(i).getJ()]<2){
				circuitOpti.add(aretes.get(i));
				tab[aretes.get(i).getI()]++;
				tab[aretes.get(i).getJ()]++;
			}
			else if (tab[aretes.get(i).getI()]==2 && tab[aretes.get(i).getJ()]<2){
				int a=aretes.get(i).getJ();
				for(CoupleDis cur : arbre.getTs()){
					if(cur.getI()==a){
						if(tab[cur.getJ()]<2){
							circuitOpti.add(cur);
							tab[a]++;
							tab[cur.getJ()]++;
						}
					}
					if(cur.getJ()==a){
						if(tab[cur.getI()]<2){
							circuitOpti.add(cur);
							tab[a]++;
							tab[cur.getI()]++;
						}
					}
				}
			}
			else if (tab[aretes.get(i).getI()]<2 && tab[aretes.get(i).getJ()]==2){
				int a=aretes.get(i).getI();
				for(CoupleDis cur : arbre.getTs()){
					if(cur.getI()==a){
						if(tab[cur.getJ()]<2){
							circuitOpti.add(cur);
							tab[a]++;
							tab[cur.getJ()]++;
						}
					}
					if(cur.getJ()==a){
						if(tab[cur.getI()]<2){
							circuitOpti.add(cur);
							tab[a]++;
							tab[cur.getI()]++;
						}
					}
				}
				
			}
		}	
		circuit=circuitOpti;
		
	}
	
	public List<CoupleDis> getCircuit(){
		return circuit;
	}

}
