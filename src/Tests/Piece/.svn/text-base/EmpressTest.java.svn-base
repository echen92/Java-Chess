package Tests.Piece;

import Model.Board;
import Model.Move;
import Model.Piece.Empress;
import Model.Piece.Piece;
import Model.Player;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by erik on 2/13/14.
 */
public class EmpressTest {
    Board board;

    @Before
    public void setUp() throws Exception {
        board = new Board();
        board.initBoard();
    }

    @Test
    public void testIsValidMove() throws Exception {
        Piece empress = new Empress(Player.WHITE);
        board.setPieceAt(empress, 2,0);
        Move jumpToEnemyQueen = new Move(2,0,7,3);
        boolean success = empress.isValidMove(jumpToEnemyQueen);
        assert(success);
    }

    @Test
    public void canCaptureEnemyPieces() throws Exception {
        Piece empress = new Empress(Player.WHITE);
        board.setPieceAt(empress, 2,0);
        Move jumpToEnemyQueen = new Move(2,0,7,3);

        boolean success = board.movePiece(jumpToEnemyQueen);
        assert(success);
        assert(Board.getPieceAt(2,0) == null);
        assert(Board.getPieceAt(7,3) instanceof Empress);
    }
}
