package Test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import Monopoly.Dice;

public class DiceTest {

    private Dice dice;

    @Before
    public void setUp() {
        System.out.println("Setting up before each test");
        dice = new Dice();
    }

    @After
    public void tearDown() {
        System.out.println("Tearing down after each test");
        dice = null;
    }

    public void init() {
        System.out.println("Initializing test class");
    }

    public void close() {
        System.out.println("Closing test class");
    }

    @Test
    public void testRollRange() {
        System.out.println("Testing roll range");
        for (int i = 0; i < 1000; i++) {
            int rollResult = dice.roll();
            assertTrue("Roll result is out of range: " + rollResult, rollResult >= 2 && rollResult <= 12);
        }
    }
}
