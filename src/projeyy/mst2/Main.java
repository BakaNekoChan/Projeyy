package projeyy.mst2;

import projeyy.generator.Generator;

public class Main {

	public static void main(String[] args) {
		//Créer une matrice vide
		double [][]matrice = Generator.generateMatrice(6);
		
		// Implémente la mtrice
		Generator.printMatrice(matrice);
		
		// Créer un arbre trié
		ArbreTrie at = new ArbreTrie(matrice);
		
		// Affiche l'arbre trié
		System.out.println("Pour parcourir une distance minimale, il faut parcourir les arètes suivante:"+at.minArbreCouvrant());
		
		// Créer un circuit optimisivé en fonction de l'arbre trié trouvé avant
		CircuitOpti co = new CircuitOpti (at, at.minArbreCouvrant());
		
		// Affiche le parcours du circuit
		System.out.println("Pour parcourir une distance minimale dans un circuit, il faut parcourir les arètes suivante:"+co.getCircuit());

	}

}
