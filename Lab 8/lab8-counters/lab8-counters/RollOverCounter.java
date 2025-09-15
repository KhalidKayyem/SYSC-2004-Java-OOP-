/**
 * RollOverCounter this class extends Counter and resets to its minimum 
 * value when it reaches the maximum.
 * Instead of stopping at the maximum, this counter "rolls over" to 
 * the minimum count, maintaining continuous counting.
 * @author Khalid Kayyem
 * @version 03-02-2025
 */
public class RollOverCounter extends Counter
{
    /**
     * Constructs a new RollOverCounter whose current count is
     * initialized to DEFAULT_MINIMUM, and which counts between
     * DEFAULT_MINIMUM and DEFAULT_MAXIMUM, inclusive.
     */
    public RollOverCounter()
    {
        super();

    }

    /**
     * Constructs a new RollOverCounter whose current count is
     * initialized to minCount, and which counts between
     * minCount and maxCount, inclusive.
     */
    public RollOverCounter(int minCount, int maxCount)
    {
        super(minCount, maxCount);

    }
    
    /**
     * countUp this method Increments the counter by 1. 
     * If the counter reaches its maximum value, it resets to the minimum value
     */
    public void countUp()
    {
        // If we've reached the maximum count, invoking this
        // method rolls the counter over to its minimum value.
        super.countUp();
        if (this.isAtMaximum()) {
            this.reset();
        } else {
            this.countUp();
        }
    }
    
    /**
     * Decrement the counter by 1. If we have reached the minimum count,
     * invoking this method rolls the counter over to its maximum value.
     */
    public void countDown()
    {
     if (isAtMinimum()) {
     setToMaximum();
     } else {
     super.decrementCount();
     }
    }
}
