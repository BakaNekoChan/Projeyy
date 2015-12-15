package projeyy.mst2;

import projeyy.generator.Generator;

public class Main {

	public static void main(String[] args) {
		double [][]matrice = Generator.generateMatrice(6);
		Generator.printMatrice(matrice);
		ArbreTrie at = new ArbreTrie(matrice);
		System.out.println("Pour parcourir une distance minimale, il faut parcourir les arètes suivante:"+at.minArbreCouvrant());
		CircuitOpti co = new CircuitOpti (at, at.minArbreCouvrant());
		System.out.println("Pour parcourir une distance minimale dans un circuit, il faut parcourir les arètes suivante:"+co.getCircuit());

	}

}
