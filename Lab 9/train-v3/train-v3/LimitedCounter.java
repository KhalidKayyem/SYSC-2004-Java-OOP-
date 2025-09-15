/**
 * LimitedCounter The LimitedCounter class extends Counter and increments normally 
 * until it reaches its maximum value.
 * @author Khalid Kayyem
 * @version 03-02-2025
 */
public class LimitedCounter extends Counter
{
    

    /**
     * Constructs a new RollOverCounter whose current count is
     * initialized to DEFAULT_MINIMUM, and which counts between
     * DEFAULT_MINIMUM and DEFAULT_MAXIMUM, inclusive.
     */
    public LimitedCounter()
    {
        super();
    }

    /**
     * Constructs a new RollOverCounter whose current count is
     * initialized to minCount, and which counts between
     * minCount and maxCount, inclusive.
     * 
     * @param minCount Amount to set as lower limit.
     * @param maxCount Amount to set as upper limit.
     */
    public LimitedCounter(int minCount, int maxCount)
    {
        super(minCount, maxCount);
    }

    
    /**
     * Increment this counter by 1. If the counter is at maximum count, then do nothing. If not increment by 1.
     * To reset, the reset button should be pressed.
     */
    public void countUp()
    {
        // If we've reached the maximum count, invoking this
        // method rolls the counter over to its minimum value.
        if (!isAtMaximum()) {
            super.countUp();
        }
    }
    
    /**
     * Involve the decrement count function.
     */
    public void countDown(){
        if (!isAtMinimum()) {
            decrementCount();
        }
    }
}
