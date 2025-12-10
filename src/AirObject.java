/**
 * Main class for all air objects inclusing airplanes, balloons, birds, drones
 * and rockets.
 * 
 * @author madelync05 and ellae
 * @version Fall 2025
 */
public class AirObject implements Comparable<AirObject> {
    // ~ Fields ................................................................

    private int xOrig;
    private int yOrig;
    private int zOrig;
    private int xWidth;
    private int yWidth;
    private int zWidth;
    private String name;

    // ~ Constructors ..........................................................
    /**
     * Create a new AirObject object.
     * 
     * @param xO
     *            x-cord for origin
     * @param yO
     *            y-cord for origin
     * @param zO
     *            z-cord for origin
     * @param xW
     *            x-cord for width
     * @param yW
     *            y-cord for width
     * @param zW
     *            z-cord for width
     * @param n
     *            object name
     */
    public AirObject(int xO, int yO, int zO, int xW, int yW, int zW, String n) {
        xOrig = xO;
        yOrig = yO;
        zOrig = zO;
        xWidth = xW;
        yWidth = yW;
        zWidth = zW;
        name = n;
    }


    // ~Public Methods ........................................................
    /**
     * Returns the x-cord for for origin
     * 
     * @return x-cord for origin
     */
    public int getXOrig() {
        return xOrig;
    }


    /**
     * Returns the y-cord for for origin
     * 
     * @return y-cord for origin
     */
    public int getYOrig() {
        return yOrig;
    }


    /**
     * Returns the z-cord for for origin
     * 
     * @return z-cord for origin
     */
    public int getZOrig() {
        return zOrig;
    }


    /**
     * Returns the x-cord for for width
     * 
     * @return x-cord for width
     */
    public int getXWidth() {
        return xWidth;
    }


    /**
     * Returns the y-cord for for width
     * 
     * @return y-cord for width
     */
    public int getYWidth() {
        return yWidth;
    }


    /**
     * Returns the z-cord for for width
     * 
     * @return z-cord for width
     */
    public int getZWidth() {
        return zWidth;
    }


    /**
     * Returns name of the air object
     * 
     * @return object name
     */
    public String getName() {
        return name;
    }


    /**
     * Compares the names of two air objects
     * 
     * @param other the other air object
     * @return 0 if names are equal, negative or positive value if names are
     *         different
     */
    public int compareTo(AirObject other) {
        return this.name.compareTo(other.getName());
    }


    /**
     * sees if the input would be valid
     * 
     * @return true if the object is valid, false otherwise
     */
    public boolean isValid() {
        return !(xOrig < 0 || xOrig > 1024 || yOrig < 0 || yOrig > 1023
            || zOrig < 0 || zOrig > 1023 || getXWidth() < 1 || getXWidth()
                + xOrig > 1025 || getYWidth() < 1 || getYWidth() + yOrig > 1025
            || getZWidth() < 1 || getZWidth() + zOrig > 1025 || name == null);
    }
}
