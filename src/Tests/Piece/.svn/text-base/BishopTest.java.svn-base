package Tests.Piece;

import Model.Board;
import Model.Move;
import Model.Piece.Bishop;
import Model.Piece.Pawn;
import Model.Piece.Piece;
import Model.Player;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for Bishop class
 */
public class BishopTest {
    Board board;

    @Before
    public void setUp() throws Exception {
        board = new Board();
        board.initBoard();
    }

    @Test
    public void checkIsValidMove() throws Exception {
        Piece bishop = Board.getPieceAt(0, 2);
        Move invalidMove = new Move(0,2,2,4);
        assert(!bishop.isValidMove(invalidMove));

        Move movePawnOutOfWay = new Move(1,3,2,3);
        assert(board.movePiece(movePawnOutOfWay));

        assert(Board.getPieceAt(2, 3) instanceof Pawn);
        assert(Board.getPieceAt(1, 3) == null);


        Move moveBishopToBehindPawn = new Move(0,2,1,3);
        assert(bishop.isValidMove(moveBishopToBehindPawn));

        assert(board.movePiece(moveBishopToBehindPawn));
        assert(Board.getPieceAt(1, 3) instanceof Bishop);
    }

    @Test
    public void testBishopMovement() {
        Move movePawnOutOfWay = new Move(1,3,2,3);
        boolean pawnMoveSuccess;
        if (board.movePiece(movePawnOutOfWay)) pawnMoveSuccess = true;
        else pawnMoveSuccess = false;

        assert(pawnMoveSuccess);

        Move moveBishopNextToPawn = new Move(0,2,2,4);
        boolean bishopMoveSuccess = board.movePiece(moveBishopNextToPawn);

        assert(bishopMoveSuccess);
        assert(Board.getPieceAt(2, 4) instanceof Bishop);
    }

    @Test
    public void canCaptureEnemyPieces() throws Exception {
        Piece enemy = new Pawn(Player.BLACK);
        Move m = new Move(0,2,1,3);
        board.removePieceAt(1,3);
        board.setPieceAt(enemy, 1,3);

        assert(Board.getPieceAt(1, 3).getColorOfPiece() == Player.BLACK);
        assert(Board.getPieceAt(0, 2).getColorOfPiece() == Player.WHITE);

        boolean success = board.movePiece(m);
        assert(success);
    }

}
