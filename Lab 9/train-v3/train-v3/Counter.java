
/**
 * Class Counter this class represents a simple counting mechanism with a defined range.
 * It maintains a minimum and maximum count value, provides methods to retrieve and modify the count,
 * and includes functionality to reset and increment the count.
 *
 * @author (Khalid Kayyem)
 * @version (02-03-2025)
 */
public abstract class Counter
{
    /** The current value of this counter. */
    private int count;

    /** The minimum value of this counter. */
    private int minimumCount;

    /** The maximum value of this counter. */
    private int maximumCount;

    /** The default minimum value of this counter. */
    private static final int DEFAULT_MINIMUM = 0;

    /** The default maximum value of this counter. */
    private static final int DEFAULT_MAXIMUM = 999;

    /**
     * Constructs a new Counter whose current count is
     * initialized to DEFAULT_MINIMUM, and which counts between
     * DEFAULT_MINIMUM and DEFAULT_MAXIMUM, inclusive.
     */
    public Counter()
    {
        minimumCount = DEFAULT_MINIMUM;
        maximumCount = DEFAULT_MAXIMUM;
        count = minimumCount;
    }

    /**
     * Constructs a new Counter whose current count is
     * initialized to minCount, and which counts between
     * minCount and maxCount, inclusive.
     * 
     * @param minCount Amount to set as lower limit of the counter.
     * @param maxCount Amount to set as upper limit of the counter.
     */
    public Counter(int minCount, int maxCount)
    {
        minimumCount = minCount;
        maximumCount = maxCount;
        count = minimumCount;
    }

    /**
     * The maximum counter amount.
     * 
     * @return Returns the maximum value of this counter.
     */
    public int maximumCount()
    {
        return maximumCount;
    }

    /**
     * 
     * The minumum counte amount.
     * 
     * @return Returns the minimum value of this counter.
     */
    public int minimumCount()
    {
        return minimumCount;
    }

    /**
     * Return the current amount of the counter.
     * 
     * @return Returns this counter's current count.
     */
    public int count()
    {
        return count;
    }

    /**
     * 
     * @return Returns true if this counter is at its minimum value.
     */
    public boolean isAtMinimum()
    {
         return (count == minimumCount);
    }

    /**
     * @return Returns true if this counter is at its maximum value.
     */
    public boolean isAtMaximum()
    {
        return (count == maximumCount);
    }

    /**
     * Resets this counter to its minimum value.
     */
    public void reset()
    {
        count = minimumCount;
    }

    /**
    * Increment this counter by 1.
    */
    public void countUp(){
        count++;
    }
    
    /**
    * Decrement this counter by 1.
    */
     public void decrementCount()
     {
         count--;
     }
     
    /**
    * Sets the counter to its maximum value.
    */
     public void setToMaximum()
     {
         count = maximumCount;
     }
    
    /**
     * The abstract class to of method countDown to be implemented in sub-classes.
     */
    public abstract void countDown();
}