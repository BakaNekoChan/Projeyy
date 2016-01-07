package projeyy.mst;

public class CoupleDis extends Couple implements Comparable<CoupleDis> {
	
	private final double val;

	// crée un couple-distance vide via le constructeur de couple
	public CoupleDis(){
		super(-1,-1);
		val=-1;
	}
	
	// crée un couple-distance (i, j, distance) via le constructeur de couple
	public CoupleDis(int ii, int jj, double m){
		super(ii,jj);
		val=m;
	}

	// retourne la distance du couple
	public double getVal(){
		return val;
	}


	@Override
	// retourne un entier permettant de comparer deux couple-distance
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
	
	// permet d'affiche un couple-distance
	public String toString(){
		return "["+getI()+", "+getJ()+"] -> "+val;
	}
	

}
