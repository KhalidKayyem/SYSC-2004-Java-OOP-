import java.util.Stack;

/**
 * The Game class is the main class of the "World of Zuul" application. 
 * "World of Zuul" is a simple text-based adventure game where users 
 * can explore different rooms, interact with items, and navigate using commands.
 * 
 * To play this game, create an instance of this class and call the "play" method.
 * 
 * This class is responsible for setting up the game by creating rooms, 
 * placing items, and managing user input. It processes commands that allow 
 * players to move between rooms, inspect their surroundings, interact with 
 * objects, and track their movement history using the "back" and "stackBack" commands.
 * 
 * The game includes essential commands such as moving between rooms, 
 * looking around, eating items, and quitting the game.
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

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room previousRoom;
    private Stack<Room> roomHistory;    // Tracks the full movement history for "stackBack"
    private Item carriedItem;
    private boolean isHungry;
    private int itemsPickedUp;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        this.previousRoom = null;
        this.roomHistory = new Stack<>();
        this.carriedItem = null;
        this.isHungry = true;
        this.itemsPickedUp = 0;
    }

    /**
     * Create all the rooms and link their exits together. Also creats the items and adds them to the rooms
     */
    private void createRooms()
    {
        Room outside, theatre, pub, lab, office, transporterRoom;
      
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        transporterRoom = new TransporterRoom("inside a mysterious portal chamber");
        
        // initialise room exits
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        transporterRoom.setExit("out", transporterRoom);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);
        office.setExit("portal", transporterRoom);

        // Create items
        Item car = new Item("car","a grey car", 1.5);
        Item book = new Item("book","an old dusty book", 1.5);
        Item laptop = new Item("laptop","a powerful gaming laptop", 2.3);
        Item key = new Item("key","a small rusty key", 0.1);
        Item coffee = new Item("coffee","a cup of coffee", 0.5);
        Item cookie1 = new Item("cookie", "a delicious chocolate chip cookie", 0.2);
        Item cookie2 = new Item("cookie", "a tasty oatmeal raisin cookie", 0.2);
        Beamer beamer1 = new Beamer("beamer", "a futuristic teleportation device", 2.0);
        Beamer beamer2 = new Beamer("beamer", "a second teleportation device", 2.0);

        // Add items to rooms
        outside.addItem(car);
        office.addItem(book);
        lab.addItem(laptop);
        pub.addItem(coffee);
        theatre.addItem(key);
        pub.addItem(cookie1);
        outside.addItem(cookie2);
        lab.addItem(beamer1);
        theatre.addItem(beamer2);

        currentRoom = outside;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("You must find and eat a cookie before picking up other items.");
        System.out.println("You can pick up five items before needing another cookie.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * 
     * @param command The command to be processed
     * @return true If the command ends the game, false otherwise
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            look(command);
        }
        else if (commandWord.equals("eat")) {
            eat(command);
        }
        else if (commandWord.equals("back")) {
            goBack(command);
        }
        else if (commandWord.equals("stackBack")) {
            stackBack(command);
        }
        else if (commandWord.equals("take")) {
            take(command);
        }
        else if (commandWord.equals("drop")) {
            drop(command);
        }
        else if (commandWord.equals("charge")) {
            charge(command);
        }
        else if (commandWord.equals("fire")) {
            fire(command);
        }

        // else command not recognised.
        return wantToQuit;
    }

    /**
     * Print out some help information.
     * Here we print a cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println("You must find a cookie before picking up other items.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.getCommands());
    }

    /** 
     * "goRoom" Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * 
     * @param command The command to be processed
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            roomHistory.push(currentRoom);  // Store current room in history
            previousRoom = currentRoom;  // Set lastRoom before moving
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            if (carriedItem != null) {
                System.out.println("You are carrying: " + carriedItem.getDescription());
            }
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * 
     * @param command The command to be processed
     * @return true, if this command quits the game, false otherwise
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * "Look" prints the description of the current room
     * 
     * 
     * @param command The command that will be processed
     */
    private void look(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Look what?");
            return;
        }
        System.out.println(currentRoom.getLongDescription());      
        if (carriedItem != null) {
            System.out.println("You are carrying: " + carriedItem.getDescription());
        } else {
            System.out.println("You are not carrying anything.");
        }
    }
    
    /**
     * "Eat" Prints a message that indicates the player has eaten
     * 
     * 
     * @param command The command to be processed
     */
    private void eat(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Eat what?");
            return;
        }

        if (carriedItem == null) {
            System.out.println("You have no food to eat.");
        } else if (carriedItem.getName().equalsIgnoreCase("cookie")) {
            System.out.println("You have eaten the " + carriedItem.getName() + ". You feel refreshed!");
            carriedItem = null; // Remove the cookie after eating
            isHungry = false;
            itemsPickedUp = 0;
        } else {
            System.out.println("You cannot eat that!");
        }
    }


    /**
     * "goBack" Moves the player back to the previous room
     * 
     * 
     * @param command The command to be processed
     */
    private void goBack(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Back what?");
            return;
        }

        if (previousRoom == null) {
            System.out.println("You cannot go back. No previous room.");
        } else {
            roomHistory.push(currentRoom);
            Room temp = currentRoom;  // Swap current and last room
            currentRoom = previousRoom;
            previousRoom = temp;
            System.out.println("You have gone back to: " + currentRoom.getLongDescription());
        }
    }
    
    /**
     * "stackBack" Moves the player back through the movement history stack
     * 
     * 
     * @param command The command to be processed
     */
    private void stackBack(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("StackBack what?");
            return;
        }

        if (roomHistory.isEmpty()) {
            System.out.println("You cannot stack back. No previous room.");
        } else {
            previousRoom = currentRoom;  // Update lastRoom for accurate toggling
            currentRoom = roomHistory.pop();
            System.out.println("You have gone back to: " + currentRoom.getLongDescription());
        }
    }
    
    /**
     * take. Attempts to pick up an item from the current room. This method checks if the player is holding an item and whether the specified item
     * exists in the room. If the player is hungry, they can only pick up a "cookie".
     * Otherwise, the item is added to the player's carried item slot, and the room's 
     * item list is updated.
     * 
     * 
     * @param command The command containing the item name to be picked up.
     */
    private void take(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Take what?");
            return;
        }

        String itemName = command.getSecondWord();

        if (carriedItem != null) {
            System.out.println("You are already holding something.");
            return;
        }

        Item itemToTake = currentRoom.getItem(itemName);

        if (itemToTake == null) {
            System.out.println("That item is not in the room.");
        } else if (isHungry && !itemToTake.getName().equalsIgnoreCase("cookie")) {
            System.out.println("You are too hungry to pick up anything but a cookie!");
        } else {
            carriedItem = itemToTake;
            currentRoom.removeItem(itemName);
            System.out.println("You picked up " + itemName + ".");

            if (!itemToTake.getName().equalsIgnoreCase("cookie")) {
                itemsPickedUp++;
            }

            if (itemsPickedUp >= 5) {
                isHungry = true;
                System.out.println("You are starting to feel hungry again.");
            }
        }
    }
    
    /**
     * drop. Drops the currently carried item into the current room. In this method, if the player is holding an item, it is placed back into the room,
     * and the player's carried item slot is cleared. If the player is not
     * carrying anything, a message is displayed indicating that there is
     * nothing to drop.
     * 
     * @param command The command input from the player. (included for consistency but not used).
     */
    private void drop(Command command) {
        if (carriedItem == null) {
            System.out.println("You have nothing to drop.");
            return;
        }

        currentRoom.addItem(carriedItem);
        System.out.println("You have dropped " + carriedItem.getName() + ".");
        carriedItem = null;
    }
    
    /**
     * charge. Charges the Beamer with the current room. This method checks if the player is carrying a Beamer. If they are not,
     * an error message is displayed. If the player is holding a Beamer, it is 
     * charged with the current room, allowing future teleportation.
     * 
     * @param command The command input from the player (included for consistency but not used).
     */
    private void charge(Command command) {
        if (carriedItem == null || !(carriedItem instanceof Beamer)) {
            System.out.println("You are not carrying a beamer to charge!");
            return;
        }

        Beamer beamer = (Beamer) carriedItem;
        beamer.charge(currentRoom);
    }
    
    /**
     * fire. This method Fires the Beamer to teleport the player to the previously charged room.
     * 
     * This method checks if the player is carrying a Beamer. If they are not,
     * an error message is displayed. If the Beamer is carried, it attempts to 
     * teleport the player to the room it was charged in. If the Beamer was 
     * never charged, the teleportation fails.
     * 
     * @param command The command input from the player (included for consistency but not used).
     */
    private void fire(Command command) {
        if (carriedItem == null || !(carriedItem instanceof Beamer)) {
            System.out.println("You are not carrying a beamer to fire!");
            return;
        }

        Beamer beamer = (Beamer) carriedItem;
        Room teleportedRoom = beamer.fire();

        if (teleportedRoom != null) {
            currentRoom = teleportedRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

}
