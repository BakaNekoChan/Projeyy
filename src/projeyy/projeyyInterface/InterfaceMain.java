package projeyy.projeyyInterface;

import projeyy.brutforceObservable.BrutForce;

public class InterfaceMain {

	public static void main(String[] args){
		Fenetre f1 = new Fenetre();
		BrutForce monAlgo = new BrutForce(6);
		monAlgo.addObserver(f1.getPan());
		monAlgo.execute();
	}
}
