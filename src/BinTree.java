public class BinTree
{
    // ~ Fields ................................................................

    private BaseNode root;
    private final int worldSize;

    // ~ Constructors ..........................................................

    public BinTree(int worldSize)
    {
        this.worldSize = worldSize;
        root = EmptyLeaf.getInstance();
    }

    // ~Public Methods ........................................................


    public void clear()
    {
        root = EmptyLeaf.getInstance();
    }


    public void insert(AirObject obj)
    {
        if(root==null) {
            System.out.println("re");
        }
        root = root.insert(obj, 0, 0, 0, worldSize, worldSize, worldSize, 0);
    }
    
    public String printTree()
    {
        if(root==null) {
            System.out.println("ldre");
        }
        String output = root.print(0, 0, 0, worldSize, worldSize, worldSize, 0); 
        return output + 1 + " BinTree nodes printed\r\n";
    }
}
