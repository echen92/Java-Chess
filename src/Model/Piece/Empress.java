package Model.Piece;

import Model.Board;
import Model.Move;
import Model.Player;

/**
 * An Empress piece can move anywhere on the map but may not
 * kill or put the King in check/checkmate
 */
public class Empress extends Piece {

    /**
     * Constructor for an Empress piece
     * @param c Player of the piece
     */
    public Empress(Player c) {
        super(c);
    }

    public boolean isValidMove(Move move) {
        int startX = move.getStartX();
        int startY = move.getStartY();
        int endX = move.getEndX();
        int endY = move.getEndY();
        Piece dest = Board.getPieceAt(endX, endY);

        if(startX == endX && startY == endY)
            return false;

        if(dest == null)
            return true;

        return (dest.getColorOfPiece() != this.getColorOfPiece()) && !(dest instanceof King);
    }

    public boolean isPathObstructed(Move move) {
        return false;
    }
}
