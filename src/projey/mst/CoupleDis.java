package projey.mst;

public class CoupleDis extends Couple implements Comparable<CoupleDis> {
	
	private final int val;

	public CoupleDis(){
		super(-1,-1);
		val=-1;
	}
	public CoupleDis(int ii, int jj, int valu){
		super(ii,jj);
		val=valu;
	}

	public int getVal(){
		return val;
	}


	@Override
	public int compareTo(CoupleDis cd2) {
		if (val==cd2.val){
			if (getI()==cd2.getI())
				return getJ()-cd2.getJ();
			else
				return getI()-cd2.getI();
		}
		else 
			return val-cd2.val;
	}
	
	

}
