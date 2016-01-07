package projeyy.mst;


public class Couple {

	private final int i;
	private final int j;
	
	// crée un couple (i,j)
	public Couple(int ii, int jj){
		i=ii;
		j=jj;
	}

	// crée un couple vide
	public Couple() {
		i=-1;
		j=-1;
	}
	
	// crée un couple par recopie
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
