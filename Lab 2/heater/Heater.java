/**
 * A Heater models a simple space-heater. The operations provided by a Heater
 * object are:
 * 1. Increase and decrease the temperature setting by a set amount.
 * 2. Return the current temperature setting.
 * 3. Change the set amount by which the temperature is increased and lowered.
 * 
 * @author L.S. Marshall, SCE, Carleton University
 * (incomplete implementation for SYSC 2004 Lab 2)
 * @author Khalid Kayyem.
 * @version 1.03 January 16, 2025
 */
public class Heater
{
    /** The temperature setting that the heater should maintain. */
    private int temperature;
    
    /** The temperature setting for a newly created heater. */
    private static final int INITIAL_TEMPERATURE = 15;
    
    /** 
     * The amount by which the temperature setting is raised/lowered when
     * warmer() and cooler() are invoked.
     */
     private int increment;
    
     /** The maximum temperature the heater can reach */
     
     private int max;
     
     /** The minimum temperature the heater can reach */
     
     private int min;
     
    /** 
     * The default amount by which the temperature setting is 
     * increased when warmer() is invoked and decreased when cooler()
     * is invoked.
     */
    private static final int DEFAULT_INCREMENT = 5;
    
    /**
     * Constructs a new Heater with an initial temperature setting of 15
     * degrees, and which increments and decrements the temperature
     * setting in increments of 5 degrees.
     */
    public Heater()
    {
        temperature = INITIAL_TEMPERATURE;
        increment = DEFAULT_INCREMENT;
        min = 0;
        max = 100;
    }
 
    /**
     * Write an appropriate comment here.
     */    
    public Heater(int minTemp, int maxTemp)
    {
        temperature = INITIAL_TEMPERATURE;
        increment = DEFAULT_INCREMENT;
        min = minTemp;
        max = maxTemp;
    }

    /**
     * Returns the current temperature.
     */    
    public int temperature()
    {
        return temperature;
    }
    
    /**
     * Increase the heaters temperature by the value in increment.
     */
    public void warmer()
    {
        int new_temp = temperature + increment;
        if(new_temp <= max){
            temperature = new_temp;
        }
    }

    /**
     * Decreases the heaters temperature by the value stored in increment.
     */    
    public void cooler()
    { 
        int new_temp = temperature - increment;
        if(new_temp >= min){
            temperature = new_temp;
        }    
    }
    
    /**
     * Change value of increment when argument > 0.
     */    
    public void setIncrement(int newIncrement)
    { 
        if(newIncrement > 0){
            increment = newIncrement;
        }
    }
}
