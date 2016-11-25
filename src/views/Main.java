package views;

import javax.swing.*;

/**
 * Created by blou on 21/11/16.
 */
public class Main {

    public static void main(String[] argv) {
        MovingPanelXZ movingPanel = new MovingPanelXZ();
        JFrame frame = new JFrame();
        frame.add(movingPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
