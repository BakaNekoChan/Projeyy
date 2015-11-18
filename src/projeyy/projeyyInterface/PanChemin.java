package projeyy.projeyyInterface;

import java.awt.Color; 
import javax.swing.JFrame;
import javax.swing.JPanel;

import projeyy.generator.Generator;

import java.awt.Graphics;
import java.util.ArrayList;

public class PanChemin extends JPanel {

	private ArrayList<Point> points = new ArrayList<Point>();
	private ArrayList<Arete> aretes = new ArrayList<Arete>();
	private static final int taille_point = 25;
	
	// mise en place de la matrice distance avec generator, matrice xy recupère l'emplacement des différents points.	
		int nbPointMatrice = 5;
		Generator matrice = new Generator();
		double[][] tabDistance = matrice.generateMatrice(nbPointMatrice);
	
	public void paintComponent(Graphics g){                
		
		rempliArray(nbPointMatrice);
		
		for(int i = 0; i < nbPointMatrice; i++){
			
			paintSommet(g,i);
			
			if( i < nbPointMatrice - 1){
				paintArette(g,i);
			}
		} 
	  } 
	
	private void paintSommet(Graphics g, int i){
		g.setColor(Color.RED);
		g.fillOval(points.get(i).getX() - taille_point/2, points.get(i).getY() - taille_point/2, taille_point, taille_point);
	}
	
	private void paintArette(Graphics g, int i){
		g.setColor(Color.RED);
		g.drawLine(aretes.get(i).getX(),aretes.get(i).getY(),aretes.get(i).getX2(),aretes.get(i).getY2());
	}
	
	
	
	//méthode pour ArrayList
	
	// rempli les ArrayList
	public void rempliArray(int nbPoint){
		setPoint((int) (Math.random()*(390)),(int) (Math.random()*(490)));
		
		for (int i = 0; i < nbPoint; i++){
			setPoint((int) (Math.random()*(390)),(int) (Math.random()*(490)));
			setArete(points.get(i).getX(),points.get(i).getY(),points.get(i+1).getX(),points.get(i+1).getY());
		}
	}
	
	// ajout d'un point
	public void setPoint(int x, int y){
		points.add(new Point(x,y));
	}
	// ajout d'une arete
	public void setArete(int x, int y, int x2, int y2){
		aretes.add(new Arete(x,y,x2,y2));
	}
	
	
}
