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
//            + "airplane a1 0 210 100 520 342 30 usair 717 4 and "
//            + "balloon b1 100 11 101 21 12 31 hot_air 15\r\n"
            + "in leaf node 0 512 0 512 512 1024 2\r\n"
//            + "rocket enterprise 0 100 20 10 50 50 5000 9929 and bird pterodactyl 0 "
//            + "100 20 10 50 50 dinosaur 1\r\n"
            + "in leaf node 512 0 0 512 1024 1024 1\r\n",
//            + "rocket enterprise 0 100 20 10 50 50 5000 9929 and bird pterodactyl 0 "
//            + "100 20 10 50 50 dinosaur 1\r\n",
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
//        +"airplane a1 0 210 100 520 342 30 usair 717 4\r\n"
        +"balloon b1 100 11 101 21 12 31 hot_air 15\r\n"
        +"bird b2 0 100 20 103 50 530 dinosaur 1\r\n"
        //+"in leaf node 0 512 0 512 512 1024 2\r\n"
//        +"drone d1 100 1010 101 924 2 900 droners 3\r\n"
        //+"in leaf node 512 0 0 512 1024 1024 1\r\n"
        +"3 nodes were visited in the bintree\r\n",
        w.intersect(20, 20, 20, 104, 104, 104));   
        assertFuzzyEquals(
        "The following objects intersect (500 520 900 104 104 104):\r\n" 
        + "in internal node 0 0 0 1024 1024 1024 0\r\n"
        + "in internal node 0 0 0 512 1024 1024 1\r\n"
        +"in leaf node 0 512 0 512 512 1024 2\r\n"
//        +"airplane a1 0 210 100 520 342 30 usair 717 4\r\n"
//        +"balloon b1 100 11 101 21 12 31 hot_air 15\r\n"
//        +"bird b2 0 100 20 103 50 530 dinosaur 1\r\n"
        //+"in leaf node 0 512 0 512 512 1024 2\r\n"
//        +"drone d1 100 1010 101 924 2 900 droners 3\r\n"
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
}