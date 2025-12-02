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
    /**
     * Adds object to the node only if there is space
     * 
     * @param obj
     *            Object being added
     */
    public void addObject(AirObject obj)
    {
        objects[count] = obj;
        count++;
    }

    /**
     * Checks if two boxes have a non-empty intersection box
     */
    private boolean twoIntersect(AirObject box1, AirObject box2)
    {
        int b1X = box1.getXOrig();
        int b1Y = box1.getYOrig();
        int b1Z = box1.getZOrig();
        int b1XW = b1X + box1.getXWidth();
        int b1YW = b1Y + box1.getYWidth();
        int b1ZW = b1Z + box1.getZWidth();

        int b2X = box2.getXOrig();
        int b2Y = box2.getYOrig();
        int b2Z = box2.getZOrig();
        int b2XW = b2X + box2.getXWidth();
        int b2YW = b2Y + box2.getYWidth();
        int b2ZW = b2Z + box2.getZWidth();

        return (b1X < b2XW && b2X < b1XW) && (b1Y < b2YW && b2Y < b1YW)
            && (b1Z < b2ZW && b2Z < b1ZW);

    }
    
    /**
     * Checks if all the boxes have a non-empty intersection box. 
     */
    private boolean allBoxesIntersect(AirObject[] boxes)
    {
        for (int i = 0; i < 4; i++)
        {
            for (int j = i + 1; j < 4; j++)
            {
                if (!twoIntersect(boxes[i], boxes[j]))
                {
                    return false;
                }
            }
        }
        return true;
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
        if (count < 3)
        {
            addObject(obj);
            return this;
        }
        
        AirObject[] temp = new AirObject[count + 1];
        for (int i = 0; i < count; i++)
        {
            temp[i] = objects[i];
        }
        temp[count] = obj;
        
        if (count == 3)
        {
            if (allBoxesIntersect(temp))
            {
                addObject(obj);
                return this;
            }
        }
        
        InternalNode internal = new InternalNode();
        for (int i = 0; i < temp.length; i++)
        {
            internal.insert(temp[i], x, y, z, xW, yW, zW, depth);
        }
        return internal;
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
