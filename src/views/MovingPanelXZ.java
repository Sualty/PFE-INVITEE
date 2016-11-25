package views;

import java.awt.*;

/**
 * Created by blou on 21/11/16.
 */
public class MovingPanelXZ extends MovingPanel {


    public MovingPanelXZ() {
        super();
    }

    @Override
    void setLabelText() {
        this.kind_label.setText("XZ");
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Font font = new Font("Serif", Font.PLAIN, 20);
        g.setFont(font);
        g.drawString("x", 655, 350);
        g.drawString("z", 350, 50); 
        g.drawOval((int)dynamicsPoint.getPosition()[0],(int)dynamicsPoint.getPosition()[2],10,10);
        System.out.println("Positions " + dynamicsPoint.getPosition()[0]+" ___ "+dynamicsPoint.getPosition()[2]);
        System.out.println("Accelerations "+dynamicsPoint.getAcceleration()[0]+" ___ "+dynamicsPoint.getAcceleration()[2]);
    }
}
