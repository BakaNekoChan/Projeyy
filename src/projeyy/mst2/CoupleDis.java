package projeyy.mst2;


public class CoupleDis extends Couple implements Comparable<CoupleDis>{
	private final double val;
	
	//Constructeur vide de CoupleDis via le constructeur de Couple
	public CoupleDis(){
		super(-1,-1);
		val=-1;
	}
	
	//Constructeur de CoupleDis via le constructeur de Couple
	public CoupleDis(int ii, int jj, double m){
		super(ii,jj);
		val=m;
	}
	
	// Retourne la valeur de la distance entre les deux villes du CoupleDis
	public double getVal(){
		return val;
	}


	@Override
	
	// Retourne un entier permettant de comparer deux CoupleDis 
	public int compareTo(CoupleDis cd2) {
		if (val==cd2.val){
			if (getI()==cd2.getI())
				return getJ()-cd2.getJ();
			else
				return getI()-cd2.getI();
		}
		else 
			return (int) ((val-cd2.val)*1000);
	}
	// toString pour pouvoir afficher un CoupleDis
	public String toString(){
		return "["+getI()+", "+getJ()+"] -> "+val;
	}
	
}
