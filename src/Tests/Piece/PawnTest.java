package Tests.Piece;

import Model.Board;
import Model.Move;
import Model.Piece.Pawn;
import Model.Piece.Piece;
import Model.Player;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by erik on 2/4/14.
 */
public class PawnTest {
    Board board;

    @Before
    public void setUp() throws Exception {
        board = new Board();
        board.initBoard();
    }

    @Test
    public void checkIsValidMove() throws Exception {
        Piece p = board.getPieceAt(1,0);
        Move move = new Move(1,0,3,0);
        assertTrue(p.isValidMove(move));
    }

    @Test
    public void canCaptureEnemyPieces() {
        Piece whitePawn = board.getPieceAt(1,0);
        Piece blackPawn = new Pawn(Player.BLACK);
        board.setPieceAt(blackPawn,2,1);

        Move killBlackPawn = new Move(1,0,2,1);
        boolean success = board.movePiece(killBlackPawn);

        assert(success);
        assertNull(board.getPieceAt(1, 0));
        assertEquals(board.getPieceAt(2,1).getColorOfPiece(), Player.WHITE);
    }

    @Test
    public void cannotMoveMoreThan2SpacesFirstMove() throws Exception {
        Piece p = board.getPieceAt(1,0);
        Move move = new Move(1,0,5,0);
        assertTrue(!p.isValidMove(move));
    }
}
