package projeyy.projeyyInterface;


import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Fenetre extends JFrame {
//taille de la fenetre
	int tailleX = 400;
	int tailleY = 500;	

// déclaration des différents JPanel

	private PanChemin generalPan = new PanChemin();
	
	public Fenetre(){
		this.setTitle("Test interface");
		this.setSize(tailleX, tailleY);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
		
		this.setContentPane(generalPan);
		this.setVisible(true);
  }
	
}