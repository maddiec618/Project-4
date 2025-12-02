public class InternalNode implements BaseNode
{
    //~ Fields ................................................................
    private BaseNode[] children = new BaseNode[8];
    //~ Constructors ..........................................................
    public InternalNode()
    {
        children = new BaseNode[8];
        EmptyLeaf.getInstance();
    }
    //~Public  Methods ........................................................
    @Override
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
     // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public String print(int x, int y, int z, int xW, int yW, int zW, int depth)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
