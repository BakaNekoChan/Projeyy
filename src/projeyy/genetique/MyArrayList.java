package projeyy.genetique;

import java.util.ArrayList;

public class MyArrayList<T> extends ArrayList<T>{
	public MyArrayList(MyArrayList<T> maPop) {
		super(maPop);
	}

	public MyArrayList() {
		super();
	}

	public void supprimerRang(int debut, int fin){
		removeRange(debut, fin);
	}
}
