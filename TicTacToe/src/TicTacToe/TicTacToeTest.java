package TicTacToe;

import org.junit.Test;
import static org.junit.Assert.*;

public class TicTacToeTest {

    @Test(expected = RuntimeException.class)
    public void testInvalidCoordinates() {
        TicTacToe game = new TicTacToe();
        game.play(-1, 0);
    }

    @Test(expected = RuntimeException.class)
    public void testCellOccupied() {
        TicTacToe game = new TicTacToe();
        game.play(0, 0);
        game.play(0, 0);
    }

    @Test
    public void testGameInProgress() {
        TicTacToe game = new TicTacToe();
        game.play(0, 0);
        assertEquals('-', game.checkWinner());
    }

    @Test
    public void testPlayerWins() {
        TicTacToe game = new TicTacToe();
        game.play(0, 0);
        game.play(1, 0);
        game.play(0, 1);
        game.play(1, 1);
        game.play(0, 2);
        assertEquals('X', game.checkWinner());
    }

    @Test
    public void testDraw() {
        TicTacToe game = new TicTacToe();
        game.play(0, 0);
        game.play(1, 1);
        game.play(0, 1);
        game.play(0, 2);
        game.play(1, 0);
        game.play(1, 2);
        game.play(2, 0);
        game.play(2, 1);
        game.play(2, 2);
        assertEquals('D', game.checkWinner());
    }

    @Test
    public void testInitializedBoard() {
        TicTacToe game = new TicTacToe();
        char[][] expectedBoard = {
            {'-', '-', '-'},
            {'-', '-', '-'},
            {'-', '-', '-'}
        };
        assertArrayEquals(expectedBoard, game.getBoard());
    }

    @Test
    public void testPlayerSwitch() {
        TicTacToe game = new TicTacToe();
        game.play(0, 0);
        assertEquals('O', game.getCurrentPlayer());
    }

    @Test
    public void testMoveOrder() {
        TicTacToe game = new TicTacToe();
        game.play(0, 0);
        game.play(0, 1);
        assertEquals('O', game.getBoard()[0][1]);
    }

    @Test
    public void testGameCompletionWithWinner() {
        TicTacToe game = new TicTacToe();
        game.play(0, 0);
        game.play(1, 0);
        game.play(0, 1);
        game.play(1, 1);
        game.play(0, 2);
        assertEquals('X', game.checkWinner());
        assertTrue(game.isGameOver());
    }

    @Test
    public void testGameCompletionWithDraw() {
        TicTacToe game = new TicTacToe();
        game.play(0, 0);
        game.play(1, 1);
        game.play(0, 1);
        game.play(0, 2);
        game.play(1, 0);
        game.play(1, 2);
        game.play(2, 0);
        game.play(2, 1);
        game.play(2, 2);
        assertEquals('D', game.checkWinner());
        assertTrue(game.isGameOver());
    }
}
