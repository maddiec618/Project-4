import java.util.Random;

/**
 * Creates a Skip List.
 * 
 * @author madelync05 and ellae
 * @version Fall 2025
 */
public class SkipList<K extends Comparable<K>, V> // why k and v
{
    // ~ Fields ................................................................
    private static class SkipNode<K, V> {
        K key;
        V value;
        SkipNode<K, V>[] forward;
        int depth;

        /**
         * Create a new SkipNode object.
         * 
         * @param k
         *            key
         * @param v
         *            value
         * @param level
         *            node level
         */
        @SuppressWarnings("unchecked")
        public SkipNode(K k, V v, int level) {
            key = k;
            depth = level;
            value = v;
            forward = new SkipNode[level + 1];
            for (int i = 0; i < level; i++) {
                forward[i] = null;
            }
        }


        /**
         * to string method
         * 
         * @return the string representation of the node
         */
        public String toString() {
            return value.toString();
        }
    }

    private SkipNode<K, V> head;
    private int level;
    private int size;
    static private Random ran;
    private int count;

    // ~ Constructors ..........................................................
    public SkipList(Random r) {
        ran = r;
        head = new SkipNode<>(null, null, 0);
        level = -1;
        size = 0;
        count = 0;
    }


    // ~Public Methods ........................................................
    int randomLevel() {
        int lev;
        for (lev = 0; Math.abs(ran.nextInt()) % 2 == 0; lev++) {
//            System.out.println("he"+lev);
            // if (lev >= 16) break;
            ;
        }
        return lev;
    }


    @SuppressWarnings("unchecked")
    private void adjustHead(int newLevel) {
        @SuppressWarnings("rawtypes")
        SkipNode temp = head;
        head = new SkipNode<>(null, null, newLevel);
        for (int i = 0; i <= level; i++) {
            head.forward[i] = temp.forward[i];
        }
        level = newLevel;
    }


    /** Insert a key, element pair into the skip list */
    @SuppressWarnings("unchecked")
    public boolean insert(K key, V value) {
        if(!(find(key)==null)){
            return false;
        }

        int newLevel = randomLevel();
// System.out.println("Key "+key+"\n value "+value.toString());
// System.out.println("level "+level+" new level "+newLevel);
        if (newLevel > level) { // If new node is deeper
            adjustHead(newLevel); // adjust the header
        }
// System.out.println("level "+level+" new level "+newLevel);
        SkipNode<K, V>[] update = new SkipNode[level + 1];
        SkipNode<K, V> x = head;
        for (int i = level; i >= 0; i--) {
            while ((x.forward[i] != null) && (x.forward[i].key.compareTo(
                key) < 0)) {
                // System.out.println("x old "+ x.toString());
                x = x.forward[i];
//                System.out.println("x new "+ x.toString());
            }
            update[i] = x;
        }
        x = x.forward[0];
        if (x != null && x.key.compareTo(key) == 0) {
            return false;
        }
        x = new SkipNode<>(key, value, newLevel);
        for (int i = 0; i <= newLevel; i++) { // Splice into list
            x.forward[i] = update[i].forward[i]; // Who x points to
            update[i].forward[i] = x; // Who points to x
        }
        size++; // Increment dictionary size
// System.out.println("size:"+size+"\nkey:"+key);
        return true;
    }


    /**
     * prints the skip list
     */
    @SuppressWarnings("rawtypes")
    public String print() {
        if (size == 0) {
            return "SkipList is empty";
        }
        String re = "";
        re += "node has depth " + (level + 1) + " value null\n";
        SkipNode<K, V> node = head.forward[0]; // Start at first real node at
                                               // level 0

        while (node != null) {
            re += "Node has depth " + (node.depth + 1) + ", Value: " + node
                .toString();
            node = node.forward[0];
        }
        re += size + " skiplist nodes printed";
        return re;
    }


    /**
     * method to get the size of the skiplist
     * 
     * @return int representing size of skip list
     */
    public int getSize() {
        return size;
    }


    /**
     * method deleting a value from the skiplist
     * 
     * @param key
     *            the key value that is to be removed.
     * @return String representing the key that was removed, null otherwise
     */
    @SuppressWarnings("unchecked")
    public V deleteKey(K key) {
        SkipNode<K, V>[] update = new SkipNode[level + 1];
        SkipNode<K, V> x = head;
        // going through to find where key would be
        for (int i = level; i >= 0; i--) {
            while (x.forward[i] != null && (x.forward[i].key.compareTo(
                key) < 0)) {
                x = x.forward[i];
            }
            update[i] = x;
        }
        x = x.forward[0];
        if (x != null && x.key.compareTo(key) == 0) {
            for (int i = 0; i <= level; i++) {
                if (update[i].forward[i] != x)
                    break;
                update[i].forward[i] = x.forward[i];
            }
//            while (level > 0 && head.forward[level] == null) {
//                level--;
//            }
            size--;
            return x.value;
        }
//        if(size==0) {
//            count ++;
//        }
        return null;
    }


    /**
     * gets a singular key if it exists
     * 
     * @param k
     *            the key to search for
     * @return String a string representation of the k if it exists, null
     *         otherwise
     */
    public String find(K key) {
        SkipNode<K, V> x = head;
        for (int i = level; i >= 0; i--) {
            while ((x.forward[i] != null) && (x.forward[i].key.compareTo(
                key) < 0)) { // go forward
                x = x.forward[i]; // Go one last step
            }
        }
        x = x.forward[0]; // Move to actual record, if it exists
        if ((x != null) && (x.key.compareTo(key) == 0)) {
            return x.value.toString();
        } // Got it
        return null;
    }
    
}
