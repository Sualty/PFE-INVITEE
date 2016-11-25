package views;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame {

	  public Frame(){
		    this.setTitle("Test");
//		    //Définir sa taille : x pixels de large et y pixels de haut
		    this.setSize(1000, 700);
		    //Pour que l'objet se positionne au centre
		    this.setLocationRelativeTo(null);
		    //Terminer le processus lorsqu'on clique sur la croix rouge
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
	  }
	  

}
