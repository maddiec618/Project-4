/**
 * Creates a bird object that extends the AirObject class and also contains
 * the bird's type and number.
 * 
 * @author madelync05 and ellae
 * @version Fall 2025
 */
public class Bird
    extends AirObject
{
    // ~ Fields ................................................................
    private String type;
    private int number;

    // ~ Constructors ..........................................................
    /**
     * Create a new Bird object.
     * 
     * @param n
     *            name of the bird
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
     * @param num
     *            bird number
     */
    public Bird(
        String n,
        int x,
        int y,
        int z,
        int xWidth,
        int yWidth,
        int zWidth,
        String t,
        int num)
    {
        super(x, y, z, xWidth, yWidth, zWidth, n);
        type = t;
        number = num;
    }


    // ~Public Methods ........................................................
    /**
     * Returns bird type.
     * 
     * @return bird type
     */
    public String getType()
    {
        return type;
    }


    /**
     * Returns bird's number.
     * 
     * @return bird's number
     */
    public int getNumber()
    {
        return number;
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
        return !(type==null||number<1);
    }
    /**
     * to string method for the object
     * @return a string representation of the object
     */
    public String toString() {
        if (getName()==null) {
            return null;
        }
        return "Bird "+ getName() +" "+ getXorig()+" "+getYorig() +" "+getZorig()+
            " "+getXwidth()+ " "+ getYwidth()+" "+getZwidth()+ " "+ type+" "+number
           + "\n"; 
        
    }
}
