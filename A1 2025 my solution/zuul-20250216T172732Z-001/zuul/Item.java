/** 
 * Class Item. The Item class represents an object that can be placed in a room
 * within the "World of Zuul" adventure game.
 * 
 * Each item has a description and a weight, which helps define its 
 * characteristics in the game world.
 * 
 * Items can be added to rooms and interacted with by the player.
 * 
 * @author  Khalid Kayyem, 101277502
 * @version 15-03-2025
 */


public class Item {
    private String name;
    private String description;
    private double weight;

    /** 
     * Constructs an Item with a given description and weight.
     * 
     * 
     * @param name The name of the item
     * @param description A short description of the item.
     * @param weight The weight of the item in kilograms.
     */
    public Item(String description, double weight) {
        this.description = description;
        this.weight = weight;
        this.name = name;
    }
    
    /**
     * getName this method returns the name
     * 
     * @return the name of the item
     */
    public String getName(){
        return name;
    }
    
    /**
     * Returns a description of the item, including its weight.
     * 
     * @return A string describing the item, its name, and its weight.
     */
    public String getDescription() {
        return description + name + ": " + " (Weight: " + weight + " kg)";
    }
}
