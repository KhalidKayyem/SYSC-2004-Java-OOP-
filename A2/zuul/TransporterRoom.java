import java.util.Random;
import java.util.ArrayList;

/**
 * Class TranspoterRoom. 
 * a special type of room that teleports the player to a random room upon exit.
 * 
 * A TransporterRoom overrides the default exit behavior. Instead of moving to a fixed room,
 * it selects a random room from all available rooms. This adds an unpredictable element to the game.
 *
 * This class extends the Room class and inherits its attributes and behaviors.
 * It introduces the concept of random teleportation upon attempting to exit.
 * 
 * @author Khalid Kayyem, 101277502
 * @version 16-03-2025
 */
public class TransporterRoom extends Room
{
    private Random random;
    
    /**
     * Constructs a TransporterRoom with the given description.
     * 
     * The description specifies what the room looks like. The TransporterRoom behaves like a normal 
     * room except that it teleports the player to a random room when they try to leave.
     * 
     * @param description A brief description of the room. 
     */
    public TransporterRoom(String description)
    {
        super(description);
        this.random = new Random();
    }
    
    /**
     * getExit. This method does not return a fixed exit.
     * Instead, it randomly selects a room from the available list of rooms.
     * 
     * @param direction The direction the player is trying to go.
     * @return a randomly chosen room
     */
    @Override
    public Room getExit(String direction)
    {
        return findRandomRoom();
    }

    /**
     * findRandomRoom. This method selects a random room from all available rooms.
     *
     * @return A randomly chosen room.
     */
    private Room findRandomRoom()
    {
        ArrayList<Room> allRooms = Room.getAllRooms();
        if (allRooms.size() <= 1) {
            return this;  // If there's only one room, return itself
        }

        Room randomRoom;
        do {
            randomRoom = allRooms.get(random.nextInt(allRooms.size()));
        } while (randomRoom == this);  // Ensure it doesn't teleport to itself

        return randomRoom;
    }
}
