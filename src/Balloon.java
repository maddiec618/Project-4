/**
 * Creates a balloon object that extends the AirObject class and also contains
 * the balloon's type and ascent rate.
 * 
 * @author madelync05 and ellae
 * @version Fall 2025
 */
public class Balloon
    extends AirObject
{
    // ~ Fields ................................................................
    private String type;
    private int ascentRate;

    // ~ Constructors ..........................................................
    /**
     * Create a new Balloon object.
     * 
     * @param n
     *            name of the balloon
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
     * @param t
     *            type name
     * @param ar
     *            ascent rate
     */
    public Balloon(
        String n,
        int x,
        int y,
        int z,
        int xWidth,
        int yWidth,
        int zWidth,
        String t,
        int ar)
    {
        super(x, y, z, xWidth, yWidth, zWidth, n);
        type = t;
        ascentRate = ar;
    }


    // ~Public Methods ........................................................
    /**
     * Returns balloon type.
     * 
     * @return balloon type
     */
    public String getType()
    {
        return type;
    }


    /**
     * Returns ballon's ascent rate
     * 
     * @return ascent rate
     */
    public int getAscentRate()
    {
        return ascentRate;
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
        return !(type==null||ascentRate<1);
    }
    /**
     * to string method for the object
     * @return a string representation of the object
     */
    public String toString() {
        return "Balloon "+ getName() +" "+ getXOrig()+" "+getYOrig() +" "+getZOrig()+
            " "+getXWidth()+ " "+ getYWidth()+" "+getZWidth()+ " "+ type+" "+ascentRate
            + "\n"; 
        
    }
}
