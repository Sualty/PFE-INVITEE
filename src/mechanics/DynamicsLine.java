package mechanics;

/**
 * a line composed of a begin point and a end point
 */
public class DynamicsLine {
    private DynamicsPoint point_begin;
    private DynamicsPoint point_end;

    public DynamicsLine() {
        this.point_begin = new DynamicsPoint();
        this.point_end = new DynamicsPoint();
    }
}
