import java.util.ArrayList;

/**
 * The test class TrainTest.
 *
 * @author  Lynn Marshall
 * @version May 2015
 */
import junit.framework.TestCase;

public class TrainTest extends junit.framework.TestCase
{
    
    
    private Train aTrain;
    private Car car1, car2, car3, car4;
    
    /**
     * Default constructor for test class TrainTest
     * Initializes a Train object with four cars 
     * two business and two economy
     */
    public TrainTest()
    {   
        aTrain = new Train();  // Correctly assign the instance variable

        car1 = new Car(1250, true); // Business class
        car2 = new Car(1300, false); // Economy class
        car3 = new Car(1740, false); // Economy class
        car4 = new Car(1260, true); // Business class
    
        // Add cars to the train
        aTrain.addCar(car1);
        aTrain.addCar(car2);
        aTrain.addCar(car3);
        aTrain.addCar(car4);
        
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }
    
    /**
     * Tests creating an empty train.
     */
    public void testCreateEmptyTrain()
    {
        Train emptyTrain = new Train();
        
        /* Verify that a new train has no cars. */
        assertEquals(0, emptyTrain.cars().size());
    }
    
    /**
     * Tests adding cars to the train.
     */
    public void testAddCar()
    {
        ArrayList<Car> cars = aTrain.cars();
        assertEquals(4, cars.size());
        
        
        /* Verify that each car added to the train was placed at
         * the end of the list.
         */
        assertSame(car1, cars.get(0));
        assertSame(car2, cars.get(1));
        assertSame(car3, cars.get(2));
        assertSame(car4, cars.get(3));       
    }
     
    /**
     * Tests issuing tickets.
     */
    public void testIssueTicket()
    {    
        /* Book all the seats in the business-class car. */
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(aTrain.issueTicket(true));
        }
        
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(aTrain.issueTicket(true));
        }
        
        /* Attempt to book one more business-class seat, even
         * though they are all booked.
         */
        assertFalse(aTrain.issueTicket(true));       
 
        ArrayList<Car> cars = aTrain.cars();
        
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(0).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(3).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertFalse(cars.get(1).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertFalse(cars.get(2).seats()[i].isBooked());
        }
        
        /* Book all the seats in the first economy-class car. */
        for (int i = 0; i <Car.ECONOMY_SEATS; i++) {
            assertTrue(aTrain.issueTicket(false));
        }
        
        cars = aTrain.cars();
        
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(0).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(3).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertTrue(cars.get(1).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertFalse(cars.get(2).seats()[i].isBooked());
        }  
        
        /* Book all the seats in the second economy-class car. */
        for (int i = 0; i <Car.ECONOMY_SEATS; i++) {
            assertTrue(aTrain.issueTicket(false));
        }
        
        /* check that all seats are now booked */
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(0).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(3).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertTrue(cars.get(1).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertTrue(cars.get(2).seats()[i].isBooked());
        }  
        
        /* Try to book another business class seat (fails)*/
        assertFalse(aTrain.issueTicket(true));
        
        /* Try to book another economy class seat (fails)*/
        assertFalse(aTrain.issueTicket(false));
    }
    
    /**
     * Tests cancelling tickets.
     */
    public void testCancelTicket()
    {   
        /* Book all the seats in the business-class car. */
        for (int i = 0; i <Car.BUSINESS_SEATS; i++) {
            assertTrue(aTrain.issueTicket(true));
        }
        
        /* Book half the seats in the business-class car. */
        for (int i = 0; i <Car.BUSINESS_SEATS / 2; i++) {
            assertTrue(aTrain.issueTicket(true));
        }
        
        /* Book all the seats in the first economy-class car. */
        for (int i = 0; i <Car.ECONOMY_SEATS; i++) {
            assertTrue(aTrain.issueTicket(false));
        }
        
        /*empty second economy class car*/
        
        ArrayList<Car> cars = aTrain.cars();
        
        assertTrue(aTrain.cancelTicket(1300, 4));
        assertFalse(cars.get(1).seats()[3].isBooked());        
        
        /* Cancel ticket in a non-existent car. */
        assertFalse(aTrain.cancelTicket(1500, 7));
        
        /* Cancel ticket in a non-existent seat. */
        assertFalse(aTrain.cancelTicket(1300, 54));
        
        /* Cancel ticket for a seat that hasn't been booked. */
        assertFalse(aTrain.cancelTicket(1740, 21));
        assertFalse(cars.get(2).seats()[20].isBooked());        
        
        /* Attempt to cancel the same ticket twice. */
        assertTrue(aTrain.cancelTicket(1250, 11));
        assertFalse(cars.get(0).seats()[10].isBooked());
        
        assertFalse(aTrain.cancelTicket(1250, 11));  
        assertFalse(cars.get(0).seats()[10].isBooked()); 
        
        //cancel one of the booked seats near the front half of the second business car
        assertTrue(aTrain.cancelTicket(1260, 2)); 
        assertFalse(cars.get(3).seats()[1].isBooked());
        
        //attempt to cancel one of the booked seats near the back of the second business car
        assertFalse(aTrain.cancelTicket(1260, Car.BUSINESS_SEATS - 2)); 
        assertFalse(car4.seats()[Car.BUSINESS_SEATS - 3].isBooked());
    }
    
    /**
     * test book cancel ticket 
     */
      public void testBookCancelTicket()
    {        
        /* Book all the seats in the business-class car. */
        for (int i = 0; i <Car.BUSINESS_SEATS; i++) {
            assertTrue(aTrain.issueTicket(true));
        }
        
        /* Book half the seats in the business-class car. */
        for (int i = 0; i <Car.BUSINESS_SEATS / 2; i++) {
            assertTrue(aTrain.issueTicket(true));
        }
        
        /* Book all the seats in the first economy-class car. */
        for (int i = 0; i <Car.ECONOMY_SEATS; i++) {
            assertTrue(aTrain.issueTicket(false));
        }
        
        /*empty second economy class car*/
        ArrayList<Car> cars = aTrain.cars();
        //Cancel any three tickets from economy class car
        assertTrue(aTrain.cancelTicket(1300, 5));  
        assertTrue(aTrain.cancelTicket(1300, 12)); 
        assertTrue(aTrain.cancelTicket(1300, 20));
        
        //Verify seats are unbooked 
        
        assertFalse(cars.get(1).seats()[4].isBooked());  
        assertFalse(cars.get(1).seats()[11].isBooked()); 
        assertFalse(cars.get(1).seats()[19].isBooked()); 
        
        //Book seats
        assertTrue(aTrain.issueTicket(false));  //seat 5
        assertTrue(aTrain.issueTicket(false));  //seat 12
        assertTrue(aTrain.issueTicket(false));  //seat 20
        assertTrue(aTrain.issueTicket(false));  //seat 1
        
        //Verify right seats were booked 
        assertTrue(cars.get(1).seats()[4].isBooked());  // Seat 5
        assertTrue(cars.get(1).seats()[11].isBooked()); // Seat 12
        assertTrue(cars.get(1).seats()[19].isBooked()); // Seat 20
        assertTrue(cars.get(2).seats()[0].isBooked());  //seat 1
        
        
        assertTrue(aTrain.cancelTicket(1250, 10));  
        assertTrue(aTrain.cancelTicket(1260, 2)); 
        assertTrue(aTrain.cancelTicket(1260, 3));
        
        
        assertFalse(cars.get(0).seats()[9].isBooked());  
        assertFalse(cars.get(3).seats()[1].isBooked()); 
        assertFalse(cars.get(3).seats()[2].isBooked()); 
        
        
        assertTrue(aTrain.issueTicket(true));  //seat 10 car 1
        assertTrue(aTrain.issueTicket(true));  //seat 2 car 2
        assertTrue(aTrain.issueTicket(true));  //seat 3 car 2
        assertTrue(aTrain.issueTicket(true));  //seat 16 car 2
        
    }
    
}

