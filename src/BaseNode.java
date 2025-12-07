/**
 * Interface class for the base node for the Bintree
 *
 * @author madelync05 and ellae
 * @version Fall 2025
 */
public interface BaseNode {
    // ----------------------------------------------------------
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
     * @return BaseNode that now represents root
     */
    public BaseNode insert(
        AirObject obj,
        int x,
        int y,
        int z,
        int xW,
        int yW,
        int zW,
        int depth);


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
    public String print(int x, int y, int z, int xW, int yW, int zW, int depth);


    /**
     * deletes a node object from the subtree.
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
     * @return BaseNode that now represents root
     */
    public BaseNode delete(
        AirObject obj,
        int x,
        int y,
        int z,
        int xW,
        int yW,
        int zW,
        int depth);


    public void collision(
        StringBuilder collisions,
        int i,
        int j,
        int k,
        int worldSize,
        int worldSize2,
        int worldSize3,
        int l);
    
    void intersect(
        StringBuilder out,
        int nodeX, int nodeY, int nodeZ,
        int nodeXW, int nodeYW, int nodeZW,
        int qx, int qy, int qz, int qxW, int qyW, int qzW,
        int depth);
}
