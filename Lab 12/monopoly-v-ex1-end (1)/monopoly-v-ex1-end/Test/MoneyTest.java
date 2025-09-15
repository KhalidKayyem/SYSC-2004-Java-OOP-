package Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import Monopoly.Money;

public class MoneyTest {

    private Money money;

    @Before
    public void setUp() {
        System.out.println("Setting up before each test");
        money = new Money(10, 50); // Example initial Money object
    }

    @After
    public void tearDown() {
        System.out.println("Tearing down after each test");
        money = null;
    }

    @Test
    public void testIsEqualTo() {
        System.out.println("Testing isEqualTo()");
        Money amount1 = new Money(10, 50);
        Money amount2 = new Money(5, 75);
        Money amount3 = new Money(10, 51);

        assertTrue(money.isEqualTo(amount1));
        assertFalse(money.isEqualTo(amount2));
        assertFalse(money.isEqualTo(amount3));
    }

    @Test
    public void testPlus() {
        System.out.println("Testing plus()");
        Money amount1 = new Money(5, 25);
        Money expected1 = new Money(15, 75);
        Money result1 = money.plus(amount1);
        assertTrue(result1.isEqualTo(expected1));

        Money amount2 = new Money(-5, -75);
        Money expected2 = new Money(4, 75);
        Money result2 = money.plus(amount2);
        assertTrue(result2.isEqualTo(expected2));
    }

    @Test
    public void testMinus() {
        System.out.println("Testing minus()");
        Money amount1 = new Money(5, 25);
        Money expected1 = new Money(5, 25);
        Money result1 = money.minus(amount1);
        assertTrue(result1.isEqualTo(expected1));

        Money amount2 = new Money(15, 75);
        Money expected2 = new Money(-5, -25);
        Money result2 = money.minus(amount2);
        assertTrue(result2.isEqualTo(expected2));
    }

    public void init() {
        System.out.println("init() method");
    }

    public void close() {
        System.out.println("close() method");
    }
}