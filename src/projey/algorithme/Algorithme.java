package projey.algorithme;

import java.util.ArrayList;
import java.util.Observable;

import projeyy.projeyyInterface.Point;

public abstract class Algorithme extends Observable{
	public abstract void execute();
	public abstract ArrayList<Point> getPoints();
	public abstract ArrayList<Integer> getPlusCourtChemin();
	public abstract double getDistancePlusCourtChemin();
	
	public void notifierObservateurs()
    {
            setChanged();
            notifyObservers();
    }
}
