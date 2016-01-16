package projeyy.projeyyInterface;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

import projey.algorithme.Algorithme;
import projey.algorithme.BackTrack;
import projey.algorithme.BrutForce3;
import projey.algorithme.Genetique;

import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

public class Fenetre extends JFrame implements Observer {

	//taille de la fenetre
	int tailleX = 1000;
	int tailleY = 1000;
	
	int nbrVille = 6;
	protected AlgoEnum algo;

// d�claration des JPanel
	private JPanel generalPan = new JPanel();
	private PanChemin centerPan = new PanChemin();
	private PanDistance bottomPan = new PanDistance();
// d�claration JLabel
	private JLabel northLabel = new JLabel("Bienvenu sur le TSP !");
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
	private JRadioButtonMenuItem brutforce3 = new JRadioButtonMenuItem("Brutforce 3");
	private JRadioButtonMenuItem backtrack = new JRadioButtonMenuItem("Backtrack");
	private JRadioButtonMenuItem genetique = new JRadioButtonMenuItem("Genetique");
	private JMenuItem nbVille = new JMenuItem("Nombre de ville");
	
// item de � propos
	private JMenuItem apropos = new JMenuItem("?");
	protected Algorithme monAlgoBrutForce3 = new BrutForce3(nbrVille);
	protected Algorithme monAlgoBacktrack = new BackTrack(nbrVille);
	protected Algorithme monAlgoGenetique = new Genetique(nbrVille, 10, 10);

	
	
	public Fenetre(){
		algo = AlgoEnum.BrutForce3;
		this.monAlgoBacktrack.addObserver(this);
		this.monAlgoGenetique.addObserver(this);
		this.monAlgoBrutForce3.addObserver(this);
		
		this.setTitle("TSP");
		this.setSize(tailleX, tailleY);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
		this.setLayout(new BorderLayout(0,0));
		
		northLabel.setFont(courier);  
	    northLabel.setForeground(Color.black);  
	    northLabel.setHorizontalAlignment(JLabel.CENTER);
	    
	    northLabel.setMinimumSize(new Dimension(tailleX,50));
	    northLabel.setPreferredSize(new Dimension(tailleX,50));
	    centerPan.setMinimumSize(new Dimension(tailleX-50,tailleY-425));
	    centerPan.setPreferredSize(new Dimension(tailleX-50,tailleY-425));
	    bottomPan.setMinimumSize(new Dimension(tailleX,100));
	    bottomPan.setPreferredSize(new Dimension(tailleX,100));
	    
		generalPan.add(northLabel, BorderLayout.NORTH);
		generalPan.add(centerPan);
		generalPan.add(bottomPan, BorderLayout.SOUTH);
		this.initMenu();
		this.setContentPane(generalPan);
		this.setVisible(true);
  }
	
	public void initMenu(){
	// menu fichier
		lancer.addActionListener(
				new ActionListener(){ 
					public void actionPerformed(ActionEvent event){
						ThreadAlgo exe;
						switch(algo){
							case BackTrack:
								exe = new ThreadAlgo(monAlgoBacktrack);
								exe.start();
								break;
							case Genetique:
								exe = new ThreadAlgo(monAlgoGenetique);
								exe.start();
								break;
							default:
								exe = new ThreadAlgo(monAlgoBrutForce3);
								exe.start();
								break;
								
						}
					}
				}
			);
		fichier.add(lancer);
		fichier.add(save);
		fichier.add(charger);
		fichier.addSeparator();
	//pour quitter l'application 
		quitter.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent event){
							System.exit(0);
							}
						}
					);
		fichier.add(quitter);
	// menu preference
		brutforce3.addActionListener(new BrutForce3Listener());
		backtrack.addActionListener(new BacktrackListener());
		genetique.addActionListener(new GenetiqueListener());
		
		bg.add(brutforce3);
		bg.add(backtrack);
		bg.add(genetique);
		
		
		choixAlgo.add(brutforce3);
		choixAlgo.add(backtrack);
		choixAlgo.add(genetique);
		
		brutforce3.setSelected(true);
		
		preference.add(choixAlgo);
		nbVille.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
			    String nb = JOptionPane.showInputDialog(null, "Veuillez Choisir le nombre de ville !", "nombres villes", JOptionPane.QUESTION_MESSAGE);
			    if(nbVille != null){
			    JOptionPane.showMessageDialog(null, "Vous avez choisi " + nb + " villes", "Choix effectu�", JOptionPane.INFORMATION_MESSAGE);
			    nbrVille = Integer.parseInt(nb);
			    monAlgoBrutForce3 = new BrutForce3(nbrVille);
			    monAlgoBacktrack = new BackTrack(nbrVille);
			    monAlgoGenetique= new Genetique(nbrVille,10,10);
				Fenetre.this.monAlgoBacktrack.addObserver(Fenetre.this);
				Fenetre.this.monAlgoGenetique.addObserver(Fenetre.this);
				Fenetre.this.monAlgoBrutForce3.addObserver(Fenetre.this);
			    }
			    else{
				    JOptionPane.showMessageDialog(null, "Vous n'avez pas choisi, le nombre de villes sera donc 5.","Ok", JOptionPane.INFORMATION_MESSAGE);
				    nbrVille = 5;
			    }
			}
		});
		preference.add(nbVille);
	//menu apropos
		apropos.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent arg0) {
		        JOptionPane jop = new JOptionPane();        
		        String mess = "Bienvenu ! \n Voici l'application du probl�me du voyageur de commerce (TSP) !\n";
		        mess += "Cr�� par des �tudiants dans le cadre d'un projet tutor� :  \n";
		        mess += "\n Lo�c Wisnieswki, Anthony Chaillot, Romain Hagemann et  Lucie Borrossi . \n";
		        mess += "\n Enjoy !";        
		        JOptionPane.showMessageDialog(null, mess, "� propos", JOptionPane.INFORMATION_MESSAGE);        
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
	
	 class BrutForce3Listener implements ActionListener{
		    //Red�finition de la m�thode actionPerformed()
		    public void actionPerformed(ActionEvent arg0) {
		      northLabel.setText("L'algorithme actuellement ex�cut� est : BruteForce 3");
		      algo = AlgoEnum.BrutForce3;
		    }
	 }
	 
	 class BacktrackListener implements ActionListener{
		    //Red�finition de la m�thode actionPerformed()
		    public void actionPerformed(ActionEvent arg0) {
		      northLabel.setText("L'algorithme actuellement ex�cut� est : BackTrack"); 
		      algo = AlgoEnum.BackTrack;
		    }
	 }
	 
	 class GenetiqueListener implements ActionListener{
		    //Red�finition de la m�thode actionPerformed()
		    public void actionPerformed(ActionEvent arg0) {
		      northLabel.setText("L'algorithme actuellement ex�cut� est : Genetique"); 
		      algo = AlgoEnum.Genetique;
		    }
	 }
	 
	 //methode observer
	 public void update(Observable o, Object arg){

			bottomPan.setDistanceP(bottomPan.getDistance());
			bottomPan.setDistance(((Algorithme) o).getDistancePlusCourtChemin());
			centerPan.viderTout();
			centerPan.setArrayPoint(new ArrayList<Point>(((Algorithme)o).getPoints()));
			ArrayList<Integer> ordrePoints = new ArrayList<Integer>(((Algorithme) o).getPlusCourtChemin());
			
			for(int i = 1; i< ordrePoints.size();i++){
				centerPan.setArete(centerPan.getPoints().get(ordrePoints.get(i-1)), centerPan.getPoints().get(ordrePoints.get(i)));
			}
			
			centerPan.setArete(centerPan.getPoints().get(ordrePoints.get(ordrePoints.size()-1)), centerPan.getPoints().get(ordrePoints.get(0)));
			repaint();
	}
	 
	public void setTailleX(int x){
		tailleX = x;
	}
	public void setTailleY(int y){
		tailleY = y;
	}
}