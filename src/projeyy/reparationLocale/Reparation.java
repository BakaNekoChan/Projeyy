package projeyy.reparationLocale;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.ArrayList;

import projeyy.generator.Generator;

public class Reparation {
	private static final int NOMBRE_VILLES = 50;
	private static double[][] maMatrice = Generator.generateMatrice(NOMBRE_VILLES);
	private static PrintWriter pw = createPrintWriter();
	private static MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
	
	public static void main(String[] args) {
		ArrayList<Integer> chemin = new ArrayList<Integer>();
		for (int i = 0; i < NOMBRE_VILLES; i++){
			chemin.add(i);
		}
		reparation(chemin, 500);
		pw.close();
	}


	//Méthode qui permet d'essayer une réparation locale nombreIteration fois. cheminDepart prend la nouvelle valeur suite à toutes les réparations
	public static void reparation(ArrayList<Integer> cheminDepart, int nombreIteration){
		if(cheminDepart.size() > 3){
			for(int i = 0; i < nombreIteration; i++){
				pw.println(memoryBean.getHeapMemoryUsage().getUsed()); //écriture de la mémoire dans le fichier.

				
				ArrayList<Integer> cheminRepare = new ArrayList<Integer>(cheminDepart);
				//Selection de la première arrête.
				int point1 = (int)(Math.random() * (double)cheminDepart.size());
				int point2 = (point1 != cheminDepart.size()-1)? point1+1 : 0;

				//Selection de la seconde arrête.
				int point3 = (int)(point2 + (double)(1 + Math.random() * (cheminDepart.size() - 3))) % cheminDepart.size();
				int point4 = point3+1;

				int echange = 0;
				int max1 = Math.max(point1, point2);
				int min2 = Math.min(point3, point4);
				echange = cheminRepare.get(max1);
				cheminRepare.set(max1, cheminRepare.get(min2));
				cheminRepare.set(min2, echange);
				
				if(calculerDistance(cheminRepare) < calculerDistance(cheminDepart)) cheminDepart = cheminRepare;
				System.out.println(calculerDistance(cheminDepart));
			}
		}
	}
	
	private static double calculerDistance (ArrayList<Integer> listeVilles){
		double distance = 0;
		for(int i = 1; i < listeVilles.size(); i++){
			distance += maMatrice[listeVilles.get(i-1)][listeVilles.get(i)];
		}
		distance += maMatrice[listeVilles.get(0)][listeVilles.get(listeVilles.size()-1)];
		return distance;
	}
	
	private static PrintWriter createPrintWriter(){ //fonction qui permet d'initialiser l'ouverture du fichier.
		PrintWriter pw = null;
		try{
			pw = new PrintWriter (new BufferedWriter (new FileWriter (new File("monGraph"))));
		}
		catch(IOException o){
			System.out.println("ça marche pas");
			System.exit(0);
		}
		return pw;
	}
}
