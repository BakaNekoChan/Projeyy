package projeyy.projeyyInterface;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;

import projey.algorithme.BrutForce3;

import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

public class Fenetre extends JFrame implements Observer {
//taille de la fenetre
	int tailleX = 1000;
	int tailleY = 1000;	

// d�claration des JPanel
	private JPanel generalPan = new JPanel();
	private PanChemin centerPan = new PanChemin();
	private PanDistance bottomPan = new PanDistance();
// d�claration JLabel
	private JLabel northLabel = new JLabel();
	private Font courier = new Font("Courier",Font.BOLD,14);
	
// d�claration des diff�rents �l�ments du menu
	private JMenuBar menuBar = new JMenuBar();
	private ButtonGroup bg = new ButtonGroup();

	
	private JMenu fichier = new JMenu("Fichier");
	private JMenu preference = new JMenu("Pr�f�rence");
	private JMenu aPropos = new JMenu("� propos");
	private JMenu choixAlgo = new JMenu("Choix de l'algorithme");
	
// item de Fichier
	private JMenuItem lancer = new JMenuItem("Lancer");
	private JMenuItem charger = new JMenuItem("Charger une matrice");
	private JMenuItem save = new JMenuItem("Sauvegarder une matrice");
	private JMenuItem quitter = new JMenuItem("Quitter");
	
// item de Pr�f�rence
	private JRadioButtonMenuItem brutforce = new JRadioButtonMenuItem("Brutforce");
	private JRadioButtonMenuItem brutforce2 = new JRadioButtonMenuItem("Brutforce 2");
	private JRadioButtonMenuItem brutforce3 = new JRadioButtonMenuItem("Brutforce 3");
	private JRadioButtonMenuItem mst = new JRadioButtonMenuItem("MST");
	private JRadioButtonMenuItem genetique = new JRadioButtonMenuItem("Genetique");
	private JMenuItem nbVille = new JMenuItem("Nombre de ville");
	
// item de � propos
	private JMenuItem apropos = new JMenuItem("?");
	
	
	
	public Fenetre(){
		this.setTitle("TSP");
		this.setSize(tailleX, tailleY);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
		this.setLayout(new BorderLayout());
		
		northLabel.setFont(courier);  
	    northLabel.setForeground(Color.black);  
	    northLabel.setHorizontalAlignment(JLabel.CENTER);
	    
		generalPan.add(northLabel, BorderLayout.NORTH);
		generalPan.add(centerPan, BorderLayout.CENTER);
		generalPan.add(bottomPan, BorderLayout.SOUTH);
		this.initMenu();
		this.setContentPane(generalPan);
		this.setVisible(true);
  }
	
	public void initMenu(){
	// menu fichier
		fichier.add(lancer);
		fichier.add(save);
		fichier.add(charger);
		fichier.addSeparator();
	//pour quitter l'application 
		quitter.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent event){System.exit(0);}});
		fichier.add(quitter);
	// menu preference
		brutforce.addActionListener(new BrutForceListener());
		brutforce2.addActionListener(new BrutForce2Listener());
		brutforce3.addActionListener(new BrutForce3Listener());
		mst.addActionListener(new MstListener());
		genetique.addActionListener(new GenetiqueListener());
		
		bg.add(brutforce);
		bg.add(brutforce2);
		bg.add(brutforce3);
		bg.add(mst);
		bg.add(genetique);
		
		
		choixAlgo.add(brutforce);
		choixAlgo.add(brutforce2);
		choixAlgo.add(brutforce3);
		choixAlgo.add(mst);
		choixAlgo.add(genetique);
		
		brutforce3.setSelected(true);
		
		preference.add(choixAlgo);
		preference.add(nbVille);
	//menu apropos
		apropos.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent arg0) {
		        JOptionPane jop = new JOptionPane();        
		        String mess = "Bienvenu ! \n Voici l'application du probl�me du voyageur de commerce (TSP) !\n";
		        mess += "Cr�� par des �tudiants dans le cadre d'un projet tutor� \n";
		        mess += "\n Enjoy !";        
		        jop.showMessageDialog(null, mess, "� propos", JOptionPane.INFORMATION_MESSAGE);        
		      }            
		    });
		aPropos.add(apropos);
		
		menuBar.add(fichier);
		menuBar.add(preference);
		menuBar.add(aPropos);
		
		this.setJMenuBar(menuBar);
	}
	
	
	//getter 
	public PanChemin getPan(){
		return centerPan;
	}
	
	//class interne
	 class BrutForceListener implements ActionListener{
		    //Red�finition de la m�thode actionPerformed()
		    public void actionPerformed(ActionEvent arg0) {
		      northLabel.setText("L'algorithme actuellement ex�cut� est : BruteForce");        
		    }
	}
	 
	 class BrutForce2Listener implements ActionListener{
		    //Red�finition de la m�thode actionPerformed()
		    public void actionPerformed(ActionEvent arg0) {
		      northLabel.setText("L'algorithme actuellement ex�cut� est : BruteForce 2");        
		    }
	 }
	 
	 class BrutForce3Listener implements ActionListener{
		    //Red�finition de la m�thode actionPerformed()
		    public void actionPerformed(ActionEvent arg0) {
		      northLabel.setText("L'algorithme actuellement ex�cut� est : BruteForce 3");        
		    }
	 }
	 
	 class MstListener implements ActionListener{
		    //Red�finition de la m�thode actionPerformed()
		    public void actionPerformed(ActionEvent arg0) {
		      northLabel.setText("L'algorithme actuellement ex�cut� est : MST");        
		    }
	 }
	 
	 class GenetiqueListener implements ActionListener{
		    //Red�finition de la m�thode actionPerformed()
		    public void actionPerformed(ActionEvent arg0) {
		      northLabel.setText("L'algorithme actuellement ex�cut� est : Genetique");        
		    }
	 }
	 
	 //methode observer
	 public void update(Observable o, Object arg){

			bottomPan.setDistanceP(bottomPan.getDistance());
			bottomPan.setDistance(((BrutForce3) o).getDistancePlusCourtChemin());
			centerPan.viderTout();
			centerPan.setArrayPoint(new ArrayList<Point>(((BrutForce3)o).getPoints()));
			ArrayList<Integer> ordrePoints = new ArrayList<Integer>(((BrutForce3) o).getPlusCourtChemin());
			
			for(int i = 0; i< ordrePoints.size();i++){
				centerPan.setArete(centerPan.getPoints().get(ordrePoints.get(i-1)), centerPan.getPoints().get(ordrePoints.get(i)));
			}
			
			centerPan.setArete(centerPan.getPoints().get(ordrePoints.get(ordrePoints.size()-1)), centerPan.getPoints().get(ordrePoints.get(0)));
			repaint();
		}
	
}