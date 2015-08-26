package Tests.Piece;

import Model.Board;
import Model.Move;
import Model.Piece.Pawn;
import Model.Piece.Piece;
import Model.Piece.Queen;
import Model.Player;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by erik on 2/5/14.
 */
public class QueenTest {
    Board board;

    @Before
    public void setUp() throws Exception {
        board = new Board();
        board.initBoard();
    }

    @Test
    public void testIsValidMove() throws Exception {
        Piece queen = Board.getPieceAt(0,3);
        Move moveQueenForward = new Move(0,3,1,3);
        Move moveQueenDiagonal = new Move(0,3,1,4);

        boolean success = queen.isValidMove(moveQueenForward);
        assert(!success);

        success = queen.isValidMove(moveQueenDiagonal);
        assert(!success);

        Move movePawnOutOfWay1 = new Move(1,3,2,3);
        Move movePawnOutOfWay2 = new Move(1,4,2,4);
        board.movePiece(movePawnOutOfWay1);
        board.movePiece(movePawnOutOfWay2);

        success = queen.isValidMove(moveQueenForward);
        assert(success);

        success = queen.isValidMove(moveQueenDiagonal);
        assert(success);
    }

    @Test
    public void testQueenMovement() throws Exception {
        Piece queen = Board.getPieceAt(0,3);
        Move moveQueenForward = new Move(0,3,1,3);
        Move moveQueenDiagonal = new Move(0,3,1,4);

        boolean success = board.movePiece(moveQueenForward);
        assert(!success);

        success = board.movePiece(moveQueenDiagonal);
        assert(!success);

        Move movePawnOutOfWay1 = new Move(1,3,2,3);
        Move movePawnOutOfWay2 = new Move(1,4,2,4);
        board.movePiece(movePawnOutOfWay1);
        board.movePiece(movePawnOutOfWay2);

        success = board.movePiece(moveQueenForward);
        assert(success);

        Piece queen2 = new Queen(Player.WHITE);
        board.setPieceAt(queen2, 0, 3);
        success = board.movePiece(moveQueenDiagonal);
        assert(success);
    }

    @Test
    public void canCaptureEnemyPieces() {
        board.removePieceAt(1,3);
        Piece enemy = new Pawn(Player.BLACK);
        board.setPieceAt(enemy, 1, 3);

        Move killEnemy = new Move(0,3,1,3);
        boolean success = board.movePiece(killEnemy);
        assert(success);
    }

}
