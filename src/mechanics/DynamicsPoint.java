package mechanics;

/**
 * Point which is going to move according to its velocity and acceleration
 */
public class DynamicsPoint {

    private float[] position;
    private float[] vitesse;
    private float[] acceleration;
    private float timer;

    /**
     * default constructor
     */
    public DynamicsPoint() {
        this.position = new float[3];
        position[0] = 350;
        position[1] = 350;
        position[2] = 350;

        this.vitesse = new float[3];
        vitesse[0] = 0;
        vitesse[1] = 0;
        vitesse[2] = 0;

        this.acceleration = new float[3];
        acceleration[0] = 0;
        acceleration[1] = 0;
        acceleration[2] = 0;

        timer = 0;
    }


    /**
     * constructor
     * @param position
     * @param vitesse
     * @param acceleration
     */
    public DynamicsPoint(float[] position, float[] vitesse, float[] acceleration, float timer) {
        for(int i=0;i<3;i++) {
            this.position[i] = position[i];
            this.vitesse[i] = vitesse[i];
            this.acceleration[i] = acceleration[i];
            this.timer = timer;
        }
    }

    /**
     * updates the acceleration / velocity / position of the moving point
     * @param acc
     */
    public void updateData(float[] acc) {
        for(int i=0;i<3;i++) {
            this.acceleration[i] = acc[i]*9.81f;
        }
        float timer_before = timer;
        timer = acc[3];

        float facteur = 20000;
        vitesse[0] = (acceleration[0])*(timer-timer_before);
        vitesse[1] = (acceleration[1])*(timer-timer_before);
        vitesse[2] = (acceleration[2])*(timer-timer_before);
        System.out.print("Vitesse X "+vitesse[0]+" ");
        System.out.println("Vitesse Z "+vitesse[2]+" \n");
       /* position[0] += vitesse[0]*1/10;
        position[1] += vitesse[1]*1/10;
        position[2] += vitesse[2]*1/10;*/

        // vitesse = vitesse(t-1) + acceleration(t)*(timer(t) - timer(t-1))
       //position = position(t-1) + vitesse(t)*(timer(t) - timer(t-1))

        /*position[0] += vitesse[0]*(timer-timer_before)*facteur;
        position[1] += vitesse[1]*(timer-timer_before)*facteur;
        position[2] += vitesse[2]*(timer-timer_before)*facteur;*/

        //position[0] += velocity[0] * timeStep + 0.5 * acceleration[0] * timeStep * timeStep;
        //velocity[0] += acceleration[0] * timeStep;
        position[0] = 350+facteur*(vitesse[0]*(timer-timer_before) + 0.5f*acceleration[0]*(timer-timer_before)*(timer-timer_before)) ;
        position[1] = 350+facteur*(vitesse[1]*(timer-timer_before) + 0.5f*acceleration[1]*(timer-timer_before)*(timer-timer_before)) ;
        position[2] = 350+facteur*(vitesse[2]*(timer-timer_before) + 0.5f*acceleration[2]*(timer-timer_before)*(timer-timer_before)) ;
        /*System.out.print("Position X "+position[0]+" ");
        System.out.print("Position Z "+position[2]+" ");*/
    }

    public float[] getAcceleration() {
        return this.acceleration;
    }

    public float[] getPosition() {
        return this.position;
    }


}
