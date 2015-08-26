package Model.Piece;

import Model.Board;
import Model.Move;
import Model.Player;

import static java.lang.Math.abs;

/**
 * A class representing a King piece
 */
public class King extends Piece {

    /**
     * Constructor for a King piece
     * @param c Player of the piece
     */
    public King(Player c) {
        super(c);
    }

    public boolean isValidMove(Move move)
    {
        int diffX = abs(move.getEndX()-move.getStartX());
        int diffY = abs(move.getEndY()-move.getStartY());

        return (diffX <= 1 && diffY <= 1) && !isPathObstructed(move);
    }

    public boolean isPathObstructed(Move move) {
        Piece p = Board.getPieceAt(move.getEndX(), move.getEndY());
        boolean validDestination = (p == null) || (p.getColorOfPiece() != this.getColorOfPiece());

        return !validDestination;
    }

    /**
     * Determines if the king is in check or checkmate
     * @return True if in check/checkmate, false otherwise
     */
    public boolean isInCheckOrCheckmate() {
        // TODO: Implement check and checkmate
        return false;
    }

}
