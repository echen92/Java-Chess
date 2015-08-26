package Tests.Piece;

import Model.Board;
import Model.Move;
import Model.Piece.Pawn;
import Model.Piece.Piece;
import Model.Player;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by erik on 2/5/14.
 */
public class RookTest {
    Board board;

    @Before
    public void setUp() throws Exception {
        board = new Board();
        board.initBoard();
    }

    @Test
    public void testIsValidMove() throws Exception {
        Piece rook = Board.getPieceAt(0,0);
        Move blockedByAlly = new Move(0,0,4,4);
        boolean success = rook.isValidMove(blockedByAlly);
        assert(!success);

        Move validMove = new Move(0,0,2,0);
        board.removePieceAt(1, 0);
        success = rook.isValidMove(validMove);
        assert(success);
    }

    @Test
    public void testRookMovement() throws Exception {
        Move blockedByAlly = new Move(0,0,4,4);
        boolean success = board.movePiece(blockedByAlly);
        assert(!success);

        Move validMove = new Move(0,0,2,0);
        board.removePieceAt(1, 0);
        success = board.movePiece(validMove);
        assert(success);
    }

    @Test
    public void canCaptureEnemyPieces() throws Exception {
        board.removePieceAt(1, 0);
        Piece enemy = new Pawn(Player.BLACK);
        board.setPieceAt(enemy, 1, 0);
        Move killEnemy = new Move(0, 0, 1, 0);
        boolean success = board.movePiece(killEnemy);
        assert(success);
    }
}
