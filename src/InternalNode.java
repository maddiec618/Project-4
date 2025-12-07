public class InternalNode
    implements BaseNode
{
    // ~ Fields ................................................................
    // private BaseNode[] children = new BaseNode[8];
    private BaseNode left;
    private BaseNode right;
    public static int count;
    public static int count2;

    private int height;
    private int discriminator;

    // ~ Constructors ..........................................................
    public InternalNode(int h)
    {
        this.discriminator = h % 3;
        height = h;
        count = 0;
        count2 = 0;
        this.left = EmptyLeaf.getInstance();
        this.right = EmptyLeaf.getInstance();
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
        int depth)
    {

        this.height = depth;

        if (xW <= 1 && yW <= 1 && zW <= 1)
        {
            left = left.insert(obj, x, y, z, xW, yW, zW, depth + 1);
            return this;
        }

        int mid = 0;

        if (discriminator == 0)
        {
            mid = x + xW / 2;
        }
        else if (discriminator == 1)
        {
            mid = y + yW / 2;
        }
        else
        {
            mid = z + zW / 2;
        }

        boolean goesLeft = false;
        boolean fitRight = false;

        if (discriminator == 0)
        {
            goesLeft = obj.getXOrig() + obj.getXWidth() <= mid;
            fitRight = obj.getXOrig() >= mid;
        }
        else if (discriminator == 1)
        {
            goesLeft = obj.getYOrig() + obj.getYWidth() <= mid;
            fitRight = obj.getYOrig() >= mid;
        }
        else
        {
            goesLeft = obj.getZOrig() + obj.getZWidth() <= mid;
            fitRight = obj.getZOrig() >= mid;
        }

        int leftXW = xW;
        int leftYW = yW;
        int leftZW = zW;
        int rightXW = xW;
        int rightYW = yW;
        int rightZW = zW;

        int rightX = x;
        int rightY = y;
        int rightZ = z;

        if (discriminator == 0)
        {
            leftXW = xW / 2;
            rightXW = xW - leftXW;
            rightX = x + leftXW;
        }
        else if (discriminator == 1)
        {
            leftYW = yW / 2;
            rightYW = yW - leftYW;
            rightY = y + leftYW;
        }
        else
        {
            leftZW = zW / 2;
            rightZW = zW - leftZW;
            rightZ = z + leftZW;
        }

        if (goesLeft)
        {
            if (leftXW > 0 && leftYW > 0 && leftZW > 0)
            {
                left = left
                    .insert(obj, x, y, z, leftXW, leftYW, leftZW, depth + 1);
            }
        }
        else if (fitRight)
        {
            if (rightXW > 0 && rightYW > 0 && rightZW > 0)
            {
                right = right.insert(
                    obj,
                    rightX,
                    rightY,
                    rightZ,
                    rightXW,
                    rightYW,
                    rightZW,
                    depth + 1);
            }
        }
        else
        {
            if (leftXW > 0 && leftYW > 0 && leftZW > 0)
            {
                left = left
                    .insert(obj, x, y, z, leftXW, leftYW, leftZW, depth + 1);
            }

            if (rightXW > 0 && rightYW > 0 && rightZW > 0)
            {
                right = right.insert(
                    obj,
                    rightX,
                    rightY,
                    rightZ,
                    rightXW,
                    rightYW,
                    rightZW,
                    depth + 1);
            }
        }
        return this;
    }


    @Override
    public String print(int x, int y, int z, int xW, int yW, int zW, int depth)
    {
        BinTree.addCount();
        count2++;
        String output = "I (" + x + ", " + y + ", " + z + ", " + xW + ", " + yW
            + ", " + zW + ") " + depth + "\r\n";

        if (discriminator == 0)
        {
            int leftXW = xW / 2;
            int rightXW = xW - leftXW;

            output += left.print(x, y, z, leftXW, yW, zW, depth + 1);
            output += right.print(x + leftXW, y, z, rightXW, yW, zW, depth + 1);
        }
        else if (discriminator == 1)
        {
            int leftYW = yW / 2;
            int rightYW = yW - leftYW;

            output += left.print(x, y, z, xW, leftYW, zW, depth + 1);
            output += right.print(x, y + leftYW, z, xW, rightYW, zW, depth + 1);

        }
        else
        {
            int leftZW = zW / 2;
            int rightZW = zW - leftZW;

            output += left.print(x, y, z, xW, yW, leftZW, depth + 1); // to
            output += right.print(x, y, z + leftZW, xW, yW, rightZW, depth + 1);
        }
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
        int depth)
    {
        this.height = depth;

        if (xW <= 1 && yW <= 1 && zW <= 1) {
            left = left.delete(obj, x, y, z, xW, yW, zW, depth + 1);
            if (left instanceof EmptyLeaf) {
                return EmptyLeaf.getInstance();
            }
            return this;
        }

        int leftXW = xW;
        int leftYW = yW;
        int leftZW = zW;
        int rightXW = xW;
        int rightYW = yW;
        int rightZW = zW;

        int rightX = x;
        int rightY = y;
        int rightZ = z;

        if (discriminator == 0) {
            leftXW  = xW / 2;
            rightXW = xW - leftXW;
            rightX  = x + leftXW;
        }
        else if (discriminator == 1) {
            leftYW  = yW / 2;
            rightYW = yW - leftYW;
            rightY  = y + leftYW;
        }
        else { 
            leftZW  = zW / 2;
            rightZW = zW - leftZW;
            rightZ  = z + leftZW;
        }

        if (leftXW > 0 && leftYW > 0 && leftZW > 0 &&
            boxIntersectsObject(x, y, z, leftXW, leftYW, leftZW, obj)) {
            left = left.delete(obj, x, y, z, leftXW, leftYW, leftZW, depth + 1);
        }

        if (rightXW > 0 && rightYW > 0 && rightZW > 0 &&
            boxIntersectsObject(rightX, rightY, rightZ, rightXW, rightYW, rightZW, obj)) {
            right = right.delete(
                obj,
                rightX,
                rightY,
                rightZ,
                rightXW,
                rightYW,
                rightZW,
                depth + 1);
        }

        boolean leftEmpty  = left  instanceof EmptyLeaf;
        boolean rightEmpty = right instanceof EmptyLeaf;

        if (leftEmpty && rightEmpty) {
            return EmptyLeaf.getInstance();
        }

        boolean leftLeaf  = left  instanceof LeafNode;
        boolean rightLeaf = right instanceof LeafNode;

        if (leftLeaf && rightLeaf) {
            LeafNode l = (LeafNode) left;
            LeafNode r = (LeafNode) right;

            int distinct = countDistinctObjects(l, r);
            if (distinct <= 3) {
                return mergeTwoLeavesDistinct(l, r);
            }
        }

        if (leftLeaf && rightEmpty) {
            return copyLeaf((LeafNode) left);
        }
        if (rightLeaf && leftEmpty) {
            return copyLeaf((LeafNode) right);
        }

        return this;
    }


    private LeafNode copyLeaf(LeafNode src)
    {
        LeafNode copy = new LeafNode();
        for (AirObject obj : src.getObjects())
        {
            if (obj != null)
            {
                copy.insertDirect(obj);
            }
        }
        return copy;
    }


    private int countDistinctObjects(LeafNode leftLeaf, LeafNode rightLeaf)
    {
        String[] names = new String[8];
        int distinct = 0;

        AirObject[] leftObjs = leftLeaf.getObjects();
        for (int i = 0; i < leftObjs.length; i++)
        {
            AirObject obj = leftObjs[i];
            if (obj != null && !containsName(names, distinct, obj.getName()))
            {
                names[distinct] = obj.getName();
                distinct++;
            }
        }

        AirObject[] rightObjs = rightLeaf.getObjects();
        for (int i = 0; i < rightObjs.length; i++)
        {
            AirObject obj = rightObjs[i];
            if (obj != null && !containsName(names, distinct, obj.getName()))
            {
                names[distinct] = obj.getName();
                distinct++;
            }
        }

        return distinct;
    }
    
    private boolean boxIntersectsObject(
        int bx, int by, int bz,
        int bxW, int byW, int bzW,
        AirObject obj)
    {
        int bx2 = bx + bxW;
        int by2 = by + byW;
        int bz2 = bz + bzW;

        int ox  = obj.getXOrig();
        int oy  = obj.getYOrig();
        int oz  = obj.getZOrig();
        int ox2 = ox + obj.getXWidth();
        int oy2 = oy + obj.getYWidth();
        int oz2 = oz + obj.getZWidth();

        return (bx < ox2 && ox < bx2)
            && (by < oy2 && oy < by2)
            && (bz < oz2 && oz < bz2);
    }


    private
        LeafNode
        mergeTwoLeavesDistinct(LeafNode leftLeaf, LeafNode rightLeaf)
    {
        LeafNode merged = new LeafNode();
        String[] names = new String[8];
        int used = 0;

        AirObject[] leftObjs = leftLeaf.getObjects();
        for (int i = 0; i < leftObjs.length; i++)
        {
            AirObject obj = leftObjs[i];
            if (obj != null && !containsName(names, used, obj.getName()))
            {
                merged.insertDirect(obj);
                names[used] = obj.getName();
                used++;
            }
        }

        AirObject[] rightObjs = rightLeaf.getObjects();
        for (int i = 0; i < rightObjs.length; i++)
        {
            AirObject obj = rightObjs[i];
            if (obj != null && !containsName(names, used, obj.getName()))
            {
                merged.insertDirect(obj);
                names[used] = obj.getName();
                used++;
            }
        }

        return merged;
    }


    private boolean containsName(String[] names, int used, String name)
    {
        for (int i = 0; i < used; i++)
        {
            if (names[i].equals(name))
            {
                return true;
            }
        }
        return false;
    }

    @Override
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
        if (discriminator == 0) {
            int leftXW  = xW / 2;
            int rightXW = xW - leftXW;

            left.collision(output, x, y, z, leftXW, yW, zW, depth + 1);
            right.collision(output, x + leftXW, y, z, rightXW, yW, zW, depth + 1);
        }
        else if (discriminator == 1) {
            int leftYW  = yW / 2;
            int rightYW = yW - leftYW;

            left.collision(output, x, y, z, xW, leftYW, zW, depth + 1);
            right.collision(output, x, y + leftYW, z, xW, rightYW, zW, depth + 1);
        }
        else { 
            int leftZW  = zW / 2;
            int rightZW = zW - leftZW;

            left.collision(output, x, y, z, xW, yW, leftZW,  depth + 1);
            right.collision(output, x, y, z + leftZW, xW, yW, rightZW, depth + 1);
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

    @Override
    public void intersect(
        StringBuilder output,
        int nodeX, int nodeY, int nodeZ,
        int nodeXW, int nodeYW, int nodeZW,
        int qx, int qy, int qz, int qxW, int qyW, int qzW,
        int depth)
    {
        if (!boxesIntersect(nodeX, nodeY, nodeZ, nodeXW, nodeYW, nodeZW,
                            qx, qy, qz, qxW, qyW, qzW)) {
            return;
        }

        BinTree.count++;

        output.append("in internal node ")
              .append(nodeX).append(" ")
              .append(nodeY).append(" ")
              .append(nodeZ).append(" ")
              .append(nodeXW).append(" ")
              .append(nodeYW).append(" ")
              .append(nodeZW).append(" ")
              .append(depth).append("\r\n");

        if (discriminator == 0) {
            int leftXW  = nodeXW / 2;
            int rightXW = nodeXW - leftXW;

            int leftX   = nodeX;
            int rightX  = nodeX + leftXW;
            int childY  = nodeY;
            int childZ  = nodeZ;
            int childYW = nodeYW;
            int childZW = nodeZW;

            if (leftXW > 0 &&
                boxesIntersect(leftX, childY, childZ, leftXW, childYW, childZW,
                               qx, qy, qz, qxW, qyW, qzW)) {
                left.intersect(output,
                               leftX, childY, childZ,
                               leftXW, childYW, childZW,
                               qx, qy, qz, qxW, qyW, qzW,
                               depth + 1);
            }

            if (rightXW > 0 &&
                boxesIntersect(rightX, childY, childZ, rightXW, childYW, childZW,
                               qx, qy, qz, qxW, qyW, qzW)) {
                right.intersect(output,
                                rightX, childY, childZ,
                                rightXW, childYW, childZW,
                                qx, qy, qz, qxW, qyW, qzW,
                                depth + 1);
            }
        }
        else if (discriminator == 1) {
            int leftYW  = nodeYW / 2;
            int rightYW = nodeYW - leftYW;

            int leftY   = nodeY;
            int rightY  = nodeY + leftYW;
            int childX  = nodeX;
            int childZ  = nodeZ;
            int childXW = nodeXW;
            int childZW = nodeZW;

            if (leftYW > 0 &&
                boxesIntersect(childX, leftY, childZ, childXW, leftYW, childZW,
                               qx, qy, qz, qxW, qyW, qzW)) {
                left.intersect(output,
                               childX, leftY, childZ,
                               childXW, leftYW, childZW,
                               qx, qy, qz, qxW, qyW, qzW,
                               depth + 1);
            }

            if (rightYW > 0 &&
                boxesIntersect(childX, rightY, childZ, childXW, rightYW, childZW,
                               qx, qy, qz, qxW, qyW, qzW)) {
                right.intersect(output,
                                childX, rightY, childZ,
                                childXW, rightYW, childZW,
                                qx, qy, qz, qxW, qyW, qzW,
                                depth + 1);
            }
        }
    }
}