import java.util.Random;

/**
 * The world for this project. We have a Skip List and a Bintree
 *
 * @author {Your Name Here}
 * @version {Put Something Here}
 */
public class WorldDB implements ATC {
    private final int worldSize = 1024;
    private Random rnd;
     /*
     * should have an empty skip list
     */
    private SkipList<String, AirObject> skip;
    private BinTree bin;
    private AirObject[] objects;
    private int objCount;

    /**
     * Create a brave new World.
     * 
     * @param r
     *            A random number generator to use
     */
    public WorldDB(Random r) {
        bin = new BinTree(worldSize);
        rnd = r;
//        if (rnd == null) {
//            rnd = new Random();
//        }
        skip = new SkipList<String, AirObject>(r);
        objects = new AirObject[128];
        objCount = 0;
        clear();
    }


    /**
     * Clear the world
     */
    public void clear() {
        skip = new SkipList<String, AirObject>(rnd);
        bin.clear();
        objCount = 0;
        for (int i = 0; i < objects.length; i++) {
            objects[i] = null;
        }
    }


    // ----------------------------------------------------------
    /**
     * (Try to) insert an AirObject into the database
     * 
     * @param a
     *            An AirObject.
     * @return True iff the AirObject is successfully entered into the database
     */
    public boolean add(AirObject a) {
        if (!a.isValid()) {
            return false;
        }

        if (skip.find(a.getName()) != null) {
            return false;
        }

        skip.insert(a.getName(), a);
        bin.insert(a);
        
        objects[objCount++] = a;
        
        return true;
    }


    // ----------------------------------------------------------
    /**
     * The AirObject with this name is deleted from the database (if it exists).
     * Print the AirObject's toString value if one with that name exists. If no
     * such AirObject with this name exists, return null.
     * 
     * @param name
     *            AirObject name.
     * @return A string representing the AirObject, or null if no such name.
     */
    public String delete(String name) {
        AirObject value = skip.deleteKey(name);
        if (value == null)
            return null;
        bin.delete(value);
        
        for (int i = 0; i < objCount; i++) {
            if (objects[i] != null
                && objects[i].getName().equals(value.getName())) {
                // shift tail left
                for (int j = i; j < objCount - 1; j++) {
                    objects[j] = objects[j + 1];
                }
                objects[objCount - 1] = null;
                objCount--;
                break;
            }
        }
        
        return value.toString();
    }


    // ----------------------------------------------------------
    /**
     * Return a listing of the Skiplist in alphabetical order on the names. See
     * the sample test cases for details on format.
     * 
     * @return String listing the AirObjects in the Skiplist as specified.
     */
    public String printskiplist() {
        return skip.print();
    }


    // ----------------------------------------------------------
    /**
     * Return a listing of the Bintree nodes in preorder. See the sample test
     * cases for details on format.
     * 
     * @return String listing the Bintree nodes as specified.
     */
    public String printbintree() {
        return bin.printTree();
    }


    // ----------------------------------------------------------
    /**
     * Print an AirObject with a given name if it exists
     * 
     * @param name
     *            The name of the AirObject to print
     * @return String showing the toString for the AirObject if it exists Return
     *         null if there is no such name
     */
    public String print(String name) {
        return skip.find(name);
    }


    // ----------------------------------------------------------
    /**
     * Return a listing of the AirObjects found in the database between the min
     * and max values for names. See the sample test cases for details on
     * format.
     * 
     * @param start
     *            Minimum of range
     * @param end
     *            Maximum of range
     * @return String listing the AirObjects in the range as specified. Null if
     *         the parameters are bad
     */
    public String rangeprint(String start, String end) {
        if (start == null || end == null) {
            return null;
        }
        if (start.compareTo(end) > 0) {
            return null;
        }

        String objectsInRange = "Found these records in the range " + start + " to " + end + "\r\n";

        AirObject[] matches = new AirObject[objCount];
        int m = 0;
        for (int i = 0; i < objCount; i++) {
            AirObject a = objects[i];
            String name = a.getName();
            if (name.compareTo(start) >= 0 && name.compareTo(end) <= 0) {
                matches[m++] = a;
            }
        }

        for (int i = 0; i < m; i++) {
            objectsInRange += matches[i].toString();
        }

        return objectsInRange;
    }


    // ----------------------------------------------------------
    /**
     * Return a listing of all collisions between AirObjects bounding boxes that
     * are found in the database. See the sample test cases for details on
     * format. Note that the collision is only reported for the node that
     * contains the origin of the intersection box.
     * 
     * @return String listing the AirObjects that participate in collisions.
     */
    public String collisions() {
        return bin.collision();
    }


    // ----------------------------------------------------------
    /**
     * Return a listing of all AirObjects whose bounding boxes that intersect
     * the given bounding box. Note that the collision is only reported for the
     * node that contains the origin of the intersection box. See the sample
     * test cases for details on format.
     * 
     * @param x
     *            Bounding box upper left x
     * @param y
     *            Bounding box upper left y
     * @param z
     *            Bounding box upper left z
     * @param xwid
     *            Bounding box x width
     * @param ywid
     *            Bounding box y width
     * @param zwid
     *            Bounding box z width
     * @return String listing the AirObjects that intersect the given box.
     *         Return null if any input parameters are bad
     */
    public String intersect(int x, int y, int z, int xwid, int ywid, int zwid) {
        if (x < 0 || y < 0 || z < 0 || xwid < 0 || ywid < 0 || zwid < 0
            || x >= 1024 || y >= 1024 || z >= 1024 || xwid + x > 1024 || zwid
                + z > 1024 || ywid + y > 1024) {
            return null;
        }
        return bin.intersect(x, y, z, xwid, ywid, zwid);
    }
}