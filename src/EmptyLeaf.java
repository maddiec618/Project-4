// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 * 
 * @author madelync05 & ellae
 * @version Dec 1, 2025
 */
public class EmptyLeaf
    implements BaseNode
{
    // ~ Fields ................................................................
    private static final EmptyLeaf INSTANCE = new EmptyLeaf();

    // ~ Constructors ..........................................................
    private EmptyLeaf()
    {
    }


    public static EmptyLeaf getInstance()
    {
        return INSTANCE;
    }

    // ~Public Methods ........................................................


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
        LeafNode leaf = new LeafNode();
        leaf.addObject(obj);
        return leaf;
    }


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
//        return "E (" + x + ", " + y + ", " + z + ", " + xW + ", " + yW + ", "
//            + zW + ") " + depth + " \r\n";
        return "";
    }


    @Override
    public BaseNode delete(
        AirObject obj,
        int x,
        int y,
        int z,
        int xW,
        int yW,
        int zW,
        int depth) {
        // TODO Auto-generated method stub
        return null;
    }
}
