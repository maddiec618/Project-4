public class BinTree
{
    // ~ Fields ................................................................

    private BaseNode root;
    private final int worldSize;
    public static int count;
    public static int c2;

    // ~ Constructors ..........................................................

    public BinTree(int worldSize)
    {
        this.worldSize = worldSize;
        root = EmptyLeaf.getInstance();
        count=0;
        c2=0;
    }

    // ~Public Methods ........................................................

public static void addCount() {
    c2++;
}
    public static void setCount(int s) {
        count = s;
    }
    public void clear()
    {
        root = EmptyLeaf.getInstance();
    }
    
    
    public void delete(AirObject obj) {
          root = root.delete(obj, 0, 0, 0, worldSize, worldSize, worldSize,0);
//        BaseNode newRoot = root.delete(obj, 0, 0, 0, worldSize, worldSize, worldSize);
//        boolean deleted = newRoot != root; // if tree changed, something was deleted
//        root = newRoot;
//        return deleted;
    }
    
    public void insert(AirObject obj)
    {
        //count++;
        root = root.insert(obj, 0, 0, 0, worldSize, worldSize, worldSize, 0);
    }
    
    public String printTree()
    {
        c2 = 0;

        if (root instanceof EmptyLeaf) {
            c2 = 1;
            return "E (0, 0, 0, " + worldSize + ", " + worldSize + ", " + worldSize + ") 0\r\n"
                 + "1 Bintree nodes printed\r\n";
        }

        String output = root.print(0, 0, 0, worldSize, worldSize, worldSize, 0);
        return output + c2 + " Bintree nodes printed\r\n";
    }
    
    public String collision() {
        StringBuilder collisions = new StringBuilder();
        collisions.append("The following collisions exist in the database:\r\n");

        if (root instanceof EmptyLeaf) {
            return collisions.toString();
        }

        root.collision(collisions, 0, 0, 0, worldSize, worldSize, worldSize, 0);
        return collisions.toString();
    }
}
