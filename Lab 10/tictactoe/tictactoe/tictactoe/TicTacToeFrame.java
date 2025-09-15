import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A class modelling a tic-tac-toe (noughts and crosses, Xs and Os) game in a very
 * simple GUI window.
 * 
 * @author Khalid Kayyem
 * @version March 26, 2025
 */

public class TicTacToeFrame extends TicTacToe 
{ 
   private JTextArea status; // text area to print game status
   
   /** 
    * Constructs a new Tic-Tac-Toe board and sets up the basic
    * JFrame containing a JTextArea in a JScrollPane GUI.
    */
   public TicTacToeFrame()
   { 
       super();
       
       JFrame frame = new JFrame("Tic Tac Toe");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(300, 300);
       status = new JTextArea(10, 30);
       status.setEditable(false);
       JScrollPane scrollPane = new JScrollPane(status);
       frame.add(scrollPane);
       frame.setVisible(true);
    }
   
   /**
    * Prints the board to the GUI using toString().
    */
    public void print() {  
        status.append(toString());
    }
}