import java.util.Random;
import student.TestCase;
/**
 * @author madelync05 & ellae
 * @version fall 2025
 */
public class AirControlTest extends TestCase {

    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing here
    }


    /**
     * Get code coverage of the class declaration.
     *
     * @throws Exception
     */
    public void testRInit() throws Exception {
        AirControl recstore = new AirControl();
        assertNotNull(recstore);
    }


    // ----------------------------------------------------------
    /**
     * Test syntax: Sample Input/Output
     *
     * @throws Exception
     */
    public void testSampleInput() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);

        assertTrue(w.add(new Balloon("B1", 10, 11, 11, 21, 12, 31, "hot_air",
            15)));
        assertTrue(w.add(new AirPlane("Air1", 0, 10, 1, 20, 2, 30, "USAir", 717,
            4)));
        assertTrue(w.add(new Drone("Air2", 100, 1010, 101, 924, 2, 900,
            "Droners", 3)));
        assertTrue(w.add(new Bird("pterodactyl", 0, 100, 20, 10, 50, 50,
            "Dinosaur", 1)));
        assertFalse(w.add(new Bird("pterodactyl", 0, 100, 20, 10, 50, 50,
            "Dinosaur", 1)));
         assertTrue(w.add(new Rocket("Enterprise",
         0, 100, 20, 10, 50, 50, 5000, 99.29)));
        
         assertFuzzyEquals(
         "Rocket Enterprise 0 100 20 10 50 50 5000 99.29",
         w.delete("Enterprise"));

        assertFuzzyEquals("Airplane Air1 0 10 1 20 2 30 USAir 717 4", w.print(
            "Air1"));
        assertNull(w.print("air1"));

        assertFuzzyEquals("I (0, 0, 0, 1024, 1024, 1024) 0\r\n"
            + "  I (0, 0, 0, 512, 1024, 1024) 1\r\n"
            + "    Leaf with 3 objects (0, 0, 0, 512, 512, 1024) 2\r\n"
            + "    (Airplane Air1 0 10 1 20 2 30 USAir 717 4)\r\n"
            + "    (Balloon B1 10 11 11 21 12 31 hot_air 15)\r\n"
            + "    (Bird pterodactyl 0 100 20 10 50 50 Dinosaur 1)\r\n"
            + "    Leaf with 1 objects (0, 512, 0, 512, 512, 1024) 2\r\n"
            + "    (Drone Air2 100 1010 101 924 2 900 Droners 3)\r\n"
            + "  Leaf with 1 objects (512, 0, 0, 512, 1024, 1024) 1\r\n"
            + "  (Drone Air2 100 1010 101 924 2 900 Droners 3)\r\n"
            + "5 Bintree nodes printed\r\n", w.printbintree());

        assertFuzzyEquals("Node has depth 3, Value (null)\r\n"
            + "Node has depth 3, "
            + "Value (Airplane Air1 0 10 1 20 2 30 USAir 717 4)\r\n"
            + "Node has depth 1, "
            + "Value (Drone Air2 100 1010 101 924 2 900 Droners 3)\r\n"
            + "Node has depth 2, "
            + "Value (Balloon B1 10 11 11 21 12 31 hot_air 15)\r\n"
            + "Node has depth 2, "
            + "Value (Bird pterodactyl 0 100 20 10 50 50 Dinosaur 1)\r\n"
            + "4 skiplist nodes printed\r\n", w.printskiplist());

             assertFuzzyEquals(
             "Found these records in the range a to z\r\n"
             + "Bird pterodactyl 0 100 20 10 50 50 Dinosaur 1\r\n",
             w.rangeprint("a", "z"));
             assertFuzzyEquals(
             "Found these records in the range a to l\r\n",
             w.rangeprint("a", "l"));
             assertNull(w.rangeprint("z", "a"));
            
             assertFuzzyEquals(
             "The following collisions exist in the database:\r\n"
             + "In leaf node (0, 0, 0, 512, 512, 1024) 2\r\n"
             + "(Airplane Air1 0 10 1 20 2 30 USAir 717 4) "
             + "and (Balloon B1 10 11 11 21 12 31 hot_air 15)\r\n"
             + "In leaf node (0, 512, 0, 512, 512, 1024) 2\r\n"
             + "In leaf node (512, 0, 0, 512, 1024, 1024) 1\r\n",
             w.collisions());
            
             assertFuzzyEquals(
             "The following objects intersect (0 0 0 1024 1024 1024):\r\n"
             + "In Internal node (0, 0, 0, 1024, 1024, 1024) 0\r\n"
             + "In Internal node (0, 0, 0, 512, 1024, 1024) 1\r\n"
             + "In leaf node (0, 0, 0, 512, 512, 1024) 2\r\n"
             + "Airplane Air1 0 10 1 20 2 30 USAir 717 4\r\n"
             + "Balloon B1 10 11 11 21 12 31 hot_air 15\r\n"
             + "Bird pterodactyl 0 100 20 10 50 50 Dinosaur 1\r\n"
             + "In leaf node (0, 512, 0, 512, 512, 1024) 2\r\n"
             + "Drone Air2 100 1010 101 924 2 900 Droners 3\r\n"
             + "In leaf node (512, 0, 0, 512, 1024, 1024) 1\r\n"
             + "5 nodes were visited in the bintree\r\n",
             w.intersect(0, 0, 0, 1024, 1024, 1024));
    }


    /**
     * Test syntax: Check various forms of bad input parameters
     *
     * @throws Exception
     */
    public void testBadInput() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);
        assertFalse(w.add(new AirPlane("a", 1, 1, 1, 1, 1, 1, null, 1, 1)));
        assertFalse(w.add(new AirPlane("a", 1, 1, 1, 1, 1, 1, "Alaska", 0, 1)));
        assertFalse(w.add(new AirPlane("a", 1, 1, 1, 1, 1, 1, "Alaska", 1, 0)));
        assertFalse(w.add(new Balloon(null, 1, 1, 1, 1, 1, 1, "hot", 5)));
        assertFalse(w.add(new Balloon("b", -1, 1, 1, 1, 1, 1, "hot", 5)));
        assertFalse(w.add(new Balloon("b", 1, -1, 1, 1, 1, 1, "hot", 5)));
        assertFalse(w.add(new Balloon("b", 1, 1, -1, 1, 1, 1, "hot", 5)));
        assertFalse(w.add(new Balloon("b", 1, 1, 1, 0, 1, 1, "hot", 5)));
        assertFalse(w.add(new Balloon("b", 1, 1, 1, 1, 0, 1, "hot", 5)));
        assertFalse(w.add(new Balloon("b", 1, 1, 1, 1, 1, 0, "hot", 5)));
        assertFalse(w.add(new Balloon("b", 1, 1, 1, 1, 1, 1, null, 5)));
        assertFalse(w.add(new Balloon("b", 1, 1, 1, 1, 1, 1, "hot", -1)));
        assertFalse(w.add(new Bird("b", 1, 1, 1, 1, 1, 1, null, 5)));
        assertFalse(w.add(new Bird("b", 1, 1, 1, 1, 1, 1, "Ostrich", 0)));
        assertFalse(w.add(new Drone("d", 1, 1, 1, 1, 1, 1, null, 5)));
        assertFalse(w.add(new Drone("d", 1, 1, 1, 1, 1, 1, "Droner", 0)));
        assertFalse(w.add(new Rocket("r", 1, 1, 1, 1, 1, 1, -1, 1.1)));
        assertFalse(w.add(new Rocket("r", 1, 1, 1, 1, 1, 1, 1, -1.1)));
        assertFalse(w.add(new AirPlane("a", 2000, 1, 1, 1, 1, 1, "Alaska", 1,
            1)));
        assertFalse(w.add(new AirPlane("a", 1, 2000, 1, 1, 1, 1, "Alaska", 1,
            1)));
        assertFalse(w.add(new AirPlane("a", 1, 1, 2000, 1, 1, 1, "Alaska", 1,
            1)));
        assertFalse(w.add(new AirPlane("a", 1, 1, 1, 2000, 1, 1, "Alaska", 1,
            1)));
        assertFalse(w.add(new AirPlane("a", 1, 1, 1, 1, 2000, 1, "Alaska", 1,
            1)));
        assertFalse(w.add(new AirPlane("a", 1, 1, 1, 1, 1, 2000, "Alaska", 1,
            1)));
        assertFalse(w.add(new AirPlane("a", 1000, 1, 1, 1000, 1, 1, "Alaska", 1,
            1)));
        assertFalse(w.add(new AirPlane("a", 1, 1000, 1, 1, 1000, 1, "Alaska", 1,
            1)));
        assertFalse(w.add(new AirPlane("a", 1, 1, 1000, 1, 1, 1000, "Alaska", 1,
            1)));
        assertNull(w.delete(null));
        assertNull(w.print(null));
        assertNull(w.rangeprint(null, "a"));
        assertNull(w.rangeprint("a", null));
        assertNull(w.intersect(-1, 1, 1, 1, 1, 1));
        assertNull(w.intersect(1, -1, 1, 1, 1, 1));
        assertNull(w.intersect(1, 1, -1, 1, 1, 1));
        assertNull(w.intersect(1, 1, 1, -1, 1, 1));
        assertNull(w.intersect(1, 1, 1, 1, -1, 1));
        assertNull(w.intersect(1, 1, 1, 1, 1, -1));
        assertNull(w.intersect(2000, 1, 1, 1, 1, 1));
        assertNull(w.intersect(1, 2000, 1, 1, 1, 1));
        assertNull(w.intersect(1, 1, 2000, 1, 1, 1));
        assertNull(w.intersect(1, 1, 1, 2000, 1, 1));
        assertNull(w.intersect(1, 1, 1, 1, 2000, 1));
        assertNull(w.intersect(1, 1, 1, 1, 1, 2000));
        assertNull(w.intersect(1000, 1, 1, 1000, 1, 1));
        assertNull(w.intersect(1, 1000, 1, 1, 1000, 1));
        assertNull(w.intersect(1, 1, 1000, 1, 1, 1000));
    }


    /**
     * Test empty: Check various returns from commands on empty database
     *
     * @throws Exception
     */
    public void testEmpty() throws Exception {
        WorldDB w = new WorldDB(null);
        assertNull(w.delete("hello"));
        assertFuzzyEquals("SkipList is empty", w.printskiplist());
        assertFuzzyEquals("E (0, 0, 0, 1024, 1024, 1024) 0\r\n"
            + "1 Bintree nodes printed\r\n", w.printbintree());
        assertNull(w.print("hello"));
        assertFuzzyEquals("Found these records in the range begin to end\n", w
            .rangeprint("begin", "end"));
        assertFuzzyEquals("The following collisions exist in the database:\n", w
            .collisions());
        assertFuzzyEquals("The following objects intersect (1, 1, 1, 1, 1, 1)\n"
            + "1 nodes were visited in the bintree\n", w.intersect(1, 1, 1, 1,
                1, 1));
    }

    /**
     * tests the delete mehtod in bin
     *
     * @throws Exception
     */
    public void testBinDelete() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);
        assertTrue(w.add(new Balloon("B1", 100, 11, 101, 21, 12, 31, "hot_air",
            15)));
        assertTrue(w.add(new AirPlane("A1", 0, 210, 100, 52, 342, 30, "USAir",
            717, 4)));
        assertTrue(w.add(new Drone("D1", 100, 10, 101, 90, 2, 90, "Droners",
            3)));
        assertTrue(w.add(new Bird("B2", 1000, 100, 20, 9, 50, 530, "Dinosaur",
            1)));
        assertFuzzyEquals("I (0, 0, 0, 1024, 1024, 1024) 0\r\n"
            + "    leaf with 3 objects 0 0 0 512 1024 1024 1\r\n"
            + "    (airplane a1 0 210 100 52 342 30 usair 717 4)\r\n"
            + "    (balloon b1 100 11 101 21 12 31 hot_air 15)\r\n"
            + "    drone d1 100 10 101 90 2 90 droners 3\r\n"
            + "    leaf with 1 objects 512 0 0 512 1024 1024 1\r\n"
            + "    bird b2 1000 100 20 9 50 530 dinosaur 1\r\n"
            + "3 Bintree nodes printed\r\n", w.printbintree());
        System.out.println("\n\n\n here");
        assertFuzzyEquals("Drone D1 100 10 101 90 2 90 Droners 3", w.delete(
            "D1"));
        assertFuzzyEquals(
             "    leaf with 3 objects 0 0 0 1024 1024 1024 0\r\n"
            + "    (airplane a1 0 210 100 52 342 30 usair 717 4)\r\n"
            + "    (balloon b1 100 11 101 21 12 31 hot_air 15)\r\n"
            + "    bird b2 1000 100 20 9 50 530 dinosaur 1\r\n"
            + "1 Bintree nodes printed\r\n", w.printbintree());
    }
    /**
     * Test syntax: Sample Input/Output
     *
     * @throws Exception
     */
    public void testSampleInsert() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);
        System.out.println("here");

        assertTrue(w.add(new Balloon("B1", 10, 11, 11, 21, 12, 31, "hot_air",
            15)));
        assertTrue(w.add(new AirPlane("Air1", 0, 10, 1, 20, 2, 30, "USAir", 717,
            4)));
        assertTrue(w.add(new Drone("Air2", 100, 1010, 101, 924, 2, 900,
            "Droners", 3)));
        assertTrue(w.add(new Bird("pterodactyl", 0, 100, 20, 10, 50, 50,
            "Dinosaur", 1)));
        assertFalse(w.add(new Bird("pterodactyl", 0, 100, 20, 10, 50, 50,
            "Dinosaur", 1)));
        assertFuzzyEquals("Node has depth 3, Value (null)\r\n"
            + "Node has depth 3, "
            + "Value (Airplane Air1 0 10 1 20 2 30 USAir 717 4)\r\n"
            + "Node has depth 1, "
            + "Value (Drone Air2 100 1010 101 924 2 900 Droners 3)\r\n"
            + "Node has depth 2, "
            + "Value (Balloon B1 10 11 11 21 12 31 hot_air 15)\r\n"
            + "Node has depth 2, "
            + "Value (Bird pterodactyl 0 100 20 10 50 50 Dinosaur 1)\r\n"
            + "4 skiplist nodes printed\r\n", w.printskiplist());
    }


    /**
     * tests delete for skip lists when not in the list
     *
     * @throws Exception
     */
    public void testDeleteSkipNotIn() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);
        assertTrue(w.add(new Balloon("B1", 10, 11, 11, 21, 12, 31, "hot_air",
            15)));
        assertTrue(w.add(new AirPlane("A1", 0, 10, 1, 20, 2, 30, "USAir", 717,
            4)));
        assertTrue(w.add(new Drone("D1", 100, 1010, 101, 924, 2, 900, "Droners",
            3)));
        assertTrue(w.add(new Bird("B2", 0, 100, 20, 10, 50, 50, "Dinosaur",
            1)));
        assertTrue(w.add(new Rocket("R1", 0, 100, 20, 10, 50, 50, 100, 102.3)));
        assertNull(w.delete("H2"));
        assertNull(w.delete("s2"));
        assertNull(w.delete("d1"));
        assertNull(w.delete("b1"));
    }


    /**
     * tests delete for skip lists
     *
     * @throws Exception
     */
    public void testDeleteSkip() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);
        assertTrue(w.add(new Balloon("B1", 10, 11, 11, 21, 12, 31, "hot_air",
            15)));
        assertTrue(w.add(new AirPlane("A1", 0, 10, 1, 20, 2, 30, "USAir", 717,
            4)));
        assertTrue(w.add(new Drone("D1", 100, 1010, 101, 924, 2, 900, "Droners",
            3)));
        assertTrue(w.add(new Bird("B2", 0, 100, 20, 10, 50, 50, "Dinosaur",
            1)));
        assertTrue(w.add(new Rocket("R1", 0, 100, 20, 10, 50, 50, 100, 102.3)));
        System.out.println(w.printskiplist());
        assertFuzzyEquals("Bird B2 0 100 20 10 50 50 Dinosaur 1", w.delete(
            "B2"));
        assertNull(w.delete("B2"));
    }


    /**
     * tests find for skip lists
     *
     * @throws Exception
     */
    public void testFindSkip() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);
        assertTrue(w.add(new Balloon("B1", 10, 11, 11, 21, 12, 31, "hot_air",
            15)));
        assertTrue(w.add(new AirPlane("A1", 0, 10, 1, 20, 2, 30, "USAir", 717,
            4)));
        assertTrue(w.add(new Drone("D1", 100, 1010, 101, 924, 2, 900, "Droners",
            3)));
        assertTrue(w.add(new Bird("B2", 0, 100, 20, 10, 50, 50, "Dinosaur",
            1)));
        assertTrue(w.add(new Rocket("R1", 0, 100, 20, 10, 50, 50, 100, 102.3)));
        assertFalse(w.add(new Bird("B2", 0, 100, 20, 10, 50, 50, "Dinosaur",
            1)));
        assertFuzzyEquals("Bird B2 0 100 20 10 50 50 Dinosaur 1", w.print(
            "B2"));
        assertFuzzyEquals("Airplane A1 0 10 1 20 2 30 USAir 717 4", w.print(
            "A1"));
        assertFuzzyEquals("Drone D1 100 1010 101 924 2 900 Droners 3", w.print(
            "D1"));
        assertNull(w.print("air1"));
        assertNull(w.print("a1"));
        assertNull(w.print("b2"));

    }


    /**
     * tests print for skip lists
     *
     * @throws Exception
     */
    public void testPrintSkip() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);
        assertTrue(w.add(new Balloon("B1", 10, 11, 11, 21, 12, 31, "hot_air",
            15)));
        assertTrue(w.add(new AirPlane("A1", 0, 10, 1, 20, 2, 30, "USAir", 717,
            4)));
        assertTrue(w.add(new Drone("D1", 100, 1010, 101, 924, 2, 900, "Droners",
            3)));
        assertTrue(w.add(new Bird("B2", 0, 100, 20, 10, 50, 50, "Dinosaur",
            1)));
        assertTrue(w.add(new Rocket("R1", 0, 100, 20, 10, 50, 50, 100, 102.3)));
        assertFalse(w.add(new Bird("B2", 0, 100, 20, 10, 50, 50, "Dinosaur",
            1)));
        assertFuzzyEquals("Node has depth 3, Value (null)\r\n"
            + "Node has depth 3, "
            + "Value (Airplane A1 0 10 1 20 2 30 USAir 717 4)\r\n"
            + "Node has depth 2, "
            + "Value (Balloon B1 10 11 11 21 12 31 hot_air 15)\r\n"
            + "Node has depth 2, "
            + "Value (Bird B2 0 100 20 10 50 50 Dinosaur 1)\r\n"
            + "Node has depth 1, "
            + "Value (Drone D1 100 1010 101 924 2 900 Droners 3)\r\n"
            + "Node has depth 1, "
            + "Value (Rocket R1 0 100 20 10 50 50 100 102.3)\r\n"
            + "5 skiplist nodes printed\r\n", w.printskiplist());
    }


    /**
     * tests print for skip lists with deletions
     *
     * @throws Exception
     */
    public void testPrintSkipDelete() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);
        assertTrue(w.add(new Balloon("B1", 10, 11, 11, 21, 12, 31, "hot_air",
            15)));
        assertTrue(w.add(new AirPlane("A1", 0, 10, 1, 20, 2, 30, "USAir", 717,
            4)));
        assertTrue(w.add(new Drone("D1", 100, 1010, 101, 924, 2, 900, "Droners",
            3)));
        assertTrue(w.add(new Bird("B2", 0, 100, 20, 10, 50, 50, "Dinosaur",
            1)));
        assertTrue(w.add(new Rocket("R1", 0, 100, 20, 10, 50, 50, 100, 102.3)));
        assertFalse(w.add(new Bird("B2", 0, 100, 20, 10, 50, 50, "Dinosaur",
            1)));
        assertFuzzyEquals("Node has depth 3, Value (null)\r\n"
            + "Node has depth 3, "
            + "Value (Airplane A1 0 10 1 20 2 30 USAir 717 4)\r\n"
            + "Node has depth 2, "
            + "Value (Balloon B1 10 11 11 21 12 31 hot_air 15)\r\n"
            + "Node has depth 2, "
            + "Value (Bird B2 0 100 20 10 50 50 Dinosaur 1)\r\n"
            + "Node has depth 1, "
            + "Value (Drone D1 100 1010 101 924 2 900 Droners 3)\r\n"
            + "Node has depth 1, "
            + "Value (Rocket R1 0 100 20 10 50 50 100 102.3)\r\n"
            + "5 skiplist nodes printed\r\n", w.printskiplist());
        assertFuzzyEquals("Bird B2 0 100 20 10 50 50 Dinosaur 1", w.delete(
            "B2"));
        assertFuzzyEquals("Node has depth 3, Value (null)\r\n"
            + "Node has depth 3, "
            + "Value (Airplane A1 0 10 1 20 2 30 USAir 717 4)\r\n"
            + "Node has depth 2, "
            + "Value (Balloon B1 10 11 11 21 12 31 hot_air 15)\r\n"
            + "Node has depth 1, "
            + "Value (Drone D1 100 1010 101 924 2 900 Droners 3)\r\n"
            + "Node has depth 1, "
            + "Value (Rocket R1 0 100 20 10 50 50 100 102.3)\r\n"
            + "4 skiplist nodes printed\r\n", w.printskiplist());
        assertFuzzyEquals("Airplane A1 0 10 1 20 2 30 USAir 717 4", w.delete(
            "A1"));
        assertFuzzyEquals("Node has depth 3, Value (null)\r\n"
            + "Node has depth 2, "
            + "Value (Balloon B1 10 11 11 21 12 31 hot_air 15)\r\n"
            + "Node has depth 1, "
            + "Value (Drone D1 100 1010 101 924 2 900 Droners 3)\r\n"
            + "Node has depth 1, "
            + "Value (Rocket R1 0 100 20 10 50 50 100 102.3)\r\n"
            + "3 skiplist nodes printed\r\n", w.printskiplist());
        assertFuzzyEquals("Rocket R1 0 100 20 10 50 50 100 102.3", w.delete(
            "R1"));
        assertFuzzyEquals("Node has depth 3, Value (null)\r\n"
            + "Node has depth 2, "
            + "Value (Balloon B1 10 11 11 21 12 31 hot_air 15)\r\n"
            + "Node has depth 1, "
            + "Value (Drone D1 100 1010 101 924 2 900 Droners 3)\r\n"
            + "2 skiplist nodes printed\r\n", w.printskiplist());
        assertNull(w.delete("R2"));
        assertNull(w.delete("S2"));
        assertNull(w.delete("A2"));
        assertNull(w.delete("A1"));
        assertFuzzyEquals("Balloon B1 10 11 11 21 12 31 hot_air 15", w.delete(
            "B1"));
        assertFuzzyEquals("Node has depth 3, Value (null)\r\n"
            + "Node has depth 1, "
            + "Value (Drone D1 100 1010 101 924 2 900 Droners 3)\r\n"
            + "1 skiplist nodes printed\r\n", w.printskiplist());
        assertFuzzyEquals("Drone D1 100 1010 101 924 2 900 Droners 3", w.delete(
            "D1"));
        assertFuzzyEquals("SkipList is empty", w.printskiplist());
        assertNull(w.delete("B2"));
        System.out.println("hello");
        assertTrue(w.add(new Drone("D1", 100, 1010, 101, 924, 2, 900, "Droners",
            3)));
        assertFuzzyEquals("Node has depth 3, Value (null)\r\n"
            + "Node has depth 3, "
            + "Value (Drone D1 100 1010 101 924 2 900 Droners 3)\r\n"
            + "1 skiplist nodes printed\r\n", w.printskiplist());
        assertTrue(w.add(new Balloon("B1", 10, 11, 11, 21, 12, 31, "hot_air",
            15)));
        assertFuzzyEquals("Node has depth 3, Value (null)\r\n"
            + "Node has depth 2, "
            + "Value (Balloon B1 10 11 11 21 12 31 hot_air 15)\r\n"
            + "Node has depth 3, "
            + "Value (Drone D1 100 1010 101 924 2 900 Droners 3)\r\n"
            + "2 skiplist nodes printed\r\n", w.printskiplist());
        assertTrue(w.add(new Rocket("R1", 0, 100, 20, 10, 50, 50, 100, 102.3)));
        assertFuzzyEquals("Node has depth 3, Value (null)\r\n"
            + "Node has depth 2, "
            + "Value (Balloon B1 10 11 11 21 12 31 hot_air 15)\r\n"
            + "Node has depth 3, "
            + "Value (Drone D1 100 1010 101 924 2 900 Droners 3)\r\n"
            + "Node has depth 3, "
            + "Value (Rocket R1 0 100 20 10 50 50 100 102.3)\r\n"
            + "3 skiplist nodes printed\r\n", w.printskiplist());
        assertFuzzyEquals("Rocket R1 0 100 20 10 50 50 100 102.3", w.delete(
            "R1"));
        assertTrue(w.add(new AirPlane("A1", 0, 10, 1, 20, 2, 30, "USAir", 717,
            4)));
        assertFuzzyEquals("Node has depth 3, Value (null)\r\n"
            + "Node has depth 2, "
            + "Value (Airplane A1 0 10 1 20 2 30 USAir 717 4)\r\n"
            + "Node has depth 2, "
            + "Value (Balloon B1 10 11 11 21 12 31 hot_air 15)\r\n"
            + "Node has depth 3, "
            + "Value (Drone D1 100 1010 101 924 2 900 Droners 3)\r\n"
            + "3 skiplist nodes printed\r\n", w.printskiplist());
    }


    /**
     * Test syntax: delete
     *
     * @throws Exception
     */
    public void testDeleteEmpty() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);
        assertFuzzyEquals("SkipList is empty", w.printskiplist());
        assertNull(w.delete("B2"));
        assertNull(w.delete("A2"));
        assertNull(w.delete("B1"));
        assertNull(w.delete(""));
    }


    /**
     * Test syntax: Sample Insert for bin
     *
     * @throws Exception
     */
    public void testSampleInsertBin() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);
        assertTrue(w.add(new Balloon("B1", 100, 11, 101, 21, 12, 31, "hot_air",
            15)));
        assertTrue(w.add(new AirPlane("A1", 0, 210, 100, 520, 342, 30, "USAir",
            717, 4)));
        assertTrue(w.add(new Drone("D1", 100, 1010, 101, 924, 2, 900, "Droners",
            3)));
        assertTrue(w.add(new Bird("B2", 0, 100, 20, 103, 50, 530, "Dinosaur",
            1)));
        assertFuzzyEquals("I (0, 0, 0, 1024, 1024, 1024) 0\r\n"
            + "  I (0, 0, 0, 512, 1024, 1024) 1\r\n"
            + "    Leaf with 3 objects (0, 0, 0, 512, 512, 1024) 2\r\n"
            + "    (airplane a1 0 210 100 520 342 30 usair 717 4)\r\n"
            + "    (balloon b1 100 11 101 21 12 31 hot_air 15)\r\n"
            + "    (Bird b2 0 100 20 103 50 530 dinosaur 1)\r\n"
            + "    Leaf with 2 objects (0, 512, 0, 512, 512, 1024) 2\r\n"
            + "    (airplane a1 0 210 100 520 342 30 usair 717 4)\r\n"
            + "    (Drone d1 100 1010 101 924 2 900 Droners 3)\r\n"
            + "  Leaf with 2 objects (512, 0, 0, 512, 1024, 1024) 1\r\n"
            + "    (airplane a1 0 210 100 520 342 30 usair 717 4)\r\n"
            + "  (Drone d1 100 1010 101 924 2 900 Droners 3)\r\n"
            + "5 Bintree nodes printed\r\n", w.printbintree());
        assertTrue(w.add(new Rocket("R1", 0, 100, 20, 10, 50, 50, 100, 102.3)));
        assertFuzzyEquals("I (0, 0, 0, 1024, 1024, 1024) 0\r\n"
            + "  I (0, 0, 0, 512, 1024, 1024) 1\r\n"
            + "  I (0, 0, 0, 512, 512, 1024) 2\r\n"
            + "  I (0, 0, 0, 512, 512, 512) 3\r\n"
            + "  I (0, 0, 0, 256, 512, 512) 4\r\n"
            + "  I (0, 0, 0, 256, 256, 512) 5\r\n"
            + "  I (0, 0, 0, 256, 256, 256) 6\r\n"
            + "  I (0, 0, 0, 128, 256, 256) 7\r\n"
            + "    Leaf with 3 objects (0, 0, 0, 128 128 256) 8\r\n"
            + "    (balloon b1 100 11 101 21 12 31 hot_air 15)\r\n"
            + "    (Bird b2 0 100 20 103 50 530 dinosaur 1)\r\n"
            + "    (rocket r1 0 100 20 10 50 50 100 1023)\r\n"
            + "    Leaf with 3 objects (0, 128, 0, 128 128 256) 8\r\n"
            + "    (airplane a1 0 210 100 520 342 30 usair 717 4)\r\n"
            + "    (Bird b2 0 100 20 103 50 530 dinosaur 1)\r\n"
            + "    (rocket r1 0 100 20 10 50 50 100 1023)\r\n"
            + "    Leaf with 1 objects (128 0 0 128 256 256 7)\r\n"
            + "    (airplane a1 0 210 100 520 342 30 usair 717 4)\r\n"
            + "    Leaf with 1 objects (0 0 256 256 256 256 6)\r\n"
            + "    (Bird b2 0 100 20 103 50 530 dinosaur 1)\r\n"
            + "    Leaf with 1 objects (0 256 0 256 256 512 5)\r\n"
            + "    (airplane a1 0 210 100 520 342 30 usair 717 4)\r\n"
            + "    Leaf with 1 objects (256 0 0 256 512 512) 4\r\n"
            + "    (airplane a1 0 210 100 520 342 30 usair 717 4)\r\n"
            + "    Leaf with 1 objects (0 0 512 512 512 512) 3\r\n"
            + "    (Bird b2 0 100 20 103 50 530 dinosaur 1)\r\n"
            + "  Leaf with 2 objects (0 512 0 512 512 1024) 2\r\n"
            + "    (airplane a1 0 210 100 520 342 30 usair 717 4)\r\n"
            + "  (Drone d1 100 1010 101 924 2 900 Droners 3)\r\n"
            + "  Leaf with 2 objects (512, 0, 0, 512, 1024, 1024) 1\r\n"
            + "    (airplane a1 0 210 100 520 342 30 usair 717 4)\r\n"
            + "  (Drone d1 100 1010 101 924 2 900 Droners 3)\r\n"
            + "17 Bintree nodes printed\r\n", w.printbintree());
    }


    /**
     * Test syntax: Sample Insert for bin
     *
     * @throws Exception
     */
    public void testSampleInsertBinSkip() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);
        assertTrue(w.add(new Balloon("B1", 100, 11, 101, 21, 12, 31, "hot_air",
            15)));
        assertTrue(w.add(new AirPlane("A1", 0, 210, 100, 520, 342, 30, "USAir",
            717, 4)));
        assertTrue(w.add(new Drone("D1", 100, 1010, 101, 924, 2, 900, "Droners",
            3)));
        assertTrue(w.add(new Bird("B2", 0, 100, 20, 103, 50, 530, "Dinosaur",
            1)));
        assertFuzzyEquals("I (0, 0, 0, 1024, 1024, 1024) 0\r\n"
            + "  I (0, 0, 0, 512, 1024, 1024) 1\r\n"
            + "    Leaf with 3 objects (0, 0, 0, 512, 512, 1024) 2\r\n"
            + "    (airplane a1 0 210 100 520 342 30 usair 717 4)\r\n"
            + "    (balloon b1 100 11 101 21 12 31 hot_air 15)\r\n"
            + "    (Bird b2 0 100 20 103 50 530 dinosaur 1)\r\n"
            + "    Leaf with 2 objects (0, 512, 0, 512, 512, 1024) 2\r\n"
            + "    (airplane a1 0 210 100 520 342 30 usair 717 4)\r\n"
            + "    (Drone d1 100 1010 101 924 2 900 Droners 3)\r\n"
            + "  Leaf with 2 objects (512, 0, 0, 512, 1024, 1024) 1\r\n"
            + "    (airplane a1 0 210 100 520 342 30 usair 717 4)\r\n"
            + "  (Drone d1 100 1010 101 924 2 900 Droners 3)\r\n"
            + "5 Bintree nodes printed\r\n", w.printbintree());
        assertFuzzyEquals("Node has depth 3, Value (null)\r\n"
            + "Node has depth 3, Value (airplane a1 0 210 100 520 342 30 usair 717 "
            + "4)\r\n"
            + "Node has depth 2, "
            + "Value (Balloon B1 100 11 101 21 12 31 hot_air 15)\r\n"
            + "Node has depth 2, "
            + "Value (bird b2 0 100 20 103 50 530 dinosaur 1)\r\n"
            + "Node has depth 1, "
            + "Value (Drone D1 100 1010 101 924 2 900 Droners 3)\r\n"
            + "4 skiplist nodes printed\r\n", w.printskiplist());
    }
    
    /**
     * Test syntax: Sample Insert for bin
     *
     * @throws Exception
     */
    public void testSampleDeleteBinTree() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);
        assertTrue(w.add(new Balloon("B1", 100, 11, 101, 21, 12, 31, "hot_air",
            15)));
        assertTrue(w.add(new AirPlane("A1", 0, 210, 100, 520, 342, 30, "USAir",
            717, 4)));
        assertTrue(w.add(new Drone("D1", 100, 1010, 101, 924, 2, 900, "Droners",
            3)));
        assertTrue(w.add(new Bird("B2", 0, 100, 20, 103, 50, 530, "Dinosaur",
            1)));
        assertFuzzyEquals("I (0, 0, 0, 1024, 1024, 1024) 0\r\n"
            + "  I (0, 0, 0, 512, 1024, 1024) 1\r\n"
            + "    Leaf with 3 objects (0, 0, 0, 512, 512, 1024) 2\r\n"
            + "    (airplane a1 0 210 100 520 342 30 usair 717 4)\r\n"
            + "    (balloon b1 100 11 101 21 12 31 hot_air 15)\r\n"
            + "    (Bird b2 0 100 20 103 50 530 dinosaur 1)\r\n"
            + "    Leaf with 2 objects (0, 512, 0, 512, 512, 1024) 2\r\n"
            + "    (airplane a1 0 210 100 520 342 30 usair 717 4)\r\n"
            + "    (Drone d1 100 1010 101 924 2 900 Droners 3)\r\n"
            + "  Leaf with 2 objects (512, 0, 0, 512, 1024, 1024) 1\r\n"
            + "    (airplane a1 0 210 100 520 342 30 usair 717 4)\r\n"
            + "  (Drone d1 100 1010 101 924 2 900 Droners 3)\r\n"
            + "5 Bintree nodes printed\r\n", w.printbintree());
        assertFuzzyEquals("Node has depth 3, Value (null)\r\n"
            + "Node has depth 3, Value (airplane a1 0 210 100 520 342 30 usair 717 "
            + "4)\r\n"
            + "Node has depth 2, "
            + "Value (Balloon B1 100 11 101 21 12 31 hot_air 15)\r\n"
            + "Node has depth 2, "
            + "Value (bird b2 0 100 20 103 50 530 dinosaur 1)\r\n"
            + "Node has depth 1, "
            + "Value (Drone D1 100 1010 101 924 2 900 Droners 3)\r\n"
            + "4 skiplist nodes printed\r\n", w.printskiplist());
        w.delete("D1");
        w.delete("B2");
        assertFuzzyEquals("Leaf with 2 objects (0, 0, 0, 1024, 1024, 1024) 0\r\n"
            + "    (airplane a1 0 210 100 520 342 30 usair 717 4)\r\n"
            + "    (balloon b1 100 11 101 21 12 31 hot_air 15)\r\n"
            + "1 Bintree nodes printed\r\n", w.printbintree()); 
        w.delete("A1");
        w.delete("B1");
        assertFuzzyEquals("e (0, 0, 0, 1024, 1024, 1024) 0\r\n"
            + "1 Bintree nodes printed\r\n", w.printbintree()); 
    }
    
    /**
     * Tests collisions when there are no objects
     * @throws Exception
     */
    public void testCollisionsEmpty() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);
            
         assertFuzzyEquals(
         "The following collisions exist in the database:\r\n", w.collisions());
    }
    
    /**
     * Tests collisions when an object is deleted
     * @throws Exception
     */
    public void testCollisions() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);
        assertTrue(w.add(new Balloon("B1", 100, 11, 101, 21, 12, 31, "hot_air",
            15)));
        assertTrue(w.add(new AirPlane("A1", 0, 210, 100, 520, 342, 30, "USAir",
            717, 4)));
        assertTrue(w.add(new Drone("D1", 100, 1010, 101, 924, 2, 900, "Droners",
            3)));
        assertTrue(w.add(new Bird("B2", 0, 100, 20, 103, 50, 530, "Dinosaur",
            1)));
        assertFuzzyEquals("I (0, 0, 0, 1024, 1024, 1024) 0\r\n"
            + "  I (0, 0, 0, 512, 1024, 1024) 1\r\n"
            + "    Leaf with 3 objects (0, 0, 0, 512, 512, 1024) 2\r\n"
            + "    (airplane a1 0 210 100 520 342 30 usair 717 4)\r\n"
            + "    (balloon b1 100 11 101 21 12 31 hot_air 15)\r\n"
            + "    (Bird b2 0 100 20 103 50 530 dinosaur 1)\r\n"
            + "    Leaf with 2 objects (0, 512, 0, 512, 512, 1024) 2\r\n"
            + "    (airplane a1 0 210 100 520 342 30 usair 717 4)\r\n"
            + "    (Drone d1 100 1010 101 924 2 900 Droners 3)\r\n"
            + "  Leaf with 2 objects (512, 0, 0, 512, 1024, 1024) 1\r\n"
            + "    (airplane a1 0 210 100 520 342 30 usair 717 4)\r\n"
            + "  (Drone d1 100 1010 101 924 2 900 Droners 3)\r\n"
            + "5 Bintree nodes printed\r\n", w.printbintree());
        assertFuzzyEquals(
            "The following collisions exist in the database:\r\n"
            + "in leaf node 0 0 0 512 512 1024 2\r\n"
            + "in leaf node 0 512 0 512 512 1024 2\r\n"
            + "in leaf node 512 0 0 512 1024 1024 1\r\n",
            w.collisions());
        
        
    }
    /**
     * Tests collisions when an object is deleted
     * @throws Exception
     */
    public void testCollisionsDelete() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);

        assertTrue(w.add(new Balloon("B1", 10, 11, 11, 21, 12, 31, "hot_air",
            15)));
        assertTrue(w.add(new AirPlane("Air1", 0, 10, 1, 20, 2, 30, "USAir", 717,
            4)));
        assertTrue(w.add(new Drone("Air2", 100, 1010, 101, 924, 2, 900,
            "Droners", 3)));
        assertTrue(w.add(new Bird("pterodactyl", 0, 100, 20, 10, 50, 50,
            "Dinosaur", 1)));
        assertFalse(w.add(new Bird("pterodactyl", 0, 100, 20, 10, 50, 50,
            "Dinosaur", 1)));
        assertTrue(w.add(new Rocket("Enterprise",
         0, 100, 20, 10, 50, 50, 5000, 99.29)));
        
        assertFuzzyEquals(
            "The following collisions exist in the database:\r\n"
            + "in leaf node 0 0 0 64 64 128 11\r\n"
            + "airplane air1 0 10 1 20 2 30 usair 717 4 and balloon b1 10 11 11 21 "
            + "12 31 hot_air 15\r\n"
            + "in leaf node 0 64 0 64 64 128 11\r\n"
            + "rocket enterprise 0 100 20 10 50 50 5000 9929 and bird pterodactyl 0 "
            + "100 20 10 50 50 dinosaur 1\r\n"
            + "in leaf node 0 128 0 128 128 256 8\r\n"
//            + "rocket enterprise 0 100 20 10 50 50 5000 9929 and bird pterodactyl 0 "
//            + "100 20 10 50 50 dinosaur 1\r\n"
            + "In leaf node (0, 512, 0, 512, 512, 1024) 2\r\n"
            + "In leaf node (512, 0, 0, 512, 1024, 1024) 1\r\n",
            w.collisions());
        
        assertFuzzyEquals(
         "Rocket Enterprise 0 100 20 10 50 50 5000 99.29",
         w.delete("Enterprise"));
            
         assertFuzzyEquals(
         "The following collisions exist in the database:\r\n"
         + "In leaf node (0, 0, 0, 512, 512, 1024) 2\r\n"
         + "(Airplane Air1 0 10 1 20 2 30 USAir 717 4) "
         + "and (Balloon B1 10 11 11 21 12 31 hot_air 15)\r\n"
         + "In leaf node (0, 512, 0, 512, 512, 1024) 2\r\n"
         + "In leaf node (512, 0, 0, 512, 1024, 1024) 1\r\n",
         w.collisions());
    }
    
    /**
     * Tests intersection when there are no air objects
     * @throws Exception
     */
    public void testIntersectsEmpty() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);
            
        assertFuzzyEquals(
        "The following objects intersect (0 0 0 1024 1024 1024):\r\n" 
        + "1 nodes were visited in the bintree\r\n", 
        w.intersect(0, 0, 0, 1024, 1024, 1024));
    }
    
    /**
     * Tests that leaf stays when all intersect
     */
    public void testAllIntersect() {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);

        w.add(new Balloon("B1", 10, 10, 10, 20, 20, 20, "air", 5));
        w.add(new Balloon("B2", 12, 12, 12, 18, 18, 18, "air", 5));
        w.add(new Balloon("B3", 15, 15, 15, 10, 10, 10, "air", 5));
        w.add(new Balloon("B4", 16, 16, 16, 10, 10, 10, "air", 5));

        assertNotNull(w.intersect(0, 0, 0, 200, 200, 200));
        assertFuzzyEquals( "The following objects intersect (0 0 0 200 200 200):\r\n" 
            +"in leaf node 0 0 0 1024 1024 1024 0\r\n"
            + "balloon b1 10 10 10 20 20 20 air 5\r\n"
            + "balloon b2 12 12 12 18 18 18 air 5\r\n"
            + "balloon b3 15 15 15 10 10 10 air 5\r\n"
            + "balloon b4 16 16 16 10 10 10 air 5\r\n"
            + "1 nodes were visited in the bintree", w.intersect(0,0,0,200,200,200)); 
        
        assertFuzzyEquals("leaf with 4 objects 0 0 0 1024 1024 1024 0\r\n"
            + "balloon b1 10 10 10 20 20 20 air 5\r\n"
            + "balloon b2 12 12 12 18 18 18 air 5\r\n"
            + "balloon b3 15 15 15 10 10 10 air 5\r\n"
            + "balloon b4 16 16 16 10 10 10 air 5\r\n"
            + "1 bintree nodes printed", w.printbintree());      
    }
    /**
     * Tests intersection when there are no air objects
     * @throws Exception
     */
    public void testIntersect() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);
        
        assertTrue(w.add(new Balloon("B1", 100, 11, 101, 21, 12, 31, "hot_air",
            15)));
        assertFuzzyEquals( "the following objects intersect 500 500 500 200 200 200\r\n"
            + "in leaf node 0 0 0 1024 1024 1024 0\r\n"
            + "1 nodes were visited in the bintree", w.intersect(500,500,500,200,200,200)); 
        assertTrue(w.add(new AirPlane("A1", 0, 210, 100, 520, 342, 30, "USAir",
            717, 4)));
        assertTrue(w.add(new Drone("D1", 100, 1010, 101, 924, 2, 900, "Droners",
            3)));
        assertTrue(w.add(new Bird("B2", 0, 100, 20, 103, 50, 530, "Dinosaur",
            1)));
        assertFuzzyEquals(
        "The following objects intersect (0 0 0 1024 1024 1024):\r\n" 
        + "in internal node 0 0 0 1024 1024 1024 0\r\n"
        + "in internal node 0 0 0 512 1024 1024 1\r\n"
        +"in leaf node 0 0 0 512 512 1024 2\r\n"
        +"airplane a1 0 210 100 520 342 30 usair 717 4\r\n"
        +"balloon b1 100 11 101 21 12 31 hot_air 15\r\n"
        +"bird b2 0 100 20 103 50 530 dinosaur 1\r\n"
        +"in leaf node 0 512 0 512 512 1024 2\r\n"
        +"drone d1 100 1010 101 924 2 900 droners 3\r\n"
        +"in leaf node 512 0 0 512 1024 1024 1\r\n"
        +"5 nodes were visited in the bintree\r\n",
        w.intersect(0, 0, 0, 1024, 1024, 1024));        
        assertFuzzyEquals(
        "The following objects intersect (20 20 20 104 104 104):\r\n" 
        + "in internal node 0 0 0 1024 1024 1024 0\r\n"
        + "in internal node 0 0 0 512 1024 1024 1\r\n"
        +"in leaf node 0 0 0 512 512 1024 2\r\n"
        +"balloon b1 100 11 101 21 12 31 hot_air 15\r\n"
        +"bird b2 0 100 20 103 50 530 dinosaur 1\r\n"
        +"3 nodes were visited in the bintree\r\n",
        w.intersect(20, 20, 20, 104, 104, 104));   
        assertFuzzyEquals(
        "The following objects intersect (500 520 900 104 104 104):\r\n" 
        + "in internal node 0 0 0 1024 1024 1024 0\r\n"
        + "in internal node 0 0 0 512 1024 1024 1\r\n"
        +"in leaf node 0 512 0 512 512 1024 2\r\n"
        +"in leaf node 512 0 0 512 1024 1024 1\r\n"
        +"4 nodes were visited in the bintree\r\n",
        w.intersect(500, 520, 900, 104, 104, 104)); 
    }
    /**
     * adds mutation testing for clear
     */
    public void testClear() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);
        w.clear();
        assertTrue(w.add(new Balloon("B1", 100, 11, 101, 21, 12, 31, "hot_air",
            15)));
        assertTrue(w.add(new AirPlane("A1", 0, 210, 100, 520, 342, 30, "USAir",
            717, 4)));
        assertTrue(w.add(new Drone("D1", 100, 1010, 101, 924, 2, 900, "Droners",
            3)));
        assertTrue(w.add(new Bird("B2", 0, 100, 20, 103, 50, 530, "Dinosaur",
            1)));
        w.clear();
        assertTrue(w.add(new AirPlane("A1", 0, 210, 100, 520, 342, 30, "USAir",
            717, 4)));
        assertTrue(w.add(new Drone("D1", 100, 1010, 101, 924, 2, 900, "Droners",
            3)));
        assertTrue(w.add(new Bird("B2", 0, 100, 20, 103, 50, 530, "Dinosaur",
            1)));
        w.delete("B2");
        w.clear();
    }
    /**
     * tests the delete mehtod in bin again
     *
     * @throws Exception
     */
    public void testBinDeleteTwo() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);
        assertTrue(w.add(new Balloon("B1", 100, 11, 101, 21, 12, 31, "hot_air",
            15)));
        assertFuzzyEquals(
             "    leaf with 1 objects 0 0 0 1024 1024 1024 0\r\n"
            + "    (balloon b1 100 11 101 21 12 31 hot_air 15)\r\n"
            + "1 Bintree nodes printed\r\n", w.printbintree());
        assertTrue(w.add(new AirPlane("A1", 0, 210, 100, 52, 342, 30, "USAir",
            717, 4)));
        assertFuzzyEquals(
            "    leaf with 2 objects 0 0 0 1024 1024 1024 0\r\n"
           + "    (airplane a1 0 210 100 52 342 30 usair 717 4)\r\n"
           + "    (balloon b1 100 11 101 21 12 31 hot_air 15)\r\n"
           + "1 Bintree nodes printed\r\n", w.printbintree());
        assertTrue(w.add(new Drone("D1", 100, 10, 101, 90, 2, 90, "Droners",
            3)));
        assertTrue(w.add(new Bird("B2", 1000, 100, 20, 9, 50, 530, "Dinosaur",
            1)));
        assertFuzzyEquals("I (0, 0, 0, 1024, 1024, 1024) 0\r\n"
            + "    leaf with 3 objects 0 0 0 512 1024 1024 1\r\n"
            + "    (airplane a1 0 210 100 52 342 30 usair 717 4)\r\n"
            + "    (balloon b1 100 11 101 21 12 31 hot_air 15)\r\n"
            + "    drone d1 100 10 101 90 2 90 droners 3\r\n"
            + "    leaf with 1 objects 512 0 0 512 1024 1024 1\r\n"
            + "    bird b2 1000 100 20 9 50 530 dinosaur 1\r\n"
            + "3 Bintree nodes printed\r\n", w.printbintree());
        assertFuzzyEquals("Drone D1 100 10 101 90 2 90 Droners 3", w.delete(
            "D1"));
        assertFuzzyEquals(
             "    leaf with 3 objects 0 0 0 1024 1024 1024 0\r\n"
            + "    (airplane a1 0 210 100 52 342 30 usair 717 4)\r\n"
            + "    (balloon b1 100 11 101 21 12 31 hot_air 15)\r\n"
            + "    bird b2 1000 100 20 9 50 530 dinosaur 1\r\n"
            + "1 Bintree nodes printed\r\n", w.printbintree());
    }
    /**
     * tests the insert mehtod in bin with edge cases
     *
     * @throws Exception
     */
    public void testBinInsertEdge() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);
        assertTrue(w.add(new Balloon("B1", 100, 11, 0, 21, 12, 31, "hot_air",
            15)));
        assertFuzzyEquals(
             "    leaf with 1 objects 0 0 0 1024 1024 1024 0\r\n"
            + "    (balloon b1 100 11 0 21 12 31 hot_air 15)\r\n"
            + "1 Bintree nodes printed\r\n", w.printbintree());
        assertTrue(w.add(new AirPlane("A1", 0, 210, 100, 52, 1, 30, "USAir",
            717, 4)));
        assertFuzzyEquals(
            "    leaf with 2 objects 0 0 0 1024 1024 1024 0\r\n"
           + "    (airplane a1 0 210 100 52 1 30 usair 717 4)\r\n"
           + "    (balloon b1 100 11 0 21 12 31 hot_air 15)\r\n"
           + "1 Bintree nodes printed\r\n", w.printbintree());
        assertTrue(w.add(new Drone("D1", 100, 10, 101, 90, 2, 1, "Droners",
            3)));
        assertTrue(w.add(new Bird("B2", 1000, 100, 20, 1, 50, 530, "Dinosaur",
            1)));
        assertFuzzyEquals("I (0, 0, 0, 1024, 1024, 1024) 0\r\n"
            + "    leaf with 3 objects 0 0 0 512 1024 1024 1\r\n"
            + "    (airplane a1 0 210 100 52 1 30 usair 717 4)\r\n"
            + "    (balloon b1 100 11 0 21 12 31 hot_air 15)\r\n"
            + "    drone d1 100 10 101 90 2 1 droners 3\r\n"
            + "    leaf with 1 objects 512 0 0 512 1024 1024 1\r\n"
            + "    bird b2 1000 100 20 1 50 530 dinosaur 1\r\n"
            + "3 Bintree nodes printed\r\n", w.printbintree());
        assertFuzzyEquals("Drone D1 100 10 101 90 2 1 Droners 3", w.delete(
            "D1"));
        assertFuzzyEquals(
             "    leaf with 3 objects 0 0 0 1024 1024 1024 0\r\n"
            + "    (airplane a1 0 210 100 52 1 30 usair 717 4)\r\n"
            + "    (balloon b1 100 11 0 21 12 31 hot_air 15)\r\n"
            + "    bird b2 1000 100 20 1 50 530 dinosaur 1\r\n"
            + "1 Bintree nodes printed\r\n", w.printbintree());        
        assertTrue(w.add(new Drone("24Z", 0, 0, 0, 1, 1, 1024, "Droners",
                3)));
            assertTrue(w.add(new Bird("24A", 0, 0, 0, 1024, 1024, 1024, "Dinosaur",
                1)));
            assertTrue(w.add(new Balloon("12X", 512, 0, 0, 512, 1, 1, "hot_air",
                15)));
            assertTrue(w.add(new AirPlane("12Y", 0, 512, 0, 1, 512, 1, "USAir",
                717, 4)));
            assertFuzzyEquals(
                "i 0 0 0 1024 1024 1024 0\r\n"
                + "i 0 0 0 512 1024 1024 1\r\n"
                + "i 0 0 0 512 512 1024 2\r\n"
                + "i 0 0 0 512 512 512 3\r\n"
                + "i 0 0 0 256 512 512 4\r\n"
                + "i 0 0 0 256 256 512 5\r\n"
                + "i 0 0 0 256 256 256 6\r\n"
                + "i 0 0 0 128 256 256 7\r\n"
                + "leaf with 3 objects 0 0 0 128 128 256 8\r\n"
                + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
                + "drone 24z 0 0 0 1 1 1024 droners 3\r\n"
                + "balloon b1 100 11 0 21 12 31 hot_air 15\r\n"
                + "leaf with 2 objects 0 128 0 128 128 256 8\r\n"
                + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
                + "airplane a1 0 210 100 52 1 30 usair 717 4\r\n"
                + "leaf with 1 objects 128 0 0 128 256 256 7\r\n"
                + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
                + "leaf with 2 objects 0 0 256 256 256 256 6\r\n"
                + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
                + "drone 24z 0 0 0 1 1 1024 droners 3\r\n"
                + "leaf with 1 objects 0 256 0 256 256 512 5\r\n"
                + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
                + "leaf with 1 objects 256 0 0 256 512 512 4\r\n"
                + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
                + "leaf with 2 objects 0 0 512 512 512 512 3\r\n"
                + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
                + "drone 24z 0 0 0 1 1 1024 droners 3\r\n"
                + "leaf with 2 objects 0 512 0 512 512 1024 2\r\n"
                + "airplane 12y 0 512 0 1 512 1 usair 717 4\r\n"
                + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
                + "leaf with 3 objects 512 0 0 512 1024 1024 1\r\n"
                + "balloon 12x 512 0 0 512 1 1 hot_air 15\r\n"
                + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
                + "bird b2 1000 100 20 1 50 530 dinosaur 1\r\n"
                + "17 bintree nodes printed", w.printbintree());   
            assertFuzzyEquals("bird b2 1000 100 20 1 50 530 dinosaur 1", w.delete(
                "B2"));
            
    }
    /**
     * tests the insert mehtod in bin with edge cases
     *
     * @throws Exception
     */
    public void testBinInsertMass() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);
        assertTrue(w.add(new Balloon("0X", 0, 1, 1, 1, 1, 1, "hot_air",
            15)));
        assertTrue(w.add(new AirPlane("0Y", 1, 0, 1, 1, 1, 1, "USAir",
            717, 4)));
        assertTrue(w.add(new Drone("0Z", 1, 1, 0, 1, 1, 1, "Droners",
            3)));
        assertTrue(w.add(new Bird("0A", 0, 0, 0, 1, 1, 1, "Dinosaur",
            1)));
        assertTrue(w.add(new Balloon("23X", 1023, 0, 0, 1, 1, 1, "hot_air",
            15)));
        assertTrue(w.add(new AirPlane("23A", 1023, 1023, 1023, 1, 1, 1, "USAir",
            717, 4)));
        assertTrue(w.add(new Drone("23Y", 0, 1023, 0, 1, 1, 1, "Droners",
            3)));
        assertTrue(w.add(new Bird("23Z", 0, 0, 1023, 1, 1, 1, "Dinosaur",
            1)));
        assertTrue(w.add(new Balloon("24X", 0, 0, 0, 1024, 1, 1, "hot_air",
            15)));
        assertTrue(w.add(new AirPlane("24Y", 0, 0, 0, 1, 1024, 1, "USAir",
            717, 4)));
        assertTrue(w.add(new Drone("24Z", 0, 0, 0, 1, 1, 1024, "Droners",
            3)));
        assertTrue(w.add(new Bird("24A", 0, 0, 0, 1024, 1024, 1024, "Dinosaur",
            1)));
        assertTrue(w.add(new Balloon("12X", 512, 0, 0, 512, 1, 1, "hot_air",
            15)));
        assertTrue(w.add(new AirPlane("12Y", 0, 512, 0, 1, 512, 1, "USAir",
            717, 4)));
        assertTrue(w.add(new Drone("12Z", 0, 0, 512, 1, 1, 512, "Droners",
            3)));
        assertTrue(w.add(new Bird("12A", 512, 512, 512, 512, 512, 512, "Dinosaur",
            1)));
        assertTrue(w.add(new Balloon("B12", 512, 512, 512, 1, 1, 1, "hot_air",
            15)));
        assertFuzzyEquals("i 0 0 0 1024 1024 1024 0\r\n"
            + "i 0 0 0 512 1024 1024 1\r\n"
            + "i 0 0 0 512 512 1024 2\r\n"
            + "i 0 0 0 512 512 512 3\r\n"
            + "i 0 0 0 256 512 512 4\r\n"
            + "i 0 0 0 256 256 512 5\r\n"
            + "i 0 0 0 256 256 256 6\r\n"
            + "i 0 0 0 128 256 256 7\r\n"
            + "i 0 0 0 128 128 256 8\r\n"
            + "i 0 0 0 128 128 128 9\r\n"
            + "i 0 0 0 64 128 128 10\r\n"
            + "i 0 0 0 64 64 128 11\r\n"
            + "i 0 0 0 64 64 64 12\r\n"
            + "i 0 0 0 32 64 64 13\r\n"
            + "i 0 0 0 32 32 64 14\r\n"
            + "i 0 0 0 32 32 32 15\r\n"
            + "i 0 0 0 16 32 32 16\r\n"
            + "i 0 0 0 16 16 32 17\r\n"
            + "i 0 0 0 16 16 16 18\r\n"
            + "i 0 0 0 8 16 16 19\r\n"
            + "i 0 0 0 8 8 16 20\r\n"
            + "i 0 0 0 8 8 8 21\r\n"
            + "i 0 0 0 4 8 8 22\r\n"
            + "i 0 0 0 4 4 8 23\r\n"
            + "i 0 0 0 4 4 4 24\r\n"
            + "i 0 0 0 2 4 4 25\r\n"
            + "i 0 0 0 2 2 4 26\r\n"
            + "i 0 0 0 2 2 2 27\r\n"
            + "i 0 0 0 1 2 2 28\r\n"
            + "leaf with 5 objects 0 0 0 1 1 2 29\r\n"
            + "bird 0a 0 0 0 1 1 1 dinosaur 1\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "drone 24z 0 0 0 1 1 1024 droners 3\r\n"
            + "leaf with 3 objects 0 1 0 1 1 2 29\r\n"
            + "balloon 0x 0 1 1 1 1 1 hot_air 15\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "i 1 0 0 1 2 2 28\r\n"
            + "leaf with 3 objects 1 0 0 1 1 2 29\r\n"
            + "airplane 0y 1 0 1 1 1 1 usair 717 4\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "leaf with 2 objects 1 1 0 1 1 2 29\r\n"
            + "drone 0z 1 1 0 1 1 1 droners 3\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "leaf with 2 objects 0 0 2 2 2 2 27\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "drone 24z 0 0 0 1 1 1024 droners 3\r\n"
            + "leaf with 2 objects 0 2 0 2 2 4 26\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "leaf with 2 objects 2 0 0 2 4 4 25\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "leaf with 2 objects 0 0 4 4 4 4 24\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "drone 24z 0 0 0 1 1 1024 droners 3\r\n"
            + "leaf with 2 objects 0 4 0 4 4 8 23\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "leaf with 2 objects 4 0 0 4 8 8 22\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "leaf with 2 objects 0 0 8 8 8 8 21\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "drone 24z 0 0 0 1 1 1024 droners 3\r\n"
            + "leaf with 2 objects 0 8 0 8 8 16 20\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "leaf with 2 objects 8 0 0 8 16 16 19\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "leaf with 2 objects 0 0 16 16 16 16 18\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "drone 24z 0 0 0 1 1 1024 droners 3\r\n"
            + "leaf with 2 objects 0 16 0 16 16 32 17\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "leaf with 2 objects 16 0 0 16 32 32 16\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "leaf with 2 objects 0 0 32 32 32 32 15\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "drone 24z 0 0 0 1 1 1024 droners 3\r\n"
            + "leaf with 2 objects 0 32 0 32 32 64 14\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "leaf with 2 objects 32 0 0 32 64 64 13\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "leaf with 2 objects 0 0 64 64 64 64 12\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "drone 24z 0 0 0 1 1 1024 droners 3\r\n"
            + "leaf with 2 objects 0 64 0 64 64 128 11\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "leaf with 2 objects 64 0 0 64 128 128 10\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "leaf with 2 objects 0 0 128 128 128 128 9\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "drone 24z 0 0 0 1 1 1024 droners 3\r\n"
            + "leaf with 2 objects 0 128 0 128 128 256 8\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "leaf with 2 objects 128 0 0 128 256 256 7\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "leaf with 2 objects 0 0 256 256 256 256 6\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "drone 24z 0 0 0 1 1 1024 droners 3\r\n"
            + "leaf with 2 objects 0 256 0 256 256 512 5\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "leaf with 2 objects 256 0 0 256 512 512 4\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "leaf with 4 objects 0 0 512 512 512 512 3\r\n"
            + "drone 12z 0 0 512 1 1 512 droners 3\r\n"
            + "bird 23z 0 0 1023 1 1 1 dinosaur 1\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "drone 24z 0 0 0 1 1 1024 droners 3\r\n"
            + "leaf with 4 objects 0 512 0 512 512 1024 2\r\n"
            + "airplane 12y 0 512 0 1 512 1 usair 717 4\r\n"
            + "drone 23y 0 1023 0 1 1 1 droners 3\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "i 512 0 0 512 1024 1024 1\r\n"
            + "leaf with 4 objects 512 0 0 512 512 1024 2\r\n"
            + "balloon 12x 512 0 0 512 1 1 hot_air 15\r\n"
            + "balloon 23x 1023 0 0 1 1 1 hot_air 15\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "i 512 512 0 512 512 1024 2\r\n"
            + "leaf with 1 objects 512 512 0 512 512 512 3\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "i 512 512 512 512 512 512 3\r\n"
            + "leaf with 3 objects 512 512 512 256 512 512 4\r\n"
            + "bird 12a 512 512 512 512 512 512 dinosaur 1\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "balloon b12 512 512 512 1 1 1 hot_air 15\r\n"
            + "leaf with 3 objects 768 512 512 256 512 512 4\r\n"
            + "bird 12a 512 512 512 512 512 512 dinosaur 1\r\n"
            + "airplane 23a 1023 1023 1023 1 1 1 usair 717 4\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "67 bintree nodes printed", w.printbintree());
        assertFuzzyEquals("bird 24a 0 0 0 1024 1024 1024 dinosaur 1", w.delete(
            "24A"));
        assertFuzzyEquals("bird 12a 512 512 512 512 512 512 dinosaur 1", w.delete(
            "12A"));
        assertFuzzyEquals("balloon 23x 1023 0 0 1 1 1 hot_air 15", w.delete(
            "23X"));
        assertFuzzyEquals("airplane 12y 0 512 0 1 512 1 usair 717 4", w.delete(
            "12Y"));
        assertFuzzyEquals("i 0 0 0 1024 1024 1024 0\r\n"
            + "i 0 0 0 512 1024 1024 1\r\n"
            + "i 0 0 0 512 512 1024 2\r\n"
            + "i 0 0 0 512 512 512 3\r\n"
            + "i 0 0 0 256 512 512 4\r\n"
            + "i 0 0 0 256 256 512 5\r\n"
            + "i 0 0 0 256 256 256 6\r\n"
            + "i 0 0 0 128 256 256 7\r\n"
            + "i 0 0 0 128 128 256 8\r\n"
            + "i 0 0 0 128 128 128 9\r\n"
            + "i 0 0 0 64 128 128 10\r\n"
            + "i 0 0 0 64 64 128 11\r\n"
            + "i 0 0 0 64 64 64 12\r\n"
            + "i 0 0 0 32 64 64 13\r\n"
            + "i 0 0 0 32 32 64 14\r\n"
            + "i 0 0 0 32 32 32 15\r\n"
            + "i 0 0 0 16 32 32 16\r\n"
            + "i 0 0 0 16 16 32 17\r\n"
            + "i 0 0 0 16 16 16 18\r\n"
            + "i 0 0 0 8 16 16 19\r\n"
            + "i 0 0 0 8 8 16 20\r\n"
            + "i 0 0 0 8 8 8 21\r\n"
            + "i 0 0 0 4 8 8 22\r\n"
            + "i 0 0 0 4 4 8 23\r\n"
            + "i 0 0 0 4 4 4 24\r\n"
            + "i 0 0 0 2 4 4 25\r\n"
            + "i 0 0 0 2 2 4 26\r\n"
            + "i 0 0 0 2 2 2 27\r\n"
            + "i 0 0 0 1 2 2 28\r\n"
            + "leaf with 4 objects 0 0 0 1 1 2 29\r\n"
            + "bird 0a 0 0 0 1 1 1 dinosaur 1\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "drone 24z 0 0 0 1 1 1024 droners 3\r\n"
            + "leaf with 2 objects 0 1 0 1 1 2 29\r\n"
            + "balloon 0x 0 1 1 1 1 1 hot_air 15\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "leaf with 3 objects 1 0 0 1 2 2 28\r\n"
            + "airplane 0y 1 0 1 1 1 1 usair 717 4\r\n"
            + "drone 0z 1 1 0 1 1 1 droners 3\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "leaf with 1 objects 0 0 2 2 2 2 27\r\n"
            + "drone 24z 0 0 0 1 1 1024 droners 3\r\n"
            + "leaf with 1 objects 0 2 0 2 2 4 26\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "leaf with 1 objects 2 0 0 2 4 4 25\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "leaf with 1 objects 0 0 4 4 4 4 24\r\n"
            + "drone 24z 0 0 0 1 1 1024 droners 3\r\n"
            + "leaf with 1 objects 0 4 0 4 4 8 23\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "leaf with 1 objects 4 0 0 4 8 8 22\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "leaf with 1 objects 0 0 8 8 8 8 21\r\n"
            + "drone 24z 0 0 0 1 1 1024 droners 3\r\n"
            + "leaf with 1 objects 0 8 0 8 8 16 20\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "leaf with 1 objects 8 0 0 8 16 16 19\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "leaf with 1 objects 0 0 16 16 16 16 18\r\n"
            + "drone 24z 0 0 0 1 1 1024 droners 3\r\n"
            + "leaf with 1 objects 0 16 0 16 16 32 17\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "leaf with 1 objects 16 0 0 16 32 32 16\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "leaf with 1 objects 0 0 32 32 32 32 15\r\n"
            + "drone 24z 0 0 0 1 1 1024 droners 3\r\n"
            + "leaf with 1 objects 0 32 0 32 32 64 14\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "leaf with 1 objects 32 0 0 32 64 64 13\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "leaf with 1 objects 0 0 64 64 64 64 12\r\n"
            + "drone 24z 0 0 0 1 1 1024 droners 3\r\n"
            + "leaf with 1 objects 0 64 0 64 64 128 11\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "leaf with 1 objects 64 0 0 64 128 128 10\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "leaf with 1 objects 0 0 128 128 128 128 9\r\n"
            + "drone 24z 0 0 0 1 1 1024 droners 3\r\n"
            + "leaf with 1 objects 0 128 0 128 128 256 8\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "leaf with 1 objects 128 0 0 128 256 256 7\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "leaf with 1 objects 0 0 256 256 256 256 6\r\n"
            + "drone 24z 0 0 0 1 1 1024 droners 3\r\n"
            + "leaf with 1 objects 0 256 0 256 256 512 5\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "leaf with 1 objects 256 0 0 256 512 512 4\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "leaf with 3 objects 0 0 512 512 512 512 3\r\n"
            + "drone 12z 0 0 512 1 1 512 droners 3\r\n"
            + "bird 23z 0 0 1023 1 1 1 dinosaur 1\r\n"
            + "drone 24z 0 0 0 1 1 1024 droners 3\r\n"
            + "leaf with 2 objects 0 512 0 512 512 1024 2\r\n"
            + "drone 23y 0 1023 0 1 1 1 droners 3\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "i 512 0 0 512 1024 1024 1\r\n"
            + "leaf with 2 objects 512 0 0 512 512 1024 2\r\n"
            + "balloon 12x 512 0 0 512 1 1 hot_air 15\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "leaf with 2 objects 512 512 0 512 512 1024 2\r\n"
            + "airplane 23a 1023 1023 1023 1 1 1 usair 717 4\r\n"
            + "balloon b12 512 512 512 1 1 1 hot_air 15\r\n"
            + "61 bintree nodes printed", w.printbintree());
        assertFuzzyEquals("drone 24z 0 0 0 1 1 1024 droners 3", w.delete(
            "24Z"));
        assertFuzzyEquals("drone 12z 0 0 512 1 1 512 droners 3", w.delete(
            "12Z"));
        assertFuzzyEquals("bird 23z 0 0 1023 1 1 1 dinosaur 1", w.delete(
            "23Z"));
        assertFuzzyEquals("airplane 0y 1 0 1 1 1 1 usair 717 4", w.delete(
            "0Y"));
        assertFuzzyEquals("bird 0a 0 0 0 1 1 1 dinosaur 1", w.delete(
            "0A"));
        assertFuzzyEquals("i 0 0 0 1024 1024 1024 0\r\n"
            + "i 0 0 0 512 1024 1024 1\r\n"
            + "i 0 0 0 512 512 1024 2\r\n"
            + "i 0 0 0 512 512 512 3\r\n"
            + "i 0 0 0 256 512 512 4\r\n"
            + "i 0 0 0 256 256 512 5\r\n"
            + "i 0 0 0 256 256 256 6\r\n"
            + "i 0 0 0 128 256 256 7\r\n"
            + "i 0 0 0 128 128 256 8\r\n"
            + "i 0 0 0 128 128 128 9\r\n"
            + "i 0 0 0 64 128 128 10\r\n"
            + "i 0 0 0 64 64 128 11\r\n"
            + "i 0 0 0 64 64 64 12\r\n"
            + "i 0 0 0 32 64 64 13\r\n"
            + "i 0 0 0 32 32 64 14\r\n"
            + "i 0 0 0 32 32 32 15\r\n"
            + "i 0 0 0 16 32 32 16\r\n"
            + "i 0 0 0 16 16 32 17\r\n"
            + "i 0 0 0 16 16 16 18\r\n"
            + "i 0 0 0 8 16 16 19\r\n"
            + "i 0 0 0 8 8 16 20\r\n"
            + "i 0 0 0 8 8 8 21\r\n"
            + "i 0 0 0 4 8 8 22\r\n"
            + "i 0 0 0 4 4 8 23\r\n"
            + "i 0 0 0 4 4 4 24\r\n"
            + "i 0 0 0 2 4 4 25\r\n"
            + "i 0 0 0 2 2 4 26\r\n"
            + "i 0 0 0 2 2 2 27\r\n"
            + "leaf with 3 objects 0 0 0 1 2 2 28\r\n"
            + "balloon 0x 0 1 1 1 1 1 hot_air 15\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "leaf with 2 objects 1 0 0 1 2 2 28\r\n"
            + "drone 0z 1 1 0 1 1 1 droners 3\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "leaf with 1 objects 0 2 0 2 2 4 26\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "leaf with 1 objects 2 0 0 2 4 4 25\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "leaf with 1 objects 0 4 0 4 4 8 23\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "leaf with 1 objects 4 0 0 4 8 8 22\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "leaf with 1 objects 0 8 0 8 8 16 20\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "leaf with 1 objects 8 0 0 8 16 16 19\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "leaf with 1 objects 0 16 0 16 16 32 17\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "leaf with 1 objects 16 0 0 16 32 32 16\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "leaf with 1 objects 0 32 0 32 32 64 14\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "leaf with 1 objects 32 0 0 32 64 64 13\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "leaf with 1 objects 0 64 0 64 64 128 11\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "leaf with 1 objects 64 0 0 64 128 128 10\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "leaf with 1 objects 0 128 0 128 128 256 8\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "leaf with 1 objects 128 0 0 128 256 256 7\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "leaf with 1 objects 0 256 0 256 256 512 5\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "leaf with 1 objects 256 0 0 256 512 512 4\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "leaf with 2 objects 0 512 0 512 512 1024 2\r\n"
            + "drone 23y 0 1023 0 1 1 1 droners 3\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "i 512 0 0 512 1024 1024 1\r\n"
            + "leaf with 2 objects 512 0 0 512 512 1024 2\r\n"
            + "balloon 12x 512 0 0 512 1 1 hot_air 15\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "leaf with 2 objects 512 512 0 512 512 1024 2\r\n"
            + "airplane 23a 1023 1023 1023 1 1 1 usair 717 4\r\n"
            + "balloon b12 512 512 512 1 1 1 hot_air 15\r\n"
            + "50 bintree nodes printed", w.printbintree());
        assertFuzzyEquals("balloon 0x 0 1 1 1 1 1 hot_air 15", w.delete(
            "0X"));
        assertFuzzyEquals("balloon 24x 0 0 0 1024 1 1 hot_air 15", w.delete(
            "24X"));
        assertFuzzyEquals("drone 23y 0 1023 0 1 1 1 droners 3", w.delete(
            "23Y"));
        assertFuzzyEquals("airplane 23a 1023 1023 1023 1 1 1 usair 717 4", w.delete(
            "23A"));
        assertFuzzyEquals("balloon b12 512 512 512 1 1 1 hot_air 15", w.delete(
            "B12"));
        assertTrue(w.add(new Bird("12A", 512, 512, 512, 512, 512, 512, "Dinosaur",
            1)));
        assertFuzzyEquals("i 0 0 0 1024 1024 1024 0\r\n"
            + "leaf with 2 objects 0 0 0 512 1024 1024 1\r\n"
            + "drone 0z 1 1 0 1 1 1 droners 3\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "leaf with 2 objects 512 0 0 512 1024 1024 1\r\n"
            + "bird 12a 512 512 512 512 512 512 dinosaur 1\r\n"
            + "balloon 12x 512 0 0 512 1 1 hot_air 15\r\n"
            + "3 bintree nodes printed", w.printbintree());
        assertFuzzyEquals("drone 0z 1 1 0 1 1 1 droners 3", w.delete(
            "0Z"));
        assertFuzzyEquals("leaf with 3 objects 0 0 0 1024 1024 1024 0\r\n"
            + "bird 12a 512 512 512 512 512 512 dinosaur 1\r\n"
            + "balloon 12x 512 0 0 512 1 1 hot_air 15\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "1 bintree nodes printed", w.printbintree());
        assertFuzzyEquals("bird 12a 512 512 512 512 512 512 dinosaur 1", w.delete(
            "12A"));
        assertFuzzyEquals("balloon 12x 512 0 0 512 1 1 hot_air 15", w.delete(
            "12X"));
        assertFuzzyEquals("airplane 24y 0 0 0 1 1024 1 usair 717 4", w.delete(
            "24Y"));
        assertFuzzyEquals("e 0 0 0 1024 1024 1024 0\r\n"
            + "1 bintree nodes printed", w.printbintree());
    }
    /**
     * tests the insert mehtod in bin trying to get node to zero
     *
     * @throws Exception
     */
    public void testBinInsertZero() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);
        assertTrue(w.add(new Balloon("0X", 0, 1, 1, 1, 1, 1, "hot_air",
            15)));
        assertTrue(w.add(new AirPlane("0Y", 1, 0, 1, 1, 1, 1, "USAir",
            717, 4)));
        assertTrue(w.add(new Drone("0Z", 1, 1, 0, 1, 1, 1, "Droners",
            3)));
        assertTrue(w.add(new Bird("0A", 0, 0, 0, 1, 1, 1, "Dinosaur",
            1)));
        assertFuzzyEquals("i 0 0 0 1024 1024 1024 0\r\n"
            + "i 0 0 0 512 1024 1024 1\r\n"
            + "i 0 0 0 512 512 1024 2\r\n"
            + "i 0 0 0 512 512 512 3\r\n"
            + "i 0 0 0 256 512 512 4\r\n"
            + "i 0 0 0 256 256 512 5\r\n"
            + "i 0 0 0 256 256 256 6\r\n"
            + "i 0 0 0 128 256 256 7\r\n"
            + "i 0 0 0 128 128 256 8\r\n"
            + "i 0 0 0 128 128 128 9\r\n"
            + "i 0 0 0 64 128 128 10\r\n"
            + "i 0 0 0 64 64 128 11\r\n"
            + "i 0 0 0 64 64 64 12\r\n"
            + "i 0 0 0 32 64 64 13\r\n"
            + "i 0 0 0 32 32 64 14\r\n"
            + "i 0 0 0 32 32 32 15\r\n"
            + "i 0 0 0 16 32 32 16\r\n"
            + "i 0 0 0 16 16 32 17\r\n"
            + "i 0 0 0 16 16 16 18\r\n"
            + "i 0 0 0 8 16 16 19\r\n"
            + "i 0 0 0 8 8 16 20\r\n"
            + "i 0 0 0 8 8 8 21\r\n"
            + "i 0 0 0 4 8 8 22\r\n"
            + "i 0 0 0 4 4 8 23\r\n"
            + "i 0 0 0 4 4 4 24\r\n"
            + "i 0 0 0 2 4 4 25\r\n"
            + "i 0 0 0 2 2 4 26\r\n"
            + "i 0 0 0 2 2 2 27\r\n"
            + "leaf with 2 objects 0 0 0 1 2 2 28\r\n"
            + "bird 0a 0 0 0 1 1 1 dinosaur 1\r\n"
            + "balloon 0x 0 1 1 1 1 1 hot_air 15\r\n"
            + "leaf with 2 objects 1 0 0 1 2 2 28\r\n"
            + "airplane 0y 1 0 1 1 1 1 usair 717 4\r\n"
            + "drone 0z 1 1 0 1 1 1 droners 3\r\n"
            + "30 bintree nodes printed", w.printbintree());
        assertTrue(w.add(new Balloon("0A2", 0, 0, 0, 1, 1, 1, "hot_air",
            15)));
        assertTrue(w.add(new AirPlane("0A3", 0, 0, 0, 1, 1, 1, "USAir",
            717, 4)));
        assertFuzzyEquals("i 0 0 0 1024 1024 1024 0\r\n"
            + "i 0 0 0 512 1024 1024 1\r\n"
            + "i 0 0 0 512 512 1024 2\r\n"
            + "i 0 0 0 512 512 512 3\r\n"
            + "i 0 0 0 256 512 512 4\r\n"
            + "i 0 0 0 256 256 512 5\r\n"
            + "i 0 0 0 256 256 256 6\r\n"
            + "i 0 0 0 128 256 256 7\r\n"
            + "i 0 0 0 128 128 256 8\r\n"
            + "i 0 0 0 128 128 128 9\r\n"
            + "i 0 0 0 64 128 128 10\r\n"
            + "i 0 0 0 64 64 128 11\r\n"
            + "i 0 0 0 64 64 64 12\r\n"
            + "i 0 0 0 32 64 64 13\r\n"
            + "i 0 0 0 32 32 64 14\r\n"
            + "i 0 0 0 32 32 32 15\r\n"
            + "i 0 0 0 16 32 32 16\r\n"
            + "i 0 0 0 16 16 32 17\r\n"
            + "i 0 0 0 16 16 16 18\r\n"
            + "i 0 0 0 8 16 16 19\r\n"
            + "i 0 0 0 8 8 16 20\r\n"
            + "i 0 0 0 8 8 8 21\r\n"
            + "i 0 0 0 4 8 8 22\r\n"
            + "i 0 0 0 4 4 8 23\r\n"
            + "i 0 0 0 4 4 4 24\r\n"
            + "i 0 0 0 2 4 4 25\r\n"
            + "i 0 0 0 2 2 4 26\r\n"
            + "i 0 0 0 2 2 2 27\r\n"
            + "i 0 0 0 1 2 2 28\r\n"
            + "leaf with 3 objects 0 0 0 1 1 2 29\r\n"
            + "bird 0a 0 0 0 1 1 1 dinosaur 1\r\n"
            + "balloon 0a2 0 0 0 1 1 1 hot_air 15\r\n"
            + "airplane 0a3 0 0 0 1 1 1 usair 717 4\r\n"
            + "leaf with 1 objects 0 1 0 1 1 2 29\r\n"
            + "balloon 0x 0 1 1 1 1 1 hot_air 15\r\n"
            + "leaf with 2 objects 1 0 0 1 2 2 28\r\n"
            + "airplane 0y 1 0 1 1 1 1 usair 717 4\r\n"
            + "drone 0z 1 1 0 1 1 1 droners 3\r\n"
            + "32 bintree nodes printed", w.printbintree());
        assertTrue(w.add(new Balloon("0A4", 0, 0, 0, 1, 1, 1, "hot_air",
            15)));
        assertTrue(w.add(new AirPlane("0A5", 0, 0, 0, 1, 1, 1, "USAir",
            717, 4)));
        assertFuzzyEquals("i 0 0 0 1024 1024 1024 0\r\n"
            + "i 0 0 0 512 1024 1024 1\r\n"
            + "i 0 0 0 512 512 1024 2\r\n"
            + "i 0 0 0 512 512 512 3\r\n"
            + "i 0 0 0 256 512 512 4\r\n"
            + "i 0 0 0 256 256 512 5\r\n"
            + "i 0 0 0 256 256 256 6\r\n"
            + "i 0 0 0 128 256 256 7\r\n"
            + "i 0 0 0 128 128 256 8\r\n"
            + "i 0 0 0 128 128 128 9\r\n"
            + "i 0 0 0 64 128 128 10\r\n"
            + "i 0 0 0 64 64 128 11\r\n"
            + "i 0 0 0 64 64 64 12\r\n"
            + "i 0 0 0 32 64 64 13\r\n"
            + "i 0 0 0 32 32 64 14\r\n"
            + "i 0 0 0 32 32 32 15\r\n"
            + "i 0 0 0 16 32 32 16\r\n"
            + "i 0 0 0 16 16 32 17\r\n"
            + "i 0 0 0 16 16 16 18\r\n"
            + "i 0 0 0 8 16 16 19\r\n"
            + "i 0 0 0 8 8 16 20\r\n"
            + "i 0 0 0 8 8 8 21\r\n"
            + "i 0 0 0 4 8 8 22\r\n"
            + "i 0 0 0 4 4 8 23\r\n"
            + "i 0 0 0 4 4 4 24\r\n"
            + "i 0 0 0 2 4 4 25\r\n"
            + "i 0 0 0 2 2 4 26\r\n"
            + "i 0 0 0 2 2 2 27\r\n"
            + "i 0 0 0 1 2 2 28\r\n"
            + "leaf with 5 objects 0 0 0 1 1 2 29\r\n"
            + "bird 0a 0 0 0 1 1 1 dinosaur 1\r\n"
            + "balloon 0a2 0 0 0 1 1 1 hot_air 15\r\n"
            + "airplane 0a3 0 0 0 1 1 1 usair 717 4\r\n"
            + "balloon 0a4 0 0 0 1 1 1 hot_air 15\r\n"
            + "airplane 0a5 0 0 0 1 1 1 usair 717 4\r\n"
            + "leaf with 1 objects 0 1 0 1 1 2 29\r\n"
            + "balloon 0x 0 1 1 1 1 1 hot_air 15\r\n"
            + "leaf with 2 objects 1 0 0 1 2 2 28\r\n"
            + "airplane 0y 1 0 1 1 1 1 usair 717 4\r\n"
            + "drone 0z 1 1 0 1 1 1 droners 3\r\n"
            + "32 bintree nodes printed", w.printbintree());
    }
    /**
     * tests the insert mehtod in bin with edge cases
     *
     * @throws Exception
     */
    public void testBinCollisionMass() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);
        assertNull(w.delete("H2"));
        assertNull(w.delete("s2"));
        assertNull(w.delete("d1"));
        assertNull(w.delete("b1"));
        assertTrue(w.add(new Balloon("0X", 0, 1, 1, 1, 1, 1, "hot_air",
            15)));
        assertTrue(w.add(new AirPlane("0Y", 1, 0, 1, 1, 1, 1, "USAir",
            717, 4)));
        assertTrue(w.add(new Drone("0Z", 1, 1, 0, 1, 1, 1, "Droners",
            3)));
        assertTrue(w.add(new Bird("0A", 0, 0, 0, 1, 1, 1, "Dinosaur",
            1)));
        assertTrue(w.add(new Balloon("23X", 1023, 0, 0, 1, 1, 1, "hot_air",
            15)));
        assertTrue(w.add(new AirPlane("23A", 1023, 1023, 1023, 1, 1, 1, "USAir",
            717, 4)));
        assertNull(w.delete("0z"));
        assertNull(w.delete("s2"));
        assertNull(w.delete("d1"));
        assertNull(w.delete("b1"));
        assertTrue(w.add(new Drone("23Y", 0, 1023, 0, 1, 1, 1, "Droners",
            3)));
        assertTrue(w.add(new Bird("23Z", 0, 0, 1023, 1, 1, 1, "Dinosaur",
            1)));
        assertTrue(w.add(new Balloon("24X", 0, 0, 0, 1024, 1, 1, "hot_air",
            15)));
        assertTrue(w.add(new AirPlane("24Y", 0, 0, 0, 1, 1024, 1, "USAir",
            717, 4)));
        assertTrue(w.add(new Drone("24Z", 0, 0, 0, 1, 1, 1024, "Droners",
            3)));
        assertTrue(w.add(new Bird("24A", 0, 0, 0, 1024, 1024, 1024, "Dinosaur",
            1)));
        assertTrue(w.add(new Balloon("12X", 512, 0, 0, 512, 1, 1, "hot_air",
            15)));
        assertTrue(w.add(new AirPlane("12Y", 0, 512, 0, 1, 512, 1, "USAir",
            717, 4)));
        assertTrue(w.add(new Drone("12Z", 0, 0, 512, 1, 1, 512, "Droners",
            3)));
        assertTrue(w.add(new Bird("12A", 512, 512, 512, 512, 512, 512, "Dinosaur",
            1)));
        assertTrue(w.add(new Balloon("B12", 512, 512, 512, 1, 1, 1, "hot_air",
            15)));
        assertFuzzyEquals(
            "the following collisions exist in the database\r\n"
            + "in leaf node 0 0 0 1 1 2 29\r\n"
            + "bird 0a 0 0 0 1 1 1 dinosaur 1 and bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "bird 0a 0 0 0 1 1 1 dinosaur 1 and balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "bird 0a 0 0 0 1 1 1 dinosaur 1 and airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "bird 0a 0 0 0 1 1 1 dinosaur 1 and drone 24z 0 0 0 1 1 1024 droners 3\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1 and balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1 and airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1 and drone 24z 0 0 0 1 1 1024 droners 3\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15 and airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "balloon 24x 0 0 0 1024 1 1 hot_air 15 and drone 24z 0 0 0 1 1 1024 droners 3\r\n"
            + "airplane 24y 0 0 0 1 1024 1 usair 717 4 and drone 24z 0 0 0 1 1 1024 droners 3\r\n"
            + "in leaf node 0 1 0 1 1 2 29\r\n"
            + "balloon 0x 0 1 1 1 1 1 hot_air 15 and bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "in leaf node 1 0 0 1 1 2 29\r\n"
            + "airplane 0y 1 0 1 1 1 1 usair 717 4 and bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "in leaf node 1 1 0 1 1 2 29\r\n"
            + "drone 0z 1 1 0 1 1 1 droners 3 and bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "in leaf node 0 0 2 2 2 2 27\r\n"
            + "in leaf node 0 2 0 2 2 4 26\r\n"
            + "in leaf node 2 0 0 2 4 4 25\r\n"
            + "in leaf node 0 0 4 4 4 4 24\r\n"
            + "in leaf node 0 4 0 4 4 8 23\r\n"
            + "in leaf node 4 0 0 4 8 8 22\r\n"
            + "in leaf node 0 0 8 8 8 8 21\r\n"
            + "in leaf node 0 8 0 8 8 16 20\r\n"
            + "in leaf node 8 0 0 8 16 16 19\r\n"
            + "in leaf node 0 0 16 16 16 16 18\r\n"
            + "in leaf node 0 16 0 16 16 32 17\r\n"
            + "in leaf node 16 0 0 16 32 32 16\r\n"
            + "in leaf node 0 0 32 32 32 32 15\r\n"
            + "in leaf node 0 32 0 32 32 64 14\r\n"
            + "in leaf node 32 0 0 32 64 64 13\r\n"
            + "in leaf node 0 0 64 64 64 64 12\r\n"
            + "in leaf node 0 64 0 64 64 128 11\r\n"
            + "in leaf node 64 0 0 64 128 128 10\r\n"
            + "in leaf node 0 0 128 128 128 128 9\r\n"
            + "in leaf node 0 128 0 128 128 256 8\r\n"
            + "in leaf node 128 0 0 128 256 256 7\r\n"
            + "in leaf node 0 0 256 256 256 256 6\r\n"
            + "in leaf node 0 256 0 256 256 512 5\r\n"
            + "in leaf node 256 0 0 256 512 512 4\r\n"
            + "in leaf node 0 0 512 512 512 512 3\r\n"
            + "drone 12z 0 0 512 1 1 512 droners 3 and bird 23z 0 0 1023 1 1 1 dinosaur 1\r\n"
            + "drone 12z 0 0 512 1 1 512 droners 3 and bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "drone 12z 0 0 512 1 1 512 droners 3 and drone 24z 0 0 0 1 1 1024 droners 3\r\n"
            + "bird 23z 0 0 1023 1 1 1 dinosaur 1 and bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "bird 23z 0 0 1023 1 1 1 dinosaur 1 and drone 24z 0 0 0 1 1 1024 droners 3\r\n"
            + "in leaf node 0 512 0 512 512 1024 2\r\n"
            + "airplane 12y 0 512 0 1 512 1 usair 717 4 and drone 23y 0 1023 0 1 1 1 droners 3\r\n"
            + "airplane 12y 0 512 0 1 512 1 usair 717 4 and bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "airplane 12y 0 512 0 1 512 1 usair 717 4 and airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "drone 23y 0 1023 0 1 1 1 droners 3 and bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "drone 23y 0 1023 0 1 1 1 droners 3 and airplane 24y 0 0 0 1 1024 1 usair 717 4\r\n"
            + "in leaf node 512 0 0 512 512 1024 2\r\n"
            + "balloon 12x 512 0 0 512 1 1 hot_air 15 and balloon 23x 1023 0 0 1 1 1 hot_air 15\r\n"
            + "balloon 12x 512 0 0 512 1 1 hot_air 15 and bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "balloon 12x 512 0 0 512 1 1 hot_air 15 and balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "balloon 23x 1023 0 0 1 1 1 hot_air 15 and bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "balloon 23x 1023 0 0 1 1 1 hot_air 15 and balloon 24x 0 0 0 1024 1 1 hot_air 15\r\n"
            + "in leaf node 512 512 0 512 512 512 3\r\n"
            + "in leaf node 512 512 512 256 512 512 4\r\n"
            + "bird 12a 512 512 512 512 512 512 dinosaur 1 and bird 24a 0 0 0 1024 1024 1024 dinosaur 1\r\n"
            + "bird 12a 512 512 512 512 512 512 dinosaur 1 and balloon b12 512 512 512 1 1 1 hot_air 15\r\n"
            + "bird 24a 0 0 0 1024 1024 1024 dinosaur 1 and balloon b12 512 512 512 1 1 1 hot_air 15\r\n"
            + "in leaf node 768 512 512 256 512 512 4\r\n"
            + "bird 12a 512 512 512 512 512 512 dinosaur 1 and airplane 23a 1023 1023 1023 1 1 1 usair 717 4\r\n"
            + "airplane 23a 1023 1023 1023 1 1 1 usair 717 4 and bird 24a 0 0 0 1024 1024 1024 dinosaur 1",
            w.collisions());
    }
    /**
     * tests the insert mehtod in bin with edge cases
     *
     * @throws Exception
     */
    public void testBinIntersectMass() throws Exception {
        Random rnd = new Random();
        rnd.setSeed(0xCAFEBEEF);
        WorldDB w = new WorldDB(rnd);
        w.clear();
        assertFuzzyEquals( "the following objects intersect 0 0 0 200 200 200\r\n"
            + "1 nodes were visited in the bintree", w.intersect(0,0,0,200,200,200)); 
        assertTrue(w.add(new Balloon("0X", 0, 1, 1, 1, 1, 1, "hot_air",
            15)));
        assertTrue(w.add(new AirPlane("0Y", 1, 0, 1, 1, 1, 1, "USAir",
            717, 4)));
        assertTrue(w.add(new Drone("0Z", 1, 1, 0, 1, 1, 1, "Droners",
            3)));
        assertFuzzyEquals( "the following objects intersect 0 0 0 200 200 200\r\n"
            + "in leaf node 0 0 0 1024 1024 1024 0\r\n"
            + "balloon 0x 0 1 1 1 1 1 hot_air 15\r\n"
            + "airplane 0y 1 0 1 1 1 1 usair 717 4\r\n"
            + "drone 0z 1 1 0 1 1 1 droners 3\r\n"
            + "1 nodes were visited in the bintree", w.intersect(0,0,0,200,200,200)); 
        assertTrue(w.add(new Bird("0A", 0, 0, 0, 1, 1, 1, "Dinosaur",
            1)));
        assertFuzzyEquals( "the following objects intersect 500 500 500 200 200 200\r\n"
            + "in internal node 0 0 0 1024 1024 1024 0\r\n"
            + "in internal node 0 0 0 512 1024 1024 1\r\n"
            + "in internal node 0 0 0 512 512 1024 2\r\n"
            + "in internal node 0 0 0 512 512 512 3\r\n"
            + "8 nodes were visited in the bintree", w.intersect(500,500,500,200,200,200)); 
        assertTrue(w.add(new Balloon("23X", 1023, 0, 0, 1, 1, 1, "hot_air",
            15)));
        assertTrue(w.add(new AirPlane("23A", 1023, 1023, 1023, 1, 1, 1, "USAir",
            717, 4)));
        assertTrue(w.add(new Drone("23Y", 0, 1023, 0, 1, 1, 1, "Droners",
            3)));
        assertTrue(w.add(new Bird("23Z", 0, 0, 1023, 1, 1, 1, "Dinosaur",
            1)));
        assertTrue(w.add(new Balloon("24X", 0, 0, 0, 1024, 1, 1, "hot_air",
            15)));
        assertTrue(w.add(new AirPlane("24Y", 0, 0, 0, 1, 1024, 1, "USAir",
            717, 4)));
        assertTrue(w.add(new Drone("24Z", 0, 0, 0, 1, 1, 1024, "Droners",
            3)));
        assertTrue(w.add(new Bird("24A", 0, 0, 0, 1024, 1024, 1024, "Dinosaur",
            1)));
        assertTrue(w.add(new Balloon("12X", 512, 0, 0, 512, 1, 1, "hot_air",
            15)));
        assertTrue(w.add(new AirPlane("12Y", 0, 512, 0, 1, 512, 1, "USAir",
            717, 4)));
        assertTrue(w.add(new Drone("12Z", 0, 0, 512, 1, 1, 512, "Droners",
            3)));
        assertTrue(w.add(new Bird("12A", 512, 512, 512, 512, 512, 512, "Dinosaur",
            1)));
        assertTrue(w.add(new Balloon("B12", 512, 512, 512, 1, 1, 1, "hot_air",
            15)));
    }
}