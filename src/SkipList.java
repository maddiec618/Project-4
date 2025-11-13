import java.util.Random;

/**
 * Creates a Skip List.
 * 
 * @author madelync05 and ellae
 * @version Fall 2025
 */
public class SkipList<K extends Comparable<K>, V>
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


    /** Insert a key, element pair into the skip list */
    @SuppressWarnings("unchecked")
    public void insert(K key, V value)
    {
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
        x = new SkipNode<>(key, elem, newLevel);
        for (int i = 0; i <= newLevel; i++)
        { // Splice into list
            x.forward[i] = update[i].forward[i]; // Who x points to
            update[i].forward[i] = x; // Who points to x
        }
        size++; // Increment dictionary size
    }

}
