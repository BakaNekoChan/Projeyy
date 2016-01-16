package projeyy.brutforce2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import projeyy.generator.*;

//Cette version de BruteForce ne stocke pas tous les chemins dans un tableau.

public class BrutForce {
	private static int NOMBRE_VILLES = 0;
	private static int nombreExec = 0; //Stocke le nombre de noeuds de l'arbre de résolution
	
	//Permet de stocker tous les chemins permettant d'obtenir la distanceOptimum
	private static ArrayList<ArrayList<Integer>> listeCheminsOptimums = new ArrayList<ArrayList<Integer>>();
	
	//Meilleur distance trouvée
	private static double distanceOptimum;
	
	//Matrice des distance, voir la classe Generator pour plus d'information
	private static double[][] maMatrice = null;
	
	//Objet permettant d'observer la consommation mémoire
	private static MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
	private static ThreadMXBean bean = ManagementFactory.getThreadMXBean(); 
	//Objet permettant d'écrire dans un fichier. Je stocke les différents de la mémoire pour afficher un
	//graphique sur scilab par la suite.
	private static PrintWriter pw = createPrintWriter();


	public static void main(String[] args) {
		for(int i = 9; i<10; i++){
			ArrayList<double[][]> testMatrix = Generator.useFileSerialize("Points:" + i + "_Matrices:20");
			NOMBRE_VILLES = testMatrix.get(0).length;
			long avgCPUTime = 0;
			double avgDist = 0;
			for(double[][] matrice : testMatrix){
				long t1 = bean.getCurrentThreadCpuTime();
				maMatrice = matrice;
				nombreExec = 0;
				generateTree();
				System.out.println(nombreExec);
				System.out.println(bean.getCurrentThreadCpuTime() - t1);
				avgCPUTime += bean.getCurrentThreadCpuTime() - t1;
				avgDist += distanceOptimum;
			}
			System.out.println("Moyenne distance " + i + " villes " + avgDist/testMatrix.size());
			System.out.println("Moyenne temps CPU "+ i + " villes " +avgCPUTime/testMatrix.size());
		}
	
		pw.close();
	}
	
	
	
	//méthode qui permet de générer les chemins les plus courts disponnibles dans le tableau static listCheminsOptimums.
	public static void generateTree (){
		ArrayList<Integer> listeVilles = new ArrayList<Integer>();
		for(int i = 0; i < NOMBRE_VILLES; i++){
			listeVilles.add(i);
		}
		distanceOptimum = calculerDistance(listeVilles);
		listeCheminsOptimums.add(listeVilles);
		
		
		generateTree(listeVilles, new ArrayList<Integer>());
		pw.close();
	}
	

	//methode annexe utilisée par le premier generateTree
	
	
	
	/*
	 * La méthode generateTree est une méthode récursive permettant l'exploration de l'arbre de tous
	 * les chemins possibles et vérifie si le cheminActuel final (donc quand listeVilles est vide) 
	 * est plus court que ceux déjà explorés.
	 * 
	 * Paramètre:
	 *  - listeVilles est la liste des villes pas encore visitées pendant la récurrence. Les villes dans listeVilles
	 *  ne sont donc pas présentes dans chemin Actuel.
	 *  
	 *  - cheminActuel est la liste des villes dans un ordre unique. Sur toutes les différentes execution simultanée
	 *  de generateTree, cheminActuel change, c'est ce qui permet de vérifier tous les chemins possibles.
	*/
	
	private static void generateTree (ArrayList<Integer> listeVilles,ArrayList<Integer> cheminActuel){
		
		int x = 0; //Permet de stocker temporairement les villes lors de la création de la suite de cheminActuel.
		
		pw.println(memoryBean.getHeapMemoryUsage().getUsed()); //Permet d'écrire l'état de la mémoire actuelle dans monGraph.
		nombreExec ++; //Incrémente le nombre de noeds d'arbre exploré
		
		
		//On vérifie si listeVilles est vide, pour savoir si le cheminActuel est à traiter comme un chemin final ou pas.
		if(listeVilles.isEmpty()){
			/*
			 * Si c'est le cas, on doit vérifier si ce chemin est plus court ou égal à ceux déjà exploré,
			 * on traite ensuite ces deux cas.
			 * Si le chemin est plus long que le plus petit chemin déjà trouvé, rien ne se passe, l'objet n'est plus
			 * référencé à la fin de l'exécution de la branche.
			 */
			
			if(calculerDistance(cheminActuel) < distanceOptimum){ //Cas ou cheminActuel est plus petit
				listeCheminsOptimums.clear();
				listeCheminsOptimums.add(cheminActuel);
				distanceOptimum = calculerDistance(cheminActuel);
			}
			else if(calculerDistance(cheminActuel) == distanceOptimum){ //Cas ou cheminActuel est égal
				listeCheminsOptimums.add(cheminActuel);
			}
		}
		
		
		/*
		 * Cas ou listeVilles n'est pas vide, il faut donc créer les sous-cheminActuel avec chaque ville de
		 * listeVilles pour avoir l'arbre complet des possibilités.
		 */
		else{
			for(int i = 0; i < listeVilles.size(); i++){ //Parcours de toutes les villes de listeVilles
				
				//Création du sous-cheminActuel dans un autre objet, pour que le cheminActuel ne soit pas écrasé.
				ArrayList<Integer> sousCheminActuel = new ArrayList<Integer>(cheminActuel);
				
				//Ajout d'une ville parmis les villes restantes dans listeVilles
				sousCheminActuel.add(listeVilles.get(i));
				x = listeVilles.remove(i);
				
				//generateTree suivant, avec la listeVilles amputé d'une ville, et sousCheminActuel avec une
				//ville en plus que cheminActuel
				generateTree(listeVilles, sousCheminActuel);
				
				//On remet la ville dans listeVilles quand on a fini pour les prochain traitements
				listeVilles.add(i,x);
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
