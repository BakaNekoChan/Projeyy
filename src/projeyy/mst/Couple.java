package projeyy.mst;


public class Couple {

	private final int i;
	private final int j;
	
	public Couple(int ii, int jj){
		i=ii;
		j=jj;
	}

	public Couple() {
		i=-1;
		j=-1;
	}
	
	public Couple (Couple c){
		i=c.getI();
		j=c.getJ();
	}

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}
	
	

}
