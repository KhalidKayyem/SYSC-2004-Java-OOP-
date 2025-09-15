package Test;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import Monopoly.Square;
import Monopoly.Money;

public class SquareTest {
    
    @Before
    public void setUp() {
        System.out.println("Setting up before each test");
    }

    @After
    public void tearDown() {
        System.out.println("Tearing down after each test");
    }

    public void init() {
        System.out.println("Initializing test class");
    }

    public void close() {
        System.out.println("Closing test class");
    }

    @Test
    public void testSquareSetup() {
        Square goSquare = new Square(Square.GO, "Go", 1);
        assertEquals(Square.GO, goSquare.type());
        assertEquals("Go", goSquare.name());
        assertEquals(200, goSquare.salary().dollars());
        assertEquals(0, goSquare.salary().cents());

        Square taxSquare = new Square(Square.INCOME_TAX, "Income Tax", 4);
        assertEquals(Square.INCOME_TAX, taxSquare.type());
        assertEquals("Income Tax", taxSquare.name());
        assertEquals(200, taxSquare.maximumTax().dollars());
        assertEquals(0, taxSquare.maximumTax().cents());

        Square lotSquare = new Square(Square.LOT, "Boardwalk", 39);
        assertEquals(Square.LOT, lotSquare.type());
        assertEquals("Boardwalk", lotSquare.name());
        lotSquare.setPrice(new Money(400, 0));
        assertEquals(400, lotSquare.price().dollars());
        assertEquals(0, lotSquare.price().cents());

        Square emptySquare = new Square(Square.EMPTY, "Free Parking", 20);
        assertEquals(Square.EMPTY, emptySquare.type());
        assertEquals("Free Parking", emptySquare.name());
    }
}
