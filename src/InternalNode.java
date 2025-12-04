public class InternalNode implements BaseNode {
    // ~ Fields ................................................................
    // private BaseNode[] children = new BaseNode[8];
    private BaseNode left;
    private BaseNode right;
    public static int count;
    public static int count2;

    private int height;
    private int discriminator;

    // ~ Constructors ..........................................................
    public InternalNode(int h) {
        this.discriminator = h % 3;
        height = h;
        count = 0;
        count2 = 0;
        this.left = EmptyLeaf.getInstance();
        this.right = EmptyLeaf.getInstance();
// children = new BaseNode[8];
// for (int i = 0; i < 8; i++) {
// children[i] = EmptyLeaf.getInstance();
// }

        // EmptyLeaf.getInstance();
    }


    // ~Public Methods ........................................................
    @Override
    public BaseNode insert(
        AirObject obj,
        int x,
        int y,
        int z,
        int xW,
        int yW,
        int zW,
        int depth) {
        int mid = 0;
        this.height = depth;
        this.discriminator = depth % 3; // will be zero one two
        System.out.println(discriminator + "hehe");
        /*
         * if zero will split the current x in half
         */
        if (discriminator == 0) {
            System.out.println("des = 0");
            mid = x + xW / 2;
        }
        else if (discriminator == 1) {
            System.out.println("des = 1");
            mid = y + yW / 2;
        }
        else if (discriminator == 2) {
            System.out.println("des = 2");
            mid = z + zW / 2;
        }
        else {
            System.out.println("des = uhoh" + discriminator);
        }
// boolean spans = false;
// if (discriminator == 0) {
// spans = obj.getXOrig() < mid && (obj.getXOrig() + obj
// .getXWidth()) > mid;
// }
// else if (discriminator == 1) {
// spans = obj.getYOrig() < mid && (obj.getYOrig() + obj
// .getYWidth()) > mid;
// }
// else if (discriminator == 2) {
// spans = obj.getZOrig() < mid && (obj.getZOrig() + obj
// .getZWidth()) > mid;
// }
//
// // If it spans, store it here as a leaf (so both halves implicitly "see"
// // it")
// if (spans) {
// System.out.println("spans"+obj.toString());
// LeafNode leaf = new LeafNode();
// leaf.insert(obj, x,y,z,0,0,0,depth+1);
// LeafNode leaf2 = new LeafNode();
// leaf2.insert(obj, x,y,z,xW,yW,zW,depth+1);
// // replaces this InternalNode at this location
// }
        boolean goesLeft = false;
        boolean fitRight = false;
        if (discriminator == 0) {
            System.out.println(mid + "\nmid  " + obj.toString());
            goesLeft = obj.getXOrig() + obj.getXWidth() <= mid;
            fitRight = obj.getXOrig() >= mid;
            System.out.println(obj.getXOrig() < mid);
        }
        else if (discriminator == 1) {
            goesLeft = obj.getYOrig() + obj.getYWidth() <= mid;
            fitRight = obj.getYOrig() >= mid;
        }
        else if (discriminator == 2) {
            goesLeft = obj.getZOrig() + obj.getZWidth() <= mid;
            fitRight = obj.getYOrig() >= mid;
        }
        else {
            System.out.println("error bool");
        }
        if (goesLeft) {
            if (discriminator == 0) {
                left = left.insert(obj, x, y, z, xW / 2, yW, zW, depth + 1);
            }
            else if (discriminator == 1) {
                System.out.println("here");
                left = left.insert(obj, x, y, z, xW, yW / 2, zW, depth + 1);

            }
            else if (discriminator == 2) {
                left = left.insert(obj, x, y, z, xW, yW, zW / 2, depth + 1);
            }
            else {
                System.out.println("error last");
            }
            //System.out.println(depth + " depth");
        }
        else if (fitRight) {
            if (discriminator == 0) {
                right = right.insert(obj, mid, y, z, xW - xW / 2, yW, zW, depth
                    + 1);
            }
            else if (discriminator == 1) {
                right = right.insert(obj, x, mid, z, xW, yW - yW / 2, zW, depth
                    + 1);
            }
            else if (discriminator == 2) {
                right = right.insert(obj, x, y, mid, xW, yW, zW - zW / 2, depth
                    + 1);
            }
            else {
                System.out.println("error last");
            }
        }
        else {
            if (discriminator == 0) {
                right = right.insert(obj, mid, y, z, xW - xW / 2, yW, zW, depth
                    + 1);
                left = left.insert(obj, x, y, z, xW / 2, yW, zW, depth + 1);
            }
            else if (discriminator == 1) {
                right = right.insert(obj, x, mid, z, xW, yW - yW / 2, zW, depth
                    + 1);
                left = left.insert(obj, x, y, z, xW, yW / 2, zW, depth + 1);
            }
            else if (discriminator == 2) {
                right = right.insert(obj, x, y, mid, xW, yW, zW - zW / 2, depth
                    + 1);
                left = left.insert(obj, x, y, z, xW, yW, zW / 2, depth + 1);
            }

        }
        return this;

    }


    /**
     * determines if the objet fits in one octant or spans multiple
     * 
     * @return boolean true if it is in one octant false if it spans multiple
     */
    private boolean fitsInOctant(
        AirObject obj,
        int x,
        int y,
        int z,
        int xW,
        int yW,
        int zW) {
        int midX = x + xW / 2;
        int midY = y + yW / 2;
        int midZ = z + zW / 2;
        int objX1 = obj.getXOrig(); // origin
        int objX2 = objX1 + obj.getXWidth(); // origin + width

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
        int zW) {
        int midX = x + xW / 2;
        int midY = y + yW / 2;
        int midZ = z + zW / 2;
        int octant = 0;
        if (obj.getXOrig() >= midX) {
            octant |= 1; // Set bit 0 (right side)
        }
        if (obj.getYOrig() >= midY) {
            octant |= 2; // Set bit 1 (back side)
        }
        if (obj.getZOrig() >= midZ) {
            octant |= 4; // Set bit 2 (top side)
        }

        return octant;
    }


    @Override
    public String print(
        int x,
        int y,
        int z,
        int xW,
        int yW,
        int zW,
        int depth) {
        BinTree.addCount();
        count2++;
        String temp = "";
// return "int with " + " objects (" + x + ", " + y + ", " + z
// + ", " + xW + ", " + yW + ", " + zW + ") " + depth + "\r\n";
        String output = "I (" + x + ", " + y + ", " + z + ", " + xW + ", " + yW
            + ", " + zW + ") " + depth + "\r\n";
        if (discriminator == 0) {
            output += left.print(x, y, z, xW / 2, yW, zW, depth + 1); // to
                                                                      // reduce
                                                                      // lines
            temp = right.print(x + xW / 2, y, z, xW - xW / 2, yW, zW, depth
                + 1);
            if (right.getClass().getName() != "EmptyLeaf") {
                count++;
                output += temp;
            }
        }
        else if (discriminator == 1) {
            temp = left.print(x, y, z, xW, yW / 2, zW, depth + 1);
            if (left.getClass().getName() != "EmptyLeaf") {
                count++;
                output += temp;
            }
            temp = right.print(x, y + yW / 2, z, xW, yW - yW / 2, zW, depth
                + 1);
            if (right.getClass().getName() != "EmptyLeaf") {
                count++;
                output += temp;
            }

        }
        else if (discriminator == 2) {
            temp = left.print(x, y, z, xW, yW, zW / 2, depth + 1);
            if (left.getClass().getName() != "EmptyLeaf") {
                count++;
                output += temp;
            }
            temp = right.print(x, y, z + zW / 2, xW, yW, zW - zW / 2, depth
                + 1);
            if (right.getClass().getName() != "EmptyLeaf") {
                count++;
                output += temp;
            }
        }
        //System.out.println(count + "num1 " + count2);
        BinTree.setCount(count2 + count);

        return output;
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
//        this.height = depth;
//        this.discriminator = depth % 3;
//        System.out.println("delete "+obj.toString()+" descrim"+discriminator);
//        System.out.println(x+" "+y+" "+z+"   "+xW+" "+yW+" "+zW+"\n");
//        
//        int halfX = xW / 2;
//        int halfY = yW / 2;
//        int halfZ = zW / 2;
//
//        int leftX = x, leftY = y, leftZ = z;
//        int leftXW = xW, leftYW = yW, leftZW = zW;
//        int rightX = x, rightY = y, rightZ = z;
//        int rightXW = xW, rightYW = yW, rightZW = zW;
//        if(discriminator==0) {
////            leftXW = halfX;
////            rightX = x + halfX;
////            rightXW = xW - halfX;
//            if(obj.getXOrig()<halfX) {
//                left = left.delete(obj, leftX, leftY, leftZ, leftXW, leftYW, leftZW,depth+1);
//            }
//            else if(obj.getXOrig()+obj.getXWidth()>halfX) {
//                left = left.delete(obj, leftX, leftY, leftZ, leftXW, leftYW, leftZW,depth+1);
//                right = right.delete(obj, rightX, rightY, rightZ, rightXW, rightYW, rightZW,depth+1);
//            }
//        }
//        else if(discriminator==1) {
////            leftYW = halfY;
////            rightY = y + halfY;
////            rightYW = yW - halfY;
//            if(obj.getYOrig()<halfY) {
//                left = left.delete(obj, leftX, leftY, leftZ, leftXW, leftYW, leftZW,depth+1);
//            }
//            else if(obj.getYOrig()+obj.getYWidth()>halfY) {
//                left = left.delete(obj, leftX, leftY, leftZ, leftXW, leftYW, leftZW,depth+1);
//                right = right.delete(obj, rightX, rightY, rightZ, rightXW, rightYW, rightZW,depth+1);
//            }
//        }
//        else if (discriminator==2) {
////            leftZW = halfZ;
////            rightZ = z + halfZ;
////            rightZW = zW - halfZ;
//            if(obj.getZOrig()<halfZ) {
//                left = left.delete(obj, leftX, leftY, leftZ, leftXW, leftYW, leftZW,depth+1);
//            }
//            else if(obj.getZOrig()+obj.getZWidth()>halfZ) {
//                left = left.delete(obj, leftX, leftY, leftZ, leftXW, leftYW, leftZW,depth+1);
//                right = right.delete(obj, rightX, rightY, rightZ, rightXW, rightYW, rightZW,depth+1);
//            }
//        }
//        //left = left.delete(obj, leftX, leftY, leftZ, leftXW, leftYW, leftZW,depth+1);
//        //right = right.delete(obj, rightX, rightY, rightZ, rightXW, rightYW, rightZW,depth+1);
//        boolean leftEmpty = left instanceof EmptyLeaf;
//        boolean rightEmpty = right instanceof EmptyLeaf;
//        if (leftEmpty && rightEmpty) {
//            return EmptyLeaf.getInstance();
//        }
//        boolean leftLeaf = left instanceof LeafNode;
//        boolean rightLeaf = right instanceof LeafNode;
//
//        if (leftLeaf && rightLeaf) {
//            LeafNode l = (LeafNode)left;
//            LeafNode r = (LeafNode)right;
//
//            int total = l.getCount() + r.getCount();
//
//            if (total <= 3) {
//                // collapse internal node → leaf node
//                return mergeTwoLeaves(l, r);
//            }
//        }
        
        
        this.height = depth;
        this.discriminator = depth % 3;

        int mid = 0;

        if (discriminator == 0) mid = x + xW/2;
        else if (discriminator == 1) mid = y + yW/2;
        else mid = z + zW/2;

        // --- Determine delete routing (same logic as insert) ------------------
        boolean goesLeft = false;
        boolean goesRight = false;

        if (discriminator == 0) {
            goesLeft  = obj.getXOrig() + obj.getXWidth() <= mid;
            goesRight = obj.getXOrig() >= mid;
        }
        else if (discriminator == 1) {
            goesLeft  = obj.getYOrig() + obj.getYWidth() <= mid;
            goesRight = obj.getYOrig() >= mid;
        }
        else {
            goesLeft  = obj.getZOrig() + obj.getZWidth() <= mid;
            goesRight = obj.getZOrig() >= mid;
        }

        // --- Recurse properly only where needed ------------------------------
        if (goesLeft) {
            left = left.delete(obj, x, y, z,
                               xW/2, yW, zW, depth+1);
        }
        else if (goesRight) {
            right = right.delete(obj,
                                 (discriminator == 0 ? mid : x),
                                 (discriminator == 1 ? mid : y),
                                 (discriminator == 2 ? mid : z),
                                 (discriminator == 0 ? xW - xW/2 : xW),
                                 (discriminator == 1 ? yW - yW/2 : yW),
                                 (discriminator == 2 ? zW - zW/2 : zW),
                                 depth+1);
        }
        else {
            // SPANNING → delete from both
            left = left.delete(obj, x, y, z,
                               xW/2, yW, zW, depth+1);

            right = right.delete(obj,
                                 (discriminator == 0 ? mid : x),
                                 (discriminator == 1 ? mid : y),
                                 (discriminator == 2 ? mid : z),
                                 (discriminator == 0 ? xW - xW/2 : xW),
                                 (discriminator == 1 ? yW - yW/2 : yW),
                                 (discriminator == 2 ? zW - zW/2 : zW),
                                 depth+1);
        }
        boolean leftEmpty = left instanceof EmptyLeaf;
        boolean rightEmpty = right instanceof EmptyLeaf;

        if (leftEmpty && rightEmpty)
            return EmptyLeaf.getInstance();

        if (left instanceof LeafNode && right instanceof LeafNode) {
            LeafNode L = (LeafNode)left;
            LeafNode R = (LeafNode)right;
            int total = L.getCount() + R.getCount();
            if (total <= 3)
                return mergeTwoLeaves(L, R);
        }
        if(rightEmpty&&left instanceof LeafNode) {
            LeafNode l = (LeafNode)left;
            if (l.getCount() <= 3) {
                // collapse directly into leaf
                return copyLeaf(l);
            }
        if (right instanceof LeafNode && leftEmpty) {
                LeafNode r = (LeafNode)right;
                if (r.getCount() <= 3) {
                    return copyLeaf(r);
                }
        }
        }

        
        
        return this;
    }
    private LeafNode mergeTwoLeaves(LeafNode leftLeaf, LeafNode rightLeaf) {
            LeafNode merged = new LeafNode();
            for (AirObject obj : leftLeaf.getObjects()) {
                if (obj != null) merged.insertDirect(obj); // insertDirect adds WITHOUT splitting
            }
            for (AirObject obj : rightLeaf.getObjects()) {
                if (obj != null) merged.insertDirect(obj);
            }
            return merged;
        }
        

    private LeafNode copyLeaf(LeafNode src) {
        LeafNode newLeaf = new LeafNode();
        for (AirObject o : src.getObjects()) {
            if (o != null) newLeaf.insertDirect(o);
        }
        return newLeaf;
    }
}
