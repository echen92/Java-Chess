package Tests.Piece;

import Model.Board;
import Model.Move;
import Model.Piece.Pawn;
import Model.Piece.Piece;
import Model.Piece.Wraith;
import Model.Player;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by erik on 2/13/14.
 */
public class WraithTest {
    Board board;

    @Before
    public void setUp() throws Exception {
        board = new Board();
        board.initBoard();
    }

    @Test
    public void testIsValidMove() throws Exception {
        Piece wraith = new Wraith(Player.WHITE);
        board.setPieceAt(wraith, 2,0);
        Move validMove = new Move(2,0,3,2);
        Move invalidMove = new Move(2,0,5,0);

        assert(wraith.isValidMove(validMove));
        assert(!wraith.isValidMove(invalidMove));
    }

    @Test
    public void canCaptureEnemyPieces() throws Exception {
        Piece wraith = new Wraith(Player.WHITE);
        Piece enemy = new Pawn(Player.BLACK);
        board.setPieceAt(enemy, 3,2);
        board.setPieceAt(wraith, 2,0);
        Move killEnemy = new Move(2,0,3,2);
        Move invalidMove = new Move(2,0,5,0);

        assert(board.movePiece(killEnemy));
    }
}
