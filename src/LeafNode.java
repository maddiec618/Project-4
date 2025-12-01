// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 * 
 * @author madelync05 & ellae
 * @version Dec 1, 2025
 */
public class LeafNode
    implements BaseNode
{
    // ~ Fields ................................................................
    private AirObject[] objects;
    private int count;

    // ~ Constructors ..........................................................
    public LeafNode()
    {
        objects = new AirObject[4];
        count = 0;
    }


    // ~Public Methods ........................................................
    public void addObject(AirObject obj)
    {
        if (count < 4)
        {
            objects[count] = obj;
            count++;
        }
    }


    /**
     * Inserts a new node object into the subtree.
     * 
     * @param obj
     *            Object being inserted
     * @param x
     *            x-cord for origin
     * @param y
     *            y-cord for origin
     * @param z
     *            z-cord for origin
     * @param xW
     *            x-cord for width
     * @param yW
     *            y-cord for width
     * @param zW
     *            z-cord for width
     * @param depth
     *            depth of node
     */
    public BaseNode insert(
        AirObject obj,
        int x,
        int y,
        int z,
        int xW,
        int yW,
        int zW,
        int depth)
    {
        addObject(obj);
        return this;
    }


    // ----------------------------------------------------------
    /**
     * Prints node.
     * 
     * @param x
     *            x-cord for origin
     * @param y
     *            y-cord for origin
     * @param z
     *            z-cord for origin
     * @param xW
     *            x-cord for width
     * @param yW
     *            y-cord for width
     * @param zW
     *            z-cord for width
     * @param depth
     *            depth of node
     * @return String output
     */
    public String print(int x, int y, int z, int xW, int yW, int zW, int depth)
    {
        return "Leaf with " + count + " objects (" + x + ", " + y + ", " + z
            + ", " + xW + ", " + yW + ", " + zW + ") " + depth + "\r\n";
    }
}
