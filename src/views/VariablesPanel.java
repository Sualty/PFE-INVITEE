package views;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class VariablesPanel extends JPanel {
	
	public static String path_file = "src/res/centrale1.txt";
	
	public VariablesPanel () throws IOException {
		
		/* Le premier BufferedReader pour compter le nb de lignes */
		BufferedReader reader1 = new BufferedReader(new FileReader(path_file));
		int nbLines = 0;
		
		while (reader1.readLine() != null) nbLines++;
		reader1.close();
		
		/* Définir le format du tableau */
		String[] columnNames = {"Compteur","roll","pitch","yaw","accel_x","accel_y","accel_z"};
		String[][] data = new String[nbLines][columnNames.length];
		
		/* Le deuxième BufferedReader pour lire les données du fichier log */
		BufferedReader reader2 = new BufferedReader(new FileReader(path_file));
		int indexLine = 0;
		String thisLine;
		
		while ((thisLine = reader2.readLine()) != null) {
		    if (indexLine != 0) {
		    	String[] dataOfLine = thisLine.split(",");

		    	for (int i=0; i<dataOfLine.length; i++) {
		    		data[indexLine][i] = dataOfLine[i];
		    	}
		    }
		    indexLine++;
		}
		reader2.close();
		
		
		/* Mettre les données dans le tableau et afficher le tableau qui est compris dans un scrollPanel */
		JTable table = new JTable(data, columnNames);
		this.setLayout(new BorderLayout());
		this.add(table.getTableHeader(), BorderLayout.PAGE_START);
		this.add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);

//        JTable table = new JTable(data, columnNames);
//		
//	    JScrollPane scrollPanel = new JScrollPane();
//        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
//        scrollPanel.add(table);
//        scrollPanel.setVisible(true);
//        
//        this.setLayout(new BorderLayout());
//        this.add(scrollPanel);
//        this.setVisible(true);
        
        
        
        
		

	}
}
