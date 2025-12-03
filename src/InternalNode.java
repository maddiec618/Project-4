public class InternalNode implements BaseNode
{
    //~ Fields ................................................................
    private BaseNode[] children = new BaseNode[8];
    //~ Constructors ..........................................................
    public InternalNode()
    {
        children = new BaseNode[8];
        for (int i = 0; i < 8; i++) {
            children[i] = EmptyLeaf.getInstance();
        }

        //EmptyLeaf.getInstance();
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
        if (!fitsInOctant(obj, x, y, z, xW, yW, zW)) {
            // Object is too big - it crosses octant boundaries
            // Convert this InternalNode into a LeafNode that stores the object
            LeafNode leaf = new LeafNode();
            leaf.addObject(obj);
            return leaf;
        }
        
        int octant = getOctant(obj, x, y, z, xW, yW, zW);
        System.out.println(obj.toString()+"  =  "+octant);
        int halfXW = xW / 2;
        int halfYW = yW / 2;
        int halfZW = zW / 2;
        int childX = x + ((octant & 1) * halfXW);
        int childY = y + ((octant & 2) >> 1) * halfYW;
        int childZ = z + ((octant & 4) >> 2) * halfZW;
        
        // STEP 4: Recursively insert into appropriate child
        children[octant] = children[octant].insert(
            obj, childX, childY, childZ, halfXW, halfYW, halfZW, depth + 1);
//        int childX=x;
//        int childY=y;
//        int childZ=z;
//        if ((octant & 1) != 0) { 
//            childX = x + halfXW;
//        }
//        if ((octant & 2) != 0) {
//            childY = y + halfYW;
//        }
//        if ((octant & 4) != 0) {
//            childZ = z + halfZW;
//        }
//        children[octant] = children[octant].insert(
//            obj, childX, childY, childZ, halfXW, halfYW, halfZW, depth + 1);
        return this;
    }
    /**
     * determines if the objet fits in one octant or spans multiple
     * 
     * @return boolean true if it is in one octant false if it spans multiple
     */
    private boolean fitsInOctant(AirObject obj, int x, int y, int z, int xW, int yW, int zW) {
        int midX = x + xW / 2;
        int midY = y + yW / 2;
        int midZ = z + zW / 2;
        int objX1 = obj.getXOrig();           // origin
        int objX2 = objX1 + obj.getXWidth();    // origin + width
        
        int objY1 = obj.getYOrig();
        int objY2 = objY1 + obj.getYWidth(); 
        
        int objZ1 = obj.getZOrig();
        int objZ2 = objZ1 + obj.getZWidth(); 
        // Check if object's bounding box fits entirely in one octant
        boolean crossesX = (objX1 < midX && objX2 > midX);
        boolean crossesY = (objY1 < midY && objY2 > midY);
        boolean crossesZ = (objZ1 < midZ && objZ2 > midZ);
        return !(crossesX || crossesY || crossesZ);
    }
    private int getOctant(
        AirObject obj,
        int x,
        int y,
        int z,
        int xW,
        int yW,
        int zW)
    {
        int midX = x + xW / 2;
        int midY = y + yW / 2;
        int midZ = z + zW / 2;
        int octant = 0;
        if (obj.getXOrig() >= midX) {
            octant |= 1;  // Set bit 0 (right side)
        }
        if (obj.getYOrig() >= midY) {
            octant |= 2;  // Set bit 1 (back side)
        }
        if (obj.getZOrig() >= midZ) {
            octant |= 4;  // Set bit 2 (top side)
        }
        
        return octant;
    }
   
    
    @Override
    public String print(int x, int y, int z, int xW, int yW, int zW, int depth)
    {
//        return "int with " + " objects (" + x + ", " + y + ", " + z
//            + ", " + xW + ", " + yW + ", " + zW + ") " + depth + "\r\n";
        String output = "I (" + x + ", " + y + ", " + z + ", " 
            + xW + ", " + yW + ", " + zW + ") " + depth + "\r\n";
        
        // Calculate dimensions for children
        int halfXW = xW / 2;
        int halfYW = yW / 2;
        int halfZW = zW / 2;
        
        // Recursively print all 8 children
        for (int i = 0; i < 8; i++) {
            // Calculate child's origin
            int childX = x;
            int childY = y;
            int childZ = z;
            
            if ((i & 1) != 0) {
                childX = x + halfXW;
            }
            if ((i & 2) != 0) {
                childY = y + halfYW;
            }
            if ((i & 4) != 0) {
                childZ = z + halfZW;
            }
            
            // Print the child
           
            output += children[i].print(
                childX, childY, childZ, halfXW, halfYW, halfZW, depth + 1);
        }
        
        return output;
    }

}
