package projeyy.projeyyInterface;

import java.awt.Color; 

import javax.swing.JFrame;
import javax.swing.JPanel;

import projeyy.brutforceObservable.BrutForce;
import projeyy.generator.Generator;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PanChemin extends JPanel implements Observer {

	private ArrayList<Point> points = new ArrayList<Point>();
	private ArrayList<Arete> aretes = new ArrayList<Arete>();
	private static final int taille_point = 25;
	private int nbPoints;
	// mise en place de la matrice distance avec generator, matrice xy recupère l'emplacement des différents points.	
		
	public void paintComponent(Graphics g){                
		g.clearRect(0, 0, getWidth(), getHeight() );
		
		for(int i = 0; i < points.size(); i++){
			paintSommet(g,i);
			paintArette(g,i);
		} 
	  } 
	
	private void paintSommet(Graphics g, int i){
		g.setColor(Color.RED);
		g.fillOval(points.get(i).getX()*10 - taille_point/2, points.get(i).getY()*10 - taille_point/2, taille_point, taille_point);
	}
	
	private void paintArette(Graphics g, int i){
		g.setColor(Color.RED);
		g.drawLine(aretes.get(i).getX()*10,aretes.get(i).getY()*10,aretes.get(i).getX2()*10,aretes.get(i).getY2()*10);
	}
	
	
	
	//méthode pour ArrayList
	// rempli les ArrayList
	public void rempliArray(int nbPoint){
		setPoint((int) (Math.random()*(390)),(int) (Math.random()*(490)));
		
		for (int i = 0; i < nbPoint; i++){
			setPoint((int) (Math.random()*(390)),(int) (Math.random()*(490)));
			setArete(points.get(i),points.get(i+1));
		}
	}
	
	// ajout d'un point
	public void setPoint(int x, int y){
		points.add(new Point(x,y));
	}
	// ajout d'une arete à partir de deux points
	public void setArete(Point a,Point b){
		aretes.add(new Arete(a.getX(),a.getY(),b.getX(),b.getY()));
	}
	
	public void viderTout(){
		points.clear();
		aretes.clear();
	}
	
	//méthode Observer 
	
	public void update(Observable o, Object arg){
		viderTout();
		if(o instanceof BrutForce){
			points = new ArrayList<Point>(((BrutForce) o).getPoints()); // Retourne ArrayList<Point>
			ArrayList<Integer> ordrePoints = new ArrayList<Integer>(((BrutForce) o).getPlusCourtChemin());// Retourne ArrayList<Integer>
			nbPoints = points.size();
			
			for(int i = 1; i<ordrePoints.size();i++){
				setArete(points.get(ordrePoints.get(i-1)), points.get(ordrePoints.get(i)));
			}
			
			setArete(points.get(ordrePoints.get(ordrePoints.size()-1)), points.get(ordrePoints.get(0)));
			repaint();
		}
	}
}
