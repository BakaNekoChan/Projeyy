package projeyy.projeyyInterface;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

public class Fenetre extends JFrame {
//taille de la fenetre
	int tailleX = 1000;
	int tailleY = 1000;	

// d�claration des JPanel
	private PanChemin generalPan = new PanChemin();
	private PanDistance bottomPan = new PanDistance();

// d�claration des diff�rents �l�ments du menu
	private JMenuBar menuBar = new JMenuBar();
	private ButtonGroup bg = new ButtonGroup();

	
	private JMenu fichier = new JMenu("Fichier");
	private JMenu preference = new JMenu("Préférence");
	private JMenu aPropos = new JMenu("À propos");
	private JMenu choixAlgo = new JMenu("Choix de l'algorithme");
	
// item de Fichier
	private JMenuItem lancer = new JMenuItem("Lancer");
	private JMenuItem charger = new JMenuItem("Charger une matrice");
	private JMenuItem save = new JMenuItem("Sauvegarder une matrice");
	private JMenuItem quitter = new JMenuItem("Quitter");
	
// item de Pr�f�rence
	private JRadioButtonMenuItem brutforce = new JRadioButtonMenuItem("Brutforce");
	private JRadioButtonMenuItem bruteforce2 = new JRadioButtonMenuItem("Bruteforce 2");
	private JRadioButtonMenuItem bruteforce3 = new JRadioButtonMenuItem("Bruteforce 3");
	private JRadioButtonMenuItem mst = new JRadioButtonMenuItem("MST");
	private JRadioButtonMenuItem genetique = new JRadioButtonMenuItem("Genetique");
	private JMenuItem nbVille = new JMenuItem("Nombre de ville");
	
// item de � propos
	private JMenuItem apropos = new JMenuItem("?");
	
	
	
	public Fenetre(){
		this.setTitle("Test interface");
		this.setSize(tailleX, tailleY);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
		this.setLayout(new BorderLayout());
		this.getContentPane().add(generalPan, BorderLayout.CENTER);
		this.getContentPane().add(bottomPan, BorderLayout.EAST);
		this.initMenu();
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
		bg.add(brutforce);
		bg.add(bruteforce2);
		bg.add(bruteforce3);
		bg.add(mst);
		bg.add(genetique);
		
		choixAlgo.add(brutforce);
		choixAlgo.add(bruteforce2);
		choixAlgo.add(bruteforce3);
		choixAlgo.add(mst);
		choixAlgo.add(genetique);
		
		bruteforce3.setSelected(true);
		
		preference.add(choixAlgo);
		preference.add(nbVille);
	//menu apropos
		apropos.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent arg0) {
		        JOptionPane jop = new JOptionPane();        
		        String mess = "Bienvenu ! \n Voici l'application du problème du voyageur de commerce (TSP) !\n";
		        mess += "Créé par des étudiants dans le cadre d'un projet tutoré \n";
		        mess += "\n Enjoy !";        
		        jop.showMessageDialog(null, mess, "À propos", JOptionPane.INFORMATION_MESSAGE);        
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
		return generalPan;
	}
	
}