package projeyy.projeyyInterface;

import java.awt.Color; 

import javax.swing.JFrame;
import javax.swing.JPanel;

import projeyy.brutforceObservable.BrutForce3;
import projeyy.generator.Generator;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PanDistance extends JPanel {

	private double distance;
	private double distanceP;
	
	public double getDistance(){
		return distance;
	}
	public void setDistance(double d){
		this.distance = d;
	}
	public void setDistanceP(double dp){
		this.distanceP = dp;
	}
	
	public void paintComponent(Graphics g){                
		g.clearRect(0, 0, getWidth(), getHeight() );
		g.drawString("La distance totale est de :"+Double.toString(this.distance),0,0); 
		g.drawString("La distance précédente était de :"+Double.toString(this.distanceP), 0, 15);
	 } 

}
