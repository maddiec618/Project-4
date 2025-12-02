/**
 * Creates a airplane object that extends the AirObject class and also contains
 * carrier name, flight number, and number of engines.
 * 
 * @author madelync05 and ellae
 * @version Fall 2025
 */
public class AirPlane
    extends AirObject
{
    // ~ Fields ................................................................
    private String carrier;
    private int flightNum;
    private int numEngines;

    // ~ Constructors ..........................................................
    /**
     * Create a new AirPlane object.
     * 
     * @param n
     *            name of the airplane
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
     * @param c
     *            carrier name
     * @param flight
     *            flight number
     * @param engines
     *            number of engines
     */
    public AirPlane(
        String n,
        int x,
        int y,
        int z,
        int xWidth,
        int yWidth,
        int zWidth,
        String c,
        int flight,
        int engines)
    {
        super(x, y, z, xWidth, yWidth, zWidth, n);
        carrier = c;
        flightNum = flight;
        numEngines = engines;
    }


    // ~Public Methods ........................................................
    /**
     * Returns the name of the carrier
     * 
     * @return carrier name
     */
    public String getCarrier()
    {
        return carrier;
    }


    /**
     * Returns the flight number
     * 
     * @return flight number
     */
    public int getFlightNumber()
    {
        return flightNum;
    }


    /**
     * Returns the number of engines
     * 
     * @return number of engines
     */
    public int getEngines()
    {
        return numEngines;
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
            return !(carrier==null||flightNum<1||numEngines<1);
    }
    /**
     * to string method for the object
     * @return a string representation of the object
     */
    public String toString() {
        if (getName()==null) {
            return null;
        }
        return "Airplane "+ getName() +" "+ getXOrig()+" "+getYOrig() +" "+getZOrig()+
            " "+getXWidth()+ " "+ getYWidth()+" "+getZWidth()+ " "+ carrier+" "+flightNum
            +" "+ numEngines+ "\n"; 
        
    }
}
