package views;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;

import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d.RotPosPathInterpolatorState;
import com.sun.j3d.utils.universe.SimpleUniverse;



public class MovingPanel3D extends JPanel {


    private int valeur=0;//TODO

    private Quat4f[] quats;
    private Point3f[] positions;

    private float x_max = 0;
    private float x_min = 0;
    private float y_max = 0;
    private float y_min = 0;
    private float z_max = 0;
    private float z_min = 0;

    private boolean norm = true;
    private boolean init = true;

    private JLabel values_display;

    public MovingPanel3D() throws IOException, InterruptedException {

        this.setLayout(new BorderLayout());

      /* Create a Canvas3D object to be used for rendering the Java 3D universe */
        Canvas3D canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        add(BorderLayout.CENTER,canvas3D);


        /* Create an empty Java 3D universe and associate it with the Canvas3D object */
        BranchGroup scene = createSceneGraph();
        SimpleUniverse simpleUniverse = new SimpleUniverse(canvas3D);

        // scene.addChild(createSceneGraph());

        simpleUniverse.getViewingPlatform().setNominalViewingTransform();
        simpleUniverse.addBranchGraph(scene);
        this.add(canvas3D);
    }

    public BranchGroup createSceneGraph() throws IOException, InterruptedException {

        //----------------------début de la création de la translation et de la rotation--------------------------------

        //on crée le BranchGroup principal
        BranchGroup objRoot=new BranchGroup();

        // on crée un fonction de rotation au cours du temps
        Alpha transAlpha=new Alpha(-1,6000);

        TransformGroup objSpin=new TransformGroup();
        objSpin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);// permet de modifier l'objet pendant l'execution

        Transform3D axisOfRotPos=new Transform3D();



        // ???
        //AxisAngle4f axis = new AxisAngle4f(1.0f, 0.0f, 0.0f, 0.0f);
        //axisOfRotPos.set(axis);


        //----------------------initialisation--------------------------------
        //initilaisation des tableaux de données
        initPositionsAndQuats();

        // On normalise les valeurs du tableau entre -1 et 1
        normalisation();

        //On associe un temps à chaque point
        // Concrètement on divise 1 par le nombre de points moins 1 et on multiplie par j

        int nb_donnees = positions.length;
        float tmp = positions.length;
        float[] timePosition= new float[nb_donnees];

        for (int j=0; j<nb_donnees; j++){
            timePosition[j] = (1/(tmp-1))*j;
        }

        RotPosPathInterpolator interpol=new RotPosPathInterpolator(transAlpha,
                objSpin,
                axisOfRotPos,
                timePosition,
                quats,
                positions);


        // on définit la zone sur laquelle va s'appliquer le chemin

        BoundingSphere bounds=new BoundingSphere();
        interpol.setSchedulingBounds(bounds);

        // Ajout lumière bleue (sinon l'objet est noir et on voit rien)
        Color3f light1Color = new Color3f(0.1f, 0.1f, 1.8f);
        Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
        DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
        light1.setInfluencingBounds(bounds);
        objSpin.addChild(light1);



        objSpin.addChild( interpol);

        //----------------------fin de la création de la translation et de la rotation--------------------------------


        // on cree une sphere qui hérite de la translation
        objSpin.addChild(new Sphere(0.15f));
        objSpin.addChild(new Cylinder(0.10f,1f));
        objSpin.addChild(new Box(0.10f, 0.10f, 0.10f, null));
        objRoot.addChild(objSpin);

        return objRoot;
    }

    public void initPositionsAndQuats() throws IOException {
        //Ouverture du fichier
        FileReader input = new FileReader("src/res/centrale1.txt");
        BufferedReader bufRead = new BufferedReader(input);
        String myLine = null;

        myLine = bufRead.readLine();//première ligne de consigne

        int i = 0;

        while ( (myLine = bufRead.readLine()) != null){
            valeur++;
        }

        //initialisation des tableaux
        quats = new Quat4f[valeur];
        positions = new Point3f[valeur];

        //Parcours du fichier pour trouver le min et le max de chaque coordonnee
        input.close();
        bufRead.close();
        input = new FileReader("src/res/centrale1.txt");
        bufRead = new BufferedReader(input);

        myLine = bufRead.readLine();//première ligne de consigne

        while ( (myLine = bufRead.readLine()) != null){
            String[] array = myLine.split(",");
            if (x_max <  Float.parseFloat(array[4])){
                x_max =  Float.parseFloat(array[4]);
            }
            if (x_min >  Float.parseFloat(array[4])){
                x_min =  Float.parseFloat(array[4]);
            }

            if (y_max <  Float.parseFloat(array[5])){
                y_max =  Float.parseFloat(array[5]);
            }
            if (y_min >  Float.parseFloat(array[5])){
                y_min =  Float.parseFloat(array[5]);
            }

            if (z_max <  Float.parseFloat(array[6])){
                z_max =  Float.parseFloat(array[6]);
            }
            if (z_min >  Float.parseFloat(array[6])){
                z_min =  Float.parseFloat(array[6]);
            }

            positions[i] = new Point3f (
                    Float.parseFloat(array[4]),
                    Float.parseFloat(array[5]),
                    Float.parseFloat(array[6])
            );

            quats[i] = new Quat4f (
                    Float.parseFloat(array[1]),
                    Float.parseFloat(array[2]),
                    Float.parseFloat(array[3]),
                    1.0f //Centre de la rotation sur la pointe
            );

            i++;
            System.out.println(i);
        }
    }

    public void normalisation() {
        for (int k = 0; k<valeur; k++){
            float x;
            float y;
            float z;
            x = positions[k].x;
            y = positions[k].y;
            z = positions[k].z;
            positions[k].x = ((2*(x - x_min)/(x_max-x_min)) - 1f)/2;
            positions[k].y = ((2*(y - y_min)/(y_max-y_min)) - 1f)/2;
            positions[k].z = ((2*(z - z_min)/(z_max-z_min)) - 1f)/2;
        }
    }
}//