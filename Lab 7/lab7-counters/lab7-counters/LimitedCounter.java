/**
 * LimitedCounter The LimitedCounter class extends Counter and increments normally 
 * until it reaches its maximum value.
 * @author Khalid Kayyem
 * @version 03-02-2025
 */
public class LimitedCounter extends Counter
{
    /**
     * Constructs a new LimitedCounter whose current count is
     * initialized to DEFAULT_MINIMUM, and which counts between
     * DEFAULT_MINIMUM and DEFAULT_MAXIMUM, inclusive.
     */
    public LimitedCounter()
    {
        super();
    }

    /**
     * Constructs a new LimitedCounter whose current count is
     * initialized to minCount, and which counts between
     * minCount and maxCount, inclusive.
     */
    public LimitedCounter(int minCount, int maxCount)
    {
        super(minCount, maxCount);
    }

    /**
     * countUp Increments the counter by 1, unless it is already at its maximum value.
     * If the counter is at maximum, it remains unchanged.
     */
    public void countUp()
    {
        // If we've reached the maximum count, invoking this
        // method doesn't change the state of the counter.
        if (!this.isAtMaximum()) {
            this.incrementCount();
        }
    }
}
