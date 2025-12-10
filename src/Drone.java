/**
 * Creates a Drone object that extends the AirObject class and also contains
 * the drone's brand and number of engines.
 * 
 * @author madelync05 and ellae
 * @version Fall 2025
 */
public class Drone extends AirObject {
    // ~ Fields ................................................................
    private String brand;
    private int numEngines;

    // ~ Constructors ..........................................................
    /**
     * Create a new Drone object.
     * 
     * @param n
     *            name of the drone
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
     * @param b
     *            brand name
     * @param engines
     *            number of engines
     */
    public Drone(
        String n,
        int x,
        int y,
        int z,
        int xWidth,
        int yWidth,
        int zWidth,
        String b,
        int engines) {
        super(x, y, z, xWidth, yWidth, zWidth, n);
        brand = b;
        numEngines = engines;
    }


    // ~Public Methods ........................................................
    /**
     * Returns the name of the brand
     * 
     * @return brand name
     */
    public String getBrand() {
        return brand;
    }


    /**
     * Returns the number of engines
     * 
     * @return number of engines
     */
    public int getEngines() {
        return numEngines;
    }


    /**
     * sees if the input would be valid
     * 
     * @return true if the object is valid, false otherwise
     */
    public boolean isValid() {
// if (!super.isValid()) {
// return false;
// }
        return !(brand == null || numEngines < 1);
    }


    /**
     * to string method for the object
     * 
     * @return a string representation of the object
     */
    public String toString() {
// if (getName()==null) {
// return null;
// }
        return "Drone " + getName() + " " + getXOrig() + " " + getYOrig() + " "
            + getZOrig() + " " + getXWidth() + " " + getYWidth() + " "
            + getZWidth() + " " + brand + " " + numEngines + "\n";

    }
}
