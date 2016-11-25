package views;

import java.awt.*;

/**
 * Created by blou on 21/11/16.
 */
public class MovingPanelYZ extends MovingPanel {

    public MovingPanelYZ() {
        super();
    }

    @Override
    void setLabelText() {
        this.kind_label.setText("YZ");
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Font font = new Font("Serif", Font.PLAIN, 20);
        g.setFont(font);
        g.drawString("y", 655, 350);
        g.drawString("z", 350, 50); 
        g.drawOval((int)dynamicsPoint.getPosition()[1],(int)dynamicsPoint.getPosition()[2],10,10);
    }
}
