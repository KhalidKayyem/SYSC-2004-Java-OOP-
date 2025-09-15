package Test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import Monopoly.Board;
import Monopoly.Square;

public class BoardTest {

    private Board board;

    @Before
    public void setUp() {
        System.out.println("Setting up before each test");
        board = new Board();
    }

    @After
    public void tearDown() {
        System.out.println("Tearing down after each test");
        board = null;
    }

    public void init() {
        System.out.println("Initializing test class");
    }

    public void close() {
        System.out.println("Closing test class");
    }

    @Test
    public void testStartingSquare() {
        System.out.println("Testing starting square");
        assertEquals(1, board.startingSquare().number());
    }

    @Test
    public void testSquareNumbering() {
        System.out.println("Testing square numbering");
        for (int i = 1; i <= 40; i++) {
            assertEquals(i, board.nextSquare(board.startingSquare(), i - 1).number());
        }
    }

    @Test
    public void testNextSquare() {
        System.out.println("Testing nextSquare method");
        for (int distance = 2; distance <= 12; distance++) {
            int expectedNumber = (distance % 40 == 0) ? 40 : distance % 40;
            assertEquals(expectedNumber, board.nextSquare(board.startingSquare(), distance - 1).number());
        }
    }

    @Test
    public void testBoardWrapAround() {
        System.out.println("Testing board wrap around");
        Square square40 = board.nextSquare(board.startingSquare(), 39); // Square 40
        Square wrapAroundSquare = board.nextSquare(square40, 1); // Moving forward from square 40
        assertEquals(1, wrapAroundSquare.number()); // Should wrap around to square 1
    }


}
