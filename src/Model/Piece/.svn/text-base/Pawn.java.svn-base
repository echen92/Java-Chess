package Model.Piece;

import Model.Board;
import Model.Move;
import Model.Player;

import static java.lang.Math.abs;

/**
 * A class representing a Pawn piece
 */
public class Pawn extends Piece {

    /**
     * Default constructor for a pawn
     * @param c The player it belongs to
     */
    public Pawn(Player c) {
        super(c);
    }

    public boolean isValidMove(Move move)
    {
        return validMoveDistance(move) && !isPathObstructed(move);
    }

    /**
     * Checks if the distance the pawn is to move is valid
     *
     * @param move Representation of source and target location
     * @return True if moving <= 2 spaces on first move or 1 space otherwise, false if moving more than 2 or 0 spaces
     */
    public boolean validMoveDistance(Move move) {
        int startX = move.getStartX();
        int endX = move.getEndX();

        // Move 2 spaces from first turn
        if (((startX == 1 && getColorOfPiece() == Player.WHITE) || (startX == 6 && getColorOfPiece() == Player.BLACK))
                && abs(endX - startX) <= 2)
            return true;
        // Move 1 space if previously moved
        else if (((startX != 1 && getColorOfPiece() == Player.WHITE) || (startX != 6 && getColorOfPiece() == Player.BLACK))
                && abs(endX - startX) <= 1)
            return true;
        else
            return false;
    }

    public boolean isPathObstructed(Move move) {
        int diffX = abs(move.getStartX()-move.getEndX());
        int diffY = abs(move.getStartY()-move.getEndY());
        int dir = direction(this.getColorOfPiece());
        int incrX = dir;

        Piece dest = Board.getPieceAt(move.getEndX(), move.getEndY());
        boolean destNotSameColor = dest == null || dest.getColorOfPiece() != this.getColorOfPiece();
        boolean pieceKillableAtDest = (diffX == 1 && diffY == 1) && destNotSameColor;

        // Checks if can capture piece
        if (pieceKillableAtDest)
            return false;

        // Checks movement path
        while (move.getStartX()+incrX != move.getEndX()) {
            Piece p2 = Board.getPieceAt(move.getStartX()+incrX, move.getStartY());

            if (p2 != null)
                return true;

            incrX += dir;
        }

        return false;
    }

    /**
     * Helper function to determine which way a pawn moves along board
     * @param c Player of the piece
     * @return 1 if white, -1 if black
     */
    public int direction(Player c) {
        return c == Player.WHITE ? 1 : -1;
    }

}
