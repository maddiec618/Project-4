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
    private int height;
    private int count;

    // ~ Constructors ..........................................................
    public LeafNode()
    {
        objects = new AirObject[8];
        count = 0;
        height = 0;
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
        int n = 0;
        for (int i = 0; i < boxes.length; i++)
        {
            if (boxes[i] != null)
            {
                n++;
            }
        }

        AirObject[] nonNull = new AirObject[n];
        int idx = 0;
        for (int i = 0; i < boxes.length; i++)
        {
            if (boxes[i] != null)
            {
                nonNull[idx++] = boxes[i];
            }
        }

        for (int i = 0; i < n; i++)
        {
            for (int j = i + 1; j < n; j++)
            {
                if (!twoIntersect(nonNull[i], nonNull[j]))
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
            sortNode();
            return this;
        }

        AirObject[] temp = new AirObject[count + 1];
        for (int i = 0; i < count; i++)
        {
            temp[i] = objects[i];
        }
        temp[count] = obj;

        if (xW <= 1 && yW <= 1 && zW <= 1)
        {
            addObject(obj);
            sortNode();
            return this;
        }

        if (allBoxesIntersect(temp))
        {
            addObject(obj);
            sortNode();
            return this;
        }

        InternalNode internal = new InternalNode(depth);
        for (int i = 0; i < temp.length; i++)
        {
            internal.insert(temp[i], x, y, z, xW, yW, zW, depth);
        }
        return internal;
    }


    public void sortNode()
    {
        int n = 0;
        for (AirObject obj : objects)
        {
            if (obj != null)
                n++;
        }
        for (int i = 0; i < n - 1; i++)
        {
            for (int j = 0; j < n - 1 - i; j++)
            {
                if (objects[j].compareTo(objects[j + 1]) > 0)
                {
                    AirObject temp = objects[j];
                    objects[j] = objects[j + 1];
                    objects[j + 1] = temp;
                }
            }
        }
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
    @Override
    public String print(int x, int y, int z, int xW, int yW, int zW, int depth)
    {
        String output = "";
        BinTree.addCount();
        output += "Leaf with " + count + " objects (" + x + ", " + y + ", " + z
            + ", " + xW + ", " + yW + ", " + zW + ") " + depth + "\r\n";
        for (AirObject air : objects)
        {
            if (air != null)
                output += air.toString();
        }
        return output;

    }


    /**
     * gets teh count of the leaf node
     * 
     * @return in representing the number of objects in the node
     */
    public int getCount()
    {
        return count;
    }


    /**
     * gets tha array of objects
     * 
     * @return the objects array
     */
    public AirObject[] getObjects()
    {
        return objects;
    }


    public void insertDirect(AirObject obj)
    {
        addObject(obj);
        sortNode();
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
        int depth)
    {
        boolean removed = removeObject(obj);

        if (!removed)
        {
            return this;
        }

        sortNode();

        if (count == 0)
        {
            return EmptyLeaf.getInstance();
        }
        return this;
    }


    public boolean removeObject(AirObject obj)
    {
        if (obj == null)
        {
            return false;
        }
        String target = obj.getName();
        for (int i = 0; i < count; i++)
        {
            AirObject cur = objects[i];
            if (cur != null && cur.getName().equals(target))
            {
                for (int j = i; j < count - 1; j++)
                {
                    objects[j] = objects[j + 1];
                }
                objects[count - 1] = null;
                count--;
                return true;
            }
        }
        return false;
    }


    public void collision(
        StringBuilder output,
        int x,
        int y,
        int z,
        int xW,
        int yW,
        int zW,
        int depth)
    {
        output.append("In leaf node (").append(x).append(", ").append(y)
            .append(", ").append(z).append(", ").append(xW).append(", ")
            .append(yW).append(", ").append(zW).append(") ").append(depth)
            .append("\r\n");

        for (int i = 0; i < count; i++)
        {
            AirObject a = objects[i];
            for (int j = i + 1; j < count; j++)
            {
                AirObject b = objects[j];
                if (twoIntersect(a, b))
                {
                    output.append("(").append(a.toString().trim())
                        .append(" and ").append(b.toString().trim())
                        .append(")\r\n");
                }
            }
        }
    }
    
    @Override
    public void intersect(
        StringBuilder output,
        int nodeX, int nodeY, int nodeZ,
        int nodeXW, int nodeYW, int nodeZW,
        int qx, int qy, int qz, int qxW, int qyW, int qzW,
        int depth)
    {
        BinTree.count++;

        output.append("in leaf node ")
              .append(nodeX).append(" ")
              .append(nodeY).append(" ")
              .append(nodeZ).append(" ")
              .append(nodeXW).append(" ")
              .append(nodeYW).append(" ")
              .append(nodeZW).append(" ")
              .append(depth).append("\r\n");

        for (int i = 0; i < count; i++) {
            AirObject obj = objects[i];

            if (!boxesIntersect(
                    obj.getXOrig(), obj.getYOrig(), obj.getZOrig(),
                    obj.getXWidth(), obj.getYWidth(), obj.getZWidth(),
                    qx, qy, qz, qxW, qyW, qzW)) {
                continue;
            }

            int ix = Math.max(obj.getXOrig(), qx);
            int iy = Math.max(obj.getYOrig(), qy);
            int iz = Math.max(obj.getZOrig(), qz);

            if (ix >= nodeX && ix < nodeX + nodeXW &&
                iy >= nodeY && iy < nodeY + nodeYW &&
                iz >= nodeZ && iz < nodeZ + nodeZW) {
                output.append(obj.toString());
            }
        }
    }
    
    private boolean boxesIntersect(
        int ax, int ay, int az, int axW, int ayW, int azW,
        int bx, int by, int bz, int bxW, int byW, int bzW)
    {
        int ax2 = ax + axW;
        int ay2 = ay + ayW;
        int az2 = az + azW;

        int bx2 = bx + bxW;
        int by2 = by + byW;
        int bz2 = bz + bzW;

        return (ax < bx2 && bx < ax2)
            && (ay < by2 && by < ay2)
            && (az < bz2 && bz < az2);
    }
}
