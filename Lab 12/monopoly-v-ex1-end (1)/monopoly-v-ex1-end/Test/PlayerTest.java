package Test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import Monopoly.Board;
import Monopoly.Dice;
import Monopoly.Player;

public class PlayerTest {

    private Player player;
    private Board board;
    private Dice dice;

    @Before
    public void setUp() {
        System.out.println("Setting up before each test");
        board = new Board(); // Assuming Board constructor sets up the board correctly.
        dice = new Dice(); // Assuming Dice constructor sets up the dice correctly.
        player = new Player("John Doe", board, dice);
    }

    @After
    public void tearDown() {
        System.out.println("Tearing down after each test");
        player = null;
    }

    // The init() method is used to perform initializations before the test starts.
    @Before
    public void init() {
        System.out.println("Initializing PlayerTest");
    }

    // The close() method is used to perform clean-up activities after the test completes.
    @After
    public void close() {
        System.out.println("Closing PlayerTest");
    }
    
    @Test
    public void testPlayerSetup() {
        System.out.println("Testing Player setup");
        assertEquals("The player's name should be 'John Doe'.", "John Doe", player.getName());
        assertNotNull("The board should not be null.", player.getBoard());
        assertNotNull("The dice should not be null.", player.getDice());
        assertEquals("The player's initial location should be the board's starting square.", board.startingSquare(), player.getLocation());
    }

}
