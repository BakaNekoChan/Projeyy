package projeyy.mst2;


import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class CircuitOpti {
	
	private final ArbreTrie arbre;
	private List<CoupleDis> circuit;
	
	
	// Nous donnes le circuit optimisé à partir de l'arbre minimum
	public CircuitOpti(ArbreTrie at, List<CoupleDis> aretes){
		arbre= at;
		ArrayList<CoupleDis> circuitOpti = new ArrayList<CoupleDis>();
		// Créer un tableau qui contiendra pour chaque ville le nombre de ville à laquelle elle est connecté
		int [] tab = new int[arbre.getNbVille()];			
		
		// Parcours de la liste trier d'arête 
		for (int i=0; i<aretes.size(); i++){
			
			// Test si les villes de l'arête ont moins de deux voisins, si oui rajoute l'arête, sinon ne fais rien
			if (tab[aretes.get(i).getI()]<2 && tab[aretes.get(i).getJ()]<2){
				circuitOpti.add(aretes.get(i));
				tab[aretes.get(i).getI()]++;
				tab[aretes.get(i).getJ()]++;
			}
			/* Test si l'une des deux villes à moins de deux voisins, si oui ajoute la première arête contenant cette ville et une 
			autre ville n'ayant pas deux voisins */
			
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
	// Retourne la liste contenant le circuit
	
	public List<CoupleDis> getCircuit(){
		return circuit;
	}

}
