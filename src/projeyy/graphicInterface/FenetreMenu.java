package projeyy.graphicInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FenetreMenu extends JFrame {
	//d�claration des diff�rents bouton composant le menu
	private JButton bouton1 = new JButton("exemple 1");
	private JButton bouton2 = new JButton("exemple 2");
	private JButton bouton3 = new JButton("exemple 3");
	//Sert a regrouper les diff�rents element graphiques
	private JPanel containers = new JPanel();
	//affiche des images ou du texte (superflu)je le laisse ici le temps de compl�t� l'interface
	private JLabel label = new JLabel("Menu");
	
	public FenetreMenu(){
		//nomme la fen�tre
		this.setTitle("Interface TSP");
		//definie sa taille
		this.setSize(400, 500);
		//positionne la fen�tre au milieu de l'�cran
		this.setLocationRelativeTo(null);
		//permet de fermer la fen�tre en appuyant sur la croix 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);              
		
		bouton1.addActionListener(new BoutonListener());
		bouton2.addActionListener(new Bouton2Listener());
		bouton3.addActionListener(new Bouton3Listener());
		//regroupement des boutons dans un JPanel
		JPanel nord = new JPanel();
		nord.add(bouton1);
		nord.add(bouton2);
		nord.add(bouton3);
		//Le JPanel des boutons est ins�r� et plac� dans le JPanel containers
		containers.add(nord,BorderLayout.NORTH);
		Font police = new Font("Tahoma", Font.BOLD, 16);
	    label.setFont(police);
	    label.setForeground(Color.GREEN);
	    label.setHorizontalAlignment(JLabel.CENTER);
	    //Le label est lui aussi plac� dans le containers
	    containers.add(label,BorderLayout.CENTER);
	    //d�finie le JPanel utilis� pour l'affichage
	    this.setContentPane(containers);
	    // permet l'affichage de la fen�tre
		this.setVisible(true);
  }
	//sous classe facilitant la structure du code, permet de d�finir diff�rentes actions pour les boutons
	class BoutonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {
		      label.setText("Acc�s bouton 1");        
		    }
	}
	
	class Bouton2Listener implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {
		      label.setText("Acc�s bouton 2");        
		    }
	}
	
	class Bouton3Listener implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {
		      label.setText("Acc�s bouton 3");        
		    }
	}
}
