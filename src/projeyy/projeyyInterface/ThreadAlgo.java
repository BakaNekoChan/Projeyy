package projeyy.projeyyInterface;

import projey.algorithme.Algorithme;

public class ThreadAlgo extends Thread {
	Algorithme exe;
	
	public ThreadAlgo(Algorithme exe){
		this.exe = exe;
	}
	public void run(){
		exe.execute();
	}
}
