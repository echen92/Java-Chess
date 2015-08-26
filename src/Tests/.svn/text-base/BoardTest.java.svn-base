package Tests;

import Model.Board;
import Model.Move;
import Model.Piece.Pawn;
import Model.Piece.Piece;
import Model.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by erik on 2/3/14.
 */
public class BoardTest {
    Board board;

    @Before
    public void setUp() throws Exception {
        board = new Board();
        board.initBoard();
    }

    @Test
    public void checkBoardBoundaries() throws Exception {
        assertTrue(Board.getBoard().length < 9);
    }

    @Test
    public void checkEmptySpaces() throws Exception {
        assertNull(Board.getBoard()[3][3]);
    }

    @Test
    public void checkStartingPlayerWhite() throws Exception {
        assertEquals(board.getPlayerTurn(), Player.WHITE);
    }

    @Test
    public void checkGetPieceCorrect() throws Exception {
        assertTrue(Board.getPieceAt(1, 0) instanceof Pawn);
    }

    @Test
    public void pawnMoveWasMadeProperly() throws Exception {
        Move m = new Move(1, 0, 3, 0);
        Piece p = Board.getPieceAt(1, 0);
        boolean moveSuccessful = board.movePiece(m);

        assertTrue(moveSuccessful);
        assertEquals(Board.getPieceAt(3, 0), p);
        assertNull(Board.getPieceAt(1, 0));
    }

    @Test
    public void knightMoveWasMadeProperly() throws Exception {
        Move m = new Move(0, 1, 2, 0);
        Piece p = board.getPieceAt(0, 1);
        boolean moveSuccessful = board.movePiece(m);

        assertTrue(moveSuccessful);
        assertEquals(board.getPieceAt(2, 0), p);
        assertNull(board.getPieceAt(0, 1));
    }
}
