package views;

import mechanics.DynamicsPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class MovingPanel extends JPanel{

    protected DynamicsPoint dynamicsPoint;
    protected ArrayList<float[]> list_accelerations;

    protected Label kind_label;

    protected float[] minmaxX;
    protected float[] minmaxY;
    protected float[] minmaxZ;

    public MovingPanel() {
        super();
        setPreferredSize( new Dimension( 1000, 1000 ) );

        //init minmax tables
        minmaxX = new float[]{999,999};
        minmaxY = new float[]{999,999};
        minmaxZ = new float[]{999,999};

        //setting dynamic point
        dynamicsPoint = new DynamicsPoint();

        //seting and adding label
        kind_label = new Label();
        setLabelText();
        this.add(kind_label);

        //setting and "fulling" liste_acceleration
        this.list_accelerations = new ArrayList<>();
        try {
            initArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //setting jtextarea
        final JTextArea textArea = new JTextArea();
        textArea.setMinimumSize(new Dimension(200,50));
        textArea.setText("500000");
        this.add(textArea);

        //setting and adding button
        JButton buttonPlay = new JButton("Play");
        buttonPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dynamicsPoint = new DynamicsPoint();
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        float[] f=new float[4];
                        for(int i=0;i<list_accelerations.size();i++) {
                            for(int k=0;k<4;k++)
                                f[k] = list_accelerations.get(i)[k];
                            setMinMax(f);
                        }
                        for(int i=0;i<list_accelerations.size();i++) {
                            for(int k=0;k<4;k++)
                                f[k] = list_accelerations.get(i)[k];
                            dynamicsPoint.updateData(f);
                            repaint();
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("MINMAX X :"+minmaxX[0]+" "+minmaxX[1]);
                        System.out.println("MINMAX Y :"+minmaxY[0]+" "+minmaxY[1]);
                        System.out.println("MINMAX Z :"+minmaxZ[0]+" "+minmaxZ[1]);
                    }});
                thread.start();
            }
        });

        this.add(buttonPlay);

        //setting and adding erase button
        JButton button_erase = new JButton("Erase");
        button_erase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                erase();
            }
        });
        this.add(button_erase);
    }
    
    @Override
    public void paintComponent(Graphics g){
        //super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.draw(new Line2D.Double(350, 650, 350, 45));
        g2.draw(new Line2D.Double(50, 350, 650, 350));
       
    }


    public void initArray() throws IOException {
        FileReader input = new FileReader("src/res/centrale1.txt");
        BufferedReader bufRead = new BufferedReader(input);
        String myLine = null;

        myLine = bufRead.readLine();//première ligne de consigne
        while ( (myLine = bufRead.readLine()) != null)
        {
            String[] array = myLine.split(",");

            float[] tmp = new float[4];

            tmp[0] = Float.parseFloat(array[4]);
            tmp[1] = Float.parseFloat(array[5]);
            tmp[2] = Float.parseFloat(array[6]);
            tmp[3] = Float.parseFloat(array[0]);
          //  System.out.println(tmp[0]+"");
            //System.out.println(tmp[2]+"");
            this.list_accelerations.add(tmp);
        }
    }

    abstract void setLabelText();

    public void erase() {
        Graphics g = this.getGraphics();
        super.paintComponent(g);
        paintComponent(g);
    }

    public void setMinMax(float[] tab) {
        //X
        if(minmaxX[0]==999) //si le min est pas défini
            minmaxX[0]=tab[0];

        else if(minmaxX[0]<=tab[0]) { //sinon, si le min est inférieur à la valeur de tab
            if (minmaxX[1] == 999 || minmaxX[1] < tab[0])//on checke le max en conséquence
                minmaxX[1] = tab[0];
        }

        else if(minmaxX[0]>tab[0]) {//si la valeur de tab est inférieure au min, c'est le nouveau min
                if(minmaxX[1]==999)
                    minmaxX[1] = minmaxX[0];
                minmaxX[0] = tab[0];
        }

        //Y
        if(minmaxY[0]==999) //si le min est pas défini
            minmaxY[0]=tab[0];

        else if(minmaxY[0]<=tab[1]) { //sinon, si le min est inférieur à la valeur de tab
            if (minmaxY[1] == 999 || minmaxY[1] < tab[1])//on checke le max en conséquence
                minmaxY[1] = tab[1];
        }

        else if(minmaxY[0]>tab[1]) {//si la valeur de tab est inférieure au min, c'est le nouveau min
                if(minmaxY[1]==999)
                    minmaxY[1] = minmaxY[0];
                minmaxY[0] = tab[1];
        }
        //Z
        if(minmaxZ[0]==999) //si le min est pas défini
            minmaxZ[0]=tab[2];

        else if(minmaxZ[0]<=tab[2]) { //sinon, si le min est inférieur à la valeur de tab
            if (minmaxZ[1] == 999 || minmaxX[1] < tab[2])//on checke le max en conséquence
                minmaxZ[1] = tab[2];
        }
        else if(minmaxZ[0]>tab[2]) {//si la valeur de tab est inférieure au min, c'est le nouveau min
            if(minmaxZ[1]==999)
                minmaxZ[1] = minmaxZ[0];
            minmaxZ[0] = tab[2];
        }
    }
}
