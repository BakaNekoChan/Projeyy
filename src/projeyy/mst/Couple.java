package projeyy.mst;


public class Couple {

	private final int i;
	private final int j;
	
	// cr�e un couple (i,j)
	public Couple(int ii, int jj){
		i=ii;
		j=jj;
	}

	// cr�e un couple vide
	public Couple() {
		i=-1;
		j=-1;
	}
	
	// cr�e un couple par recopie
	public Couple (Couple c){
		i=c.getI();
		j=c.getJ();
	}

	// retourne le i du couple
	public int getI() {
		return i;
	}

	// retourne le j du couple
	public int getJ() {
		return j;
	}
	
	

}
