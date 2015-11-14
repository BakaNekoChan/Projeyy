package projeyy.projeyyInterface;

import java.awt.Color; 
import javax.swing.JFrame;
import javax.swing.JPanel;

import projeyy.generator.Generator;

import java.awt.Graphics;

public class PanChemin extends JPanel {

	int x;
	int y;
	int x2;
	int y2;
	
	// mise en place de la matrice distance avec generator, matrice xy recupère l'emplacement des différents points.	
		int nbPointMatrice = 5;
		Generator matrice = new Generator();
		double[][] tabDistance = matrice.generateMatrice(nbPointMatrice);
	
	public void paintComponent(Graphics g){                
		x = (int) (Math.random()*(400));
		y = (int) (Math.random()*(500));
		
		for(int i = 0; i < nbPointMatrice; i++){
			
			x2 = (int) (Math.random()*(400));
			y2 = (int) (Math.random()*(500));
			
			paintSommet(g,x,y);
			if( i < nbPointMatrice - 1){
				paintArette(g,x,y,x2,y2);
			}
			x = x2; y = y2;
		} 
	  } 
	
	private void paintSommet(Graphics g, int x, int y){
		g.setColor(Color.RED);
		g.fillOval(x - 25/2, y - 25/2, 25, 25);
	}
	
	private void paintArette(Graphics g, int x, int y, int x2, int y2){
		g.drawLine(x, y, x2, y2);
	}
	
	//getteur
	public int getPosX(){
		return x;
	}
	
	public int getPosY(){
		return y;
	}
	
	//setteur
	public void setPosX(int x){
		this.x = x;
	}
	public void setPosY(int y){
		this.y = y;
	}
	
	
}
