
/**
 * Write a description of class PlayerTwenty here.
 *
 * @author (Khalid Kayyem)
 * @version (Valentines day)
 */
public class PlayerTwenty extends Player {
    
    /**
     * Constructor for objects of class PlayerTwenty
     * @param dice the dice used
     * @param name the player name
     */
    public PlayerTwenty(Dice dice, String name)
    {
        super(dice, name);
    }

    /**
     * An example of a method - replace this comment with your own
     * @return true when the player ends the turn, else false
     */
    protected boolean stopRolling(){
        return score() >= 20;
    }
}
