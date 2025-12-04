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
        if(root==null) {
            System.out.println("re");
        }
        //count++;
        root = root.insert(obj, 0, 0, 0, worldSize, worldSize, worldSize, 0);
    }
    
    public String printTree()
    {
        if(root==null) {
            System.out.println("ldre");
        }
        c2=0;
        String output = root.print(0, 0, 0, worldSize, worldSize, worldSize, 0); 
        if(count==0) {
            output="e 0 0 0 1024 1024 1024 0\n1 bintree nodes printed";
            return output;
        }
       
        return output+c2 + " BinTree nodes printed\r\n";
    }
}
