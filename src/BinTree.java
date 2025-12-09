public class BinTree
{
    // ~ Fields ................................................................

    private BaseNode root;
    private final int worldSize;
    /**
     * First count
     */
    public static int count;
    /**
     * Second count
     */
    public static int c2;

    // ~ Constructors ..........................................................
    /**
     * Create a new BinTree object.
     * 
     * @param worldSize
     */
    public BinTree(int worldSize)
    {
        this.worldSize = worldSize;
        root = EmptyLeaf.getInstance();
        count = 0;
        c2 = 0;
    }

    // ~Public Methods ........................................................


    public static void addCount()
    {
        c2++;
    }


    public static void setCount(int s)
    {
        count = s;
    }


    public void clear()
    {
        root = EmptyLeaf.getInstance();
    }


    public void delete(AirObject obj)
    {
        root = root.delete(obj, 0, 0, 0, worldSize, worldSize, worldSize, 0);
    }


    public void insert(AirObject obj)
    {
        root = root.insert(obj, 0, 0, 0, worldSize, worldSize, worldSize, 0);
    }


    public String printTree()
    {
        c2 = 0;

        if (root instanceof EmptyLeaf)
        {
            c2 = 1;
            return "E (0, 0, 0, " + worldSize + ", " + worldSize + ", "
                + worldSize + ") 0\r\n" + "1 Bintree nodes printed\r\n";
        }

        String output = root.print(0, 0, 0, worldSize, worldSize, worldSize, 0);
        return output + c2 + " Bintree nodes printed\r\n";
    }


    public String collision()
    {
        StringBuilder collisions = new StringBuilder();
        collisions
            .append("The following collisions exist in the database:\r\n");

        if (root instanceof EmptyLeaf)
        {
            return collisions.toString();
        }

        root.collision(collisions, 0, 0, 0, worldSize, worldSize, worldSize, 0);
        return collisions.toString();
    }


    public String intersect(int x, int y, int z, int xW, int yW, int zW)
    {
        StringBuilder out = new StringBuilder();
        out.append("The following objects intersect (").append(x).append(", ")
            .append(y).append(", ").append(z).append(", ").append(xW)
            .append(", ").append(yW).append(", ").append(zW).append(")\r\n");

        c2 = 0;

        root.intersect(
            out,
            0,
            0,
            0,
            worldSize,
            worldSize,
            worldSize,
            x,
            y,
            z,
            xW,
            yW,
            zW,
            0);

        out.append(c2).append(" nodes were visited in the bintree\r\n");
        return out.toString();
    }
    
    public static boolean boxesIntersect(
        int ax, int ay, int az, int axW, int ayW, int azW,
        int bx, int by, int bz, int bxW, int byW, int bzW)
    {
        int ax2 = ax + axW;
        int ay2 = ay + ayW;
        int az2 = az + azW;

        int bx2 = bx + bxW;
        int by2 = by + byW;
        int bz2 = bz + bzW;

        // Strict < so touching faces do NOT count as intersection
        return (ax < bx2 && bx < ax2)
            && (ay < by2 && by < ay2)
            && (az < bz2 && bz < az2);
    }

}
