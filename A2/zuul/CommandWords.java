/**
 * Commandwords class. The Commandwords class is part of the "World of Zuul" application, 
 * a simple text-based adventure game.
 * 
 * It maintains an enumeration of all valid command words used in the game 
 * and is responsible for identifying and validating player commands as they are entered.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * 
 * @author Lynn Marshall
 * @version October 21, 2012
 * 
 * @author Khalid Kayyem, 101277502
 * @version 16-03-2025
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
            "go", "quit", "help", "look", "eat", "back", "stackBack", "take", "drop", "charge", "fire"
    };



    /**
     * Constructor CommandWords - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid Command word. 
     * 
     * @param aString The String to check
     * @return true if it is valid, false otherwise
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /** 
     * Returns a space-separated list of all valid command words.
     * 
     * @return A string containing all valid commands.
     */
    public String getCommandList() {
        return String.join(" ", validCommands);
    }
}
