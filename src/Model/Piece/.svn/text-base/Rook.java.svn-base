package Model.Piece;

import Model.Board;
import Model.Move;
import Model.Player;

import static java.lang.Math.abs;

/**
 * A class representing a Rook piece
 */
public class Rook extends Piece {

    /**
     * Constructor for a Rook piece
     * @param c Player of the piece
     */
    public Rook(Player c) {
        super(c);
    }

    public boolean isValidMove(Move move)
    {
        int diffX = abs(move.getEndX()-move.getStartX());
        int diffY = abs(move.getEndY()-move.getStartY());

        return (diffX == 0 || diffY == 0) && !isPathObstructed(move);
    }

    public boolean isPathObstructed(Move move) {
        int startX = move.getStartX();
        int startY = move.getStartY();
        int endX = move.getEndX();
        int endY = move.getEndY();
        int diffX = abs(startX-endX);
        int diffY = abs(startY-endY);


        if (diffX == 0) {
            if (endY - startY < 0)
                return !canMoveLeft(move);
            else
                return !canMoveRight(move);
        }

        if (diffY == 0) {
            if (endX - startX < 0)
                return !canMoveDown(move);
            else
                return !canMoveUp(move);
        }

        return false;
    }

    /**
     * Checks if can move up
     * @param move The move to execute
     * @return True if path moving up not blocked, false otherwise
     */
    protected boolean canMoveUp(Move move) {
        Piece dest = Board.getPieceAt(move.getEndX(), move.getEndY());
        boolean notSameColor = dest == null || (dest.getColorOfPiece() != this.getColorOfPiece());

        for (int i = move.getStartX()+1; i < move.getEndX()+1; i++) {
            if (i == move.getEndX() && notSameColor)
                return true;
            if (Board.getPieceAt(i, move.getStartY()) != null)
                return false;
        }

        return true;
    }

    /**
     * Checks if can move down
     * @param move The move to execute
     * @return True if path moving down not blocked, false otherwise
     */
    protected boolean canMoveDown(Move move) {
        Piece dest = Board.getPieceAt(move.getEndX(), move.getEndY());
        boolean notSameColor = dest == null || (dest.getColorOfPiece() != this.getColorOfPiece());

        for (int i = move.getStartX()-1; i > move.getEndX()-1; i--) {
            if (i == move.getEndX() && notSameColor)
                return true;
            if (Board.getPieceAt(i, move.getStartY()) != null)
                return false;
        }

        return true;
    }

    /**
     * Checks if can move left
     * @param move The move to execute
     * @return True if path moving left not blocked, false otherwise
     */
    protected boolean canMoveLeft(Move move) {
        Piece dest = Board.getPieceAt(move.getEndX(), move.getEndY());
        boolean notSameColor = dest == null || (dest.getColorOfPiece() != this.getColorOfPiece());

        for (int i = move.getStartY()-1; i > move.getEndY()-1; i--) {
            if (i == move.getEndX() && notSameColor)
                return true;
            if (Board.getPieceAt(move.getStartX(), i) != null)
                return false;
        }

        return true;
    }

    /**
     * Checks if can move right
     * @param move The move to execute
     * @return True if path moving right not blocked, false otherwise
     */
    protected boolean canMoveRight(Move move) {
        Piece dest = Board.getPieceAt(move.getEndX(), move.getEndY());
        boolean notSameColor = dest == null || (dest.getColorOfPiece() != this.getColorOfPiece());

        for (int i = move.getStartY()+1; i < move.getEndY()+1 ; i++) {
            if (i == move.getEndX() && notSameColor)
                return true;
            if (Board.getPieceAt(move.getStartX(), i) != null)
                return false;
        }

        return true;
    }

}
