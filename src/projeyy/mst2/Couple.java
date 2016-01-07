package projeyy.mst2;


public class Couple {
	private final int i;
	private final int j;
	
	// Constructeur de couple de ville
	public Couple(int ii, int jj){
		i=ii;
		j=jj;
	}
	
	// Constructeur vide
	public Couple() {
		i=-1;
		j=-1;
	}
	
	// Constructeur par recopie
	public Couple (Couple c){
		i=c.getI();
		j=c.getJ();
	}
	// Retourne i
	public int getI() {
		return i;
	}
	
	// Retourne j
	public int getJ() {
		return j;
	}
}
