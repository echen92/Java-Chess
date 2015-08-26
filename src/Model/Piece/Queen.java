package Model.Piece;

import Model.Board;
import Model.Move;
import Model.Player;

import static java.lang.Math.abs;

/**
 * A class representing the Queen piece
 */
public class Queen extends Piece {

    /**
     * Constructor for a Queen piece
     * @param c Player of the piece
     */
    public Queen(Player c) {
        super(c);
    }

    public boolean isValidMove(Move move)
    {
        boolean validDestination;
        Piece dest = Board.getPieceAt(move.getEndX(), move.getEndY());

        if(dest == null)
            validDestination = true;
        else validDestination = (dest.getColorOfPiece() != this.getColorOfPiece());

        boolean pathBlocked = isPathObstructed(move);

        return !pathBlocked && validDestination;
    }

    public boolean isPathObstructed(Move move) {
        Piece rook = new Rook(this.getColorOfPiece());
        Piece bishop = new Bishop(this.getColorOfPiece());
        int startX = move.getStartX();
        int startY = move.getStartY();
        int endX = move.getEndX();
        int endY = move.getEndY();
        int diffX = abs(endX-startX);
        int diffY = abs(endY-startY);

        boolean canMoveStraight = rook.isValidMove(move);
        boolean canMoveDiag = bishop.isValidMove(move);

        if (diffX == diffY) {
            return !canMoveDiag;
        }
        else if (diffX == 0 ^ diffY == 0) {
            return !canMoveStraight;
        }

        // should never be reached
        return true;
    }
}
