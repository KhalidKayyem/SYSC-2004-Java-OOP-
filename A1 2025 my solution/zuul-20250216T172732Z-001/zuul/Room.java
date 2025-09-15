import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Class room, represents a location in the "World of Zuul" adventure game.
 * 
 * A Room is a specific area within the game world. Each room can have 
 * multiple exits leading to other rooms, allowing the player to move between them. 
 * The room also stores references to any items present within it.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * 
 * @author Lynn Marshall
 * @version October 21, 2012
 * 
 * @author Khalid Kayyem, 101277502
 * @version 16-02-2025
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private ArrayList<Item> items; // List to store items in the room

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * 
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new ArrayList<>(); // Initialize the item list
    }

    /**
     * Define an exit from this room.
     * 
     * @param direction The direction of the exit
     * @param neighbour The room to which the exit leads
     */
    public void setExit(String direction, Room neighbour) 
    {
        exits.put(direction, neighbour);
    }

    /**
     * Returns a short description of the room, i.e. the one that
     * was defined in the constructor
     * 
     * @return a string, The short description of the room
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Returns a detailed long description of the room, including its exits and any items present.
     * The format of the description is:
     * 
     *     You are in [room description].
     *     Exits: [available exits]
     *     Items in this room:
     *     - [item 1]
     *     - [item 2]
     *     
     * If there are no items in the room, it will state "No items here."
     *     
     * @return A string containing the full description of the room, including exits and items.
     */
    public String getLongDescription() {
        String itemDescriptions = "No items here.";

        if (!items.isEmpty()) {
            itemDescriptions = "Items in this room:";
            for (Item item : items) {
                itemDescriptions += "\n- " + item.getDescription();
            }
        }

        return "You are in " + description + ".\n" + getExitString() + "\n" + itemDescriptions;
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * 
     * @return a String Details of the room's exits
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * The method getExit returns the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * 
     * @param direction The exit's direction
     * @return The room in the given direction
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }

    /**
     * Adds an item to the room.
     * @param item The item to be added.
     */
    public void addItem(Item item) {
        items.add(item);
    }
}

