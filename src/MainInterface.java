import java.io.IOException;

import javax.swing.JTabbedPane;

import views.ComposantOnglet;
import views.Frame;
import views.MovingPanel3D;
import views.MovingPanelXY;
import views.MovingPanelXZ;
import views.MovingPanelYZ;
import views.VariablesPanel;

public class MainInterface {
		
	private static JTabbedPane pane;
	
	public static void main(String[] args) throws IOException {
 
	  MainInterface minterface = new MainInterface();
	  minterface.initComponents();
	  
	  Frame frame = new Frame();
	  frame.add(pane);
	  frame.setVisible(true);
	}
  
	public void initComponents() throws IOException {
	  	pane = new JTabbedPane();
	    pane.add(new MovingPanelXY());
	    pane.setTabComponentAt(0, new ComposantOnglet("XY", pane));
	    pane.add(new MovingPanelXZ());
	    pane.setTabComponentAt(1, new ComposantOnglet("XZ", pane));
	    pane.add(new MovingPanelYZ());
	    pane.setTabComponentAt(2, new ComposantOnglet("YZ", pane));
		try {
			pane.add(new MovingPanel3D());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pane.setTabComponentAt(3, new ComposantOnglet("3D", pane));
	    pane.add(new VariablesPanel());
	    pane.setTabComponentAt(4, new ComposantOnglet("Variables of test", pane));
	    
	}
}
