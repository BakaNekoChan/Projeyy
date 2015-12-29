package projeyy.projeyyInterface;

import projeyy.brutforceObservable.BrutForce3;

public class InterfaceMain {

	public static void main(String[] args){
		Fenetre f1 = new Fenetre();
		BrutForce3 monAlgo = new BrutForce3(6);
		monAlgo.addObserver(f1);
		monAlgo.execute();
	}
}
