package Monopoly;
/**
 * A square on the Monopoly game board.
 * 
 * @author D.L. Bailey, SCE, Carleton University 
 * @version 1.13
 */
public class Square
{ 
    /**
     * The square's type.
     */
    private int type;
    
    /**
     * Types of squares.
     */
    /* An empty square is a placeholder for a square on the real Monopoly
     * game board that has not yet been implemented in a class.
     */
    public final static int EMPTY = 0;//
    
    /* The Go square. Players collect their salary when they reach this
     * square.
     */
    public final static int GO = 1;
    
    /* The Income Tax square. Players must pay income tax if they land
     * on this square.
     */
    public final static int INCOME_TAX = 2;
    
    /* A square with a street name. Players can purchase these squares.
     */
    public final static int LOT = 3;
    
    /**
     * The square's name.
     */
    private String name;
    
    /**
     * The square's number.
     */
    private int number;
    
    /**
     * The maximum amount of tax players must pay when they
     * land on an income tax square.
     */
    private Money maximumTax;
    
    /**
     * The amount players receives when they land on the GO square.
     */
    private Money salary;  
    
    /**
     * The purchase price of this square (only valid for squares of type LOT).
     * 
     */
    private Money price;    
 
    /**
     * Constructs a new square with the specified type, name and 
     * number.
     *
     * @param type The square's type (see the constant defintions
     *             for a list of valid types). The value of this
     *             parameter is not checked to ensure that it is
     *             valid.
     * @param name The square's name.
     * @param number The square's number.
     */
    public Square(int type, String name, int number)
    {
        this.type = type;
        this.name = name;
        this.number = number;
        
        if (type == GO) {
            salary = new Money(200, 0);
        } else if (type == INCOME_TAX) {
            maximumTax = new Money(200, 0);
        }
    }
    
    /**
     * Returns the description of this square.
     * 
     * @return a string containing the square's name and other
     *         information about the square. 
     */
    public String description()
    {       
        String s;
        s = name;

        if (type == EMPTY) {
            s = s + " (not implemented)";    
        } else if (type == GO) {
            s = s + ", collect " + salary.toString(); 
        } else if (type == INCOME_TAX) {
            s = s + ", maximum tax payable is " + maximumTax.toString();
        } else if (type == LOT) {
            s = s + ", price is " + price.toString();
        } 
        return s;        
    }

    /**
     * Returns this square's number.
     * 
     * @return the square's number.
     */
    public int number()
    {
        return number;
    }  
 
    /**
     * Sets this square's purchase price to the specified amount.
     * Should only be invoked on Square objects with type LOT.
     * 
     * @param amount The purchase price of this square.
     */
    public void setPrice(Money amount)
    {
        if (type == LOT)
            price = amount;
    }
    
    
        /**
     * Returns the type of this square.
     * 
     * @return the type of this square.
     */
    public int type() {
        return type;
    }
    
    /**
     * Returns the name of this square.
     * 
     * @return the name of this square.
     */
    public String name() {
        return name;
    }
    
    /**
     * Returns the maximum tax for this square.
     * 
     * @return the maximum tax for this square.
     */
    public Money maximumTax() {
        return maximumTax;
    }
    
    /**
     * Returns the salary for this square.
     * 
     * @return the salary for this square.
     */
    public Money salary() {
        return salary;
    }
    
    /**
     * Returns the price for this square.
     * 
     * @return the price for this square.
     */
    public Money price() {
        return price;
    }

}
