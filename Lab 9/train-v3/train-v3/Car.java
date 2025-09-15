/**
 * car models a list of seats in the train.
 *
 * @author Khalid Kayyem
 * @version 1.3 Febuary 04, 2025
 */
public class Car
{
    /** This car's identifier. */
    private int id;
   
    /**
     * true == this car is a business-class car,
     * false == this car is an economy-class car.
     */
    private boolean businessClass;
    
    /** The cost of a business class seat, in dollars. */
    public static final double BUSINESS_SEAT_COST = 125.0;
    
    /** The cost of an economy class seat, in dollars. */
    public static final double ECONOMY_SEAT_COST = 50.0;    
  
    /** The number of seats in a business class car. */
    public static final int BUSINESS_SEATS = 30;   
    
    /** The number of seats in an economy class car. */
    public static final int ECONOMY_SEATS = 40;   
   
    /** The list of this car's seats. */
    private Seat[] seats;
   
    /**
     * Constructs a new Car object with the specified id.
     * If parameter isBusinessClass is true, the car is a business-class
     * car. If parameter isBusinessClass is false, the car is an
     * economy-class car.
     * @param carID the unique identifier for the car.
     * @param isBusinessClass True if this is a business-class car, false if its an economy-class car.
     */
    public Car(int carId, boolean isBusinessClass)
    {
        id = carId;
        businessClass = isBusinessClass;
        double cost; 
        int num_seats;
        if(businessClass){
            seats = new Seat[30];
            cost = 125.0;
            num_seats = 30;
        }
        else{
            seats = new Seat[40];
            cost = 50.0;
            num_seats = 40;
        }
        for(int i = 0; i < num_seats; i++){
            seats[i] = new Seat(i+1, cost); 
        }
        // assign all the seats in the array to have a seat number and a cost.
    }

    /**
     * Returns this car's list of seats. This method is intended for 
     * testing purposes only, and should not be called by other objects,
     * as it may be removed from the final version of this class.
     * 
     * @return The seats in this car, an array of Seat objects.
     */
    public Seat[] seats()
    {
        return seats;
    }
 
    /** 
     * Returns true if this is a business-class car,
     * false if this is an economy-class car.
     * @return True if business-class, false if not.
     */
    public boolean isBusinessClass()
    {
        if(businessClass){
            return true;
        }
        return false;
    }
 
    /**
     * Returns the id of this car.
     * @return the cars unique identifier
     */
    public int id()
    {
        return id;
    }
    
    /**
     * Attempts to book a seat. If successful, this method prints a 
     * ticket and returns true.
     * If no seats are available, this method returns false.
     * @return True if a seat was booked, false otherwirse.
     */
    public boolean bookNextSeat()
    {
        for(int i = 0; i < seats.length; i++){
            if(!seats[i].isBooked()){
                    seats[i].book();
                    return true;
                }
        }
        return false;
    }

    /** 
     * Cancels the booking for the specified seat, which must be between
     * 1 and the maximum number of seats in the car.
     * If the seat number is valid and if the seat has been reserved, this
     * method cancels the booking for that seat and returns true. 
     * If the seat number is not valid, this method returns false. 
     * If the seat number is valid, but the seat has not been reserved, 
     * this method returns false.
     * @param seatNo the seatNo to cancel
     * @return True if it was cancelled, false otherwise.
     */
    public boolean cancelSeat(int seatNo)
    {
        if(seatNo >= 1 && seatNo <= seats.length){
            return seats[seatNo - 1].cancelBooking();
        }
        return false;
    }    
}
