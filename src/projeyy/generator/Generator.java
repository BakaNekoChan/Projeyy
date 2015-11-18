package projeyy.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Generator {

	private static int sizePlan = 100;


	//Cette méthode est celle utilisé pour générer la matrice. Elle retourne une matrice complète pour ne pas avoir à se soucier de que côté de la matrice est remplie.
	//Pour l'utilister, il suffit de donner le nombre de points que l'on veut traiter, et le générateur renvoie une matrice carrée de taille nbPoints avec la distance entre chaque points.
	public static double[][] generateMatrice (int nbPoints){
		int[][] plan;
		plan = generatePlane(nbPoints, Generator.sizePlan);
		int i = 0;
		int j = 0;
		double [][] resultat = new double[nbPoints][nbPoints];

		while(i < nbPoints){
			j = 0;
			while(j < nbPoints){
				if(i == j){
					resultat[i][j] = -1;
				}
				else{
					resultat [i][j] = Math.sqrt(Math.pow(plan[i][0]-plan[j][0], 2) + Math.pow(plan[i][1]-plan[j][1], 2));
				}
				j++;
			}
			i++;
		}
		return resultat;
	}

	private static int[][] generatePlane (int nbPoints, int sizePlan){
		int[][] points = new int[nbPoints][2];
		for (int i = 0; i < nbPoints; i++){
			points[i][0] = (int)(Math.random()*sizePlan);
			points[i][1] = (int)(Math.random()*sizePlan);
		}
		return points;
	}

	private static void generateFile(int nbPoints, int nbMatrices, String filePath){
		PrintWriter pw = null;
		try{
			pw = new PrintWriter (new BufferedWriter (new FileWriter (new File(filePath))));
		}
		catch(IOException o){
			System.out.println("ça marche pas");
			System.exit(0);
		}
		pw.println(nbPoints);
		pw.println(nbMatrices);

		for(int i = 0; i<nbMatrices; i++){
			double[][] matriceTmp = generateMatrice(nbPoints);

			for(int j = 0; j<matriceTmp.length; j++){
				for(int k = 0; k < matriceTmp.length; k++){
					pw.print(matriceTmp[j][k] + " ");
				}
				pw.println();

			}

			pw.println();
		}
		pw.close();
	}

	
	private static ArrayList<double[][]> useFile(String filePath){
		ArrayList<double[][]> mesMatrices = new ArrayList<double[][]>();
		File f = new File(filePath);
		Scanner sc = null;

		try {
			sc = new Scanner(f).useLocale(Locale.US);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Erreur lors de la récupération du fichier, la liste des matrices est vide.");
		}
		
		int nbPoints = sc.nextInt();
		int nbMatrices = sc.nextInt();
		
		for(int i = 0; i<nbMatrices; i++){
			double[][] matriceTmp = new double[nbPoints][nbPoints];
			for (int j = 0; j < nbPoints; j++) {
				for (int j2 = 0; j2 < nbPoints; j2++) {
					matriceTmp[j][j2] = sc.nextDouble();
				}
			}
			mesMatrices.add(matriceTmp);
		}
		sc.close();
		return mesMatrices;
	}

	public static void main(String[] args) {
		generateFile(12,100,"MonFichier");
		ArrayList<double[][]> mesMatrices = useFile("MonFichier");
		printMatrice(mesMatrices.get(0));
	}
	
	public static void printMatrice(double[][] m){
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
