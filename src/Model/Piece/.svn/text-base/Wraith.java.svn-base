package Model.Piece;

import Model.Board;
import Model.Move;
import Model.Player;

import static java.lang.Math.abs;

/**
 * A Wraith piece can move like a Bishop and Knight
 */
public class Wraith extends Piece {

    /**
     * Constructor for a Wraith piece
     * @param c Player of the piece
     */
    public Wraith(Player c) {
        super(c);
    }

    public boolean isValidMove(Move move) {
        boolean validDestination;
        Piece dest = Board.getPieceAt(move.getEndX(), move.getEndY());

        if(dest == null)
            validDestination = true;
        else validDestination = (dest.getColorOfPiece() != this.getColorOfPiece());

        boolean pathBlocked = isPathObstructed(move);

        return !pathBlocked && validDestination;
    }

    public boolean isPathObstructed(Move move) {
        Piece knight = new Knight(this.getColorOfPiece());
        Piece bishop = new Bishop(this.getColorOfPiece());
        int startX = move.getStartX();
        int startY = move.getStartY();
        int endX = move.getEndX();
        int endY = move.getEndY();
        int diffX = abs(endX-startX);
        int diffY = abs(endY-startY);

        boolean canMoveKnight = knight.isValidMove(move);
        boolean canMoveDiag = bishop.isValidMove(move);

        if (diffX == diffY) {
            return !canMoveDiag;
        }
        else if ((diffX == 1 && diffY == 2) || (diffX == 2 && diffY == 1)) {
            return !canMoveKnight;
        }

        // should never be reached
        return true;
    }

}
