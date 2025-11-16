/**
 * Creates a rocket object that extends the AirObject class and also contains
 * the rocket's ascent rate and trajectory.
 * 
 * @author madelync05 and ellae
 * @version Fall 2025
 */
public class Rocket
    extends AirObject
{
    // ~ Fields ................................................................
    private int ascentRate;
    private double trajectory;

    // ~ Constructors ..........................................................
    /**
     * Create a new Rocket object.
     * 
     * @param n
     *            name of the rocket
     * @param x
     *            x-cord for origin
     * @param y
     *            y-cord for origin
     * @param z
     *            z-cord for origin
     * @param xWidth
     *            x-cord for width
     * @param yWidth
     *            y-cord for width
     * @param zWidth
     *            z-cord for width
     * @param ar
     *            ascent rate
     * @param t
     *            trajectory
     */
    public Rocket(
        String n,
        int x,
        int y,
        int z,
        int xWidth,
        int yWidth,
        int zWidth,
        int ar,
        double t)
    {
        super(x, y, z, xWidth, yWidth, zWidth, n);
        ascentRate = ar;
        trajectory = t;
    }


    // ~Public Methods ........................................................
    /**
     * Returns rocket's ascent rate
     * 
     * @return ascent rate
     */
    public int getAscentRate()
    {
        return ascentRate;
    }


    /**
     * Returns rocket's trajectory
     * 
     * @return rocket's trajectory
     */
    public double getTrajectory()
    {
        return trajectory;
    }
    /**
     * sees if the input would be valid
     * 
     * @return true if the object is valid, false otherwise
     */
    public boolean isValid() {
        if (!super.isValid()) {
            return false;
        }
        return !(ascentRate<1||trajectory<1);
    }
}
