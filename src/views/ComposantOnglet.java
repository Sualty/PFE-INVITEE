package views;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
 
public class ComposantOnglet extends JPanel implements ActionListener {
  private JTabbedPane pane;
 
  public ComposantOnglet(String titre, JTabbedPane pane) {
    this.pane = pane;
    setOpaque(false);
    JLabel label = new JLabel(titre);
    add(label);
    JButton button = new JButton("Close");
    button.setPreferredSize(new Dimension(100,20));
    button.addActionListener(this);
    add(button);
  }
 
  public void actionPerformed(ActionEvent e) {
    int i = pane.indexOfTabComponent(this);
    if (i != -1)
      pane.remove(i);
  }
}