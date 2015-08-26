package Tests.Piece;

import Model.Board;
import Model.Move;
import Model.Piece.Pawn;
import Model.Piece.Piece;
import Model.Player;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by erik on 2/4/14.
 */
public class KingTest {
    Board b;

    @Before
    public void setUp() throws Exception {
        b = new Board();
        b.initBoard();
    }

    @Test
    public void checkIsValidMove() throws Exception {
        Piece king = Board.getPieceAt(0,4);
        Move moveKingWhileBlocked = new Move(0,4,1,4);
        assert(!king.isValidMove(moveKingWhileBlocked));

        Move moveKingsPawn = new Move(1,4,2,4);
        boolean movedKingsPawn = b.movePiece(moveKingsPawn);
        assert(movedKingsPawn);

        Move moveKingFwd = new Move(0,4,1,4);
        assert(king.isValidMove(moveKingFwd));
    }

    @Test
    public void testKingMovement() throws Exception {
        Move moveKingsPawn = new Move(1,4,2,4);
        b.movePiece(moveKingsPawn);
        assert(Board.getPieceAt(2,4) instanceof Pawn);

        Move moveKingFwd = new Move(0,4,1,4);
        boolean kingMoveSuccessful = b.movePiece(moveKingFwd);
        assert(kingMoveSuccessful);
    }

    @Test
    public void canCaptureEnemyPieces() throws Exception {
        Piece enemy = new Pawn(Player.BLACK);
        b.removePieceAt(1,4);
        b.setPieceAt(enemy, 1,4);
        assert(Board.getPieceAt(1,4).getColorOfPiece() == Player.BLACK);

        Move killPawn = new Move(0,4,1,4);
        boolean success = b.movePiece(killPawn);
        assert(success);
    }
}
