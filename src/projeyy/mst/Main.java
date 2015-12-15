package projeyy.mst;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import projeyy.generator.Generator;
import projeyy.mst2.CircuitOpti;


public class Main {

	public static void main(String[] args) {
		double [][]matrice = Generator.generateMatrice(7);
		Generator.printMatrice(matrice);
		TreeSet<CoupleDis> ts = new TreeSet<CoupleDis>();
		ArbreTrie.listeAretes(matrice, ts);
		List<CoupleDis> aretes = new ArrayList<CoupleDis>();
		aretes=ArbreTrie.minCircuit(ts);
		System.out.println("Pour parcourir une distance minimale, il faut parcourir les ar�tes suivante:"+aretes);

		
	}

}