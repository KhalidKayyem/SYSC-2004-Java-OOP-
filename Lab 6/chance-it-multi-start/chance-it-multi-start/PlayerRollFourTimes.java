
/**
 * This class models a player that will always roll the dice four times.
 * @author (Khalid Kayyem)
 * @version (Valentines day)
 */
public class PlayerRollFourTimes extends Player
{
    /**
     * Constructor for objects of class PlayerRollFourTimes
     * @param dice the pair of dice
     * @param name the players name
     */
    public PlayerRollFourTimes(Dice dice, String name)
    {
        super(dice, name);
    }

    /**
     * An example of a method - replace this comment with your own
     * @return true when the player decides to end the turn, false otherwise 
     */
    protected boolean stopRolling() {
        return numRolls==4;
    }
}
