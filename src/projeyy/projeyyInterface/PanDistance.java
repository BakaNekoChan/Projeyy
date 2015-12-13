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

public class PanDistance extends JPanel implements Observer {

	private String distance;
	private String distanceP;
	
	
	public void paintComponent(Graphics g){                
		g.clearRect(0, 0, getWidth(), getHeight() );
		g.drawString("La distance totale est de :"+this.distance,0,0); 
		g.drawString("La distance précédente était de :"+this.distanceP, 0, 15);
	 } 
	
	
	public void update(Observable o, Object arg){

		if(o instanceof BrutForce3){
			distanceP = distance;
			distance = Integer.toString(((BrutForce3) o).getDistance());	
		}
		repaint();
	}
	
	
}
