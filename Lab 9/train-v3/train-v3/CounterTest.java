/**
 * The test class CounterTest.
 *
 * @author  Khalid Kayyem
 * @version 14-03-2025
 */

public class CounterTest extends junit.framework.TestCase
{
    private RollOverCounter rollOverCounter;
    private LimitedCounter limitedCounter;

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    protected void setUp()
    {
        rollOverCounter = new RollOverCounter(1, 10);
        limitedCounter = new LimitedCounter(1, 10);
    }

    /**
     * Tests the original (Lab 8) RollOverCounter methods.
     */
    public void testAllRollOverCounterMethods()
    {    
        assertEquals(1, rollOverCounter.minimumCount());
        assertEquals(10, rollOverCounter.maximumCount());
        assertEquals(1, rollOverCounter.count());        
        
        assertTrue(rollOverCounter.isAtMinimum());
        assertFalse(rollOverCounter.isAtMaximum());

        // Count up to max
        for (int i = 1; i < 10; i++) {
            rollOverCounter.countUp();
        }
        assertTrue(rollOverCounter.isAtMaximum());

        // Rolling over (10 -> 1)
        rollOverCounter.countUp();
        assertEquals(1, rollOverCounter.count());

        // Reset
        rollOverCounter.countUp();
        rollOverCounter.countUp();
        rollOverCounter.reset();
        assertEquals(1, rollOverCounter.count());
    }    

    /**
     * Tests all LimitedCounter methods.
     */
    public void testAllLimitedCounterMethods()
    {
        assertEquals(1, limitedCounter.minimumCount());
        assertEquals(10, limitedCounter.maximumCount());
        assertEquals(1, limitedCounter.count());        
        
        assertTrue(limitedCounter.isAtMinimum());
        assertFalse(limitedCounter.isAtMaximum());

        // Count up to max
        for (int i = 1; i < 10; i++) {
            limitedCounter.countUp();
        }
        assertTrue(limitedCounter.isAtMaximum());

        // Try exceeding max (should stay at 10)
        limitedCounter.countUp();
        assertEquals(10, limitedCounter.count());

        // Reset
        limitedCounter.reset();
        assertEquals(1, limitedCounter.count());
    }

    /**
     * Tests new methods in RollOverCounter.
     */
    public void testNewRollOverCounterMethods()
    {
        // Test setToMaximum()
        rollOverCounter.setToMaximum();
        assertEquals(10, rollOverCounter.count());

        // Test countDown() from max to min
        for (int i = 10; i > 1; i--) {
            rollOverCounter.countDown();
            assertEquals(i - 1, rollOverCounter.count());
        }

        // Ensure countDown rolls back to max
        rollOverCounter.countDown();
        assertEquals(10, rollOverCounter.count());
    }

    /**
     * Tests new methods in LimitedCounter.
     */
    public void testNewLimitedCounterMethods()
    {
        // Test setToMaximum()
        limitedCounter.setToMaximum();
        assertEquals(10, limitedCounter.count());

        // Test countDown() from max to min
        for (int i = 10; i > 1; i--) {
            limitedCounter.countDown();
            assertEquals(i - 1, limitedCounter.count());
        }

        // Ensure countDown stops at min (1)
        limitedCounter.countDown();
        assertEquals(1, limitedCounter.count());
    }
}