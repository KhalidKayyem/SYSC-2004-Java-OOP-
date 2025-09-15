/**
 * Beamer class. 
 * The Beamer is an advanced item that allows players to store a location (room)
 * and later teleport back to it. It must first be charged in a room before it
 * can be used. Once fired, the Beamer resets and must be recharged to be used again.
 *
 * This class extends the item class, inheriting its attributes and behaviors.
 * It introduces new functionality related to charging and firing the Beamer.
 *
 * @author  Khalid Kayyem
 * @version 16-03-2025
 */
public class Beamer extends Item {
    private Room chargedRoom;
    private boolean isCharged;

    /**
     * Beamer. Constructs a Beamer with the specified name, description, and weight.
     * Calls the superclass constructor to initialize inherited attributes.
     * Sets the charged room to null and initializes the Beamer as not charged.
     * 
     * @param name The beamers name
     * @param description a brief description of the beamer
     * @param weight the weight of the beamer, in kg
     */
    public Beamer(String name, String description, double weight) {
        super(name, description, weight);
        this.chargedRoom = null;
        this.isCharged = false;
    }

    /**
     * charge. This method charges the beamer with the current room.
     * If the Beamer is already charged, the player must fire it before charging again.
     *
     * @param room The room to charge the beamer with.
     */
    public void charge(Room room) {
        if (isCharged) {
            System.out.println("The beamer is already charged! Fire it before charging again.");
        } else {
            this.chargedRoom = room;
            this.isCharged = true;
            System.out.println("The beamer has been charged in " + room.getShortDescription());
        }
    }

    /**
     * fire. This method fires the beamer, teleporting the player back to the charged room.
     * If the Beamer is not charged, it cannot be fired.
     * Once fired, the Beamer resets and must be recharged before being used again.
     *
     * @return The room the player is teleported to, or null if not charged.
     */
    public Room fire() {
        if (!isCharged) {
            System.out.println("The beamer is not charged! You must charge it first.");
            return null;
        }
        System.out.println("Firing the beamer! You are teleported back to " + chargedRoom.getShortDescription());
        Room temp = chargedRoom;
        this.chargedRoom = null; // Reset charge after use
        this.isCharged = false;
        return temp;
    }

    /**
     * isCharged. this method checks if the beamer is charged.
     *
     * @return true if charged, false otherwise.
     */
    public boolean isCharged() {
        return isCharged;
    }
}
