package Tests.Piece;

import Model.Board;
import Model.Move;
import Model.Piece.Knight;
import Model.Piece.Pawn;
import Model.Piece.Piece;
import Model.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


/**
 * Created by erik on 2/4/14.
 */
public class KnightTest {
    Board board;

    @Before
    public void setUp() throws Exception {
        board = new Board();
        board.initBoard();
    }

    @Test
    public void testIsValidMove() throws Exception {
        Piece knight = Board.getPieceAt(0, 1);
        Move validMove = new Move(0,1,2,2);
        boolean success = knight.isValidMove(validMove);
        assert(success);

        Move invalidMove = new Move(0,1,1,3);
        success = knight.isValidMove(invalidMove);
        assert(!success);
    }

    @Test
    public void testKnightMovement() throws Exception {
        Move validMove = new Move(0,1,2,2);
        boolean success = board.movePiece(validMove);
        assert(success);

        Piece knight = new Knight(Player.WHITE);
        board.setPieceAt(knight, 0, 1);
        Move invalidMove = new Move(0,1,1,3);
        success = board.movePiece(invalidMove);
        assert(!success);
    }

    @Test
    public void canTakeEnemyPieces() throws Exception {
        Piece enemy = new Pawn(Player.BLACK);
        board.setPieceAt(enemy, 2, 2);
        Move killPawn = new Move(0, 1, 2, 2);

        boolean success = board.movePiece(killPawn);
        assertTrue(success);
    }
}
