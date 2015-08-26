package Model.Piece;

import Model.Board;
import Model.Move;
import Model.Player;

import static java.lang.Math.abs;

/**
 * A class representing a Knight piece
 */
public class Knight extends Piece {

    /**
     * Constructor for a Knight piece
     * @param c Player of the piece
     */
    public Knight(Player c) {
        super(c);
    }

    public boolean isValidMove(Move move)
    {
        int diffX = abs(move.getEndX() - move.getStartX());
        int diffY = abs(move.getEndY() - move.getStartY());

        Piece dest = Board.getPieceAt(move.getEndX(), move.getEndY());
        boolean validDestination;
        if(dest == null)
            validDestination = true;
        else validDestination = dest.getColorOfPiece() != this.getColorOfPiece();

        return ((diffX == 1 && diffY == 2) || (diffX == 2 && diffY == 1)) && validDestination;
    }

    public boolean isPathObstructed(Move move) {
        // Not needed for knights
        return false;
    }
}
