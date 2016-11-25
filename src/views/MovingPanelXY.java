package views;

import java.awt.*;

/**
 * Created by blou on 21/11/16.
 */
public class MovingPanelXY extends MovingPanel {

    public MovingPanelXY() {
        super();
    }

    @Override
    void setLabelText() {
        this.kind_label.setText("XY");
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Font font = new Font("Serif", Font.PLAIN, 20);
        g.setFont(font);
        g.drawString("x", 655, 350);
        g.drawString("y", 350, 50); 
        g.drawOval((int)dynamicsPoint.getPosition()[0],(int)dynamicsPoint.getPosition()[1],10,10);
        System.out.println(dynamicsPoint.getPosition()[0]+ " __ "+dynamicsPoint.getPosition()[1]);
    }
}
