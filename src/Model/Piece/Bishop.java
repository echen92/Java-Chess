package Model.Piece;

import Model.Board;
import Model.Move;
import Model.Player;

import static java.lang.Math.abs;

/**
 * A class representing the Bishop piece
 */
public class Bishop extends Piece {

    /**
     * Constructor for a Bishop
     * @param c Player of the piece
     */
    public Bishop(Player c) {
        super(c);
    }

    public boolean isValidMove(Move move)
    {
        int diffX = abs(move.getEndX()-move.getStartX());
        int diffY = abs(move.getEndY()-move.getStartY());
        Piece dest = Board.getPieceAt(move.getEndX(), move.getEndY());
        boolean validDestination;

        if(dest == null)
            validDestination = true;
        else validDestination = (dest.getColorOfPiece() != this.getColorOfPiece());

        return (diffX == diffY) && (diffX != 0) && !isPathObstructed(move) && validDestination;
    }

    public boolean isPathObstructed(Move move) {
        int startX = move.getStartX();
        int startY = move.getStartY();
        int endX = move.getEndX();
        int endY = move.getEndY();

        int dirX = direction(startX, endX);
        int dirY = direction(startY, endY);
        int incrX = dirX;
        int incrY = dirY;

        while (startX+incrX < endX) {
            Piece currPiece = Board.getPieceAt(startX+incrX, startY+incrY);

            if (currPiece != null)
                return true;

            incrX += dirX;
            incrY += dirY;
        }

        return false;
    }

    /**
     * Helper function to determine direction of travel
     * @param start Starting position
     * @param end Ending position
     * @return 1 if end - start > 0, -1 otherwise
     */
    public int direction(int start, int end) {
        return (end-start > 0) ? 1 : -1;
    }
}
