package projey; 
 
import java.util.ArrayList; 
import java.util.Hashtable;
 
public class BrutForce { 
	
	private Hashtable<ArrayList<Integer>, Integer> tab;
 
    /*entr�es: demi-matrice contenant les distances entre les diff�rentes villes 
    sorties: retourne la valeur du chemin le plus cours 
    execution: calcul la valeur de chaque chemin et stocke le plus court  
    */ 
	public int brutForce (int [][] mat){ 
		
        ArrayList<Integer> villes= new ArrayList<Integer>(); 
        
        for(int i=0; i<mat.length; i++){ //cr�ation de la liste de villes 
            villes.add(i); 
        } 
        
        ArrayList<ArrayList<Integer>> cheminsTab = new ArrayList<ArrayList<Integer>>();
        generate(villes, cheminsTab);
        int min=0;
        
        for(int i=0; i<cheminsTab.size(); i++){
        	int x=0;
        	
        	for(int j=1; j<cheminsTab.get(i).size()){
        		x+=mat[cheminsTab.get(i).get(j-1)][cheminsTab.get(i).get(j)];
        	}
        	
        	if (min=0){
        		min=x;
        	}
        	else if(x<min){
        		min=x;
        	}
        }
        return min;
    } 
    
	//methode qui permet de générer un tableau de tous les chemins possible (cheminsTab) par rapport à une liste de villes données (représentées par un tableau de nombres)
	  public static void generateTree (ArrayList<Integer> listeVilles, ArrayList<ArrayList<Integer>> cheminsTab){
	    generateTree(listeVilles, cheminsTab, new ArrayList<Integer>());
	  }

	  //methode annexe utilisée par le premier generateTree
	  private static void generateTree (ArrayList<Integer> listeVilles, ArrayList<ArrayList<Integer>> cheminsTab,ArrayList<Integer> cheminActuel){
	    int x = 0;
	    if(listeVilles.isEmpty()){
	      cheminsTab.add(cheminActuel);
	    }
	    else{
	      for(int i = 0; i < listeVilles.size(); i++){
	        ArrayList<Integer> cheminActuelS = new ArrayList<Integer>(cheminActuel);
	        cheminActuelS.add(listeVilles.get(i));
	        x = listeVilles.remove(i);
	        generateTree(listeVilles, cheminsTab, cheminActuelS);
	        listeVilles.add(i,x);
	        }
	    }
	  }
} 
 
 