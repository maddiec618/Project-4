import java.util.Random;

/**
 * Creates a Skip List.
 * 
 * @author madelync05 and ellae
 * @version Fall 2025
 */
public class SkipList<K extends Comparable<K>, V>       //why k and v
{
    // ~ Fields ................................................................
    private static class SkipNode<K, V> 
    {
        K key;
        V value;
        SkipNode<K, V>[] forward;

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
        public SkipNode(K k, V v, int level)
        {
            key = k;
            value = v;
            forward = new SkipNode[level + 1];
            for (int i = 0; i < level; i++)
            {
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
//        /**
//         * creates a skip node object with no parameters
//         */
//        @SuppressWarnings("unchecked")
//        public SkipNode()
//        {
//            key = null;
//            value = null;
//            forward = new SkipNode[1];
//        }
    }

    private SkipNode<K, V> head;
    private int level;
    private int size;
    static private Random ran = new Random();

    // ~ Constructors ..........................................................
    public SkipList()
    {
        head = new SkipNode<>(null, null, 0);
        level = -1;
        size = 0;
    }


    // ~Public Methods ........................................................
    int randomLevel()
    {
        int lev;
        for (lev = 0; Math.abs(ran.nextInt()) % 2 == 0; lev++)
        {
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
    public boolean insert(K key, V value)
    {
        
        int newLevel = randomLevel();
        if (newLevel > level) { // If new node is deeper
            adjustHead(newLevel); // adjust the header
          }
        SkipNode<K, V>[] update = new SkipNode[level + 1];
        SkipNode<K, V> x = head;
        for (int i = level; i >= 0; i--)
        {
            while ((x.forward[i] != null)
                && (x.forward[i].key.compareTo(key) < 0))
            {
                x = x.forward[i];
            }
            update[i] = x;
        }
        if (x.forward[0] != null && x.forward[0].key.compareTo(key) == 0) {
            return false; 
        }
        x = new SkipNode<>(key, value, newLevel);
        for (int i = 0; i <= newLevel; i++)
        { // Splice into list
            x.forward[i] = update[i].forward[i]; // Who x points to
            update[i].forward[i] = x; // Who points to x
        }
        size++; // Increment dictionary size
        System.out.println("size:"+size+"\nkey:"+key);
        return true;
    }
    /**
     * prints the skip list
     */
    public String print() {
        if(size==0) {
        return "SkipList is empty";}
        String re= "";
        re+= head.forward[0].toString();
        re+=head.forward[1].toString();
        return re;
//        for(int i = 0; i < size; i++) {
//        re+= head.forward[i].toString();
//        }
//        return re; 
        
    }
    public int getSize() {
        return size;
    }
}
