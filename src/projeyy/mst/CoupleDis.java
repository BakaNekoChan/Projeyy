package projeyy.mst;

public class CoupleDis extends Couple implements Comparable<CoupleDis> {
	
	private final double val;

	public CoupleDis(){
		super(-1,-1);
		val=-1;
	}
	public CoupleDis(int ii, int jj, double m){
		super(ii,jj);
		val=m;
	}

	public double getVal(){
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
			return (int) ((val-cd2.val)*1000);
	}
	
	public String toString(){
		return "["+getI()+", "+getJ()+"] -> "+val;
	}
	

}
